package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet;

import com.skynet.spms.client.gui.base.Utils;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LeaseRequisitionSheetItemListGrid extends ListGrid {

	public LeaseRequisitionSheetItemListGrid() {
		this.setCanEdit(false);
		drawGrid();
	}

	public void drawGrid() {

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		partNumberField.setCanFilter(true);

		// 关键字
		ListGridField keywordField = new ListGridField("keyword");
		keywordField.setCanFilter(true);

		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		Utils.formatQuantityField(quantityField);
		quantityField.setCanFilter(true);

		// 租赁开始日期
		ListGridField startDateField = new ListGridField("startDate");
		startDateField.setWidth(80);
		startDateField.setCanFilter(true);
		startDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 租赁结束日期
		ListGridField endDateField = new ListGridField("endDate");
		endDateField.setWidth(80);
		endDateField.setCanFilter(true);
		endDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 租赁天数
		ListGridField leaseDaysField = new ListGridField("leaseDays");
		leaseDaysField.setCanFilter(true);

		setFields(partNumberField, keywordField, startDateField, endDateField,
				leaseDaysField);
	}
}
