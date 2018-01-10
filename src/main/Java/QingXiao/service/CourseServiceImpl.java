package QingXiao.service;

import QingXiao.mappers.CourseMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/24.
 */
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    CourseMapper courseMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    private int result=0;

    /*
    上传时应该是好多课程记录一起上传，所以还要有一个循环。
    * 插入学生课程信息：（1）首先检查教师是否在数据库，不在则插入，在则获取教师ID
    * （2）检查课程是否在数据库，不在则插入，在则获取课程ID
    * （3）检查教学实体是否在数据库，不在则插入，在则获取教学实体ID
    *    教学实体检查通过教师ID，课程ID，时间确定唯一性。
    * （4）检查学生选课信息是否在数据库中，不在则插入。
    * */
    @Override
    public int insertCourse(String listJson, String userName) {
        //String listJson="";
        List<Map> list1 = JSON.parseArray(listJson, Map.class);
        for(Map<String, Object>  map: list1){

            HashMap<String, Object> courseMap = new HashMap<>();
            HashMap<String, Object> teacherMap = new HashMap<>();
            HashMap<String, Object> teachMap = new HashMap<>();
            HashMap<String, Object> studentCourseMap = new HashMap<>();
            //String userName=(String) map.get("userName");

            String teacherName = (String) map.get("teacherName");

            //课程实体信息
            String courseCode=(String) map.get("courseCode");
            String courseNature =(String) map.get("courseNature");
           // int credit =(Integer) map.get("credit");
           // int classHours=(Integer) map.get("classHours");
            String courseName = (String) map.get("courseName");
            courseMap.put("courseCode", courseCode);
            courseMap.put("courseNature", courseNature);
            //courseMap.put("credit",credit);
           // courseMap.put("classHours",classHours);
            courseMap.put("courseName",courseName);

            //教师实体信息
            teacherMap.put("teacherName",teacherName);

            //教学实体信息
            int startYear=(Integer) map.get("startYear");
            int endYear =(Integer) map.get("endYear");
            int semester =(Integer) map.get("semester");
            String classsroom = (String) map.get("classsroom");
            int dayOfWeek=(Integer) map.get("dayOfWeek");
            int startSection=(Integer) map.get("startSection");
            int endSection =(Integer) map.get("endSection");
            int startWeek =(Integer) map.get("startWeek");
            int endWeek=(Integer) map.get("endWeek");
            int everyWeek =(Integer) map.get("everyWeek");
            int sameTime=(Integer) map.get("sameTime");
            String teachTime = String.valueOf(startYear)+"-"+String.valueOf(endYear)+"-"+String.valueOf(semester);

            teachMap.put("startYear",startYear);
            teachMap.put("endYear",endYear);
            teachMap.put("semester",semester);
            teachMap.put("classsroom",classsroom);
            teachMap.put("dayOfWeek",dayOfWeek);
            teachMap.put("startSection",startSection);

            teachMap.put("endSection",endSection);
            teachMap.put("startWeek", startWeek);
            teachMap.put("endWeek",endWeek);
            teachMap.put("everyWeek",everyWeek);
            teachMap.put("sameTime",sameTime);
            teachMap.put("teachTime",teachTime);
            teachMap.put("startDate",TimeFactory.getCurrentTimeDate()); //该字段有待商量
            teachMap.put("downloadTimes", 0);
            teachMap.put("commentTimes", 0);


            // 学生选课实体信息
            courseMap.put("chooseCourseTime", TimeFactory.getCurrentTimeDate());

            String userID=userInformMapper.queryUserIDByUserName(userName);
            if(userID!=null) {
                System.out.println("userID不为空");
                String courseID = courseMapper.selectCourseIDByCourseName(courseName);
                if (courseID == null) {
                    System.out.println("courseID为空");
                    courseID = IdFactory.getUUID();
                    courseMap.put("courseID", courseID);
                    courseMapper.insertCourseAllMap(courseMap);
                    System.out.println("插入课程信息成功");
                } else {
                }
                String teacherID = courseMapper.selectTeacherIDByTeacherName(teacherName);
                if (teacherID == null) {
                    System.out.println("teacherID为空");
                    teacherID = IdFactory.getUUID();
                    teacherMap.put("teacherID", teacherID);
                    courseMapper.insertTeacherAllMap(teacherMap);
                    System.out.println("插入教师信息成功");
                } else {
                }
                String teachID = courseMapper.selectTeachID(teacherID, courseID, teachTime);
                if (teachID == null) {
                    System.out.println("teachID为空");
                    teachID = IdFactory.getUUID();
                    teachMap.put("teacherID",teacherID);
                    teachMap.put("courseID", courseID);
                    teachMap.put("teachID", teachID);
                    courseMapper.insertTeachAllMap(teachMap);
                    System.out.println("插入教学实体信息成功");
                }
                String studentCourseID=courseMapper.selectStudentCourseID(teachID,userID);
                if (studentCourseID == null) {
                    System.out.println("studentCourseID为空");
                    studentCourseID = IdFactory.getUUID();
                    studentCourseMap.put("studentCourseID", studentCourseID);
                    studentCourseMap.put("teachID",teachID);
                    studentCourseMap.put("userID",userID);
                    courseMapper.insertStudentCourseAllMap(studentCourseMap);
                    System.out.println("插入学生选课信息成功");
                    result=3201;
                }else{
                    //课程信息已上传导入到数据库
                   result=3202;
                }

            }else{
                // 用户不存在
                result=3003;
            }
        }


     return  result;

    }
}
