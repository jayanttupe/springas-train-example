package com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseLeaseContractItem;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseItem.BasePartItem;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.p.PartStatusCode;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.w.WarrantyTimeCycleReferenceCode;
import com.skynet.spms.persistence.entity.spmsdd.UnitOfRent;

/**
 * @author 曹宏炜
 * @category 基础租赁合同明细项
 * @version 1.0
 * @created 03-四月-2011 11:32:27
 */
@MappedSuperclass
public class BaseLeaseContractItem extends BasePartItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 租赁开始日期
	private Date startDate;
	// 租赁结束日期
	private Date endDate;
	// 租赁天数
	private int leaseDays;
	// 备件的原价值
	private float originalValue;
	// 标准单位租金
	private float generalRentOfUnit;
	// 延期单位租金
	private float extendedRentOfUnit;
	// 延期描述
	private String extendedDescription;
	/** 货架寿命 －时寿代码 **/
	private ShelfLifeCode m_ShelfLifeCode;
	/** 担保时间/循环代码 **/
	private WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode;
	/** 机型适用代码 **/
	private String m_ModelofApplicabilityCode;
	/** 包装代码 **/
	private PackagingCode m_PackagingCode;
	/** 单位代码 **/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	/** 项号 **/
	private Integer itemNumber;
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	private ManufacturerCode m_ManufacturerCode;
	private PartStatusCode m_PartStatusCode;
	private String m_CertificateType;
	private UnitOfRent m_UnitOfRent;
	/** 备件状态 **/
	private ConditionCode m_ConditionCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "M_CONDITIONCODE")
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(ConditionCode mConditionCode) {
		m_ConditionCode = mConditionCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SHELFLIFE_CODE")
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}

	public void setM_ShelfLifeCode(ShelfLifeCode shelfLifeCode) {
		m_ShelfLifeCode = shelfLifeCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "WTCR_CODE")
	public WarrantyTimeCycleReferenceCode getM_WarrantyTimeCycleReferenceCode() {
		return m_WarrantyTimeCycleReferenceCode;
	}

	public void setM_WarrantyTimeCycleReferenceCode(
			WarrantyTimeCycleReferenceCode warrantyTimeCycleReferenceCode) {
		m_WarrantyTimeCycleReferenceCode = warrantyTimeCycleReferenceCode;
	}

	@Column(name = "MAC_CODE")
	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(String modelofApplicabilityCode) {
		m_ModelofApplicabilityCode = modelofApplicabilityCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}

	public void setM_PackagingCode(PackagingCode packagingCode) {
		m_PackagingCode = packagingCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UOM_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
		m_UnitOfMeasureCode = unitOfMeasureCode;
	}

	@Column(name = "ITEM_NUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "LEASEDAYS")
	public int getLeaseDays() {
		return leaseDays;
	}

	public void setLeaseDays(int leaseDays) {
		this.leaseDays = leaseDays;
	}

	@Column(name = "ORIGINALVALUE")
	public float getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(float originalValue) {
		this.originalValue = originalValue;
	}

	@Column(name = "GENERALRENTOFUNIT")
	public float getGeneralRentOfUnit() {
		return generalRentOfUnit;
	}

	public void setGeneralRentOfUnit(float generalRentOfUnit) {
		this.generalRentOfUnit = generalRentOfUnit;
	}

	@Column(name = "EXTENDEDRENTOFUNIT")
	public float getExtendedRentOfUnit() {
		return extendedRentOfUnit;
	}

	public void setExtendedRentOfUnit(float extendedRentOfUnit) {
		this.extendedRentOfUnit = extendedRentOfUnit;
	}

	@Column(name = "EXTENDEDDESCRIPTION")
	public String getExtendedDescription() {
		return extendedDescription;
	}

	public void setExtendedDescription(String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INTERCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "M_MANUFCODE_ID")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PARTSTATUSCODE")
	public PartStatusCode getM_PartStatusCode() {
		return m_PartStatusCode;
	}

	public void setM_PartStatusCode(PartStatusCode m_PartStatusCode) {
		this.m_PartStatusCode = m_PartStatusCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_UNITOFRENT")
	public UnitOfRent getM_UnitOfRent() {
		return m_UnitOfRent;
	}

	public String getM_CertificateType() {
		return m_CertificateType;
	}

	@Column(name = "M_CERTIFICATETYPE")
	public void setM_CertificateType(String mCertificateType) {
		m_CertificateType = mCertificateType;
	}

	public void setM_UnitOfRent(UnitOfRent m_UnitOfRent) {
		this.m_UnitOfRent = m_UnitOfRent;
	}

}