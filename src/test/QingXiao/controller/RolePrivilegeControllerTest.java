package QingXiao.controller;

import QingXiao.entity.Role;
import QingXiao.entity.UserInform;
import QingXiao.mappers.RoleMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by xpb on 2018/6/12.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class RolePrivilegeControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  CourseController courseController;
    @Resource
    protected RoleMapper roleMapper;


    private String jsonStringUserRegister="{'password':'123456','userName':'admin22'," +
            "'phoneNum':'18256914858','identifyCode':'4728','zone':'86'}";
    private String jsonStringInsertCourse1="{'password':'123456','userName':'admin22'," +
            "'phoneNum':'18256914858','identifyCode':'4728','zone':'86''password':'123456ab','phoneNum':'18256914858'}";

    private String jsonStringInsertRole="[{'roleName':'普通用户']";
    private String insertRoleUrl="/RolePrivilege/InsertRole";
    private String insertUserRoleUrl="/RolePrivilege/InsertUserRole";
    private String queryUserRoleUrl="/RolePrivilege/QueryUserRole";
    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidXNlcmlkIjoibnVsbCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTE3MzE0OTg5LCJuYmYiOjE1MTQ3MjI5ODl9.uJgOjO8djDQ5vlF2-9gQx-zrqqoECvDuWdqnxvczvpI";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void insertRoleTest1() throws Exception
    {


    }

    @Test
    public void insertRoleTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertRoleUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertRole.getBytes())
                        //  .param("userName","admin")   //添加参数(可以添加多个)
                        // .param("passWord","123")   //添加参数(可以添加多个)
                )
                .andExpect(status().isOk())    //返回的状态是200
                //.andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void insertUserRoleTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertUserRoleUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertRole.getBytes())
                        //  .param("userName","admin")   //添加参数(可以添加多个)
                        // .param("passWord","123")   //添加参数(可以添加多个)
                )
                .andExpect(status().isOk())    //返回的状态是200
                //.andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void queryUserRoleTest() throws Exception {
        String phoneNum="18256914858";
        UserInform userInform= roleMapper.queryRoleNameSetByPhoneNum(phoneNum);
        System.out.println("-----返回的userInform = " + userInform);
        System.out.println("-----返回的roleSet:" + userInform.getRoleList().size());
        List<Role> roles =userInform.getRoleList();
        System.out.println("-----返回的roleSet大小:" + roles.size());
        System.out.println("-----返回的role:"+roles.get(0).toString());
        for (Role role : roles) {
            System.out.println("-----返回的role" + role.toString());
            System.out.println("-----返回的roleID" + role.getRoleID());
            System.out.println("-----返回的roleName" + role.getRoleName());
        }
    }

}
