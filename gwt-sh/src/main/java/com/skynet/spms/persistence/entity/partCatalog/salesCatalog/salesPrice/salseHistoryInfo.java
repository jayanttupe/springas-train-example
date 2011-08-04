package com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * @author 王坤杰
 * @version 1.0
 * @created 30-四月-2011 15:26:17
 */
public class salseHistoryInfo extends BaseEntity {

	private java.util.Date deliveryDate;//交货日期
	private int manufacturersFiles;//销售数量
	private float previousPurchaseAveragePrice;//历次采购均价
	private String salesOrderNumber;//销售订单编号
	private float totalAmount;//总金额
	private float unitPriceAmount;//销售单价
	private CustomerIdentificationCode m_CustomerIdentificationCode;//客户识别代码
	private InternationalCurrencyCode m_InternationalCurrencyCode;//币种
	
	@Column(name = "DELIVERY_DATE")
	public java.util.Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(java.util.Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Column(name = "MANUFACTURERS_FILES")
	public int getManufacturersFiles() {
		return manufacturersFiles;
	}
	public void setManufacturersFiles(int manufacturersFiles) {
		this.manufacturersFiles = manufacturersFiles;
	}
	@Column(name = "PRE_PUR_AVER_PRICE")
	public float getPreviousPurchaseAveragePrice() {
		return previousPurchaseAveragePrice;
	}
	public void setPreviousPurchaseAveragePrice(float previousPurchaseAveragePrice) {
		this.previousPurchaseAveragePrice = previousPurchaseAveragePrice;
	}
	@Column(name = "SALES_ORDER-NUMBER")
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	@Column(name = "TOTAL_AMOUNT")
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name = "UNIT_PRICE_AMOUNT")
	public float getUnitPriceAmount() {
		return unitPriceAmount;
	}
	public void setUnitPriceAmount(float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUST_IDENT_CODE_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}
	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "INTER_CURR_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

}