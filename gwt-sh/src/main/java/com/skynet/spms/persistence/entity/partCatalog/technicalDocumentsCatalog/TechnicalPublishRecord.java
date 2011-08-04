package com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData.OptionalPart;
import com.skynet.spms.persistence.entity.spmsdd.TechnicalPublishType;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:27
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY: 
 *Confirm by: 
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_TECH_PUBLISH_RCD")
public class TechnicalPublishRecord extends BaseEntity {

	private String referenceNumber;//技术引用号
	private TechnicalPublishType m_TechnicalPublishType;
	private List<Attachment> m_Attachment;//附件
	
	@Reference(itemCls=Attachment.class)
	@OneToMany()
	@JoinColumn(name = "TECH_PUBLISH_RCD_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
	@Column(name="REFERENCE_NUMBER")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "TECHNICAL_PUBLISH_TYPE")
	public TechnicalPublishType getM_TechnicalPublishType() {
		return m_TechnicalPublishType;
	}
	public void setM_TechnicalPublishType(
			TechnicalPublishType m_TechnicalPublishType) {
		this.m_TechnicalPublishType = m_TechnicalPublishType;
	}

}