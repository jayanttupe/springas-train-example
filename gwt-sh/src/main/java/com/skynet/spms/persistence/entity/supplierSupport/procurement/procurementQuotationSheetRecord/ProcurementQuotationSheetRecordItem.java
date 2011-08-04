package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.baseQuotationSheetItem.BaseQuotationSheetItem;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
//import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.partSupplierPriceIndex;
//import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementContract.procurementOrderItem.ProcurementOrderItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;


/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:39
 */
@Entity
@Table(name = "SPMS_PRO_QUOTATION_ITEM")
public class ProcurementQuotationSheetRecordItem extends BaseQuotationSheetItem {

	private String procurementPlanItemNumber;
//	/public List<partSupplierPriceIndex> m_partSupplierPriceIndex;
//	public ProcurementOrderItem m_ProcurementOrderItem;
	
	private ProcurementQuotationSheetRecord procurementQuotationSheetRecord;

	/**询价件**/
	private ProcurementInquirySheetItem procurementInquirySheetItem;
	
	private List<ProcurementPiecewiseQuotationItem> m_ProcurementPiecewiseQuotationItem;
	
	/**是否加入比价单*/
	private Boolean isJoinParity;
	
	public Boolean getIsJoinParity() {
		return isJoinParity;
	}
	public void setIsJoinParity(Boolean isJoinParity) {
		this.isJoinParity = isJoinParity;
	}
	@OneToOne
	@JoinColumn(name = "PISITEM_ID")
	public ProcurementInquirySheetItem getProcurementInquirySheetItem() {
		return procurementInquirySheetItem;
	}
	public void setProcurementInquirySheetItem(
			ProcurementInquirySheetItem procurementInquirySheetItem) {
		this.procurementInquirySheetItem = procurementInquirySheetItem;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "PROCUREMENT_QUOTATION_ID")
	public ProcurementQuotationSheetRecord getProcurementQuotationSheetRecord() {
		return procurementQuotationSheetRecord;
	}
	public void setProcurementQuotationSheetRecord(
			ProcurementQuotationSheetRecord procurementQuotationSheetRecord) {
		this.procurementQuotationSheetRecord = procurementQuotationSheetRecord;
	}
	
	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PPIECWISEQITEM_ID")
	public List<ProcurementPiecewiseQuotationItem> getM_ProcurementPiecewiseQuotationItem() {
		return m_ProcurementPiecewiseQuotationItem;
	}
	public void setM_ProcurementPiecewiseQuotationItem(
			List<ProcurementPiecewiseQuotationItem> procurementPiecewiseQuotationItem) {
		m_ProcurementPiecewiseQuotationItem = procurementPiecewiseQuotationItem;
	}

}