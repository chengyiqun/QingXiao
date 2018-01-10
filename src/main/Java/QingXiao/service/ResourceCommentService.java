package QingXiao.service;

import QingXiao.entity.Reply;

import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/28.
 */
public interface ResourceCommentService {
    int insertResourceComment(String jsonString, String userName);
    List<Reply> getResourceCommentList(String jsonString);
    List<Reply> getResourceCommentReplyList(String jsonString);
}
