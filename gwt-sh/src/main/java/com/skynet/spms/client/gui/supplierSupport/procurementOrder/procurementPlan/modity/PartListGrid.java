package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.modity;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartListGrid extends FilterListGrid {

	public PartListGrid() {
		buildListGrid();
	}

	public void buildListGrid() {
		ListGridField f1 = new ListGridField("partRequirementNumber");
		ListGridField f2 = new ListGridField("m_ProcurementRequiredType");
		ListGridField f3 = new ListGridField("partNumber");
		ListGridField f5 = new ListGridField("m_Priority");
		ListGridField f6 = new ListGridField("expectingDeliveryDate");
		f6.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		this.setFields(f1, f2, f3, f5, f6);
	}
}
