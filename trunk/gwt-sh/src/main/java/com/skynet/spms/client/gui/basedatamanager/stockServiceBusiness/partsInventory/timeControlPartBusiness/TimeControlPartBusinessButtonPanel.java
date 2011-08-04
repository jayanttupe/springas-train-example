package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.timeControlPartBusiness;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class TimeControlPartBusinessButtonPanel extends BaseButtonToolStript {
	private TimeControlPartBusinessListgrid timeControlPartBusinessListgrid;

	public TimeControlPartBusinessButtonPanel(
			final TimeControlPartBusinessListgrid timeControlPartBusinessListgrid) {
		super("stockServiceBusiness.partsInventory.timeControlPartBusiness");
		this.timeControlPartBusinessListgrid = timeControlPartBusinessListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("dispose".equalsIgnoreCase(buttonName)) {
			if (timeControlPartBusinessListgrid.getSelection().length == 1) {
				objWindow = new TimeControlPartBusinessDetailWindow("处理库存时控件预警管理", false, rect, timeControlPartBusinessListgrid, "showwindow/stock_modify_01.png", true);
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}