package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
@ViewFormAnno(value="id")
public class DiscountItemForm extends PartIndex {
	
	private String discountItem;

	public String getDiscountItem() {
		return discountItem;
	}

	public void setDiscountItem(String discountItem) {
		this.discountItem = discountItem;
	}



	
	
}
