package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsTariffRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author FANYX
 * @version 1.0    基础报关关税记录
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BaseCustomsTaiffRecord extends BaseEntity{

	/**
	 * 报关ID（页面）
	 */
	private String customsID;
	/**
	 * 指令ID（页面）
	 */
	private String orderID;
	/**
	 * 预录入编号 （页面）
	 */
	private String prepareEnterNumber;
	/**
	 * 指令单号 （页面）
	 */
	private String orderNumber;
	/**
	 * 申请单位编号
	 */
	private String applicant;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 贸易方式 （页面）
	 */
	private String tradeType;
	/**
	 * 海关编号 （页面）
	 */
	private String customsNumber;
	/**
	 * 运输方式 （页面）
	 */
	private String transportationType;
	/**
	 * 备件中心位置 （页面）
	 */
	private String spareCenterPosition;
	/**
	 * 运单号 （页面）
	 */
	private String trackingNumber;
	/**
	 * 件号 （页面）
	 */
	private String packagesNumber;
	/**
	 * 申报日期 （页面）
	 */
	private Date reportingDate;
	/**
	 * 申报日期
	 */
	private Date declarationDate;
	/**
	 * 汇率
	 */
	private String exchangeRate;
	/**
	 * 分运单号
	 */
	private String houseWaybillNumber;
	/**
	 * 主运单号
	 */
	private String masterWaybillNumber;
	/**
	 * 件数
	 */
	private String numberOfPackage;
	/**
	 * 缴款期限
	 */
	private String payDeadline;
	/**
	 * 填发日期
	 */
	private Date paymentDate;
	/**
	 * 关税单号
	 */
	private String TariffOrder;
	/**
	 * 税款金额
	 */
	private String taxPayment;
	/**
	 * 总金额
	 */
	private String totalAmount;
	/**
	 * 付款状态实体（页面）
	 */
	private String paymentStatusEntity;
	/**
	 * 报关口岸（页面）
	 */
	private String portOfClearance;
	/**
	 * 报关代理商
	 */
	private String clearanceAgent;
	/**
	 * 币种
	 */
	private String internationalCurrencyCode;
	/**
	 * 运输方式代码
	 */
	private String transportationCode;
	
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
	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name = "PREPARE_ENTER_NUMBER")
	public String getPrepareEnterNumber() {
		return prepareEnterNumber;
	}
	public void setPrepareEnterNumber(String prepareEnterNumber) {
		this.prepareEnterNumber = prepareEnterNumber;
	}
	@Column(name = "APPLICANT")
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
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
	@Column(name = "EXCHANGE_RATE")
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
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
	@Column(name = "PAY_DEADLINE")
	public String getPayDeadline() {
		return payDeadline;
	}
	public void setPayDeadline(String payDeadline) {
		this.payDeadline = payDeadline;
	}
	@Column(name = "PAYMENT_DATE")
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Column(name = "TARIFF_ORDER")
	public String getTariffOrder() {
		return TariffOrder;
	}
	public void setTariffOrder(String tariffOrder) {
		TariffOrder = tariffOrder;
	}
	@Column(name = "TAX_PAYMENT")
	public String getTaxPayment() {
		return taxPayment;
	}
	public void setTaxPayment(String taxPayment) {
		this.taxPayment = taxPayment;
	}
	@Column(name = "TOTAL_AMOUNT")
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name = "PAYMENT_STATUS_ENTITY")
	public String getPaymentStatusEntity() {
		return paymentStatusEntity;
	}
	public void setPaymentStatusEntity(String paymentStatusEntity) {
		this.paymentStatusEntity = paymentStatusEntity;
	}
	@Column(name = "PORT_OF_CLEARANCE")
	public String getPortOfClearance() {
		return portOfClearance;
	}
	public void setPortOfClearance(String portOfClearance) {
		this.portOfClearance = portOfClearance;
	}
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public String getInternationalCurrencyCode() {
		return internationalCurrencyCode;
	}
	public void setInternationalCurrencyCode(String internationalCurrencyCode) {
		this.internationalCurrencyCode = internationalCurrencyCode;
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
	@Column(name = "TRADE_TYPE")
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	@Column(name = "CUSTOMS_NUMBER")
	public String getCustomsNumber() {
		return customsNumber;
	}
	public void setCustomsNumber(String customsNumber) {
		this.customsNumber = customsNumber;
	}
	@Column(name = "TRANSPORTATION_TYPE")
	public String getTransportationType() {
		return transportationType;
	}
	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;
	}
	@Column(name = "SPARE_CENTER_POSITION")
	public String getSpareCenterPosition() {
		return spareCenterPosition;
	}
	public void setSpareCenterPosition(String spareCenterPosition) {
		this.spareCenterPosition = spareCenterPosition;
	}
	@Column(name = "TRACKING_NUMBER")
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	@Column(name = "PACKAGES_NUMBER")
	public String getPackagesNumber() {
		return packagesNumber;
	}
	public void setPackagesNumber(String packagesNumber) {
		this.packagesNumber = packagesNumber;
	}
	@Column(name = "REPORTING_DATE")
	public Date getReportingDate() {
		return reportingDate;
	}
	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}
	
}