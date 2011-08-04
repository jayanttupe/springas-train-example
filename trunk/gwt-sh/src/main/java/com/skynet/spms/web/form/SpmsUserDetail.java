package com.skynet.spms.web.form;

import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userRole.Role;
import com.skynet.spms.persistence.entity.spmsdd.EnableStatus;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SpmsUserDetail implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -1544353687316519367L;

    private List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

    private String userName;

    private boolean isEnable = true;

    private String password;

    private boolean isLock = true;

    private Date accountValidPeriod = DateUtils.addYears(new Date(), 10);

    private Date credentialValidPeriod = DateUtils.addYears(new Date(), 10);

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public SpmsUserDetail(User user) {

        List<Role> roles = user.getM_Role();
        if (roles != null) {
            for (Role role : roles) {
                authList.add(new GrantedAuthorityImpl(role.getRoleName()));
            }
        }

        userName = user.getUsername();
        password = user.getPassword();

        isEnable = (EnableStatus.enable == user.getM_EnableStatus());

    }

    public SpmsUserDetail(String user) {

        if (user.startsWith("user_")) {
            authList.add(new GrantedAuthorityImpl("user"));
        } else if (user.startsWith("admin_")) {
            authList.add(new GrantedAuthorityImpl("admin"));
            authList.add(new GrantedAuthorityImpl("user"));
        } else {
            authList.add(new GrantedAuthorityImpl("guest"));
        }
        int idx = user.indexOf("_");

        String plainPwd = user.substring(idx + 1);

        userName = user;
        password = plainPwd;
    }

    public Collection<GrantedAuthority> getAuthorities() {

        return authList;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
    }

    public boolean isAccountNonExpired() {
        return accountValidPeriod.getTime() > System.currentTimeMillis();
    }

    public boolean isAccountNonLocked() {
        return isLock;
    }

    public boolean isCredentialsNonExpired() {
        return credentialValidPeriod.getTime() > System.currentTimeMillis();
    }

    public boolean isEnabled() {
        return isEnable;
    }


}
