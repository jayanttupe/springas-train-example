package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.add.ProcurementPlanAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PartPlanNeedBusiness extends BaseBusiness {

	public MainModelLocator model = MainModelLocator.getInstance();
	private I18n ui = new I18n();

	public PartPlanNeedBusiness() {

	}

	/**
	 * 新建采购计划
	 */
	public void addProcurementPlan(final ListGrid list, ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(list)) {
			ProcurementPlanAddWindow ppa = new ProcurementPlanAddWindow(
					"新建采购计划", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
		}

	}

	public void removedDate(final ListGrid list) {
		if (ValidateUtil.isRecordSelected(list)) {
			SC.confirm(ui.getB().msgDelWarn(), new BooleanCallback() {
				public void execute(Boolean value) {
					if (value) {
						for (ListGridRecord listGridRecord : list
								.getSelection()) {
							list.removeData(listGridRecord);
						}
					}
				}
			});
		}
	}
}
