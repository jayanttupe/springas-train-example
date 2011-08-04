package com.skynet.spms.persistence.entity.organization.enterpriseInformation.clearanceAgencys;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 16:25:50
 */
@Entity
@DiscriminatorValue("ClearanceAgency")
public class ClearanceAgency extends BaseEnterpriseInformation {

	private boolean isAppoint;
	private List<EnterpriseGTA> m_EnterpriseGTA = new ArrayList<EnterpriseGTA>();
	private ClearanceAgent m_ClearanceAgent;
	
	@Column(name="IS_APPOINT")
	public boolean isAppoint() {
		return isAppoint;
	}
	public void setAppoint(boolean isAppoint) {
		this.isAppoint = isAppoint;
	}
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<EnterpriseGTA> getM_EnterpriseGTA() {
		return m_EnterpriseGTA;
	}
	public void setM_EnterpriseGTA(List<EnterpriseGTA> m_EnterpriseGTA) {
		this.m_EnterpriseGTA = m_EnterpriseGTA;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CLEARANCE_AGENT")
	public ClearanceAgent getM_ClearanceAgent() {
		return m_ClearanceAgent;
	}
	public void setM_ClearanceAgent(ClearanceAgent m_ClearanceAgent) {
		this.m_ClearanceAgent = m_ClearanceAgent;
	}
}