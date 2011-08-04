package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.add.LeaseContractAddWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.update.LeaseContractUpdateWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.view.LeaseContractViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class LeaseContractToolStrip extends BaseButtonToolStript {
	private LeaseContractListGrid leaseContractListGrid;
	private LeaseRequisitionSheetBusiness buiness = new LeaseRequisitionSheetBusiness();

	public LeaseContractToolStrip(LeaseContractListGrid leaseContractListGrid) {
		super(DSKey.C_LEASECONTRACT);
		this.leaseContractListGrid = leaseContractListGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		// buildMenu();

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// ADD,DELETE,MODIFY,PUBLISH,CANCELPUBLISH,ADDSALESQUOTATION,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if (buttonName.equals("ADD")) {
			LeaseContractAddWindow addWin = new LeaseContractAddWindow();
			ShowWindowTools.showWondow(button.getPageRect(), addWin, 500);
		} else if (buttonName.equals("DELETE")) {
			buiness.deleteSheet(leaseContractListGrid, "LeaseRequisitionSheet",
					leaseContractListGrid.getSelectedRecord().getAttribute(
							"rqId"));
		} else if (buttonName.equals("MODIFY")) {
			if (ValidateUtil.isRecordSelected(leaseContractListGrid)) {
				LeaseContractUpdateWindow modWin = new LeaseContractUpdateWindow();
				ShowWindowTools.showWindow(button.getPageRect(), modWin, 200);
			}
			// 发布
		} else if (buttonName.equals("PUBLISH")) {
			buiness.publishSheet(leaseContractListGrid,
					"LeaseRequisitionSheet", leaseContractListGrid
							.getSelectedRecord().getAttribute("rqId"));
			// 取消发布
		} else if (buttonName.equals("CANCELPUBLISH")) {
			buiness.cancelPublishSheet(leaseContractListGrid,
					"LeaseRequisitionSheet", leaseContractListGrid
							.getSelectedRecord().getAttribute("rqId"));
		} else if (buttonName.equals("COMMITEXAMINE")) {
			// buiness.approvalContractFinally(leaseContractListGrid);
			buiness.doApproval(leaseContractListGrid,
					WorkFlowBusinessType.LEASECONTRACT,
					WorkFlowSheetType.LEASECONTRACT, "id", "contractNumber",
					"extendedValueTotalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus",
					"m_Priority");
		} else if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(leaseContractListGrid)) {
				LeaseContractViewWindow view = new LeaseContractViewWindow();
				ShowWindowTools.showWindow(button.getPageRect(), view, 500);
			}
		} else if (buttonName.equals("REFRESH")) {
			buiness.refreshSheet(leaseContractListGrid);
		}
	}
}
