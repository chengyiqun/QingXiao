package QingXiao.service;

import QingXiao.entity.Reply;
import QingXiao.entity.Topic;

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

    List<Reply> getTopicCommentReplyList(String jsonString);
}
