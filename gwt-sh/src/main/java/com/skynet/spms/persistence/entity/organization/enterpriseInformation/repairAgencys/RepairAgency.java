package com.skynet.spms.persistence.entity.organization.enterpriseInformation.repairAgencys;
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
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.airworthinessInformation.AirworthinessInformationEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 16:32:54
 */
@Entity
@DiscriminatorValue("RepairAgency")
public class RepairAgency extends BaseEnterpriseInformation {

	private boolean isAppoint;
	private RepairShopCode m_RepairShopCode;
	private List<EnterpriseGTA> m_EnterpriseGTA = new ArrayList<EnterpriseGTA>();
	private List<AirworthinessInformationEntity> m_AirworthinessInformationEntity = new ArrayList<AirworthinessInformationEntity>();
	
	@Column(name="IS_APPOINT")
	public boolean isAppoint() {
		return isAppoint;
	}
	public void setAppoint(boolean isAppoint) {
		this.isAppoint = isAppoint;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="REPAIR_SHOP_CODE")
	public RepairShopCode getM_RepairShopCode() {
		return m_RepairShopCode;
	}
	public void setM_RepairShopCode(RepairShopCode m_RepairShopCode) {
		this.m_RepairShopCode = m_RepairShopCode;
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

}