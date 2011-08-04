package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder;
//工作任务分配列表
import java.util.logging.Logger;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PickupDeliveryTaskAssignListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PickupDeliveryTaskAssignListgrid");

	public void drawPickupDeliveryTaskAssignListgrid(boolean isShowLink)
	{
		setCanEdit(false);
		setShowFilterEditor(false);
		setEditEvent(ListGridEditEvent.CLICK);
		setSelectionType(SelectionStyle.SINGLE);

		// 工作类别(link)
		ListGridField assignWorkField = new ListGridField("assignWork","工作类别");
		assignWorkField.setAlign(Alignment.CENTER);
		assignWorkField.setWidth(50);
		// 工作类别
		ListGridField workTypeField = new ListGridField("workType", "工作类别");
		workTypeField.setAlign(Alignment.CENTER);
		workTypeField.setWidth(50);
		workTypeField.setCanEdit(false);
		// 任务开始时间
		ListGridField taskStartDateField = new ListGridField("taskStartDate","任务开始时间");
		taskStartDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 任务结束时间
		ListGridField taskEndDateField = new ListGridField("taskEndDate","任务结束时间");
		taskEndDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 工作状态
		ListGridField workStatusField = new ListGridField("workStatus","工作状态");
		workStatusField.setHidden(true);
		// 工作状态名称
		ListGridField workStatusNameField = new ListGridField("workStatusName","工作状态");
		workStatusNameField.setCanEdit(false);
		// 负责人员
		ListGridField workerField = new ListGridField("worker","负责人员");
		// 备注
		ListGridField memoField = new ListGridField("memo","备注");
		
		if (isShowLink == true) {
			assignWorkField.setHidden(false);
			workTypeField.setHidden(true);
		} else {
			assignWorkField.setHidden(true);
			workTypeField.setHidden(false);
		}

		setFields(assignWorkField
				,workTypeField
				,taskStartDateField
				,taskEndDateField
				,workStatusField
				,workStatusNameField
				,workerField
				,memoField
				);
		
		setCellHeight(22);
	}
}
