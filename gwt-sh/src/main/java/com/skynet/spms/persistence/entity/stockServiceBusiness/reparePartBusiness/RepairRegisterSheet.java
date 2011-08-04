package com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 待修登记单
 * 
 * @author tqc
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */

@Entity
@Table(name = "SPMS_REPAIRREGSHEET")
public class RepairRegisterSheet extends BaseEntity {

	private String cargoSpaceNumber;
	private String currency;
	private String customerName;
	private String keyCode;
	private String manufacturer;
	private int number;
	private String partNumber;
	private String planeNumber;
	private String planeType;
	private String priority;
	private String recieveRecorder;
	private float referencePrice;
	private Date registerDate;
	private String registerUser;
	private String remark;
	private String repairContractNumber;
	private String repairRegisterSheetNumber;
	private String serialNumber;
	private String unit;
	private String wareHouse;
	private String waybillNumber;

	@Column(name = "CARGO_SPACE_NUM")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "CURRENCY_CODE")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "KEY_CODE")
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "NUMBER_CODE")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PLANE_NUMBER")
	public String getPlaneNumber() {
		return planeNumber;
	}

	public void setPlaneNumber(String planeNumber) {
		this.planeNumber = planeNumber;
	}

	@Column(name = "PLANE_TYPE")
	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	@Column(name = "PRIORTY_CODE")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "RECIEVE_RECORDER")
	public String getRecieveRecorder() {
		return recieveRecorder;
	}

	public void setRecieveRecorder(String recieveRecorder) {
		this.recieveRecorder = recieveRecorder;
	}

	@Column(name = "REFRE_PRICE")
	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	@Column(name = "REG_DATE")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "REG_USER")
	public String getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}

	@Column(name = "REMARK_TXT")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REPAIR_CONTRACT_NUM")
	public String getRepairContractNumber() {
		return repairContractNumber;
	}

	public void setRepairContractNumber(String repairContractNumber) {
		this.repairContractNumber = repairContractNumber;
	}

	@Column(name = "PEPAIR_REGSHEET_NUM")
	public String getRepairRegisterSheetNumber() {
		return repairRegisterSheetNumber;
	}

	public void setRepairRegisterSheetNumber(String repairRegisterSheetNumber) {
		this.repairRegisterSheetNumber = repairRegisterSheetNumber;
	}

	@Column(name = "SERIAL_NUM")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "UNIT_CODE")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "WARE_HOUSE")
	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	@Column(name = "WAYBILL_NUM")
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

}