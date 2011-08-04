package com.skynet.spms.client.gui.customerService.leaseService.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct.add.RequisitonLeaseInstructAddWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.add.LeaseContractAddWindow;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * 租赁申请单业务类
 * **/
public class LeaseRequisitionSheetBusiness extends BaseBusiness {

	public MainModelLocator model = MainModelLocator.getInstance();

	/**
	 * 刷新ItemListGrid
	 * 
	 * @param grid
	 */
	public void refeshItemListGrid() {

		super.refeshListGrid(model.leaseRequisitionSheetItemListGrid);

	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {
		super.refeshListGrid(model.leaseRequisitionSheetListGrid);
	}

	/**
	 * 新建客户租赁合同
	 * **/
	public void addRQContract(final ListGrid grid, final ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {// 判断是否发布了
			final String state = grid.getSelectedRecord().getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus");

			// 判断是否已经存在合同
			String contractNumber = grid.getSelectedRecord().getAttribute(
					"contractNumber").toString();
			if (contractNumber != null && contractNumber != "") {
				SC.say("合同已经存在!");
				return;
			}
			if ("published".equals(state)) {
				LeaseContractAddWindow addWin = new LeaseContractAddWindow();
				ShowWindowTools.showWondow(button.getPageRect(), addWin, 200);
			} else {
				SC.say("请先发布后在新建合同");
			}

		}
	}

	/**
	 * 新建申请供应商租赁指令
	 * **/
	public void addLeaseInstruct(final ListGrid grid, ToolStripMenuButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {

			RequisitonLeaseInstructAddWindow win = new RequisitonLeaseInstructAddWindow(
					"新建申请供应商租赁指令", true, button.getPageRect(), null, "");
			ShowWindowTools.showWondow(button.getPageRect(), win, 200);
		}
	}

	/**
	 * 合同审批
	 * **/
	public void approvalContractFinally(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			Record target = grid.getSelectedRecord();
			String state = target
					.getAttribute("m_BussinessPublishStatusEntity.m_PublishStatus");
			if (state == null || state.equals("unpublished")) {
				SC.warn("信息尚未发布！请先发布信息！");
				return;
			}
			String approvalStage = target.getAttribute("ApprovalStatus");
			if (!approvalStage.equals("pass")) {
				target.setAttribute("ApprovalStatus", "");
			}

			grid.updateData(target);
			SC.warn("合同提交审批成功，请耐心等待!");
		}
	}
}
