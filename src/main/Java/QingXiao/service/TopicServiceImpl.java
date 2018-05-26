package QingXiao.service;

import QingXiao.entity.Reply;
import QingXiao.entity.Topic;
import QingXiao.mappers.SubjectMapper;
import QingXiao.mappers.TopicMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xpb on 2018/1/2.
 */
@Service("topicService")
@Transactional
public class TopicServiceImpl implements  TopicService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    SubjectMapper subjectMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    TopicMapper topicMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    private int result = 0;

    @Override
    public int insertTopic(String jsonString, String userName, List<HashMap<String, Object>> imageMapList) {
        HashMap topicMap = (HashMap) JSON.parseObject(jsonString, Map.class);
        System.out.println("topicMap:"+topicMap);
        JSONObject jsonObject= JSONObject.parseObject(jsonString);
        //JSONObject lableObject = (JSONObject)jsonObject.get("lableList");
        JSONArray lableArray = (JSONArray) jsonObject.get("lableList");
        // String lable1=(String)lableObject.get("lable") ;
        //System.out.println("lable1:"+lable1);
        //System.out.println("lableString:"+lableObject);
        System.out.println("lableArray:"+lableArray);

        String topicID =IdFactory.getUUID();
        String topicTime =TimeFactory.getCurrentTime();
        topicMap.put("topicID",topicID);
        topicMap.put("topicTime",topicTime);
        //HashMap lableMap = (HashMap)jsonObject.get("lableList");//不改变

        HashMap imageMap = (HashMap)topicMap.get("imageList");
        //HashMap lableMap = (HashMap)topicMap.get("lableList");



        String userID = userInformMapper.queryUserIDByUserName(userName);
        topicMap.put("userID",userID);
        if (userID != null) {
            System.out.println("userID不为空");
            //(1)插入动态的基本信息（2）插入动态的图片（3）插入动态的标签

            topicMapper.insertTopicInfo(topicMap);
            //topicMapper.insertTopicImage(imageMap);
            //topicMapper.insertLable(lableMap);
            for (int i = 0; i < lableArray.size(); i++) {
                HashMap<String, Object> lableMap = new HashMap<String, Object>();
                String lable = (String)lableArray.getJSONObject(i).get("lable");
                String lableID =IdFactory.getUUID();
                String lableTime =TimeFactory.getCurrentTime();
                lableMap.put("lableID",lableID);
                lableMap.put("userID",userID);
                lableMap.put("lableTime",lableTime);
                lableMap.put("objectID",topicID);
                lableMap.put("lable", lable);
                System.out.println("lable:"+lable);
                topicMapper.insertLable(lableMap);
            }
            for(int i=0;i<imageMapList.size();i++)
            {
                String imageID =IdFactory.getUUID();
                imageMapList.get(i).put("imageID",imageID);
                imageMapList.get(i).put("topicID",topicID);
                topicMapper.insertTopicImage(imageMapList.get(i));
            }
            System.out.println("插入动态成功！");
            return result = 3411;
        }
        return result = 3003;
    }

    @Override
    public int insertTopicComment(String jsonString, String userName) {
        HashMap commentMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String commentID = IdFactory.getUUID();
        commentMap.put("commentID",commentID);
        commentMap.put("commentTime", TimeFactory.getCurrentTime());
        String userID=userInformMapper.queryUserIDByUserName(userName);
        String topicID=(String) commentMap.get("topicID");

        commentMap.put("userID",userID);

        int commentType=(Integer) commentMap.get("commentType");
        String  rootComment ="";
        String objectID="";
        if(commentType ==0){
            System.out.println("此条是评论");
            topicID=(String) commentMap.get("topicID");
        }else if(commentType ==1){  //回复
            System.out.println("此条是回复");
            objectID = (String)commentMap.get("objectID");
            System.out.println("objectID:"+objectID);
            //rootComment应该是通过objectID获得，
            rootComment =topicMapper.selectRootComment(objectID);
            if(rootComment==null){
                rootComment =(String) commentMap.get("objectID");
                System.out.println("此条是对评论的回复");
            }else{
                System.out.println("此条是对回复的回复");
            }

            commentMap.put("rootComment",rootComment);
            topicMapper.updateTopicCommentReplys(rootComment);
            //更新评论的回复数
        }

        if(userID!=null) {
            System.out.println("userID不为空");

            if (topicID == null) {
                System.out.println("topicID为空");
                return   result=3222;
            }
            else{
                //教学评论信息已上传导入到数据库
                topicMapper.insertTopicComment(commentMap);
                //topicMapper.updateTopicComments(teachID);
                result=3431;
            }

        }else{
            // 用户不存在
            result=3003;
        }
        return result;
    }

    @Override
    public List<Map> getTopicMapList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String subjectID =(String)map.get("subjectID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);
        String courseResourceID="";
        if(!subjectID.equals("")){
            //courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        }
        List<Map> list= new LinkedList<>();
        System.out.println("主题ID不为空");
        list = topicMapper.selectTopicMapList(subjectID,sinceTime,nowTime);
        System.out.println("动态信息MapList:" + list);
        return list;
    }

    @Override
    public List<Topic> getTopicList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String subjectID =(String)map.get("subjectID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);
        String courseResourceID="";
        if(!subjectID.equals("")){
            //courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        }

        List<Topic> topicList= new LinkedList<>();

        System.out.println("主题ID不为空");
        //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
        //list = topicMapper.selectTopicList(subjectID,sinceTime,nowTime);
        topicList = topicMapper.selectTopicList(subjectID,sinceTime,nowTime);
        System.out.println("动态信息List:" + topicList);
        return topicList;
    }

    @Override
    public List<Map> getTopicCommentList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String topicID =(String)map.get("topicID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        // List<Map> list = courseResourceMapper.selectCourseResourceByCourseName(courseName);
        String courseResourceID="";
        if(!topicID.equals("")){
            //courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        }
        List<Map> list= new LinkedList<>();
        //if(courseResourceID!=null) {
        System.out.println("动态ID不为空");
        //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
        list = topicMapper.selectTopicCommentList(topicID,sinceTime,nowTime);
        System.out.println("动态评论信息List:" + list);
        //  }
        return list;
    }



    @Override
    public List<Reply> getTopicCommentReplyList(String jsonString) {
        HashMap map = (HashMap) JSON.parseObject(jsonString,Map.class);
        String commentID =(String)map.get("commentID");
        String uploadTime =(String)map.get("uploadTime");
        String sinceTime =(String)map.get("sinceTime");
        String nowTime =(String)map.get("nowTime");
        List<Reply> list= new LinkedList<>();
        if(commentID!=null) {
            System.out.println("动态评论ID不为空");
            //list = courseResourceMapper.selectCourseResourceByCourseID(courseID);
            list = topicMapper.selectReplyList(commentID,sinceTime,nowTime);
            System.out.println("动态评论回复信息List:" + list);
        }
        return list;
    }

}
