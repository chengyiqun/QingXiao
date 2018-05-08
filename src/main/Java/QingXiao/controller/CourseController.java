package QingXiao.controller;

import QingXiao.entity.UserInform;
import QingXiao.service.CourseService;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by xpb on 2017/10/24.
 */
@Controller
@RequestMapping("/Course")
public class CourseController {

    /*
    获取教务处验证码
    */
    @RequestMapping(value="GetIdentifyCode",method = RequestMethod.GET)
    @ResponseBody
    public void getIdentifyCode(HttpServletRequest req,HttpServletResponse resp)throws Exception{
        String userName=req.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if(userService.verifyAccessToken(userName,accessToken)==4001) {//token验证成功
            System.out.println("验证token ok");
            //验证码文件
            System.out.println("验证码根路径：" + req.getSession().getServletContext().getRealPath("/"));
            String path = req.getSession().getServletContext().getRealPath("/") + FileOperator.IDENTIFY_CODE;
            try {
                FileOperator.checkExist(path);//验证文件夹存在
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileName=IdFactory.getUUID() + ".gif";
            String filePath=path+fileName;
            System.out.println("验证码文件路径："+filePath);
            final File fileIdentifyCode=new File(filePath);
            observable = retrofitServiceBeforeSchool.loginSchoolBefore();
            observable//登录进教务系统之前，获取验证码
                    //.subscribeOn(Schedulers.io())        //不切换线程，怎么样
                    //.observeOn(Schedulers.newThread())
                    .subscribe(new io.reactivex.Observer<okhttp3.ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {}
                        @Override
                        public void onNext(okhttp3.ResponseBody responseBody) {
                            System.out.println("下载验证码");
                            try {
                                InputStream is = responseBody.byteStream();
                                FileOutputStream fos = new FileOutputStream(fileIdentifyCode);
                                BufferedInputStream bis = new BufferedInputStream(is);
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = bis.read(buffer)) != -1) {
                                    fos.write(buffer, 0, len);
                                }
                                fos.flush();
                                fos.close();
                                bis.close();
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("验证码下载完毕，文件名为："+fileName);
                        }
                        @Override
                        public void onError(Throwable e) {System.out.println("验证码下载失败");}
                        @Override
                        public void onComplete() {System.out.println("验证码retorfit请求完毕");}
                    });
            System.out.println("开始传输验证码到前端");
            FileOperator.download(req, resp, fileName, ".gif", "identifyCode.gif", FileOperator.IDENTIFY_CODE);

        } else {
            result=3004;//token验证失败
        }


        Map<String, Object> map = new HashMap<>();
        map.put("result",result);
        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
    }




    @Resource
    private  CourseService courseService;
    @Resource
    private UserService userService;

    private int result=0;
    /*
     插入学生选课实现，获取课程输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/Insert",method= RequestMethod.POST)
    @ResponseBody
    public String insertCourseInfo(HttpServletRequest req) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求courseService为" +courseService);
        BufferedReader br = req.getReader();
        String str;StringBuilder jsonString = new StringBuilder();
        while((str = br.readLine()) != null){
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入课程请求为" + jsonString);
        String userName=req.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            result= courseService.insertCourse(jsonString.toString(), userName);

        } else {
              result=userService.verifyAccessToken(userName,accessToken);
              result=3004;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result",result);

        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        return result;
    }


    //获取课程表

    public Observable<okhttp3.ResponseBody> observable;   //登录之前获取验证码
    public Observable<okhttp3.ResponseBody> observableing;   //登录进行时
    public Observable<okhttp3.ResponseBody> observableing2;   //登录进行时,重定向一次

    public static String URL_MAIN = "http://xk1.ahu.cn";// 登录成功的首页
    //这两个玩意是访问教务处的隐藏参数
    private String __VIEWSTATE = "/wEPDwUJODk4OTczODQxZGQhFC7x2TzAGZQfpidAZYYjo/LeoQ==";
    private String __EVENTVALIDATION = "/wEWDgKX/4yyDQKl1bKzCQLs0fbZDAKEs66uBwK/wuqQDgKAqenNDQLN7c0VAuaMg+INAveMotMNAoznisYGArursYYIAt+RzN8IApObsvIHArWNqOoPqeRyuQR+OEZezxvi70FKdYMjxzk=";

    private String txtUserName = "E11514029"; //用户名
    private String TextBox2 = "SHB.19971008";//密码
    private String txtSecretCode = "";   //验证码
    private String RadioButtonList1 = "学生"; //学生登陆的选项

    //这四个玩意儿时访问教务处的空参数，不知道意义何在
    //难不成时为了以后增加内容防扒的？滑稽😂
    private String Button1 = "";
    private String lbLanguage = "";
    private String hidPdrs = "";
    private String hidsc = "";


    private static String xh = "E11514029";
    private String xm = "%cb%ef%ba%ad%b1%f2";//孙涵彬的GB2312编码
    private String gnmkdm = "N121603";
    //http://xk1.ahu.cn/xskbcx.aspx?xh=E11514029&xm=%CB%EF%BA%AD%B1%F2&gnmkdm=N121603
    private static String cookiesone = "";
    private static HashSet<String> cookies = new HashSet<>();

    public interface RetrofitServiceBeforeSchool {//获取验证码
        @GET("CheckCode.aspx")
        Observable<okhttp3.ResponseBody> loginSchoolBefore();
    }

    public interface RetrofitServiceSchool {//登入

        @Headers({
                "Host: xk1.ahu.cn",
                "Referer: http://xk1.ahu.cn/default2.aspx",
                "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"
        })
        @POST("default2.aspx")
        @FormUrlEncoded
        Observable<okhttp3.ResponseBody> loginSchool(@FieldMap(encoded = true) Map<String, String> reviews);
        //encoded参数为true的话，key-value-pair将会被编码，即将中文和特殊字符进行编码转换
    }

    public interface RetrofitServiceSchool2 {//查询课表
        //http://xk1.ahu.cn/xskbcx.aspx?xh=E11514029&xm=%CB%EF%BA%AD%B1%F2&gnmkdm=N121603
        @Headers({
                "Host: xk1.ahu.cn",
                "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"})
        @GET("xskbcx.aspx")
        Observable<okhttp3.ResponseBody> loginSchool2(@Query("xh") String xh, @Query("xm") String xm, @Query("gnmkdm") String gnmkdm);
    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(chain -> {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("cookie", cookiesone);
                builder.addHeader("Referer","http://xk1.ahu.cn/xs_main.aspx?xh="+xh);
                return chain.proceed(builder.build());
            }).connectTimeout(120,TimeUnit.SECONDS)
            .build();


    private static OkHttpClient getNewClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        return client.addNetworkInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("cookie", cookiesone);
                    return chain.proceed(builder.build());
                })
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpClient getNewClient2() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                    cookiesone = header;
                }
            }
            return originalResponse;
        });

        return client.addNetworkInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }


    RetrofitServiceBeforeSchool retrofitServiceBeforeSchool = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getNewClient2())
            .build()
            .create(RetrofitServiceBeforeSchool.class);

    private static RetrofitServiceSchool retrofitServiceSchool = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getNewClient())
            .build()
            .create(RetrofitServiceSchool.class);

    private static RetrofitServiceSchool2 retrofitServiceSchool2 = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RetrofitServiceSchool2.class);


}
