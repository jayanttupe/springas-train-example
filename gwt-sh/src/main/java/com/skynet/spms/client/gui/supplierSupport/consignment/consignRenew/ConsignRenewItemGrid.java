package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 供应商寄售补库申请单明细表格
 * 
 * @author fl
 * 
 */
public class ConsignRenewItemGrid extends BaseListGrid {

	public ConsignRenewItemGrid() {
		drawGrid();
	}

	public void drawGrid() {
		/**
		 * 件号
		 */
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);
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
		Utils.formatPriceField(field6,"currency");
		field6.setCanFilter(true);
		/**
		 * 金额
		 */
		ListGridField field7 = new ListGridField("price");
		Utils.formatPriceField(field7,"currency");
		field7.setCanFilter(true);
		/**
		 * 备注
		 */
		ListGridField field8 = new ListGridField("remarkText");
		field8.setCanFilter(true);

		setFields(field2,field5, field6, field7, field8);
	}
}
