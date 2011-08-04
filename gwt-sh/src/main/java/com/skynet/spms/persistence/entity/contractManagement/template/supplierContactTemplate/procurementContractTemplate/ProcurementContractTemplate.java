package com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.procurementContractTemplate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.baseSupplierContractTemplate.BaseSupplierContractTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.s.SpecifiedShippingMethodCode;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 供应商采购合同 gqr
 * 
 * @author
 */

@Entity
@Table(name = "SPMS_PROCUREMENTTEMPLATE")
public class ProcurementContractTemplate extends BaseSupplierContractTemplate {

	/** 采购指令号 */
	private String procurementPlanNumber;
	/** 采购指令主键 */
	private String procurementPlanUUid;

	/** 唛头 **/
	private String shippMark;

	/** 交货日期 **/
	private Date dateOfDelivery;

	/** 金额总计 **/
	private float extendedValueTotalAmount;

	/** 是否分期付款 **/
	private String isInstallment;

	/** 飞机注册号 **/
	private String registrationNumber;

	/** 合同中明细备件项的总计数目 **/
	private int itemCount;

	/** 优先级 **/
	private Priority m_Priority;

	/** 飞机尾号 **/
	private String m_AircraftIdentificationNumber;

	/** 用户指定发运方式代码 **/
	private SpecifiedShippingMethodCode m_SpecifiedShippingMethodCode;

	/** 运代商 **/
	private CarrierName m_CarrierName;

	/** 供应商代码 **/
	private SupplierCode seller;

	/** 供应商 联系人 **/
	private String sellerContactMan;

	/** 供应商 联系方式 **/
	private String m_CarrierContact;

	/** GTA协议 **/
	private String gta;

	/** 支付说明 **/
	private String paymentSM;

	/** 特殊条件 **/
	private String extraProvision;

	/** 项数总计 **/
	private Integer totalCount;

	@Column(name = "SELLERCONTACTMAN")
	public String getSellerContactMan() {
		return sellerContactMan;
	}

	public void setSellerContactMan(String sellerContactMan) {
		this.sellerContactMan = sellerContactMan;
	}

	@Column(name = "TOTALCOUNT")
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "EXTRAPROVISION")
	public String getExtraProvision() {
		return extraProvision;
	}

	public void setExtraProvision(String extraProvision) {
		this.extraProvision = extraProvision;
	}

	@Column(name = "PAYMENTSM")
	public String getPaymentSM() {
		return paymentSM;
	}

	public void setPaymentSM(String paymentSM) {
		this.paymentSM = paymentSM;
	}

	@Column(name = "GTA")
	public String getGta() {
		return gta;
	}

	public void setGta(String gta) {
		this.gta = gta;
	}

	@Column(name = "SHIPP_MARK")
	public String getShippMark() {
		return shippMark;
	}

	public void setShippMark(String shippMark) {
		this.shippMark = shippMark;
	}

	@Column(name = "CARRIER_CONTACT")
	public String getM_CarrierContact() {
		return m_CarrierContact;
	}

	public void setM_CarrierContact(String m_CarrierContact) {
		this.m_CarrierContact = m_CarrierContact;
	}

	@Column(name = "DETE_OF_DELIVERY")
	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	@Column(name = "EXTENDED_VT_AMOUNT")
	public float getExtendedValueTotalAmount() {
		return extendedValueTotalAmount;
	}

	public void setExtendedValueTotalAmount(float extendedValueTotalAmount) {
		this.extendedValueTotalAmount = extendedValueTotalAmount;
	}

	@Column(name = "IS_INSTALLMENT")
	public String getIsInstallment() {
		return isInstallment;
	}

	public void setIsInstallment(String isInstallment) {
		this.isInstallment = isInstallment;
	}

	@Column(name = "REGISTRATION_NUMBER")
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "ITEM_COUNT")
	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}

	public void setM_Priority(Priority m_Priority) {
		this.m_Priority = m_Priority;
	}

	@Column(name = "AIRCRAFT_IDENTIFICATION_NUMBER")
	public String getM_AircraftIdentificationNumber() {
		return m_AircraftIdentificationNumber;
	}

	public void setM_AircraftIdentificationNumber(
			String m_AircraftIdentificationNumber) {
		this.m_AircraftIdentificationNumber = m_AircraftIdentificationNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "SPECIFIED_SM_CODE")
	public SpecifiedShippingMethodCode getM_SpecifiedShippingMethodCode() {
		return m_SpecifiedShippingMethodCode;
	}

	public void setM_SpecifiedShippingMethodCode(
			SpecifiedShippingMethodCode m_SpecifiedShippingMethodCode) {
		this.m_SpecifiedShippingMethodCode = m_SpecifiedShippingMethodCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SELLER_ID")
	public SupplierCode getSeller() {
		return seller;
	}

	public void setSeller(SupplierCode seller) {
		this.seller = seller;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARRIER_NAME_ID")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}

	public void setM_CarrierName(CarrierName m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}

	@Column(name = "PROCUREMENTPLANNUMBER")
	public String getProcurementPlanNumber() {
		return procurementPlanNumber;
	}

	public void setProcurementPlanNumber(String procurementPlanNumber) {
		this.procurementPlanNumber = procurementPlanNumber;
	}

	@Column(name = "PROCUREMENTPLANUUID")
	public String getProcurementPlanUUid() {
		return procurementPlanUUid;
	}

	public void setProcurementPlanUUid(String procurementPlanUUid) {
		this.procurementPlanUUid = procurementPlanUUid;
	}

}