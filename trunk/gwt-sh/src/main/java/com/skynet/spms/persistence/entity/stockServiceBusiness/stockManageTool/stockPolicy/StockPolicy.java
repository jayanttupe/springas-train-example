package com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockPolicy;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-3-29
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCK_POLICY")
public class StockPolicy extends BaseEntity {
	/**
	 * 库存策略编号
	 */
	private String stockPolicyNumber;
    /**
     * 库存策略描述
     */
	private String stockPolicyName;
	/**
	 * 库房编号
	 */
	private String stockRoomNumber;
    /**
     * 区域代码
     */
	private String stockAreaCode;
	/**
	 * 业务类型
	 */
	private BusinessType businessType;

	/**
	 * 备件类别
	 */
	private String backType;

	/**
	 * 适用机型
	 */
	private String suitType;

	/**
	 * 是否保税
	 */
	private String isBonded;

	/**
	 * 起始货位编号
	 */
	private String startCargoSpaceNumber;

	/**
	 * 结束货位编号
	 */
	private String endCargoSpaceNumber;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备件中心位置代码
	 */
	private String partCenterCode;
	/**
	 * 策略开始日期
	 */
    private Date enableDate;
    
	public StockPolicy() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
    @Column ( name = "ENABLE_DATE")
	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	@Column( name = "STOCK_POLICY_NAME")
	public String getStockPolicyName() {
		return stockPolicyName;
	}

	public void setStockPolicyName(String stockPolicyName) {
		this.stockPolicyName = stockPolicyName;
	}
    @Column ( name= "STOCK_AREA_CODE" )
	public String getStockAreaCode() {
		return stockAreaCode;
	}

	public void setStockAreaCode(String stockAreaCode) {
		this.stockAreaCode = stockAreaCode;
	}

	@Column(name = "STOCK_POLICY_NUMBER")
	public String getStockPolicyNumber() {
		return stockPolicyNumber;
	}

	public void setStockPolicyNumber(String stockPolicyNumber) {
		this.stockPolicyNumber = stockPolicyNumber;
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "BACK_TYPE")
	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	@Column(name = "SUIT_TYPE")
	public String getSuitType() {
		return suitType;
	}

	public void setSuitType(String suitType) {
		this.suitType = suitType;
	}

	@Column(name = "IS_BONDED")
	public String getIsBonded() {
		return isBonded;
	}

	public void setIsBonded(String isBonded) {
		this.isBonded = isBonded;
	}

	@Column(name = "START_CARGO_SPACE_NUMBER")
	public String getStartCargoSpaceNumber() {
		return startCargoSpaceNumber;
	}

	public void setStartCargoSpaceNumber(String startCargoSpaceNumber) {
		this.startCargoSpaceNumber = startCargoSpaceNumber;
	}

	@Column(name = "END_CARGO_SPACE_NUMBER")
	public String getEndCargoSpaceNumber() {
		return endCargoSpaceNumber;
	}

	public void setEndCargoSpaceNumber(String endCargoSpaceNumber) {
		this.endCargoSpaceNumber = endCargoSpaceNumber;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PART_CENTER_CODE")
	public String getPartCenterCode() {
		return partCenterCode;
	}

	public void setPartCenterCode(String partCenterCode) {
		this.partCenterCode = partCenterCode;
	}

}