package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 主订单列表
 * 
 * @author Tony FANG
 * 
 */
public class PartPlanNeedOrderListGrid extends FilterListGrid {

	public PartPlanNeedOrderListGrid() {
		// 自动抓取数据的关键
		this.setShowRowNumbers(false);
		drawGrid();
	}

	public void drawGrid() {

		// ListGridField field1 = new ListGridField("field1", "留言");
		// field1.setCanFilter(true);
		// 备件需求编号
		ListGridField field2 = new ListGridField("partRequirementNumber");
		field2.setCanFilter(true);
		// 需求类型
		ListGridField field3 = new ListGridField("m_ProcurementRequiredType");
		field3.setCanFilter(true);
		// 备件编号
		ListGridField field4 = new ListGridField("partNumber");
		field4.setCanFilter(true);
		// 优先级
		ListGridField field6 = new ListGridField("m_Priority");
		field6.setCanFilter(true);
		// 需求日期
		ListGridField field7 = new ListGridField("expectingDeliveryDate");
		field7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field7.setCanFilter(true);
		// 创建人
		ListGridField field8 = new ListGridField("createBy");
		field8.setCanFilter(true);
		// 发布状态
		ListGridField field9 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field9.setCanFilter(true);
		setFields(field2, field3, field4, field6, field7, field8, field9);

	}

}
