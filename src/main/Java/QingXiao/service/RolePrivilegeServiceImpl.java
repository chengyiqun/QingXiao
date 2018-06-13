package QingXiao.service;

import QingXiao.mappers.RoleMapper;
import QingXiao.mappers.RolePrivilegeMapper;
import QingXiao.mappers.UserInformMapper;
import QingXiao.mappers.UserRoleMapper;
import QingXiao.util.IdFactory;
import QingXiao.util.TimeFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xpb on 2018/6/12.
 */
@Service("rolePrivilegeService")
@Transactional
public class RolePrivilegeServiceImpl implements  RolePrivilegeService {

    @Resource
    protected RolePrivilegeMapper rolePrivilegeMapper;
    @Resource
    protected RoleMapper roleMapper;
    @Resource
    protected UserRoleMapper userRoleMapper;
    @Resource
    protected UserInformMapper userInformMapper;
    @Override
    public void insertRole(String jsonString) {
        System.out.println("插入角色：" );
        HashMap roleMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String roleName = (String) roleMap.get("roleName");
        System.out.println("插入角色名称："+roleName );
        String roleID= roleMapper.queryRoleIDByRoleName(roleName);
        if(roleID != null) {
            return;
        }else{
            roleID = IdFactory.getUUID();
            String createTime = TimeFactory.getCurrentTime();
            roleMap.put("roleID", roleID);
            roleMap.put("createTime", createTime);
            roleMapper.insertRole(roleMap);
        }
    }

    @Override
    public void insertUserRole(String jsonString) {
        System.out.println("插入用户角色：" );
        HashMap userRoleMap = (HashMap) JSON.parseObject(jsonString,Map.class);
        String phoneNum = (String) userRoleMap.get("phoneNum");
        String userID = userInformMapper.queryUserIDByPhoneNum(phoneNum);
        String roleName = (String) userRoleMap.get("roleName");
        System.out.println("插入用户角色名称："+roleName );
        String roleID= roleMapper.queryRoleIDByRoleName(roleName);
        String userRoleID=IdFactory.getUUID();
        String createTime= TimeFactory.getCurrentTime();
        userRoleMap.put("userRoleID",userRoleID);
        userRoleMap.put("roleID",roleID);
        userRoleMap.put("userID",userID);
        userRoleMap.put("createTime",createTime);
        userRoleMapper.insertUserRole(userRoleMap);
    }

    @Override
    public void insertPrivilege(String jsonString) {

    }

    @Override
    public void insertRolePrivilege(String jsonString) {

    }
}
