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
 * Created by xpb on 2018/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class SubjectControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  UserController userController;

    private String jsonStringSubjectInsert="{'content':'一周打卡','subjectType':'娱乐'}";
    private String jsonStringUserLogin="{'password':'123456','phoneNum':'18256914858'}";

    private String subjectInsertUrl="/Subject/Insert";
    private String userLoginUrl="/User/Login";

    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidXNlcmlkIjoibnVsbCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTE3MzE0OTg5LCJuYmYiOjE1MTQ3MjI5ODl9.uJgOjO8djDQ5vlF2-9gQx-zrqqoECvDuWdqnxvczvpI";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void subjectInsertTest() throws Exception
    {
        String responseString = mockMvc.perform
                (
                        post(subjectInsertUrl, "json").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonStringSubjectInsert.getBytes())
                                .header("userName","admin1")
                                .header("accessToken",accessToken)
                )
                //  .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())       //打印出请求和相应的内容
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
                                .header("userName","admin1")
                                .header("accessToken",accessToken)
                        //  .param("userName","admin")   //添加参数(可以添加多个)
                        // .param("passWord","123")   //添加参数(可以添加多个)
                )
                .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testAdd() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\5.jpg");
        MockMultipartFile file = new MockMultipartFile("file","5.jpg","image/jpeg",fis);

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        request.addFile(file);

        request.setParameter("name","test");
        request.setParameter("value","tttt");
        request.setParameter("isUse","true");
        request.addHeader("accessToken",accessToken);
        request.addHeader("userName","admin1");
        System.out.println("更改头像请求userController为" + userController);
        String jsonResultObject = userController.updateAvatar(request);
        //System.out.println(jsonResultObject.getData());
    }

}
