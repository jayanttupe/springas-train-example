package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseSecurityDeposit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.ClearanceAgent;
import com.skynet.spms.persistence.entity.spmsdd.PortOfClearance;
import com.skynet.spms.persistence.entity.spmsdd.SecurityDepositType;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;

/**
 * @author FANYX
 * @version 1.0   基础报关保证金
 * @created 16-三月-2011 17:21:04
 */
@MappedSuperclass
public class BaseSecurityDeposit extends BaseEntity {

	/**
	 * 报关ID
	 */
	private String customsID;

	/**
	 * 指令ID
	 */
	private String orderID;

	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 申报日期
	 */
	private Date declarationDate ;
	/**
	 * 到期时间
	 */
	private Date expireDates;
	/**
	 * 主运单号
	 */
	private String masterWaybillNumber;
	/**
	 * 分运单号
	 */
	private String houseWaybillNumber;
	/**
	 * 件数
	 */
	private String numberOfPackage;
	/**
	 * 办保原因
	 */
	private String reason;
	/**
	 * 担保金额
	 */
	private String securityDepositAmount;
	/**
	 * 保证金单编号
	 */
	private String securityDepositNumber;
	/**
	 * 保证起始日期
	 */
	private Date startDate;
	/**
	 * 运输方式代码
	 */
	private String transportationCode;
	/**
	 * 报关代理商
	 */
	private String clearanceAgent;
	/**
	 * 保证金类型
	 */
	private String securityDepositType;
	/**
	 * 报关口岸
	 */
	private String portOfClearance;
	/**
	 * 备件中心位置
	 */
	private String sparePartCenterPosition;

	@Column(name = "CUSTOMS_ID")
	public String getCustomsID() {
		return customsID;
	}
	public void setCustomsID(String customsID) {
		this.customsID = customsID;
	}
	@Column(name = "ORDER_ID")
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name = "DECLARATION_DATE")
	public Date getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	@Column(name = "EXPIRE_DATES")
	public Date getExpireDates() {
		return expireDates;
	}
	public void setExpireDates(Date expireDates) {
		this.expireDates = expireDates;
	}
	@Column(name = "HOUSE_WAYBILL_NUMBER")
	public String getHouseWaybillNumber() {
		return houseWaybillNumber;
	}
	public void setHouseWaybillNumber(String houseWaybillNumber) {
		this.houseWaybillNumber = houseWaybillNumber;
	}
	@Column(name = "MASTER_WAYBILL_NUMBER")
	public String getMasterWaybillNumber() {
		return masterWaybillNumber;
	}
	public void setMasterWaybillNumber(String masterWaybillNumber) {
		this.masterWaybillNumber = masterWaybillNumber;
	}
	@Column(name = "NUMBER_OF_PACKAGE")
	public String getNumberOfPackage() {
		return numberOfPackage;
	}
	public void setNumberOfPackage(String numberOfPackage) {
		this.numberOfPackage = numberOfPackage;
	}
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "SECURITY_DEPOSIT_AMOUNT")
	public String getSecurityDepositAmount() {
		return securityDepositAmount;
	}
	public void setSecurityDepositAmount(String securityDepositAmount) {
		this.securityDepositAmount = securityDepositAmount;
	}
	@Column(name = "SECURITY_DEPOSIT_NUMBER")
	public String getSecurityDepositNumber() {
		return securityDepositNumber;
	}
	public void setSecurityDepositNumber(String securityDepositNumber) {
		this.securityDepositNumber = securityDepositNumber;
	}
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Transient
	public String getTransportationCode() {
		return transportationCode;
	}
	public void setTransportationCode(String transportationCode) {
		this.transportationCode = transportationCode;
	}
	@Transient
	public String getClearanceAgent() {
		return clearanceAgent;
	}
	public void setClearanceAgent(String clearanceAgent) {
		this.clearanceAgent = clearanceAgent;
	}
	@Column(name = "SECURITY_DEPOSIT_TYPE")
	public String getSecurityDepositType() {
		return securityDepositType;
	}
	public void setSecurityDepositType(String securityDepositType) {
		this.securityDepositType = securityDepositType;
	}
	@Column(name = "PORT_OF_CLEARANCE")
	public String getPortOfClearance() {
		return portOfClearance;
	}
	public void setPortOfClearance(String portOfClearance) {
		this.portOfClearance = portOfClearance;
	}
	@Column(name = "SPARE_PART_CENTER_POSITION")
	public String getSparePartCenterPosition() {
		return sparePartCenterPosition;
	}
	public void setSparePartCenterPosition(String sparePartCenterPosition) {
		this.sparePartCenterPosition = sparePartCenterPosition;
	}
	
	
}