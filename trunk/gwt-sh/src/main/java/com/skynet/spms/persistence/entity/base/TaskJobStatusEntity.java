package com.skynet.spms.persistence.entity.base;
import java.util.Date;

import com.skynet.spms.persistence.entity.spmsdd.TaskJobStatus;

/**
 * 任务工作状态实体，用于跟踪任务的执行情况，分为接收，任务分派，困难，执行中，取消，工作完成。其中一旦任务下达之后，被操作人员接收，此时任务工作状态为接受，任务能
 * 够任务分派给其他操作人员来执行，一旦其他操作人员接收，则状态改为接收状态。
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:11
 */
public class TaskJobStatusEntity {

	private String operator;
	/**
	 * 版本号最高为当前操作人员操作状态。
	 */
	private int version;
	private String remarkText;
	private Date modifyDate;
	public TaskJobStatus m_TaskJobStatus;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public TaskJobStatus getM_TaskJobStatus() {
		return m_TaskJobStatus;
	}
	public void setM_TaskJobStatus(TaskJobStatus m_TaskJobStatus) {
		this.m_TaskJobStatus = m_TaskJobStatus;
	}

}