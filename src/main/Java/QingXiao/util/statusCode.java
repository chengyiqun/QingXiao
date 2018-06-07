package QingXiao.util;

/**
 * Created by xpb on 2017/10/22.
 */
public class statusCode {
    /*
    * 注册相关
    * */
    public  static int REGISTER_SUCCESS=2001;
    public  static int IDENTIFYCODE_ERROR=2002;
    public  static int USER_EXISTED=2003;
    public  static int USER_ILLEGAL=2004;

    /*
    * 登录相关
    * */
    public  static int LOGIN_SUCCESS=3001;
    public  static int PASSWORD_ERROR=3002;
    public  static int USER_NOTEXIST=3003;

    /*
    *
    * */
    public  static int TOKEN_ERROR=3004;
    public  static int UPDATE_AVATAR_SUCCESS=3005;
    /*
     * 域名全局变量
     * */

    public  static String DOMAIN="www.qingxiao.xin";
}
