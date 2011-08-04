package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.approval.ApprovalAvailableVO;
import com.skynet.spms.client.vo.approval.ApprovalRecordVO;

/**
 * 审批 service
 * 
 * @author zhangqiang
 * 
 */
@RemoteServiceRelativePath("approvalService.form")
public interface ApprovalService extends RemoteService {

	/**
	 * 保存审批记录
	 * 
	 * @param approval
	 *            审批实体
	 * @param sheetNo
	 *            单据主键
	 * @param itemNumber
	 *            单据编号
	 * @param sheetType
	 *            单据类型
	 * @param amount
	 *            金额
	 * @param priority
	 *            优先级
	 * @param bussinessType
	 *            业务类型
	 */
	public void saveApproval(String sheetNo, String itemNumber,
			String sheetType, float amount, String priority,
			String bussinessType);

	/**
	 * 保存每一次审批的记录
	 * 
	 * @param approvalId
	 * @param approvalDesc
	 * @param approvalStatus
	 */
	public void saveApprovalAvailable(String approvalId,
			ApprovalAvailableVO approvalAvailable, String taskID,
			String outcoming);

	/**
	 * 根据编号获取数据
	 * 
	 * @param id
	 */
	public ApprovalRecordVO getApprovalById(String id);
}
