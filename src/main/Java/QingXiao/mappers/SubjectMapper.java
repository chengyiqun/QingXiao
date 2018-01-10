package QingXiao.mappers;

import QingXiao.entity.CourseResource;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/1/2.
 */
public interface SubjectMapper {
    void insertSubject(HashMap hashMap);
    void updateTopics(String subjectID);

    List<Map> selectResourceCommentByResourceID(@Param("courseResourceID") String courseResourceID, @Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    List<Map> selectResourceCommentByUserID(@Param("userID") String userID, @Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    //List<CourseResource> selectResourceComment(String resourceStoreName);
    CourseResource selectResourceComment(String resourceStoreName);
    List<CourseResource> selectResourceComment1(String resourceStoreName);
    List<Map> selectResourceCommentList(@Param("resourceStoreName")String resourceStoreName,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);

    List<Map> selectResourceCommentReplyList(@Param("rootComment")String rootComment,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
    String  selectRootComment(String objectID);
}
