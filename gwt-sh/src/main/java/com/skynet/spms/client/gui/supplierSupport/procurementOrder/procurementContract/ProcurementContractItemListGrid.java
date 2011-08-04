package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 合同明细列表
 * 
 * @author gqr
 * 
 */
public class ProcurementContractItemListGrid extends BaseListGrid {

	public ProcurementContractItemListGrid() {
		drawGrid();
	}

	public void drawGrid() {
		/**
		 * 件号
		 */
		ListGridField field2 = new ListGridField("partNumber"/* , "件号" */);
		field2.setCanFilter(true);
		/**
		 * 交货日期
		 */
		ListGridField field4 = new ListGridField("deliveryDate"/* , "交货日期" */);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		/**
		 * 采购数量
		 */
		ListGridField field5 = new ListGridField("quantity"/* , "采购数量" */);
		Utils.formatQuantityField(field5, "m_UnitOfMeasureCode");
		field5.setCanFilter(true);
		/**
		 * 金额
		 */
		ListGridField field7 = new ListGridField("amount"/* , "金额" */);
		Utils.formatQuantityField(field7, "currency");
		field7.setCanFilter(true);
		/**
		 * 备注
		 */
		ListGridField field8 = new ListGridField("remarkText"/* , "备注" */);
		field8.setCanFilter(true);
		setFields(field2, field4, field5, field7, field8);
	}

}
