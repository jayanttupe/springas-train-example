package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 物流任务分配
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@MappedSuperclass
public class BasePickupDeliveryTaskAssign extends BaseEntity {
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
	 * 工作类别(订舱/取货/起运/到达/清关/交货)
	 */
	private LogisticsTaskJobType workType;

	/**
	 * 任务开始时间
	 */
	private Date taskStartDate;

	/**
	 * 任务结束时间
	 */
	private Date taskEndDate;

	/**
	 * 工作状态
	 */
	private String workStatus;

	/**
	 * 工作状态名称(1:未分配/2:处理中/3:处理结束)
	 */
	private String workStatusName;

	/**
	 * 负责人员
	 */
	private String worker;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 报送类型(1:国内/2:进口/3:出口)
	 */
	private String customsDeclarationType;

	public BasePickupDeliveryTaskAssign() {

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

	@Column(name = "TASK_START_DATE")
	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "WORK_TYPE")
	public LogisticsTaskJobType getWorkType() {
		return workType;
	}

	public void setWorkType(LogisticsTaskJobType workType) {
		this.workType = workType;
	}

	@Column(name = "TASK_END_DATE")
	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	@Column(name = "WORK_STATUS", updatable=false)
	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	@Transient
	public String getWorkStatusName() {
		return workStatusName;
	}

	public void setWorkStatusName(String workStatusName) {
		this.workStatusName = workStatusName;
	}

	@Column(name = "WORKER")
	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Transient
	public String getCustomsDeclarationType() {
		return customsDeclarationType;
	}

	public void setCustomsDeclarationType(String customsDeclarationType) {
		this.customsDeclarationType = customsDeclarationType;
	}
}