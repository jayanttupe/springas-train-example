package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;
/**
 * 物流运单实体
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author wangyx
 * @version 1.0
 * @created 24-四月-2011 17:45:26
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_SHIPPING_JOB")
public class ShippingJob extends BaseEntity {
	/**
	 * 指令ID
	 */
	private String orderID;
	
	/**
	 * 指令编号
	 */
	private String orderNumber;
	
	/**
	 * 物流任务编号
	 */
	private String logisticsTasksNumber;

	/**
	 * 起运工作编号
	 */
	private String shippingJobNumber;

	/**
	 * 件数
	 */
	private String billOfLadingContainerCount;

	/**
	 * 总重量(千克)
	 */
	private String billOfLadingWeight;

	/**
	 * 运费
	 */
	private String charges;

	/**
	 * 合同编号
	 */
	private String contractNumber;

	/**
	 * 预计到达时间
	 */
	private Date estimatedTimeOfArrival;

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
	 * 备注
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
	private String volume;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 状态名称
	 */
	private String statusName;

	
	/**
	 * 起运状态
	 */
	private String shippingStatus;

	/**
	 * 提发货业务
	 */
	private PickupDeliveryOrder pickupDeliveryOrder;

	public ShippingJob() {

	}

	public void finalize() throws Throwable {
		super.finalize();
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

	@Column(name = "LOGISTICS_TASKS_NUMBER")
	public String getLogisticsTasksNumber() {
		return logisticsTasksNumber;
	}

	public void setLogisticsTasksNumber(String logisticsTasksNumber) {
		this.logisticsTasksNumber = logisticsTasksNumber;
	}

	@Column(name = "SHIPPING_JOB_NUMBER")
	public String getShippingJobNumber() {
		return shippingJobNumber;
	}

	public void setShippingJobNumber(String shippingJobNumber) {
		this.shippingJobNumber = shippingJobNumber;
	}

	@Column(name = "BILL_OF_LADING_CONTAINER_COUNT")
	public String getBillOfLadingContainerCount() {
		return billOfLadingContainerCount;
	}

	public void setBillOfLadingContainerCount(String billOfLadingContainerCount) {
		this.billOfLadingContainerCount = billOfLadingContainerCount;
	}

	@Column(name = "BILL_OF_LADING_WEIGHT")
	public String getBillOfLadingWeight() {
		return billOfLadingWeight;
	}

	public void setBillOfLadingWeight(String billOfLadingWeight) {
		this.billOfLadingWeight = billOfLadingWeight;
	}

	@Column(name = "CHARGES")
	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	@Column(name = "CONTRACT_NUMBER")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Column(name = "ESTIMATED_TIME_OF_ARRIVAL")
	public Date getEstimatedTimeOfArrival() {
		return estimatedTimeOfArrival;
	}

	public void setEstimatedTimeOfArrival(Date estimatedTimeOfArrival) {
		this.estimatedTimeOfArrival = estimatedTimeOfArrival;
	}

	@Column(name = "NUMBER_OF_RUNS")
	public String getNumberOfRuns() {
		return numberOfRuns;
	}

	public void setNumberOfRuns(String numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}

	@Column(name = "PORT_OF_DESTINAT")
	public String getPortOfDestinat() {
		return portOfDestinat;
	}

	public void setPortOfDestinat(String portOfDestinat) {
		this.portOfDestinat = portOfDestinat;
	}

	@Column(name = "PORT_OF_SHIPMENT")
	public String getPortOfShipment() {
		return portOfShipment;
	}

	public void setPortOfShipment(String portOfShipment) {
		this.portOfShipment = portOfShipment;
	}

	@Column(name = "REMARK_TEXT")
	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	@Column(name = "SHIPMENT_DATE")
	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	@Column(name = "TRANSFER_STATION")
	public String getTransferStation() {
		return transferStation;
	}

	public void setTransferStation(String transferStation) {
		this.transferStation = transferStation;
	}

	@Column(name = "VOLUME")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "STATUS", updatable=false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "SHIPPING_STATUS")
	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	@Transient
	public PickupDeliveryOrder getPickupDeliveryOrder() {
		return pickupDeliveryOrder;
	}

	public void setPickupDeliveryOrder(PickupDeliveryOrder pickupDeliveryOrder) {
		this.pickupDeliveryOrder = pickupDeliveryOrder;
	}
}