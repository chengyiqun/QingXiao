package QingXiao.mappers;

import java.util.HashMap;

/**
 * Created by xpb on 2017/10/27.
 */
public interface ResourceDownloadInformMapper {
  void   insertResourceDownloadInform(HashMap hashMap);
  int  getDownloadNumByResourceID(String courseResourceID);
  int  getDownloadNumByUserID(String userID);
}
