package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.business.ProcurementOrderBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.add.ProcurementPlanAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.business.ProcurementPlanBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.modity.ProcurementPlanModifyWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.view.ProcurementPlanViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ProcurementPlanToolStrip extends BaseButtonToolStript {

	private ProcurementOrderBusiness orderBusiness = new ProcurementOrderBusiness();
	private ProcurementPlanBusiness business = new ProcurementPlanBusiness();

	private ProcurementPlanListGrid procurementPlanListGrid;

	public ProcurementPlanToolStrip(
			ProcurementPlanListGrid procurementPlanListGrid) {
		super(DSKey.S_PROCUREMENTPLAN);
		this.procurementPlanListGrid = procurementPlanListGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// ADD,DELETE,MODIFY,PUBLISH,CANCELPUBLISH,COMMITEXAMINE,ADDPROORDER,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if (buttonName.equals("ADD")) {
			ProcurementPlanAddWindow addWin = new ProcurementPlanAddWindow("",
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), addWin, 200);
		} else if ("ADDPROORDER".equals(buttonName)) {
			orderBusiness.addProcurementOrder(procurementPlanListGrid, button);
		} else if (buttonName.equals("DELETE")) {
			business.deleteSheet(procurementPlanListGrid);
		} else if (buttonName.equals("PUBLISH")) {
			business.publishSheet(procurementPlanListGrid);
		} else if (buttonName.equals("CANCELPUBLISH")) {
			business.cancelPublishSheet(procurementPlanListGrid);
		} else if (buttonName.equals("MODIFY")) {
			if (business.canModifiedSheet(procurementPlanListGrid)) {
				ProcurementPlanModifyWindow modify = new ProcurementPlanModifyWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), modify, 200);
			}
		} else if (buttonName.equals("COMMITEXAMINE")) {
			business.doApproval(procurementPlanListGrid,
					WorkFlowBusinessType.PROCUREMENTPLAN,
					WorkFlowSheetType.PROCUREMENTPLAN, "id",
					"procurementPlanNumber", "totalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus",
					"m_Priority");
		} else if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(procurementPlanListGrid)) {
				ProcurementPlanViewWindow view = new ProcurementPlanViewWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), view, 500);
			}
		} else if (buttonName.equals("REFRESH")) {
			business.refreshSheet(procurementPlanListGrid);
		}
	}
}
