package com.skynet.spms.web.form;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.RecipeInvoiceAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@ViewFormAnno(value="id")
public class AddressForm extends BaseIDEntity{
	
	private String rqId;

	private ShippingAddress shippingAddress;

	private RecipeInvoiceAddress recipeInvoiceAddress;

	private ConsigneeAddress consigneeAddress;
	
	private Integer businessType=0;//业务类别
	
	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getRqId() {
		return rqId;
	}

	public void setRqId(String rqId) {
		this.rqId = rqId;
	}


	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public RecipeInvoiceAddress getRecipeInvoiceAddress() {
		return recipeInvoiceAddress;
	}

	public void setRecipeInvoiceAddress(
			RecipeInvoiceAddress recipeInvoiceAddress) {
		this.recipeInvoiceAddress = recipeInvoiceAddress;
	}

	public ConsigneeAddress getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(ConsigneeAddress consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

}
