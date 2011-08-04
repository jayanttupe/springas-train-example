package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob;
/**
 * 物流到达实体
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author FANYX
 * @version 1.0
 * @created 5-五月-2011 13:23:01
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_ARRIVAL_OF_GOODS_JOB")
public class ArrivalOfGoodsJob extends BaseEntity {
    /**
     * 指令ID
     */
	private String orderID;
    /**
     * 指令编号
     */
	private String orderNumber;
	/**
     * 到达工作编号
     */
	private String arrivalOfGoodsNumber;
	/**
	 * 到达日期
	 */
	private Date arrivalOfGoodsDate;
	/**
	 * 联系人
	 */
	private String contacter;
	/**
	 * 运输班次
	 */
	private String numberOfRuns;
	/**
	 * 到达口岸(港)
	 */
	private String portOfDestinat;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 状态名称
	 */
	private String statusName;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 物流任务编号 
	 */
	private String logisticsTasksNumber;
	
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
	@Column(name = "ARRIVAL_OF_GOODS_NUMBER")
	public String getArrivalOfGoodsNumber() {
		return arrivalOfGoodsNumber;
	}
	public void setArrivalOfGoodsNumber(String arrivalOfGoodsNumber) {
		this.arrivalOfGoodsNumber = arrivalOfGoodsNumber;
	}
	@Column(name = "ARRIVAL_OF_GOODS_DATE") 
	public Date getArrivalOfGoodsDate() {
		return arrivalOfGoodsDate;
	}
	public void setArrivalOfGoodsDate(Date arrivalOfGoodsDate) {
		this.arrivalOfGoodsDate = arrivalOfGoodsDate;
	}
	@Column(name = "CONTACTER") 
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
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
	@Column(name = "STATUS")
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
	@Column(name = "MEMO") 
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "LOGISTICS_TASKS_NUMBER") 
	public String getLogisticsTasksNumber() {
		return logisticsTasksNumber;
	}
	public void setLogisticsTasksNumber(String logisticsTasksNumber) {
		this.logisticsTasksNumber = logisticsTasksNumber;
	}
	
}