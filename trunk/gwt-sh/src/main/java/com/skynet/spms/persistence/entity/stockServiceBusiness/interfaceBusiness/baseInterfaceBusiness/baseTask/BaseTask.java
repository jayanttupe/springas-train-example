package com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskDescription;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskSource;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskState;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.SPMS.TaskType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.taskActionRecord.TaskActionRecord;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:11
 */
@MappedSuperclass
public class BaseTask extends BaseEntity{
    
	/**
	 * 任务创建人
	 */
	private String taskBy;
	/**
	 * 任务日期
	 */
	private Date taskDate;
	/**
	 * 任务号:设备号00-ZZ表示+业务类型00-ZZ表示+流水号00000-ZZZZZ表示
	 */
	private String taskNo;
	/**
	 * 即业务编号，如捡货单编号，入库单编号等
	 */
	private String taskRefNum;
	/**
	 * 任务类型
	 */
	private String taskType;
	private String m_TaskActionRecord;
	private String m_TaskState;
	private String m_TaskSource;
	private String m_TaskDescription;

	@Column(name = "TASKBY")
	public String getTaskBy() {
		return taskBy;
	}
	public void setTaskBy(String taskBy) {
		this.taskBy = taskBy;
	}
	
	@Column(name = "TASKDATE")
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	
	@Column(name = "TASKNO")
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@Column(name = "TASKREFNUM")
	public String getTaskRefNum() {
		return taskRefNum;
	}
	public void setTaskRefNum(String taskRefNum) {
		this.taskRefNum = taskRefNum;
	}
	
	@Column(name = "TASKTYPE")
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	@Column(name = "M_TASKACTIONRECORD")
	public String getM_TaskActionRecord() {
		return m_TaskActionRecord;
	}
	public void setM_TaskActionRecord(String m_TaskActionRecord) {
		this.m_TaskActionRecord = m_TaskActionRecord;
	}
	@Column(name = "M_TASKSTATE")
	public String getM_TaskState() {
		return m_TaskState;
	}
	public void setM_TaskState(String m_TaskState) {
		this.m_TaskState = m_TaskState;
	}
	
	@Column(name = "M_TASKSOURCE")
	public String getM_TaskSource() {
		return m_TaskSource;
	}
	public void setM_TaskSource(String m_TaskSource) {
		this.m_TaskSource = m_TaskSource;
	}
	@Column(name = "M_TASKDESC")
	public String getM_TaskDescription() {
		return m_TaskDescription;
	}
	public void setM_TaskDescription(String m_TaskDescription) {
		this.m_TaskDescription = m_TaskDescription;
	}
	
	public BaseTask() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}