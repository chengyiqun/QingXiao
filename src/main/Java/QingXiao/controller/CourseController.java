package QingXiao.controller;

import QingXiao.service.CourseService;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.entity.GetCourseResult;
import QingXiao.util.GetCourseService;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.reactivex.functions.Function;
import net.sf.json.JSONArray;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import java.net.URLEncoder;
import java.util.*;
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
    @RequestMapping(value = "GetIdentifyCode", method = RequestMethod.GET)
    @ResponseBody
    public void getIdentifyCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userName = req.getHeader("userName");
        userName = URLDecoder.decode(userName, "UTF-8");
        String accessToken = req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if (userService.verifyAccessToken(userName, accessToken) == 4001) {//token验证成功
            System.out.println("验证token ok");
            //验证码文件
            System.out.println("验证码根路径：" + req.getSession().getServletContext().getRealPath("/"));
            String path = req.getSession().getServletContext().getRealPath("/") + FileOperator.IDENTIFY_CODE;
            try {
                FileOperator.checkExist(path);//验证文件夹存在
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileName = IdFactory.getUUID() + ".gif";
            String filePath = path + fileName;
            System.out.println("验证码文件路径：" + filePath);
            final File fileIdentifyCode = new File(filePath);
            observable = retrofitServiceGetIdentifyCode.loginSchoolBefore();
            //登录进教务系统之前，获取验证码
            observable
                    //.subscribeOn(Schedulers.io())        //不切换线程，怎么样
                    //.observeOn(Schedulers.computation())
                    .subscribe(new io.reactivex.Observer<okhttp3.ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

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
                            System.out.println("验证码下载完毕，文件名为：" + fileName);
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("验证码下载失败");
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("验证码retorfit请求完毕");
                        }
                    });
            System.out.println("开始传输验证码到前端");
            FileOperator.download(req, resp, fileName, ".gif", "identifyCode.gif", FileOperator.IDENTIFY_CODE);

        } else {
            result = 3004;//token验证失败
        }


        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        String result = com.alibaba.fastjson.JSON.toJSONString(map);
        System.out.println("结果为" + result);
    }

    /*
        登入系统并获得课表，保存到数据库并返回给前端课表得Json对象
        */
    @RequestMapping(value = "GetCourse", method = RequestMethod.POST)
    @ResponseBody
    public String GetCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userName = req.getHeader("userName");
        userName = URLDecoder.decode(userName, "UTF-8");
        String accessToken = req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if (userService.verifyAccessToken(userName, accessToken) == 4001) {//token验证成功
            result=0;
            System.out.println("验证token ok");
            BufferedReader br = req.getReader();
            String str;
            StringBuilder jsonString = new StringBuilder();
            while ((str = br.readLine()) != null) {
                jsonString.append(str);
                System.out.println("str为" + str);
            }
            System.out.println("获取教务处用户名和密码和验证码请求流：");
            System.out.println(jsonString);
            com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.parse(String.valueOf(jsonString));
            String txtUserName = jsonObject.getString("txtUserName");
            String TextBox2 = jsonObject.getString("TextBox2");
            String txtSecretCode = jsonObject.getString("txtSecretCode");
            System.out.println(txtUserName + "+" + TextBox2 + "+" + txtSecretCode);

            if (cookiesone.length() < 3) {
                System.out.println("请刷新验证码或者cookies过期");
            } else {
                /*int n = cookiesone.length() - 18;
                cookiesone = cookiesone.substring(0, n);*/
                xh = txtUserName;
                Map<String, String> reviewMap = new HashMap<String, String>();
                try {
                    RadioButtonList1 = URLEncoder.encode("学生", "gb2312");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                reviewMap.put("__VIEWSTATE", __VIEWSTATE);
                //reviewMap.put("__EVENTVALIDATION", __EVENTVALIDATION);   // 这两个是安大教务处的隐藏参数（jw1没有这个参数）
                reviewMap.put("txtUserName", txtUserName);
                reviewMap.put("TextBox1",TextBox1);//（只有jw1有这个参数，没有值，只是空字符串）
                reviewMap.put("TextBox2", TextBox2);//密码。。。
                reviewMap.put("txtSecretCode", txtSecretCode);
                reviewMap.put("RadioButtonList1", RadioButtonList1);
                reviewMap.put("Button1", Button1);
                reviewMap.put("lbLanguage", lbLanguage);
                reviewMap.put("hidPdrs", hidPdrs);
                reviewMap.put("hidsc", hidsc);

                observableing = retrofitServiceSchoolLogin.loginSchool(reviewMap);
                String finalUserName = userName;
                observableing
                        //.subscribeOn(Schedulers.io())                     //登录进教务系统
                        .flatMap((Function<okhttp3.ResponseBody, Observable<okhttp3.ResponseBody>>) responseBody -> {
                            //判断登录是否错误，如密码错，用户名错，提取系统反馈的信息
                            String str1 = responseBody2String(responseBody);
                            System.out.println(str1);
                            if (str1.contains("敏感字符")) {
                                System.out.println("__VIEWSTATE 有问题");
                            }
                            String studentName = isLogin(str1);
                            if (studentName != null && !studentName.equals("")) {
                                xm = studentName.substring(0, studentName.length() - 2);
                            }
                            final String errorType = errorType(str1);
                            System.out.println("错误：" + errorType);
                            if (errorType.contains("密码")||errorType.contains("用户名")) {
                                System.out.println("登陆错误");
                                result=3005;
                            }
                            if (errorType.contains("验证码"))
                            {
                                System.out.println("验证码错误");
                                result=3006;
                            }
                            return retrofitServiceSchoolGetCourses.loginSchool2(xh, xm, gnmkdm);
                        })
                        .map(responseBody -> {
                            String string = responseBody2String(responseBody);
                            if (string.contains("敏感字符")) {
                                System.out.println("__VIEWSTATE 有问题");
                            }
                            GetCourseService getCourseService = GetCourseService.getCourseService();
                            return getCourseService.getCourseInfo(string);
                        })
                        //.observeOn(Schedulers.computation())
                        .subscribe(new io.reactivex.Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(String JsonString) {
                                System.out.println("获取课表的字符串");
                                System.out.println(JsonString);
                                getCourseResult = courseService.insertCourse(JsonString, finalUserName);
                                result=getCourseResult.getResult();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("从教务处访问课表成功");
                            }
                        });
            }

        } else {
            result = 3004;//token验证失败
        }

        if (result == 3004||result==3005||result==3006) {
            /*Map<String,Object> map = new HashMap<>();
            map.put("result",result);
            List<Map>list=new ArrayList<>();
            list.add(map);
            JSONArray jsonArray = JSONArray.fromObject(list);
            System.out.println("jsonArray：" + jsonArray);*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", result);
            return jsonObject.toString();
        } else {
            int result = getCourseResult.getResult();
            switch (result) {
                case 3201:
                    System.out.println("插入课表ok");
                    break;
                case 3202:
                    System.out.println("数据库已存在课程");
                    break;
                case 3203:
                    System.out.println("用户不存在");
                    break;
            }
            List<Map> list = getCourseResult.getList();
            JSONArray jsonArray = JSONArray.fromObject(list);
            //JSONObject jsonObject = JSONObject.fromObject(jsonArray);
            System.out.println(jsonArray);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", result);
            jsonObject.put("courses", jsonArray);
            return jsonObject.toString();
        }
    }


    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;

    private int result = 0;

    /*
     插入学生选课实现，获取课程输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/Insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertCourseInfo(HttpServletRequest req) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求courseService为" + courseService);
        BufferedReader br = req.getReader();
        String str;
        StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入课程请求为" + jsonString);
        String userName = req.getHeader("userName");
        userName = URLDecoder.decode(userName, "UTF-8");
        String accessToken = req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if (userService.verifyAccessToken(userName, accessToken) == 4001) {
            //result = courseService.insertCourse(jsonString.toString(), userName);
            System.out.println("验证ok4001");

        } else {
            result = userService.verifyAccessToken(userName, accessToken);
            result = 3004;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);

        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        return result;
    }


    //获取课程表

    private Observable<okhttp3.ResponseBody> observable;   //登录之前获取验证码
    private Observable<okhttp3.ResponseBody> observableing;   //登录进行时
    public static GetCourseResult getCourseResult;

    private static final String URL_MAIN = "http://jw1.ahu.cn";// 登录成功的首页
    //这两个玩意是访问教务处的隐藏参数//此处是GB2312编码
    private static final String __VIEWSTATE = "dDwtNTE2MjI4MTQ7Oz7iEUOE%2bzDTFL5MlAoa%2fnIjJPPu3A%3d%3d";//jw1d的
    private static final String __EVENTVALIDATION = "%2fwEWDgKX%2f4yyDQKl1bKzCQLs0fbZDAKEs66uBwK%2fwuqQDgKAqenNDQLN7c0VAuaMg%2bINAveMotMNAoznisYGArursYYIAt%2bRzN8IApObsvIHArWNqOoPqeRyuQR%2bOEZezxvi70FKdYMjxzk%3d";

    private String txtUserName = "E11514029"; //用户名
    private String TextBox1 = "";
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
    //http://jw1.ahu.cn/xskbcx.aspx?xh=E11514029&xm=%CB%EF%BA%AD%B1%F2&gnmkdm=N121603
    private static String cookiesone = "";
    private static HashSet<String> cookies = new HashSet<>();

    public interface RetrofitServiceGetIdentifyCode {//获取验证码

        @GET("CheckCode.aspx")
        Observable<okhttp3.ResponseBody> loginSchoolBefore();
    }

    public interface RetrofitServiceSchoolLogin {//登入

        @Headers({
                "Host: jw1.ahu.cn",
                "Referer: http://jw1.ahu.cn/default2.aspx",
                "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"
        })
        @POST("default2.aspx")
        @FormUrlEncoded
        Observable<okhttp3.ResponseBody> loginSchool(@FieldMap(encoded = true) Map<String, String> reviews);
        //encoded参数为true的话，key-value-pair将会被编码，即将中文和特殊字符进行编码转换
    }

    public interface RetrofitServiceSchoolGetCourses {//查询课表

        //http://jw1.ahu.cn/xskbcx.aspx?xh=E11514029&xm=%CB%EF%BA%AD%B1%F2&gnmkdm=N121603
        @Headers({
                "Host: jw1.ahu.cn",
                "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"})
        @GET("xskbcx.aspx")
        Observable<okhttp3.ResponseBody> loginSchool2(@Query("xh") String xh, @Query("xm") String xm, @Query("gnmkdm") String gnmkdm);
    }

    private static OkHttpClient httpClientGetCourses = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(chain -> {
                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("cookie", cookiesone);
                builder.addHeader("Referer", "http://jw1.ahu.cn/xs_main.aspx?xh=" + xh);
                return chain.proceed(builder.build());
            }).connectTimeout(20, TimeUnit.SECONDS)
            .build();


    private static OkHttpClient getNewClientSchoolLogin() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        return client.addNetworkInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("cookie", cookiesone);
                    System.out.println("登陆时的cookies");
                    System.out.println(cookiesone);
                    return chain.proceed(builder.build());
                })
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpClient getNewClientGetIdentifyCode() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                for (String header : originalResponse.headers("Set-Cookie")) {
                    System.out.println("刷新验证码的cookie111");
                    System.out.println(header);
                    cookies.add(header);
                    cookiesone = header;
                }
            }
            return originalResponse;
        });

        return client.addNetworkInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }


    private RetrofitServiceGetIdentifyCode retrofitServiceGetIdentifyCode = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getNewClientGetIdentifyCode())
            .build()
            .create(RetrofitServiceGetIdentifyCode.class);

    private static RetrofitServiceSchoolLogin retrofitServiceSchoolLogin = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getNewClientSchoolLogin())
            .build()
            .create(RetrofitServiceSchoolLogin.class);

    private static RetrofitServiceSchoolGetCourses retrofitServiceSchoolGetCourses = new Retrofit.Builder()
            .baseUrl(URL_MAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClientGetCourses)
            .build()
            .create(RetrofitServiceSchoolGetCourses.class);


    /*
   判断是否登录成功，通过页面中是否能提取出“XX同学”判断。
    */
    public String isLogin(String content) {
        Document doc = Jsoup.parse(content, "UTF-8");
        Elements elements = doc.select("span#xhxm");
        try {
            Element element = elements.get(0);
            return element.text();
        } catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
        }
        return null;
    }

    /*
查找登录出错原因，通过提取页面中“<script language='javascript' defer>alert('用户名不能为空！！');”的信息判断。
 */
    public String errorType(String content) {
        String errorType3 = "";
        Document doc = Jsoup.parse(content, "UTF-8");
        Elements script = doc.select("script");
        int j = 0;
        for (Element element : script) {
            if (j == 2) {
                errorType3 = element.html();
            }
            j++;
        }
        int firstLeft = errorType3.indexOf("(");
        int firstRight = errorType3.indexOf(")");

        if (errorType3.length() > 3) {
            return errorType3.substring(firstLeft + 2, firstRight - 1);
        } else {
            return "登陆错误";
        }
    }

    /*
    把ResponseBody类型转换成String类型
     */
    public String responseBody2String(okhttp3.ResponseBody responseBody) {
        InputStream inputStream = null;
        inputStream = responseBody.byteStream();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String string = null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        string = sb.toString();
        return string;
    }
}
