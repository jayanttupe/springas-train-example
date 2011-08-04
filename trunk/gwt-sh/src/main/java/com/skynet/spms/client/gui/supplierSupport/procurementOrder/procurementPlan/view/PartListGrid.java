package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.view;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartListGrid extends FilterListGrid {

	public PartListGrid() {
		buildListGrid();
	}

	public void buildListGrid() {
		// 需求编号
		ListGridField f1 = new ListGridField("partRequirementNumber");
		// 需求类别
		ListGridField f2 = new ListGridField("m_ProcurementRequiredType");
		// 件号
		ListGridField f3 = new ListGridField("partNumber");
		// 优先级
		ListGridField f5 = new ListGridField("m_Priority");
		// 需求日期
		ListGridField f6 = new ListGridField("expectingDeliveryDate");
		f6.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		this.setFields(f1, f2, f3, f5, f6);
	}
}
