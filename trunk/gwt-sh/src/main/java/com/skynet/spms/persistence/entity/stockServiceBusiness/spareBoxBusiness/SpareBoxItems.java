package com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author FANYX
 * @version 1.0
 * @created 09-五月-2011 11:05:32
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_SPARE_BOX_ITEMS")
public class SpareBoxItems extends BaseEntity{

	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 序号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 备件项编号
	 */
	private String spareBoxItemsNumber;
	/**
	 * 航材包编号
	 */
	private String spareBoxNumber;
	
	@Column(name = "PART_NUMBER") 
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	@Column(name = "PART_SERIAL_NUMBER")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}
	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}
	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name = "SPARE_BOX_ITEMS") 
	public String getSpareBoxItemsNumber() {
		return spareBoxItemsNumber;
	}
	public void setSpareBoxItemsNumber(String spareBoxItemsNumber) {
		this.spareBoxItemsNumber = spareBoxItemsNumber;
	}
	@Column(name = "SPARE_BOX_NUMBER") 
	public String getSpareBoxNumber() {
		return spareBoxNumber;
	}
	public void setSpareBoxNumber(String spareBoxNumber) {
		this.spareBoxNumber = spareBoxNumber;
	}
	
	

}