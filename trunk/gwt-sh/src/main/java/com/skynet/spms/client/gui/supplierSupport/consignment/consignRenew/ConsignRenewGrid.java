package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 供应商寄售补库申请单表格
 * 
 * @author fl
 * 
 */
public class ConsignRenewGrid extends BaseListGrid {

	public ConsignRenewGrid() {
		drawGrid();
	}

	public void drawGrid() {
		ListGridField field1 = new ListGridField("message"/* , "留言" */);
		Utils.setMessageFieldWidth(field1);

		ListGridField field2 = new ListGridField("requisitionSheetNumber"/*
																		 * ,
																		 * "补库编号"
																		 */);
		field2.setCanFilter(true);

		ListGridField field3 = new ListGridField("supplier.code", "供应商名称");
		field3.setCanFilter(true);

		ListGridField field4 = new ListGridField("totalAmount"/* , "总金额" */);
		field4.setCanFilter(true);

		ListGridField field5 = new ListGridField("template.contractNumber"/*
																		 * ,
																		 * "寄售协议"
																		 */);
		field5.setCanFilter(true);

		ListGridField field6 = new ListGridField("consignAddr"/* , "寄售地点" */);
		field6.setCanFilter(true);

		ListGridField field7 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus" /* , "发布状态" */);
		field7.setCanFilter(true);

		ListGridField field8 = new ListGridField(
				"m_BussinessStatusEntity.status"/* , "业务状态" */);
		field8.setCanFilter(true);

		ListGridField auditStatus = new ListGridField("auditStatus");

		ListGridField field9 = new ListGridField("createDate"/* , "创建时间" */);
		field9.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field9.setCanFilter(true);

		ListGridField field10 = new ListGridField("createBy"/* , "制定人" */);
		field10.setCanFilter(true);

		setFields(field1, field2, field3, field4, field5, field6, field7,
				field8, auditStatus, field9, field10);
	}
}
