package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author huangdj
 * @version 1.0
 * @created 24-三月-2011 15:29:21
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_RECEIVING_SHEET_ITEMS")
public class ReceivingSheetItems extends BaseEntity {
	/**
	 * 收料单ID
	 */
	private String receivingSheetID;
	
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
	 * 名称
	 */
	private String partName;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode partUnit;

	/**
	 * 备件类型
	 */
	private SparePartClassCode partType;

	/**
	 * 分箱号
	 */
	private String boxNO;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 是否已检验(0:未检验/1:已检验)
	 */
	private String isCheck = "0";
	
	/**
	 * 是否序号控制
	 */
	private boolean isSerial;
	
	/**
	 * 装箱单号
	 */
	private String packingListNumber;

	public ReceivingSheetItems() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "RECEIVING_SHEET_ID")
	public String getReceivingSheetID() {
		return receivingSheetID;
	}

	public void setReceivingSheetID(String receivingSheetID) {
		this.receivingSheetID = receivingSheetID;
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

	@Column(name = "PART_NAME")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Transient
	public UnitOfMeasureCode getPartUnit() {
		return partUnit;
	}

	public void setPartUnit(UnitOfMeasureCode partUnit) {
		this.partUnit = partUnit;
	}

	@Transient
	public SparePartClassCode getPartType() {
		return partType;
	}

	public void setPartType(SparePartClassCode partType) {
		this.partType = partType;
	}

	@Column(name = "BOX_NO")
	public String getBoxNO() {
		return boxNO;
	}

	public void setBoxNO(String boxNO) {
		this.boxNO = boxNO;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IS_CHECK")
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	@Transient
	public boolean getIsSerial() {
		return isSerial;
	}

	public void setIsSerial(boolean isSerial) {
		this.isSerial = isSerial;
	}

	@Column(name = "PACKING_LIST_NUMBER")
	public String getPackingListNumber() {
		return packingListNumber;
	}

	public void setPackingListNumber(String packingListNumber) {
		this.packingListNumber = packingListNumber;
	}
}