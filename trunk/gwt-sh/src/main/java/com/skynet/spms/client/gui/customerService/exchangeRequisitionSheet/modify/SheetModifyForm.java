package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.modify;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.ExchangeRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.SheetModelLocator;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
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
public class SheetModifyForm extends VLayout {

	private DynamicForm sheetForm = new DynamicForm();

	private I18n ui = new I18n();

	private SheetModelLocator model = SheetModelLocator.getInstance();

	private ExchangeRequisitionSheetBusiness business= new ExchangeRequisitionSheetBusiness();
	
	public SheetModifyForm() {
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
		requisitionSheetNumberItem.setDisabled(true);

		// 机尾号
		final SelectItem airIdentificationNumberItem = new SelectItem();
		airIdentificationNumberItem.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(airIdentificationNumberItem);

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

		// 客户
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
		contactInformationItem.setName("contactWay");
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
		remarkTextItem.setName("remark");
		remarkTextItem.setColSpan(3);
		remarkTextItem.setWidth("100%");

		/**
		 * 基本备件信息
		 */
		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		partNumberItem.setName("exchangeRequisitionSheetItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);
		partNumberItem.setWidth("100%");

		// 序号/批次号
		TextItem batchNumberItem = new TextItem();
		batchNumberItem.setName("exchangeRequisitionSheetItem.batchNumber");
		batchNumberItem.setRequired(true);

		// 原始合同编号
		// TextItem oldContractNumberItem = new TextItem();
		// oldContractNumberItem.setName("exchangeRequisitionSheetItem.oldContractNumber");
		// oldContractNumberItem.setRequired(true);

		// 备注
		TextAreaItem remarkItemTextItem = new TextAreaItem();
		remarkItemTextItem.setName("exchangeRequisitionSheetItem.remarkText");
		remarkItemTextItem.setColSpan(3);
		remarkItemTextItem.setWidth("100%");

		sheetForm.setFields(requisitionSheetNumberItem, 
				m_CustomerIdentificationCodeItem, linkMantItem,
				contactInformationItem, 
				priorityItem,airIdentificationNumberItem,
				remarkTextItem, partNumberItem,
				batchNumberItem,remarkItemTextItem);

		this.addMember(sheetForm);

		BtnsHLayout btnGroup = new BtnsHLayout();
		btnGroup.setLayoutLeftMargin(15);

		IButton btnSave = new IButton(ui.getB().btnSaveModify());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// 保存
				sheetForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功");
						business.refeshRQGrird();
					}
				});
			}
		});
		
		//关闭
		IButton btnClose= new IButton(ui.getB().btnClose());
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				business.closeWindow();
			}
		});

		btnGroup.setMembers(btnSave,btnClose);
		this.addMember(btnGroup);
	}

}
