package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EnterpriseVO  implements IsSerializable  {
	private String abbreviation;
	private String address_en;
	private String address_zh;
	private String email;
	private String enterpriseHomepageURL;
	private String enterpriseName_en;
	private String enterpriseName_zh;
	private String fax;
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getAddress_en() {
		return address_en;
	}
	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}
	public String getAddress_zh() {
		return address_zh;
	}
	public void setAddress_zh(String address_zh) {
		this.address_zh = address_zh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnterpriseHomepageURL() {
		return enterpriseHomepageURL;
	}
	public void setEnterpriseHomepageURL(String enterpriseHomepageURL) {
		this.enterpriseHomepageURL = enterpriseHomepageURL;
	}
	public String getEnterpriseName_en() {
		return enterpriseName_en;
	}
	public void setEnterpriseName_en(String enterpriseName_en) {
		this.enterpriseName_en = enterpriseName_en;
	}
	public String getEnterpriseName_zh() {
		return enterpriseName_zh;
	}
	public void setEnterpriseName_zh(String enterpriseName_zh) {
		this.enterpriseName_zh = enterpriseName_zh;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
}
