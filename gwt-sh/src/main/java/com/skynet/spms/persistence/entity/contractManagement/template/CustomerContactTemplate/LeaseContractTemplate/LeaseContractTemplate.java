package com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.baseCustomerContractTemplate.BaseCustomerContract;
import com.skynet.spms.persistence.entity.contractManagement.template.baseContractTemplate.ContractProvision;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.o.OrderTransactionCode;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;
import com.skynet.spms.persistence.entity.customerService.order.requisitionSupplierLeaseOrder.CustomerRequisitionSupplierLeaseOrder;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 实现针对客户租赁合同的模板构建，在本系统中，客户租赁业务可以依据模板来创建所需要客户租赁主合同内容。
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 03-四月-2011 11:32:56
 */
@Entity
@Table(name = "SPMS_LEASECONTRACTTEMPLATE")
public class LeaseContractTemplate extends BaseCustomerContract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 外贸中的&ldquo;唛头&rdquo;
	 * 是运输标志另一种称呼，是为了便于识别货物，防止错发货，通常由型号，图形或收货单位简称，目的港，件数或批号等组成
	 * 。通常是由一个简单的几何图形和一些字母、数字及简单
	 * 的文字组成，其作用在于使货物在装卸、运输、保管过程中容易被有关人员识别，以防错发错运。其主要内容包括-
	 * （1）收货人或买方名称字首（2）参照号码；（
	 * 3）目的港（地）名称；（4）件数、批号。此外，有的运输标志还包括原产地、合同号、许可证号和体积与重量等内容。运输标志
	 * 的内容繁简不一，由买卖双方根据商品特点和具体要求商定。
	 */
	private String shippingMark;
	/**
	 * 合同总金额
	 * **/
	private Float extendedValueTotalAmount = 0.0f;
	/**
	 * 是否分期付款
	 * **/
	private Boolean installment;
	/**
	 * 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息
	 */
	private Boolean buyerFreightAgent;
	/**
	 * 是否客户确认是在客户送修合同，交换合同中出现的一个确认标识，一旦客户确认合同中的条款以及附件信息，可通过客户业务确认函来确认标识。
	 * 该标识为True状态时表示客户 已经确认，否则标识为False状态，一旦合同客户未能确认将影响合同的正常执行流程。
	 */
	private String customerConfirm;
	/**
	 * 是否依赖供应商业务
	 * **/
	private Integer dependSupplier;
	private String aiNumber;
	private ContractProvision m_ContractProvision;
	// private CustomerLeaseAskReturnPickupOrder
	// m_CustomerLeaseAskReturnPickupOrder;
	// private customerLeasePostponeOrder m_customerLeasePostponeOrder;
	// private SupplierLeaseCustomerLeaseDeliveryOrder
	// m_SupplierLeaseCustomerLeaseDeliveryOrder;
	// private CustomerRequisitionSupplierLeaseOrder
	// m_CustomerRequisitionSupplierLeaseOrder;
	// private CustomerLeaseDeliveryOrder m_CustomerLeaseDeliveryOrder;
	// private List<LeaseContractPostponeRequsitionSheet>
	// m_LeaseContractPostponeRequsitionSheet;
	private List<LeaseContractItem> m_LeaseContractItem;

	// private ShippingAddress m_ShippingAddress;
	// private RecipeInvoiceAddress m_RecipeInvoiceAddress;
	// private ConsigneeAddress m_ConsigneeAddress;
	// private BankInformation m_BankInformation;
	private Priority m_Priority;
	private OrderTransactionCode m_OrderTransactionCode;
	private CustomerIdentificationCode buyer;
	private CarrierName m_CarrierName;
	private CAGECode seller;
	/** 租赁申请号 **/
	private String leaseRequisitionNumber;
	/** 联系人 **/
	private String linkman;
	/** 联系方式 **/
	private String contactInformation;
	/** 特殊条款 **/
	private String extraProvision;
	/** 项数总计 **/
	private Integer quantity = 0;

	private CustomerRequisitionSupplierLeaseOrder supplierLeaseOrder;

	@OneToOne(cascade = CascadeType.REMOVE)
	public CustomerRequisitionSupplierLeaseOrder getSupplierLeaseOrder() {
		return supplierLeaseOrder;
	}

	public void setSupplierLeaseOrder(
			CustomerRequisitionSupplierLeaseOrder supplierLeaseOrder) {
		this.supplierLeaseOrder = supplierLeaseOrder;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	@Column(name = "LEASEREQUISITIONNUMBER")
	public String getLeaseRequisitionNumber() {
		return leaseRequisitionNumber;
	}

	public void setLeaseRequisitionNumber(String leaseRequisitionNumber) {
		this.leaseRequisitionNumber = leaseRequisitionNumber;
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

	@Column(name = "SHIPPINGMARK")
	public String getShippingMark() {
		return shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	@Column(name = "EXTENDTALAMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Column(name = "INSTALLMENT")
	public void setInstallment(Boolean installment) {
		this.installment = installment;
	}

	public Boolean getInstallment() {
		return installment;
	}

	@Column(name = "BUYERFREIGHTAGENT")
	public boolean isBuyerFreightAgent() {
		return buyerFreightAgent;
	}

	public void setBuyerFreightAgent(boolean buyerFreightAgent) {
		this.buyerFreightAgent = buyerFreightAgent;
	}

	@Column(name = "CUSTOMERCONFIRM")
	public String getCustomerConfirm() {
		return customerConfirm;
	}

	public void setCustomerConfirm(String customerConfirm) {
		this.customerConfirm = customerConfirm;
	}

	@Column(name = "DEPENDSUPPLIER")
	public Integer getDependSupplier() {
		return dependSupplier;
	}

	public void setDependSupplier(Integer dependSupplier) {
		this.dependSupplier = dependSupplier;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "M_CONTRACTPROVISION_ID")
	public ContractProvision getM_ContractProvision() {
		return m_ContractProvision;
	}

	public void setM_ContractProvision(ContractProvision m_ContractProvision) {
		this.m_ContractProvision = m_ContractProvision;
	}

	// @OneToMany
	// @JoinColumn(name = "LCPRSHEET_ID")
	// public List<LeaseContractPostponeRequsitionSheet>
	// getM_LeaseContractPostponeRequsitionSheet() {
	// return m_LeaseContractPostponeRequsitionSheet;
	// }
	//
	// public void setM_LeaseContractPostponeRequsitionSheet(
	// List<LeaseContractPostponeRequsitionSheet>
	// m_LeaseContractPostponeRequsitionSheet) {
	// this.m_LeaseContractPostponeRequsitionSheet =
	// m_LeaseContractPostponeRequsitionSheet;
	// }

	@OneToMany(mappedBy = "leaseContractTemplate")
	public List<LeaseContractItem> getM_LeaseContractItem() {
		return m_LeaseContractItem;
	}

	public void setM_LeaseContractItem(
			List<LeaseContractItem> m_LeaseContractItem) {
		this.m_LeaseContractItem = m_LeaseContractItem;
	}

	// @OneToOne
	// @JoinColumn(name = "M_SHIPPINGADDRESS_ID")
	// public ShippingAddress getM_ShippingAddress() {
	// return m_ShippingAddress;
	// }
	//
	// public void setM_ShippingAddress(ShippingAddress m_ShippingAddress) {
	// this.m_ShippingAddress = m_ShippingAddress;
	// }

	// @OneToOne
	// @JoinColumn(name = "M_RECIPEINVOICEADDRESS_ID")
	// public RecipeInvoiceAddress getM_RecipeInvoiceAddress() {
	// return m_RecipeInvoiceAddress;
	// }
	//
	// public void setM_RecipeInvoiceAddress(
	// RecipeInvoiceAddress m_RecipeInvoiceAddress) {
	// this.m_RecipeInvoiceAddress = m_RecipeInvoiceAddress;
	// }
	//
	// @OneToOne
	// @JoinColumn(name = "M_CONSIGNEEADDRESS_ID")
	// public ConsigneeAddress getM_ConsigneeAddress() {
	// return m_ConsigneeAddress;
	// }
	//
	// public void setM_ConsigneeAddress(ConsigneeAddress m_ConsigneeAddress) {
	// this.m_ConsigneeAddress = m_ConsigneeAddress;
	// }

	// @OneToOne
	// @JoinColumn(name = "M_BANKINFORMATION_ID")
	// public BankInformation getM_BankInformation() {
	// return m_BankInformation;
	// }
	//
	// public void setM_BankInformation(BankInformation m_BankInformation) {
	// this.m_BankInformation = m_BankInformation;
	// }

	@Enumerated(EnumType.STRING)
	@Column(name = "M_PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_ORDERTRANSACTIONCODE")
	public OrderTransactionCode getM_OrderTransactionCode() {
		return m_OrderTransactionCode;
	}

	public void setM_OrderTransactionCode(
			OrderTransactionCode m_OrderTransactionCode) {
		this.m_OrderTransactionCode = m_OrderTransactionCode;
	}

	@ManyToOne
	@JoinColumn(name = "BUYER_ID")
	public CustomerIdentificationCode getBuyer() {
		return buyer;
	}

	public void setBuyer(CustomerIdentificationCode buyer) {
		this.buyer = buyer;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "M_CARRIERNAME")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}

	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SELLER")
	public CAGECode getSeller() {
		return seller;
	}

	public void setSeller(CAGECode seller) {
		this.seller = seller;
	}

	@Column(name = "AINUMBER")
	public String getAiNumber() {
		return aiNumber;
	}

	public void setAiNumber(String aiNumber) {
		this.aiNumber = aiNumber;
	}

}