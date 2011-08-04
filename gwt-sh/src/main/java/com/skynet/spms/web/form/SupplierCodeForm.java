package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

@ViewFormAnno(value="supplierCodeId")
public class SupplierCodeForm {

	private String supplierCodeId;
	private String supplierCode;
	private String supplierName;
	
	public String getSupplierCodeId() {
		return supplierCodeId;
	}
	public void setSupplierCodeId(String supplierCodeId) {
		this.supplierCodeId = supplierCodeId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
