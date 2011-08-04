package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord;
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

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.procurementContractTemplate.ProcurementContractTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.BaseQuotationSheet;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:38
 */
@Entity
@Table(name = "SPMS_PRO_QUOTATION")
public class ProcurementQuotationSheetRecord extends BaseQuotationSheet {

	/**供应商报价的折扣率，如果没有折扣率此项可以不填写**/
	private Float discountPercent;
	/**供应商明细项累积的总报价**/
	private  Float totalAmount;
	/** 总价减去折扣价格的折扣后总报价**/
	private  Float discountTotalAmount;
	/** 供应商采购合同**/
	private ProcurementContractTemplate m_ProcurementContractTemplate;
	/** 商业及政府实体代码**/
	private CAGECode supplier;
	
	/**供应商*/
	private SupplierCode supplierCode;
	
	/**报价单明细**/
	private List<ProcurementQuotationSheetRecordItem> m_ProcurementQuotationSheetRecordItem;
	
	/**对应询价单**/
	private ProcurementInquirySheet procurementInquirySheet;
	
	@ManyToOne
	@JoinColumn(name = "PIS_ID")
	public ProcurementInquirySheet getProcurementInquirySheet() {
		return procurementInquirySheet;
	}
	public void setProcurementInquirySheet(
			ProcurementInquirySheet procurementInquirySheet) {
		this.procurementInquirySheet = procurementInquirySheet;
	}
	@Column(name="DISCOUNT_PERCENT")
	public Float getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	
	@Column(name="TOTAL_AMOUNT")
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name="DISCOUNT_TOTAL_AMOUNT")
	public Float getDiscountTotalAmount() {
		return discountTotalAmount;
	}
	public void setDiscountTotalAmount(Float discountTotalAmount) {
		this.discountTotalAmount = discountTotalAmount;
	}
	
	@OneToOne
	@JoinColumn(name = "SUPPLIER_CODE")
	public void setSupplier(CAGECode supplier) {
		this.supplier = supplier;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PQSITEM_ID")
	public List<ProcurementQuotationSheetRecordItem> getM_ProcurementQuotationSheetRecordItem() {
		return m_ProcurementQuotationSheetRecordItem;
	}
	public void setM_ProcurementQuotationSheetRecordItem(
			List<ProcurementQuotationSheetRecordItem> procurementQuotationSheetRecordItem) {
		m_ProcurementQuotationSheetRecordItem = procurementQuotationSheetRecordItem;
	}
	public CAGECode getSupplier() {
		return supplier;
	}
	
	@ManyToOne
	@JoinColumn(name = "SUPPLIERCODE_ID")
	public SupplierCode getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(SupplierCode supplierCode) {
		this.supplierCode = supplierCode;
	}

}