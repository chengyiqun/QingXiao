package QingXiao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
public interface TeachCommentMapper {
   void insertTeachCommentAllMap(HashMap map);
   void updateTeachComments(String teachID);

   List<Map> selectTeachCommentList(@Param("teachID")String teachID, @Param("sinceTime")String sinceTime, @Param("nowTime") String nowTime);
   void updateTeachCommentReplys(String rootComment);
   List<Map> selectTeachCommentReplyList(@Param("rootComment")String rootComment,@Param("sinceTime")String sinceTime,@Param("nowTime") String nowTime);
   String  selectRootComment(String objectID);
}
