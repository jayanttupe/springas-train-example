package com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial;

/**
 * @author huangdj
 * @version 1.1
 * @created 2011-4-7
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_PACKING_LIST_BOX_ITEMS")
public class PackingListBoxItems extends BaseEntity {
	/**
	 * 装箱单ID
	 */
	private String packingListID;
	
	/**
	 * 项号
	 */
	private String itemNumber;

	/**
	 * 箱号
	 */
	private String boxNumber;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 包装材质
	 */
	private PackagingMaterial packagingCode;

	/**
	 * 装箱人
	 */
	private String packingBy;

	/**
	 * 件数
	 */
	private String billOfLadingContainerCount;

	/**
	 * RFID标签唯一编号
	 */
	private String packingRFIDTagUUID;
	
	/**
	 * 备注
	 */
	private String memo;

	public PackingListBoxItems() {

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

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "BOX_NUMBER")
	public String getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_CODE")
	public PackagingMaterial getPackagingCode() {
		return packagingCode;
	}

	public void setPackagingCode(PackagingMaterial packagingCode) {
		this.packagingCode = packagingCode;
	}

	@Column(name = "PACKING_BY")
	public String getPackingBy() {
		return packingBy;
	}

	public void setPackingBy(String packingBy) {
		this.packingBy = packingBy;
	}

	@Column(name = "BILL_OF_LADING_CONTAINER_COUNT")
	public String getBillOfLadingContainerCount() {
		return billOfLadingContainerCount;
	}

	public void setBillOfLadingContainerCount(String billOfLadingContainerCount) {
		this.billOfLadingContainerCount = billOfLadingContainerCount;
	}

	@Column(name = "PACKING_RFID_TAG_UUID")
	public String getPackingRFIDTagUUID() {
		return packingRFIDTagUUID;
	}

	public void setPackingRFIDTagUUID(String packingRFIDTagUUID) {
		this.packingRFIDTagUUID = packingRFIDTagUUID;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}