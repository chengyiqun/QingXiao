package QingXiao.controller;

import QingXiao.entity.Article;
import QingXiao.entity.UserInform;
import QingXiao.service.ArticleService;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/3/26.
 */

@Controller
@RequestMapping("/Article")
public class ArticleController {

    private int insertResult =100;
    private int readResult =100;
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;

    @RequestMapping("/list")
    public String list(){
        List<UserInform> list=userService.findAll();
        System.out.println(String.valueOf(list));
        return String.valueOf(list);
    }

    /*测试文件上传*/
    @RequestMapping("/uploads")
    public @ResponseBody
    String uploads(HttpServletRequest request, @RequestParam("myFileName") MultipartFile file){
        String url = null;
        url ="eee";// 只要能上传文件，并且返回文件在服务器上的相对路径即可。
        return request.getServletContext().getContextPath() + url;
    }




    /*
     *采用spring提供的上传文件的方法,上传文章图片
     */
    //@CrossOrigin
    @RequestMapping(value = "/uploadArticleImage",method=RequestMethod.POST)
    @ResponseBody
    public String  uploadArticleImage(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传文章图片请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result="";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传文章图片请求流："+br);
        String userName=request.getHeader("userName");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传文章图片请求头信息userName："+userName);
        System.out.println("获取上传文章图片请求头信息accessToken："+accessToken);
        //userName= URLDecoder.decode(userName);
        //userName= URLDecoder.decode(userName,"UTF-8");
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传文章图片请求流："+jsonString);
        if(jsonString!=null){
            //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        }
        //System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(true
                //userService.verifyAccessToken(userName,accessToken)==4001
        ) {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                System.out.println("上传文章图片请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传文章图片请求file为" + file);
                    if (file != null) {
                        System.out.println("上传文章图片请求文件不为空");
                        System.out.println("上传文章图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.ARTICLE_PICTURE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传文章图片请求文件的路径："+path);
                        String pictureRealName=file.getOriginalFilename();
                        String[] strs=pictureRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }
                        String pictureStoreName= IdFactory.getUUID()+".jpg";
                        //String pictureStoreName= IdFactory.getUUID()+"."+strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath=path+pictureStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传文章图片");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            filePath="http://60.205.218.103:80/QingXiao/"+FileOperator.ARTICLE_PICTURE+pictureStoreName;
                            resultMap.put("default",filePath);
                            //userService.updateAvatar(path,userName,pictureRealName,pictureStoreName);

                            //resultMap.put("errno",0);
                           // resultMap.put("data","http://60.205.218.103:80/QingXiao/"+FileOperator.ARTICLE_PICTURE+pictureStoreName);//这里应该是项目路径
                            System.out.println("上传文章图片的路径："+pictureStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",3005); //上传文章图片成功，返回图片地址加result。
            //resultMap.put("default","E:\\apache-tomcat-8.0.30\\webapps\\QingXiao\\avatar\\f1cd4ef0284e4ea88ffe1bccdbba0f2a.jpg");

        }else{
            resultMap.put("result",3004);  //token验证失败，重新登录
        }

        result = JSON.toJSONString(resultMap);
        System.out.println("result：" + result);
        return result;
    }

    /*
     *采用spring提供的上传文件的方法,上传文章图片
     */
    //@CrossOrigin
    @RequestMapping(value = "/uploadArticleImage1",method=RequestMethod.POST)
    @ResponseBody
    public Object  uploadArticleImage1(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传文章图片请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result="";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传文章图片请求流："+br);
        String userName=request.getHeader("userName");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传文章图片请求头信息userName："+userName);
        System.out.println("获取上传文章图片请求头信息accessToken："+accessToken);
        //userName= URLDecoder.decode(userName);
        //userName= URLDecoder.decode(userName,"UTF-8");
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传文章图片请求流："+jsonString);
        if(jsonString!=null){
            //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        }
        //System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(true
            //userService.verifyAccessToken(userName,accessToken)==4001
                ) {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                System.out.println("上传文章图片请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传文章图片请求file为" + file);
                    if (file != null) {
                        System.out.println("上传文章图片请求文件不为空");
                        System.out.println("上传文章图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.ARTICLE_PICTURE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传文章图片请求文件的路径："+path);
                        String pictureRealName=file.getOriginalFilename();
                        String[] strs=pictureRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }
                        String pictureStoreName= IdFactory.getUUID()+".jpg";
                        //String pictureStoreName= IdFactory.getUUID()+"."+strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath=path+pictureStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传文章图片");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            //resultMap.put("default",filePath);
                            //userService.updateAvatar(path,userName,pictureRealName,pictureStoreName);
                            filePath="http://60.205.218.103:80/QingXiao/"+FileOperator.ARTICLE_PICTURE+pictureStoreName;
                            resultMap.put("default",filePath);
                            //resultMap.put("errno",0);
                           // resultMap.put("data","http://60.205.218.103:80/QingXiao/"+FileOperator.ARTICLE_PICTURE+pictureStoreName);//这里应该是项目路径
                            System.out.println("上传文章图片的路径："+pictureStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",3005); //上传文章图片成功，返回图片地址加result。
            //resultMap.put("default","E:\\apache-tomcat-8.0.30\\webapps\\QingXiao\\avatar\\f1cd4ef0284e4ea88ffe1bccdbba0f2a.jpg");

        }else{
            resultMap.put("result",3004);  //token验证失败，重新登录
        }

       // result = JSON.toJSONString(resultMap);
        //System.out.println("result：" + result);
        return resultMap;
    }

    /*
     *采用spring提供的上传文件的方法,上传文章图片,wangEditor适用
     */
    //@CrossOrigin
    @RequestMapping(value = "/uploadArticleImage3",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadArticleImage3(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传文章图片请求为" + request);
        JSONObject jsonObject=new JSONObject();
        String realPath="";
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result="";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传文章图片请求流："+br);
        String userName=request.getHeader("userName");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传文章图片请求头信息userName："+userName);
        System.out.println("获取上传文章图片请求头信息accessToken："+accessToken);
        //userName= URLDecoder.decode(userName);
        //userName= URLDecoder.decode(userName,"UTF-8");
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传文章图片请求流："+jsonString);
        if(jsonString!=null){
            //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        }
        //System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(true
            //userService.verifyAccessToken(userName,accessToken)==4001
                ) {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                System.out.println("上传文章图片请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传文章图片请求file为" + file);
                    if (file != null) {
                        System.out.println("上传文章图片请求文件不为空");
                        System.out.println("上传文章图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.ARTICLE_PICTURE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        String rootPath = path.substring(0, path.indexOf("\\QingXiao\\"));
                        System.out.println("上传文章图片文件的路径rootPath："+rootPath);
                        System.out.println("上传文章图片请求文件的路径："+path);
                        path =rootPath+FileOperator.ARTICLE_PICTURE;
                        System.out.println("上传文章图片请求文件的路径："+path);
                        String pictureRealName=file.getOriginalFilename();
                        String[] strs=pictureRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }
                        String pictureStoreName= IdFactory.getUUID()+".jpg";
                        //String pictureStoreName= IdFactory.getUUID()+"."+strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath=path+pictureStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传文章图片");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            //resultMap.put("default",filePath);
                            //userService.updateAvatar(path,userName,pictureRealName,pictureStoreName);

                            resultMap.put("errno",0);
                            resultMap.put("data","http://60.205.218.103:80/"+FileOperator.ARTICLE_PICTURE+pictureStoreName);//这里应该是项目路径
                            realPath="http://60.205.218.103:80/"+FileOperator.ARTICLE_PICTURE+pictureStoreName;
                            System.out.println("上传文章图片的路径："+pictureStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",3005); //上传文章图片成功，返回图片地址加result。
            //resultMap.put("default","E:\\apache-tomcat-8.0.30\\webapps\\QingXiao\\avatar\\f1cd4ef0284e4ea88ffe1bccdbba0f2a.jpg");

        }else{
            resultMap.put("result",3004);  //token验证失败，重新登录
        }
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(realPath);
        jsonObject.put("errno",0);
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }


    /*
         *采用spring提供的上传文件的方法,上传文章图片,ckEditor适用
         */
    //@CrossOrigin
    @RequestMapping(value = "/uploadArticleImage4",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadArticleImage4(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传文章图片请求为" + request);
        JSONObject jsonObject=new JSONObject();
        String realPath="";
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result="";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传文章图片请求流："+br);
        String userName=request.getHeader("userName");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传文章图片请求头信息userName："+userName);
        System.out.println("获取上传文章图片请求头信息accessToken："+accessToken);
        //userName= URLDecoder.decode(userName);
        //userName= URLDecoder.decode(userName,"UTF-8");
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传文章图片请求流："+jsonString);
        if(jsonString!=null){
            //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        }
        //System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(true
            //userService.verifyAccessToken(userName,accessToken)==4001
                ) {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                System.out.println("上传文章图片请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传文章图片请求file为" + file);
                    if (file != null) {
                        System.out.println("上传文章图片请求文件不为空");
                        System.out.println("上传文章图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.ARTICLE_PICTURE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        String rootPath = path.substring(0, path.indexOf("\\QingXiao\\"));
                        System.out.println("上传文章图片文件的路径rootPath："+rootPath);
                        System.out.println("上传文章图片请求文件的路径："+path);
                        path =rootPath+FileOperator.ARTICLE_PICTURE;
                        System.out.println("上传文章图片请求文件的路径："+path);
                        String pictureRealName=file.getOriginalFilename();
                        String[] strs=pictureRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }
                        String pictureStoreName= IdFactory.getUUID()+".jpg";
                        //String pictureStoreName= IdFactory.getUUID()+"."+strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath=path+pictureStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传文章图片");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            //resultMap.put("default",filePath);
                            //userService.updateAvatar(path,userName,pictureRealName,pictureStoreName);

                            resultMap.put("errno",0);
                            resultMap.put("data","http://60.205.218.103:80/"+FileOperator.ARTICLE_PICTURE+pictureStoreName);//这里应该是项目路径
                            realPath="http://60.205.218.103:80/"+FileOperator.ARTICLE_PICTURE+pictureStoreName;
                            System.out.println("上传文章图片的路径："+pictureStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",3005); //上传文章图片成功，返回图片地址加result。
            //resultMap.put("default","E:\\apache-tomcat-8.0.30\\webapps\\QingXiao\\avatar\\f1cd4ef0284e4ea88ffe1bccdbba0f2a.jpg");

        }else{
            resultMap.put("result",3004);  //token验证失败，重新登录
        }

        jsonObject.put("url",realPath);
        jsonObject.put("uploaded",1);
        jsonObject.put("res",1);
        System.out.println("返回的jsonobject：" + jsonObject);
        return jsonObject;
    }


    /*
        *采用spring提供的上传文件的方法,上传文章图片,ckEditor适用
        */
    //@CrossOrigin
    //@CrossOrigin(origins = "http://localhost:80")
    @RequestMapping(value = "/uploadArticleImage2",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadArticleImage2(HttpServletRequest request, HttpServletResponse resp) throws IllegalStateException, IOException
    {
        System.out.println("上传文章图片请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result="";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传文章图片请求流："+br);
        String userName=request.getHeader("userName");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传文章图片请求头信息userName："+userName);
        System.out.println("获取上传文章图片请求头信息accessToken："+accessToken);
        //userName= URLDecoder.decode(userName);
        //userName= URLDecoder.decode(userName,"UTF-8");
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传文章图片请求流："+jsonString);
        if(jsonString!=null){
            //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        }
        //System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(true
            //userService.verifyAccessToken(userName,accessToken)==4001
                ) {
            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                System.out.println("上传文章图片请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传文章图片请求file为" + file);
                    if (file != null) {
                        System.out.println("上传文章图片请求文件不为空");
                        System.out.println("上传文章图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.AVATAR;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传文章图片请求文件的路径："+path);
                        String avatarRealName=file.getOriginalFilename();
                        String[] strs=avatarRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }
                        //String avatarStoreName= IdFactory.getUUID()+".jpg";
                        String avatarStoreName= IdFactory.getUUID()+"."+strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath=path+avatarStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传文章图片");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            resultMap.put("default",filePath);
                            userService.updateAvatar(path,userName,avatarRealName,avatarStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",3005); //上传文章图片成功，返回图片地址加result。
            //resultMap.put("default","E:\\apache-tomcat-8.0.30\\webapps\\QingXiao\\avatar\\f1cd4ef0284e4ea88ffe1bccdbba0f2a.jpg");
        }else{
            resultMap.put("result",3004);  //token验证失败，重新登录
        }
       JSONObject jsonObject = new JSONObject();
        //jsonObject.put(JSONObject.DEFAULT_TYPE_KEY,)
        result = JSON.toJSONString(resultMap);
        System.out.println("result：" + result);
        resp.addHeader("Content-Type","application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding","gzip");
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("text/plain;charset=utf-8" );
        resp.setCharacterEncoding("UTF-8");
        System.out.println("结果为" + result);
       jsonObject.put("url","");
       return jsonObject;
    }



   /*
   上传文章实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/uploadArticle",method=RequestMethod.POST)
    public void uploadArticle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("获取上传文章请求流：");
        //BufferedReader br1 = req.getReader();
        //System.out.println("获取上传文章请求流："+br1);

        String author=req.getParameter("author");
        String content=req.getParameter("content");
        String title=req.getParameter("title");
        String summary=req.getParameter("summary");
        System.out.println("获取上传文章author："+author);
        System.out.println("获取上传文章content："+content);
        System.out.println("获取上传文章title："+title);
        System.out.println("获取上传文章summary："+summary);
        HashMap<String, Object> articleMap = new HashMap<>();
        articleMap.put("author",author);
        articleMap.put("content",content);
        articleMap.put("title",title);
        articleMap.put("summary",summary);
        //int deleteResult =userService.deleteUser(stuffTel);
        int insertResult=articleService.insertArticle(articleMap);
        System.out.println("此次上传的结果为"+insertResult);
        String result1=insertResult+"This is a 上传文章结果";
        System.out.println("结果为" + result1);
        Map<String, Object> map = new HashMap<>();
        map.put("RESULT_KEY", insertResult);

        resp.addHeader("Content-Type","application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding","gzip");
        resp.setContentType("text/plain;charset=utf-8" );
        resp.setCharacterEncoding("UTF-8");
        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        printWriter.close();

    }

 /*
   上传文章实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/articleList",method=RequestMethod.POST)
    @ResponseBody
    public String queryArticleList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("获取文章列表请求流：");
        //BufferedReader br1 = req.getReader();
        //System.out.println("获取上传文章请求流："+br1);
        String jsonString="";
        String sinceTime=req.getParameter("sinceTime");
        String nowTime=req.getParameter("nowTime");
        String title=req.getParameter("title");
        String summary=req.getParameter("summary");
        System.out.println("获取上传文章author："+sinceTime);
        System.out.println("获取上传文章nowTime："+nowTime);
        System.out.println("获取上传文章title："+title);
        HashMap<String, Object> articleMap = new HashMap<>();
        HashMap<String, Object> articleListMap = new HashMap<>();
        articleMap.put("sinceTime",sinceTime);
        articleMap.put("nowTime",nowTime);
        articleMap.put("title",title);
        articleMap.put("summary",summary);
        //int queryResult=articleService.insertArticle(articleMap);

        List<Map> list = articleService.queryArticleList(sinceTime,nowTime);
        articleListMap.put("articleList",list);
        articleListMap.put("result",200);
        String resultString = JSON.toJSONString(list);
        String result = JSON.toJSONString(articleListMap);
        //System.out.println("此次查询的结果为"+queryResult);
        // String result1=queryResult+"This is a 查询文章列表结果";
        System.out.println("结果为" + resultString);
        System.out.println("result：" + result);
        //return resultString;
        return result;
    }


/*
   上传文章实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/articleList1",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject queryArticleList1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("获取文章列表请求流：");
        //BufferedReader br1 = req.getReader();
        //System.out.println("获取上传文章请求流："+br1);
        JSONObject jsonObject=new JSONObject();
        String jsonString="";
        String sinceTime=req.getParameter("sinceTime");
        String nowTime=req.getParameter("nowTime");
        String title=req.getParameter("title");
        String summary=req.getParameter("summary");
        System.out.println("获取上传文章author："+sinceTime);
        System.out.println("获取上传文章nowTime："+nowTime);
        System.out.println("获取上传文章title："+title);
        System.out.println("获取上传文章summary："+summary);
        HashMap<String, Object> articleMap = new HashMap<>();
        HashMap<String, Object> articleListMap = new HashMap<>();
        articleMap.put("sinceTime",sinceTime);
        articleMap.put("nowTime",nowTime);
        articleMap.put("title",title);
        articleMap.put("summary",summary);
        //int queryResult=articleService.insertArticle(articleMap);


        //List<Map> list = articleService.queryArticleList(sinceTime,nowTime);
        List<Article> list = articleService.queryArticleListWithMap(sinceTime,nowTime);
        articleListMap.put("articleList",list);
        articleListMap.put("result",200);
        String resultString = JSON.toJSONString(list);
        String result = JSON.toJSONString(articleListMap);
        //System.out.println("此次查询的结果为"+queryResult);
        // String result1=queryResult+"This is a 查询文章列表结果";
        System.out.println("结果为" + resultString);
        System.out.println("result：" + result);
        //return resultString;
        JSONArray jsonArray=new JSONArray();
        for (Object object : list) {
            jsonArray.add(object);
        }
        jsonObject.put("result",200);
        jsonObject.put("articleList",jsonArray);
        System.out.println("jsonObject：" + jsonObject);
        return jsonObject;
    }

     /*
   获取文章内容实现，获取输入流，进行处理，返回文章。
    */

    @RequestMapping(value = "/articleInfo",method=RequestMethod.GET)

    public ModelAndView queryArticle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("获取文章详情请求流：");
        //BufferedReader br1 = req.getReader();
        //System.out.println("获取上传文章请求流："+br1);
        String jsonString="";


        String articleID=req.getParameter("articleID");
        String nowTime=req.getParameter("nowTime");
        String title=req.getParameter("title");
        String summary=req.getParameter("summary");
        System.out.println("获取文章ID："+articleID);
        System.out.println("获取文章title："+title);
        HashMap<String, Object> articleMap = new HashMap<>();
        HashMap<String, Object> articleListMap = new HashMap<>();
        articleMap.put("nowTime",nowTime);
        articleMap.put("title",title);
        articleMap.put("summary",summary);
        //int queryResult=articleService.insertArticle(articleMap);

        Article articleInfo = articleService.queryArticleInfo(articleID);
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(articleInfo);
        jsonObject.put("result",200);
        jsonObject.put("articleInfo",jsonArray);
        System.out.println("jsonObject：" + jsonObject);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/show_article");
        mav.addObject("articleInfo", articleInfo);
        mav.addObject("author", "ShuangShi");
        return mav;
    }


    /*
    删除文章实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/deleteArticle",method=RequestMethod.POST)
    public void deleteArticle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("获取删除文章请求流：");
        BufferedReader br = req.getReader();
        System.out.println("获取删除文章请求流："+br);

        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("删除文章请求为" + jsonString);
        JSONObject jsonObject= JSONObject.parseObject(jsonString);
        String stuffTel=(String)jsonObject.get("stuffTel");
        System.out.println("删除文章请求的电话为" + stuffTel);
        int deleteResult =userService.deleteUser(stuffTel);
        System.out.println("此次删除的结果为"+deleteResult);
        String result1=deleteResult+"This is a 删除文章结果";
        System.out.println("结果为" + result1);
        Map<String, Object> map = new HashMap<>();
        map.put("RESULT_KEY", insertResult);

        resp.addHeader("Content-Type","application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding","gzip");
        resp.setContentType("text/plain;charset=utf-8" );
        resp.setCharacterEncoding("UTF-8");
        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        printWriter.close();

    }
}
