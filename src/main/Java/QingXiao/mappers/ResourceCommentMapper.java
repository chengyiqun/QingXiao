package QingXiao.mappers;

import QingXiao.entity.CourseResource;
import QingXiao.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/28.
 */
public interface ResourceCommentMapper {
    void insertResourceComment(HashMap hashMap);
    List<Map> selectResourceCommentByResourceID(@Param("courseResourceID") String courseResourceID, @Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    List<Map> selectResourceCommentByUserID(@Param("userID") String userID, @Param("sinceTime") String sinceTime, @Param("nowTime") String nowTime);
    //List<CourseResource> selectResourceComment(String resourceStoreName);
    CourseResource selectResourceComment(String resourceStoreName);
    List<CourseResource> selectResourceComment1(String resourceStoreName);
    List<Map> selectResourceCommentMapList(@Param("resourceStoreName")String resourceStoreName,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
    void updateResourceCommentReplys(String resourceCommentID);
    List<Map> selectResourceCommentReplyList(@Param("rootComment")String rootComment,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
    String  selectRootComment(String objectID);

    List<Reply> selectResourceCommentList(@Param("courseResourceID")String courseResourceID, @Param("sinceTime")String sinceTime, @Param("nowTime") String nowTime);
    List<Reply> selectResourceCommentReply(@Param("commentID")String resourceCommentID, @Param("sinceTime")String sinceTime, @Param("nowTime") String nowTime);

}
