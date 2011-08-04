package com.skynet.spms.client.gui.customerplatform.buybackSheetService.ui;

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
		ListGridField field2 = new ListGridField("partNumber"/*, "件号"*/);
		ListGridField field3 = new ListGridField("remarkText"/*, "备件描述"*/);
		ListGridField field4 = new ListGridField("m_ManufacturerCode.code"/*,
				"制造厂商"*/);
		ListGridField field5 = new ListGridField("quantity"/*, "数量"*/);
		ListGridField field6 = new ListGridField("m_UnitOfMeasureCode"/*, "单位"*/);
		ListGridField field7 = new ListGridField("unitPriceAmount"/*, "单价"*/);
		ListGridField field10 = new ListGridField("m_PackagingCode"/*, "包装代码"*/);
		ListGridField field11 = new ListGridField("m_ShelfLifeCode"/*, "时寿代码"*/);
		setFields(field2, field3, field4, field5, field6, field7,
				field10, field11);
	}
}
