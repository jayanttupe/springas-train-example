package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.PartSupplierPriceIndex;

@ViewFormAnno(value="id")
public class PartSupplierPriceForm extends PartSupplierPriceIndex {

	private String partIndexId;
	
	private String supplierCodeId;

	public String getSupplierCodeId() {
		return supplierCodeId;
	}

	public void setSupplierCodeId(String supplierCodeId) {
		this.supplierCodeId = supplierCodeId;
	}

	public String getPartIndexId() {
		return partIndexId;
	}

	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
}
