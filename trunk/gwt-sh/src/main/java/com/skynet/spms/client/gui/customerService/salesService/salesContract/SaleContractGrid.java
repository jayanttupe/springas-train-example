package com.skynet.spms.client.gui.customerService.salesService.salesContract;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 销售合同信息 (Grid)
 * 
 * @author fl
 * 
 */
public class SaleContractGrid extends BaseListGrid {
	public SaleContractGrid() {
		drawGrid();
	}

	public void drawGrid() {
		ListGridField field1 = new ListGridField("message"/* , "留言" */);
		Utils.setMessageFieldWidth(field1);
		ListGridField field2 = new ListGridField("contractNumber");
		field2.setCanFilter(true);
		ListGridField m_Priority = new ListGridField("m_Priority");
		m_Priority.setCanFilter(true);
		ListGridField field3 = new ListGridField("customerIdenty.code");
		field3.setCanFilter(true);
		ListGridField field4 = new ListGridField("createDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		ListGridField field5 = new ListGridField("extendedValueTotalAmount");
		field5.setCanFilter(true);
		ListGridField field8 = new ListGridField("createBy");
		field8.setCanFilter(true);
		ListGridField field9 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field9.setCanFilter(true);
		ListGridField field10 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field10.setCanFilter(true);
		ListGridField field11 = new ListGridField("auditStatus");
		field11.setCanFilter(true);
		setFields(field1, field2, m_Priority, field3, field4, field5, field8,
				field9, field10, field11);
		for (ListGridField field : getFields()) {
			field.setCanFilter(true);
		}
	}
}
