/**
 * 
 */
package com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**待收料运单
 * @author Administrator
 * 
 */
@ViewFormAnno(value = "id")
@Entity
@Table(name = "SPMS_WAIT_REC_SHEET")
public class WaitReceivingSheet extends BaseEntity{

	/**
	 * 运单号
	 */
	private String wayBillNumber;
	/**
	 * 指令单号
	 */
	private String orderNumber;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 合同编号
	 */
	private String contratNumber;
	/**
	 * 货运代理
	 */
	private String cargoAgent;
	/**
	 * 运输方式
	 */
	private String transportationType;
	/**
	 * 到达日期
	 */
	private Date arrivalDate;

	 @Column(name = "WAY_BILL_NUMBER")
	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	 @Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	 @Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "CONTRAT_NUMBER")
	public String getContratNumber() {
		return contratNumber;
	}

	public void setContratNumber(String contratNumber) {
		this.contratNumber = contratNumber;
	}

	@Column(name = "CARGO_AGENT")
	public String getCargoAgent() {
		return cargoAgent;
	}

	public void setCargoAgent(String cargoAgent) {
		this.cargoAgent = cargoAgent;
	}

	 @Column(name = "TRANSPORTATION_TYPE")
	public String getTransportationType() {
		return transportationType;
	}

	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;
	}

	@Column(name = "ARRIVAL_DATE")
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public WaitReceivingSheet() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}
