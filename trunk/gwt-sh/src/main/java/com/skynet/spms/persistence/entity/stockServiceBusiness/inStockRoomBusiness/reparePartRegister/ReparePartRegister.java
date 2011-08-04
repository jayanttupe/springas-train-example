/**
 * 
 */
package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.reparePartRegister;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;

/**
 * @author Administrator
 *
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_REPARE_PART_REGISTER")
public class ReparePartRegister extends BaseEntity{
	/**
	 * 登记单号
	 */
	private String repairRegisterSheetNumber;
	 /**
	  * 登记日期
	  */
	private Date registerDate;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 序号/批号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 单位
	 */
	private UnitOfMeasureCode unit;
	/**
	 * 收料单号
	 */
	private String receivingSheetID;
	/**
	 * 收料单编号
	 */
	private String receivingSheetNumber;
	/**
	 * 制造商
	 */
	private String manufacturer;
	/**
	 * 备件名称
	 */
	private String partName;
	/**
	 * 逻辑库
	 */
	private String logicStock;
	/**
	 * 货位 
	 */
	private String cargoSpace;
	/**
	 * 备注
	 */
	private String remark;
	
	public ReparePartRegister() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "REPAIR_REG_SHEET_NUMBER")
	public String getRepairRegisterSheetNumber() {
		return repairRegisterSheetNumber;
	}

	public void setRepairRegisterSheetNumber(String repairRegisterSheetNumber) {
		this.repairRegisterSheetNumber = repairRegisterSheetNumber;
	}

	@Column(name = "REGISTER_DATE")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "CONTRACT_NUMBER") 
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	@Column(name = "QUANTITY") 
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT") 
	public UnitOfMeasureCode getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasureCode unit) {
		this.unit = unit;
	}

	@Column(name = "RECEIVING_SHEET_ID") 
	public String getReceivingSheetID() {
		return receivingSheetID;
	}

	public void setReceivingSheetID(String receivingSheetID) {
		this.receivingSheetID = receivingSheetID;
	}

	@Column(name = "RECEIVING_SHEET_NUMBER") 
	public String getReceivingSheetNumber() {
		return receivingSheetNumber;
	}

	public void setReceivingSheetNumber(String receivingSheetNumber) {
		this.receivingSheetNumber = receivingSheetNumber;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "PART_NAME") 
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "LOGIC_STOCK")
	public String getLogicStock() {
		return logicStock;
	}

	public void setLogicStock(String logicStock) {
		this.logicStock = logicStock;
	}

	@Column(name = "CARGO_SPACE")
	public String getCargoSpace() {
		return cargoSpace;
	}

	public void setCargoSpace(String cargoSpace) {
		this.cargoSpace = cargoSpace;
	}

	@Column(name = "REMARK") 
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
	
}
