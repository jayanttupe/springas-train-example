package com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.baseQuotationSheetItem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem.baseQuantityPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.baseSupplyChain.baseQuotationSheet.BaseQuotationSheet;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.w.WarrantyTimeCycleReferenceCode;
import com.skynet.spms.persistence.entity.spmsdd.EquipmentType;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 基础报价单明细项
 * 
 * @author Administrator
 * @version 1.0
 * @created 10-三月-2011 11:10:35
 */
@MappedSuperclass
public class BaseQuotationSheetItem extends BaseEntity {

	/** 项号 * */
	private Integer itemNumber;

	/***************************************************************************
	 * 报价件号，不同于询价号，业务人员依据客户的询价请求 提供性价比更好的备件件号
	 **************************************************************************/
	private String quotationPartNumber;

	/** 描述 * */
	private String description;

	/** 数量* */
	private Float quantity;

	/**
	 * 特殊包装说明
	 */
	private String packageDescription;

	/**
	 * 特殊包装费用
	 */
	private Float packagePrice;

	/** 单价 * */
	private Float unitPriceAmount;
	
	/**金额**/
	private Float amount;

	/** 交货期（天） * */
	private Integer deliveryLeadTime;

	/** 折扣 * */
	private Float discountPercent;

	/** 最小销售数量 * */
	private Float minimumSalesQuantity;

	/** 联系人* */
	private String linkman;

	/** 备件来源 * */
	private String partSource;

	/**
	 * 标准包装数量 Specifies the standard selling quantity per package of the subject
	 * part number, conforming to the Unit of Measure.
	 * 
	 */
	private Float standardPackageQuantity;

	/**
	 * 担保时间/循环 The total number of calendar days, operating cycles, or operating
	 * hours that will be provided as the warranty period.
	 */
	private Integer warrantyTimeCycle;

	/** 件号变更原因* */
	private String reasonForPartNumberChange;

	/** 付款要求* */
	private String paymentRequirement;

	/**是否分段报价**/
	private Boolean isBreakPrice;

	/** 制造商代码 * */
	private ManufacturerCode m_ManufacturerCode;

	/** 证书类型* */
	private String m_CertificateType;

	/** 备注** */
	private String remark;

	/** 国际货币代码 * */
	private InternationalCurrencyCode m_InternationalCurrencyCode;

	/** 担保时间/循环代码 * */
	private WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode;

	/** 货架寿命代码 * */
	private ShelfLifeCode m_ShelfLifeCode;

	/**基础数量分段价格明细项**/
//	private List<baseQuantityPiecewiseQuotationItem> m_baseQuantityPiecewiseQuotationItem;

	
	
	private EquipmentType m_EquipmentType;

	/**适用机型**/
	private String m_AircraftModelIdentifier;

	/** 备件状态代码 * */
	private ConditionCode m_ConditionCode;

	/** 包装代码 * */
	private PackagingCode m_PackagingCode;

	/**附件**/
	private List<Attachment> m_Attachment;

	/** 单位代码 * */
	private UnitOfMeasureCode m_UnitOfMeasureCode;

	// public partIndex m_partIndex;
	
	
	/**ATA章节号**/
	private String ata; 
	@Column(name="ATA")
	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	private BaseQuotationSheet m_BaseQuotationSheet;

	

	/** 工作任务的优先级* */
	private Priority m_Priority;
	/**机尾号*/
	private String airIdentificationNumber;

	//
	// /** 机型适用代码 * */
	// private String m_ModelofApplicabilityCode;
	//
	// /** 采购状态实体* */
	// private RequisitionStatusEntity m_RequisitionStatusEntity;
	//	
	// /** 一类容器价格 * */
	// private Float categoryIContainerPriceAmount;
	//
	
//	private SalesRequisitionSheetItem salesRequisitionSheetItem;
//	
//	@OneToOne
//	@JoinColumn(name = "SSHEETITEM_ID")
//	public SalesRequisitionSheetItem getSalesRequisitionSheetItem() {
//		return salesRequisitionSheetItem;
//	}
//
//	public void setSalesRequisitionSheetItem(
//			SalesRequisitionSheetItem salesRequisitionSheetItem) {
//		this.salesRequisitionSheetItem = salesRequisitionSheetItem;
//	}

//	@Column("AIRIDENTIFICATIONNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}

