package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

/**
 * @author hdj
 * @version 1.1
 * @created 2011-3-26
 */
public class InStockRecordForPrint {

	// 件号
	private String partNumber;
	// 序号/批次号
	private String partSerialNumber;
	// 制造商
	private String manufacturerCode;
	// 数量
	private int quantity;
	// 单位
	private String unit;
	// 航材分类
	private String sparePartClassCode;
	// 客户订单号(暂时先使用合同编号)
	private String contratNumber;
	// 库房编号
	private String stockRoomNumber;
	// 入库检验单编号
	private String checkAndAcceptSheetNumber;
	
	public InStockRecordForPrint() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSparePartClassCode() {
		return sparePartClassCode;
	}

	public void setSparePartClassCode(String sparePartClassCode) {
		this.sparePartClassCode = sparePartClassCode;
	}

	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}

	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	public String getCheckAndAcceptSheetNumber() {
		return checkAndAcceptSheetNumber;
	}

	public void setCheckAndAcceptSheetNumber(String checkAndAcceptSheetNumber) {
		this.checkAndAcceptSheetNumber = checkAndAcceptSheetNumber;
	}
}