package QingXiao.controller;

import QingXiao.service.RolePrivilegeService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xpb on 2018/6/12.
 */
@Controller
@RequestMapping("/RolePrivilege")
public class RolePrivilegeController {
    @Autowired
    protected RolePrivilegeService rolePrivilegeService;
    private int result=0;
    /*
     插入角色实现，获取输入流，进行处理，返回结果。
     */
    @RequestMapping(value = "/InsertRole",method= RequestMethod.POST)
    @ResponseBody
    public void insertRole(String jsonString) throws Exception {
        System.out.println("请求rolePrivilegeService为" +rolePrivilegeService);
        jsonString="{'roleName':'User2'}";
        rolePrivilegeService.insertRole(jsonString);
    }


    /*
    插入用户角色实现，获取输入流，进行处理，返回结果。
    */
    @RequestMapping(value = "/InsertPrivilege",method= RequestMethod.POST)
    @ResponseBody
    public void insertPrivilege(String jsonString) throws Exception {
        System.out.println("请求rolePrivilegeService为" +rolePrivilegeService);
        jsonString="{'roleName':'User2'}";
        rolePrivilegeService.insertPrivilege(jsonString);
    }

    /*
    插入角色实现，获取输入流，进行处理，返回结果。
    */
    @RequestMapping(value = "/InsertUserRole",method= RequestMethod.POST)
    @ResponseBody
    public void insertUserRole(String jsonString) throws Exception {
        System.out.println("请求rolePrivilegeService为" +rolePrivilegeService);
        jsonString="{'roleName':'User2','phoneNum':'18256914858'}";
        rolePrivilegeService.insertUserRole(jsonString);
    }


    /*
    插入用户角色实现，获取输入流，进行处理，返回结果。
    */
    @RequestMapping(value = "/InsertRolePrivilege",method= RequestMethod.POST)
    @ResponseBody
    public void insertRolePrivilege(String jsonString) throws Exception {
        System.out.println("请求rolePrivilegeService为" +rolePrivilegeService);
        jsonString="{'roleName':'User1'}";
        rolePrivilegeService.insertRolePrivilege(jsonString);
    }
}