	@Column(name = "ITEM_NUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "QUOTAP_NUMBER")
	public String getQuotationPartNumber() {
		return quotationPartNumber;
	}

	public void setQuotationPartNumber(String quotationPartNumber) {
		this.quotationPartNumber = quotationPartNumber;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// @Column(name = "CATEGORY_AMOUNT")
	// public Float getCategoryIContainerPriceAmount() {
	// return categoryIContainerPriceAmount;
	// }
	//
	// public void setCategoryIContainerPriceAmount(
	// Float categoryIContainerPriceAmount) {
	// this.categoryIContainerPriceAmount = categoryIContainerPriceAmount;
	// }

	// @Column(name = "CERTIFICATION_SOURCE")
	// public String getCertificationSource() {
	// return certificationSource;
	// }
	//
	// public void setCertificationSource(String certificationSource) {
	// this.certificationSource = certificationSource;
	// }

	@Column(name = "UNTI_AMOUNT")
	public Float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(Float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}

	@Column(name = "DELIVERY_LEAD_TIME")
	public Integer getDeliveryLeadTime() {
		return deliveryLeadTime;
	}

	public void setDeliveryLeadTime(Integer deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
	}

	@Column(name = "DIS_PERCENT")
	public Float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Column(name = "MINI_QUANTITY")
	public Float getMinimumSalesQuantity() {
		return minimumSalesQuantity;
	}

	public void setMinimumSalesQuantity(Float minimumSalesQuantity) {
		this.minimumSalesQuantity = minimumSalesQuantity;
	}

	@Column(name = "PART_SOURCE")
	public String getPartSource() {
		return partSource;
	}

	public void setPartSource(String partSource) {
		this.partSource = partSource;
	}

	@Column(name = "STA_QUANTITY")
	public Float getStandardPackageQuantity() {
		return standardPackageQuantity;
	}

	public void setStandardPackageQuantity(Float standardPackageQuantity) {
		this.standardPackageQuantity = standardPackageQuantity;
	}

	@Column(name = "WARR_CYCLE")
	public Integer getWarrantyTimeCycle() {
		return warrantyTimeCycle;
	}

	public void setWarrantyTimeCycle(Integer warrantyTimeCycle) {
		this.warrantyTimeCycle = warrantyTimeCycle;
	}

	@Column(name = "REASON_CHANGE")
	public String getReasonForPartNumberChange() {
		return reasonForPartNumberChange;
	}

	public void setReasonForPartNumberChange(String reasonForPartNumberChange) {
		this.reasonForPartNumberChange = reasonForPartNumberChange;
	}

	//
	// @Column(name = "MODELOF_CODE")
	// public String getM_ModelofApplicabilityCode() {
	// return m_ModelofApplicabilityCode;
	// }
	//
	// public void setM_ModelofApplicabilityCode(String
	// m_ModelofApplicabilityCode) {
	// this.m_ModelofApplicabilityCode = m_ModelofApplicabilityCode;
	// }

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "WARRANTY_CODE")
	public WarrantyTimeCycleReferenceCode getM_WarrantyTimeCycleReferenceCode() {
		return m_WarrantyTimeCycleReferenceCode;
	}

	public void setM_WarrantyTimeCycleReferenceCode(
			WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode) {
		this.m_WarrantyTimeCycleReferenceCode = m_WarrantyTimeCycleReferenceCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SHEL_CODE")
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}

	public void setM_ShelfLifeCode(ShelfLifeCode m_ShelfLifeCode) {
		this.m_ShelfLifeCode = m_ShelfLifeCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}

	public void setM_PackagingCode(PackagingCode m_PackagingCode) {
		this.m_PackagingCode = m_PackagingCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CONDITION_CODE")
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(ConditionCode m_ConditionCode) {
		this.m_ConditionCode = m_ConditionCode;
	}

	@ManyToOne
	@JoinColumn(name = "MANU_CODE")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	@Column(name = "QUANTITY")
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	@Column(name = "PACKAGEPRICE")
	public Float getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(Float packagePrice) {
		this.packagePrice = packagePrice;
	}

	@Column(name = "PAYMENTREQUIREMENT")
	public String getPaymentRequirement() {
		return paymentRequirement;
	}

	public void setPaymentRequirement(String paymentRequirement) {
		this.paymentRequirement = paymentRequirement;
	}

	@Column(name = "PACKAGEDESCRIPTION")
	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	
	@Column(name = "ISBREAKPRICE")
	public Boolean getIsBreakPrice() {
		return isBreakPrice;
	}

	public void setIsBreakPrice(Boolean isBreakPrice) {
		this.isBreakPrice = isBreakPrice;
	}

	@Column(name = "CERTIFICATETYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String certificateType) {
		m_CertificateType = certificateType;
	}


	@Column(name = "AIRCRAFT_MODEL_IDENTIFIER")
	public String getM_AircraftModelIdentifier() {
		return m_AircraftModelIdentifier;
	}

	public void setM_AircraftModelIdentifier(String aircraftModelIdentifier) {
		m_AircraftModelIdentifier = aircraftModelIdentifier;
	}


	@Column(name = "AMOUNT")
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@OneToMany
	@JoinColumn(name = "ATTACHMENT_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}

	public void setM_Attachment(List<Attachment> attachment) {
		m_Attachment = attachment;
	}


//	@OneToMany(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "BQPIECEWISEQITEM_ID")
//	public List<baseQuantityPiecewiseQuotationItem> getM_baseQuantityPiecewiseQuotationItem() {
//		return m_baseQuantityPiecewiseQuotationItem;
//	}

//	public void setM_baseQuantityPiecewiseQuotationItem(
//			List<baseQuantityPiecewiseQuotationItem> quantityPiecewiseQuotationItem) {
//		m_baseQuantityPiecewiseQuotationItem = quantityPiecewiseQuotationItem;
//	}

	

	

}