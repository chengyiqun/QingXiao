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
public class TeachCommentControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  TeachCommentController teachCommentController;


    private String jsonStringInsertTeachComment="{'teachID':'adabcbe79dca495dae252b28fb190bb8' ,'commentType':0,'score':90,'content':'很好！！！','isAnonymous':1,'toUserId':'','userID':''}";
    private String insertTeachCommentUrl="/TeachComment/Insert";
    private String jsonStringInsertTeachCommentReply="{'teachID':'adabcbe79dca495dae252b28fb190bb8' ,'commentType':1,'score':90,'content':'很好！！！','isAnonymous':1,'objectID':'94e7f049355143f6bb1c5a6771336401','toUserID':'8e59adf6f18045b29bccb02bea96e933','userID':''}";
    private String insertTeachCommentReplyUrl="/TeachComment/Insert";

    private String jsonStringGetTeachCommentList="{'teachID':'adabcbe79dca495dae252b28fb190bb8' ,'sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getTeachCommentUrl="/TeachComment/GetCommentList";
    private String jsonStringGetTeachCommentReplyList="{'teachCommentID':'94e7f049355143f6bb1c5a6771336401','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getTeachCommentReplyUrl="/TeachComment/GetCommentReplyList";

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
                (post(insertTeachCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTeachComment.getBytes())
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
    public void insertReplyTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertTeachCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTeachCommentReply.getBytes())
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
    public void getCommentListTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(getTeachCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetTeachCommentList.getBytes())
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
    public void getReplyListTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(getTeachCommentReplyUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetTeachCommentReplyList.getBytes())
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
