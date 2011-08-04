package com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StorageRacksTypeCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StorageSizeType;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_CARGO_SPACE")
public class CargoSpace extends BaseEntity {
	/**
	 * 操作类型(null:生成货位/setCargo:设置货位)
	 */
	private String operatorType;
	
	/**
	 * 货位ID
	 */
	private String[] cargoSpaceID;
	
	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 库房ID
	 */
	private String stockRoomID;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 库房中文名称
	 */
	private String stockRoomChineseName;

	/**
	 * 库房区域ID
	 */
	private String stockAreaID;

	/**
	 * 库房区域代码、所属区域
	 */
	private String stockAreaName;

	/**
	 * 逻辑库ID
	 */
	private String logicStockID;

	/**
	 * 逻辑库名称
	 */
	private String logicStockName;

	/**
	 * 货架排
	 */
	private String storageRacksRowCode;

	/**
	 * 货架形式
	 */
	private String storageRacksTypeCode;

	/**
	 * 列数
	 */
	private String storageRacksColumnCode;

	/**
	 * 层数
	 */
	private String storageRacksTierCode;

	/**
	 * 货位箱数
	 */
	private String storageRacksCaseCode;

	/**
	 * 货架编号
	 */
	private String storageRacksCode;

	/**
	 * 货架区域号
	 */
	private String storageRacksAreaCode;

	/**
	 * 货架号
	 */
	private String storageRacksNumber;
	
	/**
	 * 货位尺寸，长*宽*高
	 */
	private StorageSizeType storageSizeType;
	
	/**
	 * 货位承重
	 */
	private String storageWeight;
	
	/**
	 * 是否生成过货位(0:未生成/1:已经生成)
	 */
	private String createdFlg = "0";
	

	public CargoSpace() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Transient
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	@Transient
	public String[] getCargoSpaceID() {
		return cargoSpaceID;
	}

	public void setCargoSpaceID(String[] cargoSpaceID) {
		this.cargoSpaceID = cargoSpaceID;
	}

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "STOCK_ROOM_ID")
	public String getStockRoomID() {
		return stockRoomID;
	}

	public void setStockRoomID(String stockRoomID) {
		this.stockRoomID = stockRoomID;
	}

	@Transient
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Transient
	public String getStockRoomChineseName() {
		return stockRoomChineseName;
	}

	public void setStockRoomChineseName(String stockRoomChineseName) {
		this.stockRoomChineseName = stockRoomChineseName;
	}

	@Column(name = "STOCK_AREA_ID")
	public String getStockAreaID() {
		return stockAreaID;
	}

	public void setStockAreaID(String stockAreaID) {
		this.stockAreaID = stockAreaID;
	}

	@Transient
	public String getStockAreaName() {
		return stockAreaName;
	}

	public void setStockAreaName(String stockAreaName) {
		this.stockAreaName = stockAreaName;
	}

	@Column(name = "LOGIC_STOCK_ID")
	public String getLogicStockID() {
		return logicStockID;
	}

	public void setLogicStockID(String logicStockID) {
		this.logicStockID = logicStockID;
	}

	@Transient
	public String getLogicStockName() {
		return logicStockName;
	}

	public void setLogicStockName(String logicStockName) {
		this.logicStockName = logicStockName;
	}

	@Column(name = "STORAGE_RACKS_ROW_CODE")
	public String getStorageRacksRowCode() {
		return storageRacksRowCode;
	}

	public void setStorageRacksRowCode(String storageRacksRowCode) {
		this.storageRacksRowCode = storageRacksRowCode;
	}

	@Column(name = "STORAGE_RACKS_TYPE_CODE")
	public String getStorageRacksTypeCode() {
		return storageRacksTypeCode;
	}

	public void setStorageRacksTypeCode(String storageRacksTypeCode) {
		this.storageRacksTypeCode = storageRacksTypeCode;
	}

	@Column(name = "STORAGE_RACKS_COLUMN_CODE")
	public String getStorageRacksColumnCode() {
		return storageRacksColumnCode;
	}

	public void setStorageRacksColumnCode(String storageRacksColumnCode) {
		this.storageRacksColumnCode = storageRacksColumnCode;
	}

	@Column(name = "STORAGE_RACKS_TIER_CODE")
	public String getStorageRacksTierCode() {
		return storageRacksTierCode;
	}

	public void setStorageRacksTierCode(String storageRacksTierCode) {
		this.storageRacksTierCode = storageRacksTierCode;
	}

	@Column(name = "STORAGE_RACKS_CASE_CODE")
	public String getStorageRacksCaseCode() {
		return storageRacksCaseCode;
	}

	public void setStorageRacksCaseCode(String storageRacksCaseCode) {
		this.storageRacksCaseCode = storageRacksCaseCode;
	}

	@Transient
	public String getStorageRacksCode() {
		return storageRacksCode;
	}

	public void setStorageRacksCode(String storageRacksCode) {
		this.storageRacksCode = storageRacksCode;
	}

	@Transient
	public String getStorageRacksAreaCode() {
		return storageRacksAreaCode;
	}

	public void setStorageRacksAreaCode(String storageRacksAreaCode) {
		this.storageRacksAreaCode = storageRacksAreaCode;
	}

	@Transient
	public String getStorageRacksNumber() {
		return storageRacksNumber;
	}

	public void setStorageRacksNumber(String storageRacksNumber) {
		this.storageRacksNumber = storageRacksNumber;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STORAGESIZETYPE")
	public StorageSizeType getStorageSizeType() {
		return storageSizeType;
	}

	public void setStorageSizeType(StorageSizeType storageSizeType) {
		this.storageSizeType = storageSizeType;
	}

	@Column(name = "STORAGEWEIGHT")
	public String getStorageWeight() {
		return storageWeight;
	}

	public void setStorageWeight(String storageWeight) {
		this.storageWeight = storageWeight;
	}

	@Transient
	public String getCreatedFlg() {
		return createdFlg;
	}

	public void setCreatedFlg(String createdFlg) {
		this.createdFlg = createdFlg;
	}
}