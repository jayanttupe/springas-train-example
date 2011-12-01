package cn.com.oceansoft.flex4.server.common.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 实体类 - 用户
 */

@Entity
@Table(name = "tbl_comm_user")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;// 用户名
    private String password;// 密码
    private String email;// E-mail
    private String displayName;// 姓名
    private String projId;  //项目
    private String department;// 部门
    private Boolean isAccountEnabled;// 账号是否启用
    private Boolean isAccountLocked;// 账号是否锁定
    private Boolean isAccountExpired;// 账号是否过期
    private Boolean isCredentialsExpired;// 凭证是否过期
    private Integer loginFailureCount;// 连续登录失败的次数
    private Date lockedDate;// 账号锁定日期
    private Date loginDate;// 最后登录日期
    private String loginIp;// 最后登录IP

    private Set<Role> roleSet;// 角色集合
    private Set<GrantedAuthority> authorities;// 角色信息

    // Constructors
    public User() {
    }

    @Column(updatable = false, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(nullable = false)
    public Boolean getIsAccountEnabled() {
        return isAccountEnabled;
    }

    public void setIsAccountEnabled(Boolean isAccountEnabled) {
        this.isAccountEnabled = isAccountEnabled;
    }

    @Column(nullable = false)
    public Boolean getIsAccountLocked() {
        return isAccountLocked;
    }

    public void setIsAccountLocked(Boolean isAccountLocked) {
        this.isAccountLocked = isAccountLocked;
    }

    @Column(nullable = false)
    public Boolean getIsAccountExpired() {
        return isAccountExpired;
    }

    public void setIsAccountExpired(Boolean isAccountExpired) {
        this.isAccountExpired = isAccountExpired;
    }

    @Column(nullable = false)
    public Boolean getIsCredentialsExpired() {
        return isCredentialsExpired;
    }

    public void setIsCredentialsExpired(Boolean isCredentialsExpired) {
        this.isCredentialsExpired = isCredentialsExpired;
    }

    @Column(nullable = false)
    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @OrderBy("displayName asc")
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Transient
    public boolean isEnabled() {
        return this.isAccountEnabled;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return !this.isAccountLocked;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return !this.isAccountExpired;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return !this.isCredentialsExpired;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}