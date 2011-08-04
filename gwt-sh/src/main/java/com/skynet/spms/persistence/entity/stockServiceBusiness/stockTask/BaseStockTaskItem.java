package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.webservice.WebServiceUtils;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.webservice.entity.BasePartTaskItem;
import com.skynet.spms.webservice.entity.StockCountTaskItem;

/**
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BaseStockTaskItem extends BaseEntity {
	
	
	/**
	 * 任务ID
	 */
	private String taskID;
	
	/**
	 * 条码标签唯一编号
	 */
	private String barcodeTagUUID;
	
	/**
	 * RFID标签唯一序列号
	 */
	private String tagIdentifierCode;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 批次号
	 */
	private String partSerialNumber;

	/**
	 * 件名称
	 */
	private String partName;

	/**
	 * 计量单位
	 */
	private String partUnit;

	/**
	 * 数量
	 */
	private double quantity;
	
	/**
	 * 后处理建议
	 */
	private String postaction;

	/**
	 * 操作状态(waiting:待处理/succee:成功/failure:失败)
	 */
	private TaskItemStatus operationStatus;

	public BaseStockTaskItem() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "TASK_ID")
	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Column(name = "BARCODE_TAG_UUID")
	public String getBarcodeTagUUID() {
		return barcodeTagUUID;
	}

	public void setBarcodeTagUUID(String barcodeTagUUID) {
		this.barcodeTagUUID = barcodeTagUUID;
	}

	@Column(name = "TAG_IDENTIFIER_CODE")
	public String getTagIdentifierCode() {
		return tagIdentifierCode;
	}

	public void setTagIdentifierCode(String tagIdentifierCode) {
		this.tagIdentifierCode = tagIdentifierCode;
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
	public String getPartUnit() {
		return partUnit;
	}

	public void setPartUnit(String partUnit) {
		this.partUnit = partUnit;
	}

	@Column(name = "QUANTITY")
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "POSTACTION")
	public String getPostaction() {
		return postaction;
	}

	public void setPostaction(String postaction) {
		this.postaction = postaction;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "OPERATION_STATUS")
	public TaskItemStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(TaskItemStatus operationStatus) {
		this.operationStatus = operationStatus;
	}
	
	@Transient
	protected void fillTaskItem(BasePartTaskItem baseItem){
		
		baseItem.setBarcodeTagUUID(getBarcodeTagUUID());
		baseItem.setPartNumber(getPartNumber());
		baseItem.setPartSerialNumber(getPartSerialNumber());
		baseItem.setQuantity(getQuantity());
		baseItem.setPartName(getPartName());
		baseItem.setPostaction(getPostaction());
		baseItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(this.getOperationStatus()));

		return;
	}
	
	protected void updateItemWithWsItem(BasePartTaskItem baseItem){
		
		this.setPartNumber(baseItem.getPartNumber());
		this.setPartName(baseItem.getPartName());
		this.setPostaction(baseItem.getPostaction());
		this.setPartSerialNumber(baseItem.getPartSerialNumber());		
		this.setQuantity(baseItem.getQuantity());
		this.setOperationStatus(WebServiceUtils.convertXmlTaskItemStatus(baseItem.getOperationStatus()));

	}
}