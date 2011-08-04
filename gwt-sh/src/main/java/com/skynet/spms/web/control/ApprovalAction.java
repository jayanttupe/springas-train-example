package com.skynet.spms.web.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.text.SimpleDateFormat;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.gui.base.ApprovalResult;
import com.skynet.spms.client.service.ApprovalService;
import com.skynet.spms.client.service.WfTaskService;
import com.skynet.spms.client.vo.approval.ApprovalAvailableVO;
import com.skynet.spms.client.vo.approval.ApprovalRecordVO;
import com.skynet.spms.manager.approval.ApprovalManager;
import com.skynet.spms.persistence.entity.approvalEntityManage.ApprovalRecord;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.ApprovalAvailableEntity;
import com.skynet.spms.persistence.entity.spmsdd.ApprovalStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * 审批action
 * 
 * @author zhangqiang
 * 
 */
@Controller
@GwtRpcEndPoint
@Transactional
public class ApprovalAction implements ApprovalService {

	@Autowired
	private ApprovalManager approvalManager;

	@Autowired
	private WfTaskService wfTaskService;

	@Override
	@Transactional
	public void saveApproval(String sheetNo, String itemNumber,String sheetType, float amount,
			String priority, String bussinessType) {
		ApprovalRecord approval = new ApprovalRecord();
		approval.setSheetNo(sheetNo); // 单据主键
		approval.setItemNumber(itemNumber);//单据编号
		approval.setSheetType(sheetType); // 单据类型
		approval.setAmount(Float.valueOf(amount)); // 单据总金额
		if (priority != null) {
			approval.setM_Priority(Priority.valueOf(priority)); // 优先级
		}
		String createBy = GwtActionHelper.getCurrUser(); // 当前登录用户
		approval.setCreateBy(createBy);
		approval.setCreateDate(new Date()); // 创建时间

		// 保存数据，并启动工作流
		approvalManager.saveApproval(approval, bussinessType);
	}

	@Override
	public ApprovalRecordVO getApprovalById(String id) {
		ApprovalRecord record = approvalManager.getApprovalRecordById(id, true);
		ApprovalRecordVO vo = new ApprovalRecordVO();
		if (null == record) {
			return vo;
		}
		Date d = record.getCreateDate();
		if (null != d) {
			vo.setCreateDate(record.getCreateDate().toString());
		}
		vo.setId(record.getId());
		vo.setApprovalNumber(record.getApprovalNumber());
		if (record.getM_Priority() != null) {
			vo.setM_Priority(record.getM_Priority().toString());
		}
		vo.setSheetType(record.getSheetType());
		vo.setCreateBy(record.getCreateBy());
		vo.setSheetNo(record.getSheetNo());
		vo.setItemNumber(record.getItemNumber());
		// 当前审批人
		String currentUser = GwtActionHelper.getCurrUser();
		vo.setCurrentApprovalUser(currentUser);

		// 审批结果
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != record.getM_ApprovalAvailableEntity()
				&& record.getM_ApprovalAvailableEntity().size() > 0) {
			for (int i = 0; i < record.getM_ApprovalAvailableEntity().size(); i++) {
				ApprovalAvailableEntity available = record
						.getM_ApprovalAvailableEntity().get(i);
				ApprovalAvailableVO availableVO = new ApprovalAvailableVO();
				availableVO.setApprovalDesc(available.getApprovalDesc());
				availableVO.setApprovalStatus(available.getApprovalStauts()
						.toString());
				if (null != available.getCreateDate()) {
					availableVO.setApprovalTime(sdf.format(available
							.getCreateDate()));
				}
				availableVO.setApprovalUser(available.getCreateBy());
				availableVO.setTaskID(available.getTaskID());
				vo.getApprovalAvaibleVoList().add(availableVO);
			}

		}

		return vo;
	}

	@Override
	public void saveApprovalAvailable(String approvalId,
			ApprovalAvailableVO approvalAvailableVO, String taskID,
			String outcoming) {
		ApprovalRecord record = approvalManager.getApprovalRecordById(
				approvalId, true);
		if (null != record) {
			String currentUser = GwtActionHelper.getCurrUser();
			ApprovalAvailableEntity available = new ApprovalAvailableEntity();

			available.setApprovalDesc(approvalAvailableVO.getApprovalDesc());

			for (ApprovalResult result : ApprovalResult.values()) {
				String key = result.getKey();
				// String value = result.getValue();
				if (key.equals(approvalAvailableVO.getApprovalStatus())) {
					available.setApprovalStauts(ApprovalStatus.valueOf(result
							.toString()));
					break;
				}
			}
			available.setCreateBy(currentUser);
			available.setCreateDate(new Date());
			available.setTaskID(approvalAvailableVO.getTaskID());

			approvalManager.saveApprovalAvailable(available);
			record.getM_ApprovalAvailableEntity().add(available);
			approvalManager.updateApproval(record);
		}

		// 提交审批
		wfTaskService.commitTask(taskID, outcoming);

	}

}
