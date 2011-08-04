package com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.customerCredit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.base.BaseCredit;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:41
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_CUSTOMER_CREDIT")
public class CustomerCredit extends BaseCredit{

	private String enterpriseId;

	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}