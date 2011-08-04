package com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct.modify.RequisitonLeaseInstructUpdateWindow;
import com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct.view.RequisitonLeaseInstructViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class RequisitionLeaseInstructToolStrip extends BaseButtonToolStript {
	private static I18n ui = new I18n();
	private LeaseRequisitionSheetBusiness bus = new LeaseRequisitionSheetBusiness();
	private RequisitionLeaseInstructListGrid requisitionLeaseInstructListGrid;

	public RequisitionLeaseInstructToolStrip(
			RequisitionLeaseInstructListGrid requisitionLeaseInstructListGrid) {
		super("customerService.leaseService.LeaseInstruct");
		this.requisitionLeaseInstructListGrid = requisitionLeaseInstructListGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// ADD,DELETE,MODIFY,VIEW,PRINT,PUBLISH,CANCELPUBLISH,MESSAGE,LOG,EXPORT,SENIORSEARCH
		if (buttonName.equals("MODIFY")) {
			if (ValidateUtil.isRecordSelected(requisitionLeaseInstructListGrid)) {
				RequisitonLeaseInstructUpdateWindow addWin = new RequisitonLeaseInstructUpdateWindow(
						ui.getB().modifyFormTitle(
								ui.getM().mod_requisitionLeaseInstruct_name()),
						true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), addWin, 500);
			}
			// 发布
		} else if (buttonName.equals("PUBLISH")) {
			bus.publishSheet(requisitionLeaseInstructListGrid,
					"LeaseContractTemplate", requisitionLeaseInstructListGrid
							.getSelectedRecord().getAttribute(
									"m_LeaseContractTemplate.id"));
			// 取消发布
		} else if (buttonName.equals("CANCELPUBLISH")) {
			bus.cancelPublishSheet(requisitionLeaseInstructListGrid,
					"LeaseContractTemplate", requisitionLeaseInstructListGrid
							.getSelectedRecord().getAttribute(
									"m_LeaseContractTemplate.id"));
		} else if (buttonName.equals("DELETE")) {
			bus.deleteSheet(requisitionLeaseInstructListGrid,
					"LeaseContractTemplate", requisitionLeaseInstructListGrid
							.getSelectedRecord().getAttribute(
									"m_LeaseContractTemplate.id"));

		} else if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(requisitionLeaseInstructListGrid)) {
				RequisitonLeaseInstructViewWindow view = new RequisitonLeaseInstructViewWindow(
						ui.getB().viewFormTitle(
								ui.getM().mod_requisitionLeaseInstruct_name()),
						true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), view, 200);
			}
		} else if (buttonName.equals("REFRESH")) {
			bus.refreshSheet(requisitionLeaseInstructListGrid);
		}
	}
}
