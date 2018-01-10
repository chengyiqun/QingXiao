package QingXiao.service;

import QingXiao.entity.UserInform;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xpb on 2017/9/22.
 */
public interface UserService {

    List<UserInform> findAll();

    void updateAvatar(String avatar, String userID, String avatarRealName, String avatarStoreName);

    HashMap downloadAvatar(String userName);

    int deleteUser(String userName);
    int updatePassword(String userName, String password);
    HashMap login(String userName, String passWord);
    int register(HashMap infoMap);

    int verifyAccessToken(HashMap map);

    int verifyAccessToken(String userName, String accessToken);

    //void updateAvatar(String avatar, String userID);

}
