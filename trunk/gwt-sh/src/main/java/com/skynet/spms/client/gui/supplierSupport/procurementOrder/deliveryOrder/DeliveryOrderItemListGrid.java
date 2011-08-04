package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 订单明细列表
 * @author Tony FANG
 *
 */
public class DeliveryOrderItemListGrid extends FilterListGrid {

	public DeliveryOrderItemListGrid() {

	}

	public void drawGrid() {
		//自动抓取数据的关键
		this.setAutoFetchData(true);

		this.setWidth100();
		this.setMargin(5);
		this.setShowFilterEditor(true);
		this.setCellHeight(22);

		//启动可渲染
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);

		ListGridField field1 = new ListGridField("field1", "项号");

		ListGridField field2 = new ListGridField("field2", "件号");

		ListGridField field3 = new ListGridField("field3", "数量");
		Utils.formatQuantityField(field3);//格式化数量

		ListGridField field4 = new ListGridField("field4", "单价");
		Utils.formatPriceField(field4);//格式化单价
		field4.setAlign(Alignment.RIGHT);
		

		ListGridField field5 = new ListGridField("field5", "总价");
		Utils.formatPriceField(field5);//格式化总价
		field5.setAlign(Alignment.RIGHT);
		


		this.setFields(field1, field2, field3, field4, field5);

	

		//绑定假数据
		this.setDataSource(Utils.getXmlDataSource());
	}

}
