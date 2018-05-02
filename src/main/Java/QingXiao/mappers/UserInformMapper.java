package QingXiao.mappers;

import QingXiao.entity.UserInform;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xpb on 2017/10/21.
 */
public interface UserInformMapper {

 UserInform getUserByName(String userName);
 UserInform getUserByPhoneNum(String phoneNum);
 String queryUserIDByUserName(@Param("userName")String userName);
 String queryUserIDByPhoneNum(@Param("phoneNum")String phoneNum);
 String  queryPasswordByUserID(String userID);
 //void  insertUser(@Param("ID") int id, @Param("USERNAME") String username,@Param("PASSWORD") String password);
 List<UserInform> findAll();
 void  insertUser(@Param("userID") String id, @Param("userName") String username, @Param("password") String password);
 void  insertUserAuto(@Param("userName") String username, @Param("password") String password);

 void deleteUser(String id);
 void updatePassword(@Param("userID") String id, @Param("password") String passWord);
 /*
    mybatis 查询语句似乎不支持重载
    */
 void  insertUserAllMap(HashMap infoMap);
 void  insertUserAll(@Param("userInform") UserInform userInform);

 void updateAccessToken(@Param("access_token")String accessToken,@Param("userID") String userID);
 void updateExpiresIn(@Param("expires_in")Date expiresIn,@Param("userID") String userID);

 void updateAccessToken(HashMap tokenMap);
 void  updateAvatar(@Param("avatar")String avatar,@Param("phoneNum") String phoneNum);
 void updateAvatarByUserName(@Param("avatar")String avatar,@Param("userName") String userName);
 String selectAccessTokenByUserID(@Param("userID") String userID);

 void updateAvatarName(@Param("avatarStoreName")String avatarStoreName ,@Param("avatarRealName")String avatarRealName,@Param("userName") String userName);
 HashMap selectAvatarName (@Param("userName") String userName);

}
