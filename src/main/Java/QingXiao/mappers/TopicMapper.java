package QingXiao.mappers;

import QingXiao.entity.CourseResource;
import QingXiao.entity.Reply;
import QingXiao.entity.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/1/2.
 */
public interface TopicMapper {

    void insertTopicInfo(HashMap hashMap);
    void insertTopicImage(HashMap hashMap);
    void insertTopicComment(HashMap hashMap);
    void insertLable(HashMap hashMap);
    List<Map> selectTopicMapList(@Param("subjectID")String subjectID,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
    List<Topic> selectTopicList(@Param("startItem")int startItem);
    List<Reply> selectReplyList(@Param("commentID")String commentID, @Param("sinceTime")String sinceTime, @Param("nowTime") String nowTime);

    List<Map> selectTopicCommentList(@Param("topicID")String topicID,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
    void updateTopicReplys(String topicID);
    void updateTopicCommentReplys(String topicID);
    String  selectRootComment(String objectID);
}
