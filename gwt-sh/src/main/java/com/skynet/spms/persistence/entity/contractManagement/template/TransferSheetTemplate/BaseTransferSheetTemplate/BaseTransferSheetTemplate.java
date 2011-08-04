package com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.BaseTransferSheetTemplate;
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
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:05
 */
@MappedSuperclass
public class BaseTransferSheetTemplate extends BaseContactEntity {

	/**调拨单编号重载基础合同实体的合同编号**/
	private String transferSheetNumber;
	
	/** 交货方式 **/
	private DeliveryType m_DeliveryType;
	
	/***运输方式**/
	private TransportationCode m_TransportationCode;
	
	/** 贸易方式 **/
	private TradeMethods m_TradeMethods;
	
	/***结汇支付方式**/
	private Payment m_Payment;
	
	/**企业GTA协议**/
	private String m_EnterpriseGTA;
	
	/**币种*/
	private InternationalCurrencyCode m_InternationalCurrencyCode;
	
	
	//private List<ApprovalRecord> m_ApprovalRecord;
	

	
	
	@Column(name="ENTERPRISE_GTA")
	public String getM_EnterpriseGTA() {
		return m_EnterpriseGTA;
	}
	public void setM_EnterpriseGTA(String enterpriseGTA) {
		m_EnterpriseGTA = enterpriseGTA;
	}
	@Column(name="TRANSFER_SHEET_NUMBER")
	public String getTransferSheetNumber() {
		return transferSheetNumber;
	}
	public void setTransferSheetNumber(String transferSheetNumber) {
		this.transferSheetNumber = transferSheetNumber;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "INTERNATIONAL_CURRENCY_CODE")
	public InternationalCurrencyCode getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(
			InternationalCurrencyCode internationalCurrencyCode) {
		m_InternationalCurrencyCode = internationalCurrencyCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSPORTATION_CODE")
	public TransportationCode getM_TransportationCode() {
		return m_TransportationCode;
	}
	public void setM_TransportationCode(TransportationCode transportationCode) {
		m_TransportationCode = transportationCode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PAY_MENT")
	public Payment getM_Payment() {
		return m_Payment;
	}
	public void setM_Payment(Payment payment) {
		m_Payment = payment;
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
	@Column(name = "DELIVERYTYPE")
	public DeliveryType getM_DeliveryType() {
		return m_DeliveryType;
	}

	public void setM_DeliveryType(DeliveryType m_DeliveryType) {
		this.m_DeliveryType = m_DeliveryType;
	}
	
	

}