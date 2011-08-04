package com.skynet.spms.persistence.entity.supplierSupport.consignment.consignRenew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.baseSupplyChain.baseApplicationForm.BaseApplicationForm;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.consignmentAgreementTemplate.ConsignmentAgreementTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.DeliveryType;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 寄售补库申请单
 * 
 * @author fl
 * 
 */
@Entity
@Table(name = "SPMS_CONSIGNRENEW")
public class ConsignRenew extends BaseApplicationForm {
	private static final long serialVersionUID = -2848783980095177249L;
	/** 寄售地点 **/
	private String consignAddr;
	/** 交货方式 **/
	private DeliveryType m_DeliveryType;
	/** 交货日期 */
	private Date deliveryDate;
	/**
	 * 如果是买方指定运代，如果运代在字典中不存在需要进行维护运代的企业信息
	 */
	private Boolean buyerFreightAgent;
	/** 运输方式 **/
	private TransportationCode m_TransportationCode;
	/** 运代商 **/
	private CarrierName carrierName;
	/** 指定运代商联系人 **/
	private String appointForwarderLinkman;
	/** 指定运代商联系方式 **/
	private String appointForwarderContact;
	/** 贸易方式 **/
	private TradeMethods m_TradeMethods;
	/** 项数统计 **/
	private int totalCount;
	/** 供应商 */
	private SupplierCode supplier;
	/**
	 * 库房编号
	 */
	private StockRoom stockRoom;
	/** 寄售协议编号 **/
	private ConsignmentAgreementTemplate template;
	/**
	 * 寄售补库原因
	 */
	private String resean;
	/**
	 * 供应商补库单号
	 */
	private String supplierRenewNum;
	/**
	 * 单据审核状态
	 */
	private AuditStatus auditStatus;
	
	/**
	 * 寄售补库原因
	 */
	@Column(name = "RESEAN")
	public String getResean() {
		return resean;
	}

	public void setResean(String resean) {
		this.resean = resean;
	}

	/**
	 * 供应商补库单号
	 */
	@Column(name = "SUPPLIERRENEWNUM")
	public String getSupplierRenewNum() {
		return supplierRenewNum;
	}

	public void setSupplierRenewNum(String supplierRenewNum) {
		this.supplierRenewNum = supplierRenewNum;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATID")
	public ConsignmentAgreementTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ConsignmentAgreementTemplate template) {
		this.template = template;
	}

	/** 寄售地点 **/
	@Column(name = "CONSIGNADDR")
	public String getConsignAddr() {
		return consignAddr;
	}

	public void setConsignAddr(String consignAddr) {
		this.consignAddr = consignAddr;
	}

	/** 交货方式 **/
	@Enumerated(EnumType.STRING)
	@Column(name = "M_DELIVERYTYPE")
	public DeliveryType getM_DeliveryType() {
		return m_DeliveryType;
	}

	public void setM_DeliveryType(DeliveryType m_DeliveryType) {
		this.m_DeliveryType = m_DeliveryType;
	}

	/** 交货时间 **/
	@Column(name = "DELIVERYDATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/** 是否制定运代 **/
	@Column(name = "BUYERFREIGHTAGENT")
	public Boolean getBuyerFreightAgent() {
		return buyerFreightAgent;
	}

	public void setBuyerFreightAgent(Boolean buyerFreightAgent) {
		this.buyerFreightAgent = buyerFreightAgent;
	}

	/** 运输方式 **/
	@Enumerated(EnumType.STRING)
	@Column(name = "M_TRANSPORTATIONCODE")
	public TransportationCode getM_TransportationCode() {
		return m_TransportationCode;
	}

	public void setM_TransportationCode(TransportationCode m_TransportationCode) {
		this.m_TransportationCode = m_TransportationCode;
	}

	/** 运代商 **/
	@ManyToOne
	@JoinColumn(name = "CARRIERNAME")
	public CarrierName getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(CarrierName carrierName) {
		this.carrierName = carrierName;
	}

	/** 运代商联系人 **/
	@Column(name = "APPOINTFORWARDERLINKMAN")
	public String getAppointForwarderLinkman() {
		return appointForwarderLinkman;
	}

	public void setAppointForwarderLinkman(String appointForwarderLinkman) {
		this.appointForwarderLinkman = appointForwarderLinkman;
	}

	/** 运代商联系方式 **/
	@Column(name = "APPOINTFORWARDERCONTACT")
	public String getAppointForwarderContact() {
		return appointForwarderContact;
	}

	public void setAppointForwarderContact(String appointForwarderContact) {
		this.appointForwarderContact = appointForwarderContact;
	}

	/** 交易方式 **/
	@Enumerated(EnumType.STRING)
	@Column(name = "M_TRADEMETHODS")
	public TradeMethods getM_TradeMethods() {
		return m_TradeMethods;
	}

	public void setM_TradeMethods(TradeMethods m_TradeMethods) {
		this.m_TradeMethods = m_TradeMethods;
	}

	/** 审核状态 **/
	@Enumerated(EnumType.STRING)
	@Column(name = "AUDITSTATUS")
	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	/** 项数总计 **/
	@Column(name = "TOTALCOUNT")
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/** 供应商 **/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUPPLIER")
	public SupplierCode getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierCode supplier) {
		this.supplier = supplier;
	}

	/** 库房 **/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCKROOMID")
	public StockRoom getStockRoom() {
		return stockRoom;
	}

	public void setStockRoom(StockRoom stockRoom) {
		this.stockRoom = stockRoom;
	}
}
