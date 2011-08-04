package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LeaseContractListGrid extends ListGrid {

	public LeaseContractListGrid() {
		drawGrid();
	}

	public void drawGrid() {

		// ListGridField userGroupsField = new ListGridField("留言");
		// userGroupsField.setWidth(35);

		// 合同编号
		ListGridField field1 = new ListGridField("contractNumber");
		field1.setCanFilter(true);

		// 优先级
		ListGridField field2 = new ListGridField("m_Priority");
		field2.setCanFilter(true);

		// 客户名称
		ListGridField field3 = new ListGridField("buyer.code");
		field3.setCanFilter(true);

		// 总金额
		ListGridField field6 = new ListGridField("extendedValueTotalAmount");

		// 制定人
		ListGridField field7 = new ListGridField("makeBy");
		field7.setCanFilter(true);

		// 发布状态
		ListGridField field9 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field9.setCanFilter(true);
		field9.setWidth(60);

		// 业务状态
		ListGridField field10 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field10.setWidth(60);
		field10.setCanFilter(true);

		// 审核状态
		ListGridField field11 = new ListGridField("auditStatus");
		field11.setWidth(60);
		field11.setCanFilter(true);

		setFields(field1, field2, field3, field6, field7, field9, field10,
				field11);
	}
}
