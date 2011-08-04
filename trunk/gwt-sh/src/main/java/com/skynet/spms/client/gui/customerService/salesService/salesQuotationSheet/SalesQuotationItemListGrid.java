package com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SalesQuotationItemListGrid extends FilterListGrid {

	public void drawGrid() {
		this.setShowRowNumbers(false);
		//询价件号
		ListGridField field2 = new ListGridField(
				"salesInquirySheetItem.partNumber");

		//报价件号
		ListGridField field3 = new ListGridField("quotationPartNumber");

		//优先级
		ListGridField field4 = new ListGridField("m_Priority");

		//优先级
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);

		//单价
		ListGridField field6 = new ListGridField("unitPriceAmount");
		Utils.formatPriceField(field6);

		//金额
		ListGridField field7 = new ListGridField("amount");
		Utils.formatPriceField(field7);

		//交货期(天)
		ListGridField field8 = new ListGridField("deliveryLeadTime");

		//备注
		ListGridField field9 = new ListGridField("remark");
		
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		field5.setCanFilter(true);
		field6.setCanFilter(true);
		field7.setCanFilter(true);
		field8.setCanFilter(true);
		field9.setCanFilter(true);

		this.setFields( field2, field3, field4, field5, field6, field7,
				field8, field9);

	}

	@Override
	protected Canvas createRecordComponent(final ListGridRecord record,
			Integer colNum) {

		String fieldName = this.getFieldName(colNum);

		if (fieldName.equals("iconField")) {

		}
		return null;
	}
}
