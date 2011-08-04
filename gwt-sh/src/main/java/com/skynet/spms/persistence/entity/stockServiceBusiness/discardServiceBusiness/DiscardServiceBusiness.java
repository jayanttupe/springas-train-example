package com.skynet.spms.persistence.entity.stockServiceBusiness.discardServiceBusiness;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.PartDiscardClassify;

/**
 * @author fanyx
 * @version 1.1
 * @created 2011-4-6
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_DISCARD_SERVICE")
public class DiscardServiceBusiness extends BaseEntity {
	/**
	 * 报废申请单编号
	 */
	private String discardReportNumber;

	
	/**
	 * 是否送修登记
	 */
	private boolean isRepair;

	/**
	 * 件号
	 */
	private String partNumber;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 报废类型
	 */
	private PartDiscardClassify discardTypes;

	/**
	 * 原价(单价)
	 */
	private String originalUnitPrice;

	/**
	 * 当前残值(单价)
	 */
	private String remainderUnitPrice;

	/**
	 * 库房编号
	 */
	private String stockRoomNumber;

	/**
	 * 处理意向
	 */
	private String disposeDescribe;

	/**
	 * 待处理航材状态描述
	 */
	private String discardPartStatusDescribe;

	/**
	 * 申请人
	 */
	private String applicant;

	/**
	 * 质检处理人
	 */
	private String qualityTestPerson;

	/**
	 * 合同编号
	 */
	private String contractNumber;

	/**
	 * 序列/批次号
	 */
	private String partSerialNumber;

	/**
	 * 数量
	 */
	private String quantity;

	/**
	 * 单位
	 */
	private UnitOfMeasureCode unitMeasureCode;

	/**
	 * 购买日期
	 */
	private Date buyData;

	/**
	 * 当前残值(总价)
	 */
	private String remainderTotalPrice;
   
	/**
	 * 使用小时/起落
	 */
	private String usedTime;

	/**
	 * 货位编号
	 */
	private String cargoSpaceNumber;

	/**
	 * 处理人
	 */
	private String discardReportProcessor;

	/**
	 * 申请日期
	 */
	private Date applicationDate;

	/**
	 * 处理日期
	 */
	private Date discardReportProcessDate;

	/**
	 * 状态
	 */
	private String state;

	public DiscardServiceBusiness() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "DISCARD_REPORT_NUMBER")
	public String getDiscardReportNumber() {
		return discardReportNumber;
	}

	public void setDiscardReportNumber(String discardReportNumber) {
		this.discardReportNumber = discardReportNumber;
	}

	@Column(name = "IS_REPAIR")
	public boolean getIsRepair() {
		return isRepair;
	}

	public void setIsRepair(boolean isRepair) {
		this.isRepair = isRepair;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DISCARD_TYPES")
	public PartDiscardClassify getDiscardTypes() {
		return discardTypes;
	}

	public void setDiscardTypes(PartDiscardClassify discardTypes) {
		this.discardTypes = discardTypes;
	}

	@Column(name = "ORIGINAL_UNIT_PRICE")
	public String getOriginalUnitPrice() {
		return originalUnitPrice;
	}

	public void setOriginalUnitPrice(String originalUnitPrice) {
		this.originalUnitPrice = originalUnitPrice;
	}

	@Column(name = "REMAINDER_UNIT_PRICE")
	public String getRemainderUnitPrice() {
		return remainderUnitPrice;
	}

	public void setRemainderUnitPrice(String remainderUnitPrice) {
		this.remainderUnitPrice = remainderUnitPrice;
	}

	@Column(name = "STOCK_ROOM_NUMBER")
	public String getStockRoomNumber() {
		return stockRoomNumber;
	}

	public void setStockRoomNumber(String stockRoomNumber) {
		this.stockRoomNumber = stockRoomNumber;
	}

	@Column(name = "DISPOSE_DESCRIBE")
	public String getDisposeDescribe() {
		return disposeDescribe;
	}

	public void setDisposeDescribe(String disposeDescribe) {
		this.disposeDescribe = disposeDescribe;
	}

	@Column(name = "DISCARD_PART_STATUS_DESCRIBE")
	public String getDiscardPartStatusDescribe() {
		return discardPartStatusDescribe;
	}

	public void setDiscardPartStatusDescribe(String discardPartStatusDescribe) {
		this.discardPartStatusDescribe = discardPartStatusDescribe;
	}

	@Column(name = "APPLICANT")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@Column(name = "QUALITY_TEST_PERSON")
	public String getQualityTestPerson() {
		return qualityTestPerson;
	}

	public void setQualityTestPerson(String qualityTestPerson) {
		this.qualityTestPerson = qualityTestPerson;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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
	@Column(name = "UNIT_MEASURE_CODE")
	public UnitOfMeasureCode getUnitMeasureCode() {
		return unitMeasureCode;
	}

	public void setUnitMeasureCode(UnitOfMeasureCode unitMeasureCode) {
		this.unitMeasureCode = unitMeasureCode;
	}

	@Column(name = "BUY_DATA")
	public Date getBuyData() {
		return buyData;
	}

	public void setBuyData(Date buyData) {
		this.buyData = buyData;
	}

	@Column(name = "REMAINDER_TOTAL_PRICE")
	public String getRemainderTotalPrice() {
		return remainderTotalPrice;
	}

	public void setRemainderTotalPrice(String remainderTotalPrice) {
		this.remainderTotalPrice = remainderTotalPrice;
	}

	@Column(name = "USED_TIME")
	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	@Column(name = "CARGO_SPACE_NUMBER")
	public String getCargoSpaceNumber() {
		return cargoSpaceNumber;
	}

	public void setCargoSpaceNumber(String cargoSpaceNumber) {
		this.cargoSpaceNumber = cargoSpaceNumber;
	}

	@Column(name = "DISCARD_REPORT_PROCESSOR")
	public String getDiscardReportProcessor() {
		return discardReportProcessor;
	}

	public void setDiscardReportProcessor(String discardReportProcessor) {
		this.discardReportProcessor = discardReportProcessor;
	}

	@Column(name = "APPLICATION_DATE")
	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Column(name = "DISCARD_REPORT_PROCESS_DATE")
	public Date getDiscardReportProcessDate() {
		return discardReportProcessDate;
	}

	public void setDiscardReportProcessDate(Date discardReportProcessDate) {
		this.discardReportProcessDate = discardReportProcessDate;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}