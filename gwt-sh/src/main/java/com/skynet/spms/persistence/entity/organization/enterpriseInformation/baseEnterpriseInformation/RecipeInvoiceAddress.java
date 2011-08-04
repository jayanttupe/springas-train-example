package com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation;

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
 * @created 10-三月-2011 11:11:00
 */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_RECIPE_INVOICE_ADDRESS")
public class RecipeInvoiceAddress  extends BaseEntity{

	private String itemNumber;
	private String address;
	private String postCode;
	private boolean isDefault;
	private String recipient;
	private String enterpriseId;
	
	@Column(name="ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="POST_CODE")
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Column(name="IS_DEFAULT")
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	@Column(name="RECIPIENT")
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	/*
	@ManyToOne
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