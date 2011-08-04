package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 寄售协议明细表格
 * 
 * @author fl
 * 
 */
public class ConsignProtocolItemGrid extends BaseListGrid {

	public ConsignProtocolItemGrid() {
		drawGrid();
	}

	public void drawGrid() {
		// 设置删除按钮
		ListGridField field2 = new ListGridField("partNumber" /* , "件号" */);
		field2.setCanFilter(true);
		
		ListGridField field4 = new ListGridField("quantity" /* , "数量" */);
		Utils.formatQuantityField(field4,"m_UnitOfMeasureCode");
		field4.setCanFilter(true);
		
		ListGridField field5 = new ListGridField("unitPrice" /* , "单价" */);
		Utils.formatPriceField(field5, "currency");
		field5.setCanFilter(true);
		
		ListGridField field6 = new ListGridField("price" /* , "金额" */);
		Utils.formatPriceField(field6, "currency");
		field6.setCanFilter(true);
		
		ListGridField field7 = new ListGridField("consignAddr" /* , "寄售地点" */);
		field7.setCanFilter(true);
		
		ListGridField field8 = new ListGridField("remarkText" /* , "备注" */);
		field8.setCanFilter(true);
		setFields(field2, field4, field5, field6, field7,
				field8);
	}
}
