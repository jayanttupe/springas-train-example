package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockCheckResultButtonPanel extends BaseButtonToolStript {

	private StockCheckResultListgrid stockCheckResultListgrid;

	public StockCheckResultButtonPanel(
			final StockCheckResultListgrid stockCheckResultListgrid) {
		super("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResult");
		this.stockCheckResultListgrid = stockCheckResultListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("view_sc".equalsIgnoreCase(buttonName)) {
			if (stockCheckResultListgrid.getSelection().length == 1) {
				objWindow = new StockCheckResultDetailWindow("查看盘点结果",
						false,
						rect,
						stockCheckResultListgrid,
						"showwindow/stock_add_01.png",
						true,
						false);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("submit_scr".equalsIgnoreCase(buttonName)){
			if (stockCheckResultListgrid.getSelection().length == 1) {
				ListGridRecord rdd = stockCheckResultListgrid.getSelectedRecord();
				rdd.setAttribute("state", "已上报");
				stockCheckResultListgrid.updateData(rdd);
			} else {
				SC.say("请选择一条记录进行上报！");
			}
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}