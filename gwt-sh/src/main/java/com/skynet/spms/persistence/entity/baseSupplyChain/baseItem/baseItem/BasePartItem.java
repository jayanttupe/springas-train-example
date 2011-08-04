package com.skynet.spms.persistence.entity.baseSupplyChain.baseItem.baseItem;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 基础备件明细项
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:34
 */
@MappedSuperclass
public class BasePartItem extends BaseEntity {
	
	/** 件号 **/
	private String partNumber;

	/** 备件描述 **/
	private String description;

	/** 数量 **/
	private Integer quantity;

	/** 备注 **/
	private String remarkText;
	
	/**单价**/
	private Float unitPrice;
	
	@Column(name = "UNIT_PRICE")
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

}