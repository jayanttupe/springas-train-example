package com.skynet.spms.client.gui.customerplatform.buybackSheetService.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.customerplatform.buybackSheetService.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerplatform.buybackSheetService.ui.add.BuyBackSheetAddWin;
import com.skynet.spms.client.gui.customerplatform.buybackSheetService.ui.update.BuyBackSheetUpdateWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class BuyBackSheetBusiness extends BaseBusiness{

	public SheetModelLocator sheetModel = SheetModelLocator.getInstance();

	public BuyBackSheetBusiness() {

	}

	/**
	 * 修改申请单
	 */
	public void updateBuyBackSheet(ToolStripButton button) {
		BuyBackSheetUpdateWin updateWin = new BuyBackSheetUpdateWin("修改申请单",
				true, null, null, "");
		sheetModel.openenWindow = updateWin;
		ShowWindowTools.showWindow(button.getPageRect(),updateWin,1);
	}

	/**
	 * 添加申请单
	 */
	public void addBuyBackSheet(ToolStripButton button) {
		BuyBackSheetAddWin addWin = new BuyBackSheetAddWin("新建申请单", true, null,
				null, "");
		sheetModel.openenWindow = addWin;
		ShowWindowTools.showWindow(button.getPageRect(),addWin,1);
	}
}
