package cn.com.oceansoft.flex4.server.common.service;


import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.entity.User;

import java.util.List;

/**
 * Service接口 - 用户接口类
 */

public interface UserService extends BaseService<User, String> {

    public User getUserByUsername(String username);

    public String changePassword(String oldPassword, String newPassword);

    public void logoutPrincipal();

    public User getUserSessionInfo();

    public List<Role> getRoleListByUser(String userId);

    public List<Role> getValidRoleListByUser(String userId);
}
