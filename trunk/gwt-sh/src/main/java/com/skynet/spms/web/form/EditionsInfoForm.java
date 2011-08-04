package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
@ViewFormAnno(value="id")
public class EditionsInfoForm extends EditionsInformation {
	

	private String partSaleReleaseId;
	
	public String getPartSaleReleaseId() {
		return partSaleReleaseId;
	}

	public void setPartSaleReleaseId(String partSaleReleaseId) {
		this.partSaleReleaseId = partSaleReleaseId;
	}
	
}
