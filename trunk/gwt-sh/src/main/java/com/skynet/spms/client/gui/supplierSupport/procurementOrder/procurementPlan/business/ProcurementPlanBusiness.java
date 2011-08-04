package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 * 采购计划业务类
 * **/
public class ProcurementPlanBusiness extends BaseBusiness {

	public MainModelLocator model = MainModelLocator.getInstance();

	/**
	 * 提交审批
	 * **/
	public void approvalAvailableEntity(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			Record target = grid.getSelectedRecord();// 获得选中的数据
			if (target.getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus").equals(
					"unpublished")) {
				SC.say("请先发布后在提交审批");
				return;
			}
			target.setAttribute("m_ApprovalAvailableEntity.approvalStauts",
					"approvaling");// 设置状态
			grid.updateData(target);
			SC.say("提交审批成功");
		}
	}
}
