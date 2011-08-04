package com.skynet.spms.client.gui.customerService.repairService.sheet.view;

import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.repairService.sheet.SheetModelLocator;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 申请单信息
 * 
 * @author tqc
 * 
 */
public class SheetViewForm extends VLayout {

	private DynamicForm sheetForm = new DynamicForm();

	private SheetModelLocator model = SheetModelLocator.getInstance();

	public SheetViewForm() {
		build();
	}

	private void build() {
		sheetForm.setNumCols(4);
		sheetForm
				.setDataSource(model.repairRequisitionListGrid.getDataSource());
		sheetForm.editSelectedData(model.repairRequisitionListGrid);

		TextItem requisitionSheetNumberItem = new TextItem();
		requisitionSheetNumberItem.setName("requisitionSheetNumber");
		requisitionSheetNumberItem.setDisabled(true);

		SelectItem priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");
		priorityItem.setRequired(true);

		final SelectItem m_CustomerIdentificationCodeItem = new SelectItem();
		m_CustomerIdentificationCodeItem
				.setName("m_CustomerIdentificationCode.id");
		m_CustomerIdentificationCodeItem.setRequired(true);
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				m_CustomerIdentificationCodeItem);

		final TextItem linkMantItem = new TextItem();
		linkMantItem.setName("linkman");
		linkMantItem.setRequired(true);

		final TextAreaItem contactInformationItem = new TextAreaItem();
		contactInformationItem.setName("contactInformation");
		contactInformationItem.setColSpan(3);
		contactInformationItem.setWidth("100%");
		contactInformationItem.setRequired(true);

		// 带出客户相关信息
		m_CustomerIdentificationCodeItem
				.addChangedHandler(new ChangedHandler() {
					public void onChanged(ChangedEvent event) {
						CodeRPCTool.bindCustomerData(
								m_CustomerIdentificationCodeItem.getValue()
										+ "", linkMantItem,
								contactInformationItem);
					}
				});

		TextAreaItem remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");
		remarkTextItem.setColSpan(3);
		remarkTextItem.setWidth("100%");

		/**
		 * 基本备件信息
		 */
		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		partNumberItem.setName("repairRequisitionSheetItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);
		partNumberItem.setWidth("100%");

		// 序号/批次号
		TextItem batchNumberItem = new TextItem();
		batchNumberItem.setName("repairRequisitionSheetItem.batchNumber");
		batchNumberItem.setRequired(true);
		
		//原始合同编号
		TextItem oldContractNumberItem = new TextItem();
		oldContractNumberItem.setName("repairRequisitionSheetItem.oldContractNumber");
		oldContractNumberItem.setRequired(true);

		// 备注
		TextAreaItem remarkItemTextItem = new TextAreaItem();
		remarkItemTextItem.setName("repairRequisitionSheetItem.remarkText");
		remarkItemTextItem.setColSpan(3);
		remarkItemTextItem.setWidth("100%");

		sheetForm.setFields(requisitionSheetNumberItem, priorityItem,
				m_CustomerIdentificationCodeItem, linkMantItem,
				contactInformationItem, remarkTextItem, partNumberItem,
				batchNumberItem, oldContractNumberItem,remarkItemTextItem);

		Utils.setReadOnlyForm(sheetForm);
		
		this.addMember(sheetForm);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);

		this.addMember(btnGroup);
	}

}
