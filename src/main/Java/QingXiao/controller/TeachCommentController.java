package QingXiao.controller;

import QingXiao.service.CourseService;
import QingXiao.service.TeachCommentService;
import QingXiao.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
@Controller
@RequestMapping("/TeachComment")
public class TeachCommentController {

    @Resource
    private TeachCommentService teachCommentService;
    @Resource
    private UserService userService;

    private int result=0;
    /*
     插入学生选课实现，获取课程输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/Insert",method= RequestMethod.POST)
    @ResponseBody
    public String insertTeachComment(HttpServletRequest req) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求teachCommentService为" +teachCommentService);
        BufferedReader br = req.getReader();
        System.out.println("获取插入教学评论请求流："+br);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入教学评论请求为" + jsonString);
        String userName=req.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=req.getHeader("accessToken");
        System.out.println("插入教学评论请求的用户为" + userName);

        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            result= teachCommentService.insertTeachComment(jsonString, userName);
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
  *获取教学实体评价信息的list
  */
    @RequestMapping(value = "/GetCommentList",method= RequestMethod.POST)
    @ResponseBody
    public String  getTeachCommentList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("课程资源评论list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取课程资源评论list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取课程资源评论list请求头信息userName："+userName);
        System.out.println("获取课程资源评论list请求头信息accessToken："+accessToken);
        String str;StringBuilder jsonString = new StringBuilder();
        while((str = br.readLine()) != null){
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取课程资源评论list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            List<Map> list = teachCommentService.getTeachCommentList(jsonString.toString());
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
  *获取教学实体评论的回复信息的list
  */
    @RequestMapping(value = "/GetCommentReplyList",method= RequestMethod.POST)
    @ResponseBody
    public String  getResourceCommentReplyList(HttpServletRequest request) throws IllegalStateException, IOException
    {
        System.out.println("课程资源评论list请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        BufferedReader br = request.getReader();

        System.out.println("获取课程资源评论list请求流："+br);
        String userName=request.getHeader("userName");
        userName=URLDecoder.decode(userName,"UTF-8");
        String accessToken=request.getHeader("accessToken");
        System.out.println("获取课程资源评论list请求头信息userName："+userName);
        System.out.println("获取课程资源评论list请求头信息accessToken："+accessToken);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        String  resultString ="";
        System.out.println("111获取课程资源评论list请求流："+jsonString);
        System.out.println("Token验证结果："+userService.verifyAccessToken(userName,accessToken));
        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            List<Map> list = teachCommentService.getTeachCommentReplyList(jsonString);
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
