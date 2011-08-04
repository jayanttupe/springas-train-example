package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:13
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_VAT")
public class VAT extends BaseEntity{
	
	private String vatIdentificationNumber;
	private String vatAddressTelephone;
	private String vatEnterpriseName;
	private String vatBankInformation;

	@Column(name="VAT_IDENTIFICATION_NUMBER")
	public String getVatIdentificationNumber() {
		return vatIdentificationNumber;
	}
	public void setVatIdentificationNumber(String vatIdentificationNumber) {
		this.vatIdentificationNumber = vatIdentificationNumber;
	}
	@Column(name="VAT_ADDRESS_TELEPHONE")
	public String getVatAddressTelephone() {
		return vatAddressTelephone;
	}
	public void setVatAddressTelephone(String vatAddressTelephone) {
		this.vatAddressTelephone = vatAddressTelephone;
	}
	@Column(name="VAT_ENTERPRISE_NAME")
	public String getVatEnterpriseName() {
		return vatEnterpriseName;
	}
	public void setVatEnterpriseName(String vatEnterpriseName) {
		this.vatEnterpriseName = vatEnterpriseName;
	}
	@Column(name="VAT_BANKINFORMATION")
	public String getVatBankInformation() {
		return vatBankInformation;
	}
	public void setVatBankInformation(String vatBankInformation) {
		this.vatBankInformation = vatBankInformation;
	}
}