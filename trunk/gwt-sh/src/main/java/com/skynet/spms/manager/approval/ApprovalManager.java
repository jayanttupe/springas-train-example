package com.skynet.spms.manager.approval;

import com.skynet.spms.persistence.entity.approvalEntityManage.ApprovalRecord;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.ApprovalAvailableEntity;

public interface ApprovalManager {

	/**
	 * 保存审批实体
	 * 
	 * @param approval
	 * @return
	 */
	public void saveApproval(ApprovalRecord approval, String businessType);

	/**
	 * 更新实体
	 * 
	 * @param approval
	 */
	public void updateApproval(ApprovalRecord approval);

	/**
	 * 根据主键获取实体
	 * 
	 * @param bussinessKey
	 * @return
	 */
	public ApprovalRecord getApprovalRecordById(String bussinessKey,
			boolean fetch);

	/**
	 * 保存
	 * 
	 * @param available
	 */
	public void saveApprovalAvailable(ApprovalAvailableEntity available);

	// /**
	// * 根据合同编号和
	// * @param sheetNo
	// * @param sheetType
	// * @return
	// */
	// public BaseContactEntity getContactBySheetNoAndSheetType(String
	// sheetNo,String sheetType);
	/**
	 * @param sheetNO
	 *            单据的编号
	 * @param sheetType
	 *            单据类型
	 * @param status
	 *            审核结果
	 */
	public void updateSheetApprovalStatus(String sheetNO, String sheetType,
			String status);
}
