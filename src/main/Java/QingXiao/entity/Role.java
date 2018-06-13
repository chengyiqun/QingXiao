package QingXiao.entity;

import java.util.Set;

/**
 * Created by xpb on 2018/6/9.
 */
public class Role {
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
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Privilege> getPrivilegeSet() {
        return privilegeSet;
    }

    public void setPrivilegeSet(Set<Privilege> privilegeSet) {
        this.privilegeSet = privilegeSet;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID='" + roleID + '\'' +
                ", roleName='" + roleName + '\'' +
                ", privileges='" + privileges + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                ", privilegeSet=" + privilegeSet +
                '}';
    }
}
