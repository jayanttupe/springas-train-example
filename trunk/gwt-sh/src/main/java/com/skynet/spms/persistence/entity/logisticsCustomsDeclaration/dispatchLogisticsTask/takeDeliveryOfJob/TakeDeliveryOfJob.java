
package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;
/**
 * 物流取货计划实体
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
 * @author 
 *
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_TAKE_DELIVERY_OF_JOB")
public class TakeDeliveryOfJob extends BaseEntity{
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
	 * 取货工作编号
	 */
	private String takeDeliveryNumber;
	/**
	 * 取货详细地址
	 */
	private String addressOfTakeDelivery;
	/**
	 * 联系人
	 */
    private String contacter;
     /**
      * 运输班次(航班号/车号/船号)
      */
	private String numberOfRuns;
    /**
     * 取货日期
     */
	private Date takeDeliveryDate;
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
	 * 提发货业务
	 */
	private PickupDeliveryOrder pickupDeliveryOrder;
    
    	
	public TakeDeliveryOfJob (){
		
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
	@Column(name = "TAKE_DELIVERY_NUMBER")
	public String getTakeDeliveryNumber() {
		return takeDeliveryNumber;
	}
	public void setTakeDeliveryNumber(String takeDeliveryNumber) {
		this.takeDeliveryNumber = takeDeliveryNumber;
	}
	@Column(name = "ADDRESS_OF_TAKE_DELIVERY")
	public String getAddressOfTakeDelivery() {
		return addressOfTakeDelivery;
	}
	public void setAddressOfTakeDelivery(String addressOfTakeDelivery) {
		this.addressOfTakeDelivery = addressOfTakeDelivery;
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
	@Column(name = "TAKE_DELIVERY_DATE")
	public Date getTakeDeliveryDate() {
		return takeDeliveryDate;
	}
	public void setTakeDeliveryDate(Date takeDeliveryDate) {
		this.takeDeliveryDate = takeDeliveryDate;
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
	@Transient
	public PickupDeliveryOrder getPickupDeliveryOrder() {
		return pickupDeliveryOrder;
	}

	public void setPickupDeliveryOrder(PickupDeliveryOrder pickupDeliveryOrder) {
		this.pickupDeliveryOrder = pickupDeliveryOrder;
	}
}
