package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract;

import com.skynet.spms.client.gui.base.Utils;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SSLeaseContractItemListGrid extends ListGrid {

	public SSLeaseContractItemListGrid() {
		drawGrid();
		this.setAutoFetchData(true);
	}

	public void drawGrid() {

		// 件号
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);

		// 数量
		ListGridField field4 = new ListGridField("quantity");
		field4.setCanFilter(true);
		Utils.formatQuantityField(field4);

		// 起租日期
		ListGridField field6 = new ListGridField("startDate");
		field6.setCanFilter(true);
		field6.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 结束日期
		ListGridField field7 = new ListGridField("endDate");
		field7.setCanFilter(true);
		field7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 租赁天数
		ListGridField field8 = new ListGridField("leaseDays");
		field8.setCanFilter(true);

		// 标准单位租金
		ListGridField field9 = new ListGridField("generalRentOfUnit");
		field9.setCanFilter(true);
		Utils.formatPriceField(field9);

		// 总租金
		ListGridField field11 = new ListGridField("price");
		field11.setCanFilter(true);
		Utils.formatPriceField(field11);

		// 备注
		ListGridField field12 = new ListGridField("remarkText");

		this.setFields(field2, field4, field6, field7, field8, field9, field11,
				field12);
	}
}
