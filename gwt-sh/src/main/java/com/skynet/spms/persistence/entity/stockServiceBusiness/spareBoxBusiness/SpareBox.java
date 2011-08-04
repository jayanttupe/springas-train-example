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
@Table(name = "SPMS_SPARE_BOX")
public class SpareBox extends BaseEntity{
	
	/**
	 * 航材包货位号
	 */
	private String spareBoxCargoSpaceNumber;
	/**
	 * 航材包编号
	 */
	private String spareBoxNumber;
	/**
	 * 航材包件号
	 */
	private String spareBoxPartNumber;
	/**
	 * 航材包序号/批次号
	 */
	private String spareBoxSerialNumber;
	
	@Column(name = "SPARE_BOX_CARGO_NUMBER") 
	public String getSpareBoxCargoSpaceNumber() {
		return spareBoxCargoSpaceNumber;
	}
	public void setSpareBoxCargoSpaceNumber(String spareBoxCargoSpaceNumber) {
		this.spareBoxCargoSpaceNumber = spareBoxCargoSpaceNumber;
	}
	@Column(name = "SPARE_BOX_NUMBER")
	public String getSpareBoxNumber() {
		return spareBoxNumber;
	}
	public void setSpareBoxNumber(String spareBoxNumber) {
		this.spareBoxNumber = spareBoxNumber;
	}
	@Column(name = "SPARE_BOX_PART_NUMBER")
	public String getSpareBoxPartNumber() {
		return spareBoxPartNumber;
	}
	public void setSpareBoxPartNumber(String spareBoxPartNumber) {
		this.spareBoxPartNumber = spareBoxPartNumber;
	}
	@Column(name = "SPARE_BOX_SERIAL_NUMBER")
	public String getSpareBoxSerialNumber() {
		return spareBoxSerialNumber;
	}
	public void setSpareBoxSerialNumber(String spareBoxSerialNumber) {
		this.spareBoxSerialNumber = spareBoxSerialNumber;
	}
}