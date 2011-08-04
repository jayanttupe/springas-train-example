package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseCustomsDeclaration.baseCustomsDeclarationRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author FANYX
 * @version 1.0   基础报关记录
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BaseCustomsDeclarationRecord extends BaseEntity{

	/**
	 * 批准文号
	 */
	private String approvalNumber;
	/**
	 * 经营单位
	 */
	private String businessUnit;
	/**
	 * 收货单位
	 */
	private String consignee;
	/**
	 * 集装箱号
	 */
	private String containerNumber;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 海关编号
	 */
	private String customsNumber;
	/**
	 *备案号 
	 */
	private String customsRecordationNumber;
	/**
	 * 申报日期
	 */
	private Date declarationDate;
	/**
	 * 运费
	 */
	private String freight;
	/**
	 * 毛重(kg)
	 */
	private String grossWeight;
	/**
	 * 保费
	 */
	private String insurancePremium;
	/**
	 * 征税比例(%)
	 */
	private String levyProportion;
	/**
	 * 许可证号
	 */
	private String licenseNumber;
	/**
	 * 杂费
	 */
	private String miscellaneousFees;
	/**
	 * 净重(kg)
	 */
	private String netWeight;
	/**
	 * 件数
	 */
	private String numberOfPackage;
	/**
	 * 指令ID
	 */
	private String orderID;
	/**
	 * 指令编号
	 */
	private String orderNumber;
	/**
	 * 包装种类
	 */
	private String packageType;
	/**
	 * 预录入编号
	 */
	private String preEntryNumber;
	/**
	 * 用途
	 */
	private String purpose;
	/**
	 * 备注
	 */
	private String remarkText;
	/**
	 * 运输工具班次
	 */
	private String transportName;
	/**
	 * 单证状态实体
	 */
	private String documentStatusEntity;
	/**
	 * 征减免税方式代码
	 */
	private String freeOfTaxCode;
	/**
	 *运输方式代码 
	 */
	private String transportationCode;
	/**
	 *交货方式 
	 */
	private String deliveryType;
	/**
	 *贸易方式
	 */
	private String tradeMethods;
	/**
	 *报关代理商
	 */
	private String clearanceAgent;
	/**
	 * 主运单号
	 */
	private String mainWayBill;

	/**
	 * 分运单号
	 */
	private String houseWayBill;
    /**
     * (0:即不是保证金也不是关税)/(1:保证金)/(2:关税)
     */
    private String securityOrCustomsTariffName;
	
	@Column(name = "APPROVAL_NUMBER")
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	@Column(name = "BUSINESS_UNIT")
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	@Column(name = "CONSIGNEE")
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	@Column(name = "CONTAINER_NUMBER")
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column(name = "CUSTOMS_NUMBER")
	public String getCustomsNumber() {
		return customsNumber;
	}
	public void setCustomsNumber(String customsNumber) {
		this.customsNumber = customsNumber;
	}
	@Column(name = "CUSTOMS_RECORDATION_NUMBER")
	public String getCustomsRecordationNumber() {
		return customsRecordationNumber;
	}
	public void setCustomsRecordationNumber(String customsRecordationNumber) {
		this.customsRecordationNumber = customsRecordationNumber;
	}
	@Column(name = "DECLARATION_DATE")
	public Date getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	@Column(name = "FREIGHT")
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	@Column(name = "GROSS_WEIGHT")
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	@Column(name = "INSURANCE_PREMIUM")
	public String getInsurancePremium() {
		return insurancePremium;
	}
	public void setInsurancePremium(String insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	@Column(name = "LEVY_PROPORTION")
	public String getLevyProportion() {
		return levyProportion;
	}
	public void setLevyProportion(String levyProportion) {
		this.levyProportion = levyProportion;
	}
	@Column(name = "LICENSE_NUMBER")
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	@Column(name = "MISCELLANEOUS_FEES")
	public String getMiscellaneousFees() {
		return miscellaneousFees;
	}
	public void setMiscellaneousFees(String miscellaneousFees) {
		this.miscellaneousFees = miscellaneousFees;
	}
	@Column(name = "NET_WEIGHT")
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	@Column(name = "NUMBER_OF_PACKAGE")
	public String getNumberOfPackage() {
		return numberOfPackage;
	}
	public void setNumberOfPackage(String numberOfPackage) {
		this.numberOfPackage = numberOfPackage;
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
	@Column(name = "PACKAGE_TYPE")
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	@Column(name = "PRE_ENTITY_NUMBER")
	public String getPreEntryNumber() {
		return preEntryNumber;
	}
	public void setPreEntryNumber(String preEntryNumber) {
		this.preEntryNumber = preEntryNumber;
	}
	@Column(name = "PURPOSE")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Column(name = "TRANSPORT_NAME")
	public String getTransportName() {
		return transportName;
	}
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	@Column(name = "DOCUMENT_STATUS_ENTITY")
	public String getDocumentStatusEntity() {
		return documentStatusEntity;
	}
	public void setDocumentStatusEntity(String documentStatusEntity) {
		this.documentStatusEntity = documentStatusEntity;
	}
	@Column(name = "FREE_OF_TAX_CODE")
	public String getFreeOfTaxCode() {
		return freeOfTaxCode;
	}
	public void setFreeOfTaxCode(String freeOfTaxCode) {
		this.freeOfTaxCode = freeOfTaxCode;
	}
	@Transient
	public String getTransportationCode() {
		return transportationCode;
	}
	public void setTransportationCode(String transportationCode) {
		this.transportationCode = transportationCode;
	}
	@Transient
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	@Transient
	public String getTradeMethods() {
		return tradeMethods;
	}
	public void setTradeMethods(String tradeMethods) {
		this.tradeMethods = tradeMethods;
	}
	@Transient
	public String getClearanceAgent() {
		return clearanceAgent;
	}
	public void setClearanceAgent(String clearanceAgent) {
		this.clearanceAgent = clearanceAgent;
	}
	@Transient
	public String getMainWayBill() {
		return mainWayBill;
	}
	public void setMainWayBill(String mainWayBill) {
		this.mainWayBill = mainWayBill;
	}
	@Transient
	public String getHouseWayBill() {
		return houseWayBill;
	}
	public void setHouseWayBill(String houseWayBill) {
		this.houseWayBill = houseWayBill;
	}
	@Transient
	public String getSecurityOrCustomsTariffName() {
		return securityOrCustomsTariffName;
	}
	public void setSecurityOrCustomsTariffName(String securityOrCustomsTariffName) {
		this.securityOrCustomsTariffName = securityOrCustomsTariffName;
	}
}