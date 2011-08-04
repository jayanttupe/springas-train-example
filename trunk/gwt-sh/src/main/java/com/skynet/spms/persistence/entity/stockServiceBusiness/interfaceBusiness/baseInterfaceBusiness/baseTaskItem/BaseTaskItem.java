package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.taskItemActionRecord.TaskItemActionRecord;

/**
 * @author 朱江
 * @version 1.0
 * @created 15-三月-2011 12:33:12
 */
@MappedSuperclass
public class BaseTaskItem extends BaseEntity{
	
	/**
	 * ATA章节号
	 */
	private String ATA;
	/**
	 * 推荐架位编号
	 */
	private String cargoSpaceNumber;
	/**
	 * 证书号码
	 */
	private String cerificateNumber;
	/**
	 * 当前部件状态
	 */
	private String currentStatus;
	/**
	 * 到期日期
	 */
	private Date expireDate;
	/**
	 * 国际商品编码
	 */
	private String internationalCommodityCode;
	/**
	 * 是或否危险商品
	 */
	private String isDangerous;
	/**
	 * 禁售标示
	 */
	private String isSaleForbidden;
	/**
	 * 任务项号
	 */
	private String itemNo;
	/**
	 * 批号
	 */
	private String lotNumber;
	/**
	 * 制造日期
	 */
	private Date manufacturDate;
	/**
	 * 厂家代码
	 */
	private String manufacturerCode;
	/**
	 * 零部件描述
	 */
	private String partDescribe;
	/**
	 * 当前件号
	 */
	private String partNumber;
	/**
	 * 原始件号
	 */
	private String partNumberOriginal;
	/**
	 * 序号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 上架时推荐库房,捡货时的指定库房
	 */
	private String stockRoomNumber;
	/**
	 * 任务号
	 */
	private String taskNo;
	/**
	 * 单位
	 */
	private String unit;
	
	private String m_TaskItemActionRecord;
	
	@Column(name = "ATA")
	public String getATA() {
		return ATA;
	}
	public void setATA(String aTA) {
		ATA = aTA;
	}
	
	@Column(name = "CARGOSPACE_NUM")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}
	
	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}
	
	@Column(name = "CERIFICATE_NUM")
	public String getCerificateNumber() {
		return cerificateNumber;
	}
	public void setCerificateNumber(String cerificateNumber) {
		this.cerificateNumber = cerificateNumber;
	}
	
	@Column(name = "CURRENT_STATUS")
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@Column(name = "INTER_COMMODITY_CODE")
	public String getInternationalCommodityCode() {
		return internationalCommodityCode;
	}
	public void setInternationalCommodityCode(String internationalCommodityCode) {
		this.internationalCommodityCode = internationalCommodityCode;
	}
	
	@Column(name = "IS_DANGEROUS")
	public String getIsDangerous() {
		return isDangerous;
	}
	public void setIsDangerous(String isDangerous) {
		this.isDangerous = isDangerous;
	}
	@Column(name = "IS_SALE_FORBIDDEN")
	public String getIsSaleForbidden() {
		return isSaleForbidden;
	}
	public void setIsSaleForbidden(String isSaleForbidden) {
		this.isSaleForbidden = isSaleForbidden;
	}
	
	@Column(name = "ITEMNO")
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	
	@Column(name = "LOTNUMBER")
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	
	@Column(name = "MANU_FACTURDATE")
	public Date getManufacturDate() {
		return manufacturDate;
	}
	public void setManufacturDate(Date manufacturDate) {
		this.manufacturDate = manufacturDate;
	}
	
	@Column(name = "MANUFACTURER_CODE")
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	
	@Column(name = "PART_DESCRIBE")
	public String getPartDescribe() {
		return partDescribe;
	}
	public void setPartDescribe(String partDescribe) {
		this.partDescribe = partDescribe;
	}
	
	@Column(name = "PARTNUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	@Column(name = "PARTNUMBER_ORIGINAL")
	public String getPartNumberOriginal() {
		return partNumberOriginal;
	}
	public void setPartNumberOriginal(String partNumberOriginal) {
		this.partNumberOriginal = partNumberOriginal;
	}
	
	@Column(name = "PARTSERIAL_NUMBER")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}
	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}
	
	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "STOCKROOMNUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}
	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}
	
	@Column(name = "TASKNO")
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@Column(name = "UNITS")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "M_TASKITEM_ACT_RECORD")
	public String getM_TaskItemActionRecord() {
		return m_TaskItemActionRecord;
	}
	public void setM_TaskItemActionRecord(String m_TaskItemActionRecord) {
		this.m_TaskItemActionRecord = m_TaskItemActionRecord;
	}

	public BaseTaskItem() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}