package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockRoomButtonPanel extends BaseButtonToolStript {
	private StockRoomListgrid stockroomManageListgrid;

	public StockRoomButtonPanel(
			final StockRoomListgrid stockroomManageListgrid) {
		super("stockServiceBusiness.basicData.stockRoom");
		this.stockroomManageListgrid = stockroomManageListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new StockRoomDetailWindow("新增库房", false, rect, stockroomManageListgrid, "showwindow/stock_add_01.png", false, false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (stockroomManageListgrid.getSelection().length == 1) {
				objWindow = new StockRoomDetailWindow("修改库房", false, rect, stockroomManageListgrid, "showwindow/stock_modify_01.png", true, false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (stockroomManageListgrid.getSelection().length == 1) {
				objWindow = new StockRoomDetailWindow("查看库房", false, rect, stockroomManageListgrid, "showwindow/stock_modify_01.png", true, true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (stockroomManageListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					stockroomManageListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
