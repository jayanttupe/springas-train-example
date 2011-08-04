package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 回购合同明细表格
 * 
 * @author fl
 * 
 */
public class BuyBackPactItemGrid extends BaseListGrid {

	public BuyBackPactItemGrid() {
		drawGrid();
	}

	public void drawGrid() {
		ListGridField field2 = new ListGridField("partNumber"/* , "件号" */);
		field2.setCanFilter(true);
		ListGridField field3 = new ListGridField("m_ConditionCode"/* , "备件状态" */);
		field3.setCanFilter(true);
		ListGridField field4 = new ListGridField("m_ManufacturerCode.code"/*
																		 * ,
																		 * "制造厂商"
																		 */);
		field4.setCanFilter(true);

		ListGridField field5 = new ListGridField("quantity"/* , "数量" */);
		Utils.formatQuantityField(field5, "m_UnitOfMeasureCode");
		field5.setCanFilter(true);
		ListGridField field7 = new ListGridField("unitPrice"/* , "单价" */);
		Utils.formatQuantityField(field7, "currency");
		field7.setCanFilter(true);
		ListGridField field9 = new ListGridField("price"/* , "金额" */);
		Utils.formatQuantityField(field9, "currency");
		field9.setCanFilter(true);
		ListGridField field10 = new ListGridField("m_PackagingCode"/* , "包装代码" */);
		field10.setCanFilter(true);
		ListGridField field11 = new ListGridField("m_ShelfLifeCode"/* , "时寿代码" */);
		field11.setCanFilter(true);
		setFields(field2, field3, field4, field5, field7, field9, field10,
				field11);
		setCellHeight(22);
	}
}
