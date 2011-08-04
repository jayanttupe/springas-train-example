package com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.csdd.p.PartStatusCode;

/**
 * @author 补桓
 * @version 1.0
 * @created 15-三月-2011 12:33:18
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_STOCK_MOVE_ITEMS")
public class StockMovingRecordItems extends BaseEntity{
	/**
	 * 移库单ID
	 */
	private String stockMovingRecordID;
	/**
	 * 生产日期
	 */
	private Date dateOfManufacture;
	/**
	 * 移入库货位编码
	 */
	private String movingInFreightLotNumber;
	/**
	 * 移出库货位编码
	 */
	private String movingOutFreightLotNumber;
	/**
	 * 件号
	 */
	private String partNumber;
	/**
	 * 序号
	 */
	private String partSerialNumber;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 备件状态
	 */
	private String status;
	/**
	 * 移库单编号
	 */
	private String stockMovingNumber;
	/**
	 * 明细项编号
	 */
	private int stockMovingNumberItem;
	/**
	 * 计量单位
	 */
	private String unitMeasureCode;
	/**
	 * 价格
	 */
	private float unitPrice;
	/**
	 * 备件中心代码
	 */
	private String m_PartStatusCode;
	/**
	 * 制造商代码
	 */
	private String m_ManufacturerCode;
	
	/**
	 * 状态
	 */
	private String state;
		
	/**
	 * 总金额
	 */
	private float totalAmount;
	
	@Column(name="TOTAL_AMOUNT")
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="M_STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="DATEOF_MANU_FACTURE")
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	@Column(name="MOVING_INFREIGHT_LOTNUM")
	public String getMovingInFreightLotNumber() {
		return movingInFreightLotNumber;
	}
	public void setMovingInFreightLotNumber(String movingInFreightLotNumber) {
		this.movingInFreightLotNumber = movingInFreightLotNumber;
	}
	@Column(name="MOVING_OUTFREIGHT_LOTNUM")
	public String getMovingOutFreightLotNumber() {
		return movingOutFreightLotNumber;
	}
	public void setMovingOutFreightLotNumber(String movingOutFreightLotNumber) {
		this.movingOutFreightLotNumber = movingOutFreightLotNumber;
	}
	@Column(name="PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	@Column(name="PART_SERIAL_NUM")
	public String getPartSerialNumber() {
		return partSerialNumber;
	}
	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}
	@Column(name="M_QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name="M_STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="STOCK_MOVING_NUM")
	public String getStockMovingNumber() {
		return stockMovingNumber;
	}
	public void setStockMovingNumber(String stockMovingNumber) {
		this.stockMovingNumber = stockMovingNumber;
	}
	@Column(name="STOCK_MOVING_NUM_ITEM")
	public int getStockMovingNumberItem() {
		return stockMovingNumberItem;
	}
	public void setStockMovingNumberItem(int stockMovingNumberItem) {
		this.stockMovingNumberItem = stockMovingNumberItem;
	}
	@Column(name="UNIT_MEASURE_CODE")
	public String getUnitMeasureCode() {
		return unitMeasureCode;
	}
	public void setUnitMeasureCode(String unitMeasureCode) {
		this.unitMeasureCode = unitMeasureCode;
	}
	@Column(name="UNIT_PRICE")
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name="PART_STATUS_CODE")
	public String getM_PartStatusCode() {
		return m_PartStatusCode;
	}
	public void setM_PartStatusCode(String m_PartStatusCode) {
		this.m_PartStatusCode = m_PartStatusCode;
	}
	@Column(name="M_MANUFACTURER_CODE")
	public String getM_ManufacturerCode() {
		return m_ManufacturerCode;
	}
	public void setM_ManufacturerCode(String m_ManufacturerCode) {
		this.m_ManufacturerCode = m_ManufacturerCode;
	}
	@Column(name = "STOCK_MOVING_RECORD_ID") 
	public String getStockMovingRecordID() {
		return stockMovingRecordID;
	}
	public void setStockMovingRecordID(String stockMovingRecordID) {
		this.stockMovingRecordID = stockMovingRecordID;
	}
	
	
	

}