package com.skynet.spms.persistence.entity.customerService.sales.salesContract;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.SalesContractTemplate.SalesContractTemplate;
import com.skynet.spms.persistence.entity.customerService.BuybackService.Other.BaseContractItem;

/**
 * 销售合同明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_SALESCONTRACTITEM")
public class SalesContractItem extends BaseContractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3074529630522068256L;
	/** 金额 */
	private float price;
	/**
	 * 关联的合同
	 */
	private SalesContractTemplate salesTemplate;
	/** 序号/批号 */
	private String orderNum;

	/** 交货日期 **/
	private Date deliveryDate;
	/**
	 * 折扣百分比代码
	 */
	private Float m_DiscountPercentCode;
	
	/** 序号/批号 */
	@Column(name = "ORDERNUM")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	/** 交货日期 **/
	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * 关联的合同
	 */
	@ManyToOne
	@JoinColumn(name = "SCTID")
	public SalesContractTemplate getSalesTemplate() {
		return salesTemplate;
	}

	public void setSalesTemplate(SalesContractTemplate salesTemplate) {
		this.salesTemplate = salesTemplate;
	}

	/** 金额 */
	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * 折扣百分比代码
	 */
	@Column(name = "DISCOUNT_PERCENT_CODE")
	public Float getM_DiscountPercentCode() {
		return m_DiscountPercentCode;
	}
	/**
	 * 折扣百分比代码
	 */
	public void setM_DiscountPercentCode(Float m_DiscountPercentCode) {
		this.m_DiscountPercentCode = m_DiscountPercentCode;
	}
}
