package cn.com.oceansoft.flex4.server.common.dao.impl;

import cn.com.oceansoft.flex4.server.common.dao.UserDao;
import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Dao实现类 - 用户
 */

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {


    public boolean isExistByUsername(String username) {
        String hql = "from User user where lower(user.username) = lower(?)";
        User user = (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String hql = "from User user where lower(user.username) = lower(?)";
        return (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
    }

    public List<Role> getRoleListByUser(String userId) {
        User user = get(userId);
        Set<Role> roleSet = user.getRoleSet();
        List<Role> roleList = new ArrayList<Role>();
        for (Role role : roleSet) {
            roleList.add(role);
        }
        return roleList;
    }
}
