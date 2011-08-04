package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 客户送修合同状态
 * 
 * @author tqc
 * 
 */
public class ContractState implements IsSerializable {

	private String approvalStage;
	private String auditStatus;

	public ContractState() {
	}

	/**
	 * 审批阶段
	 * 
	 * @return
	 */
	public String getApprovalStage() {
		return approvalStage;
	}

	public void setApprovalStage(String approvalStage) {
		this.approvalStage = approvalStage;
	}

	/**
	 * 审批结果
	 * 
	 * @return
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
