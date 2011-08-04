package com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * @author 
 * @version 1.1
 * @created 
 */
@ViewFormAnno(value="id")
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Table(name = "SPMS_PICKING_LIST_PART_ITEMS")
public class PickingListPartItems extends BaseEntity {

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 件名称
	 */
	private String partName;

	/**
	 * 发货指令明细ID
	 */
	private String deliveryVanningItemsID;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 制造商
	 */
	private String manufacturer;

	/**
	 * 应发数量
	 */
	private String qty;

	/**
	 * 实发数量
	 */
	private double sendQty;

	/**
	 * 当前库存数量
	 */
	private String inStockQty;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unit;

	/**
	 * 备件类型
	 */
	private SparePartClassCode partType;

	/**
	 * 备件状况
	 */
	private String partStatus;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 剩余寿命
	 */
	private String life;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 配料单号
	 */
	private String pickingListID;

	public PickingListPartItems() {

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

	@Column(name = "PART_NAME")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "DELIVERY_VANNING_ITEMS_ID")
	public String getDeliveryVanningItemsID() {
		return deliveryVanningItemsID;
	}

	public void setDeliveryVanningItemsID(String deliveryVanningItemsID) {
		this.deliveryVanningItemsID = deliveryVanningItemsID;
	}

	@Column(name = "PART_SERIAL_NUMBER")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "QTY")
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	@Column(name = "SEND_QTY")
	public double getSendQty() {
		return sendQty;
	}

	public void setSendQty(double sendQty) {
		this.sendQty = sendQty;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT")
	public UnitOfMeasureCode getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasureCode unit) {
		this.unit = unit;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PART_TYPE")
	public SparePartClassCode getPartType() {
		return partType;
	}

	public void setPartType(SparePartClassCode partType) {
		this.partType = partType;
	}

	@Transient
	public String getInStockQty() {
		return inStockQty;
	}

	public void setInStockQty(String inStockQty) {
		this.inStockQty = inStockQty;
	}

	@Column(name = "PART_STATUS")
	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "LIFE")
	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "PICKINGLIST_ID")
	public String getPickingListID() {
		return pickingListID;
	}

	public void setPickingListID(String pickingListID) {
		this.pickingListID = pickingListID;
	}
}