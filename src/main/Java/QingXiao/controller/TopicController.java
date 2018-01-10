package QingXiao.controller;

import QingXiao.entity.Reply;
import QingXiao.entity.Topic;
import QingXiao.service.TeachCommentService;
import QingXiao.service.TopicService;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by xpb on 2018/1/2.
 */
@Controller
@RequestMapping("/Topic")
public class TopicController {

    @Resource
    private TopicService topicService;
    @Resource
    private UserService userService;

    private int result=0;
    /*
     插入动态实现，获取输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/Insert",method= RequestMethod.POST)
    @ResponseBody
    public String insertTopic(HttpServletRequest request) throws Exception {
        System.out.println("请求为" + request);
        System.out.println("请求topicService为" +topicService);
        BufferedReader br = request.getReader();
        System.out.println("获取插入动态请求流："+br);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入动态请求为" + jsonString);
        String userName=request.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("插入动态请求的用户为" + userName);
        List<HashMap<String, Object>>  imageMapList = new LinkedList<HashMap<String, Object>>();
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
           // result= topicService.insertTopic(jsonString, userName);
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
                String jsonString2=multiRequest.getParameter("QingXiao");
                int  json6= multiRequest.getContentLength();
                System.out.println("上传动态图片请求jsonString2:" + jsonString2);
                System.out.println("上传动态图片请求json6:" + json6);
                System.out.println("上传动态图片请求" + iter.toString());
                while (iter.hasNext()) {
                    String picOrder=multiRequest.getParameter("picOrder");
                    System.out.println("上传图片的次序：" + picOrder);
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("上传课程资源请求file为" + file);
                    if (file != null) {
                        System.out.println("上传动态图片请求文件不为空");
                        System.out.println("上传动态图片请求文件的根路径："+ request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.COURSE_RESOURCE;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("上传动态图片请求文件的路径："+path);
                        String resourceRealName=file.getOriginalFilename();
                        String picOrder1=file.getName();
                        System.out.println("上传图片的次序：" + picOrder1);
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
                            System.out.println("开始上传动态图片");
                            if(!jsonString.equals("")) {  // 判断字符串是否为空
                                System.out.println("开始上传动态图片的jsonString不为空"+jsonString);
                                HashMap<String, Object> imageMap = new HashMap<>();
                                imageMap.put("jsonString",jsonString);
                                imageMap.put("userName",userName);
                                imageMap.put("imagePath",filePath);
                                imageMap.put("fileName",resourceRealName);
                                imageMap.put("imageType",1);
                                imageMap.put("imageStoreName",resourceStoreName);
                                imageMapList.add(imageMap);
                                //result = courseResourceService.insertCourseResource(jsonString,userName, filePath, resourceRealName, resourceType,resourceStoreName);
                            }else{
                                System.out.println("开始上传动态图片的jsonString为空");
                                result=3302;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            result=topicService.insertTopic(jsonString,userName,imageMapList);
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            //resultMap.put("result",); //上传资源成功。
        } else {
            //result=userService.verifyAccessToken(userName,accessToken);
            result=3004;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result",result);

        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        return result;
    }

    /*
 *获取动态信息的list
 */
    @RequestMapping(value = "/GetTopicList",method= RequestMethod.POST)
    @ResponseBody
    public String  getTopicList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("动态list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取动态list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取动态list请求头信息userName："+userName);
        System.out.println("获取动态list请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取动态list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            //List<Map> list = topicService.getTopicMapList(jsonString);
            //resultString = JSON.toJSONString(list);
            List<Topic> topicList = topicService.getTopicList(jsonString);
            resultString = JSON.toJSONString(topicList);
        }else{
            result=3004;
            // resultMap.put("result",3004);  //token验证失败，重新登录
        }
        resultMap.put("result",result);

        System.out.println("动态result：" + resultString);
        return resultString;
    }


    /*
     插入动态评论，获取输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/InsertComment",method= RequestMethod.POST)
    @ResponseBody
    public String insertTopicComment(HttpServletRequest req) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求topicService为" +topicService);
        BufferedReader br = req.getReader();
        System.out.println("获取插入动态评论请求流："+br);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入动态评论请求为" + jsonString);
        String userName=req.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=req.getHeader("accessToken");
        System.out.println("插入动态评论请求的用户为" + userName);

        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            result= topicService.insertTopicComment(jsonString, userName);
        } else {
            //result=userService.verifyAccessToken(userName,accessToken);
            result=3004;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result",result);

        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        return result;
    }

    /*
  *获取动态的评价信息的list
  */
    @RequestMapping(value = "/GetCommentList",method= RequestMethod.POST)
    @ResponseBody
    public String  getResourceCommentList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("动态评论list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取动态评论list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取动态评论list请求头信息userName："+userName);
        System.out.println("获取动态评论list请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取动态评论list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            List<Map> list = topicService.getTopicCommentList(jsonString);
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
  *获取动态评论的回复信息的list
  */
    @RequestMapping(value = "/GetCommentReplyList",method= RequestMethod.POST)
    @ResponseBody
    public String  getResourceCommentReplyList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("评论回复list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取评论回复list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取评论回复list请求头信息userName："+userName);
        System.out.println("获取评论回复list请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取课程资源评论list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            List<Reply> list = topicService.getTopicCommentReplyList(jsonString);
            resultString = JSON.toJSONString(list);
        }else{
            result=3004;
            // resultMap.put("result",3004);  //token验证失败，重新登录
        }
        resultMap.put("result",result);

        System.out.println("result：" + resultString);
        return resultString;
    }

}
