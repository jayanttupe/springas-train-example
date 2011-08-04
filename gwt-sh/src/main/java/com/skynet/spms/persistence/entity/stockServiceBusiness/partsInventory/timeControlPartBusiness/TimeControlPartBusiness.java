package com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.timeControlPartBusiness;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-3-31
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_TEST")
public class TimeControlPartBusiness extends BaseEntity {
	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批次
	 */
	private String partSerialNumber;

	/**
	 * 制造商
	 */
	private String manufacturerCode;

	/**
	 * 关键字
	 */
	private String keyCode;

	/**
	 * 数量
	 */
	private String quantity;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unitOfMeasure;

	/**
	 * 备件状况
	 */
	private String partStatusCode;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 出厂日期
	 */
	private Date manufactureDate;

	/**
	 * 购买日期
	 */
	private Date buyDate;

	/**
	 * 到限日期
	 */
	private Date timeControlTaskEndDate;

	/**
	 * 周期(月)
	 */
	private String timeControlTaskCycle;

	/**
	 * 恢复代码
	 */
	private String renewalProcedureCode;

	/**
	 * 检测方法
	 */
	private String periodicCheckCode;
	
	/**
	 * 本次工作日期
	 */
	private Date thisWorkDate;
	
	/**
	 * 下次到期日期
	 */
	private Date theNextDueDate;
	
	/**
	 * 完成工作说明
	 */
	private String finishWorkInstructions;
	
	public TimeControlPartBusiness() {

	}

	public void finalize() throws Throwable {
		super.finalize();
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

	@Column(name = "MANUFACTURER_CODE")
	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	@Column(name = "KEY_CODE")
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_OF_MEASURE")
	public UnitOfMeasureCode getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasureCode unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Column(name = "PART_STATUS_CODE")
	public String getPartStatusCode() {
		return partStatusCode;
	}

	public void setPartStatusCode(String partStatusCode) {
		this.partStatusCode = partStatusCode;
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

	@Column(name = "MANUFACTURE_DATE")
	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	@Column(name = "BUY_DATE")
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "TIME_CONTROL_TASK_END_DATE")
	public Date getTimeControlTaskEndDate() {
		return timeControlTaskEndDate;
	}

	public void setTimeControlTaskEndDate(Date timeControlTaskEndDate) {
		this.timeControlTaskEndDate = timeControlTaskEndDate;
	}

	@Column(name = "TIME_CONTROL_TASK_CYCLE")
	public String getTimeControlTaskCycle() {
		return timeControlTaskCycle;
	}

	public void setTimeControlTaskCycle(String timeControlTaskCycle) {
		this.timeControlTaskCycle = timeControlTaskCycle;
	}

	@Column(name = "RENEWAL_PROCEDURE_CODE")
	public String getRenewalProcedureCode() {
		return renewalProcedureCode;
	}

	public void setRenewalProcedureCode(String renewalProcedureCode) {
		this.renewalProcedureCode = renewalProcedureCode;
	}

	@Column(name = "PERIODIC_CHECK_CODE")
	public String getPeriodicCheckCode() {
		return periodicCheckCode;
	}

	public void setPeriodicCheckCode(String periodicCheckCode) {
		this.periodicCheckCode = periodicCheckCode;
	}
	
	@Column(name = "THIS_WORK_DATE")
	public Date getThisWorkDate() {
		return thisWorkDate;
	}

	public void setThisWorkDate(Date thisWorkDate) {
		this.thisWorkDate = thisWorkDate;
	}
	
	@Column(name = "THE_NEXT_DUE_DATE")
	public Date getTheNextDueDate() {
		return theNextDueDate;
	}

	public void setTheNextDueDate(Date theNextDueDate) {
		this.theNextDueDate = theNextDueDate;
	}
	
	@Column(name = "FINISH_WORK_INSTRUCTIONS")
	public String getFinishWorkInstructions() {
		return finishWorkInstructions;
	}

	public void setFinishWorkInstructions(String finishWorkInstructions) {
		this.finishWorkInstructions = finishWorkInstructions;
	}

}