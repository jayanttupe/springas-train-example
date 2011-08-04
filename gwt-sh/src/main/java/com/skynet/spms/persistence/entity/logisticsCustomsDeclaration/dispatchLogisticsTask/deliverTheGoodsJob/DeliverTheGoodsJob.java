package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;
/**
 * 物流交货计划实体
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * @author wangyx
 * @version 1.0
 * @created 25-四月-2011 13:03:34
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_DELIVER_THE_GOODS_JOB")
public class DeliverTheGoodsJob extends BaseEntity {
	/**
	 * 指令ID
	 */
	private String orderID;
	/**
	 * 指令编号
	 */
	private String orderNumber;
	/**
	 * 交货工作编号
	 */
	private String deliverTheGoodsNumber;
	/**
	 * 物流任务编号 
	 */
	private String logisticsTasksNumber;
	/**
	 * 交货地点详细地址
	 */
	private String addressOfDeliverTheGoods;
	/**
	 * 交货日期
	 */
	private Date deliverTheGoodsDate;
	/**
	 * 联系人
	 */
	private String contacter;
	/**
	 * 运输班次
	 */
	private String numberOfRuns;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 交货状态 
	 */
	private String status;
	/**
	 * 交货状态 名称
	 */
	private String statusName;

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
	@Column(name = "DELIVER_THE_GOODS_NUMBER")
	public String getDeliverTheGoodsNumber() {
		return deliverTheGoodsNumber;
	}
	public void setDeliverTheGoodsNumber(String deliverTheGoodsNumber) {
		this.deliverTheGoodsNumber = deliverTheGoodsNumber;
	}
	@Column(name = "LOGISTICS_TASKS_NUMBER")
	public String getLogisticsTasksNumber() {
		return logisticsTasksNumber;
	}
	public void setLogisticsTasksNumber(String logisticsTasksNumber) {
		this.logisticsTasksNumber = logisticsTasksNumber;
	}
	@Column(name = "ADDRESS_OF_DELIVER_THE_GOODS")
	public String getAddressOfDeliverTheGoods() {
		return addressOfDeliverTheGoods;
	}
	public void setAddressOfDeliverTheGoods(String addressOfDeliverTheGoods) {
		this.addressOfDeliverTheGoods = addressOfDeliverTheGoods;
	}
	@Column(name = "DELIVER_THE_GOODS_DATE")
	public Date getDeliverTheGoodsDate() {
		return deliverTheGoodsDate;
	}
	public void setDeliverTheGoodsDate(Date deliverTheGoodsDate) {
		this.deliverTheGoodsDate = deliverTheGoodsDate;
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
	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
}
