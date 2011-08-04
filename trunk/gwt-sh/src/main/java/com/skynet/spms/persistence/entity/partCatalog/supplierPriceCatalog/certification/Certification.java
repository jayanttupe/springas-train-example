package com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.certification;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CertificateType;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:21
 */

/*************************************************************************
 *Update by : Huhf    2011-4-27
 *CHECKED BY: Shanyf  2011-4-27
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
/*@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_CERTIFICATION")
public class Certification extends BaseEntity {

	*//**
	 * 缩写 CRD
	 * Specifies the date on which a Part Origin Certificate was issued for a specific
	 * part.  For reliability, this includes the date of incorporation into of an
	 * STC/modification.
	 *//*
	private java.util.Date certificateDate;
	*//**
	 * 缩写 CEN
	 * Specifies the identification number of a certificate, e.g. Supplemental Type
	 * Certificat (STC).
	 *//*
	private String certificateNumber;
	*//**
	 * 缩写 CES
	 * Specifies the identification of the company or agency which issued the
	 * certification of  origin for a part. Source identification may consist of a
	 * company or agency name or a company CAGE Code or NCAGE Code. For reliability
	 * applications, this includes the source of the Supplemental Type Certificate
	 * (STC).
	 *//*
	private String certificationSource;
	*//**
	 * Certificate Description Provides a textual description of a Supplemental Type
	 * Certificate (STC) or Modification.
	 *//*
	private String description;
	private List<Attachment> m_Attachment;
	private CertificateType m_CertificateType;
	private CAGECode m_CAGECode;//政府企业代码
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CAGE_CODE_ID")
	public CAGECode getM_CAGECode() {
		return m_CAGECode;
	}
	public void setM_CAGECode(CAGECode m_CAGECode) {
		this.m_CAGECode = m_CAGECode;
	}
	@Column(name= "CERTIFICATE_DATE")
	public java.util.Date getCertificateDate() {
		return certificateDate;
	}
	public void setCertificateDate(java.util.Date certificateDate) {
		this.certificateDate = certificateDate;
	}
	@Column(name= "CERTIFICATE_NUMBER")
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	@Column(name= "CERTIFICATION_SOURCE")
	public String getCertificationSource() {
		return certificationSource;
	}
	public void setCertificationSource(String certificationSource) {
		this.certificationSource = certificationSource;
	}
	@Column(name= "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Reference(itemCls=Attachment.class)
	@OneToMany()
	@JoinColumn(name = "CERTIFICATION_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "CERTIFICATE_TYPE")
	public CertificateType getM_CertificateType() {
		return m_CertificateType;
	}
	public void setM_CertificateType(CertificateType m_CertificateType) {
		this.m_CertificateType = m_CertificateType;
	}

}*/