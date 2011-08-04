package com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author huangdj
 * @version 1.1
 * @created 2011-4-7
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PACKING_LIST_PART_ITEMS")
public class PackingListPartItems extends BaseEntity {
	/**
	 * 装箱单ID
	 */
	private String packingListID;

	/**
	 * 发货指令明细ID
	 */
	private String deliveryVanningItemsID;
	
	/**
	 * 项号
	 */
	private String itemNumber;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 件名称
	 */
	private String partName;

	/**
	 * 制造商
	 */
	private String manufacturer;

	/**
	 * 实发数量
	 */
	private double sendQty;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unit;

	/**
	 * 收料追溯号
	 */
	private String receivingSheetID;

	/**
	 * 随件证件
	 */
	private String certificateType;

	/**
	 * 分箱号
	 */
	private String boxID;

	/**
	 * 状态
	 */
	private String status;

	public PackingListPartItems() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PACKING_LIST_ID")
	public String getPackingListID() {
		return packingListID;
	}

	public void setPackingListID(String packingListID) {
		this.packingListID = packingListID;
	}

	@Column(name = "DELIVERY_VANNING_ITEMS_ID")
	public String getDeliveryVanningItemsID() {
		return deliveryVanningItemsID;
	}

	public void setDeliveryVanningItemsID(String deliveryVanningItemsID) {
		this.deliveryVanningItemsID = deliveryVanningItemsID;
	}

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
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

	@Transient
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Transient
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "SEND_QTY")
	public double getSendQty() {
		return sendQty;
	}

	public void setSendQty(double sendQty) {
		this.sendQty = sendQty;
	}
	
	@Enumerated(EnumType.STRING)
	@Transient
	public UnitOfMeasureCode getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasureCode unit) {
		this.unit = unit;
	}

	@Column(name = "RECEIVING_SHEET_ID")
	public String getReceivingSheetID() {
		return receivingSheetID;
	}

	public void setReceivingSheetID(String receivingSheetID) {
		this.receivingSheetID = receivingSheetID;
	}

	@Column(name = "CERTIFICATE_TYPE")
	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	@Column(name = "BOX_ID")
	public String getBoxID() {
		return boxID;
	}

	public void setBoxID(String boxID) {
		this.boxID = boxID;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}