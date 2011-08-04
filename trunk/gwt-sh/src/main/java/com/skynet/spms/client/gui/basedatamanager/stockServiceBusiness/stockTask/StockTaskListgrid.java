package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockTaskListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockTaskListgrid");

	public void drawStockTaskListgrid() {
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 任务编号
		ListGridField taskNoField = new ListGridField("taskNo");
		// 任务类型
		ListGridField taskTypeField = new ListGridField("taskType");
		// 任务来源
		ListGridField taskSourceField = new ListGridField("taskSource");
		// 任务状态(OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
		ListGridField taskStatusField = new ListGridField("taskStatus");
		// 任务创建人
		ListGridField taskByField = new ListGridField("taskBy");
		// 任务日期
		ListGridField taskDateField = new ListGridField("taskDate");
		taskDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 任务执行人
		ListGridField actionByField = new ListGridField("actionBy");
		// 任务执行日期
		ListGridField actionDateField = new ListGridField("actionDate");
		actionDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		taskNoField.setCanFilter(true);
		taskTypeField.setCanFilter(true);
		taskSourceField.setCanFilter(true);
		taskByField.setCanFilter(true);
		taskDateField.setCanFilter(true);
		taskStatusField.setCanFilter(true);
		actionByField.setCanFilter(true);
		actionDateField.setCanFilter(true);
		
		setFields(taskNoField,
				taskTypeField,
				taskSourceField,
				taskStatusField,
				taskByField,
				taskDateField,
				actionByField,
				actionDateField);
		setCellHeight(22);
	}
}