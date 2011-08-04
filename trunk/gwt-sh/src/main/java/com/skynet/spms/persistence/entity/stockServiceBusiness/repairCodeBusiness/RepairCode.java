package com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;

/**
 * @author wangyx
 * @version 1.1
 * @created 2011-5-9
 */
@ViewFormAnno(value="id")
@Entity
@Table(name = "SPMS_REPAIR_CODE")
public class RepairCode extends BaseEntity {
	/**
	 * 任务编号 
	 */
	private String taskNumber;
	/**
	 * 补码类型 
	 */
	private repairCodeType repairCodeType;
	/**
	 * 补码原因 
	 */
	private String repairCodeReason;
	/**
	 * 补码种类 
	 */
	private repairType repairType;
	/**
	 * 备注 
	 */
	private String remark;
	
	/**
	 * 任务是否已下达
	 */
	private SendStatus sendStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SEND_STATUS")
	public SendStatus getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(SendStatus sendStatus) {
		this.sendStatus = sendStatus;
	}
	@Column(name = "TASK_NUMBER")
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "REPAIR_CODE_TYPE")
	public repairCodeType getRepairCodeType() {
		return repairCodeType;
	}
	public void setRepairCodeType(repairCodeType repairCodeType) {
		this.repairCodeType = repairCodeType;
	}
	@Column(name = "REPAIR_CODE_REASON")
	public String getRepairCodeReason() {
		return repairCodeReason;
	}
	public void setRepairCodeReason(String repairCodeReason) {
		this.repairCodeReason = repairCodeReason;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "REPAIR_TYPE")
	public repairType getRepairType() {
		return repairType;
	}
	public void setRepairType(repairType repairType) {
		this.repairType = repairType;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	//用于级联添加的集合
	private List<String> itemIdList;
	@Transient
	public List<String> getItemIdList() {
		return itemIdList;
	}
	public void setItemIdList(List<String> itemIdList) {
		this.itemIdList = itemIdList;
	}
	
}
