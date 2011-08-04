package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementContract;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.procurementContractTemplate.ProcurementContractTemplate;
import com.skynet.spms.persistence.entity.customerService.BuybackService.Other.BaseContractItem;

/**
 * 采购合同明细
 * 
 * @author gqr
 * 
 */
@Entity
@Table(name = "SPMS_PROCUREMENTCONTRACTITEM")
public class ProcurementContractItem extends BaseContractItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3074529630522068256L;
	/** 金额 */
	private Float amount;
	/** 关联的合同 */
	private ProcurementContractTemplate template;
	/** 序号/批号 */
	private String orderNum;
	/** 供应商比价 **/
	private String suppliersParityItemIds;
	
	private String suppliersParityItemNames;
	
	/** 交货日期 **/
	private Date deliveryDate;
	
	@Column(name = "SUPPLIERSPARITYITEMNAMES")
	public String getSuppliersParityItemNames() {
		return suppliersParityItemNames;
	}

	public void setSuppliersParityItemNames(String suppliersParityItemNames) {
		this.suppliersParityItemNames = suppliersParityItemNames;
	}

	

	@Column(name = "GONGYSPRICE")
	public void setSuppliersParityItemIds(String suppliersParityItemIds) {
		this.suppliersParityItemIds = suppliersParityItemIds;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String getSuppliersParityItemIds() {
		return suppliersParityItemIds;
	}



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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BCTID")
	public ProcurementContractTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ProcurementContractTemplate template) {
		this.template = template;
	}


	/** 金额 */
	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
}
