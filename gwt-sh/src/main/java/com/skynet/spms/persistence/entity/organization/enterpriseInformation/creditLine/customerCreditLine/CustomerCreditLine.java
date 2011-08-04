package com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.customerCreditLine;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.base.BaseCreditLine;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:41
 */
@Entity
@Table(name = "SPMS_CUSTOMER_CREDIT_LINE")
public class CustomerCreditLine extends BaseCreditLine{

	private String enterpriseId;

	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}