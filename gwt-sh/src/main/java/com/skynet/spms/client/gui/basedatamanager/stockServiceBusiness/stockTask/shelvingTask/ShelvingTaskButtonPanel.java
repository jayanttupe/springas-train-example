package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.shelvingTask;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.StockTaskListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ShelvingTaskButtonPanel extends BaseButtonToolStript {

	private StockTaskListgrid stockTaskListgrid;
	private ShelvingTaskItemListgrid shelvingTaskItemListgrid;
	
	public ShelvingTaskButtonPanel(StockTaskListgrid stockTaskListgrid
			,ShelvingTaskItemListgrid shelvingTaskItemListgrid) {
		super("stockServiceBusiness.stockTask.shelvingTask");
		this.stockTaskListgrid = stockTaskListgrid;
		this.shelvingTaskItemListgrid = shelvingTaskItemListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		// 上架处理
		if ("shelving".equalsIgnoreCase(buttonName)) {
			// 取得选择行
			ListGridRecord[] shelveTaskDetailRecords = shelvingTaskItemListgrid.getSelection();
			String[] barcodeTagUUIDs = new String[shelveTaskDetailRecords.length];
			String[] tagIdentifierCodes = new String[shelveTaskDetailRecords.length];
			String[] partNumbers = new String[shelveTaskDetailRecords.length];
			String[] partSerialNumbers = new String[shelveTaskDetailRecords.length];
			String[] cargoSpaceNumbers = new String[shelveTaskDetailRecords.length];
			
			if (shelvingTaskItemListgrid.getSelection().length >= 1) {
				ListGridRecord record = new ListGridRecord();
				
				for (int i = 0;i < barcodeTagUUIDs.length; i++) {
					Record curRecord = shelvingTaskItemListgrid.getEditedRecord(i);
					barcodeTagUUIDs[i] = curRecord.getAttribute("barcodeTagUUID");
					tagIdentifierCodes[i] = curRecord.getAttribute("tagIdentifierCode");	
					partNumbers[i] = curRecord.getAttribute("partNumber");	
					partSerialNumbers[i] = curRecord.getAttribute("partSerialNumber");	
					cargoSpaceNumbers[i] = curRecord.getAttribute("cargoSpaceNumber");	
				}
				
				record.setAttribute("taskNo", stockTaskListgrid.getSelectedRecord().getAttribute("taskNo"));
				record.setAttribute("barcodeTagUUIDs", barcodeTagUUIDs);
				record.setAttribute("tagIdentifierCodes", tagIdentifierCodes);
				record.setAttribute("partNumbers", partNumbers);
				record.setAttribute("partSerialNumbers", partSerialNumbers);
				record.setAttribute("cargoSpaceNumbers", cargoSpaceNumbers);
				
				shelvingTaskItemListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("上架成功！");

						String taskID = stockTaskListgrid.getSelectedRecord().getAttribute("id");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("taskID", "" + taskID);
						DataSource detailDataSource = shelvingTaskItemListgrid.getDataSource();
						shelvingTaskItemListgrid.setDataSource(detailDataSource);
						shelvingTaskItemListgrid.drawShelvingTaskItemListgrid();
						shelvingTaskItemListgrid.fetchData(criteria);
					}
				});
			} else {
				SC.say("请选择一条记录进行上架！");
			}
		}
	}
}