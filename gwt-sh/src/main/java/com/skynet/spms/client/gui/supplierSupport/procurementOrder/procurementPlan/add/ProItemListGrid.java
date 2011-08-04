package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.add;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ProItemListGrid extends ListGrid {

	public ProItemListGrid() {
		setCanRemoveRecords(true);
		buildListGrid();

	}

	public void buildListGrid() {
		ListGridField f22 = new ListGridField("partNumber");
		ListGridField f33 = new ListGridField("itemKeyword");
		ListGridField f44 = new ListGridField("plannedQuantity");
		ListGridField f55 = new ListGridField("deliveryDate");
		f55.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		this.setFields(f22, f33, f44, f55);
	}
}
