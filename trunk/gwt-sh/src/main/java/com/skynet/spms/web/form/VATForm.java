package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.VAT;

@ViewFormAnno(value="id")
public class VATForm extends VAT{
	private String enterpriseId;

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}
