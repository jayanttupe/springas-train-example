package com.skynet.spms.client.vo;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

/***
 * 客戶
 * @author Tony FANG
 *
 */
public class CustomerContact implements IsSerializable{
	private String id;
	private String linkman;
	private String address;//地址
	private String telephone;//電話
	private String postCode;//郵編
	/** 关联的gta信息 */
	private LinkedHashMap<String, String> gtaInfo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLinkman() {
		return linkman;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public LinkedHashMap<String, String> getGtaInfo() {
		return gtaInfo;
	}
	public void setGtaInfo(LinkedHashMap<String, String> gtaInfo) {
		this.gtaInfo = gtaInfo;
	}
	
}
