package com.skynet.spms.client.vo;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 运代商vo 用于封装从数据库查询出来的运代商相关信息
 * 
 * @author fl
 * 
 */
public class CarrierVO implements IsSerializable {
	/**
	 * 联系人
	 */
	String linkMan;
	/**
	 * 联系方式
	 */
	String linkType;
	/**
	 * gta协议
	 */
	LinkedHashMap<String, String>  gtaInfo;

	
	
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public LinkedHashMap<String, String> getGtaInfo() {
		return gtaInfo;
	}

	public void setGtaInfo(LinkedHashMap<String, String> gtaInfo) {
		this.gtaInfo = gtaInfo;
	}

}
