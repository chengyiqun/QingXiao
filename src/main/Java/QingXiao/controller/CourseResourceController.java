package QingXiao.controller;

import QingXiao.service.CourseResourceService;
import QingXiao.service.ResourceDownloadInformService;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
@Controller
@RequestMapping("/CourseResource")
public class CourseResourceController {

    @Resource
    private UserService userService;
    @Resource
    private CourseResourceService courseResourceService;
    @Resource
    private ResourceDownloadInformService resourceDownloadInformService;
    private int result=0;

    /*
         *采用spring提供的上传文件的方法,上传课程资源
         */
    @RequestMapping(value = "/Upload",method= RequestMethod.POST)
    @ResponseBody
    public String  uploadCourseResource(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传课程资源请求为" + request);
        String jsonString=request.getParameter("jsonStringUploadCourseResource");
        System.out.println("上传课程资源请求jsonString3:" + jsonString);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        System.out.println("获取上传课程资源请求流之前：");

        String userName=request.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传课程资源请求头信息userName："+userName);
        System.out.println("获取上传课程资源请求头信息accessToken："+accessToken);

        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
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
                String jsonString2=multiRequest.getParameter("jsonStringUploadCourseResource");
                int  json6= multiRequest.getContentLength();
                System.out.println("上传课程资源请求jsonString2:" + jsonString2);
                System.out.println("上传课程资源请求json6:" + json6);
                System.out.println("上传课程资源请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传课程资源请求file为" + file);
                    if (file != null) {
                        System.out.println("上传课程资源请求文件不为空");
                        System.out.println("上传课程资源请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.COURSE_RESOURCE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传课程资源请求文件的路径："+path);
                        String resourceRealName=file.getOriginalFilename();
                        int indexDot = resourceRealName.lastIndexOf('.');

                        String resourceType = resourceRealName.substring(indexDot,resourceRealName.length());

                        String resourceStoreName= IdFactory.getUUID()+resourceType; //后缀名类型应该是获取文件名的
                        String filePath=path+resourceStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传资源");
                            if(!jsonString.equals("")) {  // 判断字符串是否为空
                                System.out.println("开始上传资源的jsonString不为空"+jsonString);
                                result = courseResourceService.insertCourseResource(jsonString, userName, filePath, resourceRealName, resourceType,resourceStoreName);
                            }else{
                                System.out.println("开始上传资源的jsonString为空");
                                result=3302;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",); //上传资源成功。
        }else{
            result=3004;
            // resultMap.put("result",3004);  //token验证失败，重新登录
        }
        resultMap.put("result",result);
        String  resultSstring = JSON.toJSONString(resultMap);
        System.out.println("result：" + resultSstring);
        return resultSstring;
    }



    /*
   *采用spring提供的下载文件的方法,下载课程资源
   * 先验证Token,再下载，下载之后应该向资源下载表添加一条记录
   */
    @RequestMapping(value = "/Download",method=RequestMethod.GET)
    public void  downloadCourseResource(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException
    {

        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        String  resourceStoreName =request.getParameter("resourceStoreName");
        System.out.println("获取下载课程资源请求头信息userName："+userName);
        System.out.println("获取下载课程资源请求头信息accessToken："+accessToken);
        System.out.println("获取下载课程资源请求信息resourceStoreName："+resourceStoreName);
        String contentType=".jpg";  //不准，需要改正
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            try {
                FileOperator.download(request,response,contentType,resourceStoreName,FileOperator.COURSE_RESOURCE);
            } catch (Exception e) {
                e.printStackTrace();
            }
           result= resourceDownloadInformService.insertResourceDownloadInform(resourceStoreName,userName);
            System.out.println("插入下载记录的结果为："+result);
            result=courseResourceService.downloadCourseResource(resourceStoreName);
            System.out.println("更新下载次数的结果为："+result);
        }else{

        }

    }


    /*
     *获取课程资源信息的list
     */
    @RequestMapping(value = "/GetList",method= RequestMethod.POST)
    @ResponseBody
    public String  getCourseResourceList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("课程资源list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取课程资源list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取课程资源list请求头信息userName："+userName);
        System.out.println("获取课程资源list请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取课程资源list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            List<Map> list =  courseResourceService.getCourseResourceList(jsonString);
            resultString = JSON.toJSONString(list);
        }else{
            result=3004;
            // resultMap.put("result",3004);  //token验证失败，重新登录
        }
        resultMap.put("result",result);

        System.out.println("result：" + resultString);
        return resultString;
    }

    /*
        *采用spring提供的上传文件的方法,上传课程资源
        */
    @RequestMapping(value = "/Upload1",method= RequestMethod.POST)
    @ResponseBody
    public String  uploadCourseResource1(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("上传课程资源请求为" + request);

        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        System.out.println("获取上传课程资源请求流之前：");
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("获取上传课程资源请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取上传课程资源请求头信息userName："+userName);
        System.out.println("获取上传课程资源请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println("111获取上传课程资源请求流："+jsonString);

        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
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
                System.out.println("上传课程资源请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传课程资源请求file为" + file);
                    if (file != null) {
                        System.out.println("上传课程资源请求文件不为空");
                        System.out.println("上传课程资源请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.COURSE_RESOURCE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传课程资源请求文件的路径："+path);
                        String resourceRealName=file.getOriginalFilename();
                        int indexDot = resourceRealName.lastIndexOf('.');

                        /*String[] strs=resourceRealName.split("\\.");
                        for(int i=0,len=strs.length;i<len;i++){
                            System.out.println("字符串分割结果："+strs[i].toString());
                        }*/
                        //String avatarStoreName= IdFactory.getUUID()+".jpg";
                        //String resourceType = strs[1].toString();
                        String resourceType = resourceRealName.substring(indexDot,resourceRealName.length());

                        String resourceStoreName= IdFactory.getUUID()+resourceType; //后缀名类型应该是获取文件名的
                        String filePath=path+resourceStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传资源");
                            result= courseResourceService.insertCourseResource(jsonString,userName,filePath,resourceRealName,resourceType, resourceStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",); //上传资源成功。
        }else{
            result=3004;
            // resultMap.put("result",3004);  //token验证失败，重新登录
        }
        resultMap.put("result",result);
        String  resultSstring = JSON.toJSONString(resultMap);
        System.out.println("result：" + resultSstring);
        return resultSstring;
    }

}
