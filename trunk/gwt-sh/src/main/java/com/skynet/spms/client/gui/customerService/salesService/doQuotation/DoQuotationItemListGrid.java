package com.skynet.spms.client.gui.customerService.salesService.doQuotation;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.view.SalesQuotationViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DoQuotationItemListGrid extends FilterListGrid {

	public DoQuotationItemListGrid() {

	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
//		ListGridField field1 = new ListGridField("itemNumber"/*, "项号"*/);
//		field1.setCanFilter(true);
//		Utils.setMessageFieldWidth(field1);

		//询价件号
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);

		//中文全称
		ListGridField field3 = new ListGridField("description");
		field3.setCanFilter(true);
		field3.setHidden(true);

		//制造商
		ListGridField field4 = new ListGridField("m_ManufacturerCode.code");
		field4.setCanFilter(true);

		//优先级"
		ListGridField field5 = new ListGridField("m_Priority");
		field5.setCanFilter(true);

		//数量
		ListGridField field6 = new ListGridField("quantity");
		field6.setCanFilter(true);
		Utils.formatQuantityField(field6);

		//需求日期
		ListGridField field7 = new ListGridField("partRequireDate");
		field7.setCanFilter(true);
		field7.setType(ListGridFieldType.DATE);
		field7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//备注
		ListGridField field8 = new ListGridField("remark");
		field8.setCanFilter(true);

		//是否报价
		ListGridField field9 = new ListGridField(
				"m_QuotationStatusEntity.m_QuotationStatus");
		field9.setCanFilter(true);

		//报价单号
		ListGridField field10 = new ListGridField("quotationSheetNumber");
		field10.setCanFilter(true);
		field10.setHidden(true);

		this.setFields( field2, field3, field4, field5, field6, field7,
				field8, field9, field10);
		
		
	}

//	@Override
//	protected Canvas createRecordComponent(final ListGridRecord record,
//			Integer colNum) {
//
//		String fieldName = this.getFieldName(colNum);
//		if (fieldName.equals("BJDH")) {
//			final LayoutDynamicForm form = new LayoutDynamicForm();
//			form.setCellPadding(0);
//			form.setAlign(Alignment.CENTER);
//
//			LinkItem item1 = new LinkItem();
//			item1.setShowTitle(false);
//			item1.setLinkTitle(record.getAttribute("field10"));
//			item1.addClickHandler(new ClickHandler() {
//
//				public void onClick(ClickEvent event) {
//					SalesQuotationViewWindow view = new SalesQuotationViewWindow(
//							"报价单详细信息", true, null, null, null);
//					ShowWindowTools.showWindow(form.getPageRect(), view, 200);
//				}
//
//			});
//
//			form.setFields(item1);
//			return form;
//		}
//		return null;
//	}
}
