package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserInformationVo implements IsSerializable{

	private int sex;
	private String mobile;
	private String fax;
	private String telephone;
	private String contactAddresses;
	private String remark;
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getContactAddresses() {
		return contactAddresses;
	}
	public void setContactAddresses(String contactAddresses) {
		this.contactAddresses = contactAddresses;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
