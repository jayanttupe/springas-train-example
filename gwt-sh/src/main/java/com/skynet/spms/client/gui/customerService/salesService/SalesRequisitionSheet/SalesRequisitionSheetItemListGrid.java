package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SalesRequisitionSheetItemListGrid extends FilterListGrid {

	private DataSource quotationDs;

	public DataSource getSalesInquiryDs() {
		return quotationDs;
	}

	public void setSalesInquiryDs(DataSource quotationDs) {
		this.quotationDs = quotationDs;
	}

	public SalesRequisitionSheetItemListGrid() {

	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
		
		//件号
		ListGridField field2 = new ListGridField("partNumber");

		//关键字
		ListGridField field3 = new ListGridField("keyword");
		field3.setHidden(true);

		//制造商
		ListGridField field4 = new ListGridField("m_ManufacturerCode");
		field4.setHidden(true);

		//数量
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);
		
		//单价
		ListGridField field6 = new ListGridField("unitOfPrice");
		Utils.formatPriceField(field6);

		//金额
		ListGridField field7 = new ListGridField("amount");
		Utils.formatPriceField(field7);

		//交货日期
		ListGridField field8 = new ListGridField("partDeliveryDate");
		field8.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//备注
		ListGridField field9 = new ListGridField("remarkText");
		
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

}
