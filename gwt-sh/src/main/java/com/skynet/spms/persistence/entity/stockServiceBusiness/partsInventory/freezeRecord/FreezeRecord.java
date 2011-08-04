package com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.freezeRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_FREEZE_RECORD")
public class FreezeRecord extends BaseEntity {

	/**
	 * 库存记录ID
	 */
	private String partsInventoryID;

	/**
	 * 冻结自动解除日期
	 */
	private Date freezeAutoRemoveDate;

	/**
	 * 冻结数量
	 */
	private String freezeQuantity;

	/**
	 * 冻结原因
	 */
	private String freezeReasonCode;

	/**
	 * 冻结人
	 */
	private String freezeOperator;

	/**
	 * 冻结日期
	 */
	private Date freezeDate;

	/**
	 * 冻结用途
	 */
	
	private String freezeApplication;
	
	/**
	 * 无期限，直至人工解除
	 */
	private String infinitiRemove;

	/**
	 * 库存记录
	 */	
	private PartsInventoryRecord partsInventoryRecord;
	

	public FreezeRecord() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "PARTS_INVENTORY_ID")
	public String getPartsInventoryID() {
		return partsInventoryID;
	}

	public void setPartsInventoryID(String partsInventoryID) {
		this.partsInventoryID = partsInventoryID;
	}

	@Column(name = "FREEZE_AUTO_REMOVE_DATE")
	public Date getFreezeAutoRemoveDate() {
		return freezeAutoRemoveDate;
	}

	public void setFreezeAutoRemoveDate(Date freezeAutoRemoveDate) {
		this.freezeAutoRemoveDate = freezeAutoRemoveDate;
	}

	@Column(name = "FREEZE_QUANTITY")
	public String getFreezeQuantity() {
		return freezeQuantity;
	}

	public void setFreezeQuantity(String freezeQuantity) {
		this.freezeQuantity = freezeQuantity;
	}

	@Column(name = "FREEZE_REASON_CODE")
	public String getFreezeReasonCode() {
		return freezeReasonCode;
	}

	public void setFreezeReasonCode(String freezeReasonCode) {
		this.freezeReasonCode = freezeReasonCode;
	}

	@Column(name = "FREEZE_OPERATOR")
	public String getFreezeOperator() {
		return freezeOperator;
	}

	public void setFreezeOperator(String freezeOperator) {
		this.freezeOperator = freezeOperator;
	}

	@Column(name = "FREEZE_DATE")
	public Date getFreezeDate() {
		return freezeDate;
	}

	public void setFreezeDate(Date freezeDate) {
		this.freezeDate = freezeDate;
	}

	@Column(name = "FREEZE_APPLICATION")
	public String getFreezeApplication() {
		return freezeApplication;
	}

	public void setFreezeApplication(String freezeApplication) {
		this.freezeApplication = freezeApplication;
	}

	@Column(name = "INFINITI_REMOVE")
	public String getInfinitiRemove() {
		return infinitiRemove;
	}

	public void setInfinitiRemove(String infinitiRemove) {
		this.infinitiRemove = infinitiRemove;
	}

	@Transient
	public PartsInventoryRecord getPartsInventoryRecord() {
		return partsInventoryRecord;
	}

	public void setPartsInventoryRecord(PartsInventoryRecord partsInventoryRecord) {
		this.partsInventoryRecord = partsInventoryRecord;
	}
}