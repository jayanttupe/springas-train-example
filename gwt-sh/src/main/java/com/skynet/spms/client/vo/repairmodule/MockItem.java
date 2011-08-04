package com.skynet.spms.client.vo.repairmodule;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MockItem implements IsSerializable {

	private String itemDescription;
	private Float quantity;
	private Float unitOfPrice;
	private Float amount;
	private String remarkText;
	public String internationalCurrencyCode;

	public MockItem() {
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getUnitOfPrice() {
		return unitOfPrice;
	}

	public void setUnitOfPrice(Float unitOfPrice) {
		this.unitOfPrice = unitOfPrice;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	public String getInternationalCurrencyCode() {
		return internationalCurrencyCode;
	}

	public void setInternationalCurrencyCode(String internationalCurrencyCode) {
		this.internationalCurrencyCode = internationalCurrencyCode;
	}

}
