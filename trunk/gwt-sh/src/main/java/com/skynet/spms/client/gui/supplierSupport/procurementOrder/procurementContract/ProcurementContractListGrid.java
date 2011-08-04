package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 采购合同
 * 
 * @author gqr
 * 
 */
public class ProcurementContractListGrid extends BaseListGrid {
	public ProcurementContractListGrid() {
		drawGrid();
	}

	public void drawGrid() {
		// 启动可渲染
		ListGridField field1 = new ListGridField("message"/* , "留言" */);
		Utils.setMessageFieldWidth(field1);

		ListGridField field2 = new ListGridField("contractNumber"/* , "合同编号" */);
		field2.setCanFilter(true);
		ListGridField field3 = new ListGridField("m_Priority"/* , "优先级" */);
		field3.setCanFilter(true);
		ListGridField field4 = new ListGridField("seller.code"/* , "供应商名称" */);
		field4.setCanFilter(true);
		ListGridField field7 = new ListGridField("extendedValueTotalAmount"/*
																			 * ,
																			 * "总金额"
																			 */);
		field7.setCanFilter(true);
		ListGridField field8 = new ListGridField("makeBy"/* ,"制定人" */);
		field8.setCanFilter(true);
		ListGridField field9 = new ListGridField("procurementPlanNumber"/*
																		 * ,
																		 * "采购指令号"
																		 */);
		field9.setCanFilter(true);
		ListGridField field10 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus"/* , "发布状态" */);
		field10.setCanFilter(true);
		ListGridField field11 = new ListGridField(
				"m_BussinessStatusEntity.status"/* , "业务状态" */);
		field11.setCanFilter(true);
		ListGridField field12 = new ListGridField("auditStatus"/* , "审批状态" */);
		field12.setCanFilter(true);
		setFields(field1, field2, field3, field4, field7, field8, field9,
				field10, field11, field12);
	}

}
