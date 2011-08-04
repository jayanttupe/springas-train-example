package com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.GTAType;
import com.skynet.spms.persistence.entity.base.Attachment;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.AuditStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;

/**
 * 企业GTA协议是主体企业与各个合作商企业之间签订的协议框架，一份GTA协议可以被组合到多个企业中（企业通常为母子关系），即子公司可以继承母公司所签订的GTA协议
 * 。GTA协议与企业之间的关系为多对多关系（但限定在一个集团公司内部）。
 * @author 乔海龙
 * @version 1.0
 * @created 10-三月-2011 11:10:44
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_ENTERPRISE_GTA")
public class EnterpriseGTA extends BaseEntity{

	private String itemNumber;
	private String gtaNumber;
	private String title;
	private String description;
	private Date expiryDate;
	private Date signingDate;
	private GTAType m_GTAType;
	private String enterpriseId;
	
	private List<Attachment> m_Attachment = new ArrayList<Attachment>();
	private BussinessStatusEntity m_BussinessStatusEntity;
	
	@Column(name="ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="GTA_NUMBER")
	public String getGtaNumber() {
		return gtaNumber;
	}
	public void setGtaNumber(String gtaNumber) {
		this.gtaNumber = gtaNumber;
	}
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name="SIGNING_DATE")
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "GTA_TYPE")
	public GTAType getM_GTAType() {
		return m_GTAType;
	}
	public void setM_GTAType(GTAType m_GTAType) {
		this.m_GTAType = m_GTAType;
	}
	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	@Reference(itemCls=Attachment.class)
	@OneToMany()
	@JoinColumn(name = "RELATED_BUSSINESS_ID")
	public List<Attachment> getM_Attachment() {
		return m_Attachment;
	}
	public void setM_Attachment(List<Attachment> m_Attachment) {
		this.m_Attachment = m_Attachment;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_STATUS_ID")
	public BussinessStatusEntity getM_BussinessStatusEntity() {
		return m_BussinessStatusEntity;
	}
	public void setM_BussinessStatusEntity(BussinessStatusEntity m_BussinessStatusEntity) {
		this.m_BussinessStatusEntity = m_BussinessStatusEntity;
	}
}