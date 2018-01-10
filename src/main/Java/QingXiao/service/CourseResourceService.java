package QingXiao.service;

import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
public interface CourseResourceService {
    int insertCourseResource(String jsonString, String userName, String resourcePath, String fileName, String resourceType,String resourceStoreName);
    // int insertCourseResource(String jsonString,String userName);
    int downloadCourseResource(String resourceStoreName);
    List<Map>  getCourseResourceList(String jsonString);
}
