package com.skynet.spms.persistence.entity.spmsdd;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:31
 */
public enum ApprovalStatusMonitor {
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