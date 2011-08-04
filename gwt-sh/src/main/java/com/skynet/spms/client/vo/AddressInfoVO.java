package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddressInfoVO implements IsSerializable {
	
	public AddressInfoVO() {
	}

	/**
	 * 发货地址
	 */
	private String shippingAddr;
	/**
	 * 发货联系人
	 */
	private String shippingMan;
	/**
	 * 发货联系方式
	 */
	private String shippingLinkType;
	/**
	 * 发货单位
	 */
	private String shippingUnit;

	/**
	 * 发货地址邮编
	 */
	private String shippingZipCode;
	/**
	 * 发票收取地址
	 */
	private String invocieAddr;
	/**
	 * 发票收取人
	 */
	private String invocieMan;
	/**
	 * 发票收取联系方式
	 */
	private String invocieLinkType;

	/**
	 * 发票收取单位
	 * */
	private String invocieUnit;

	/**
	 * 发票收取地址的邮编
	 */
	private String invocieZipCode;
	/**
	 * 收货地址
	 */
	private String consigneeAddr;
	/**
	 * 收货联系人
	 */
	private String consigneeMan;
	/**
	 * 收货联系方式
	 */
	private String consigneeLinkType;

	/**
	 * 收货单位
	 * */
	private String consigneeUnit;
	/**
	 * 收货地址邮编
	 */
	private String consigneeZipCode;

	/**
	 * 关联对象的ID
	 */
	private String uuid;

	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public String getShippingMan() {
		return shippingMan;
	}

	public void setShippingMan(String shippingMan) {
		this.shippingMan = shippingMan;
	}

	public String getShippingLinkType() {
		return shippingLinkType;
	}

	public void setShippingLinkType(String shippingLinkType) {
		this.shippingLinkType = shippingLinkType;
	}

	public String getShippingUnit() {
		return shippingUnit;
	}

	public void setShippingUnit(String shippingUnit) {
		this.shippingUnit = shippingUnit;
	}

	public String getShippingZipCode() {
		return shippingZipCode;
	}

	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	public String getInvocieAddr() {
		return invocieAddr;
	}

	public void setInvocieAddr(String invocieAddr) {
		this.invocieAddr = invocieAddr;
	}

	public String getInvocieMan() {
		return invocieMan;
	}

	public void setInvocieMan(String invocieMan) {
		this.invocieMan = invocieMan;
	}

	public String getInvocieLinkType() {
		return invocieLinkType;
	}

	public void setInvocieLinkType(String invocieLinkType) {
		this.invocieLinkType = invocieLinkType;
	}

	public String getInvocieUnit() {
		return invocieUnit;
	}

	public void setInvocieUnit(String invocieUnit) {
		this.invocieUnit = invocieUnit;
	}

	public String getInvocieZipCode() {
		return invocieZipCode;
	}

	public void setInvocieZipCode(String invocieZipCode) {
		this.invocieZipCode = invocieZipCode;
	}

	public String getConsigneeAddr() {
		return consigneeAddr;
	}

	public void setConsigneeAddr(String consigneeAddr) {
		this.consigneeAddr = consigneeAddr;
	}

	public String getConsigneeMan() {
		return consigneeMan;
	}

	public void setConsigneeMan(String consigneeMan) {
		this.consigneeMan = consigneeMan;
	}

	public String getConsigneeLinkType() {
		return consigneeLinkType;
	}

	public void setConsigneeLinkType(String consigneeLinkType) {
		this.consigneeLinkType = consigneeLinkType;
	}

	public String getConsigneeUnit() {
		return consigneeUnit;
	}

	public void setConsigneeUnit(String consigneeUnit) {
		this.consigneeUnit = consigneeUnit;
	}

	public String getConsigneeZipCode() {
		return consigneeZipCode;
	}

	public void setConsigneeZipCode(String consigneeZipCode) {
		this.consigneeZipCode = consigneeZipCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
