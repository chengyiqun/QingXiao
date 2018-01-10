package QingXiao.service;

import QingXiao.mappers.CourseMapper;
import QingXiao.mappers.TeachCommentMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2017/10/25.
 */
@Service("teachCommentService")
@Transactional
public class TeachCommentServiceImpl implements TeachCommentService {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    TeachCommentMapper teachCommentMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    private int result =0;
    @Override
    public int insertTeachComment(String jsonString, String userName) {
        HashMap commentMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String teachCommentID = IdFactory.getUUID();
        commentMap.put("teachCommentID",teachCommentID);
        commentMap.put("commentTime", TimeFactory.getCurrentTime());
        String userID=userInformMapper.queryUserIDByUserName(userName);
        String teachID=(String) commentMap.get("teachID");

        commentMap.put("userID",userID);

        int commentType=(Integer) commentMap.get("commentType");
        String  rootComment ="";
        String objectID="";
        if(commentType ==0){
            System.out.println("此条是评论");
            teachID=(String) commentMap.get("teachID");
        }else if(commentType ==1){  //回复
            System.out.println("此条是回复");
            objectID = (String)commentMap.get("objectID");
            System.out.println("objectID:"+objectID);
            //rootComment应该是通过objectID获得，
            rootComment =teachCommentMapper.selectRootComment(objectID);
            if(rootComment==null){
                rootComment =(String) commentMap.get("objectID");
                System.out.println("此条是对评论的回复");
            }else{
                System.out.println("此条是对回复的回复");
            }

            commentMap.put("rootComment",rootComment);
            teachCommentMapper.updateTeachCommentReplys(rootComment);
            //更新评论的回复数
        }

        if(userID!=null) {
            System.out.println("userID不为空");

            if (teachID == null) {
                System.out.println("teachID为空");
                return   result=3222;
            }
            else{
                //教学评论信息已上传导入到数据库
                teachCommentMapper.insertTeachCommentAllMap(commentMap);
                teachCommentMapper.updateTeachComments(teachID);
                 result=3221;
            }

        }else{
            // 用户不存在
            result=3003;
        }
        return result;
    }

    @Override
    public List<Map> getTeachCommentList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String teachID =(String)map.get("teachID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);
        String courseResourceID="";
        if(!teachID.equals("")){
            //courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        }
        List<Map> list= new LinkedList<>();
        //if(courseResourceID!=null) {
        System.out.println("课程资源ID不为空");
        //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
        list = teachCommentMapper.selectTeachCommentList(teachID,sinceTime,nowTime);
        System.out.println("课程评论信息List:" + list);
        //  }
        return list;
    }

    @Override
    public List<Map> getTeachCommentReplyList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String teachCommentID =(String)map.get("teachCommentID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        List<Map> list= new LinkedList<>();
        if(teachCommentID!=null) {
            System.out.println("课程资源ID不为空");
            //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
            list = teachCommentMapper.selectTeachCommentReplyList(teachCommentID,sinceTime,nowTime);
            System.out.println("课程评论信息List:" + list);
        }
        return list;
    }
}
