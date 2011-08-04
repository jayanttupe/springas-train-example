package com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.RepaireContractTemplate;

import java.util.Date;
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
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.o.OrderTransactionCode;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.RepairContractItem;
import com.skynet.spms.persistence.entity.spmsdd.ApprovalStage;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 客户送修送检合同模板
 * 
 * @author tqc
 * 
 */
@Entity
@Table(name = "SPMS_REPAIRCONTRACTTEMPLATE")
public class RepairContractTemplate extends BaseCustomerContract {

	private String shippingMark;

	/** 修理备件送至承修商检修的到货日期 **/
	private Date inspectionDate;

	/** 送检之后，检修报告的日期。该日期可以在与承修商确认之后再进行合同补充方式进行填写 **/
	private Date dateOfRepairReport;

	/** 客户的送修件返库的日期，该日期可以在承修商给出修理报告之后，且上客服与客户确定合同修理费用之后在填写 **/
	private Date returnDate;

	/** 检验费用 **/
	private Float inspectionCost;

	/** 修理费用 **/
	private Float repairPriceAmount;

	/** 其他费用 **/
	private Float otherCost;

	/** 合同总金额 **/
	private Float extendedValueTotalAmount=0.0f;

	/** 是否分期付款 **/
	private boolean isInstallment;

	/** 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息 **/
	private Boolean buyerFreightAgent;

	/**
	 * 是否客户确认是在客户送修合同，交换合同中出现的一个确认标识，一旦客户确认合同中的条款以及附件信息，可通过客户业务确认函来确认标识。
	 * 该标识为True状态时表示客户 已经确认，否则标识为False状态，一旦合同客户未能确认将影响合同的正常执行流程。
	 **/
	private String isCustomerConfirm;

	/** 特殊条款 **/
	private String extraProvision;

	/** 送修合同明细项域 **/
	private RepairContractItem m_RepairContractItem;

	/** 工作任务的优先级 **/
	private Priority m_Priority;

	/** 订单事务代码 **/
	private OrderTransactionCode m_OrderTransactionCode;

	/** 客户识别代码 **/
	private CustomerIdentificationCode buyer;

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

	/** 审批阶段 **/
	private ApprovalStage approvalStage;
	
	/**上传了客户确认修理函 0:(否) 1:(是)**/
	private Integer uploadedCusAttach=0;

	/** 供应商合同主键 **/
	private String suppContractId;

	/** 供应商合同编号 **/
	private String suppContractNumber;
	
	@Column(name = "UPLOADEDCUSATTACH")
	public Integer getUploadedCusAttach() {
		return uploadedCusAttach;
	}

	public void setUploadedCusAttach(Integer uploadedCusAttach) {
		this.uploadedCusAttach = uploadedCusAttach;
	}

	@Column(name = "SUPPCONTRACTID")
	public String getSuppContractId() {
		return suppContractId;
	}

	public void setSuppContractId(String suppContractId) {
		this.suppContractId = suppContractId;
	}

	@Column(name = "SUPPCONTRACTNUMBER")
	public String getSuppContractNumber() {
		return suppContractNumber;
	}

	public void setSuppContractNumber(String suppContractNumber) {
		this.suppContractNumber = suppContractNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "APPROVALSTAGE")
	public ApprovalStage getApprovalStage() {
		return approvalStage;
	}

