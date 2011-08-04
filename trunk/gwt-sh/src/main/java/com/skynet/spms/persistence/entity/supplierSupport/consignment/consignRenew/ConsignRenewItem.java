package com.skynet.spms.persistence.entity.supplierSupport.consignment.consignRenew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * 寄售补库明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_CONSIGNRENEWITEM")
public class ConsignRenewItem extends BaseApplicationFormItem {

	private static final long serialVersionUID = 2960700903559239803L;
	/** 金额 */
	private Float price;
	/** 币种 */
	private InternationalCurrencyCode currency;
	/**
	 * 单价
	 */
	private Float unitPriceAmount;
	/**
	 * 关联的补库申请单编号
	 */
	private String consignRenewId;

	@Column(name = "CONSIGNRENEWID")
	public String getConsignRenewId() {
		return consignRenewId;
	}

	public void setConsignRenewId(String consignRenewId) {
		this.consignRenewId = consignRenewId;
	}

	/** 币种 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	public InternationalCurrencyCode getCurrency() {
		return currency;
	}

	public void setCurrency(InternationalCurrencyCode currency) {
		this.currency = currency;
	}

	/**
	 * 单价
	 * 
	 * @return
	 */
	@Column(name = "UNITPRICEAMOUNT")
	public Float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(Float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	@Column(name = "PRICE")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
