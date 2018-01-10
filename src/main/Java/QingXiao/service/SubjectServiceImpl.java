package QingXiao.service;

import QingXiao.mappers.CourseMapper;
import QingXiao.mappers.CourseResourceMapper;
import QingXiao.mappers.SubjectMapper;
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
 * Created by xpb on 2018/1/2.
 */
@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements  SubjectService {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    SubjectMapper subjectMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    private int result = 0;

    /*
    * 插入主题，目前没有完成插入主题的图片
    * */
    @Override
    public int insertSubject(String jsonString, String userName) {
        HashMap subjectMap = (HashMap) JSON.parseObject(jsonString, Map.class);
        String subjectID = IdFactory.getUUID();
        String createTime = TimeFactory.getCurrentTime();
        String userID = userInformMapper.queryUserIDByUserName(userName);
        subjectMap.put("subjectID",subjectID);
        subjectMap.put("creatorID",userID);
        subjectMap.put("createTime",createTime);
        if (userID != null) {
            System.out.println("userID不为空");
            subjectMapper.insertSubject(subjectMap);
            System.out.println("插入主题成功！");
            return result = 3401;
        }
        return result = 3003;
    }

}

