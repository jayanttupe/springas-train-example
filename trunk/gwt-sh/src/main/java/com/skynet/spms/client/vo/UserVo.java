package com.skynet.spms.client.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserVo implements IsSerializable  {

	private String id;
	private String userName;
	private String realName;
	private String email;
	private Date lastVisit;
	private EnterpriseVO enterpriseVo;
	private DepartmentVo departmentVo;
	private DutyVo dutyVo;
	private UserInformationVo userInfoVo;
	
	private CustomerVo customerVo;
	
	
	
	
	
	
	
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	public EnterpriseVO getEnterpriseVo() {
		return enterpriseVo;
	}
	public void setEnterpriseVo(EnterpriseVO enterpriseVo) {
		this.enterpriseVo = enterpriseVo;
	}
	public DepartmentVo getDepartmentVo() {
		return departmentVo;
	}
	public void setDepartmentVo(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}
	public DutyVo getDutyVo() {
		return dutyVo;
	}
	public void setDutyVo(DutyVo dutyVo) {
		this.dutyVo = dutyVo;
	}
	public UserInformationVo getUserInfoVo() {
		return userInfoVo;
	}
	public void setUserInfoVo(UserInformationVo userInfoVo) {
		this.userInfoVo = userInfoVo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	
	
}
