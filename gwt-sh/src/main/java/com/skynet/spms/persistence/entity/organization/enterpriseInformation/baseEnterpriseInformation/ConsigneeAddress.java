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
 * @created 10-三月-2011 11:10:40
 */
@Entity
@FilterDefs({ @FilterDef(name = "active", defaultCondition = "IS_DELETED = :isDele", parameters = @ParamDef(name = "isDele", type = "boolean")) })
@Filter(name = "active")
@Table(name = "SPMS_CONSIGNEE_ADDRESS")
public class ConsigneeAddress extends BaseEntity {

	private String itemNumber;
	private String address;
	private String postCode;
	private boolean isDefault;
	private String recipient;
	private String contactMan;
	private String contactWay;
	private String enterpriseId;
	
	@Column(name="CONTACT_MAN")
	public String getContactMan() {
		return contactMan;
	}
	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}
	@Column(name="CONTACT_WAY")
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	@Column(name="ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "POST_CODE")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "IS_DEFAULT")
	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "RECIPIENT")
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}