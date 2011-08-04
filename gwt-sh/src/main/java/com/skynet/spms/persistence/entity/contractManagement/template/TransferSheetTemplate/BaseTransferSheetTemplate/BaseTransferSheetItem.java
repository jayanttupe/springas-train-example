package com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.BaseTransferSheetTemplate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.m.ModelofApplicabilityCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrder;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 21-五月-2011 15:15:46
 */
@MappedSuperclass
public class BaseTransferSheetItem extends BaseEntity {

	/** 项号 **/
	private Integer itemNumber;
	/**
	 * 采购计划明细项编号，是一个人工可识别的明细项编号，通过这个编号可以跟踪采购计划的执行情况。
	 */
	private String procurementPlanItemNumber;
	/** 件号 **/
	private String partNumber;
	/** 关键字 **/
	private String itemKeyword;
	/** ATA章节号 **/
	private String ATA;
	/** 单价 **/
	private Float unitPriceAmount;
	/**
	 * 调拨单价已本币方式进行计算 计划单价=采购系数*单价*汇率
	 */
	private Float plannedUnitPrice;
	/** 调拨数量 **/
	private Float plannedQuantity;
	/**
	 * 采购系数做为采购价格中的一个可变动权重常量，其中计划单价的计算是由，单价*采购系数*汇率得到。采购系数即利润
	 */
	public Float coefficient;
	/**
	 * 要求采购到库的日期
	 */
	private Integer leadTime;
	/** 交货日期 **/
	private Date deliveryDate;
	/** 备件需求编号 **/
	private String partRequirementNumber;
	/**
	 * 需求产生的具体日期
	 */
	private Date requiredDate;
	/** 总金额 **/
	private  Float amount;
	
	/**币种**/
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	
	/**随机资料（证书类型）**/
	private String m_CertificateType;
	
	/**单位**/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	
	// private List<ProcurementQuantityStrategy> m_ProcurementQuantityStrategy;
	
	/**制造商**/
	private ManufacturerCode m_ManufacturerCode;
	//private List<SupplierCode> supplierCode;
	// public ProcurementInquirySheetItem m_ProcurementInquirySheetItem;
	// private RemarkTextEntity m_RemarkTextEntity;
	//private ProcurementOrder procurementOrder;
	/** 备注 **/
	private String remarkText;

	
	/** 机型适用代码 **/
	private String m_ModelofApplicabilityCode;
	
	
	@Column(name = "REMARKTEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

//	@ManyToOne
//	@JoinColumn(name = "PROEMEDER_ID")
//	public ProcurementOrder getProcurementOrder() {
//		return procurementOrder;
//	}
//
//	public void setProcurementOrder(ProcurementOrder procurementOrder) {
//		this.procurementOrder = procurementOrder;
//	}

	@Column(name = "ITEMNUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "PROCUREMENTPLANITEMNUMBER")
	public String getProcurementPlanItemNumber() {
		return procurementPlanItemNumber;
	}

	public void setProcurementPlanItemNumber(String procurementPlanItemNumber) {
		this.procurementPlanItemNumber = procurementPlanItemNumber;
	}

	@Column(name = "PARTNUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "ITEM_KEYWORD")
	public String getItemKeyword() {
		return itemKeyword;
	}

	public void setItemKeyword(String keyword) {
		this.itemKeyword = keyword;
	}

	@Column(name = "ATA")
	public String getATA() {
		return ATA;
	}

	public void setATA(String aTA) {
		ATA = aTA;
	}

	@Column(name = "UNITPRICEAMOUNT")
	public Float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(Float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	@Column(name = "PLANNEDUNITPRICE")
	public Float getPlannedUnitPrice() {
		return plannedUnitPrice;
	}

	public void setPlannedUnitPrice(Float plannedUnitPrice) {
		this.plannedUnitPrice = plannedUnitPrice;
	}

	@Column(name = "PLANNEDQUANTITY")
	public Float getPlannedQuantity() {
		return plannedQuantity;
	}

	public void setPlannedQuantity(Float plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}

	@Column(name = "COEFFICIENT")
	public Float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "LEADTIME")
	public Integer getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "PARTREQUIREMENTNUMBER")
	public String getPartRequirementNumber() {
		return partRequirementNumber;
	}

	public void setPartRequirementNumber(String partRequirementNumber) {
		this.partRequirementNumber = partRequirementNumber;
	}

	@Column(name = "REQUIREDDATE")
	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INTRNICURRECCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_CERTIFICATETYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFMEASURECODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	// public List<ProcurementQuantityStrategy>
	// getM_ProcurementQuantityStrategy() {
	// return m_ProcurementQuantityStrategy;
	// }
	// public void setM_ProcurementQuantityStrategy(
	// List<ProcurementQuantityStrategy> m_ProcurementQuantityStrategy) {
	// this.m_ProcurementQuantityStrategy = m_ProcurementQuantityStrategy;
	// }
	@ManyToOne
	@JoinColumn(name = "M_MANUFACTURERCODE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	// public List<SupplierCode> getSupplierCode() {
	// return supplierCode;
	// }
	// public void setSupplierCode(List<SupplierCode> supplierCode) {
	// this.supplierCode = supplierCode;
	// }

	// @OneToOne
	// @JoinColumn(name = "M_REMARKTEXTENTITY_ID")
	// public RemarkTextEntity getM_RemarkTextEntity() {
	// return m_RemarkTextEntity;
	// }
	//
	// public void setM_RemarkTextEntity(RemarkTextEntity m_RemarkTextEntity) {
	// this.m_RemarkTextEntity = m_RemarkTextEntity;
	// }
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MAC_CODE")
	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(
			String m_ModelofApplicabilityCode) {
		this.m_ModelofApplicabilityCode = m_ModelofApplicabilityCode;
	}

}