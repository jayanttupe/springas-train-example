package cn.com.oceansoft.flex4.server.common.service.impl;

import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * spring security - 用户验证
 */

@Service
@Transactional
public class MyDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = null;
        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User [" + username + "] not found");
        }
        if (user == null) {
            throw new UsernameNotFoundException("User [" + username + "] not found!");
        }
        user.setAuthorities(getGrantedAuthorities(user));
        return user;
    }

    // 获得管理角色数组
    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoleSet()) {
            grantedAuthorities.add(new GrantedAuthorityImpl(role.getValue()));
        }
        return grantedAuthorities;
    }

}
