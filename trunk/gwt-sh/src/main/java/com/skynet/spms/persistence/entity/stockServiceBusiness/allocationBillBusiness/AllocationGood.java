package com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-16
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_ALLOCATION_GOOD")
public class AllocationGood extends BaseEntity {
	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 关键字
	 */
	private String keyWord;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 数量
	 */
	private String quantity;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 备件状态
	 */
	private String state;

	/**
	 * 单价
	 */
	private String unitPrice;

	/**
	 * 计划调入日期
	 */
	private Date planDate;

	/**
	 * 收货库房
	 */
	private String inWareHouse;

	/**
	 * 发货库房
	 */
	private String outWareHouse;

	/**
	 * 是否时寿
	 */
	private String isLifeLimit;

	/**
	 * 调拨单ID
	 */
	private String allocationBillID;

	public AllocationGood() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "KEY_WORD")
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "PART_SERIAL_NUMBER")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "UNIT_PRICE")
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "PLAN_DATE")
	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	@Column(name = "IN_WARE_HOUSE")
	public String getInWareHouse() {
		return inWareHouse;
	}

	public void setInWareHouse(String inWareHouse) {
		this.inWareHouse = inWareHouse;
	}

	@Column(name = "OUT_WARE_HOUSE")
	public String getOutWareHouse() {
		return outWareHouse;
	}

	public void setOutWareHouse(String outWareHouse) {
		this.outWareHouse = outWareHouse;
	}

	@Column(name = "IS_LIFE_LIMIT")
	public String getIsLifeLimit() {
		return isLifeLimit;
	}

	public void setIsLifeLimit(String isLifeLimit) {
		this.isLifeLimit = isLifeLimit;
	}

	@Column(name = "ALLOCATION_BILL_ID")
	public String getAllocationBillID() {
		return allocationBillID;
	}

	public void setAllocationBillID(String allocationBillID) {
		this.allocationBillID = allocationBillID;
	}
}