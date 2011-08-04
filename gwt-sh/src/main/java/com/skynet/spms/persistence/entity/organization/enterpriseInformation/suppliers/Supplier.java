package com.skynet.spms.persistence.entity.organization.enterpriseInformation.suppliers;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.airworthinessInformation.AirworthinessInformationEntity;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.supplierQAManage.SupplierQAEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 16:13:53
 */
@Entity
@DiscriminatorValue("Supplier")
public class Supplier extends BaseEnterpriseInformation {

	private boolean isAppoint;
	private List<SupplierQAEntity> m_SupplierQAEntity = new ArrayList<SupplierQAEntity>();
	private List<EnterpriseGTA> m_EnterpriseGTA = new ArrayList<EnterpriseGTA>();
	private List<AirworthinessInformationEntity> m_AirworthinessInformationEntity = new ArrayList<AirworthinessInformationEntity>();
	private SupplierCode m_SupplierCode;
	
	@Column(name="IS_APPOINT")
	public boolean isAppoint() {
		return isAppoint;
	}
	public void setAppoint(boolean isAppoint) {
		this.isAppoint = isAppoint;
	}
	@Reference(itemCls=SupplierQAEntity.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<SupplierQAEntity> getM_SupplierQAEntity() {
		return m_SupplierQAEntity;
	}
	public void setM_SupplierQAEntity(List<SupplierQAEntity> m_SupplierQAEntity) {
		this.m_SupplierQAEntity = m_SupplierQAEntity;
	}
	
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<EnterpriseGTA> getM_EnterpriseGTA() {
		return m_EnterpriseGTA;
	}
	public void setM_EnterpriseGTA(List<EnterpriseGTA> m_EnterpriseGTA) {
		this.m_EnterpriseGTA = m_EnterpriseGTA;
	}
	
	@Reference(itemCls=AirworthinessInformationEntity.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<AirworthinessInformationEntity> getM_AirworthinessInformationEntity() {
		return m_AirworthinessInformationEntity;
	}
	public void setM_AirworthinessInformationEntity(
			List<AirworthinessInformationEntity> m_AirworthinessInformationEntity) {
		this.m_AirworthinessInformationEntity = m_AirworthinessInformationEntity;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SUPPLIER_CODE")
	public SupplierCode getM_SupplierCode() {
		return m_SupplierCode;
	}
	public void setM_SupplierCode(SupplierCode m_SupplierCode) {
		this.m_SupplierCode = m_SupplierCode;
	}

}