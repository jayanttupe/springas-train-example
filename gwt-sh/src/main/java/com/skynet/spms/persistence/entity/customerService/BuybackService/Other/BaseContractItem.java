package com.skynet.spms.persistence.entity.customerService.BuybackService.Other;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseItem.BasePartItem;
import com.skynet.spms.persistence.entity.csdd.c.ConditionCode;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PackagingCode;
import com.skynet.spms.persistence.entity.csdd.s.ShelfLifeCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

@MappedSuperclass
public class BaseContractItem extends BasePartItem {
	/** 收料追溯号 */
	private String num;
	/** 机型适用代码 **/
	private String m_ModelofApplicabilityCode;
	/** 包装代码 **/
	private PackagingCode m_PackagingCode;
	/** 单位代码 **/
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	/** 币种 */
	private InternationalCurrencyCode currency;
	/** 制造商代码 **/
	private ManufacturerCode m_ManufacturerCode;
	/** 备件状态代码 **/
	private ConditionCode m_ConditionCode;
	/** 货架寿命 －时寿代码 **/
	private ShelfLifeCode m_ShelfLifeCode;
	/** 随件资料多个逗号隔开 **/
	private String m_CertificateType;
	/** ata章节号 */
	public String ata;
	@Column(name = "MAC_CODE")
	public String getM_ModelofApplicabilityCode() {
		return m_ModelofApplicabilityCode;
	}

	public void setM_ModelofApplicabilityCode(String m_ModelofApplicabilityCode) {
		this.m_ModelofApplicabilityCode = m_ModelofApplicabilityCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CONDITION_CODE")
	public ConditionCode getM_ConditionCode() {
		return m_ConditionCode;
	}

	public void setM_ConditionCode(ConditionCode m_ConditionCode) {
		this.m_ConditionCode = m_ConditionCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SHELFLIFE_CODE")
	public ShelfLifeCode getM_ShelfLifeCode() {
		return m_ShelfLifeCode;
	}

	public void setM_ShelfLifeCode(ShelfLifeCode m_ShelfLifeCode) {
		this.m_ShelfLifeCode = m_ShelfLifeCode;
	}

	@Column(name = "CERTIFICATE_TYPE")
	public String getM_CertificateType() {
		return m_CertificateType;
	}

	public void setM_CertificateType(String m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UOM_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingCode getM_PackagingCode() {
		return m_PackagingCode;
	}

	public void setM_PackagingCode(PackagingCode m_PackagingCode) {
		this.m_PackagingCode = m_PackagingCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANUFACTURER_CODE")
	public ManufacturerCode getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}

	public void setM_ManufacturerCode(ManufacturerCode m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}

	/** 币种 */
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	public InternationalCurrencyCode getCurrency() {
		return currency;
	}

	public void setCurrency(InternationalCurrencyCode currency) {
		this.currency = currency;
	}

	/** 收料追溯号 */
	@Column(name = "NUM")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Column(name="ata")
	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}
	
	

}