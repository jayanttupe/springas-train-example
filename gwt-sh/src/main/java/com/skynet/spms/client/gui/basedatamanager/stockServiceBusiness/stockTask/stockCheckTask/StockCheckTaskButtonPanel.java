package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.stockCheckTask;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.StockTaskListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StockCheckTaskButtonPanel extends BaseButtonToolStript {

	private StockTaskListgrid stockTaskListgrid;
	private StockCheckTaskItemListgrid stockCheckTaskItemListgrid;
	
	public StockCheckTaskButtonPanel(StockTaskListgrid stockTaskListgrid,
			StockCheckTaskItemListgrid stockCheckTaskItemListgrid) {
		super("stockServiceBusiness.stockTask.stockCheckTask");

		this.stockTaskListgrid = stockTaskListgrid;
		this.stockCheckTaskItemListgrid = stockCheckTaskItemListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
	}
}