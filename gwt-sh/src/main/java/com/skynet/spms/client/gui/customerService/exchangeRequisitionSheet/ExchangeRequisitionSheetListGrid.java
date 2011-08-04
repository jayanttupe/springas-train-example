package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 送修申请单列表
 * 
 * @author tqc
 * 
 */
public class ExchangeRequisitionSheetListGrid extends ListGrid {
	private I18n ui = new I18n();

	public ExchangeRequisitionSheetListGrid() {
		this.setWidth100();
		this.setMargin(5);
		this.setCellHeight(22);
		this.setShowFilterEditor(true);
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setAutoFetchData(true);
	}

	/**
	 * 绘制表格列
	 */
	public void drawGrid() {
		ListGridField requisitionSheetNumberField = new ListGridField(
				"requisitionSheetNumber");
		requisitionSheetNumberField.setCanEdit(false);
		requisitionSheetNumberField.setCanFilter(true);

		ListGridField priorityField = new ListGridField("m_Priority");
		priorityField.setCanEdit(false);
		priorityField.setCanFilter(true);

		ListGridField customerIdentificationCodeField = new ListGridField(
				"m_CustomerIdentificationCode.code");
		customerIdentificationCodeField.setCanEdit(false);
		customerIdentificationCodeField.setCanFilter(true);

		ListGridField requisitionDateField = new ListGridField(
				"requisitionDate");
		requisitionDateField.setType(ListGridFieldType.DATE);
		requisitionDateField
				.setDateFormatter(DateDisplayFormat.TOSERIALIZEABLEDATE);
		requisitionDateField.setCanEdit(false);
		requisitionDateField.setCanFilter(true);

		ListGridField bussinessPublishStatusField = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus", ui.getB()
						.listTitlePublishStatus());
		bussinessPublishStatusField.setCanEdit(false);
		bussinessPublishStatusField.setCanFilter(true);

		ListGridField bussinessStatusField = new ListGridField(
				"m_BussinessStatusEntity.status", ui.getB()
						.listTitleBusStatus());
		bussinessStatusField.setCanEdit(false);
		bussinessStatusField.setCanFilter(true);

		this.setFields(requisitionSheetNumberField, priorityField,
				customerIdentificationCodeField, requisitionDateField,
				bussinessPublishStatusField, bussinessStatusField);

	}

}
