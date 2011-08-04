package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LeaseRequisitionSheetListGrid extends ListGrid {
	public LeaseRequisitionSheetListGrid() {
		this.setAutoFetchData(true);
	}

	public void drawGrid() {

		// 申请单编号
		ListGridField field1 = new ListGridField("requisitionSheetNumber");
		field1.setCanFilter(true);
		// 优先级
		ListGridField field11 = new ListGridField("m_Priority");
		field11.setCanFilter(true);
		// 客户名称
		ListGridField field2 = new ListGridField(
				"m_CustomerIdentificationCode.code");
		field2.setCanFilter(true);

		// 申请日期
		ListGridField field3 = new ListGridField("requisitionDate");
		field3.setWidth(100);
		field3.setCanFilter(true);
		field3.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 联系人
		ListGridField field4 = new ListGridField("linkman");
		field4.setCanFilter(true);

		// 联系方式
		ListGridField field5 = new ListGridField("contactWay");
		field5.setCanFilter(true);

		// 是否发布
		ListGridField field6 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field6.setWidth(60);
		field6.setCanFilter(true);

		// 业务状态
		ListGridField field7 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field7.setWidth(60);
		field7.setCanFilter(true);

		setFields(field1, field11, field2, field3, field4, field5, field6,
				field7);
	}
}
