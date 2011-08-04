package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;

/**
 * 基础提发货指令，是提货指令及发货指令的抽象父类。
 * 
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BasePickupDeliveryOrder extends BaseEntity {
	/**
	 * 物流任务编号
	 */
	private String logisticsTasksNumber;

	/**
	 * 指令编号
	 */
	private String orderNumber;

	/**
	 * 指令下达人员
	 */
	private String orderedBy;

	/**
	 * 指令描述信息
	 */
	private String description;

	/**
	 * 指定运代商
	 */
	private String appointForwarder;

	/**
	 * 指定运代商联系方式
	 */
	private String appointForwarderContact;

	/**
	 * 指定运代商联系人
	 */
	private String appointForwarderLinkman;

	/**
	 * 合同编号
	 */
	private String contractNumber;

	/** 
	 * 合同主键 
	 */
	private String contractID;

	/**
	 * 合同交货期
	 */
	private Date contractLeadTime;

	/**
	 * 是否买方指定运代
	 */
	private String isAppointFreightAgent;

	/**
	 * 是否保税库发货
	 */
	private String isBonded;

	/**
	 * 是否依赖客户业务合同
	 */
	private String isDependCustomerContract;

	/**
	 * 用户指定发运方式代码
	 */
	private String specifiedShippingMethodCode;

	/**
	 * 运代商ID
	 */
	private String carrierID;
	
	/**
	 * 运代商名称
	 */
	private String carrierName;

	/**
	 * 交货方式
	 */
	private String deliveryType;

	/**
	 * 贸易方式
	 */
	private String tradeMethods;

	/**
	 * 运代费用支付方式
	 */
	private String forwarderPaymentType;

	/**
	 * 运输服务方式
	 */
	private String shippingServiceType;

	/**
	 * 报关代理商
	 */
	private String clearanceAgent;

	/**
	 * 运输方式代码
	 */
	private String transportationCode;

	/**
	 * 优先级
	 */
	private String priority;

	/**
	 * 发货地址详细信息
	 */
	private String addressOfShipper;

	/**
	 * 发货单位
	 */
	private String companyOfShipper;

	/**
	 * 发货人电子邮箱
	 */
	private String mailOfShipper;

	/**
	 * 发货人传真
	 */
	private String faxOfShipper;

	/**
	 * 发货人姓名
	 */
	private String shipper;

	/**
	 * 发货人联系电话
	 */
	private String telephonOfShipper;

	/**
	 * 发件人唛头
	 */
	private String shippingMark;

	/**
	 * 收货地址详细信息
	 */
	private String addressOfConsignee;

	/**
	 * 收货单位
	 */
	private String companyOfConsignee;

	/**
	 * 收货人姓名
	 */
	private String consignee;

	/**
	 * 收货人电子邮箱
	 */
	private String mailOfConsignee;

	/**
	 * 收货人传真
	 */
	private String faxOfConsignee;

	/**
	 * 收货人联系电话
	 */
	private String telephoneOfConsignee;

	/**
	 * 收件人唛头
	 */
	private String consigneeMark;

	/**
	 * 提发货指令类型(1:提货/2:发货)
	 */
	private String pickupDeliveryOrderType;

	/**
	 * 币种
	 */
	private String internationalCurrencyCode;

	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 状态
	 */
	private String status = "1";

	/**
	 * 状态(1:未处理/2:处理中/3:已处理)
	 */
	private String statusName;

	/** 供应商识别代码(add by tz_taiqc) **/
	private String offerCode;

	/**
	 * 是否发布(0:未发布/1:业务已发布/2:物流已发布)
	 */
	private String isPublish = "0";

	@Column(name = "CONTRACT_ID")
	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	@Column(name = "OFFER_CODE")
	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	@Column(name = "LOGISTICS_TASKS_NUMBER")
	public String getLogisticsTasksNumber() {
		return logisticsTasksNumber;
	}

	public void setLogisticsTasksNumber(String logisticsTasksNumber) {
		this.logisticsTasksNumber = logisticsTasksNumber;
	}

	@Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "ORDERED_BY")
	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "APPOINT_FORWARDER")
	public String getAppointForwarder() {
		return appointForwarder;
	}

	public void setAppointForwarder(String appointForwarder) {
		this.appointForwarder = appointForwarder;
	}

	@Column(name = "APPOINT_FORWARDER_CONTACT")
	public String getAppointForwarderContact() {
		return appointForwarderContact;
	}

	public void setAppointForwarderContact(String appointForwarderContact) {
		this.appointForwarderContact = appointForwarderContact;
	}

	@Column(name = "APPOINT_FORWARDER_LINKMAN")
	public String getAppointForwarderLinkman() {
		return appointForwarderLinkman;
	}

	public void setAppointForwarderLinkman(String appointForwarderLinkman) {
		this.appointForwarderLinkman = appointForwarderLinkman;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "CONTRACT_LEAD_TIME")
	public Date getContractLeadTime() {
		return contractLeadTime;
	}

	public void setContractLeadTime(Date contractLeadTime) {
		this.contractLeadTime = contractLeadTime;
	}

	@Column(name = "IS_APPOINT_FREIGHT_AGENT")
	public String getIsAppointFreightAgent() {
		return isAppointFreightAgent;
	}

	public void setIsAppointFreightAgent(String isAppointFreightAgent) {
		this.isAppointFreightAgent = isAppointFreightAgent;
	}

	@Column(name = "IS_BONDED")
	public String getIsBonded() {
		return isBonded;
	}

	public void setIsBonded(String isBonded) {
		this.isBonded = isBonded;
	}

	@Column(name = "IS_DEPEND_CUSTOMER_CONTRACT")
	public String getIsDependCustomerContract() {
		return isDependCustomerContract;
	}

	public void setIsDependCustomerContract(String isDependCustomerContract) {
		this.isDependCustomerContract = isDependCustomerContract;
	}

	@Column(name = "SPECIFIED_SHIPPING_METHOD_CODE")
	public String getSpecifiedShippingMethodCode() {
		return specifiedShippingMethodCode;
	}

	public void setSpecifiedShippingMethodCode(
			String specifiedShippingMethodCode) {
		this.specifiedShippingMethodCode = specifiedShippingMethodCode;
	}

	@Column(name = "CARRIER_ID")
	public String getCarrierID() {
		return carrierID;
	}

	public void setCarrierID(String carrierID) {
		this.carrierID = carrierID;
	}

	@Transient
	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Column(name = "TRADE_METHODS")
	public String getTradeMethods() {
		return tradeMethods;
	}

	public void setTradeMethods(String tradeMethods) {
		this.tradeMethods = tradeMethods;
	}

	@Column(name = "FORWARDER_PAYMENT_TYPE")
	public String getForwarderPaymentType() {
		return forwarderPaymentType;
	}

	public void setForwarderPaymentType(String forwarderPaymentType) {
		this.forwarderPaymentType = forwarderPaymentType;
	}

	@Column(name = "SHIPPING_SERVICE_TYPE")
	public String getShippingServiceType() {
		return shippingServiceType;
	}

	public void setShippingServiceType(String shippingServiceType) {
		this.shippingServiceType = shippingServiceType;
	}

	@Column(name = "CLEARANCE_AGENT")
	public String getClearanceAgent() {
		return clearanceAgent;
	}

	public void setClearanceAgent(String clearanceAgent) {
		this.clearanceAgent = clearanceAgent;
	}

	@Column(name = "TRANSPORTATION_CODE")
	public String getTransportationCode() {
		return transportationCode;
	}

	public void setTransportationCode(String transportationCode) {
		this.transportationCode = transportationCode;
	}

	@Column(name = "PRIORITY")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "ADDRESS_OF_SHIPPER")
	public String getAddressOfShipper() {
		return addressOfShipper;
	}

	public void setAddressOfShipper(String addressOfShipper) {
		this.addressOfShipper = addressOfShipper;
	}

	@Column(name = "COMPANY_OF_SHIPPER")
	public String getCompanyOfShipper() {
		return companyOfShipper;
	}

	public void setCompanyOfShipper(String companyOfShipper) {
		this.companyOfShipper = companyOfShipper;
	}

	@Column(name = "MAIL_OF_SHIPPER")
	public String getMailOfShipper() {
		return mailOfShipper;
	}

	public void setMailOfShipper(String mailOfShipper) {
		this.mailOfShipper = mailOfShipper;
	}

	@Column(name = "FAX_OF_SHIPPER")
	public String getFaxOfShipper() {
		return faxOfShipper;
	}

	public void setFaxOfShipper(String faxOfShipper) {
		this.faxOfShipper = faxOfShipper;
	}

	@Column(name = "SHIPPER")
	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	@Column(name = "TELEPHON_OF_SHIPPER")
	public String getTelephonOfShipper() {
		return telephonOfShipper;
	}

	public void setTelephonOfShipper(String telephonOfShipper) {
		this.telephonOfShipper = telephonOfShipper;
	}

	@Column(name = "ADDRESS_OF_CONSIGNEE")
	public String getAddressOfConsignee() {
		return addressOfConsignee;
	}

	public void setAddressOfConsignee(String addressOfConsignee) {
		this.addressOfConsignee = addressOfConsignee;
	}

	@Column(name = "COMPANY_OF_CONSIGNEE")
	public String getCompanyOfConsignee() {
		return companyOfConsignee;
	}

	public void setCompanyOfConsignee(String companyOfConsignee) {
		this.companyOfConsignee = companyOfConsignee;
	}

	@Column(name = "CONSIGNEE")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name = "MAIL_OF_CONSIGNEE")
	public String getMailOfConsignee() {
		return mailOfConsignee;
	}

	public void setMailOfConsignee(String mailOfConsignee) {
		this.mailOfConsignee = mailOfConsignee;
	}

	@Column(name = "FAX_OF_CONSIGNEE")
	public String getFaxOfConsignee() {
		return faxOfConsignee;
	}

	public void setFaxOfConsignee(String faxOfConsignee) {
		this.faxOfConsignee = faxOfConsignee;
	}

	@Column(name = "SHIPPING_MARK")
	public String getShippingMark() {
		return shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	@Column(name = "TELEPHONE_OF_CONSIGNEE")
	public String getTelephoneOfConsignee() {
		return telephoneOfConsignee;
	}

	public void setTelephoneOfConsignee(String telephoneOfConsignee) {
		this.telephoneOfConsignee = telephoneOfConsignee;
	}

	@Column(name = "PICKUP_DELIVERY_ORDER_TYPE")
	public String getPickupDeliveryOrderType() {
		return pickupDeliveryOrderType;
	}

	public void setPickupDeliveryOrderType(String pickupDeliveryOrderType) {
		this.pickupDeliveryOrderType = pickupDeliveryOrderType;
	}

	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public String getInternationalCurrencyCode() {
		return internationalCurrencyCode;
	}

	public void setInternationalCurrencyCode(String internationalCurrencyCode) {
		this.internationalCurrencyCode = internationalCurrencyCode;
	}

	@Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "STATUS", updatable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "CONSIGNEE_MARK")
	public String getConsigneeMark() {
		return consigneeMark;
	}

	public void setConsigneeMark(String consigneeMark) {
		this.consigneeMark = consigneeMark;
	}

	@Column(name = "IS_PUBLISH")
	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
}