package QingXiao.service;

import QingXiao.entity.UserInform;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.JwtHelper;
import QingXiao.util.MobClient;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.net.ssl.*;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xpb on 2017/9/22.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private int result =3000;
    private int registerResult =3000;
    //private IdFactory idFactory = new IdFactory();

    private String appkey="10085a6d32d89";  // 短信登录sdk的

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserInformMapper userInformMapper;
    //String name, String userId, String role,
   // String audience, String issuer, long TTLMillis, String base64Security)
    private  static String role="Common";//
    private  static String audience="";  //接受者
    private  static String issuer="QingXiao";   //发行者
    private long TTLMillis=30L * 24L * 3600L * 1000L;//token有效时间,一个月
    private  static String base64Security="QingXiao96";//加密字符串
    public List<UserInform> findAll() {
        List<UserInform> list=userInformMapper.findAll();
        return list;
    }

    public HashMap login(String phoneNum, String password) {
        Connection connection = null;
        HashMap map = new HashMap();
        HashMap tokenMap = new HashMap();
        //1、先判断是否有相应的用户名
        //String userID =userInformMapper.queryUserIDByUserName(userName);
        String userID =userInformMapper.queryUserIDByPhoneNum(phoneNum);
        //验证用户名密码
        UserInform userInform = userInformMapper.getUserByPhoneNum(phoneNum);
        if (userInform != null) {
            System.out.println("qqqqqqqqqqq"+userInform.toString());
        }
        if (userID == null) {
            result=3003;  //用户不存在
            //return result;
        }else{
            // result=1;
            //2、再判断密码是否正确
            String  passWordTrue =userInformMapper.queryPasswordByUserID(userID);
            if (!passWordTrue.equals(password)) {
                result=3002;          //密码错误
                // return result;
            }else{
                result=3001;      //登录成功
                //拼装accessToken
                String accessToken = JwtHelper.createJWT(userInform.getUserName(), String.valueOf(userInform.getUserID()),
                        role, audience, issuer,TTLMillis,base64Security);
                System.out.println("登录的Token:"+accessToken);
                tokenMap.put("accessToken",accessToken);

                tokenMap.put("userID",userID);

                long nowMillis = System.currentTimeMillis();
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                tokenMap.put("expiresIn",exp);
                userInformMapper.updateAccessToken(tokenMap);
                //userInformMapper.updateExpiresIn(exp,userID);
                map.put("userName",userInform.getUserName());
                map.put("avatar",userInform.getAvatar());
                map.put("sex",userInform.getSex());
                map.put("accessToken",accessToken);
                map.put("userIntroduction",userInform.getUserIntroduction());
                //map.put("avatarStoreName",userInform.getAvatarRealName());
                //map.put("avatarRealName",userInform.getAvatarStoreName());
            }
        }


        map.put("result",result);
        System.out.println("登录返回结果："+map);
        return map;
    }

