package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ProcurementPlanItemListGrid extends FilterListGrid {

	public ProcurementPlanItemListGrid() {
		this.setShowRowNumbers(false);
		buildListGrid();
	}

	public void buildListGrid() {

		ListGridField f2 = new ListGridField("partNumber");
		f2.setCanFilter(true);

		ListGridField f4 = new ListGridField("deliveryDate");
		f4.setCanFilter(true);
		f4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		ListGridField f5 = new ListGridField("plannedQuantity");
		f5.setCanFilter(true);
		f5.setAlign(Alignment.RIGHT);
		Utils.formatQuantityField(f5);
		ListGridField f6 = new ListGridField("plannedUnitPrice");
		f6.setCanFilter(true);
		f6.setAlign(Alignment.RIGHT);
		Utils.formatPriceField(f6);
		ListGridField f7 = new ListGridField("unitPriceAmount");
		f7.setCanFilter(true);
		f7.setAlign(Alignment.RIGHT);
		Utils.formatPriceField(f7);
		ListGridField f8 = new ListGridField("partRequirementNumber");
		f8.setCanFilter(true);
		ListGridField f9 = new ListGridField("remarkText");
		f9.setCanFilter(true);
		this.setFields(f2, f4, f5, f6, f7, f9);
	}

}
