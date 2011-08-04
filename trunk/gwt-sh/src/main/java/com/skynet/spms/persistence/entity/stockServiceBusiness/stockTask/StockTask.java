package com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 15-三月-2011 12:33:17
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_STOCK_TASK")
public class StockTask extends BaseEntity{
	/**
	 * 任务编号(SHL:上架/PIK:拣货/MOV:移库/SCK:盘点/5:补码/6:发卡)
	 */
	private String taskNo;

	/**
	 * 任务类型(1:上架/2:拣货/3:移库/4:盘点/5:补码/6:发卡)
	 */
	private TaskType taskType;

	/**
	 * 任务状态(OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
	 */
	private TaskStatus taskStatus;

	/**
	 * 任务来源
	 */
	private String taskSource;

	/**
	 * 任务库房ID
	 */
	private String taskStockRoomID;

	/**
	 * 业务单据号
	 */
	private String bussinessBillNO;

	/**
	 * 任务创建人
	 */
	private String taskBy;

	/**
	 * 任务日期
	 */
	private Date taskDate;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 任务执行人
	 */
	private String actionBy;

	/**
	 * 任务执行日期
	 */
	private Date actionDate;

	/**
	 * 执行设备号
	 */
	private String actionDevice;

	public StockTask() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	@Column(name = "TASK_NO")
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_TYPE")
	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_STATUS")
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "TASK_SOURCE")
	public String getTaskSource() {
		return taskSource;
	}

	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}

	@Column(name = "TASK_STOCK_ROOM_ID")
	public String getTaskStockRoomID() {
		return taskStockRoomID;
	}

	public void setTaskStockRoomID(String taskStockRoomID) {
		this.taskStockRoomID = taskStockRoomID;
	}

	@Column(name = "BUSSINESS_BILL_NO")
	public String getBussinessBillNO() {
		return bussinessBillNO;
	}

	public void setBussinessBillNO(String bussinessBillNO) {
		this.bussinessBillNO = bussinessBillNO;
	}

	@Column(name = "TASK_BY")
	public String getTaskBy() {
		return taskBy;
	}

	public void setTaskBy(String taskBy) {
		this.taskBy = taskBy;
	}

	@Column(name = "TASK_DATE")
	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ACTION_BY")
	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	@Column(name = "ACTION_DATE")
	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	@Column(name = "ACTION_DEVICE")
	public String getActionDevice() {
		return actionDevice;
	}

	public void setActionDevice(String actionDevice) {
		this.actionDevice = actionDevice;
	}
}