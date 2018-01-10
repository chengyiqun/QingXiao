package QingXiao.service;

import QingXiao.mappers.CourseResourceMapper;
import QingXiao.mappers.ResourceDownloadInformMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xpb on 2017/10/27.
 */

@Service("resourceDownloadInformService")
@Transactional
public class ResourceDownloadInformServiceImpl implements ResourceDownloadInformService {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserInformMapper userInformMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    CourseResourceMapper courseResourceMapper;
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    ResourceDownloadInformMapper resourceDownloadInformMapper;
    private int result=0;
    @Override
    public int insertResourceDownloadInform(String resourceStoreName, String userName) {
        String userID=userInformMapper.queryUserIDByUserName(userName);
        String courseResourceID=courseResourceMapper.selectCourseResourceIDByResourceStoreName(resourceStoreName);
        HashMap<String, Object> downloadMap = new HashMap<>();
        downloadMap.put("userID",userID);
        downloadMap.put("courseResourceID",courseResourceID);
        downloadMap.put("downloadTime", TimeFactory.getCurrentTime());
        downloadMap.put("resourceDownloadID", IdFactory.getUUID());
        resourceDownloadInformMapper.insertResourceDownloadInform(downloadMap);
        return result=2000;
    }
}
