package com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.spmsdd.StockCheckResult;

/**
 * @author buhuan
 * @version 1.1
 * @created 2011-3-30
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCK_CHECK")
public class StockCheck extends BaseEntity {
	/**
	 * 盘点编号
	 */
	private String checkNumber;

	/**
	 * 盘点项目描述
	 */
	private String checkDescribe;

	/**
	 * 盘点开始日期
	 */
	private Date checkStartDate;

	/**
	 * 盘点结束日期
	 */
	private Date checkEndDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 货位编号(起始)
	 */
	private String fromCargoSpaceNumber;

	/**
	 * 货位编号(结束)
	 */
	private String toCargoSpaceNumber;
	
	/**
	 * 下达状态
	 */
	private SendStatus sendStatus;
	
	/**
	 * 盘点结果
	 */
	private StockCheckResult stockCheckResult;
	
	/**
	 * 盘点明细
	 */
	private List<StockCheckItem> stockCheckItemList=new ArrayList<StockCheckItem>();

	public StockCheck() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "CHECK_NUMBER")
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	@Column(name = "CHECK_DESCRIBE")
	public String getCheckDescribe() {
		return checkDescribe;
	}

	public void setCheckDescribe(String checkDescribe) {
		this.checkDescribe = checkDescribe;
	}

	@Column(name = "CHECK_START_DATE")
	public Date getCheckStartDate() {
		return checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}

	@Column(name = "CHECK_END_DATE")
	public Date getCheckEndDate() {
		return checkEndDate;
	}

	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "FROM_CARGO_SPACE_NUMBER")
	public String getFromCargoSpaceNumber() {
		return fromCargoSpaceNumber;
	}

	public void setFromCargoSpaceNumber(String fromCargoSpaceNumber) {
		this.fromCargoSpaceNumber = fromCargoSpaceNumber;
	}

	@Column(name = "TO_CARGO_SPACE_NUMBER")
	public String getToCargoSpaceNumber() {
		return toCargoSpaceNumber;
	}

	public void setToCargoSpaceNumber(String toCargoSpaceNumber) {
		this.toCargoSpaceNumber = toCargoSpaceNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SEND_STATUS")
	public SendStatus getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(SendStatus sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STOCK_CHECK_RESULT")
	public StockCheckResult getStockCheckResult() {
		return stockCheckResult;
	}

	public void setStockCheckResult(StockCheckResult stockCheckResult) {
		this.stockCheckResult = stockCheckResult;
	}

	@OneToMany(targetEntity= StockCheckItem.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STOCK_CHECK_ID")
	public List<StockCheckItem> getStockCheckItemList() {
		return stockCheckItemList;
	}

	public void setStockCheckItemList(List<StockCheckItem> stockCheckItemList) {
		this.stockCheckItemList = stockCheckItemList;
	}
}