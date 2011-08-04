package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.webservice.WebServiceUtils;
import com.skynet.spms.webservice.entity.StockCountExeItem;
import com.skynet.spms.webservice.entity.StockCountTaskItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:18
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCK_CHECK_TASK_ITEM")
public class StockCheckTaskItem extends BaseStockTaskItem {
	
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;
    
    /**
     * 货位编号
     */
    private String cargoSpaceNumber;

    /**
     * 实际数量
     */
    private double realityQuantity;

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

    @Column(name = "REALITY_QUANTITY")
	public double getRealityQuantity() {
		return realityQuantity;
	}

	public void setRealityQuantity(double realityQuantity) {
		this.realityQuantity = realityQuantity;
	}
}