package com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem.BaseApplicationFormItem;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;

/**
 * 销售明细
 * 
 * @author Administrator
 * @version 1.0
 * @created 05-五月-2011 11:15:45
 */
@Entity
@Table(name = "SPMS_SALESREQUISITION_ITEM")
public class SalesRequisitionSheetItem extends BaseApplicationFormItem {

	/**
	 * 生产批次号/序号 Identifies a transmission grouping of part orders (S1BOOKED),
	 * part order exceptions (S1ORDEXC), repair order (R1CPOXMT) repair order
	 * acknowledgments (R1CPOACK), repair order exceptions (R1EXCXMT), or repair
	 * order exception acknowledgments (R1EXCACK).
	 */
	private String batchNumber;
	/**
	 * 备件交货日期
	 */
	private Date partDeliveryDate;
	/**
	 * 单价
	 */
	private Float unitOfPrice;
	/**
	 * 币种
	 */
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	/**
	 * 对应报价件
	 */
	private SalesQuotationSheetItem salesQuotationSheetItem;
	
	/**
	 * 件号描述
	 */
	private String partDescription;
	
//	/** 关联的回购申请单 */
//	private BuybackRequisitionSheet sheet;
//
//	@ManyToOne
//	@JoinColumn(name = "SHEETID")
//	public BuybackRequisitionSheet getSheet() {
//		return sheet;
//	}
//
//	public void setSheet(BuybackRequisitionSheet sheet) {
//		this.sheet = sheet;
//	}


	

	/** 金额* */
	private Float amount;

	
	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	/**采购订单*/
	private SalesRequisitionSheet salesRequisitionSheet;
	
	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "BATCH_NUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	@Column(name = "PART_DELIVERY_DATE")
	public Date getPartDeliveryDate() {
		return partDeliveryDate;
	}

	public void setPartDeliveryDate(Date partDeliveryDate) {
		this.partDeliveryDate = partDeliveryDate;
	}

	@Column(name = "UNTI_PRICE")
	public Float getUnitOfPrice() {
		return unitOfPrice;
	}

	public void setUnitOfPrice(Float unitOfPrice) {
		this.unitOfPrice = unitOfPrice;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode internationalCurrencyCode) {
		m_InternationalCurrencyCode = internationalCurrencyCode;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SQSHEETITEM_ID")
	public SalesQuotationSheetItem getSalesQuotationSheetItem() {
		return salesQuotationSheetItem;
	}

	public void setSalesQuotationSheetItem(
			SalesQuotationSheetItem salesQuotationSheetItem) {
		this.salesQuotationSheetItem = salesQuotationSheetItem;
	}

	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "SALES_REQUISITION_ID")
	public SalesRequisitionSheet getSalesRequisitionSheet() {
		return salesRequisitionSheet;
	}

	public void setSalesRequisitionSheet(SalesRequisitionSheet salesRequisitionSheet) {
		this.salesRequisitionSheet = salesRequisitionSheet;
	}

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "SQSHEETITEM_ID")
//	public SalesQuotationSheetItem getM_SalesQuotationSheetItem() {
//		return m_SalesQuotationSheetItem;
//	}
//
//	public void setM_SalesQuotationSheetItem(
//			SalesQuotationSheetItem salesQuotationSheetItem) {
//		m_SalesQuotationSheetItem = salesQuotationSheetItem;
//	}

}