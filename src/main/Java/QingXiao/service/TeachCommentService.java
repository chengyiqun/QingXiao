package QingXiao.service;

import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
public interface TeachCommentService {
    //int insertTeachComment(String jsonString);
    int insertTeachComment(String jsonString, String userName);
    List<Map> getTeachCommentList(String jsonString);

    List<Map> getTeachCommentReplyList(String jsonString);
}
