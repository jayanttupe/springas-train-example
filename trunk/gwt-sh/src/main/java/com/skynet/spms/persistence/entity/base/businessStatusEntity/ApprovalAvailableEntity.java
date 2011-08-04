package com.skynet.spms.persistence.entity.base.businessStatusEntity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseStatusEntity;
import com.skynet.spms.persistence.entity.spmsdd.ApprovalStatus;

/**
 * 审批实体的版本(version)用于控制审批人员对于审批实体的每一次业务操作的跟踪。当业务实体内聚了审批实体后，审批实体包括了操作人员信息，版本号信息，以及操作
 * 人员可能填写的审批意见的信息。版本号默认由1开始升级，每次修改审批实体后，版本信息自动增加1的位数。
 * 
 * 版本的管理有利于对于实体的操作历史记录，通常能记录下哪个操作人员，在什么时候，修改了什么内容，版本号的更新情况。系统默认显示当前版本号最高的为当前实体的默认状态
 * 。
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:32:56
 */
@Entity
@Table(name = "SPMS_APPROVALAVAILABLE")
public class ApprovalAvailableEntity extends BaseStatusEntity {

	private static final long serialVersionUID = 1L;
	
	public ApprovalStatus approvalStauts;
	/** 审批意见 */
	private String approvalDesc;
	/** 审批taskID*/
	private String taskID;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	

	public ApprovalAvailableEntity() {
		super();
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public ApprovalStatus getApprovalStauts() {
		return approvalStauts;
	}

	public void setApprovalStauts(ApprovalStatus approvalStauts) {
		this.approvalStauts = approvalStauts;
	}

	@Column(name="APPROVAL_DESC")
	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	@Column(name="TASK_ID")
	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Column(name="CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}