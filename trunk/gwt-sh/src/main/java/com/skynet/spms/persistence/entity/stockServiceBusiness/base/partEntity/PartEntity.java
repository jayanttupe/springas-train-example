package com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 备件实体----凡是进入过备件系统中的所有件，
 * 件号，序号/批号，唯一标识一个备件的实体，
 * @author skynet189
 * @version 1.0
 * @created 13-六月-2011 11:08:58
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_PART_ENTITY")
public class PartEntity extends BaseEntity {

	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 部件序号/批号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 条码标签唯一编号
	 */
	private String barcodeTagUUID;
	/**
	 * RFID标签唯一编号
	 */
	private String rFIDTagUUID;

	public PartEntity(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

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
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "BARCODE_TAG_UUID")
	public String getBarcodeTagUUID() {
		return barcodeTagUUID;
	}

	public void setBarcodeTagUUID(String barcodeTagUUID) {
		this.barcodeTagUUID = barcodeTagUUID;
	}

	@Column(name = "RFID_TAG_UUID")
	public String getrFIDTagUUID() {
		return rFIDTagUUID;
	}

	public void setrFIDTagUUID(String rFIDTagUUID) {
		this.rFIDTagUUID = rFIDTagUUID;
	}
}