package com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 补桓
 * @version 1.1
 * @created 2011-03-25
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_TEMPERATURE_HUMIDITY")
public class StockTemperatureAndHumidityRecord extends BaseEntity {
	/**
	 * 测量点位
	 */
	private String measurePoinTlocation;

	/**
	 * 记录结束日期
	 */
	private Date  recordEndDate;

	/**
	 * 记录仪编号
	 */
	private String recorderNumber;

	/**
	 * 记录开始日期
	 */
	private Date  recordStartDate;

	/**
	 * 仓库温湿度记录编号
	 */
	private String stockTemperatureAndHumidityRecordNumber;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件项号
	 */
	private String itemNumber;

	@Column(name = "MEASURE_POIN_TLOCATION")
	public String getMeasurePoinTlocation() {
		return measurePoinTlocation;
	}

	public void setMeasurePoinTlocation(String measurePoinTlocation) {
		this.measurePoinTlocation = measurePoinTlocation;
	}

	@Column(name = "RECORD_END_DATE")
	public Date getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	@Column(name = "RECORDER_NUMBER")
	public String getRecorderNumber() {
		return recorderNumber;
	}

	public void setRecorderNumber(String recorderNumber) {
		this.recorderNumber = recorderNumber;
	}

	@Column(name = "RECORD_START_DATE")
	public Date  getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(Date recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	@Column(name = "STOCK_NUMBER")
	public String getStockTemperatureAndHumidityRecordNumber() {
		return stockTemperatureAndHumidityRecordNumber;
	}

	public void setStockTemperatureAndHumidityRecordNumber(String stockTemperatureAndHumidityRecordNumber) {
		this.stockTemperatureAndHumidityRecordNumber = stockTemperatureAndHumidityRecordNumber;
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

}
