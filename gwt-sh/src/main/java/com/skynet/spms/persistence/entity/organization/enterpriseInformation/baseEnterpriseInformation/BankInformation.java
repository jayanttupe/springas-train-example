package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

/**
 * @author 乔海龙
 * @version 1.0
 * @created 10-三月-2011 11:10:32
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_BANK_INFO")
public class BankInformation extends BaseEntity{

	private String itemNumber;
	private String bankName;
	private String bankAccount;
	private String bankAddress;
	private boolean isDefault;
	private String enterpriseId;
	
	
	@Column(name="ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="BANK_NAME")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name="BANK_ACCOUNT")
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Column(name="BANK_ADDRESS")
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	@Column(name="IS_DEFAULT")
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	/*@ManyToOne
	@JoinColumn(name = "ENTERPRISE_ID")
	public EnterpriseInformation getM_enterpriseInformation() {
		return m_enterpriseInformation;
	}
	public void setM_enterpriseInformation(
			EnterpriseInformation m_enterpriseInformation) {
		this.m_enterpriseInformation = m_enterpriseInformation;
	}*/
	
	@Column(name="ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}