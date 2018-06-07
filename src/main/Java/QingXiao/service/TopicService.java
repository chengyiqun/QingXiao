package QingXiao.service;

import QingXiao.entity.Topic;
import QingXiao.entity.TopicCommentReplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/1/2.
 */
public interface TopicService {
    //int insertTopic(String jsonString, String userName);
    int insertTopic(String jsonString, String userName, List<HashMap<String, Object>> imageMapList);

    int insertTopicComment(String jsonString, String userName);

    List<Map> getTopicMapList(String jsonString);

    List<Topic> getTopicList(int page);

    List<Map> getTopicCommentList(String jsonString);

    List<TopicCommentReplay> getTopicCommentReplyList(String jsonString);
}
