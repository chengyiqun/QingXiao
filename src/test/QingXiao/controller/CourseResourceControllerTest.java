package QingXiao.controller;

import com.sun.net.httpserver.Headers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

/**
 * Created by xpb on 2017/10/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class CourseResourceControllerTest {


    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    @Autowired
    protected  CourseResourceController courseResourceController;


    private String jsonStringUploadCourseResource="{'resourceDescribe':'考试试卷','courseName':'软件工程课程设计','resourceType':2017}";
   // private String jsonStringDownloadCourseResource="{'resourcePath':'D:\\Users\\xpb\\IdeaProjects\\QingXiao\\target\\test-classes\\courseResource\\727617f18046408493bc9a383532484b.jpg','fileName':'软件工程课程设计期末试卷'}";
    private String jsonStringDownloadCourseResource="{\"fileName\":\"软件工程课程设计期末试卷\",\"resourcePath\":\"D:\\\\Users\\\\xpb\\\\IdeaProjects\\\\QingXiao\\\\target\\\\test-classes\\\\courseResource\\\\727617f18046408493bc9a383532484b.jpg\"}";

    private String uploadCourseResourceUrl="/CourseResource/Upload";
    private String uploadCourseResourceUrl1="/CourseResource/Upload1";
    private String jsonStringGetCourseResourceList="{'courseName':'软件工程课程设计','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-27 20:52:46.517'}";

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
                (post(uploadCourseResourceUrl)          //请求的url,请求的方法是get
                                //get("/user/showUser2")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                                .body(jsonStringUploadCourseResource.getBytes())
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
    public void testAdd() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\5.jpg");
        MockMultipartFile file = new MockMultipartFile("file","5.jpg","image/jpeg",fis);
        FileInputStream fis2 = new FileInputStream("C:\\Users\\Public\\Pictures\\5.jpg");
        MockMultipartFile file2 = new MockMultipartFile("file","5.jpg","image/jpeg",fis2);

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        request.addFile(file);
        request.addFile(file2);
        //RequestBody requestBody=RequestBody.create(null, jsonStringUploadCourseResource);
        request.setParameter("name","test");
        request.setParameter("value","tttt");
        request.setParameter("isUse","true");
        request.setContent(jsonStringUploadCourseResource.getBytes()); //设置请求体
        //request.addPart();
        //request.addPart("name",new Part("中国心", Charset.forName("UTF-8")));
        //request.addPart(jsonStringUploadCourseResource);
        request.addHeader("accessToken",accessToken);
        request.addHeader("userName","hehehe");
        System.out.println("更改头像请求courseResourceController为" +courseResourceController);
        String jsonResultObject = courseResourceController.uploadCourseResource1(request);
       // System.out.println(jsonResultObject.getData());
        System.out.println(jsonResultObject);
    }

    @Test
    public void download() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("name","test");
        request.setParameter("value","tttt");
        request.setParameter("resourceStoreName","e4f94e875a874f749ee3be1ef374c976.jpg");
       // request.setContent(jsonStringDownloadCourseResource.getBytes()); //设置请求体
        //request.addPart(jsonStringUploadCourseResource);
        request.addHeader("accessToken",accessToken);
        request.addHeader("userName","hehehe");

        System.out.println("下载课程资源请求courseResourceController为" +courseResourceController);
        courseResourceController.downloadCourseResource(request,response);
        // System.out.println(jsonResultObject.getData());
        int length= response.getContentLength();
        System.out.println("返回内容长度为："+length);
    }

    @Test
    public void getCourseResourceList() throws IOException {
        //RegisterConfig.RootPath = "E:\\work\\Admin";

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("name","test");
        request.setParameter("value","tttt");
        request.setParameter("isUse","true");
        request.setContent(jsonStringGetCourseResourceList.getBytes()); //设置请求体
        //request.addPart(jsonStringUploadCourseResource);
        request.addHeader("accessToken",accessToken);
        request.addHeader("userName","hehehe");

        System.out.println("获取课程资源list请求courseResourceController为" +courseResourceController);
         String result =courseResourceController.getCourseResourceList(request);
        System.out.println("返回内容为："+result);
    }
}
