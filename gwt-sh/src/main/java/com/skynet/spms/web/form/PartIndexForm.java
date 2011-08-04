package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
@ViewFormAnno(value="id")
public class PartIndexForm extends PartIndex {
	
	private String manufacturerCodeId;

	public String getManufacturerCodeId() {
		return manufacturerCodeId;
	}

	public void setManufacturerCodeId(String manufacturerCodeId) {
		this.manufacturerCodeId = manufacturerCodeId;
	}
}
