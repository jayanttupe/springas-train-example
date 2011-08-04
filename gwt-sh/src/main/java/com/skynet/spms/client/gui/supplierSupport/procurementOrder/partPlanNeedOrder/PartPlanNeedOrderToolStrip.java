package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.add.PartPlanNeedOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.business.PartPlanNeedBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.modity.PartPlanNeedOrderModifyWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.view.PartPlanNeedOrderViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class PartPlanNeedOrderToolStrip extends BaseButtonToolStript {

	private PartPlanNeedOrderListGrid listGrid;
	private PartPlanNeedBusiness business = new PartPlanNeedBusiness();

	public PartPlanNeedOrderToolStrip(PartPlanNeedOrderListGrid listGrid) {
		super(DSKey.S_PARTREQUIREMENT);
		this.listGrid = listGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// ADD,DELETE,MODIFY,BUSINESSSTATE,ADDPRO_PLAN,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if (buttonName.equals("ADD")) {
			PartPlanNeedOrderAddWindow addWin = new PartPlanNeedOrderAddWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), addWin, 200);
		} else if (buttonName.equals("ADDPRO_PLAN")) {
			business.addProcurementPlan(listGrid, button);
		} else if (buttonName.equals("DELETE")) {
			business.removedDate(listGrid);
		} else if (buttonName.equals("MODIFY")) {
			if (ValidateUtil.isRecordSelected(listGrid)) {
				PartPlanNeedOrderModifyWindow modify = new PartPlanNeedOrderModifyWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), modify, 200);
			}
		} else if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(listGrid)) {
				PartPlanNeedOrderViewWindow view = new PartPlanNeedOrderViewWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), view, 0);
			}
		} else if (buttonName.equals("REFRESH")) {
			business.refreshSheet(listGrid);
		}
	}
}
