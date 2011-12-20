package cn.com.oceansoft.flex4.server.common.service.impl;

import cn.com.oceansoft.flex4.server.common.dao.RoleDao;
import cn.com.oceansoft.flex4.server.common.dao.UserDao;
import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service实现类 - 用户
 */

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Resource
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }

    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);


    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public String changePassword(String oldPassword, String newPassword) {
        String username = "unknown";
        String flg = "OK";
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals("unknown")) {
            try {
                User user = userDao.getUserByUsername(username);
                if (user != null && user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    flg = "旧密码不正确";
                }
            } catch (Exception e) {
                flg = "密码修改失败，请与管理员联系!";
            }
        }
        return flg;
    }

    public void logoutPrincipal() {
        SecurityContextHolder.clearContext();
    }

    public User getUserSessionInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //获得 已分配 的角色列表
    public List<Role> getRoleListByUser(String userId) {
        return userDao.getRoleListByUser(userId);
    }

    //获得 可分配 的角色列表
    public List<Role> getValidRoleListByUser(String userId) {
        List<Role> resultList = roleDao.getAll();
        List<Role> roleList = userDao.getRoleListByUser(userId);
        for (Role role : roleList) {
            resultList.remove(role);
        }
        return resultList;
    }

}
