package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;

@ViewFormAnno(value="id")
public class PartSaleReleaseForm extends PartSaleRelease {

	private String partIndexId;

	public String getPartIndexId() {
		return partIndexId;
	}

	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
}