	public void setApprovalStage(ApprovalStage approvalStage) {
		this.approvalStage = approvalStage;
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

	public void setAirIdentificationNumber(String airIdentificationNumber) {
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

	@Column(name = "INSPECTION_DATE")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	@Column(name = "DATE_REPAIR_REPORT")
	public Date getDateOfRepairReport() {
		return dateOfRepairReport;
	}

	public void setDateOfRepairReport(Date dateOfRepairReport) {
		this.dateOfRepairReport = dateOfRepairReport;
	}

	@Column(name = "RETURN_DATE")
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "INSPECTION_COST")
	public Float getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(Float inspectionCost) {
		this.inspectionCost = inspectionCost;
	}

	@Column(name = "REPAIR_RPICE_AMOUNT")
	public Float getRepairPriceAmount() {
		return repairPriceAmount;
	}

	public void setRepairPriceAmount(Float repairPriceAmount) {
		this.repairPriceAmount = repairPriceAmount;
	}

	@Column(name = "OTHER_COST")
	public Float getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Float otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "EXT_VT_AMOUNT")
	public Float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(Float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Column(name = "IS_INSTALLMENT")
	public boolean isInstallment() {
		return isInstallment;
	}

	public void setInstallment(boolean isInstallment) {
		this.isInstallment = isInstallment;
	}

	@Column(name = "IS_BUYER_FA")
	public Boolean getBuyerFreightAgent() {
		return buyerFreightAgent;
	}

	public void setBuyerFreightAgent(Boolean buyerFreightAgent) {
		this.buyerFreightAgent = buyerFreightAgent;
	}

	@Column(name = "IS_CUSTOMER_CM")
	public String getIsCustomerConfirm() {
		return isCustomerConfirm;
	}

	public void setIsCustomerConfirm(String isCustomerConfirm) {
		this.isCustomerConfirm = isCustomerConfirm;
	}

	/*
	 * @OneToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "REPAIR_REG_SHEET") public RepairRegisterSheet
	 * getM_RepairRegisterSheet() { return m_RepairRegisterSheet; }
	 * 
	 * public void setM_RepairRegisterSheet( RepairRegisterSheet
	 * m_RepairRegisterSheet) { this.m_RepairRegisterSheet =
	 * m_RepairRegisterSheet; }
	 */
	/*
	 * @OneToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "CUSTOMER_RI_ORDER_ID") public
	 * CustomerRepairInspectionOrder getM_CustomerRepairInspectionOrder() {
	 * return m_CustomerRepairInspectionOrder; }
	 * 
	 * public void setM_CustomerRepairInspectionOrder(
	 * CustomerRepairInspectionOrder m_CustomerRepairInspectionOrder) {
	 * this.m_CustomerRepairInspectionOrder = m_CustomerRepairInspectionOrder; }
	 */

	/*
	 * @OneToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "REPAIRCONFIRMATION_T_ID") public RepairConfirmation
	 * getM_RepairConfirmation() { return m_RepairConfirmation; }
	 * 
	 * public void setM_RepairConfirmation(RepairConfirmation
	 * m_RepairConfirmation) { this.m_RepairConfirmation = m_RepairConfirmation;
	 * }
	 */

	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "REPAIRQUOTESHEET_T_ID") public RepairQuoteSheet
	 * getM_RepairQuoteSheet() { return m_RepairQuoteSheet; }
	 * 
	 * public void setM_RepairQuoteSheet(RepairQuoteSheet m_RepairQuoteSheet) {
	 * this.m_RepairQuoteSheet = m_RepairQuoteSheet; }
	 * 
	 * @OneToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "INSPECTOUTLAYRECORD_T_ID") public InspectOutlayRecord
	 * getM_InspectOutlayRecord() { return m_InspectOutlayRecord; }
	 * 
	 * public void setM_InspectOutlayRecord( InspectOutlayRecord
	 * m_InspectOutlayRecord) { this.m_InspectOutlayRecord =
	 * m_InspectOutlayRecord; }
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPAIRCONTRACTITEM_ID")
	public RepairContractItem getM_RepairContractItem() {
		return m_RepairContractItem;
	}

	public void setM_RepairContractItem(RepairContractItem m_RepairContractItem) {
		this.m_RepairContractItem = m_RepairContractItem;
	}

	/*
	 * @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "BANKINFORMATION_ID") public BankInformation
	 * getBankInformation() { return bankInformation; }
	 * 
	 * public void setBankInformation(BankInformation bankInformation) {
	 * this.bankInformation = bankInformation; }
	 */

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

	@ManyToOne
	@JoinColumn(name = "CUSTOMERIDENTIFICATIONCODE")
	public CustomerIdentificationCode getBuyer() {
		return buyer;
	}

	public void setBuyer(CustomerIdentificationCode buyer) {
		this.buyer = buyer;
	}

}