/*
* 注册流程：验证手机验证码，验证手机号是否已存在，
* 自动给用户生成一个系统的用户名（以后用户可以再修改）
*
* */
    public int register(HashMap infoMap) {
        HashMap userMap = new HashMap();
        System.out.println("注册之前Mapper!"+userInformMapper);
        System.out.println("注册之前!");
        String  userName=(String)infoMap.get("userName");
        String  phoneNum=(String)infoMap.get("phoneNum");
        String  password=(String)infoMap.get("password");
        String  identifyCode=(String)infoMap.get("identifyCode");
        String  zone=(String)infoMap.get("zone");
        System.out.println("注册之前!!!!"+userName);
        String verifyResult = null;
        try {
            verifyResult = checkcode(phoneNum,zone,identifyCode);
            System.out.println("验证码验证结果："+verifyResult);
            HashMap resultMap = (HashMap) JSON.parseObject(verifyResult,Map.class);
            //if((Integer)resultMap.get("status")==200){
                  if(true){
                System.out.println("验证码验证结果："+verifyResult);
            }else{
              return   result=2002;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String userID=userInformMapper.queryUserIDByUserName( userName);
        String userID=userInformMapper.queryUserIDByPhoneNum(phoneNum);
        System.out.println("注册之前222!");
        if (!(userID == null)) {
            result=2003;  //用户已存在
            System.out.println("用户已存在!");
            return result;
        }else{
            String  ID=IdFactory.getUUID();
            System.out.println("用户未存在，开始注册!");
            userMap.put("userID",ID);
            userMap.put("userName","用户"+IdFactory.getUUID());
            userMap.put("password",password);
            userMap.put("phoneNum",phoneNum);

            userMap.put("registerTime", TimeFactory.getCurrentTime());
            userInformMapper.insertUserAllMap(userMap);

            //userMapper.insertUserAuto(userName,passWord);  //错误
            System.out.println("注册成功!");
            result=2001;
        }

        return result;
    }




    @Override
    public int verifyAccessToken(HashMap map) {
        String accessToken =(String) map.get( "accessToken");
        String userName =(String) map.get( "userName");
        String userID= userInformMapper.queryUserIDByUserName(userName);
        String accessTokenServer = userInformMapper.selectAccessTokenByUserID(userID);
        if(accessTokenServer.equals(accessToken)&&JwtHelper.isJwtValid(accessToken)){
            return result=4001;
        }else{
            //token验证未通过，请重新登录
            return result=4000;
        }

        // return 1000;
    }

    @Override
    public int verifyAccessToken(String userName, String accessToken) {
        String userID= userInformMapper.queryUserIDByUserName(userName);
        System.out.println(userID);
        String accessTokenServer = userInformMapper.selectAccessTokenByUserID(userID);
        System.out.println("accessTokenServer："+accessTokenServer);
        if (accessTokenServer != null) {
            System.out.println("Token验证结果1："+accessTokenServer.equals(accessToken));
        }else {
            System.out.println("accessTokenServer空");
            return 4000;
        }
        System.out.println("Token验证结果2："+JwtHelper.isJwtValid(accessToken));
            if(accessTokenServer.equals(accessToken)&&JwtHelper.isJwtValid(accessToken)){
                return result=4001;
            }else{
                //token验证未通过，请重新登录
                return result=4000;
            }


        // return 1000;
    }

    @Override
    public void updateAvatar(String avatar, String userName,String avatarRealName,String avatarStoreName) {
       userInformMapper.updateAvatar(avatar,userName);
       userInformMapper.updateAvatarName(avatarStoreName,avatarRealName,userName);
    }
    @Override
    public HashMap downloadAvatar(String userName){
        HashMap avatarMap;
        avatarMap=userInformMapper.selectAvatarName(userName);
        return avatarMap;
    }

    @Override
    public int changeName(String userName,String newName) {
        int result = 0;
        System.out.println("改名之前!");
        String userID= userInformMapper.queryUserIDByUserName(userName);
        System.out.println("改名之前222!");
        if ((userID == null)) {
            System.out.println("用户不存在,非法操作!");
            return result;
        }else{
            System.out.println("用户存在，开始改名!");
            String newNameID=userInformMapper.queryUserIDByUserName(newName);
            if (newNameID==null){
                System.out.println("新用户名不存在，可以改名");
                userInformMapper.changeName(newName,userID);
                result = 3007;
            }else {
                System.out.println("新用户名已存在");
                result=3008;
            }


        }
        return result;
    }

    public int  deleteUser(String userName) {
        System.out.println("删除之前!");
        String userID= userInformMapper.queryUserIDByUserName(userName);
        System.out.println("删除之前222!");
        if ((userID == null)) {
            result=0;  //用户不存在
            System.out.println("用户名不存在!");
            return result;
        }else{
            System.out.println("用户名存在，开始删除!");
            userInformMapper.deleteUser(userID);
            System.out.println("删除成功!");
            result=1;
        }

        return result;

    }


    public int updatePassword(String userName,String password) {
        System.out.println("更改之前!");
        String userID= userInformMapper.queryUserIDByUserName(userName);
        System.out.println("更改之前222!");
        if ((userID == null)) {
            result=0;  //用户不存在
            System.out.println("用户名不存在!");
            return result;
        }else{
            System.out.println("用户名存在，开始更改!");
            userInformMapper.updatePassword(userID,password);
            //userMapper.insertUserAuto(userName,passWord);  //错误
            System.out.println("更改成功!");
            result=1;
        }
        return result;
    }

    /**
     * 服务端发验证服务端发送的短信
     * @return
     * @throws Exception
     */
    public  String checkcode(String phone,String zone,String code) throws Exception{

        String address = "https://webapi.sms.mob.com/sms/verify";
        MobClient client = null;
        try {
            client = new MobClient(address);
            client.addParam("appkey", appkey).addParam("phone", phone)
                    .addParam("zone", zone).addParam("code", code);
            client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            client.addRequestProperty("Accept", "application/json");
            String result = client.post();
            return result;
        } finally {
            client.release();
        }
    }

}
