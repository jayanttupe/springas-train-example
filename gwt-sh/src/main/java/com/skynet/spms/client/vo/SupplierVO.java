package com.skynet.spms.client.vo;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 供应商 vo 用于封装从数据库查询出来的供应商信息
 * 
 * @author fl
 */
public class SupplierVO implements IsSerializable {
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
	LinkedHashMap<String, String> gtas;

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

	public LinkedHashMap<String, String> getGtas() {
		return gtas;
	}

	public void setGtas(LinkedHashMap<String, String> gtas) {
		this.gtas = gtas;
	}

}
