package com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import java.util.Date;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-25
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCKTEMHURITEM")
public class StockTemperatureAndHumidityItem extends BaseEntity {
	
	/**
	 * 温湿度记录编号
	 */
	private String stockTemperatureAndHumidityRecordNumber;

	/**
	 * 记录日期时间
	 */
	private Date recordDateTime;

	/**
	 * 记录序号
	 */
	private String recordSerialNumber;

	/**
	 * 温度
	 */
	private String temperature;

	/**
	 * 湿度
	 */
	private String humidity;

	/**
	 * 温度警告
	 */
	private String temperatureAlarm;
	
	/**
	 * 湿度警告
	 */
	private String humidityAlarm;

	/**
	 * 温度单位
	 */
	private String temperatureUnit;

	@Column(name = "STOCK_NUMBER")
	public String getStockTemperatureAndHumidityRecordNumber() {
		return stockTemperatureAndHumidityRecordNumber;
	}

	public void setStockTemperatureAndHumidityRecordNumber(String stockTemperatureAndHumidityRecordNumber) {
		this.stockTemperatureAndHumidityRecordNumber = stockTemperatureAndHumidityRecordNumber;
	}

	@Column(name = "RECORD_DATE_TIME")
	public Date getRecordDateTime() {
		return recordDateTime;
	}

	public void setRecordDateTime(Date recordDateTime) {
		this.recordDateTime = recordDateTime;
	}

	@Column(name = "RECORD_SERIAL_NUMBER")
	public String getRecordSerialNumber() {
		return recordSerialNumber;
	}

	public void setRecordSerialNumber(String recordSerialNumber) {
		this.recordSerialNumber = recordSerialNumber;
	}

	@Column(name = "TEMPERATURE")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	@Column(name = "HUMIDITY")
	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	@Column(name = "TEMPERATURE_ALARM")
	public String getTemperatureAlarm() {
		return temperatureAlarm;
	}

	public void setTemperatureAlarm(String temperatureAlarm) {
		this.temperatureAlarm = temperatureAlarm;
	}

	@Column(name = "HUMIDITY_ALARM")
	public String getHumidityAlarm() {
		return humidityAlarm;
	}

	public void setHumidityAlarm(String humidityAlarm) {
		this.humidityAlarm = humidityAlarm;
	}

	@Column(name = "TEMPERATURE_UNIT")
	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

}
