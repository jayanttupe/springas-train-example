package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseInstruct;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LeaseInstructListGrid extends ListGrid {
	public LeaseInstructListGrid() {
		// setSelectionType(SelectionStyle.SIMPLE);
		// setSelectionAppearance(SelectionAppearance.CHECKBOX);
		this.setAutoFetchData(true);
		this.setCanEdit(false);
	}

	public void drawGrid() {
		// ListGridField field1 = new ListGridField("留言");
		// field1.setCanFilter(true);

		ListGridField field2 = new ListGridField("orderNumber");
		field2.setCanFilter(true);

		ListGridField field3 = new ListGridField("createDate");
		field3.setCanFilter(true);
		field3.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		ListGridField field4 = new ListGridField("orderedBy");
		field4.setCanFilter(true);

		ListGridField field5 = new ListGridField("contractNumber");
		field5.setCanFilter(true);

		ListGridField field6 = new ListGridField(
				"m_CustomerIdentificationCode.code");
		field6.setCanFilter(true);

		ListGridField field7 = new ListGridField("description");
		field7.setCanFilter(true);

		ListGridField field8 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field8.setCanFilter(true);

		ListGridField field9 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field9.setCanFilter(true);

		setFields(field2, field3, field4, field5, field6, field7, field8,
				field9);
	}

}
