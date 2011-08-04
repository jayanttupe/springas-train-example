package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet.BaseInquirySheet;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 采购询价单是采购计划人员下达采购指令之后，采购人员依据采购指令生成供应商询价单，询价单与计划采购指令之间的关联关系为一对多。采购询价单与供应商之间的关联关系为一
 * 对多，也就说针对一份采购询价单可以同时向多家供应商进行询价。
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:37
 */
@Entity
@Table(name = "SPMS_PROINQUIRYSHEET")
public class ProcurementInquirySheet extends BaseInquirySheet {

	/**采购指令编号**/
	private String orderNumber;
	/**对应采购报价单**/
	//private List<ProcurementQuotationSheetRecord> m_ProcurementQuotationSheetRecord;
	
	/**供应商**/
	private String m_supplier;
	
	/**询价供应商数量**/
	private Integer inquirySuppliersCount;
	
	/**报价供应商数量**/
	private Integer quotationSuppliersCount;
	
	/** 备件需求日期* */
	private Date partRequireDate;
	
	/** 询价单明细**/
	private List<ProcurementInquirySheetItem> m_ProcurementInquirySheetItem;
	
	
	/** 工作任务的优先级* */
	private Priority m_Priority;	
	
	/**机尾号*/
	private String airIdentificationNumber;
	
	@Column(name="AIRIDENTIFICATIONNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}
	public void setAirIdentificationNumber(String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="PISITEM_ID")
	public List<ProcurementInquirySheetItem> getM_ProcurementInquirySheetItem() {
		return m_ProcurementInquirySheetItem;
	}
	public void setM_ProcurementInquirySheetItem(
			List<ProcurementInquirySheetItem> procurementInquirySheetItem) {
		m_ProcurementInquirySheetItem = procurementInquirySheetItem;
	}
	
	@Column(name = "ORDERNUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Column(name = "M_SUPPLIER")
	public String getM_supplier() {
		return m_supplier;
	}
	public void setM_supplier(String m_supplier) {
		this.m_supplier = m_supplier;
	}

	@Column(name = "PART_REQUIRE_DATE")
	public Date getPartRequireDate() {
		return partRequireDate;
	}

	public void setPartRequireDate(Date partRequireDate) {
		this.partRequireDate = partRequireDate;
	}
	
	@Column(name ="INQUIRY_SUPPLIERS_COUNT")
	public Integer getInquirySuppliersCount() {
		return inquirySuppliersCount;
	}
	public void setInquirySuppliersCount(Integer inquirySuppliersCount) {
		this.inquirySuppliersCount = inquirySuppliersCount;
	}
	
	@Column(name ="QUOTATION_SUPPLIERS_COUNT")
	public Integer getQuotationSuppliersCount() {
		return quotationSuppliersCount;
	}
	public void setQuotationSuppliersCount(Integer quotationSuppliersCount) {
		this.quotationSuppliersCount = quotationSuppliersCount;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}

}