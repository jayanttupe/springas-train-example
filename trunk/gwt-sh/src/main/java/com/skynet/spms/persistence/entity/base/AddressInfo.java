package com.skynet.spms.persistence.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 地址信息
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_ADDRESSINFO")
public class AddressInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7671293142921581635L;

	/**
	 * 发货地址
	 * 
	 * @return
	 */
	@Column(name = "SHIPPINGADDR")
	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	/**
	 * 发货人
	 * 
	 * @return
	 */
	@Column(name = "SHIPPINGMAN")
	public String getShippingMan() {
		return shippingMan;
	}

	public void setShippingMan(String shippingMan) {
		this.shippingMan = shippingMan;
	}

	/**
	 * 发货地址邮编
	 * 
	 * @return
	 */
	@Column(name = "SHIPPINGZIPCODE")
	public String getShippingZipCode() {
		return shippingZipCode;
	}

	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	/**
	 * 收发票地址
	 * 
	 * @return
	 */
	@Column(name = "INVOCIEADDR")
	public String getInvocieAddr() {
		return invocieAddr;
	}

	public void setInvocieAddr(String invocieAddr) {
		this.invocieAddr = invocieAddr;
	}

	/**
	 * 收发票人
	 * 
	 * @return
	 */
	@Column(name = "INVOCIEMAN")
	public String getInvocieMan() {
		return invocieMan;
	}

	public void setInvocieMan(String invocieMan) {
		this.invocieMan = invocieMan;
	}

	/**
	 * 收发票地址邮编
	 * 
	 * @return
	 */
	@Column(name = "INVOCIEZIPCODE")
	public String getInvocieZipCode() {
		return invocieZipCode;
	}

	public void setInvocieZipCode(String invocieZipCode) {
		this.invocieZipCode = invocieZipCode;
	}

	/**
	 * 收货地址
	 * 
	 * @return
	 */
	@Column(name = "CONSIGNEEADDR")
	public String getConsigneeAddr() {
		return consigneeAddr;
	}

	public void setConsigneeAddr(String consigneeAddr) {
		this.consigneeAddr = consigneeAddr;
	}

	/**
	 * 收货人
	 * 
	 * @return
	 */
	@Column(name = "CONSIGNEEMAN")
	public String getConsigneeMan() {
		return consigneeMan;
	}

	public void setConsigneeMan(String consigneeMan) {
		this.consigneeMan = consigneeMan;
	}

	/**
	 * 收货地址邮编
	 * 
	 * @return
	 */
	@Column(name = "CONSIGNEEZIPCODE")
	public String getConsigneeZipCode() {
		return consigneeZipCode;
	}

	public void setConsigneeZipCode(String consigneeZipCode) {
		this.consigneeZipCode = consigneeZipCode;
	}

	/**
	 * 发货联系方式
	 * 
	 * @return
	 */
	@Column(name="SHIPPINGLINKTYPE")
	public String getShippingLinkType() {
		return shippingLinkType;
	}

	public void setShippingLinkType(String shippingLinkType) {
		this.shippingLinkType = shippingLinkType;
	}

	/**
	 * 发货单位
	 * 
	 * @return
	 */
	@Column(name="SHIPPINGUNIT")
	public String getShippingUnit() {
		return shippingUnit;
	}

	public void setShippingUnit(String shippingUnit) {
		this.shippingUnit = shippingUnit;
	}

	/**
	 * 发票收取联系方式
	 * 
	 * @return
	 */
	@Column(name="INVOCIELINKTYPE")
	public String getInvocieLinkType() {
		return invocieLinkType;
	}

	public void setInvocieLinkType(String invocieLinkType) {
		this.invocieLinkType = invocieLinkType;
	}

	/**
	 * 发票收取单位
	 * 
	 * @return
	 */
	@Column(name="INVOCIEUNIT")
	public String getInvocieUnit() {
		return invocieUnit;
	}

	public void setInvocieUnit(String invocieUnit) {
		this.invocieUnit = invocieUnit;
	}

	/**
	 * 收货联系方式
	 * 
	 * @return
	 */
	@Column(name="CONSIGNEELINKTYPE")
	public String getConsigneeLinkType() {
		return consigneeLinkType;
	}

	public void setConsigneeLinkType(String consigneeLinkType) {
		this.consigneeLinkType = consigneeLinkType;
	}

	/**
	 * 收货单位
	 * 
	 * @return
	 */
	@Column(name="CONSIGNEEUNIT")
	public String getConsigneeUnit() {
		return consigneeUnit;
	}

	public void setConsigneeUnit(String consigneeUnit) {
		this.consigneeUnit = consigneeUnit;
	}

	/**
	 * 关联对象的ID
	 * 
	 * @return
	 */
	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

}
