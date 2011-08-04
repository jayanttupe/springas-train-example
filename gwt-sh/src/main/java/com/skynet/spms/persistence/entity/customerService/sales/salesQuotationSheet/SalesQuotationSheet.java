package com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.businessStatusEntity.RequisitionStatusEntity;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.BaseQuotationSheet;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;

/**
 * 报价单
 * 
 * @author tqc
 * @version 1.0
 * @created 17-三月-2011 9:25:36
 */
@Entity
@Table(name = "SPMS_SALESQUOTAIONSHEET")
public class SalesQuotationSheet extends BaseQuotationSheet {

	/**
	 * 对于总价给予折扣
	 */
	private Float discountPercent;
	/**
	 * 明细项总价，等于明细项价格的累积价格
	 */
	private Float totalAmount;
	/**
	 * 将总价减去折扣后的价格
	 */
	private Float discountTotalAmount;

	/**
	 * 客户识别代码
	 */
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	/**
	 * 客户订单
	 */
	// private List<SalesRequisitionSheet> m_SalesRequisitionSheet;
	/**
	 * 报价单明细
	 */
	private List<SalesQuotationSheetItem> m_SalesQuotationSheetItem;

	
	/** 采购状态实体* */
	private RequisitionStatusEntity m_RequisitionStatusEntity;
	
	/**
	 * 对应询价单
	 */
	private SalesInquirySheet salesInquirySheet;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REQUISITION_ID")
	public RequisitionStatusEntity getM_RequisitionStatusEntity() {
		return m_RequisitionStatusEntity;
	}

	public void setM_RequisitionStatusEntity(
			RequisitionStatusEntity requisitionStatusEntity) {
		m_RequisitionStatusEntity = requisitionStatusEntity;
	}


	@ManyToOne
	@JoinColumn(name = "CIC_ID")
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}

	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode customerIdentificationCode) {
		m_CustomerIdentificationCode = customerIdentificationCode;
	}

	@Column(name = "DISCOUNT_PERCENT")
	public Float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Column(name = "DISCOUNT_TOTAL_AMOUNT")
	public Float getDiscountTotalAmount() {
		return discountTotalAmount;
	}

	public void setDiscountTotalAmount(Float discountTotalAmount) {
		this.discountTotalAmount = discountTotalAmount;
	}

	@Column(name = "TOTAL_AMOUNT")
	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SQSITEM_ID")
	public List<SalesQuotationSheetItem> getM_SalesQuotationSheetItem() {
		return m_SalesQuotationSheetItem;
	}

	public void setM_SalesQuotationSheetItem(
			List<SalesQuotationSheetItem> m_SalesQuotationSheetItem) {
		this.m_SalesQuotationSheetItem = m_SalesQuotationSheetItem;
	}

	@ManyToOne(cascade= CascadeType.REFRESH)
	@JoinColumn(name = "SIS_ID")
	public SalesInquirySheet getSalesInquirySheet() {
		return salesInquirySheet;
	}

	public void setSalesInquirySheet(SalesInquirySheet salesInquirySheet) {
		this.salesInquirySheet = salesInquirySheet;
	}

}