package com.skynet.spms.persistence.entity.baseSupplyChain.baseInquirySheet.baseInquirySheetItem;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.csdd.a.AircraftIdentificationNumber;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

@MappedSuperclass
public class BaseInquirySheetItem extends BaseEntity {
	/**项号**/
	private Integer itemNumber;
	
	/** 件号* */
	private String partNumber;

	/** 数量* */
	private Float quantity;

	/** 指定交货地址* */
	private String deliveryAddress;

	/** 备件状态代码* */
	private ConditionCode m_ConditionCode;

	private SupplierCode m_SupplierCode;

	/** 单位代码 * */
	private UnitOfMeasureCode m_UnitOfMeasureCode;

	/** （随机资料）证书类型* */
	private String m_CertificateType;// 应该是List 暂时定字符串

	/** 工作任务的优先级* */
	private Priority m_Priority;

	/** 机尾号* */
	private String airIdentificationNumber;

	/** 国际货币代码* */
	private InternationalCurrencyCode m_InternationalCurrencyCode;



	/** 备件需求日期* */
	private Date partRequireDate;

	/** 报价状态实体 * */
	public QuotationStatusEntity m_QuotationStatusEntity;
	
	/**ATA章节号**/
	private String ata; 
	/** 制造商代码 * */
	private ManufacturerCode m_ManufacturerCode;
	// 以下待定
	

	

	/** 备注* */
	private String remark;

	/** 机型适用代码* */
	private String m_ModelofApplicabilityCode;
	
	
	@Column(name="ATA")
	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
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
	@Column(name = "CONDITION_CODE")
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(ConditionCode conditionCode) {
		m_ConditionCode = conditionCode;
	}
	
	@ManyToOne
	@JoinColumn(name="SUPPLIERCODE")
	public SupplierCode getM_SupplierCode() {
		return m_SupplierCode;
	}

	public void setM_SupplierCode(SupplierCode supplierCode) {
		m_SupplierCode = supplierCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_OF_MEASURE_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode unitOfMeasureCode) {
		m_UnitOfMeasureCode = unitOfMeasureCode;
	}

	@Column(name = "CERIFICATE_TYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String certificateType) {
		m_CertificateType = certificateType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}

	@ManyToOne
	@JoinColumn(name = "MANUFACTURER_CODE")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode manufacturerCode) {
		m_ManufacturerCode = manufacturerCode;
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


	
	@Column(name = "AIRIDENTIFICATIONNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(
			String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "QSTATUSENTITY_ID")
	public QuotationStatusEntity getM_QuotationStatusEntity() {
		return m_QuotationStatusEntity;
	}

	public void setM_QuotationStatusEntity(
			QuotationStatusEntity quotationStatusEntity) {
		m_QuotationStatusEntity = quotationStatusEntity;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "MODELO_APPLICABILITY_CODE")
	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(String modelofApplicabilityCode) {
		m_ModelofApplicabilityCode = modelofApplicabilityCode;
	}

	@Column(name = "DELIVERYADDRESS")
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Column(name = "PART_REQUIRE_DATE")
	public Date getPartRequireDate() {
		return partRequireDate;
	}

	public void setPartRequireDate(Date partRequireDate) {
		this.partRequireDate = partRequireDate;
	}

	@Column(name = "ITEMNUMBER")
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

}
