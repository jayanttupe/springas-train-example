package com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.TimeAndLifePartStatus;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PARTS_INVENTORY_RECORD")
public class PartsInventoryRecord extends BaseEntity {
	/**
	 * 入库记录号
	 */
	private String inStockRecordNumber;

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
	 * 接收检验登记单号
	 */
	private String checkAndAcceptSheetNumber;

	/**
	 * 库存结存数量
	 */
	private int balanceQuantity;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unit;

	/**
	 * 是否冻结(0:未冻结/1:已冻结)
	 */
	private String isFreeze = "0";

	/**
	 * 是否时控件
	 */
	private String isTimePart;

	/**
	 * 时控件到限日期
	 */
	private Date limitDate;

	/**
	 * 是否寿命件
	 */
	private String isLifePart;
	
	/**
	 * 寿命期 
	 */
	private String usefulLifePeriod;

	/**
	 * 时寿件到限日期
	 */
	private Date lifeDate;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 备件状况
	 */
	private String partStatus;

	/**
	 * 入库日期
	 */
	private Date inStockDate;

	/**
	 * 入库员
	 */
	private String inStockOperator;

	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 检验员 
	 */
	private String inspector;
	
	/**
	 * 检验日期 
	 */
	private Date inspectionDate;
	
	/**
	 * 供货单位 
	 */
	private String supplyUnit;
	
	/**
	 * 合同编号
	 */
	private String contratNumber;

	/**
	 * 区域
	 */
	private String stockAreaNumber;
	
	/**
	 * 航材类型代码
	 */
	private String sparePartClassCode;
	
	/**
	 * 备件中心位置代码
	 */
	private String partCentreLocation;
	
	/**
	 * 制造商代码
	 */
	private String manufacturerCode;
	
	/**
	 * 出厂日期 
	 */
	private Date buildDate;
	
	/**
	 * 收料单编号 
	 */
	private String receivingNumber;
	
	/**
	 * 条码标签唯一编号
	 */
	private String barcodeTagUUID;
	
	/**
	 * RFID标签唯一编号
	 */
	private String rFIDTagUUID;
	
	/**
	 * 时控件状态
	 */
	private TimeAndLifePartStatus timeControlStatus = TimeAndLifePartStatus.Monitoring;
	
	/**
	 * 时寿件状态
	 */
	private TimeAndLifePartStatus lifePartStatus = TimeAndLifePartStatus.Monitoring;
	
	public String getSparePartClassCode() {
		return sparePartClassCode;
	}

	public void setSparePartClassCode(String sparePartClassCode) {
		this.sparePartClassCode = sparePartClassCode;
	}

	public String getPartCentreLocation() {
		return partCentreLocation;
	}

	public void setPartCentreLocation(String partCentreLocation) {
		this.partCentreLocation = partCentreLocation;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	@Column (name= "STOCK_AREA_NUMBER")
	public String getStockAreaNumber() {
		return stockAreaNumber;
	}

	public void setStockAreaNumber(String stockAreaNumber) {
		this.stockAreaNumber = stockAreaNumber;
	}

	public PartsInventoryRecord() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "IN_STOCK_RECORD_NUMBER")
	public String getInStockRecordNumber() {
		return inStockRecordNumber;
	}

	public void setInStockRecordNumber(String inStockRecordNumber) {
		this.inStockRecordNumber = inStockRecordNumber;
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

	@Column(name = "CHECK_AND_ACCEPT_SHEET_NUMBER")
	public String getCheckAndAcceptSheetNumber() {
		return checkAndAcceptSheetNumber;
	}

	public void setCheckAndAcceptSheetNumber(String checkAndAcceptSheetNumber) {
		this.checkAndAcceptSheetNumber = checkAndAcceptSheetNumber;
	}

	@Column(name = "BALANCE_QUANTITY")
	public int getBalanceQuantity() {
		return balanceQuantity;
	}

	public void setBalanceQuantity(int balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT")
	public UnitOfMeasureCode getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasureCode unit) {
		this.unit = unit;
	}

	@Column(name = "IS_FREEZE")
	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Column(name = "IS_TIME_PART")
	public String getIsTimePart() {
		return isTimePart;
	}

	public void setIsTimePart(String isTimePart) {
		this.isTimePart = isTimePart;
	}

	@Column(name = "LIMIT_DATE")
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	@Column(name = "IS_LIFE_PART")
	public String getIsLifePart() {
		return isLifePart;
	}

	public void setIsLifePart(String isLifePart) {
		this.isLifePart = isLifePart;
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

	@Column(name = "PART_STATUS")
	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	@Column(name = "IN_STOCK_DATE")
	public Date getInStockDate() {
		return inStockDate;
	}

	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}

	@Column(name = "IN_STOCK_OPERATOR")
	public String getInStockOperator() {
		return inStockOperator;
	}

	public void setInStockOperator(String inStockOperator) {
		this.inStockOperator = inStockOperator;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "INSPECTOR")
	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	@Column(name = "IN_SPECTION_DATE")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Column(name = "SUPPLY_UNIT") 
	public String getSupplyUnit() {
		return supplyUnit;
	}

	public void setSupplyUnit(String supplyUnit) {
		this.supplyUnit = supplyUnit;
	}

	@Column(name = "USEFUL_LIFE_PERIOD")
	public String getUsefulLifePeriod() {
		return usefulLifePeriod;
	}

	public void setUsefulLifePeriod(String usefulLifePeriod) {
		this.usefulLifePeriod = usefulLifePeriod;
	}
	
	@Column(name = "LIFE_DATE")
	public Date getLifeDate() {
		return lifeDate;
	}

	public void setLifeDate(Date lifeDate) {
		this.lifeDate = lifeDate;
	}

	@Column(name = "CONTRAT_NUMBER")
	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}

	@Column(name = "BUILD_DATE") 
	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	@Column(name = "RECEIVING_NUMBER") 
	public String getReceivingNumber() {
		return receivingNumber;
	}

	public void setReceivingNumber(String receivingNumber) {
		this.receivingNumber = receivingNumber;
	}

	@Transient
	public String getBarcodeTagUUID() {
		return barcodeTagUUID;
	}

	public void setBarcodeTagUUID(String barcodeTagUUID) {
		this.barcodeTagUUID = barcodeTagUUID;
	}

	@Transient
	public String getrFIDTagUUID() {
		return rFIDTagUUID;
	}

	public void setrFIDTagUUID(String rFIDTagUUID) {
		this.rFIDTagUUID = rFIDTagUUID;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TIME_CONTROL_STATUS")
	public TimeAndLifePartStatus getTimeControlStatus() {
		return timeControlStatus;
	}

	public void setTimeControlStatus(TimeAndLifePartStatus timeControlStatus) {
		this.timeControlStatus = timeControlStatus;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "LIFE_PART_STATUS")
	public TimeAndLifePartStatus getLifePartStatus() {
		return lifePartStatus;
	}

	public void setLifePartStatus(TimeAndLifePartStatus lifePartStatus) {
		this.lifePartStatus = lifePartStatus;
	}
	
}