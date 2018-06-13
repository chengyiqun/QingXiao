package QingXiao.mappers;

import java.util.HashMap;

/**
 * Created by xpb on 2018/6/10.
 */
public interface PrivilegeMapper {
    void insertPrivilege(HashMap privilegeMap);
    String queryPrivilegeNameByPrivilegeID(String privilegeID);
}
