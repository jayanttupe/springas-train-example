package com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.StockCheckResult;

/**
 * @author 黄帝君
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCK_CHECK_ITEM")
public class StockCheckItem extends BaseEntity {

	/**
	 * 盘点ID
	 */
	private String stockCheckID;
		
	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 序号/批号
	 */
	private String partSerialNumber;

	/**
	 * 件名称
	 */
	private String partName;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 明细编号
	 */
	private int itemnumber;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode partUnit;
	
	/**
	 * 盘点编号
	 */
	private String checkNumber;
	
	/**
	 * 盘点结果
	 */
	private String checkResult;
	
	/**
	 * 盘点日期
	 */
	private Date checkDate;
	
	/**
	 * 盘点人
	 */
	private String checkMan;
	
	/**
	 * 盘存数量
	 */
	private int checkQuantity;
	
	/**
	 * 盘存单位
	 */
	private String checkUnits;
	
	/**
	 * 单价
	 */
	private float unitPrice;
	
	/**
	 * 盘点结果
	 */
	private StockCheckResult stockCheckResult;
	
    @Column ( name = "UNIT_PRICE" )
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column ( name = "CHECK_RESULT" )
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

    @Column ( name = "CHECK_DATE")
	public Date getCheckDate() {
		return checkDate;
	}


	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

    @Column (name = "CHECK_MAN")
	public String getCheckMan() {
		return checkMan;
	}


	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

    @Column (name = "CHECK_QUANTITY")
	public int getCheckQuantity() {
		return checkQuantity;
	}


	public void setCheckQuantity(int checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

    @Column ( name = "CHECK_UNITS")
	public String getCheckUnits() {
		return checkUnits;
	}


	public void setCheckUnits(String checkUnits) {
		this.checkUnits = checkUnits;
	}


	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column (name ="CHECK_NUMBER")
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	@Column(name = "STOCK_CHECK_ID")
	public String getStockCheckID() {
		return stockCheckID;
	}

	public void setStockCheckID(String stockCheckID) {
		this.stockCheckID = stockCheckID;
	}

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
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

	@Column(name = "PART_NAME")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
    @Column (name ="ITEM_NUMBER")
	public int getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(int itemnumber) {
		this.itemnumber = itemnumber;
	}

	@Column(name = "PART_UNIT")
	public UnitOfMeasureCode getPartUnit() {
		return partUnit;
	}

	public void setPartUnit(UnitOfMeasureCode unitOfMeasure) {
		this.partUnit = unitOfMeasure;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STOCK_CHECK_RESULT")
	public StockCheckResult getStockCheckResult() {
		return stockCheckResult;
	}

	public void setStockCheckResult(StockCheckResult stockCheckResult) {
		this.stockCheckResult = stockCheckResult;
	}
}