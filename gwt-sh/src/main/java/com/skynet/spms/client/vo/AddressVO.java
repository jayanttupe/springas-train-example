package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddressVO implements IsSerializable {
	private String uuid;
	/**
	 * 联系方式
	 */
	private String linkType;
	/**
	 * 联系人
	 */
	private String linkMan;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 地址信息
	 */
	private String address;
	/**
	 * 即codeid
	 */
	private String enterpriseId;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
