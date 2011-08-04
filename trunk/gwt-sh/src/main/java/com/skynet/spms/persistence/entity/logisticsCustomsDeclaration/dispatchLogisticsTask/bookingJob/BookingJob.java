package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;
/**
 * 物流委托书实体
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author wangyx
 * @version 1.0
 * @created 25-四月-2011 14:38:04
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_BOOKING_JOB")
public class BookingJob extends BaseEntity {
	/**
	 * 指令ID
	 */
	private String orderID;
	/**
	 * 指令编号
	 */
	private String orderNumber;
	
	/**
	 * 物流任务编号
	 */
	private String logisticsTasksNumber;

	/**
	 * 订舱工作编号
	 */
	private String bookingJobNumber;

	/**
	 * 委托书编号
	 */
	private String forwardingOrderNumber;

	/**
	 * 目的口岸(港)
	 */
	private String portOfDestinat;

	/**
	 * 起运口岸(港)
	 */
	private String portOfShipment;

	/**
	 * 货物箱(件)总数
	 */
	private String billOfLadingContainerCount;

	/**
	 * 运输申报价值
	 */
	private String declaredValueForCarriage;

	/**
	 * 运输班次(航班号/车号/船号)
	 */
	private String numberOfRuns;

	/**
	 * 运输保险价值
	 */
	private String valueOfInsurance;

	/**
	 * 特别要求
	 */
	private String specialRequests;

	/**
	 * 国内/国际
	 */
	private String inlandOrInternational;

	/**
	 * 原产地证书
	 */
	private String certificateOfOrigin;

	/**
	 * 原产地
	 */
	private String countryOfOrigin;

	/**
	 * 海关申报价值
	 */
	private String declaredValueForCustoms;

	/**
	 * 产品许可证号
	 */
	private String productsPermitLicense;

	/**
	 * 币种
	 */
	private String internationalCurrencyCode;

	/**
	 * 订舱单状态(1:未分配/2:处理中/3:处理结束)
	 */
	private String bookingStatus;

	/**
	 * 订舱单状态名称
	 */
	private String bookingStatusName;

	/**
	 * 提发货业务
	 */
	private PickupDeliveryOrder pickupDeliveryOrder;

	public BookingJob() {

	}

	public void finalize() throws Throwable {
		super.finalize();
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

	@Column(name = "LOGISTICS_TASKS_NUMBER")
	public String getLogisticsTasksNumber() {
		return logisticsTasksNumber;
	}

	public void setLogisticsTasksNumber(String logisticsTasksNumber) {
		this.logisticsTasksNumber = logisticsTasksNumber;
	}

	@Column(name = "BOOKING_JOB_NUMBER")
	public String getBookingJobNumber() {
		return bookingJobNumber;
	}

	public void setBookingJobNumber(String bookingJobNumber) {
		this.bookingJobNumber = bookingJobNumber;
	}

	@Column(name = "FORWARDING_ORDER_NUMBER")
	public String getForwardingOrderNumber() {
		return forwardingOrderNumber;
	}

	public void setForwardingOrderNumber(String forwardingOrderNumber) {
		this.forwardingOrderNumber = forwardingOrderNumber;
	}

	@Column(name = "PORT_OF_DESTINAT")
	public String getPortOfDestinat() {
		return portOfDestinat;
	}

	public void setPortOfDestinat(String portOfDestinat) {
		this.portOfDestinat = portOfDestinat;
	}

	@Column(name = "PORT_OF_SHIPMENT")
	public String getPortOfShipment() {
		return portOfShipment;
	}

	public void setPortOfShipment(String portOfShipment) {
		this.portOfShipment = portOfShipment;
	}

	@Column(name = "BILL_OF_LADING_CONTAINER_COUNT")
	public String getBillOfLadingContainerCount() {
		return billOfLadingContainerCount;
	}

	public void setBillOfLadingContainerCount(String billOfLadingContainerCount) {
		this.billOfLadingContainerCount = billOfLadingContainerCount;
	}

	@Column(name = "DECLARED_VALUE_FOR_CARRIAGE")
	public String getDeclaredValueForCarriage() {
		return declaredValueForCarriage;
	}

	public void setDeclaredValueForCarriage(String declaredValueForCarriage) {
		this.declaredValueForCarriage = declaredValueForCarriage;
	}

	@Column(name = "NUMBER_OF_RUNS")
	public String getNumberOfRuns() {
		return numberOfRuns;
	}

	public void setNumberOfRuns(String numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}

	@Column(name = "VALUE_OF_INSURANCE")
	public String getValueOfInsurance() {
		return valueOfInsurance;
	}

	public void setValueOfInsurance(String valueOfInsurance) {
		this.valueOfInsurance = valueOfInsurance;
	}

	@Column(name = "SPECIAL_REQUESTS")
	public String getSpecialRequests() {
		return specialRequests;
	}

	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}

	@Column(name = "INLAND_OR_INTERNATIONAL")
	public String getInlandOrInternational() {
		return inlandOrInternational;
	}

	public void setInlandOrInternational(String inlandOrInternational) {
		this.inlandOrInternational = inlandOrInternational;
	}

	@Column(name = "CERTIFICATE_OF_ORIGIN")
	public String getCertificateOfOrigin() {
		return certificateOfOrigin;
	}

	public void setCertificateOfOrigin(String certificateOfOrigin) {
		this.certificateOfOrigin = certificateOfOrigin;
	}

	@Column(name = "COUNTRY_OF_ORIGIN")
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	@Column(name = "DECLARED_VALUE_FOR_CUSTOMS")
	public String getDeclaredValueForCustoms() {
		return declaredValueForCustoms;
	}

	public void setDeclaredValueForCustoms(String declaredValueForCustoms) {
		this.declaredValueForCustoms = declaredValueForCustoms;
	}

	@Column(name = "PRODUCTS_PERMIT_LICENSE")
	public String getProductsPermitLicense() {
		return productsPermitLicense;
	}

	public void setProductsPermitLicense(String productsPermitLicense) {
		this.productsPermitLicense = productsPermitLicense;
	}

	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public String getInternationalCurrencyCode() {
		return internationalCurrencyCode;
	}

	public void setInternationalCurrencyCode(String internationalCurrencyCode) {
		this.internationalCurrencyCode = internationalCurrencyCode;
	}

	@Column(name = "BOOKING_STATUS", updatable=false)
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingMemoStatus) {
		this.bookingStatus = bookingMemoStatus;
	}

	@Transient
	public String getBookingStatusName() {
		return bookingStatusName;
	}

	public void setBookingStatusName(String bookingStatusName) {
		this.bookingStatusName = bookingStatusName;
	}

	@Transient
	public PickupDeliveryOrder getPickupDeliveryOrder() {
		return pickupDeliveryOrder;
	}

	public void setPickupDeliveryOrder(PickupDeliveryOrder pickupDeliveryOrder) {
		this.pickupDeliveryOrder = pickupDeliveryOrder;
	}
}
