package com.skynet.spms.client.gui.commonOrder.delivery.ui;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 主订单列表
 * 
 * @author Tony FANG
 * 
 */
public class DeliveryOrderListGrid extends ListGrid {
	
	CommonOrderModuleConst constui=GWT.create(CommonOrderModuleConst.class);
	
	public DeliveryOrderListGrid() {

	}

	public void drawGrid() {
		this.setWidth100();
		this.setMargin(5);
		this.setCellHeight(22);
		this.setShowFilterEditor(true);
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setAutoFetchData(true);
		this.setShowAllRecords(false);

		ListGridField field2 = new ListGridField("orderNumber");
		
		ListGridField field3 = new ListGridField("priority");
		field3.setCellFormatter(new ItemRender(EnumTool.PRIORITY));

		ListGridField field4 = new ListGridField("businessType");
		field4.setCellFormatter(new ItemRender(EnumTool.BUSINESSTYPE));

		ListGridField field5 = new ListGridField("contractNumber");

		ListGridField field6 = new ListGridField("companyOfShipper");

		ListGridField field7 = new ListGridField("companyOfConsignee");

		ListGridField field8 = new ListGridField("shippedDate");
		field8.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field8.setType(ListGridFieldType.DATE);

		ListGridField field9 = new ListGridField("isPublish");
		
		field9.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if ("0".equals(value)) {
					return constui.unpublished();
				} else {
					return constui.published();
				}
			}
		});

		ListGridField field10 = new ListGridField("createDate");
		field10.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field10.setType(ListGridFieldType.DATE);

		ListGridField field11 = new ListGridField("statusName");
		field11.setCellFormatter(new CellFormatter() {

			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				// (1:未处理/2:处理中/3:已处理)
				if ("1".equals(value)) {
					return constui.status1();
				} else if ("2".equals(value)) {
					return constui.status2();
				} else if ("3".equals(value)) {
					return constui.status3();
				} else {
					return constui.status1();
				}
			}
		});

		setFields(field2, field3, field4, field5, field6, field7, field8,
				field9, field10, field11);

	}

}
