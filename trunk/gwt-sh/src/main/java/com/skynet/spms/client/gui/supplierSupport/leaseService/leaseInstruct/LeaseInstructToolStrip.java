package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseInstruct;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.supplierSupport.leaseService.business.SSLeaseSheetBusiness;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class LeaseInstructToolStrip extends BaseButtonToolStript {
	private LeaseInstructListGrid leaseInstructListGrid;
	private SSLeaseSheetBusiness business;

	public LeaseInstructToolStrip(LeaseInstructListGrid leaseInstructListGrid) {
		super("supplierSupport.LeaseService.LeaseInstruct");
		this.leaseInstructListGrid = leaseInstructListGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// VIEW,ADDCONTRACT,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if (buttonName.equals("VIEW")) {
			if (ValidateUtil.isRecordSelected(leaseInstructListGrid)) {
				LookLeaseInstructWindow window = new LookLeaseInstructWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWondow(button.getPageRect(), window, 200);
			}
		} else if (buttonName.equals("ADDCONTRACT")) {
			business.addSSLeaseContract(leaseInstructListGrid, button);
		} else if (buttonName.equals("REFRESH")) {
			business.refreshSheet(leaseInstructListGrid);
		}
	}

}
