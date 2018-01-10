package QingXiao.controller;

import QingXiao.entity.UserInform;
import QingXiao.service.CourseService;
import QingXiao.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xpb on 2017/10/24.
 */
@Controller
@RequestMapping("/Course")
public class CourseController {

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
        System.out.println("获取插入课程请求流："+br);
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("插入课程请求为" + jsonString);
        String userName=req.getHeader("userName");
        userName= URLDecoder.decode(userName,"UTF-8");
        String accessToken=req.getHeader("accessToken");
        System.out.println("插入课程请求的用户为" + userName);

        if(userService.verifyAccessToken(userName,accessToken)==4001) {
            result= courseService.insertCourse(jsonString, userName);

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

}
