package com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 订单明细列表
 * 
 * @author Tony FANG
 * 
 */
public class TransferOrderItemListGrid extends FilterListGrid {

	public TransferOrderItemListGrid() {

	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
		//项号
		ListGridField field1 = new ListGridField("itemNumber");
		Utils.setMessageFieldWidth(field1);
		
		//件号
		ListGridField field2 = new ListGridField("partNumber");

		//关键字
		ListGridField field3 = new ListGridField("itemKeyword");
		field3.setHidden(true);

		//交货日期
		ListGridField field4 = new ListGridField("deliveryDate");

		//调拨数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		Utils.formatQuantityField(field5);

//		ListGridField field7 = new ListGridField("plannedUnitPrice", "调拨单价");
//		Utils.formatPriceField(field7);
//
//		ListGridField field8 = new ListGridField("amount", "金额");
//		Utils.formatPriceField(field8);
//		field8.setAlign(Alignment.RIGHT);

		//备注
		ListGridField field9 = new ListGridField("remarkText");

		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		field5.setCanFilter(true);

//		field7.setCanFilter(true);
//		field8.setCanFilter(true);
		field9.setCanFilter(true);
		this.setFields(field1, field2, field3, field4, field5,
				field9);

	}

}
