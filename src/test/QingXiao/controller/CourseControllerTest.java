package QingXiao.controller;

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

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by xpb on 2017/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class CourseControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  CourseController courseController;

    private String jsonStringUserRegister="{'password':'123456','userName':'admin22'," +
            "'phoneNum':'18256914858','identifyCode':'4728','zone':'86'}";
    private String jsonStringInsertCourse1="{'password':'123456','userName':'admin22'," +
            "'phoneNum':'18256914858','identifyCode':'4728','zone':'86''password':'123456ab','phoneNum':'18256914858'}";

    private String jsonStringInsertCourse="[{'teacherName':'小明','courseName':'软件工程课程设计','startYear':2017,'endYear':2018," +
            "'semester':1,'classsroom':'博南A101','dayOfWeek':1,'startSection':1,'endSection':2,'startWeek':1,'endWeek':17," +
            "'everyWeek':1,'sameTime':0}]";
    private String insertCourseUrl="/Course/Insert";

    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidXNlcmlkIjoibnVsbCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTE3MzE0OTg5LCJuYmYiOjE1MTQ3MjI5ODl9.uJgOjO8djDQ5vlF2-9gQx-zrqqoECvDuWdqnxvczvpI";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void insertTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertCourseUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertCourse.getBytes())
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

}
