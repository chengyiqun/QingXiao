package QingXiao.mappers;

import QingXiao.entity.UserInform;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * Created by xpb on 2018/6/10.
 */
public interface RoleMapper {
   void insertRole(HashMap roleMap);
   String queryRoleNameByRoleID(@Param("roleID") String roleID);
   String queryRoleIDByRoleName(@Param("roleName") String roleName);

   UserInform queryRoleNameSetByPhoneNum(@Param("phoneNum") String phoneNum);
}
