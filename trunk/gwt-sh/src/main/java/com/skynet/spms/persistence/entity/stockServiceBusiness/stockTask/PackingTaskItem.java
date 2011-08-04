package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PACKING_TASK_ITEM")
public class PackingTaskItem extends BaseStockTaskItem{

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;
	
	/**
	 * 分箱号
	 */
	private String boxNO;
	
	/**
	 * 实发数量
	 */
	private double sendQty;
	
	/**
	 * RFID标签唯一编号
	 */
	private String packingRFIDTagUUID;
	
	/**
	 * 包装材质
	 */
	private PackagingMaterial packagingMaterial;

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "BOX_NO")
	public String getBoxNO() {
		return boxNO;
	}

	public void setBoxNO(String boxNO) {
		this.boxNO = boxNO;
	}

	@Column(name = "SEND_QTY")
	public double getSendQty() {
		return sendQty;
	}

	public void setSendQty(double sendQty) {
		this.sendQty = sendQty;
	}

	@Column(name = "PACKING_RFID_TAG_UUID")
	public String getPackingRFIDTagUUID() {
		return packingRFIDTagUUID;
	}

	public void setPackingRFIDTagUUID(String packingRFIDTagUUID) {
		this.packingRFIDTagUUID = packingRFIDTagUUID;
	}

	@Column(name = "PACKAGING_MATERIAL")
	public PackagingMaterial getPackagingMaterial() {
		return packagingMaterial;
	}

	public void setPackagingMaterial(PackagingMaterial packagingMaterial) {
		this.packagingMaterial = packagingMaterial;
	}
}