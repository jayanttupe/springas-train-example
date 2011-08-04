package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SPMS_REPAIR_CODE_TASK_ITEM")
public class RepairCodeTaskItem extends BaseStockTaskItem{

	/**
	 * 在SPMS备件管理中，备件的证书由统一的证书条码来进行管理，通过唯一的证书条码可与指定的件号+序号/批次号的件进行关联绑定。
	 */
	private String cerificateBarcode;
	/**
	 * 标识当前的件需要进行时寿时控处理的下一个日期
	 */
	private Date expireDate;
	/**
	 * 国际商品编码
	 */
	private String internationalCommodityCode;
	/**
	 * 货位编码
	 */
	private String locationNumber;
	/**
	 * 生产日期
	 */
	public Date manufactureDate;
	/**
	 * 制造商代码
	 */
	private String manufacturerCode;
	/**
	 * 备件描述
	 */
	private String partDescription;
	/**
	 * 备件状态代码
	 */
	private String partStatusCode;
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;
	/**
	 * 供应商代码
	 */
	private String supplierCode;
	
	@Column(name = "CERIFICATE_BARCODE")
	public String getCerificateBarcode() {
		return cerificateBarcode;
	}
	public void setCerificateBarcode(String cerificateBarcode) {
		this.cerificateBarcode = cerificateBarcode;
	}
	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@Column(name = "ICOMMODITY_CODE")
	public String getInternationalCommodityCode() {
		return internationalCommodityCode;
	}
	public void setInternationalCommodityCode(String internationalCommodityCode) {
		this.internationalCommodityCode = internationalCommodityCode;
	}
	@Column(name = "LOCATION_NUMBER")
	public String getLocationNumber() {
		return locationNumber;
	}
	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}
	@Column(name = "MANUFACTURE_DATE")
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	@Column(name = "MANUFACTURER_CODE")
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	@Column(name = "PART_DESCRIPTION")
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
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
	@Column(name = "SUPPLIER_CODE")
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
}
