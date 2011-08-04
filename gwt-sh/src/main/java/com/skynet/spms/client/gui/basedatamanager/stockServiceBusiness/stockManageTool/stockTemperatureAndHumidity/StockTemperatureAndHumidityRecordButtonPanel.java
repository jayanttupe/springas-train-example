package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockTemperatureAndHumidityRecordButtonPanel extends BaseButtonToolStript{
	
	private StockTemperatureAndHumidityRecordListGrid stockTemperatureAndHumidityRecordListGrid;
	
	public StockTemperatureAndHumidityRecordButtonPanel(final StockTemperatureAndHumidityRecordListGrid stockTemperatureAndHumidityRecordListGrid){
		super("stockServiceBusiness.stockManageTool.stockTemperatureAndHumidityRecord");

		this.stockTemperatureAndHumidityRecordListGrid = stockTemperatureAndHumidityRecordListGrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("guide".equalsIgnoreCase(buttonName)) {
			objWindow = new StockTemperatureAndHumidityRecordAddWindow("导入", false, rect, stockTemperatureAndHumidityRecordListGrid, "showwindow/stock_add_01.png", false);
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (stockTemperatureAndHumidityRecordListGrid.getSelection().length == 1) {
				objWindow = new StockTemperatureAndHumidityRecordViewWindow(rect, stockTemperatureAndHumidityRecordListGrid);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("print".equalsIgnoreCase(buttonName)) {
			// 打印
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
