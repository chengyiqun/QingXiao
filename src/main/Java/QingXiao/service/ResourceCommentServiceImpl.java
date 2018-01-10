package QingXiao.service;

import QingXiao.entity.Reply;
import QingXiao.mappers.*;
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
 * Created by xpb on 2017/10/28.
 */
@Service("resourceCommentService")
@Transactional
public class ResourceCommentServiceImpl implements ResourceCommentService {

    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    CourseResourceMapper courseResourceMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    ResourceCommentMapper resourceCommentMapper;
    private int result =0;
    @Override
    public int insertResourceComment(String jsonString, String userName) {
        HashMap resourceCommentMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String resourceCommentID = IdFactory.getUUID();

        resourceCommentMap.put("resourceCommentID",resourceCommentID);
        resourceCommentMap.put("commentTime", TimeFactory.getCurrentTime());
        String userID=userInformMapper.queryUserIDByUserName(userName);
        String resourceStoreName="";
        String courseResourceID="";

        resourceCommentMap.put("userID",userID);
        int commentType=(Integer) resourceCommentMap.get("commentType");
        String  rootComment ="";
        String objectID="";
        if(commentType ==0){
            System.out.println("此条是评论");
            //resourceStoreName=(String) resourceCommentMap.get("resourceStoreName");
           // courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
           // resourceCommentMap.put("courseResourceID",courseResourceID);
           // resourceCommentMap.remove("resourceStoreName");
        }else if(commentType ==1){  //回复
            System.out.println("此条是回复");
            objectID = (String) resourceCommentMap.get("objectID");
            System.out.println("objectID:"+objectID);

            //rootComment应该是通过objectID获得，

            rootComment =resourceCommentMapper.selectRootComment(objectID);
            if(rootComment==null){
                rootComment =(String) resourceCommentMap.get("objectID");
                System.out.println("此条是对评论的回复");
            }else{
                System.out.println("此条是对回复的回复");
            }

            resourceCommentMap.put("rootComment",rootComment);
            resourceCommentMapper.updateResourceCommentReplys(rootComment);
            //更新评论的回复数
        }
        if(userID!=null) {
            System.out.println("userID不为空");
            if (courseResourceID == null) {
                System.out.println("courseResourceID为空");
                return  result=3352;
            } else {
                resourceCommentMapper.insertResourceComment(resourceCommentMap);
                courseResourceMapper.updateResourceComments(resourceStoreName);  //资源评论数加一
                System.out.println("插入评论成功！");
                result=3351;
            }

        }else{
            // 用户不存在
            System.out.println("userID为空");
            result=3003;
        }
        return result;
    }

    @Override
    public List<Reply> getResourceCommentList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        //String resourceStoreName =(String)map.get("resourceStoreName");
        String courseResourceID =(String)map.get("courseResourceID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);

        if(!courseResourceID.equals("")){
            //courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        }
        List<Reply> list= new LinkedList<>();
        //if(courseResourceID!=null) {
            System.out.println("课程资源ID不为空"+courseResourceID);
            //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
            list = resourceCommentMapper.selectResourceCommentList(courseResourceID,sinceTime,nowTime);
            System.out.println("课程评论信息List:" + list);
      //  }
        return list;
    }

    @Override
    public List<Reply> getResourceCommentReplyList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String commentID =(String)map.get("resourceCommentID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        List<Reply> list= new LinkedList<>();
        if(commentID!=null) {
            System.out.println("课程资源ID不为空");
            //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
            list = resourceCommentMapper.selectResourceCommentReply(commentID,sinceTime,nowTime);
            System.out.println("课程评论信息List:" + list);
        }
        return list;
    }
}
