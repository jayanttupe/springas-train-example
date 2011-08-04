package com.skynet.spms.persistence.entity.spmsdd;

/**
 * 审批结果
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:32 create 新建（未提交审批）,approvaling,审批中, noPass,（未通过）,
 *          pass（通过）
 */
public enum AuditStatus {
	create, approvaling, noPass, pass
}