package com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackPact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.BuybackContractTemplate.BuybackContractTemplate;
import com.skynet.spms.persistence.entity.customerService.BuybackService.Other.BaseContractItem;

/**
 * 回购合同明细
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_BUYBACKPACTITEM")
public class BuybackPactItem extends BaseContractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3074529630522068256L;
	/** 金额 */
	private float price;
	/**
	 * 关联的合同
	 */
	private BuybackContractTemplate template;
	/** 序号/批号 */
	private String orderNum;

	/** 序号/批号 */
	@Column(name = "ORDERNUM")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 关联的合同
	 */
	@ManyToOne
	@JoinColumn(name = "BCTID")
	public BuybackContractTemplate getTemplate() {
		return template;
	}

	public void setTemplate(BuybackContractTemplate template) {
		this.template = template;
	}

	/** 金额 */
	@Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
