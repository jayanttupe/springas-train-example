package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.add.BuyBackPactAddWin;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.update.BuyBackPactUpdateWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class BuyBackPactBusiness extends BaseBusiness {

	public BuybackPactModelLocator model = BuybackPactModelLocator.getInstance();

	public BuyBackPactBusiness() {

	}

	/**
	 * 修改回购合同
	 */
	public void updateBuyBackPact(ToolStripButton button) {
		BuyBackPactUpdateWin updateWin = new BuyBackPactUpdateWin("修改合同", true,
				null, null, "");
		model.openedWindow = updateWin;
		ShowWindowTools.showWindow(button.getPageRect(), updateWin, 1);
	}

	/**
	 * 添加回购合同
	 */
	public void addBuyBackPact(ToolStripButton button) {
		BuyBackPactAddWin addWin = new BuyBackPactAddWin("新建回购合同", true, null, null,
				"");
		model.openedWindow = addWin;
		ShowWindowTools.showWindow(button.getPageRect(), addWin, 1);
	}

}
