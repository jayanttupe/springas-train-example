package com.skynet.spms.client.gui.customerService.salesService.salesContract;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 销售合同明细表格
 * 
 * @author fl
 * 
 */
public class SaleContractItemGrid extends BaseListGrid {
	public SaleContractItemGrid() {
		drawGrid();
	}

	public void drawGrid() {

		ListGridField field2 = new ListGridField("partNumber"/* , "件号" */);
		field2.setCanFilter(true);

		ListGridField field3 = new ListGridField("description"/* , "备件状态" */);
		field3.setCanFilter(true);

		ListGridField field4 = new ListGridField("m_ManufacturerCode.code"/*
																		 * ,
																		 * "制造厂商"
																		 */);
		field4.setCanFilter(true);
		field4.setHidden(true);

		ListGridField field5 = new ListGridField("quantity"/* , "数量" */);
		field5.setCanFilter(true);
		Utils.formatQuantityField(field5, "m_UnitOfMeasureCode");

		ListGridField field7 = new ListGridField("unitPrice"/* , "单价" */);
		field7.setCanFilter(true);
		Utils.formatPriceField(field7, "currency");

		ListGridField field9 = new ListGridField("price"/* , "金额" */);
		field9.setCanFilter(true);
		Utils.formatPriceField(field9, "currency");

		setFields(field2, field3, field4, field5, field7, field9);
	}
}
