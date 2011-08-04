package com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 送修送检指令列表
 * 
 * @author tqc
 * 
 */
public class CustomerRepairInsOrderListGrid extends ListGrid {
	private I18n ui = new I18n();

	public CustomerRepairInsOrderListGrid() {
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
		// 指令编号
		ListGridField requisitionSheetNumberField = new ListGridField(
				"orderNumber");
		requisitionSheetNumberField.setCanEdit(false);
		requisitionSheetNumberField.setCanFilter(true);

		// 客户名称
		ListGridField customerIdentificationCodeField = new ListGridField(
				"m_CustomerIdentificationCode.code");
		customerIdentificationCodeField.setCanEdit(false);
		customerIdentificationCodeField.setCanFilter(true);

		// 指令下达时间
		ListGridField requisitionDateField = new ListGridField("createDate");
		requisitionDateField.setType(ListGridFieldType.DATE);
		requisitionDateField
				.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		requisitionDateField.setCanEdit(false);
		requisitionDateField.setCanFilter(true);

		// 送修合同编号
		ListGridField contractNumber = new ListGridField("contractNumber");
		contractNumber.setCanEdit(false);
		contractNumber.setCanFilter(true);


		// 指令描述
		ListGridField description = new ListGridField("description");
		description.setCanEdit(false);
		description.setCanFilter(true);

		// 发布状态
		ListGridField bussinessPublishStatusField = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus", ui.getB()
						.listTitlePublishStatus());
		bussinessPublishStatusField.setCanEdit(false);
		bussinessPublishStatusField.setCanFilter(true);

		// 业务状态
		ListGridField bussinessStatusField = new ListGridField(
				"m_BussinessStatusEntity.status", ui.getB()
						.listTitleBusStatus());
		bussinessStatusField.setCanEdit(false);
		bussinessStatusField.setCanFilter(true);

		this.setFields(requisitionSheetNumberField,
				customerIdentificationCodeField, requisitionDateField,
				contractNumber,description,
				bussinessPublishStatusField, bussinessStatusField);

	}

}
