package com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet.baseInquirySheetItem.BaseInquirySheetItem;
/**
 * 询价单明细
 * 
 * @author tqc
 * @version 1.0
 * @created 16-三月-2011 16:02:42
 */
@Entity
@Table(name = "SPMS_SALESINQUIRYSHEET_ITEM")
public class SalesInquirySheetItem extends BaseInquirySheetItem {
	
	/** 询价单 **/
	private SalesInquirySheet salesInquirySheet;
	
	/**报价编号*/
	private String quotationSheetNumber;

	@Column(name = "QUOTATION_NUMBER")
	public String getQuotationSheetNumber() {
		return quotationSheetNumber;
	}

	public void setQuotationSheetNumber(String quotationSheetNumber) {
		this.quotationSheetNumber = quotationSheetNumber;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "SALES_INQUIRY_SHEETT_ID")
	public SalesInquirySheet getSalesInquirySheet() {
		return salesInquirySheet;
	}

	public void setSalesInquirySheet(SalesInquirySheet salesInquirySheet) {
		this.salesInquirySheet = salesInquirySheet;
	}

}
