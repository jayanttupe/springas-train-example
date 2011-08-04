package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SSLeaseContractListGrid extends ListGrid {

	public SSLeaseContractListGrid() {
		drawGrid();
	}

	public void drawGrid() {
		// ListGridField field1 = new ListGridField("留言");
		// field1.setCanFilter(true);

		// 合同编号
		ListGridField field2 = new ListGridField("contractNumber");
		field2.setCanFilter(true);

		// 优先级
		ListGridField field3 = new ListGridField("m_Priority");
		field3.setCanFilter(true);

		// 供应商名称
		ListGridField field4 = new ListGridField("seller.code");
		field4.setCanFilter(true);

		// 合同金额
		ListGridField field5 = new ListGridField("extendedValueTotalAmount");
		field5.setCanFilter(true);

		// 创建日期
		ListGridField field6 = new ListGridField("createDate");
		field6.setCanFilter(true);
		field6.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 制定人
		ListGridField field7 = new ListGridField("makeBy");
		field7.setCanFilter(true);

		// 联系人
		ListGridField field8 = new ListGridField("linkman");
		field8.setCanFilter(true);

		// 发布状态
		ListGridField field9 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field9.setCanFilter(true);

		// 业务状态
		ListGridField field10 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field10.setCanFilter(true);

		// 审批状态
		ListGridField field11 = new ListGridField("auditStatus");
		field11.setCanFilter(true);

		this.setFields(field2, field3, field4, field5, field6, field7, field8,
				field9, field10, field11);
	}
}
