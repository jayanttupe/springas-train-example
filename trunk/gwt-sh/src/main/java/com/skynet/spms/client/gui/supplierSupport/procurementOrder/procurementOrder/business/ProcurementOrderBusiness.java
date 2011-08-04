package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add.ProcurementOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ProcurementOrderBusiness extends BaseBusiness {

	public ProcurementOrderModelLocator orderModel = ProcurementOrderModelLocator
			.getInstance();

	public ProcurementOrderBusiness() {

	}

	/**
	 * 添加采购指令
	 */
	public void addProcurementOrder(final ListGrid list, ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(list)) {
			if (list.getSelectedRecord().getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus").equals(
					"published")) {
				ProcurementOrderAddWindow addWin = new ProcurementOrderAddWindow(
						"新建采购指令", true, null, null, "");
				ShowWindowTools.showWindow(button.getPageRect(), addWin, 200);
			} else {
				SC.say("请先发布后在新建采购指令");
			}
		}

	}

}
