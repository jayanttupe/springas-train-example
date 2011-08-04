package com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.baseQuotationSheetItem.BaseQuotationSheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;

/**
 * 报价单明细项
 * 
 * @author tqc
 * @version 1.0
 * @created 17-三月-2011 9:25:36
 */
@Entity
@Table(name = "SPMS_SALESQUOTAIONSHEET_ITEM")
public class SalesQuotationSheetItem extends BaseQuotationSheetItem {
	
	private SalesQuotationSheet salesQuotationSheet;
	
	/** 对应询价单明细* */
	private SalesInquirySheetItem salesInquirySheetItem;
	
	private Date partRequireDate;
	
	/**是否加如比价但**/
	private Boolean isJoinParity;

	public Boolean getIsJoinParity() {
		return isJoinParity;
	}

	public void setIsJoinParity(Boolean isJoinParity) {
		this.isJoinParity = isJoinParity;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "SALES_QUOTATION_SHEETT_ID")
	public SalesQuotationSheet getSalesQuotationSheet() {
		return salesQuotationSheet;
	}

	public void setSalesQuotationSheet(SalesQuotationSheet salesQuotationSheet) {
		this.salesQuotationSheet = salesQuotationSheet;
	}
	
	@OneToOne
	@JoinColumn(name = "SSHEETITEM_ID")
	public SalesInquirySheetItem getSalesInquirySheetItem() {
		return salesInquirySheetItem;
	}

	public void setSalesInquirySheetItem(
			SalesInquirySheetItem salesInquirySheetItem) {
		this.salesInquirySheetItem = salesInquirySheetItem;
	}
	
	@Column(name = "REQUIRE_DATE")
	public Date getPartRequireDate() {
		return partRequireDate;
	}

	public void setPartRequireDate(Date partRequireDate) {
		this.partRequireDate = partRequireDate;
	}
}