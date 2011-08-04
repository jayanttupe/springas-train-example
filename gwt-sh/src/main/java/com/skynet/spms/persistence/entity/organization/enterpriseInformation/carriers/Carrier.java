package com.skynet.spms.persistence.entity.organization.enterpriseInformation.carriers;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 16:29:50
 */
@Entity
@DiscriminatorValue("Carrier")
public class Carrier extends BaseEnterpriseInformation {

	private boolean isAppoint;
	private CarrierName m_CarrierName;
	private List<EnterpriseGTA> m_EnterpriseGTA = new ArrayList<EnterpriseGTA>();

	@Column(name="IS_APPOINT")
	public boolean isAppoint() {
		return isAppoint;
	}
	public void setAppoint(boolean isAppoint) {
		this.isAppoint = isAppoint;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CARRIER_NAME")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}
	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}
	
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<EnterpriseGTA> getM_EnterpriseGTA() {
		return m_EnterpriseGTA;
	}
	public void setM_EnterpriseGTA(List<EnterpriseGTA> m_EnterpriseGTA) {
		this.m_EnterpriseGTA = m_EnterpriseGTA;
	}
}