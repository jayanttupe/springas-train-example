package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockPolicy;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockPolicyButtonPanel extends BaseButtonToolStript {
	private StockPolicyListgrid stockPolicyListgrid;

	public StockPolicyButtonPanel(
			final StockPolicyListgrid stockPolicyListgrid) {
		super("stockServiceBusiness.basicData.stockPolicy");
		this.stockPolicyListgrid = stockPolicyListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new StockPolicyDetailWindow("新增库存策略管理", false, rect, stockPolicyListgrid, "showwindow/stock_add_01.png", false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (stockPolicyListgrid.getSelection().length == 1) {
				objWindow = new StockPolicyDetailWindow("修改库存策略管理", false, rect, stockPolicyListgrid, "showwindow/stock_modify_01.png", true);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (stockPolicyListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					stockPolicyListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}