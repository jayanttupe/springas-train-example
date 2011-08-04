package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 主订单列表
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementOrderListGrid extends FilterListGrid {

	public ProcurementOrderListGrid() {
		this.setShowRowNumbers(false);
		drawGrid();
	}

	public void drawGrid() {

		this.setCellHeight(22);

		// ListGridField field1 = new ListGridField("field1", "留言");
		// field1.setWidth(35);
		// field1.setCanFilter(true);
		// 采购指令编号
		ListGridField field2 = new ListGridField("orderNumber");
		field2.setCanFilter(true);
		// 优先级
		ListGridField field3 = new ListGridField("m_Priority");
		field3.setWidth(60);
		field3.setCanFilter(true);
		// 指令下单时间
		ListGridField field4 = new ListGridField("createDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		// 计划员
		ListGridField field5 = new ListGridField("createBy");
		field5.setCanFilter(true);
		// 指令来源
		ListGridField field8 = new ListGridField("procurementPlanNumber");
		field8.setCanFilter(true);
		// 发布状态
		ListGridField field88 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		// 业务状态
		ListGridField field9 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field9.setWidth(60);
		field9.setCanFilter(true);
		// 备注
		ListGridField field10 = new ListGridField("remarkText");
		field10.setCanFilter(true);
		setFields(field2, field3, field4, field5, field8, field88, field9,
				field10);
	}

}
