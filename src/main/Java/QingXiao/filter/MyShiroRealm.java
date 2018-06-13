package QingXiao.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import QingXiao.entity.Privilege;
import QingXiao.entity.Role;
import QingXiao.entity.UserInform;
import QingXiao.mappers.RoleMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * Created by xpb on 2018/6/9.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    RoleMapper roleMapper;

    //这里因为没有调用后台，直接默认只有一个用户("luoguohui"，"123456")
    private static final String USER_NAME = "18256914858";
    private static final String PASSWORD = "123456";



    /**
     * 登录验证 AuthenticationToken token 表示页面传过来的用户名和密码
     * 逻辑：通过用户名查询数据库，获取到该用户的密码，封装到AuthenticationInfo对象中，返回。
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("进入验证：" + authcToken);
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //String userName=(String)token.getPrincipal();
        String userName=(String)token.getUsername();
        System.out.println("获取的用户名：" +userName);
        String  password=(String)token.getPassword().toString();
        System.out.println("获取的密码：" +password);
        String  password2=String.valueOf(token.getPassword());
        System.out.println("获取的密码：" +password2);
        //String  password1=(String)token.getCredentials();
        //System.out.println("获取的密码：" +password1);
        // 通过查询数据库进行比较用户名密码，
        AuthenticationInfo info =null;
        if(userName.equals(USER_NAME)){
            System.out.println("验证成功：" );
            // 获取到该用户，构造方法，第二个参数表示密码，第三个参数随便定义字符串的名称
            info=new SimpleAuthenticationInfo(USER_NAME, (PASSWORD), getName());
            System.out.println("验证成功11111!" );

        }else{
            System.out.println("验证失败11111!" );
            throw new AuthenticationException();
        }
        System.out.println("返回数据!" );
        return info;

    }

    /*
   * 授权
   */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("进入授权pc：" + pc);

        //获取用户名
        String phoneNum=(String)pc.getPrimaryPrincipal();
        System.out.println("进入授权phoneNum：" + phoneNum);
        //从AuthenticationInfo中获取到用户对象
        //UserInform user = (UserInform) pc.fromRealm(this.getName()).iterator().next();
        List<String> list = new ArrayList<String>();
        // 继续操作，通过对象导航的方式，获取到该用户下的角色，具有哪些权限
        UserInform userInform= roleMapper.queryRoleNameSetByPhoneNum(phoneNum);
        System.out.println("-----返回的userInform = " + userInform);

        List<Role> roles =userInform.getRoleList();
        Set<String> roleNameSet=new HashSet<>();
        // 遍历，获取到每一个角色对象
        for (Role role : roles) {
            // 通过角色对象获取到该角色具有的权限
            System.out.println("-----返回的RoleName-----" + role.getRoleName());
            roleNameSet.add(role.getRoleName());

           /* Set<Privilege> privilegeSet = role.getPrivilegeSet();
            for (Privilege privilege :privilegeSet) {
                list.add(privilege.getPrivilegeName());
            }*/
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //进行授权角色
        info.setRoles(roleNameSet);
        //进行授权权限
        info.addStringPermissions(list);
        //info.setStringPermissions(list);
        System.out.println("-----授权完成 " );
        return info;
    }

}