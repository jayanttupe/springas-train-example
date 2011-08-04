package com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 主订单列表
 * @author Tony FANG
 *
 */
public class StockSecurityOrderListGrid extends FilterListGrid {

	public StockSecurityOrderListGrid() {

	}

	public void drawGrid() {
		//自动抓取数据的关键
		this.setAutoFetchData(true);

		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setSelectionAppearance(SelectionAppearance.CHECKBOX);

		this.setWidth100();

		//设置显示筛选栏
		this.setShowFilterEditor(true);

		this.setCellHeight(22);

		ListGridField field1 = new ListGridField("field1", "件号");

		ListGridField field2 = new ListGridField("field2", "库存位置");

		ListGridField field3 = new ListGridField("field3", "策略描述");

		ListGridField field4 = new ListGridField("field4", "有效起止日期");

		ListGridField field5 = new ListGridField("field5", "失效结束日期");

		ListGridField field6 = new ListGridField("field6", "是否发布");

		ListGridField field7 = new ListGridField("field7", "最后更新日期");

		ListGridField field8 = new ListGridField("field8", "操作人员");

		setFields(field1, field2, field3, field4, field5, field6, field7,
				field8);

		//绑定假数据
		this.setDataSource(Utils.getXmlDataSource());

	}

}
