package QingXiao.entity;

/**
 * Created by xpb on 2018/6/10.
 */
public class Privilege {
    String privilegeID;
    String privilegeName;
    String privilegeFatherID;
    String privileges;
    String createTime;
    String status;

    public String getPrivilegeID() {
        return privilegeID;
    }

    public void setPrivilegeID(String privilegeID) {
        this.privilegeID = privilegeID;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeFatherID() {
        return privilegeFatherID;
    }

    public void setPrivilegeFatherID(String privilegeFatherID) {
        this.privilegeFatherID = privilegeFatherID;
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
}
