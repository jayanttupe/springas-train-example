package com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.baseSupplierContractTemplate.BaseSupplierContractTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.o.OrderTransactionCode;
import com.skynet.spms.persistence.entity.csdd.s.SpecifiedShippingMethodCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract.SSLeaseContractItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:26
 */
@Entity
@Table(name = "SPMS_SSLEASECONTRACTTEMPLATE")
public class SSLeaseContractTemplate extends BaseSupplierContractTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 唛头 **/
	private String shippMark;
	/** 交货日期 **/
	private Date dateOfDelivery;
	/** 合同总金额 **/
	private Float extendedValueTotalAmount = 0.0f;
	/** 是否分期付款 **/
	private String Installment;
	/** 是否仅送检 **/
	private Boolean Inspection;
	/**
	 * True标识本合同依赖客户的送修合同，False标识本合同不依赖与其他合同。
	 */
	private Boolean DependCustomer;

	/** 政府企业代码 **/
	private CAGECode buyer;
	/** 收货地址 **/
	// private ConsigneeAddress m_ConsigneeAddress;
	/** 订单事务代码 **/
	private OrderTransactionCode m_OrderTransactionCode;
	/** 运代商 **/
	private CarrierName m_CarrierName;
	/** 用户指定发运方式代码 **/
	private SpecifiedShippingMethodCode m_SpecifiedShippingMethodCode;
	/****/
	// public LeasePickupOrder m_LeasePickupOrder;
	/** 收发票地址 **/
	// private RecipeInvoiceAddress m_RecipeInvoiceAddress;
	/** 供应商代码 **/
	private SupplierCode seller;
	/** 供应商 联系人 **/
	private String linkman;
	/** 供应商联系方式 **/
	private String contactInformation;
	/** 银行账户信息 **/
	// private BankInformation m_BankInformation;
	// public AskCustomerConfirmLeaseContractOrder
	// m_AskCustomerConfirmLeaseContractOrder;
	/** 发货地址 **/
	// private ShippingAddress m_ShippingAddress;
	private List<SSLeaseContractItem> m_LeaseContractItem;
	private Priority m_Priority;

	// public LeaseDeliveryOrder m_LeaseDeliveryOrder;
	/** 合同条款 **/
	// private List<ContractProvision> m_ContractProvision;
	/** 特殊条款 **/
	private String extraProvision;
	/** 项数总计 **/
	private Integer quantity;

	/** 飞机机尾号 **/
	private String aiNumber;
	/** 支付说明 **/
	private String paymentExplain;
	
	/**申请租赁指令编号**/
	private String orderNumber;

	@Column(name = "ORDERNUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "PAYMENTEXPLAIN")
	public String getPaymentExplain() {
		return paymentExplain;
	}

	public void setPaymentExplain(String paymentExplain) {
		this.paymentExplain = paymentExplain;
	}

	private String customerLeaseContractId;

	@Column(name = "CUSTOMERLEASECONTRACTID")
	public String getCustomerLeaseContractId() {
		return customerLeaseContractId;
	}

	public void setCustomerLeaseContractId(String customerLeaseContractId) {
		this.customerLeaseContractId = customerLeaseContractId;
	}

	@Column(name = "AINUMBER")
	public String getAiNumber() {
		return aiNumber;
	}

	public void setAiNumber(String aiNumber) {
		this.aiNumber = aiNumber;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "CONTACTINFORMATION")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Column(name = "EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	@Column(name = "SHIPPMARK")
	public String getShippMark() {
		return shippMark;
	}

	public void setShippMark(String shippMark) {
		this.shippMark = shippMark;
	}

	@Column(name = "DATEOFDELIVERY")
	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	@Column(name = "EXTENDVALANT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	@Column(name = "EXTENDEDVALUETOTALAMOUNT")
	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(Float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Column(name = "INSTALLMENT")
	public String getInstallment() {
		return Installment;
	}

	public void setInstallment(String installment) {
		Installment = installment;
	}

	@Column(name = "INSPECTION")
	public Boolean getInspection() {
		return Inspection;
	}

	public void setInspection(Boolean inspection) {
		Inspection = inspection;
	}

	@Column(name = "DEPENDCUSTOMER")
	public Boolean getDependCustomer() {
		return DependCustomer;
	}

	public void setDependCustomer(Boolean dependCustomer) {
		DependCustomer = dependCustomer;
	}

	@OneToOne
	@JoinColumn(name = "BUYER_ID")
	public CAGECode getBuyer() {
		return buyer;
	}

	public void setBuyer(CAGECode buyer) {
		this.buyer = buyer;
	}

	// @OneToOne
	// @JoinColumn(name = "M_CONSIGNEEADDRESS")
	// public ConsigneeAddress getM_ConsigneeAddress() {
	// return m_ConsigneeAddress;
	// }
	//
	// public void setM_ConsigneeAddress(ConsigneeAddress m_ConsigneeAddress) {
	// this.m_ConsigneeAddress = m_ConsigneeAddress;
	// }

	@Enumerated(EnumType.STRING)
	@Column(name = "M_ORDERTRANSACTIONCODE")
	public OrderTransactionCode getM_OrderTransactionCode() {
		return m_OrderTransactionCode;
	}

	public void setM_OrderTransactionCode(
			OrderTransactionCode m_OrderTransactionCode) {
		this.m_OrderTransactionCode = m_OrderTransactionCode;
	}

	@OneToOne
	@JoinColumn(name = "M_CARRIERNAME")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}

	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_SPEFIEPPINGHODE")
	public SpecifiedShippingMethodCode getM_SpecifiedShippingMethodCode() {
		return m_SpecifiedShippingMethodCode;
	}

	public void setM_SpecifiedShippingMethodCode(
			SpecifiedShippingMethodCode m_SpecifiedShippingMethodCode) {
		this.m_SpecifiedShippingMethodCode = m_SpecifiedShippingMethodCode;
	}

	// @OneToOne
	// @JoinColumn(name = "M_RECIPEINVOICEADDRESS")
	// public RecipeInvoiceAddress getM_RecipeInvoiceAddress() {
	// return m_RecipeInvoiceAddress;
	// }
	//
	// public void setM_RecipeInvoiceAddress(
	// RecipeInvoiceAddress m_RecipeInvoiceAddress) {
	// this.m_RecipeInvoiceAddress = m_RecipeInvoiceAddress;
	// }

	@ManyToOne
	@JoinColumn(name = "SELLER")
	public SupplierCode getSeller() {
		return seller;
	}

	public void setSeller(SupplierCode seller) {
		this.seller = seller;
	}

	// @OneToOne
	// @JoinColumn(name = "M_BANKINFORMATION")
	// public BankInformation getM_BankInformation() {
	// return m_BankInformation;
	// }
	//
	// public void setM_BankInformation(BankInformation m_BankInformation) {
	// this.m_BankInformation = m_BankInformation;
	// }

	// @OneToOne
	// @JoinColumn(name = "M_SHIPPINGADDRESS")
	// public ShippingAddress getM_ShippingAddress() {
	// return m_ShippingAddress;
	// }
	//
	// public void setM_ShippingAddress(ShippingAddress m_ShippingAddress) {
	// this.m_ShippingAddress = m_ShippingAddress;
	// }

	@OneToMany(mappedBy = "ssleaseContractTemplate")
	public List<SSLeaseContractItem> getM_LeaseContractItem() {
		return m_LeaseContractItem;
	}

	public void setM_LeaseContractItem(
			List<SSLeaseContractItem> m_LeaseContractItem) {
		this.m_LeaseContractItem = m_LeaseContractItem;
	}

	@Enumerated(EnumType.STRING)
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}
	// public List<ContractProvision> getM_ContractProvision() {
	// return m_ContractProvision;
	// }
	//
	// public void setM_ContractProvision(
	// List<ContractProvision> m_ContractProvision) {
	// this.m_ContractProvision = m_ContractProvision;
	// }

}