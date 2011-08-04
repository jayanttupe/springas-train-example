package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.vo.approval.ApprovalAvailableVO;
import com.skynet.spms.client.vo.approval.ApprovalRecordVO;

public interface ApprovalServiceAsync {

	/**
	 * 保存审批记录，返回生成的主键值
	 * @param approval 审批实体
	 * @param sheetNo 单据主键
	 * @param itemNumber 单据编号
	 * @param sheetType 单据类型
	 * @param amount 金额
	 * @param priority 优先级
	 * @param bussinessType 业务类型
	 */
	public void saveApproval(String sheetNo,String itemNumber,String sheetType,float amount,String priority,String bussinessType,AsyncCallback<Void> callback);
	
	/**
	 * 保存每一次审批的记录
	 * @param approvalId
	 * @param approvalDesc
	 * @param approvalStatus
	 */
	public void saveApprovalAvailable(String approvalId,ApprovalAvailableVO approvalAvailable,String taskID,String outcoming,AsyncCallback<Void> callback);
	
	/**
	 * 根据编号获取数据
	 * @param id
	 */
	public void getApprovalById(String id,AsyncCallback<ApprovalRecordVO> callback);
}
