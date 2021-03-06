package QingXiao.controller;

import QingXiao.entity.UserInform;
import QingXiao.mappers.UserInformMapper;
import QingXiao.service.UserService;
import QingXiao.util.FileOperator;
import QingXiao.util.IdFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/21.
 */

@Controller
@RequestMapping("/User")
public class UserController {
    private int loginResult = 100;
    private int registerResult = 100;
    @Resource
    private UserService userService;


    @RequestMapping("/list")
    public String list() {
        List<UserInform> list = userService.findAll();
        System.out.println(String.valueOf(list));
        return String.valueOf(list);
    }

    @RequestMapping(value = "/GetVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public String getVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求Service为" + userService);
        HashMap resultMap;
        BufferedReader br = req.getReader();
        System.out.println("获取请求流：" + br);

        String str;
        StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("请求为" + jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString.toString());
        String phoneNum = (String) jsonObject.get("phoneNum");//不改变
        //String password = (String) jsonObject.get("password");
        System.out.println("请求的phoneNum为" + phoneNum );
        int result =0;
        result  = userService.getVerifyCode(phoneNum);
        System.out.println("结果为" + result);

        System.out.println(" result结果为" + result);
         //String result1=result+"";
        JSONObject jsonObject1 = new JSONObject();
        System.out.println(jsonObject.toString());
        jsonObject1.put("result", result);
        return jsonObject1.toString();
    }
    /*
    登录实现，获取登录输入流，进行处理，返回登录结果。
   返回Token,外加用户头像，性别等基本信息。
    */
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("请求为" + req);
        System.out.println("请求Service为" + userService);
        HashMap resultMap;
        BufferedReader br = req.getReader();

        System.out.println("获取请求流：" + br);

        String str;
        StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("请求为" + jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString.toString());
        String phoneNum = (String) jsonObject.get("phoneNum");//不改变
        String password = (String) jsonObject.get("password");
        System.out.println("请求的phoneNum为" + phoneNum + "\n请求的passWord为" + password);

        resultMap = userService.login(phoneNum, password);
        String result1 = loginResult + "This is a 登录结果";
        System.out.println("结果为" + result1);
        String accessToken = (String) resultMap.get("accessToken");
        System.out.println(" resultMap结果为" + resultMap);
        System.out.println("accessToken1111结果为" + accessToken);
        if (accessToken != null) {
            System.out.println("accessToken结果为" + accessToken);
            resultMap.remove("accessToken");
            resp.addHeader("accessToken", accessToken);
        }

        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding", "gzip");

        resp.setContentType("text/plain;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String result = JSON.toJSONString(resultMap);
        System.out.println("结果为" + result);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        //printWriter.write(result1);
        //  printWriter.flush();
        printWriter.close();
        //  return result;
    }

    /*
    网页端登录实现，获取登录输入流，进行处理，返回登录结果。
    */
    //@RequiresPermissions( "index" )
    @RequestMapping(value = "/LoginWeb1",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject loginWeb1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("请求为" + req);
        Map<String, Object> resultMap = new HashMap<>();
        BufferedReader br = req.getReader();
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
        }
        System.out.println("登录请求为" + jsonString);
        JSONObject jsonObject= JSONObject.parseObject(jsonString);
        String phoneNum = (String)jsonObject.get("phoneNum");//不改变
        String userName= (String)jsonObject.get("userName");//不改变
        String password = (String) jsonObject.get("password");
        String requestType= (String) jsonObject.get("requestType");
        System.out.println("请求的userName为" + userName + "\n请求的passWord为" + password);
        System.out.println("登录的phoneNum:"+phoneNum);
        resultMap = userService.login(phoneNum, password);
        HttpSession session =req.getSession() ;

        if (null != resultMap.get("userID")) {
            // 设置返回信息
        }

        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 调用安全认证框架的登录方法
            System.out.println("调用安全处理框架:");
            subject.login(new UsernamePasswordToken(phoneNum, password,true));
            System.out.println("验证结束，返回:");
           // UserInform user = (UserInform) SecurityUtils.getSubject().getPrincipal();
            //System.out.println("验证结束，返回数据:"+user.toString());
        }catch(AuthenticationException ex){
            System.out.println("登陆失败: " + ex.getMessage());
        }

        /*
        * Cookie cookie =new  Cookie("time","20080808");// 新建Cookie`

          cookie.setDomain(".helloweenvsfei.com");// 设置域名`

          cookie.setPath("/");// 设置路径`

          cookie.setMaxAge(Integer.MAX_VALUE);// 设置有效期`

          response.addCookie(cookie);// 输出到客户端`
       */
        Cookie cookie=new Cookie("username",phoneNum);
        cookie.setMaxAge(30*24*60*60);//一个小时有效
        cookie.setPath("/");// 设置路径
        resp.addCookie(cookie);
        Cookie cookie2=new Cookie("userType","1");
        cookie2.setMaxAge(30*24*60*60);//一个小时有效
        cookie2.setPath("/");// 设置路径
        resp.addCookie(cookie2);

        //return "redirect:/login.jsp";
        String result = JSON.toJSONString(resultMap);
        System.out.println("结果为" + result);
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("result",resultMap.get("result"));
        System.out.println("jsonObject1：" + jsonObject1);
        return jsonObject1;
    }

    /*
    网页端登录实现，获取登录输入流，进行处理，返回登录结果。
    */
    @RequestMapping(value = "/LoginWeb",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject loginWeb(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("请求为" + req);
        Map<String, Object> resultMap = new HashMap<>();
        BufferedReader br = req.getReader();
        String str, jsonString = "";
        while((str = br.readLine()) != null){
            jsonString += str;
        }
        System.out.println("登录请求为" + jsonString);
        // System.out.println("69请求的userName2为" + userName2 + "\n69请求的passWord2为" + passWord2);
        //loginResult = stuffService.login(userName2, passWord2);

        //map.put("userName",userName);
        // map.put("passWord",passWord);
        JSONObject jsonObject= JSONObject.parseObject(jsonString);
        String phoneNum = (String)jsonObject.get("phoneNum");//不改变
        String userName= (String)jsonObject.get("userName");//不改变
        String password = (String) jsonObject.get("password");
        String requestType= (String) jsonObject.get("requestType");
        System.out.println("请求的userName为" + userName + "\n请求的passWord为" + password);
        System.out.println("登录的phoneNum:"+phoneNum);
        resultMap = userService.login(phoneNum, password);
        HttpSession session =req.getSession() ;

        if (null != resultMap.get("userID")) {
            // 设置返回信息

            // 存储Session
            //session.setAttribute("userId", resultMap.get("userID"));
            // session.setAttribute("userName", resultMap.get("userName"));
            //session.setAttribute("privilegeLevel", resultMap.get("userName"));
        }

        /*
        * Cookie cookie =new  Cookie("time","20080808");// 新建Cookie`

          cookie.setDomain(".helloweenvsfei.com");// 设置域名`

          cookie.setPath("/");// 设置路径`

          cookie.setMaxAge(Integer.MAX_VALUE);// 设置有效期`

          response.addCookie(cookie);// 输出到客户端`
       */
        Cookie cookie=new Cookie("username",phoneNum);
        cookie.setMaxAge(30*24*60*60);//一个小时有效
        cookie.setPath("/");// 设置路径
        resp.addCookie(cookie);
        Cookie cookie2=new Cookie("userType","1");
        cookie2.setMaxAge(30*24*60*60);//一个小时有效
        cookie2.setPath("/");// 设置路径
        resp.addCookie(cookie2);

        //return "redirect:/login.jsp";
        String result = JSON.toJSONString(resultMap);
        System.out.println("结果为" + result);
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("result",resultMap.get("result"));
        System.out.println("jsonObject1：" + jsonObject1);
        return jsonObject1;
    }

    /*
  注册实现，获取注册输入流，进行处理，返回注册结果。
  */
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("注册请求为" + req);
        System.out.println("请求registerService为" + userService);
        BufferedReader br = req.getReader();
        System.out.println("获取注册请求流：" + br);

        String str;StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("注册请求为" + jsonString);

        HashMap mapType = (HashMap) JSON.parseObject(jsonString.toString(), Map.class);
        registerResult = userService.register(mapType);
        String result1 = registerResult + "This is a 注册结果";
        System.out.println("结果为" + result1);
        Map<String, Object> map = new HashMap<>();
        map.put("result", registerResult);

        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding", "gzip");
        resp.setContentType("text/plain;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        //resp.setCharacterEncoding("application/json;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        printWriter.close();

    }

    /*
    更改密码实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("更改密码请求为" + req);
        System.out.println("更改密码请求userService为" + userService);
        BufferedReader br = req.getReader();
        System.out.println("获取更改密码请求流：" + br);

        String str;StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("更改密码请求为" + jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString.toString());
        String stuffTel = (String) jsonObject.get("stuffTel");
        String passWord = (String) jsonObject.get("stuffPassword");

        System.out.println("更改密码请求的stuffTel为" + stuffTel +
                "\n更改密码请求的passWord为" + passWord);
        int updateResult = userService.updatePassword(stuffTel, passWord);
        System.out.println("此次更改密码的结果为" + updateResult);
        String result1 = updateResult + "This is a 更改密码结果";
        System.out.println("结果为" + result1);
        Map<String, Object> map = new HashMap<>();
        map.put("RESULT_KEY", registerResult);

        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding", "gzip");
        resp.setContentType("text/plain;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String result = JSON.toJSONString(map);

        System.out.println("结果为" + result);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        printWriter.close();

    }

    @RequestMapping(value = "/changeName", method = RequestMethod.POST)
    @ResponseBody
    public String changeName(String newName,HttpServletRequest request)throws Exception{
        String newNameDecoded=URLDecoder.decode(newName, "UTF-8");
        int result = 0;
        String userName = request.getHeader("userName");
        String accessToken = request.getHeader("accessToken");
        System.out.println("获取更改昵称请求头信息userName：" + userName);
        System.out.println("获取更改昵称请求头信息accessToken：" + accessToken);
        userName = URLDecoder.decode(userName, "UTF-8");
        int tokenResult = userService.verifyAccessToken(userName, accessToken);
        System.out.println("Token验证结果：" + tokenResult);
        if (tokenResult == 3004) {
            System.out.println("tokenError");
            result = 3004;
        }else {
            System.out.println(newNameDecoded);
            result = userService.changeName(userName,newNameDecoded);
        }

        JSONObject jsonObject = new JSONObject();
        System.out.println(jsonObject.toString());
        jsonObject.put("result", result);
        return jsonObject.toString();
    }
    /*
     *采用spring提供的上传文件的方法,更改用户头像
     */
    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    @ResponseBody
    public String updateAvatar(HttpServletRequest request) throws IllegalStateException, IOException {
        System.out.println("更改头像请求为" + request);
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> mapType = new HashMap<>();
        String result = "";
        //BufferedReader br = request.getReader();

        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //System.out.println("获取更改头像请求流："+br);
        String userName = request.getHeader("userName");
        String accessToken = request.getHeader("accessToken");
        System.out.println("获取更改头像请求头信息userName：" + userName);
        System.out.println("获取更改头像请求头信息accessToken：" + accessToken);
        //userName= URLDecoder.decode(userName);
        userName = URLDecoder.decode(userName, "UTF-8");
        String str;StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println("111获取更改头像请求流：" + jsonString);
        //mapType = (HashMap)JSON.parseObject(jsonString,Map.class);
        System.out.println("Token验证结果：" + userService.verifyAccessToken(userName, accessToken));
        //if(userService.verifyAccessToken(mapType)==4001) {
        if (userService.verifyAccessToken(userName, accessToken) == 4001) {
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
                System.out.println("更改头像请求" + iter.toString());
                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    System.out.println("更改头像请求file为" + file);
                    if (file != null) {
                        System.out.println("更改头像请求文件不为空");
                        System.out.println("更改头像请求文件的根路径：" + request.getSession().getServletContext().getRealPath("/"));
                        String path = request.getSession().getServletContext().getRealPath("/") + FileOperator.AVATAR;
                        //String path = "E:/Avatar/" + file.getOriginalFilename();  //不能少个杠。
                        //上传
                        System.out.println("更改头像请求文件的路径：" + path);
                        String avatarRealName = file.getOriginalFilename();
                        String[] strs = avatarRealName.split("\\.");
                        for (String str1 : strs) {
                            System.out.println("字符串分割结果：" + str1.toString());
                        }
                        //String avatarStoreName= IdFactory.getUUID()+".jpg";
                        String avatarStoreName = IdFactory.getUUID() + "." + strs[1].toString(); //后缀名类型应该是获取文件名的
                        String filePath = path + avatarStoreName;  //需要更改文件名
                        // FileOperator.judeDirExists(path);
                        try {
                            FileOperator.checkExist(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            file.transferTo(new File(filePath));
                            System.out.println("开始上传头像");
                            //userService.updateAvatar(filePath,userName,avatarRealName,avatarStoreName);
                            userService.updateAvatar(path, userName, avatarRealName, avatarStoreName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
            resultMap.put("result", 3005); //更改头像成功，返回图片地址加result。
        } else {
            resultMap.put("result", 3004);  //token验证失败，重新登录
        }

        result = JSON.toJSONString(resultMap);
        System.out.println("result：" + result);
        return result;
    }


    /*
     *采用spring提供的下载文件的方法,下载用户头像
     * 先验证Token,再下载
     */
    @RequestMapping(value = "/DownloadAvatar", method = RequestMethod.POST)
    public void downloadAvatar(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        String userName = request.getHeader("userName");
        userName = URLDecoder.decode(userName, "UTF-8");
        String accessToken = request.getHeader("accessToken");
        HashMap avatarMap;
        avatarMap = userService.downloadAvatar(userName);
        System.out.println("avatarMap"+avatarMap.toString());
        String avatarStoreName = (String) avatarMap.get("avatar_store_name");  //
        String avatarRealName = (String) avatarMap.get("avatar_real_name");
        String avatar = (String) avatarMap.get("avatar");
        String contentType = ".jpg";

        /*BufferedReader br = request.getReader();
        String str;StringBuilder jsonString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            jsonString.append(str);
            System.out.println("str为" + str);
        }
        System.out.println("获取更改头像请求流：" + jsonString);*/
        if (userService.verifyAccessToken(userName, accessToken) == 4001) {
            System.out.println("文件操作22");
            System.out.println("realName1 "+avatarRealName);
            try {
                System.out.println("realName2 "+avatarRealName);
                FileOperator.download(request, response, avatarStoreName, contentType, avatarRealName, FileOperator.AVATAR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("token验证失败12121");
        }

    }



    /*
    删除用户实现，获取输入流，进行处理，返回结果。
    */

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BufferedReader br = req.getReader();
        System.out.println("获取注册请求流：" + br);

        String str, jsonString = "";
        while ((str = br.readLine()) != null) {
            jsonString += str;
            System.out.println("str为" + str);
        }
        System.out.println(jsonString);
        System.out.println("注册请求为" + jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String stuffTel = (String) jsonObject.get("stuffTel");
        System.out.println("删除用户请求的电话为" + stuffTel);
        int deleteResult = userService.deleteUser(stuffTel);
        System.out.println("此次删除的结果为" + deleteResult);
        String result1 = deleteResult + "This is a 删除用户结果";
        System.out.println("结果为" + result1);
        Map<String, Object> map = new HashMap<>();
        map.put("RESULT_KEY", registerResult);

        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        resp.addHeader("Accept-Encoding", "gzip");
        resp.setContentType("text/plain;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String result = JSON.toJSONString(map);
        System.out.println("结果为" + result);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(result);
        printWriter.close();

    }
}
