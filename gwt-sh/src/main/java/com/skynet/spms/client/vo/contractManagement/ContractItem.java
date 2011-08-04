package com.skynet.spms.client.vo.contractManagement;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 合同明细
 * 
 * @author tqc
 * 
 */
public class ContractItem implements IsSerializable {

	private String id;
	/** 件号 * */
	private String partNumber;
	/** 数量 * */
	private Integer quantity;
	/** 单价 * */
	private Float unitPriceAmount;
	/** 合计* */
	private Float totalAmount;
	/**单位**/
	private String unit;
	/**币种**/
	private String currency;

	public ContractItem() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	

	public Float getUnitPriceAmount() {
		return unitPriceAmount;
	}

	public void setUnitPriceAmount(Float unitPriceAmount) {
		this.unitPriceAmount = unitPriceAmount;
	}
	
	public String getUnit() {
		return unit;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
