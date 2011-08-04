package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
@ViewFormAnno(value="id")
public class ApplicationDataForm extends PartIndex {
	
	private String description;
	
	private boolean isCOMACPatent;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCOMACPatent() {
		return isCOMACPatent;
	}

	public void setCOMACPatent(boolean isCOMACPatent) {
		this.isCOMACPatent = isCOMACPatent;
	}
	
	
}
