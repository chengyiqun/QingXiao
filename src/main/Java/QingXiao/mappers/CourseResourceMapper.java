package QingXiao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
public interface CourseResourceMapper {
    void insertCourseResourceAllMap(HashMap map);
    List<Map>  selectCourseResourceByCourseName(String courseName);
    List<Map>  selectCourseResourceByCourseID(String courseID);
    List<Map>  selectCourseResource(@Param("courseID") String courseID,@Param("sinceTime") String sinceTime,@Param("nowTime") String nowTime);
    String selectCourseResourceIDByResourceStoreName(String resourceStoreName);
    void updateResourceDownloads(String resourceStoreName);
    void updateResourceComments(String resourceStoreName);
}
