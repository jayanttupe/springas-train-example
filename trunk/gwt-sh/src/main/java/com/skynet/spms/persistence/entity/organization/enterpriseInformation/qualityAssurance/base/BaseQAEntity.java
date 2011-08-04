package com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.base;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.EnableStatus;
import com.skynet.spms.persistence.entity.spmsdd.SupplierRank;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-三月-2011 17:26:27
 */
@MappedSuperclass
public class BaseQAEntity extends BaseEntity{
	
	private Date expiryDate;
	private Date modifyDate;
	private Date startDate;
	public BussinessPublishStatusEntity m_BussinessPublishStatusEntity ;
	public BussinessStatusEntity m_BussinessStatusEntity;
	public EnableStatus m_EnableStatus;
	public SupplierRank m_SupplierRank;
	
	@Column(name="START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name="MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "SUPPLIER_RANK")
	public SupplierRank getM_SupplierRank() {
		return m_SupplierRank;
	}
	public void setM_SupplierRank(SupplierRank m_SupplierRank) {
		this.m_SupplierRank = m_SupplierRank;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "ENABLE_STATUS")
	public EnableStatus getM_EnableStatus() {
		return m_EnableStatus;
	}
	public void setM_EnableStatus(EnableStatus m_EnableStatus) {
		this.m_EnableStatus = m_EnableStatus;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "BUSSINESS_PUBLISH_STATUS_ID")
	public BussinessPublishStatusEntity getM_BussinessPublishStatusEntity() {
		return m_BussinessPublishStatusEntity;
	}
	public void setM_BussinessPublishStatusEntity(BussinessPublishStatusEntity m_BussinessPublishStatusEntity) {
		this.m_BussinessPublishStatusEntity = m_BussinessPublishStatusEntity;
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