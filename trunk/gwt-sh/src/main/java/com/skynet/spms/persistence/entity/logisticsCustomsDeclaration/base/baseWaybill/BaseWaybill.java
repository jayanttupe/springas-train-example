package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.baseWaybill;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.ConsigneeDetails;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.ShipperDetails;
import com.skynet.spms.persistence.entity.spmsdd.GeneralJobStatus;
import com.skynet.spms.persistence.entity.spmsdd.Payment;
import com.skynet.spms.persistence.entity.spmsdd.ShippingServiceType;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.spmsdd.TransportationCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 16-三月-2011 17:21:04
 */
@MappedSuperclass
public class BaseWaybill extends BaseEntity{

	/**
	 * 件数
	 */
	private int billOfLadingContainerCount;
	/**
	 * 总重量(千克)
	 */
	private float billOfLadingWeight;
	/**
	 * 运费
	 */
	private float charges;
	/**
	 * 运单依赖的合同编号信息
	 */
	private String contractNumber;
	/**
	 * 预计到达时间
	 */
	private Date estimatedTimeOfArrival;
	/**
	 * 分运单号
	 */
	private String houseWayBill;
	/**
	 * 主运单号
	 */
	private String mainWayBill;
	/**
	 * 运输班次
	 */
	private String numberOfRuns;
	/**
	 * 目的口岸(港)
	 */
	private String portOfDestinat;
	/**
	 * 起运口岸(港)
	 */
	private String portOfShipment;
	/**
	 *备注
	 */
	private String remarkText;
	/**
	 * 起运日期
	 */
	private Date shipmentDate;
	/**
	 * 中转站
	 */
	private String transferStation;
	/**
	 * 总体积(立方米)
	 */
	private float volume;
	/**
	 * 收货人详细信息
	 */
	private String m_ConsigneeDetails;
	/**
	 * 发货人详细信息
	 */
	private String m_ShipperDetails;
	/**
	 * 支付方式
	 */
	private String m_Payment;
	/**
	 * 通用工作状态
	 */
	private String m_GeneralJobStatus;
	/**
	 * 运输方式代码
	 */
	private String m_TransportationCode;
	/**
	 * 运输服务方式
	 */
	private String m_ShippingServiceType;
	/**
	 * 贸易方式
	 */
	private String  m_TradeMethods ;
	/**
	 * 国际货币代码
	 */
	private String m_InternationalCurrencyCode;
	/**
	 * 运代商
	 */
	private String m_CarrierName;
	
	@Column ( name = "BILLOF_CONTAINER_COUNT")
	public int getBillOfLadingContainerCount() {
		return billOfLadingContainerCount;
	}
	public void setBillOfLadingContainerCount(int billOfLadingContainerCount) {
		this.billOfLadingContainerCount = billOfLadingContainerCount;
	}
	@Column ( name = "BILLOF_LADING_WEIGHT")
	public float getBillOfLadingWeight() {
		return billOfLadingWeight;
	}
	public void setBillOfLadingWeight(float billOfLadingWeight) {
		this.billOfLadingWeight = billOfLadingWeight;
	}
	@Column ( name = "CHARGES")
	public float getCharges() {
		return charges;
	}
	public void setCharges(float charges) {
		this.charges = charges;
	}
	@Column ( name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	@Column ( name = "ESTIMATED_TIMEOF_ARRIVAL")
	public Date getEstimatedTimeOfArrival() {
		return estimatedTimeOfArrival;
	}
	public void setEstimatedTimeOfArrival(Date estimatedTimeOfArrival) {
		this.estimatedTimeOfArrival = estimatedTimeOfArrival;
	}
	@Column ( name = "HOUSE_WAY_BILL")
	public String getHouseWayBill() {
		return houseWayBill;
	}
	public void setHouseWayBill(String houseWayBill) {
		this.houseWayBill = houseWayBill;
	}
	@Column ( name = "MAIN_WAY_BILL")
	public String getMainWayBill() {
		return mainWayBill;
	}
	public void setMainWayBill(String mainWayBill) {
		this.mainWayBill = mainWayBill;
	}
	@Column ( name = "NUMBEROF_RUNS")
	public String getNumberOfRuns() {
		return numberOfRuns;
	}
	public void setNumberOfRuns(String numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}
	@Column ( name = "PORTOF_DESTINAT")
	public String getPortOfDestinat() {
		return portOfDestinat;
	}
	public void setPortOfDestinat(String portOfDestinat) {
		this.portOfDestinat = portOfDestinat;
	}
	@Column ( name = "PORTOF_SHIPMENT")
	public String getPortOfShipment() {
		return portOfShipment;
	}
	public void setPortOfShipment(String portOfShipment) {
		this.portOfShipment = portOfShipment;
	}
	@Column ( name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	@Column ( name = "SHIPMENT_DATE")
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	@Column ( name = "TRANSFER_STATION")
	public String getTransferStation() {
		return transferStation;
	}
	public void setTransferStation(String transferStation) {
		this.transferStation = transferStation;
	}
	@Column ( name = "M_VOLUME")
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	@Column ( name = "M_CONSIGNEE_DETAILS") 
	public String getM_ConsigneeDetails() {
		return m_ConsigneeDetails;
	}
	public void setM_ConsigneeDetails(String m_ConsigneeDetails) {
		this.m_ConsigneeDetails = m_ConsigneeDetails;
	}
	@Column ( name = "M_SHIPPER_DETAILS")
	public String getM_ShipperDetails() {
		return m_ShipperDetails;
	}
	public void setM_ShipperDetails(String m_ShipperDetails) {
		this.m_ShipperDetails = m_ShipperDetails;
	}
	@Column ( name = "M_PAYMENT")
	public String getM_Payment() {
		return m_Payment;
	}
	public void setM_Payment(String m_Payment) {
		this.m_Payment = m_Payment;
	}
	@Column ( name = "M_GENERAL_JOB_STATUS")
	public String getM_GeneralJobStatus() {
		return m_GeneralJobStatus;
	}
	public void setM_GeneralJobStatus(String m_GeneralJobStatus) {
		this.m_GeneralJobStatus = m_GeneralJobStatus;
	}
	@Column ( name = "M_TRANSPORTATION_CODE")
	public String getM_TransportationCode() {
		return m_TransportationCode;
	}
	public void setM_TransportationCode(String m_TransportationCode) {
		this.m_TransportationCode = m_TransportationCode;
	}
	@Column ( name = "M_SHIPPING_SERVICETYPE")
	public String getM_ShippingServiceType() {
		return m_ShippingServiceType;
	}
	public void setM_ShippingServiceType(String m_ShippingServiceType) {
		this.m_ShippingServiceType = m_ShippingServiceType;
	}
	@Column ( name = "M_TRADEMETHODS")
	public String getM_TradeMethods() {
		return m_TradeMethods;
	}
	public void setM_TradeMethods(String m_TradeMethods) {
		this.m_TradeMethods = m_TradeMethods;
	}
	@Column ( name = "M_INTER_CURRENCY_CODE")
	public String getM_InternationalCurrencyCode() {
		return m_InternationalCurrencyCode;
	}
	public void setM_InternationalCurrencyCode(String m_InternationalCurrencyCode) {
		this.m_InternationalCurrencyCode = m_InternationalCurrencyCode;
	}
	@Column ( name = "M_CARRIER_NAME")
	public String getM_CarrierName() {
		return m_CarrierName;
	}
	public void setM_CarrierName(String m_CarrierName) {
		this.m_CarrierName = m_CarrierName;
	}
	
	

}