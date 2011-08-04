package com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.ui;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 担保索赔申请明细表格
 * 
 * @author fl
 * 
 */
public class GuaranteeItemGrid extends BaseListGrid {

	public GuaranteeItemGrid() {
	}

	public void drawGrid() {
		/**
		 * 件号
		 */
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);
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
		 * 备注
		 */
		ListGridField field6 = new ListGridField("remarkText");
		field6.setCanFilter(true);
		setFields(field2, field4, field5, field6);
	}
}
