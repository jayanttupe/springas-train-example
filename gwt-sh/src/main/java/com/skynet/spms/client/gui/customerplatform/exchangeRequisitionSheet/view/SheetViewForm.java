package com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.view;

import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.ExchangeRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.SheetModelLocator;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
/**
 * 申请单信息
 * 
 * @author tqc
 * 
 */
public class SheetViewForm extends VLayout {

	private DynamicForm sheetForm = new DynamicForm();

	private I18n ui = new I18n();

	private SheetModelLocator model = SheetModelLocator.getInstance();

	ExchangeRequisitionSheetBusiness bus = new ExchangeRequisitionSheetBusiness();

	public SheetViewForm() {
		build();
	}

	private void build() {
		sheetForm.setNumCols(4);
		sheetForm.setDataSource(model.dataSource);
		sheetForm.editSelectedData(model.repairRequisitionListGrid);

		// 编号
		TextItem requisitionSheetNumberItem = new TextItem();
		requisitionSheetNumberItem.setName("requisitionSheetNumber");
		requisitionSheetNumberItem.setColSpan(3);
		requisitionSheetNumberItem.setAttribute("readOnly", true);
		
		// 机尾号
		final SelectItem airIdentificationNumberItem = new SelectItem();
		airIdentificationNumberItem.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(airIdentificationNumberItem);
		airIdentificationNumberItem.setAttribute("readOnly", true);
		
		// 优先级
		final SelectItem priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");
		priorityItem.setRequired(true);
		priorityItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (priorityItem.getValue().equals("AOG")) {
					airIdentificationNumberItem.setRequired(true);
				} else {
					airIdentificationNumberItem.setRequired(false);
				}
			}
		});
		priorityItem.setAttribute("readOnly", true);

		// 客户
		final SelectItem m_CustomerIdentificationCodeItem = new SelectItem();
		m_CustomerIdentificationCodeItem
				.setName("m_CustomerIdentificationCode.id");
		m_CustomerIdentificationCodeItem.setRequired(true);
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				m_CustomerIdentificationCodeItem);
		m_CustomerIdentificationCodeItem.setAttribute("readOnly", true);

		final TextItem linkMantItem = new TextItem();
		linkMantItem.setName("linkman");
		linkMantItem.setRequired(true);
		linkMantItem.setAttribute("readOnly", true);
		
		

		final TextAreaItem contactInformationItem = new TextAreaItem();
		contactInformationItem.setName("contactWay");
		contactInformationItem.setColSpan(3);
		contactInformationItem.setWidth("100%");
		contactInformationItem.setRequired(true);
		contactInformationItem.setAttribute("readOnly", true);

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
		remarkTextItem.setName("remark");
		remarkTextItem.setColSpan(3);
		remarkTextItem.setWidth("100%");
		remarkTextItem.setAttribute("readOnly", true);

		/**
		 * 基本备件信息
		 */
		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		partNumberItem.setName("exchangeRequisitionSheetItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);
		partNumberItem.setWidth("100%");
		partNumberItem.setAttribute("readOnly", true);

		// 序号/批次号
		TextItem batchNumberItem = new TextItem();
		batchNumberItem.setName("exchangeRequisitionSheetItem.batchNumber");
		batchNumberItem.setRequired(true);
		batchNumberItem.setAttribute("readOnly", true);

		// 原始合同编号
		// TextItem oldContractNumberItem = new TextItem();
		// oldContractNumberItem.setName("exchangeRequisitionSheetItem.oldContractNumber");
		// oldContractNumberItem.setRequired(true);

		// 备注
		TextAreaItem remarkItemTextItem = new TextAreaItem();
		remarkItemTextItem.setName("exchangeRequisitionSheetItem.remarkText");
		remarkItemTextItem.setColSpan(3);
		remarkItemTextItem.setWidth("100%");
		remarkItemTextItem.setAttribute("readOnly", true);

		sheetForm.setFields(requisitionSheetNumberItem, 
				m_CustomerIdentificationCodeItem, linkMantItem,
				contactInformationItem, 
				priorityItem,airIdentificationNumberItem,
				remarkTextItem, partNumberItem,
				batchNumberItem,remarkItemTextItem);
		
		this.addMember(sheetForm);

	}	

}
