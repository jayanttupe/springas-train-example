package com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.base;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:32
 */
@MappedSuperclass
public class BaseCredit extends BaseEntity{

	private float credit;
	private Date startDate;
	private Date expiryDate;
	public InternationalCurrencyCode m_InternationalCurrencyCode;
	
	@Column(name="CREDIT")
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
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
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}
}