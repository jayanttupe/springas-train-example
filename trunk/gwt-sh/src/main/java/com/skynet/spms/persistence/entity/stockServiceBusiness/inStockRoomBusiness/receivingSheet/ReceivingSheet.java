package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

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
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.PackagingAppearance;

/**
 * @author huangdj
 * @version 1.0
 * @created 22-三月-2011
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_RECEIVING_SHEET")
public class ReceivingSheet extends BaseEntity {
	/**
	 * 收料单编号
	 */
	private String receivingSheetNumber;

	/**
	 * 是否保税
	 */
	private boolean isBonded;
	
	/**
	 * 是否送修登记
	 */
	private boolean isRepair;

	/**
	 * 提货指令单ID
	 */
	private String orderID;

	/**
	 * 提货指令单编号
	 */
	private String orderNumber;

	/**
	 * 业务类型
	 */
	private BusinessType businessType;

	/**
	 * 合同编号
	 */
	private String contractNumber;

	/**
	 * 运单号
	 */
	private String mainWayBill;

	/**
	 * 物流操作人员
	 */
	private String logisticsCreateBy;

	/**
	 * 物流操作日期
	 */
	private Date logisticsCreateDate;

	/**
	 * 包装外观
	 */
	private PackagingAppearance packagingAppearance;

	/**
	 * 箱数
	 */
	private String boxQuantity;

	/**
	 * 收料人
	 */
	private String receivingUser;

	/**
	 * 收料日期
	 */
	private Date receivingDate;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 执行结果
	 * 0:执行正常
	 * 1:已经存在相同的件号和序号/批号
	 */
	private String executeResult = "0";
	
	/**
	 * 出错的情况,输出错误信息
	 */
	private String errMsg;

	/**
	 * 收料单明细
	 */
	private List<ReceivingSheetItems> receivingSheetItemsList; 

	public ReceivingSheet() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "RECEIVING_SHEET_NUMBER")
	public String getReceivingSheetNumber() {
		return receivingSheetNumber;
	}

	public void setReceivingSheetNumber(String receivingSheetNumber) {
		this.receivingSheetNumber = receivingSheetNumber;
	}
	
	@Column(name = "IS_BONDED")
	public boolean getIsBonded() {
		return isBonded;
	}

	public void setIsBonded(boolean isBonded) {
		this.isBonded = isBonded;
	}

	@Column(name = "IS_REPAIR")
	public boolean getIsRepair() {
		return isRepair;
	}

	public void setIsRepair(boolean isRepair) {
		this.isRepair = isRepair;
	}

	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	@Column(name = "order_Number")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BUSINESS_TYPE")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "MAIN_WAY_BILL")
	public String getMainWayBill() {
		return mainWayBill;
	}

	public void setMainWayBill(String mainWayBill) {
		this.mainWayBill = mainWayBill;
	}

	@Column(name = "LOGISTICS_CREATE_BY")
	public String getLogisticsCreateBy() {
		return logisticsCreateBy;
	}

	public void setLogisticsCreateBy(String logisticsCreateBy) {
		this.logisticsCreateBy = logisticsCreateBy;
	}

	@Column(name = "LOGISTICS_CREATE_DATE")
	public Date getLogisticsCreateDate() {
		return logisticsCreateDate;
	}

	public void setLogisticsCreateDate(Date logisticsCreateDate) {
		this.logisticsCreateDate = logisticsCreateDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PACKAGING_APPEARANCE")
	public PackagingAppearance getPackagingAppearance() {
		return packagingAppearance;
	}

	public void setPackagingAppearance(PackagingAppearance packagingAppearance) {
		this.packagingAppearance = packagingAppearance;
	}

	@Column(name = "BOX_QUANTITY")
	public String getBoxQuantity() {
		return boxQuantity;
	}

	public void setBoxQuantity(String boxQuantity) {
		this.boxQuantity = boxQuantity;
	}

	@Column(name = "RECEIVING_USER")
	public String getReceivingUser() {
		return receivingUser;
	}

	public void setReceivingUser(String receivingUser) {
		this.receivingUser = receivingUser;
	}

	@Column(name = "RECEIVING_DATE")
	public Date getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@OneToMany(targetEntity= ReceivingSheetItems.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="RECEIVING_SHEET_ID")
	public List<ReceivingSheetItems> getReceivingSheetItemsList() {
		return receivingSheetItemsList;
	}

	public void setReceivingSheetItemsList(List<ReceivingSheetItems> receivingSheetItems) {
		this.receivingSheetItemsList = receivingSheetItems;
	}

	@Transient
	public String getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}

	@Transient
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}