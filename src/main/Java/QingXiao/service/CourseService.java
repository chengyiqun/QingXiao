package QingXiao.service;

import java.util.HashMap;

/**
 * Created by xpb on 2017/10/24.
 */
public interface CourseService {

    //void insertCourse(HashMap courseMap);

    /*
        上传时应该是好多课程记录一起上传，所以还要有一个循环。
        * 插入学生课程信息：（1）首先检查教师是否在数据库，不在则插入，在则获取教师ID
        * （2）检查课程是否在数据库，不在则插入，在则获取课程ID
        * （3）检查教学实体是否在数据库，不在则插入，在则获取教学实体ID
        *    教学实体检查通过教师ID，课程ID，时间确定唯一性。
        * （4）检查学生选课信息是否在数据库中，不在则插入。
        * */
  //  void insertCourse(String listJson);

    /*
        上传时应该是好多课程记录一起上传，所以还要有一个循环。
        * 插入学生课程信息：（1）首先检查教师是否在数据库，不在则插入，在则获取教师ID
        * （2）检查课程是否在数据库，不在则插入，在则获取课程ID
        * （3）检查教学实体是否在数据库，不在则插入，在则获取教学实体ID
        *    教学实体检查通过教师ID，课程ID，时间确定唯一性。
        * （4）检查学生选课信息是否在数据库中，不在则插入。
        * */
    int  insertCourse(String listJson, String userName);
}
