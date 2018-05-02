package QingXiao.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.io.FileInputStream;
import java.io.IOException;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


/**
 * Created by xpb on 2017/10/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc ;
    @Autowired
    protected  UserController userController;

    /*private String jsonString="{'password':'123','userName':'admin'}";
    private String jsonStringUserRegister1="{'password':'123456','userName':'admin22'," +
            "'phoneNum':'15605653880','identifyCode':'9629','zone':'86'}";*/

    private String jsonStringUserRegister="{'password':'123456','userName':'程义群'," +
            "'phoneNum':'15605653880','identifyCode':'1981','zone':'86'}";
    private String jsonStringUserLogin="{'password':'123456','phoneNum':'15605653880'}";

    private String userRegisterUrl="/User/Register";
    private String userLoginUrl="/User/Login";

    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidW5pcXVlX25hbWUiOiLnqIvkuYnnvqQiLCJ1c2VyaWQiOiI2N2IxOGY1MWEyNDM0YTI3OTEzZDliMDNjMmQwMjAxZCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTI3ODQ3MzU3LCJuYmYiOjE1MjUyNTUzNTd9.sXdOgBUHVeL8k0q7_SCezN2btEASOzwpOurqvqFb5lI";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void registerTest() throws Exception
    {
        String responseString = mockMvc.perform
                (
                        post(userRegisterUrl, "json").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonStringUserRegister.getBytes())

                )
                //  .andExpect(status().isOk())    //返回的状态是200
                //.andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void loginTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(userLoginUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                        .body(jsonStringUserLogin.getBytes())
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
    public void testAdd() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\avator.jpg");
        MockMultipartFile file = new MockMultipartFile("file","avator.jpg","image/jpeg",fis);

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        request.addFile(file);

        request.setParameter("name","test");
        request.setParameter("value","tttt");
        request.setParameter("isUse","true");
        request.addHeader("accessToken",accessToken);
        request.addHeader("phoneNum","15605653880");
        System.out.println("更改头像请求userController为" + userController);
        String jsonResultObject = userController.updateAvatar(request);
        //System.out.println(jsonResultObject.getData());
    }





}