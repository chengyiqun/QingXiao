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
public class TopicControllerTest {


    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  TeachCommentController teachCommentController;
    @Autowired
    protected  TopicController topicController;

    private String jsonStringInsertTopic="{'subjectID':'af9efadc5724433eb8a461c687873064' ,'dontMaskStranger':0,'content':'下雪啦！！！','topicPlace':'安徽大学罄苑校区','lableList':[{'lable':'心情'},{'lable':'休闲'}]}";
    private String insertTopicUrl="/Topic/Insert";

    private String jsonStringGetTopicList="{'subjectID':'af9efadc5724433eb8a461c687873064'  ,'sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getTopicUrl="/Topic/GetTopicList";


    private String jsonStringInsertTopicComment="{'topicID':'d8bedabf84df4488b7fdc6037e4b4722' ,'commentType':0,'content':'66666','toUserID':'8e59adf6f18045b29bccb02bea96e933'}";
    private String jsonStringInsertTopicCommentReply="{'topicID':'d8bedabf84df4488b7fdc6037e4b4722' ,'commentType':1,'content':'99999','objectID':'faeb6f8d5351440bbccb39f0df0409ff','toUserID':'8e59adf6f18045b29bccb02bea96e933','userID':''}";
    private String jsonStringInsertTopicCommentReplyReply="{'topicID':'d8bedabf84df4488b7fdc6037e4b4722' ,'commentType':1,'content':'这是回复的回复','objectID':'3a93bd07e2914b28a83aebdabff2300f','toUserID':'8e59adf6f18045b29bccb02bea96e933','userID':''}";

    private String insertTopicCommentUrl="/Topic/InsertComment";

    private String jsonStringGetTopicCommentList="{'topicID':'adabcbe79dca495dae252b28fb190bb8' ,'sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getTopicCommentUrl="/Topic/GetCommentList";
    private String jsonStringGetTopicCommentReplyList="{'commentID':'faeb6f8d5351440bbccb39f0df0409ff','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getTopicCommentReplyUrl="/Topic/GetCommentReplyList";

    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidW5pcXVlX25hbWUiOiLnqIvkuYnnvqQiLCJ1c2VyaWQiOiI2N2IxOGY1MWEyNDM0YTI3OTEzZDliMDNjMmQwMjAxZCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTI5NTAzOTM1LCJuYmYiOjE1MjY5MTE5MzV9.m2L7JCIJ0zIvO7zjDUzXJbZ4dKEtZQbe7s04dmS5xfY";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void insertTopicTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertTopicUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTopic.getBytes())
                                .header("userName","程义群")
                                .header("accessToken",accessToken)
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
    public void insertTopicAdd() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\5.jpg");
        MockMultipartFile file = new MockMultipartFile("0","5.jpg","image/jpeg",fis);
        FileInputStream fis2 = new FileInputStream("C:\\Users\\Public\\Pictures\\clari.png");
        MockMultipartFile file2 = new MockMultipartFile("1","clari.png","image/jpeg",fis2);

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        request.addFile(file);
        request.addFile(file2);
        //RequestBody requestBody=RequestBody.create(null, jsonStringUploadCourseResource);
        request.setParameter("QingXiao","test");
        request.setParameter("picOrder","0");
        request.setParameter("isUse","true");
        request.setContent(jsonStringInsertTopic.getBytes()); //设置请求体
        //request.addPart();
        //request.addPart("name",new Part("中国心", Charset.forName("UTF-8")));
        //request.addPart(jsonStringUploadCourseResource);
        request.addHeader("accessToken",accessToken);
        request.addHeader("userName","程义群");
        System.out.println("更改头像请求topicController为" +topicController);
        String jsonResultObject = null;
        try {
            jsonResultObject = topicController.insertTopic(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(jsonResultObject.getData());
        System.out.println(jsonResultObject);
    }

    @Test
    public void getTopicListTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(getTopicUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetTopicList.getBytes())
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
    public void insertCommentTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertTopicCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTopicComment.getBytes())
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
    public void insertReplyReplyTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertTopicCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTopicCommentReplyReply.getBytes())
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
                (post(insertTopicCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertTopicCommentReply.getBytes())
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
                (post(getTopicCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetTopicCommentList.getBytes())
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
                (post(getTopicCommentReplyUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetTopicCommentReplyList.getBytes())
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
