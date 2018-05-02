package QingXiao.service;

import QingXiao.entity.UserInform;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xpb on 2017/9/22.
 */
public interface UserService {

    List<UserInform> findAll();

    void updateAvatar(String avatar, String phoneNum, String avatarRealName, String avatarStoreName);

    HashMap downloadAvatar(String userName);

    int deleteUser(String userName);
    int updatePassword(String userName, String password);
    HashMap login(String phoneNum, String passWord);
    int register(HashMap infoMap);

    int verifyAccessToken(HashMap map);

    int verifyAccessToken(String phoneNum, String accessToken);

    //void updateAvatar(String avatar, String userID);

}
