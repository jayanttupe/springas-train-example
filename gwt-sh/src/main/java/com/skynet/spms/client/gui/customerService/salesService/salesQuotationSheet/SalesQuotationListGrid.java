package com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SalesQuotationListGrid extends FilterListGrid {
	private static I18n ui = new I18n();
	
	public void drawGrid() {
		this.setShowRowNumbers(false);
		//留言
		ListGridField field1 = new ListGridField("field1");
		Utils.setMessageFieldWidth(field1);
		field1.setHidden(true);
		// 报价单编号
		ListGridField field2 = new ListGridField("quotationSheetNumber");
		// 询价单编号
		ListGridField field3 = new ListGridField(
				"salesInquirySheet.inquirySheetNumber");
		field3.setLinkText("salesInquirySheet.inquirySheetNumber");

		// 报价日期
		ListGridField field4 = new ListGridField("quotationDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		//有效日期
		ListGridField field5 = new ListGridField("priceEffectiveDate");
		field5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//客户名称
		ListGridField field6 = new ListGridField(
				"m_CustomerIdentificationCode.code",ui.getM().title_CustomerIdentificationCode());
		
		//联系人
		ListGridField field7 = new ListGridField("linkman");

		//联系方式
		ListGridField field8 = new ListGridField("contactInformation");

		//发布状态
		ListGridField field9 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");

		//业务状态
		ListGridField field10 = new ListGridField(
				"m_BussinessStatusEntity.status");

		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		field5.setCanFilter(true);
		field6.setCanFilter(true);
		field7.setCanFilter(true);
		field8.setCanFilter(true);
		field10.setCanFilter(true);

		this.setFields(field1, field2, field3, field4, field5, field6, field7,
				field8, field9, field10);

	}

	/**
	 * 渲染Grid列
	 */
//	@Override
//	protected Canvas createRecordComponent(final ListGridRecord record,
//			Integer colNum) {
//		String fieldName = this.getFieldName(colNum);
//		if (fieldName.equals("salesInquirySheet.inquirySheetNumber")) {
//			String inquirySheetNumber = record
//					.getAttribute("salesInquirySheet.inquirySheetNumber");
//
//			if (!inquirySheetNumber.isEmpty()) {
//				final LayoutDynamicForm form = new LayoutDynamicForm();
//				form.setCellPadding(0);
//				form.setAlign(Alignment.CENTER);
//
//				LinkItem item1 = new LinkItem();
//				item1.setShowTitle(false);
//				item1.setLinkTitle(inquirySheetNumber);
//				item1.addClickHandler(new ClickHandler() {
//					@Override
//					public void onClick(ClickEvent event) {
//						// SalesInquiryViewWindow win = new
//						// SalesInquiryViewWindow(
//						// "询价单详细信息", true, null, null, null);
//						// ShowWindowTools.showWindow(form.getPageRect(), win,
//						// 200);
//					}
//				});
//
//				form.setFields(item1);
//				return form;
//			}
//
//		}
//		return null;
//	}

}
