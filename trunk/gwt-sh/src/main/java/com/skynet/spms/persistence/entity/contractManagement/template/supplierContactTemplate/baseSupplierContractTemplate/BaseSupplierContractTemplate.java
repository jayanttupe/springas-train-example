package com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.baseSupplierContractTemplate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate.BaseContactEntity;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.spmsdd.DeliveryType;
import com.skynet.spms.persistence.entity.spmsdd.Payment;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;

/**
 * 基础供应商合同
 * 
 * @author tqc
 */
@MappedSuperclass
public class BaseSupplierContractTemplate extends BaseContactEntity {

	/** 运输方式 **/
	private TransportationCode m_TransportationCode;

	/** 贸易方式 **/
	private TradeMethods m_TradeMethods;

	/** 支付方式 **/
	private Payment m_Payment;

	/** 交货方式 **/
	private DeliveryType deliveryType;

	/** 币种 **/
	private InternationalCurrencyCode m_InternationalCurrencyCode;

	/** 合同有效开始日期 **/
	private Date startDate;

	/** 合同有效终止日期 **/
	private Date endDate;

	// /** 指定 运代商 **/
	// private String appointForwarder;

	/** 指定运代 商联系人 **/
	private String appointForwarderLinkman;

	/** 指定运代 商联系方式 **/
	private String appointForwarderContact;

	/** 是否卖方指定运代 **/
	private Boolean sellerFreightAgent;

	/** 企业GTA协议 GTA ids **/
	private String enterpriseIds;

	private String rqId;
	

	
	@Column(name = "ENTERPRISE_IDS")
	public String getEnterpriseIds() {
		return enterpriseIds;
	}

	public void setEnterpriseIds(String enterpriseIds) {
		this.enterpriseIds = enterpriseIds;
	}

	@Column(name = "RQID")
	public String getRqId() {
		return rqId;
	}

	public void setRqId(String rqId) {
		this.rqId = rqId;
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

	// @Column(name = "APPOINTFORWARDER")
	// public String getAppointForwarder() {
	// return appointForwarder;
	// }
	//
	// public void setAppointForwarder(String appointForwarder) {
	// this.appointForwarder = appointForwarder;
	// }

	@Column(name = "APPOINTFORLINKMAN")
	public String getAppointForwarderLinkman() {
		return appointForwarderLinkman;
	}

	public void setAppointForwarderLinkman(String appointForwarderLinkman) {
		this.appointForwarderLinkman = appointForwarderLinkman;
	}

	@Column(name = "SELLERFREIGHTAGENT")
	public Boolean getSellerFreightAgent() {
		return sellerFreightAgent;
	}

	public void setSellerFreightAgent(Boolean sellerFreightAgent) {
		this.sellerFreightAgent = sellerFreightAgent;
	}

	@Column(name = "APPOINTFORWARDERCONTACT")
	public String getAppointForwarderContact() {
		return appointForwarderContact;
	}

	public void setAppointForwarderContact(String appointForwarderContact) {
		this.appointForwarderContact = appointForwarderContact;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_TRANSPORTATIONCODE")
	public TransportationCode getM_TransportationCode() {
		return m_TransportationCode;
	}

	public void setM_TransportationCode(TransportationCode m_TransportationCode) {
		this.m_TransportationCode = m_TransportationCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_TRADEMETHODS")
	public TradeMethods getM_TradeMethods() {
		return m_TradeMethods;
	}

	public void setM_TradeMethods(TradeMethods m_TradeMethods) {
		this.m_TradeMethods = m_TradeMethods;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PAYMENT")
	public Payment getM_Payment() {
		return m_Payment;
	}

	public void setM_Payment(Payment m_Payment) {
		this.m_Payment = m_Payment;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_DELIVERYTYPE")
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_INCURCYCODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}

	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}

}