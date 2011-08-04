package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResultTrack;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockCheckResultTrackButtonPanel extends BaseButtonToolStript {

	private StockCheckResultTrackListgrid stockCheckTrackResultTrackListgrid;

	public StockCheckResultTrackButtonPanel(
			final StockCheckResultTrackListgrid stockCheckTrackResultTrackListgrid) {
		super("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResultTrack");
		this.stockCheckTrackResultTrackListgrid = stockCheckTrackResultTrackListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("view_sc".equalsIgnoreCase(buttonName)) {
			//	查看盘点报告
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}

}