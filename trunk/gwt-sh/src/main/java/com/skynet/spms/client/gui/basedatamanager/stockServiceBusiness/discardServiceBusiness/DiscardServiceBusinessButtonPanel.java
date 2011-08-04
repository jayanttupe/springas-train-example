package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.discardServiceBusiness;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DiscardServiceBusinessButtonPanel extends BaseButtonToolStript {
	private DiscardServiceBusinessListgrid discardServiceBusinessListgrid;

	public DiscardServiceBusinessButtonPanel(
			final DiscardServiceBusinessListgrid discardServiceBusinessListgrid) {
		super("stockServiceBusiness.partsInventory.discardServiceBusiness");
		this.discardServiceBusinessListgrid = discardServiceBusinessListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new DiscardServiceBusinessDetailWindow("新增备件报废管理", false, rect, discardServiceBusinessListgrid, "showwindow/stock_add_01.png", false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (discardServiceBusinessListgrid.getSelection().length == 1) {
				objWindow = new DiscardServiceBusinessDetailWindow("修改备件报废管理", false, rect, discardServiceBusinessListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (discardServiceBusinessListgrid.getSelection().length == 1) {
				objWindow = new DiscardServiceBusinessDetailWindow("查看备件报废管理", false, rect, discardServiceBusinessListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (discardServiceBusinessListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					discardServiceBusinessListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}