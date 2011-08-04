package com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.BaseTransferSheetTemplate.BaseTransferSheetTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:38
 */
@Entity
@Table(name = "SPMS_PRO_TRANSFER")
public class ProcurementTransferSheet extends
		BaseTransferSheetTemplate {

	// public PickupOrder m_PickupOrder;
	
	/**采购指令编号**/
	private String orderNumber;
	/**运代商*/
	private CarrierName m_CarrierName;
	/**运代商联系人**/
	private String carrierLinkman;
	/**运代商联系地址*/
	private String carrierContactInformation;
	
	/**由调拨来源制定运代**/
	private Boolean CAGEAssignCarrier;
	
	/**发货地址*/
	//private ShippingAddress m_ShippingAddress;
	/**收货地址*/
	//private ConsigneeAddress m_ConsigneeAddress;
	/**调拨来源企业**/
	private CAGECode transferFrom;
	/**优先级*/
	private Priority m_Priority;
	/** 机尾号* */
	private String airIdentificationNumber;
	
	/** 如果是非系统登录用户，可以填写联系人姓名，做为联系方式 * */
	private String linkman;
	/** 联系方式 * */
	private String contactInformation;
	
	/** 单位代码 * */
	private UnitOfMeasureCode m_UnitOfMeasureCode;
	
	

	/**采购订单**/
	//private ProcurementOrder procurementOrder;
	
	private Integer itemCount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "UNIT_CODE")
	public UnitOfMeasureCode getM_UnitOfMeasureCode() {
		return m_UnitOfMeasureCode;
	}

	public void setM_UnitOfMeasureCode(UnitOfMeasureCode m_UnitOfMeasureCode) {
		this.m_UnitOfMeasureCode = m_UnitOfMeasureCode;
	}
	
	@Column(name="ITEM_COUNT")
	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

//	@ManyToOne(cascade= CascadeType.REFRESH)
//	@JoinColumn(name = "PO_ID")
//	public ProcurementOrder getProcurementOrder() {
//		return procurementOrder;
//	}
//
//	public void setProcurementOrder(ProcurementOrder procurementOrder) {
//		this.procurementOrder = procurementOrder;
//	}

	@Column(name = "CONTACT_INFORMATION")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	@Column(name = "LINKMAN")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Column(name = "ORDERNUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
//	@OneToOne
//	@JoinColumn(name="CONSIGNEE_ADDRESS")
//	public ConsigneeAddress getM_ConsigneeAddress() {
//		return m_ConsigneeAddress;
//	}
//	public void setM_ConsigneeAddress(ConsigneeAddress consigneeAddress) {
//		m_ConsigneeAddress = consigneeAddress;
//	}
//	@OneToOne
//	@JoinColumn(name="SHIPPING_ADDRESS")
//	public ShippingAddress getM_ShippingAddress() {
//		return m_ShippingAddress;
//	}
//	public void setM_ShippingAddress(ShippingAddress shippingAddress) {
//		m_ShippingAddress = shippingAddress;
//	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="PRIORITY")
	public Priority getM_Priority() {
		return m_Priority;
	}
	public void setM_Priority(Priority priority) {
		m_Priority = priority;
	}
	
	@ManyToOne
	@JoinColumn(name="CARRIER_NAME")
	public CarrierName getM_CarrierName() {
		return m_CarrierName;
	}
	public void setM_CarrierName(CarrierName carrierName) {
		m_CarrierName = carrierName;
	}
	
	@ManyToOne
	@JoinColumn(name="TRANSFER_FROM")
	public CAGECode getTransferFrom() {
		return transferFrom;
	}
	public void setTransferFrom(CAGECode transferFrom) {
		this.transferFrom = transferFrom;
	}
	
	@Column(name = "AIRIDENTIFICATIONNUMBER")
	public String getAirIdentificationNumber() {
		return airIdentificationNumber;
	}

	public void setAirIdentificationNumber(
			String airIdentificationNumber) {
		this.airIdentificationNumber = airIdentificationNumber;
	}

	@Column(name = "CARRIER_LINKMAN")
	public String getCarrierLinkman() {
		return carrierLinkman;
	}

	public void setCarrierLinkman(String carrierLinkman) {
		this.carrierLinkman = carrierLinkman;
	}

	@Column(name = "CCONTACTINFORMATION")
	public String getCarrierContactInformation() {
		return carrierContactInformation;
	}

	public void setCarrierContactInformation(String carrierContactInformation) {
		this.carrierContactInformation = carrierContactInformation;
	}

	@Column(name = "CAGE_ASSIGN_CARRIER")
	public Boolean getCAGEAssignCarrier() {
		return CAGEAssignCarrier;
	}

	public void setCAGEAssignCarrier(Boolean assignCarrier) {
		CAGEAssignCarrier = assignCarrier;
	}
	

}