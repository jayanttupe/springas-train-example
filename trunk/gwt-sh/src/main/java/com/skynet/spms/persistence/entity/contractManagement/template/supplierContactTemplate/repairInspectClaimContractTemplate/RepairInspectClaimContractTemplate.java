package com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.repairInspectClaimContractTemplate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.baseCustomerContractTemplate.BaseCustomerContract;
import com.skynet.spms.persistence.entity.csdd.o.OrderTransactionCode;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.spmsdd.ApprovalStage;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairClaimContract.RepairContractItem;

/**
 * 供应商检修合同模板
 * 
 * @author tqc
 * 
 */
@Entity
@Table(name = "SPMS_REPAIRINSCLAIMCONTRACT")
public class RepairInspectClaimContractTemplate extends BaseCustomerContract {

	private String shippingMark;

	/** 检验费用 **/
	private float inspectionCost;

	/** 修理费用 **/
	private float repairPriceAmount;

	/** 其他费用 **/
	private float otherCost;

	/** 合同总金额 **/
	private float extendedValueTotalAmount;

	/** 特殊条款 **/
	private String extraProvision;

	/** 送修合同明细项域 **/
	private RepairContractItem m_RepairContractItem;

	/** 工作任务的优先级 **/
	private Priority m_Priority;

	/** 订单事务代码 **/
	private OrderTransactionCode m_OrderTransactionCode;

	/** 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息 **/
	private Boolean buyerFreightAgent;
	
	/**承修商代码**/
	private RepairShopCode m_RepairShopCode;

	/** 联系人 **/
	private String linkMan;

	/** 联系方式 **/
	private String linkWay;

	/** 运代商联系人 **/
	private String carrierLinkMan;

	/** 运代商联系方式 **/
	private String carrierLinkWay;

	/** 飞机尾号 **/
	private String airIdentificationNumber;

	/** 送修单编号 **/
	private String orderId;
	
	/**客户送修合同编号**/
	private String customerContractID;

	/** 供应商修理记录编号 **/
	private String supplierInspectOutlayRecordId;
	
	/** 审批阶段 **/
	private ApprovalStage approvalStage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "APPROVALSTAGE")
	public ApprovalStage getApprovalStage() {
		return approvalStage;
	}

	public void setApprovalStage(ApprovalStage approvalStage) {
		this.approvalStage = approvalStage;
	}

	@Column(name="CUSTOMERCONTRACTID")
	public String getCustomerContractID() {
		return customerContractID;
	}

	public void setCustomerContractID(String customerContractID) {
		this.customerContractID = customerContractID;
	}

	@ManyToOne
	@JoinColumn(name = "REPAIRSHOPCODE")
	public RepairShopCode getM_RepairShopCode() {
		return m_RepairShopCode;
	}

	public void setM_RepairShopCode(RepairShopCode m_RepairShopCode) {
		this.m_RepairShopCode = m_RepairShopCode;
	}

	@Column(name = "SUPPLIERINSRECORD_ID")
	public String getSupplierInspectOutlayRecordId() {
		return supplierInspectOutlayRecordId;
	}

	public void setSupplierInspectOutlayRecordId(
			String supplierInspectOutlayRecordId) {
		this.supplierInspectOutlayRecordId = supplierInspectOutlayRecordId;
	}

	@Column(name = "BUYERFREIGHTAGENT")
	public Boolean getBuyerFreightAgent() {
		return buyerFreightAgent;
	}

	public void setBuyerFreightAgent(Boolean buyerFreightAgent) {
		this.buyerFreightAgent = buyerFreightAgent;
	}

	@Column(name = "ORDER_ID")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "LINKWAY")
	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	@Column(name = "AIRIDENTIFICATION_NUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(
			String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}

	@Column(name = "LINKMAN")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "EXTRA_PROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	@Column(name = "CARRIER_LINKMAN")
	public String getCarrierLinkMan() {
		return carrierLinkMan;
	}

	public void setCarrierLinkMan(String carrierLinkMan) {
		this.carrierLinkMan = carrierLinkMan;
	}

	@Column(name = "CARRIERLINK_WAY")
	public String getCarrierLinkWay() {
		return carrierLinkWay;
	}

	public void setCarrierLinkWay(String carrierLinkWay) {
		this.carrierLinkWay = carrierLinkWay;
	}

	@Column(name = "SHIPPING_MARK")
	public String getShippingMark() {
		return shippingMark;
	}

	public void setShippingMark(String shippingMark) {
		this.shippingMark = shippingMark;
	}

	@Column(name = "INSPECTION_COST")
	public float getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(float inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Column(name = "REPAIR_RPICE_AMOUNT")
	public float getRepairPriceAmount() {
		return repairPriceAmount;
	}

	public void setRepairPriceAmount(float repairPriceAmount) {
		this.repairPriceAmount = repairPriceAmount;
	}

	@Column(name = "OTHER_COST")
	public float getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(float otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "EXT_VT_AMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPAIRCONTRACTITEM_ID")
	public RepairContractItem getM_RepairContractItem() {
		return m_RepairContractItem;
	}

	public void setM_RepairContractItem(RepairContractItem m_RepairContractItem) {
		this.m_RepairContractItem = m_RepairContractItem;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ORDERTRANSACTIONCODE")
	public OrderTransactionCode getM_OrderTransactionCode() {
		return m_OrderTransactionCode;
	}

	public void setM_OrderTransactionCode(
			OrderTransactionCode m_OrderTransactionCode) {
		this.m_OrderTransactionCode = m_OrderTransactionCode;
	}


}