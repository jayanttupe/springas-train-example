package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.pickingTask;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.StockTaskListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PickingTaskButtonPanel extends BaseButtonToolStript {

	private StockTaskListgrid stockTaskListgrid;
	private PickingTaskItemListgrid pickingTaskItemListgrid;
	
	public PickingTaskButtonPanel(StockTaskListgrid stockTaskListgrid,
			PickingTaskItemListgrid pickingTaskItemListgrid) {
		super("stockServiceBusiness.stockTask.pickingTask");

		this.stockTaskListgrid = stockTaskListgrid;
		this.pickingTaskItemListgrid = pickingTaskItemListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		// 拣货处理
		if ("picking".equalsIgnoreCase(buttonName)) {
			// 取得选择行
			ListGridRecord[] shelveTaskDetailRecords = pickingTaskItemListgrid.getSelection();
			String[] barcodeTagUUIDs = new String[shelveTaskDetailRecords.length];
			String[] tagIdentifierCodes = new String[shelveTaskDetailRecords.length];
			if (pickingTaskItemListgrid.getSelection().length >= 1) {
				ListGridRecord record = new ListGridRecord();
				for (int i = 0;i < barcodeTagUUIDs.length; i++) {
					barcodeTagUUIDs[i] = pickingTaskItemListgrid.getSelection()[i].getAttribute("barcodeTagUUID");
					tagIdentifierCodes[i] = pickingTaskItemListgrid.getSelection()[i].getAttribute("tagIdentifierCode");	
				}
				record.setAttribute("pickingListID", stockTaskListgrid.getSelectedRecord().getAttribute("bussinessBillNO"));
				record.setAttribute("taskNo", stockTaskListgrid.getSelectedRecord().getAttribute("taskNo"));
				record.setAttribute("recordCount", barcodeTagUUIDs.length);
				record.setAttribute("barcodeTagUUIDs", barcodeTagUUIDs);
				record.setAttribute("tagIdentifierCodes", tagIdentifierCodes);
				pickingTaskItemListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("拣货成功！");

						String taskID = stockTaskListgrid.getSelectedRecord().getAttribute("id");
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("taskID", "" + taskID);
						pickingTaskItemListgrid.fetchData(criteria);
					}
				});
			} else {
				SC.say("请选择一条记录进行上架！");
			}
		}
	}
}