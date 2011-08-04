package com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.baseApplicationFormItem;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.csdd.w.WarrantyTimeCycleReferenceCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 基础申请单明细项
 * 
 * @author tqc
 * @version 1.0
 * @created 16-三月-2011 16:05:31
 */
@MappedSuperclass
public class BaseApplicationFormItem extends BaseEntity {

	/** 项号 **/
	private Integer itemNumber;

	/** 件号 **/
	private String partNumber;

	/** ata章节号 */
	private String partAta;

	/** 数量 **/
	private Float quantity;

	/** 单位代码 **/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	
	/** 制造商代码 **/
	private ManufacturerCode m_ManufacturerCode;

	/** 货架寿命 －时寿代码 **/
	private ShelfLifeCode m_ShelfLifeCode;

	/** 机型适用代码 **/
	private String m_ModelofApplicabilityCode;

	/** 备件状态代码 **/
	private ConditionCode m_ConditionCode;

	/** 担保时间/循环代码 **/
	private WarrantyTimeCycleReferenceCode m_WarrantyTimeCycleReferenceCode;

	/** 包装代码 **/
	private PackagingCode m_PackagingCode;

	/** 随件资料多个逗号隔开 **/
	private String m_CertificateType;

	/** 备件编号 **/
	private String m_partIndex;

	// add by tqc
	/** 备注* */
	private String remarkText;

	private Priority m_Priority;
	/** 飞机尾号 **/
	private String airIdentificationNumber;

	@Column(name = "AIFNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}

	@Column(name = "PARTATA")
	public String getPartAta() {
		return partAta;
	}

	public void setPartAta(String partAta) {
		this.partAta = partAta;
	}

	@Column(name = "REMARK")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Column(name = "ITEM_NUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "QUANTITY")
	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UOM_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
		m_UnitOfMeasureCode = unitOfMeasureCode;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "MANUFACTURER_CODE")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}
	
	 public void setM_ManufacturerCode(ManufacturerCode manufacturerCode) {
	 m_ManufacturerCode = manufacturerCode;
	 }

	@Enumerated(EnumType.STRING)
	@Column(name = "SHELFLIFE_CODE")
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}

	public void setM_ShelfLifeCode(ShelfLifeCode shelfLifeCode) {
		m_ShelfLifeCode = shelfLifeCode;
	}

	@Column(name = "MAC_CODE")
	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(String modelofApplicabilityCode) {
		m_ModelofApplicabilityCode = modelofApplicabilityCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CONDITION_CODE")
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(ConditionCode conditionCode) {
		m_ConditionCode = conditionCode;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}

	public void setM_PackagingCode(PackagingCode packagingCode) {
		m_PackagingCode = packagingCode;
	}

	@Column(name = "CERTIFICATE_TYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String certificateType) {
		m_CertificateType = certificateType;
	}

	@Column(name = "PART_INDEX")
	public String getM_partIndex() {
		return m_partIndex;
	}

	public void setM_partIndex(String index) {
		m_partIndex = index;
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