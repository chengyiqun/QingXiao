package QingXiao.entity;

import java.util.Set;

/**
 * Created by xpb on 2018/6/12.
 */
public class RolePrivilege {
    String  roleID;
    String  roleName;
    String privileges;
    String createTime;
    String status;
    Set<Privilege> privilegeSet;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        roleName = roleName;
    }

    public Set<Privilege> getPrivilegeSet() {
        return privilegeSet;
    }

    public void setPrivilegeSet(Set<Privilege> privilegeSet) {
        this.privilegeSet = privilegeSet;
    }


}
