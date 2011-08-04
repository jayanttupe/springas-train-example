package com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-1
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_REPAREPART_BUSINESS")
public class ReparePartBusiness extends BaseEntity {
	/**
	 * 修理合同号
	 */
	private String repairContractNumber;

	/**
	 * 登记单编号
	 */
	private String repairRegisterSheetNumber;
	/**
	 * 制造商
	 */
	private String manufacturer;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 件名称
	 */
	private String partName;
	/**
	 * 收料单编号
	 */
	private String recieveRecorder;
	/**
	 * 序号/批号
	 */
	private String serialNumber;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 备注名称
	 */
	private String remarkName ;

	/**
	 * 逻辑库
	 */
	private String logicLibrary;

	/**
	 * 货位
	 */
	private String cargoSpaceNumber;

	public ReparePartBusiness() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "REPAIR_CONTRACT_NUMBER")
	public String getRepairContractNumber() {
		return repairContractNumber;
	}

	public void setRepairContractNumber(String repairContractNumber) {
		this.repairContractNumber = repairContractNumber;
	}

	@Column(name = "REPAIR_REGISTER_SHEET_NUMBER")
	public String getRepairRegisterSheetNumber() {
		return repairRegisterSheetNumber;
	}

	public void setRepairRegisterSheetNumber(String repairRegisterSheetNumber) {
		this.repairRegisterSheetNumber = repairRegisterSheetNumber;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "QUANTITY")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
 
	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "PART_NAME")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "RECIEVE_RECORDER")
	public String getRecieveRecorder() {
		return recieveRecorder;
	}

	public void setRecieveRecorder(String recieveRecorder) {
		this.recieveRecorder = recieveRecorder;
	}

	@Column(name = "SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "REMARK_NAME")
	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	@Column(name = "LOGIC_LIBRARY") 
	public String getLogicLibrary() {
		return logicLibrary;
	}

	public void setLogicLibrary(String logicLibrary) {
		this.logicLibrary = logicLibrary;
	}


	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

}