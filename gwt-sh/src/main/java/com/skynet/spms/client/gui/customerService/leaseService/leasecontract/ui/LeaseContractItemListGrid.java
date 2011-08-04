package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui;

import com.skynet.spms.client.gui.base.Utils;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LeaseContractItemListGrid extends ListGrid {

	public LeaseContractItemListGrid() {
		this.setCanEdit(false);
		drawGrid();
	}

	public void drawGrid() {

		// 件号
		ListGridField field1 = new ListGridField("partNumber");
		field1.setCanFilter(true);

		// 关键字
		ListGridField field2 = new ListGridField("keyword");
		field2.setCanFilter(true);

		// 数量
		ListGridField field4 = new ListGridField("quantity");
		Utils.formatQuantityField(field4);
		field4.setCanFilter(true);

		// 起租日期
		ListGridField field5 = new ListGridField("startDate");
		field5.setCanFilter(true);
		field5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 结束日期
		ListGridField field6 = new ListGridField("endDate");
		field6.setCanFilter(true);
		field6.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 租赁天数
		ListGridField field7 = new ListGridField("leaseDays");
		field7.setCanFilter(true);

		// 标准单位租金
		ListGridField field8 = new ListGridField("generalRentOfUnit");
		Utils.formatPriceField(field8);
		field8.setCanFilter(true);

		// 手续费
		ListGridField field9 = new ListGridField("factorage");
		Utils.formatPriceField(field9);
		field9.setCanFilter(true);

		// 金额
		ListGridField field10 = new ListGridField("price");
		Utils.formatPriceField(field10);
		field10.setCanFilter(true);

		setFields(field1, field2, field4, field5, field6, field7, field8,
				field9, field10);
	}
}
