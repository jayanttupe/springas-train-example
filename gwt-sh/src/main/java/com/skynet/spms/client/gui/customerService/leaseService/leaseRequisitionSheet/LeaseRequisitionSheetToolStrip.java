package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.add.LeaseRequisitionSheetAddWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.modify.LeaseRequisitionSheetModifyWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.view.LeaseRequisitionSheetViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class LeaseRequisitionSheetToolStrip extends BaseButtonToolStript {
	private LeaseRequisitionSheetListGrid leaseRequisitionSheetListGrid;
	private LeaseRequisitionSheetBusiness bus = new LeaseRequisitionSheetBusiness();

	public LeaseRequisitionSheetToolStrip(
			LeaseRequisitionSheetListGrid leaseRequisitionSheetListGrid) {
		super("customerService.leaseService.leaseRequisitionSheet", 0, 10);
		this.leaseRequisitionSheetListGrid = leaseRequisitionSheetListGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// ADD,DELETE,MODIFY,PUBLISH,CANCELPUBLISH,ADDSALESQUOTATION,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		// 添加
		if (buttonName.equals("ADD")) {

			LeaseRequisitionSheetAddWindow addWin = new LeaseRequisitionSheetAddWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWondow(button.getPageRect(), addWin, 500);
			// 删除
		} else if (buttonName.equals("DELETE")) {
			bus.deleteSheet(leaseRequisitionSheetListGrid);
			// 修改
		} else if (buttonName.equals("MODIFY")) {
			if (ValidateUtil.isRecordSelected(leaseRequisitionSheetListGrid)) {

				LeaseRequisitionSheetModifyWindow modWin = new LeaseRequisitionSheetModifyWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWondow(button.getPageRect(), modWin, 200);
			}
			// 发布
		} else if (buttonName.equals("PUBLISH")) {
			bus.publishSheet(leaseRequisitionSheetListGrid);
			// 取消发布
		} else if (buttonName.equals("CANCELPUBLISH")) {
			bus.cancelPublishSheet(leaseRequisitionSheetListGrid);
			// 新建合同
		} else if (buttonName.equals("ADDCONTRACT")) {
			bus.addRQContract(leaseRequisitionSheetListGrid, button);
		} else if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(leaseRequisitionSheetListGrid)) {
				LeaseRequisitionSheetViewWindow view = new LeaseRequisitionSheetViewWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), view, 0);
			}
		} else if (buttonName.equals("REFRESH")) {
			bus.refreshSheet(leaseRequisitionSheetListGrid);
		}
	}
}
