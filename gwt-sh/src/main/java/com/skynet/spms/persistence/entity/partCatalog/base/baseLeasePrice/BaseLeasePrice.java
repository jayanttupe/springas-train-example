package com.skynet.spms.persistence.entity.partCatalog.base.baseLeasePrice;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.csdd.l.LeaseIndicator;
import com.skynet.spms.persistence.entity.csdd.u.UnitCode;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:20
 */
/*@MappedSuperclass
public class BaseLeasePrice extends BasePrice {

	private String leaseRemarkText;
	private LeaseIndicator m_LeaseIndicator;
	private UnitCode m_UnitCode;
	
	@Column(name="LEASE_REMARK_TEXT")
	public String getLeaseRemarkText() {
		return leaseRemarkText;
	}
	public void setLeaseRemarkText(String leaseRemarkText) {
		this.leaseRemarkText = leaseRemarkText;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "LEASE_INDICATOR")
	public LeaseIndicator getM_LeaseIndicator() {
		return m_LeaseIndicator;
	}
	public void setM_LeaseIndicator(LeaseIndicator m_LeaseIndicator) {
		this.m_LeaseIndicator = m_LeaseIndicator;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitCode getM_UnitCode() {
		return m_UnitCode;
	}
	public void setM_UnitCode(UnitCode m_UnitCode) {
		this.m_UnitCode = m_UnitCode;
	}
	
	

}*/