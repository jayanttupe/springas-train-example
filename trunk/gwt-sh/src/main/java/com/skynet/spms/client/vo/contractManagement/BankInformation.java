package com.skynet.spms.client.vo.contractManagement;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BankInformation implements IsSerializable{

	private Integer itemNumber;
	private String bankName;
	private String bankAccount;
	private String bankAddress;
	private Boolean isDefault;
	private String enterpriseId;
	public Integer getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public Boolean isDefault() {
		return isDefault;
	}
	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
}
