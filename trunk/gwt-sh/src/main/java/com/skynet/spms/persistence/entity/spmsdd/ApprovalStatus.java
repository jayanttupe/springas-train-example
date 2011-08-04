package com.skynet.spms.persistence.entity.spmsdd;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:26:35
 */
public enum ApprovalStatus {
	/**
	 * 审批业务发送至审批人员，且审批人员为接受审批业务时的审批状态。
	 */
	toApproval,
	/**
	 * 审批人员接受审批请求后，未审批之前的业务状态。
	 */
	approvaling,
	pass,
	fail
}