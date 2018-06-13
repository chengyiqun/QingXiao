package QingXiao.service;

/**
 * Created by xpb on 2018/6/12.
 */
public interface RolePrivilegeService {
    void insertRole(String jsonString);
    void insertUserRole(String jsonString);
    void insertPrivilege(String jsonString);
    void insertRolePrivilege(String jsonString);

}
