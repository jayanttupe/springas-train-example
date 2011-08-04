package com.skynet.spms.client.vo.contractManagement;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 合同
 * 
 * @author tqc
 * 
 */
public class Contract implements IsSerializable {

	/** 合同主键 **/
	private String id;

	/** 合同编号 **/
	private String contractNumber;

	/** 优先级 **/
	private String m_Priority;

	/** 交货方式 **/
	private String m_DeliveryType;

	/** 贸易方式 **/
	private String m_TradeMethods;

	/** 运输方式 **/
	private String m_TransportationCode;
	
	/**支付方式**/
	private String m_Payment;

	/** 是否指定运代商 **/
	private Boolean isAppointFreightAgent;

	/** 运代商 **/
	private String appointForwarder;

	/** 联系人 **/
	private String appointForwarderLinkman;

	/** 联系方式 **/
	private String appointForwarderContact;

	/** 收货地址 **/
	private ConsigneeAddress consigneeAddress;

	/** 发货地址 **/
	private ShippingAddress shippingAddress;

	/** 收发票地址 **/
	private RecipeInvoiceAddress recipeInvoiceAddress;

	/** 合同总金额 **/
	private Float contractTotalAmount;

	/** 银行账户信息 **/
	private BankInformation bankInformation;

	/** 收款方名称 **/
	private String recieveName;
	
	/**项数总计**/
	private Integer totalCount;
	
	/** 支付说明 **/
	private String paymentSM;
	
	/**币种**/
	private String m_InternationalCurrencyCode;

	/** 合同明细 **/
	private List<ContractItem> contractItems;

	public Contract() {
	}
	
	

	public String getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}



	public void setM_InternationalCurrencyCode(String m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}



	public String getM_Payment() {
		return m_Payment;
	}



	public void setM_Payment(String m_Payment) {
		this.m_Payment = m_Payment;
	}



	public Integer getTotalCount() {
		return totalCount;
	}



	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}



	public String getPaymentSM() {
		return paymentSM;
	}



	public void setPaymentSM(String paymentSM) {
		this.paymentSM = paymentSM;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(String m_Priority) {
		this.m_Priority = m_Priority;
	}

	public String getM_DeliveryType() {
		return m_DeliveryType;
	}

	public void setM_DeliveryType(String m_DeliveryType) {
		this.m_DeliveryType = m_DeliveryType;
	}

	public String getM_TradeMethods() {
		return m_TradeMethods;
	}

	public void setM_TradeMethods(String m_TradeMethods) {
		this.m_TradeMethods = m_TradeMethods;
	}

	public String getM_TransportationCode() {
		return m_TransportationCode;
	}

	public void setM_TransportationCode(String m_TransportationCode) {
		this.m_TransportationCode = m_TransportationCode;
	}

	public List<ContractItem> getContractItems() {
		return contractItems;
	}

	public void setContractItems(List<ContractItem> contractItems) {
		this.contractItems = contractItems;
	}

	public Float getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(Float contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public RecipeInvoiceAddress getRecipeInvoiceAddress() {
		return recipeInvoiceAddress;
	}

	public void setRecipeInvoiceAddress(
			RecipeInvoiceAddress recipeInvoiceAddress) {
		this.recipeInvoiceAddress = recipeInvoiceAddress;
	}

	public String getRecieveName() {
		return recieveName;
	}

	public void setRecieveName(String recieveName) {
		this.recieveName = recieveName;
	}

	public ConsigneeAddress getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(ConsigneeAddress consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public BankInformation getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(BankInformation bankInformation) {
		this.bankInformation = bankInformation;
	}

	public Boolean getIsAppointFreightAgent() {
		return isAppointFreightAgent;
	}

	public void setIsAppointFreightAgent(Boolean isAppointFreightAgent) {
		this.isAppointFreightAgent = isAppointFreightAgent;
	}

	public String getAppointForwarder() {
		return appointForwarder;
	}

	public void setAppointForwarder(String appointForwarder) {
		this.appointForwarder = appointForwarder;
	}

	public String getAppointForwarderLinkman() {
		return appointForwarderLinkman;
	}

	public void setAppointForwarderLinkman(String appointForwarderLinkman) {
		this.appointForwarderLinkman = appointForwarderLinkman;
	}

	public String getAppointForwarderContact() {
		return appointForwarderContact;
	}

	public void setAppointForwarderContact(String appointForwarderContact) {
		this.appointForwarderContact = appointForwarderContact;
	}

}
