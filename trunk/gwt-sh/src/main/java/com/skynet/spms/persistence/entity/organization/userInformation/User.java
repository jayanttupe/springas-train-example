package com.skynet.spms.persistence.entity.organization.userInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.client.entity.UserInfo;
import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.Duty;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;
import com.skynet.spms.persistence.entity.organization.userRole.Role;
import com.skynet.spms.persistence.entity.spmsdd.EnableStatus;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 10-三月-2011 11:11:12
 */
@ViewFormAnno(value = "id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_USER")
public class User extends BaseEntity {

	private String username;
	private String realname;
	private String password;
	private String email;
	private Date lastVisit;
	/**
	 * 用于累计用户在系统登录的次数
	 */
	private Integer loginCount;
	/**
	 * 累积用户登录失败的次数
	 */
	private Integer failedLoginCount;
	/**
	 * 记录遗失密码后，累计请求的计数
	 */
	private Integer lostPasswordRequestCount;
	/**
	 * 用户在系统的等级权限
	 */
	private String accessLevel;
	/**
	 * 用于记录用户的客户端cookie的字符串信息
	 */
	private String cookieString;
	private UserInformation m_UserInformation;
	private BaseEnterpriseInformation m_BaseEnterpriseInformation;
	private DepartmentInformation m_DepartmentInformation;
	private String roleNames;
	private List<Role> m_Role;
	private List<UserGroup> m_UserGroup;
//	private UserQuota m_UserQuota;
	private EnableStatus m_EnableStatus;
	private Duty m_Duty;
	private List<UserMessageRecord> m_UserMessageRecord = new ArrayList<UserMessageRecord>();
	private List<UserBussinessMonitoringRecord> m_UserBussinessMonitoringRecord = new ArrayList<UserBussinessMonitoringRecord>();
	private IDCard  m_IDCard ;
	private String jobNumber;
	
	@Column(name="JOB_NUMBER")
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name="ROLE_NAMES")
	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	@Column(name = "USER_NAME",unique=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "REAL_NAME")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VISIT")
	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	@Column(name = "LOGIN_COUNT")
	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	@Column(name = "FAILED_LOGIN_COUNT")
	public Integer getFailedLoginCount() {
		return failedLoginCount;
	}

	public void setFailedLoginCount(Integer failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}

	@Column(name = "LOST_PASSWORD_REQUEST_COUNT")
	public Integer getLostPasswordRequestCount() {
		return lostPasswordRequestCount;
	}

	public void setLostPasswordRequestCount(Integer lostPasswordRequestCount) {
		this.lostPasswordRequestCount = lostPasswordRequestCount;
	}

	@Column(name = "ACCESS_LEVEL")
	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Column(name = "COOKIE_STRING")
	public String getCookieString() {
		return cookieString;
	}

	public void setCookieString(String cookieString) {
		this.cookieString = cookieString;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERINFO_ID")
	public UserInformation getM_UserInformation() {
		return m_UserInformation;
	}

	public void setM_UserInformation(UserInformation m_UserInformation) {
		this.m_UserInformation = m_UserInformation;
	}
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	public DepartmentInformation getM_DepartmentInformation() {
		return m_DepartmentInformation;
	}
	public void setM_DepartmentInformation(
			DepartmentInformation m_DepartmentInformation) {
		this.m_DepartmentInformation = m_DepartmentInformation;
	}
	
	@ManyToOne
	@JoinColumn(name = "ENTERPRISE_ID")
	public BaseEnterpriseInformation getM_BaseEnterpriseInformation() {
		return m_BaseEnterpriseInformation;
	}
	public void setM_BaseEnterpriseInformation(
			BaseEnterpriseInformation m_BaseEnterpriseInformation) {
		this.m_BaseEnterpriseInformation = m_BaseEnterpriseInformation;
	}

	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
			name = "SPMS_USER_REL_ROLE", 
			joinColumns =@JoinColumn(name = "USER_ID",referencedColumnName="ID"), 
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID",referencedColumnName="ID")
	)	
	public List<Role> getM_Role() {
		return m_Role;
	}

	public void setM_Role(List<Role> m_Role) {
		this.m_Role = m_Role;
	}

	/*@ManyToMany(cascade = {CascadeType.ALL},
			 mappedBy = "m_User",
	         targetEntity =UserGroup.class
	)*/
	@ManyToMany(cascade =CascadeType.PERSIST)
	@JoinTable(
			name = "SPMS_USER_USERGROUP", 
			joinColumns = { @JoinColumn(name = "USER_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "USERGROUP_ID") }
	)
	public List<UserGroup> getM_UserGroup() {
		return m_UserGroup;
	}
	public void setM_UserGroup(List<UserGroup> m_UserGroup) {
		this.m_UserGroup = m_UserGroup;
	}

//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name = "USERQUOTA_ID")
//	public UserQuota getM_UserQuota() {
//		return m_UserQuota;
//	}
//
//	public void setM_UserQuota(UserQuota m_UserQuota) {
//		this.m_UserQuota = m_UserQuota;
//	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ENABLE_STATUS")
	public EnableStatus getM_EnableStatus() {
		return m_EnableStatus;
	}

	public void setM_EnableStatus(EnableStatus m_EnableStatus) {
		this.m_EnableStatus = m_EnableStatus;
	}

	@ManyToOne
	@JoinColumn(name = "DUTY_ID")
	public Duty getM_Duty() {
		return m_Duty;
	}

	public void setM_Duty(Duty m_Duty) {
		this.m_Duty = m_Duty;
	}
	
	@Reference(itemCls=UserMessageRecord.class)
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	public List<UserMessageRecord> getM_UserMessageRecord() {
		return m_UserMessageRecord;
	}

	public void setM_UserMessageRecord(List<UserMessageRecord> m_UserMessageRecord) {
		this.m_UserMessageRecord = m_UserMessageRecord;
	}
	@Reference(itemCls=UserBussinessMonitoringRecord.class)
	@OneToMany()
	@JoinColumn(name = "USER_ID")
	public List<UserBussinessMonitoringRecord> getM_UserBussinessMonitoringRecord() {
		return m_UserBussinessMonitoringRecord;
	}

	public void setM_UserBussinessMonitoringRecord(
			List<UserBussinessMonitoringRecord> m_UserBussinessMonitoringRecord) {
		this.m_UserBussinessMonitoringRecord = m_UserBussinessMonitoringRecord;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IDCARD_ID")
	public IDCard getM_IDCard() {
		return m_IDCard;
	}

	public void setM_IDCard(IDCard m_IDCard) {
		this.m_IDCard = m_IDCard;
	}
	
	
	//transient字段
	private String enterpriseId;
	private String departmentId;
	private String dutyId;
	@Transient
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Transient
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	@Transient
	public String getDutyId() {
		return dutyId;
	}
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	
	@Transient
	public UserInfo getUserInfo(){
		UserInfo user=new UserInfo();
		user.setUserName(username);
		user.setRealName(realname);
		if(m_Duty!=null){
			user.setDuty(m_Duty.getDutyDescription());
		}
		if(m_DepartmentInformation!=null){
			user.setDepartment(m_DepartmentInformation.getDepartment());
		}
		
		return user;
	}
}