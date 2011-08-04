package com.skynet.spms.client.gui.supplierSupport.procurementOrder.pickUpOrder;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.skynet.spms.client.gui.base.Utils;

/**
 * 订单明细列表
 * @author Tony FANG
 *
 */
public class PickUpOrderItemListGrid extends ListGrid {

	public PickUpOrderItemListGrid() {

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
		field4.setAlign(Alignment.RIGHT);
		Utils.formatPriceField(field4);//格式化单价
		
		ListGridField field5 = new ListGridField("field5", "总价");
		field5.setAlign(Alignment.RIGHT);
		Utils.formatPriceField(field5);//格式总价

		ListGridField field6 = new ListGridField("field6", "备注");

		this.setFields(field1, field2, field3, field4, field5,field6);

	

		//绑定假数据
		this.setDataSource(Utils.getXmlDataSource());
	}

}
