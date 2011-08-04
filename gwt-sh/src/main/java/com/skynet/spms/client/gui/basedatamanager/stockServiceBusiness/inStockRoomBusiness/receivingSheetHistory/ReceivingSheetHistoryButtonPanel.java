package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheetHistory;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ReceivingSheetHistoryButtonPanel extends BaseButtonToolStript {

	private ReceivingSheetHistoryListgrid receivingSheetHistoryListgrid;

	public ReceivingSheetHistoryButtonPanel(
			final ReceivingSheetHistoryListgrid receivingSheetHistoryListgrid) {
		super("stockServiceBusiness.inStockRoomBusiness.receivingSheetHistory");
		this.receivingSheetHistoryListgrid = receivingSheetHistoryListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("print".equalsIgnoreCase(buttonName)) {
			// 		打印操作
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}

}