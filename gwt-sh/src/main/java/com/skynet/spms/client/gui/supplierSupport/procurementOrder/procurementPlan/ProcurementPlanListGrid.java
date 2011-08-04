package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ProcurementPlanListGrid extends FilterListGrid {

	public ProcurementPlanListGrid() {
		this.setShowRowNumbers(false);
		buildListGrid();

	}

	public void buildListGrid() {

		// ListGridField f1 = new ListGridField("field1", "留言");
		// f1.setWidth(35);
		// f1.setCanFilter(true);

		ListGridField f2 = new ListGridField("procurementPlanNumber");
		f2.setCanFilter(true);

		ListGridField f3 = new ListGridField("m_ProcurementPlanType");
		f3.setCanFilter(true);

		ListGridField f4 = new ListGridField("startDate");
		f4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		f4.setCanFilter(true);

		ListGridField f5 = new ListGridField("endDate");
		f5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		f5.setCanFilter(true);

		ListGridField f6 = new ListGridField("itemCount");
		f6.setCanFilter(true);

		ListGridField f7 = new ListGridField("totalAmount");
		f7.setCanFilter(true);
		f7.setAlign(Alignment.RIGHT);

		ListGridField f8 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		f8.setCanFilter(true);

		ListGridField f88 = new ListGridField("m_BussinessStatusEntity.status");
		f88.setCanFilter(true);

		ListGridField f9 = new ListGridField("auditStatus");
		f9.setCanFilter(true);

		ListGridField f10 = new ListGridField("createBy");
		f10.setCanFilter(true);

		ListGridField f11 = new ListGridField("createDate");
		f11.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		f11.setCanFilter(true);
		this.setFields(f2, f3, f4, f5, f6, f7, f8, f88, f9, f10, f11);

	}
}
