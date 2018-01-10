package QingXiao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * Created by xpb on 2017/10/24.
 */
public interface CourseMapper {
    void insertCourseAllMap(HashMap map);
    void insertTeacherAllMap(HashMap map);
    void insertTeachAllMap(HashMap map);
    void insertStudentCourseAllMap(HashMap map);
    String selectStudentCourseID(@Param("teachID") String teachID,@Param("userID") String userID );
    String selectTeachID(@Param("teacherID") String teacherID,@Param("courseID") String courseID ,@Param("teachTime") String teachTime);
    String selectTeacherIDByTeacherName(@Param("teacherName") String teacherName);
    String selectCourseIDByCourseName(@Param("courseName") String courseName);
}
