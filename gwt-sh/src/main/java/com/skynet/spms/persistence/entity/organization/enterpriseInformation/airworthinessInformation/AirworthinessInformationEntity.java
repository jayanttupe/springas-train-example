package com.skynet.spms.persistence.entity.organization.enterpriseInformation.airworthinessInformation;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.AirworthinessOrg;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:30
 */
@Entity
@Table(name = "SPMS_AIRWORTHINESS_INFO")
public class AirworthinessInformationEntity extends BaseEntity{

	private String airworthinessNumber;
	private Date signingDate;
	private Date expiryDate;
	private AirworthinessOrg m_AirworthinessOrg;
	private String enterpriseId;
	
	@Column(name="AIRWORTHINESS_NUMBER")
	public String getAirworthinessNumber() {
		return airworthinessNumber;
	}
	
	public void setAirworthinessNumber(String airworthinessNumber) {
		this.airworthinessNumber = airworthinessNumber;
	}
	@Column(name="SIGNING_DATE")
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "AIRWORTHINESS_ORG")
	public AirworthinessOrg getM_AirworthinessOrg() {
		return m_AirworthinessOrg;
	}
	public void setM_AirworthinessOrg(AirworthinessOrg m_AirworthinessOrg) {
		this.m_AirworthinessOrg = m_AirworthinessOrg;
	}
	/*@ManyToOne
	@JoinColumn(name = "ENTERPRISE_ID")
	public EnterpriseInformation getM_EnterpriseInformation() {
		return m_EnterpriseInformation;
	}
	public void setM_EnterpriseInformation(
			EnterpriseInformation m_EnterpriseInformation) {
		this.m_EnterpriseInformation = m_EnterpriseInformation;
	}*/
	
	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}