package QingXiao.controller;

import QingXiao.entity.CourseResource;
import QingXiao.entity.Reply;
import QingXiao.mappers.ResourceCommentMapper;
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

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by xpb on 2017/10/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class ResourceCommentControllerTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  ResourceCommentController resourceCommentController;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    protected ResourceCommentMapper resourceCommentMapper;

    //private String jsonStringInsertResourceComment="{'resourceStoreName':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','score':90,'content':'很实用！！！','objectID':'','commentType':0}";
   // private String insertResourceCommentUrl="/ResourceComment/Insert";
    //private String jsonStringInsertResourceCommentReply="{'resourceStoreName':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','score':90,'content':'很实用！！！','objectID':'8d06d45cc5954c84ab2182e7b09d1892','commentType':1,'toUserID':'8e59adf6f18045b29bccb02bea96e933'}";
    private String jsonStringInsertResourceComment="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','score':90,'content':'很实用！！！','commentType':0}";
    private String insertResourceCommentUrl="/ResourceComment/Insert";
    private String jsonStringInsertResourceCommentReply="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','score':95,'content':'很实用！！！','objectID':'96712ec5fa564c1e98f7e12f92a88df1','commentType':1,'toUserID':'8e59adf6f18045b29bccb02bea96e933'}";
    private String jsonStringInsertResourceComment1="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','score':90,'content':'很实用！！！','commentType':0}";
    private String jsonStringInsertResourceCommentReply1="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','score':95,'content':'很实用！！！','objectID':'96712ec5fa564c1e98f7e12f92a88df1','commentType':1,'toUserID':'8e59adf6f18045b29bccb02bea96e933'}";


    //,'userID':'8e59adf6f18045b29bccb02bea96e933'  e7d9614dffc94056a6d789c173eca070
    //private String jsonStringGetResourceCommentList="{'resourceStoreName':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String jsonStringGetResourceCommentList1="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String jsonStringGetResourceCommentList="{'courseResourceID':'9711e9bd0aa6490ca8bf3f25241dd6a5','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";

    private String getResourceCommentUrl="/ResourceComment/GetCommentList";
    private String jsonStringGetResourceCommentReplyList="{'resourceCommentID':'8d06d45cc5954c84ab2182e7b09d1892','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}";
    private String getResourceCommentReplyUrl="/ResourceComment/GetCommentReplyList";


    private String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidXNlcmlkIjoibnVsbCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTE3MzE0OTg5LCJuYmYiOjE1MTQ3MjI5ODl9.uJgOjO8djDQ5vlF2-9gQx-zrqqoECvDuWdqnxvczvpI";
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void insertResourceCommentTest() throws Exception
    {
        String responseString = mockMvc.perform
                (post(insertResourceCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertResourceComment.getBytes())
                                .header("userName","hehehe")
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
                (post(insertResourceCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringInsertResourceCommentReply.getBytes())
                                .header("userName","hehehe")
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
                (post(getResourceCommentUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetResourceCommentList1.getBytes())
                                .header("userName","hehehe")
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
                (post(getResourceCommentReplyUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringGetResourceCommentReplyList.getBytes())
                                .header("userName","hehehe")
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
    public void getResourceCommentListMap(){
        String sinceTime="2017-10-26 22:02:25";
        String nowTime="2018-10-26 22:02:25";
        List<Reply> list=  resourceCommentMapper.selectResourceCommentList("f2eafd3fd35e447499ab6d7fd23fb90d.jpg",sinceTime,nowTime);
        System.out.println("-----返回的list = " +list.toString()+list.size()+list.get(0));
        for(Object  map1: list){
            System.out.println("-----返回的courseResource = " +map1);
           // System.out.println("-----返回的courseResource = " +map1.getFileName());
           // System.out.println("-----返回的courseResource = " +map1.getUserInform().getUserName());
           // System.out.println("-----返回的courseResource = " +map1.getResourceCommentList().size());
        }
    }

    @Test
    public void getResourceCommentReplyListMap(){
        String sinceTime="2017-10-26 22:02:25";
        String nowTime="2018-10-26 22:02:25";
        List<Map> list=  resourceCommentMapper.selectResourceCommentReplyList("8d06d45cc5954c84ab2182e7b09d1892",sinceTime,nowTime);
        System.out.println("-----返回的list = " +list.toString()+list.size()+list.get(0));
        for(Object  map1: list){
            System.out.println("-----返回的courseResource = " +map1);
            // System.out.println("-----返回的courseResource = " +map1.getFileName());
            // System.out.println("-----返回的courseResource = " +map1.getUserInform().getUserName());
            // System.out.println("-----返回的courseResource = " +map1.getResourceCommentList().size());
        }
    }

  @Test
    public void getResourceCommentList(){
      List<CourseResource> list=  resourceCommentMapper.selectResourceComment1("f2eafd3fd35e447499ab6d7fd23fb90d.jpg");
        System.out.println("-----返回的list = " +list.toString()+list.size()+list.get(0));
        for(CourseResource  courseResource: list){
            System.out.println("-----返回的courseResource = " +courseResource);
            System.out.println("-----返回的courseResource = " +courseResource.getFileName());
             }
    }

    @Test
    public void getResourceCommentList1(){
       CourseResource courseResource= resourceCommentMapper.selectResourceComment("f2eafd3fd35e447499ab6d7fd23fb90d.jpg");
        System.out.println("-----返回的list = " +courseResource.toString());

            System.out.println("-----返回的courseResource = " +courseResource);
            System.out.println("-----返回的courseResource = " +courseResource.getFileName());
         //   System.out.println("-----返回的courseResource = " +courseResource.getUserInform().getUserName());

    }
}
