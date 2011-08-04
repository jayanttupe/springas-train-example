package com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.baseCustomerContractTemplate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate.BaseContactEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.spmsdd.DeliveryType;
import com.skynet.spms.persistence.entity.spmsdd.Payment;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;

/**
 * 基础客户合同模板域
 * 
 * @author tqc
 */
@MappedSuperclass
public class BaseCustomerContract extends BaseContactEntity {

	/** 合同有效开始日期 **/
	private Date startDate;

	/** 合同有效终止日期 **/
	private Date endDate;

	/** 指定运代商联系人 **/
	private String appointForwarderLinkman;

	/** 指定运代商联系方式 **/
	private String appointForwarderContact;

	/** 运输方式 **/
	private TransportationCode m_TransportationCode;

	/** 贸易方式 **/
	private TradeMethods m_TradeMethods;

	/** 支付方式 **/
	private Payment m_Payment;

	/** 支付说明 **/
	private String paymentExplain;

	/** 交货方式 **/
	private DeliveryType m_DeliveryType;

	/** 币种 **/
	private InternationalCurrencyCode m_InternationalCurrencyCode;

	/** 企业GTA协议 GTA ids **/
	private String enterpriseIds;

	/** 飞机机尾号 **/
	private String aircraftNumber;

	/** 运代商 **/
	private CarrierName carrierName;

	/** 客户订单编号(关联单据外键) **/
	private String rqId;

	@ManyToOne
	@JoinColumn(name = "CARRIERNAME_ID")
	public CarrierName getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(CarrierName carrierName) {
		this.carrierName = carrierName;
	}

	@Column(name = "RQ_ID")
	public String getRqId() {
		return rqId;
	}

	public void setRqId(String rqId) {
		this.rqId = rqId;
	}

	@Column(name = "AIRCRAFTNUMBER")
	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	@Lob
	@Column(name = "ENTERPRISE_IDS")
	public String getEnterpriseIds() {
		return enterpriseIds;
	}

	public void setEnterpriseIds(String enterpriseIds) {
		this.enterpriseIds = enterpriseIds;
	}

	@Column(name = "PAYMENT_EXPLAIN")
	public String getPaymentExplain() {
		return paymentExplain;
	}

	public void setPaymentExplain(String paymentExplain) {
		this.paymentExplain = paymentExplain;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSPORTATIONCODE")
	public TransportationCode getM_TransportationCode() {
		return m_TransportationCode;
	}

	public void setM_TransportationCode(TransportationCode m_TransportationCode) {
		this.m_TransportationCode = m_TransportationCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TRADEMETHODS")
	public TradeMethods getM_TradeMethods() {
		return m_TradeMethods;
	}

	public void setM_TradeMethods(TradeMethods m_TradeMethods) {
		this.m_TradeMethods = m_TradeMethods;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENT")
	public Payment getM_Payment() {
		return m_Payment;
	}

	public void setM_Payment(Payment m_Payment) {
		this.m_Payment = m_Payment;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "DELIVERYTYPE")
	public DeliveryType getM_DeliveryType() {
		return m_DeliveryType;
	}

	public void setM_DeliveryType(DeliveryType m_DeliveryType) {
		this.m_DeliveryType = m_DeliveryType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONALCURRENCYCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

	@Column(name = "STARTDATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "APPOINLINKMAN")
	public String getAppointForwarderLinkman() {
		return appointForwarderLinkman;
	}

	public void setAppointForwarderLinkman(String appointForwarderLinkman) {
		this.appointForwarderLinkman = appointForwarderLinkman;
	}

	@Column(name = "APPOINTCONTACT")
	public String getAppointForwarderContact() {
		return appointForwarderContact;
	}

	public void setAppointForwarderContact(String appointForwarderContact) {
		this.appointForwarderContact = appointForwarderContact;
	}

}