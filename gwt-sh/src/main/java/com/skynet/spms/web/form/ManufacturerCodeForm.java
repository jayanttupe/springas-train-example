package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;

@ViewFormAnno(value="manufacturerCodeId")
public class ManufacturerCodeForm {

	private String manufacturerCodeId;
	private String manufacturerCode;
	private String manufacturerName;
	

	public String getManufacturerCodeId() {
		return manufacturerCodeId;
	}
	public void setManufacturerCodeId(String manufacturerCodeId) {
		this.manufacturerCodeId = manufacturerCodeId;
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
}
