package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 物流货物的明细信息。
 * 
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BasePickupDeliveryVanningItems extends BaseEntity {
	/**
	 * 指令ID
	 */
	private String orderID;

	/**
	 * 货物箱信息ID
	 */
	private String vanningID;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 件名称
	 */
	private String partName;

	/**
	 * 备件类型
	 */
	private String partType;

	/**
	 * 数量
	 */
	private String quantity;

	/**
	 * 单价
	 */
	private String unitPriceAmount;

	/**
	 * 总金额
	 */
	private String totalAmount;

	/**
	 * 单位
	 */
	private String unit;

	/** 币种 **/
	private String currency;

	/** 合同编号 **/
	private String contractNumber;


	public BasePickupDeliveryVanningItems() {
	}

	@Column(name = "CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Column(name = "VANNING_ID")
	public String getVanningID() {
		return vanningID;
	}

	public void setVanningID(String vanningID) {
		this.vanningID = vanningID;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PART_NAME")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "PART_TYPE")
	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "UNIT_PRICE_AMOUNT")
	public String getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(String unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	@Column(name = "TOTAL_AMOUNT")
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}