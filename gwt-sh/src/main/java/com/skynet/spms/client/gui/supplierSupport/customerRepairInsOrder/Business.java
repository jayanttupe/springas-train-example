package com.skynet.spms.client.gui.supplierSupport.customerRepairInsOrder;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder.view.CustomerRepairInspectionOrderViewWindow;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.add.RepairInsClaimContractTemplateAddWin;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class Business extends BaseBusiness{

	ModelLocator model = ModelLocator.getInstance();

	/**
	 * 查看送修指令
	 * 
	 * @param grid
	 * @param button
	 */
	public void viewSheetDetail(final ListGrid grid, ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {
			CustomerRepairInspectionOrderViewWindow win = new CustomerRepairInspectionOrderViewWindow(
					grid);
			win.show();
		}
	}

	/**
	 * 新建合同
	 * 
	 * @param grid
	 * @param button
	 */
	public void addContract(final ListGrid grid, ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {
			model.selectedRecord=grid.getSelectedRecord();
			RepairInsClaimContractTemplateAddWin addWin = new RepairInsClaimContractTemplateAddWin();
			addWin.show();
		}
	}

}
