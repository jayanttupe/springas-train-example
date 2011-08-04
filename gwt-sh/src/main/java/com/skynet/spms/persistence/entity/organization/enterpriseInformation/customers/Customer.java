package com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers;
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

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.csdd.c.CustomerCategoryCode;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.customerCredit.CustomerCredit;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.customerCreditLine.CustomerCreditLine;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 24-三月-2011 15:44:15
 */
@Entity
@DiscriminatorValue("Customer")
public class Customer extends BaseEnterpriseInformation {

	private CustomerCategoryCode customerCategoryCode;
	private CustomerIdentificationCode m_CustomerIdentificationCode;
	private List<CustomerCredit> m_CustomerCredit = new ArrayList<CustomerCredit>();
	private List<CustomerCreditLine> m_CustomerCreditLine = new ArrayList<CustomerCreditLine>();
	private List<EnterpriseGTA> m_EnterpriseGTA = new ArrayList<EnterpriseGTA>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="CUSTOMER_CATEGORY_CODE")
	public CustomerCategoryCode getCustomerCategoryCode() {
		return customerCategoryCode;
	}
	public void setCustomerCategoryCode(CustomerCategoryCode customerCategoryCode) {
		this.customerCategoryCode = customerCategoryCode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_IDENTIFICATION_CODE")	
	public CustomerIdentificationCode getM_CustomerIdentificationCode() {
		return m_CustomerIdentificationCode;
	}
	
	public void setM_CustomerIdentificationCode(
			CustomerIdentificationCode m_CustomerIdentificationCode) {
		this.m_CustomerIdentificationCode = m_CustomerIdentificationCode;
	}
	
	@Reference(itemCls=CustomerCreditLine.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<CustomerCreditLine> getM_CustomerCreditLine() {
		return m_CustomerCreditLine;
	}
	public void setM_CustomerCreditLine(
			List<CustomerCreditLine> m_CustomerCreditLine) {
		this.m_CustomerCreditLine = m_CustomerCreditLine;
	}
	
	@Reference(itemCls=CustomerCredit.class)
	@OneToMany()
	@JoinColumn(name = "ENTERPRISE_ID")
	public List<CustomerCredit> getM_CustomerCredit() {
		return m_CustomerCredit;
	}
	public void setM_CustomerCredit(List<CustomerCredit> m_CustomerCredit) {
		this.m_CustomerCredit = m_CustomerCredit;
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