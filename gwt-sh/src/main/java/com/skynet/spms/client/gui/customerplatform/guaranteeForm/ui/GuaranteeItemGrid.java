package com.skynet.spms.client.gui.customerplatform.guaranteeForm.ui;

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

		ListGridField field2 = new ListGridField("partNumber"/*, "件号"*/);

		ListGridField field4 = new ListGridField("m_ManufacturerCode.code"/*, "制造厂商"*/);

		ListGridField field5 = new ListGridField("quantity"/*, "数量"*/);
		Utils.formatQuantityField(field5);

		ListGridField field6 = new ListGridField("remarkText"/*, "备注"*/);

		setFields(field2, /*field3,*/ field4, field5, field6);
	}
}
