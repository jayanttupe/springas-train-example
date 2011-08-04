package com.skynet.spms.client.entity;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserInfo implements IsSerializable{
	
	private String realName;
	
	private String userName;
	
	private String duty;
	
	private String department;
	
	private Set<String> roleSet=new HashSet<String>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void addRole(String authority) {
		roleSet.add(authority);
	}

	public Set<String> getRoleSet() {
		return roleSet;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
