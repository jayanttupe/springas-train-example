package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.csdd.c.CustomerCategoryCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;

@ViewFormAnno(value="id")
public class EnterpriseForm extends BaseEnterpriseInformation {
	
	private String cageCode;
	
	private String customerIdentificationCode;
	
	private String supplierCode;
	
	private String manufacturerCode;
	
	private String clearanceAgent;
	
	private String carrierName;
	
	private String repairShopCode;
	
	private CustomerCategoryCode customerCategoryCode;
	
	private boolean isAppoint;
	
	public CustomerCategoryCode getCustomerCategoryCode() {
		return customerCategoryCode;
	}
	public void setCustomerCategoryCode(CustomerCategoryCode customerCategoryCode) {
		this.customerCategoryCode = customerCategoryCode;
	}
	
	public boolean isAppoint() {
		return isAppoint;
	}
	public void setAppoint(boolean isAppoint) {
		this.isAppoint = isAppoint;
	}

	public String getCageCode() {
		return cageCode;
	}
	public void setCageCode(String cageCode) {
		this.cageCode = cageCode;
	}

	public String getCustomerIdentificationCode() {
		return customerIdentificationCode;
	}
	public void setCustomerIdentificationCode(String customerIdentificationCode) {
		this.customerIdentificationCode = customerIdentificationCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getClearanceAgent() {
		return clearanceAgent;
	}
	public void setClearanceAgent(String clearanceAgent) {
		this.clearanceAgent = clearanceAgent;
	}

	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getRepairShopCode() {
		return repairShopCode;
	}
	public void setRepairShopCode(String repairShopCode) {
		this.repairShopCode = repairShopCode;
	}
}
