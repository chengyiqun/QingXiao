package QingXiao.service;

import QingXiao.mappers.CourseMapper;
import QingXiao.mappers.CourseResourceMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
@Service("courseResourceService")
@Transactional
public class CourseResourceServiceImpl implements CourseResourceService {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    CourseResourceMapper courseResourceMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    CourseMapper courseMapper;
    private int result=0;
    @Override
    public int insertCourseResource(String jsonString, String userName, String resourcePath, String fileName, String resourceType,String resourceStoreName) {
        HashMap courseResourceMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String courseResourceID = IdFactory.getUUID();
        String userID=userInformMapper.queryUserIDByUserName(userName);
        String courseName=(String) courseResourceMap.get("courseName");
        String courseID = courseMapper.selectCourseIDByCourseName(courseName);
        if(userID!=null) {
            System.out.println("userID不为空");

            if (courseID == null) {
                System.out.println("courseID为空");
                return result = 3223;
            } else {
            }
            //String resourcePath=""; resourceType
            courseResourceMap.put("resourcePath", resourcePath);
            courseResourceMap.put("fileName", fileName);
            courseResourceMap.put("resourceType",resourceType);
            courseResourceMap.put("userID", userID);

            courseResourceMap.put("courseID", courseID);
            courseResourceMap.put("courseResourceID", courseResourceID);
            courseResourceMap.put("resourceStoreName", resourceStoreName);
            courseResourceMap.put("uploadTime", TimeFactory.getCurrentTime());
            courseResourceMap.put("downloadTimes", 0);
            courseResourceMap.put("commentTimes", 0);
            courseResourceMap.remove("courseName");
            courseResourceMapper.insertCourseResourceAllMap(courseResourceMap);
            return result =3301;
        }
        return result=3003;
    }

    @Override
    public int downloadCourseResource(String resourceStoreName) {
        courseResourceMapper.updateResourceDownloads(resourceStoreName);
        return result=1000;
    }

    /*
    * 获取课程资源信息列表，根据插入时间获取一定范围内的记录，uploadTime
    * */
    @Override
    public List<Map> getCourseResourceList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String courseName =(String)map.get("courseName");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
       // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);
        String courseID="";
        if(!courseName.equals("")){
           courseID=courseMapper.selectCourseIDByCourseName(courseName);
        }
        List<Map> list= new LinkedList<>();
        if(courseID!=null) {
             System.out.println("课程ID不为空");
             //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
             list = courseResourceMapper.selectCourseResource(courseID,sinceTime,nowTime);
             System.out.println("课程资源信息List:" + list);
        }
        return list;
    }


}
