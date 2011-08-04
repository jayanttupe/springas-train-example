package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 回购申请单明细表格
 * 
 * @author fl
 * 
 */
public class BuyBackSheetItemGrid extends BaseListGrid {

	public BuyBackSheetItemGrid() {
		drawGrid();
	}

	public void drawGrid() {
		/**
		 * 件号
		 */
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);
		/**
		 * 备件描述
		 */
		ListGridField field3 = new ListGridField("remarkText");
		field3.setCanFilter(true);
		/**
		 * 制造厂商
		 */
		ListGridField field4 = new ListGridField("m_ManufacturerCode.code");
		field4.setCanFilter(true);
		/**
		 * 数量
		 */
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5, "m_UnitOfMeasureCode");
		field5.setCanFilter(true);
		/**
		 * 单价
		 */
		ListGridField field6 = new ListGridField("unitPriceAmount");
		Utils.formatPriceField(field6, "currency");
		field6.setCanFilter(true);
		/**
		 * 金额
		 */
		ListGridField field7 = new ListGridField("price");
		Utils.formatPriceField(field7, "currency");
		field7.setCanFilter(true);
		
		/**
		 * 时寿代码
		 */
		ListGridField field11 = new ListGridField("m_ShelfLifeCode");
		field11.setCanFilter(true);
		setFields(field2, field3, field4, field5, field6, field7, field11);

	}
}
