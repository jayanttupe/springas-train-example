package com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author 补桓
 * @version 1.0
 * @created 15-三月-2011 12:33:18
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_STOCK_MOVE_RECORD")
public class StockMovingRecord extends BaseEntity {
    /**
     * 移入备件中心位置
     */
	private String movingInPartsLocation;
	/**
	 * 移入库房代码
	 */
	private String movingInStockRoomNumbers;
	/**
	 * 移出备件中心位置
	 */
	private String movingOutPartsLocation;
	/**
	 * 移出库房代码
	 */
	private String movingOutStockRoomNumbers;
	/**
	 * 批准者
	 */
	private String ratifier;
	/**
	 * 批准日期
	 */
	private Date ratifyDate;
	/**
	 * 移库单号
	 */
	private String stockMovingNumber;
	/**
	 * 移库原因
	 */
	private String stockMovingReason;
	/**
	 * 总金额
	 */
	private int totalAmount;
	/**
	 * 总项数
	 */
	private int totalItemsQuantity;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 移库单明细
	 */
	private List<StockMovingRecordItems> stockMovingRecordItems;
	
	
	@Column(name="MOVING_INPARTS_LOCATION")
	public String getMovingInPartsLocation() {
		return movingInPartsLocation;
	}
	public void setMovingInPartsLocation(String movingInPartsLocation) {
		this.movingInPartsLocation = movingInPartsLocation;
	}
	@Column(name="MOVING_INSTOCKROOM_NUM")
	public String getMovingInStockRoomNumbers() {
		return movingInStockRoomNumbers;
	}
	public void setMovingInStockRoomNumbers(String movingInStockRoomNumbers) {
		this.movingInStockRoomNumbers = movingInStockRoomNumbers;
	}
	@Column(name="MOVING_OUTPARTS_LOCATION")
	public String getMovingOutPartsLocation() {
		return movingOutPartsLocation;
	}
	public void setMovingOutPartsLocation(String movingOutPartsLocation) {
		this.movingOutPartsLocation = movingOutPartsLocation;
	}
	@Column(name="MOVING_OUTSTOCKROOM_NUM")
	public String getMovingOutStockRoomNumbers() {
		return movingOutStockRoomNumbers;
	}
	public void setMovingOutStockRoomNumbers(String movingOutStockRoomNumbers) {
		this.movingOutStockRoomNumbers = movingOutStockRoomNumbers;
	}
	@Column(name="RATIFIER")
	public String getRatifier() {
		return ratifier;
	}
	public void setRatifier(String ratifier) {
		this.ratifier = ratifier;
	}
	@Column(name="RATIFYDATE")
	public Date getRatifyDate() {
		return ratifyDate;
	}
	public void setRatifyDate(Date ratifyDate) {
		this.ratifyDate = ratifyDate;
	}
	@Column(name="STOCK_MOVING_NUM")
	public String getStockMovingNumber() {
		return stockMovingNumber;
	}
	public void setStockMovingNumber(String stockMovingNumber) {
		this.stockMovingNumber = stockMovingNumber;
	}
	@Column(name="STOCK_MOVING_REASON")
	public String getStockMovingReason() {
		return stockMovingReason;
	}
	public void setStockMovingReason(String stockMovingReason) {
		this.stockMovingReason = stockMovingReason;
	}
	@Column(name="TOTAL_AMOUNT")
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="TOTAL_ITEMS_QUANTITY")
	public int getTotalItemsQuantity() {
		return totalItemsQuantity;
	}
	public void setTotalItemsQuantity(int totalItemsQuantity) {
		this.totalItemsQuantity = totalItemsQuantity;
	}
	@Column(name="M_STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@OneToMany(targetEntity= StockMovingRecordItems.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_MOVING_RECORD_ID")
	public List<StockMovingRecordItems> getStockMovingRecordItems() {
		return stockMovingRecordItems;
	}
	public void setStockMovingRecordItems(
			List<StockMovingRecordItems> stockMovingRecordItems) {
		this.stockMovingRecordItems = stockMovingRecordItems;
	}

	

}