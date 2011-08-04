package com.skynet.spms.client.vo.approval;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ApprovalAvailableVO  implements IsSerializable{

	/** 当前审批人 */
	private String approvalUser;
	/** 当前审批时间 */
	private String approvalTime;
	/** 审批状态 */
	private String approvalStatus;
	/** 审批意见 */
	private String approvalDesc;
	/** taskID */
	private String taskID;
	
	public ApprovalAvailableVO() {
		super();
	}
	public String getApprovalUser() {
		return approvalUser;
	}
	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprovalDesc() {
		return approvalDesc;
	}
	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
}
