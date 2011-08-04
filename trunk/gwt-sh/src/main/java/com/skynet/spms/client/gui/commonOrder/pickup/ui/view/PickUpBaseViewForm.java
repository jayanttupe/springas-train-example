package com.skynet.spms.client.gui.commonOrder.pickup.ui.view;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.pickup.model.DataModelLocator;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class PickUpBaseViewForm extends VLayout {
	
	CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);

	private TabSet theTabs = new TabSet();

	private Tab baseTab = new Tab();

	private Tab addressTab = new Tab();

	private ValuesManager vm = new ValuesManager();

	private LayoutDynamicForm baseForm = new LayoutDynamicForm();

	private LayoutDynamicForm addressForm = new LayoutDynamicForm();

	public DataModelLocator pickUpModel = DataModelLocator.getInstance();

	private TextItem deliveryIdText;

	private TextItem contractNumberText;

	private TextItem prioritySelect;

	private DateItem deliveryDate;

	private TextItem buinessType;

	private CheckboxItem freightAgentCheckbox;

	private TextItem deliveryTypeSelect;

	private TextItem carrierNameText;

	private TextItem appointForwarderLinkmanText;

	private TextItem tradeMethodsText;

	private TextItem appointForwarderContactText;

	private TextItem shippingServiceTypeText;

	private TextItem descriptionText;

	private TextItem publishUserText;

	private TextItem pickupDeliveryOrderTypeItem;

	private TextItem contractIdItem;

	public PickUpBaseViewForm() {
		theTabs.setWidth100();
		theTabs.setHeight100();
		vm.setDataSource(DataModelLocator.getInstance().modifyOrderGrid
				.getDataSource());
		vm.editSelectedData(DataModelLocator.getInstance().modifyOrderGrid);
		build();
		buildAddressForm();
		theTabs.setTabs(baseTab, addressTab);
		addMember(theTabs);
		//initButtonOpt();
	}

	private void build() {
		baseForm.setNumCols(6);
		baseForm.setCellPadding(2);
		baseTab.setTitle(ui.base_info());
		// 提发货指令类型(1:提货/2:发货)
		pickupDeliveryOrderTypeItem = new TextItem();
		pickupDeliveryOrderTypeItem.setTitle("");
		pickupDeliveryOrderTypeItem.setName("pickupDeliveryOrderType");
		pickupDeliveryOrderTypeItem.setValue("1");
		pickupDeliveryOrderTypeItem.setVisible(false);

		// 指令编号
		deliveryIdText = new TextItem();
		deliveryIdText.setName("orderNumber");
		deliveryIdText.setValue(ui.msgAutoIdInfo());
		deliveryIdText.setDisabled(true);

		// 合同主键
		contractIdItem = new TextItem();
		contractIdItem.setName("contractID");
		pickUpModel.contractIdItem = contractIdItem;
		contractIdItem.setVisible(false);

		// 依据合同号
		contractNumberText = new TextItem();
		contractNumberText.setName("contractNumber");
		contractNumberText.setDisabled(true);
		FormItemIcon fi2 = new FormItemIcon();
		contractNumberText.setIcons(fi2);
		pickUpModel.contractNumberText = contractNumberText;

		// 优先级
		prioritySelect = new TextItem();
		prioritySelect.setName("priority");
		prioritySelect.setDisabled(true);
		pickUpModel.prioritySelect = prioritySelect;

		// 到货日期
		deliveryDate = new DateItem();
		deliveryDate.setName("arrivalDate");
		deliveryDate.setUseTextField(true);
		deliveryDate.setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);

		// 提货日期
		DateItem pickupDate = new DateItem();
		pickupDate.setName("pickupDate");
		pickupDate.setUseTextField(true);
		pickupDate.setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);

		// 业务类型
		buinessType = new TextItem();
		buinessType.setName("businessType");
		buinessType.setValue(DataModelLocator.getInstance().businessType);
		buinessType.setVisible(false);

		// 交货方式
		deliveryTypeSelect = new TextItem();
		deliveryTypeSelect.setName("deliveryType");
		deliveryTypeSelect.setDisabled(true);
		pickUpModel.deliveryTypeSelect = deliveryTypeSelect;

		// 已指定运代
		freightAgentCheckbox = new CheckboxItem();
		freightAgentCheckbox.setName("isAppointFreightAgent");
		pickUpModel.freightAgentCheckbox = freightAgentCheckbox;

		// 运代商
		carrierNameText = new TextItem();
		carrierNameText.setName("appointForwarder");
		pickUpModel.carrierNameText = carrierNameText;

		// 联系人
		appointForwarderLinkmanText = new TextItem();
		appointForwarderLinkmanText.setName("appointForwarderLinkman");
		pickUpModel.appointForwarderLinkmanText = appointForwarderLinkmanText;

		// 联系方式
		appointForwarderContactText = new TextItem();
		appointForwarderContactText.setName("appointForwarderContact");
		appointForwarderContactText.setColSpan(3);
		pickUpModel.appointForwarderContactText = appointForwarderContactText;

		// 贸易方式
		tradeMethodsText = new TextItem();
		tradeMethodsText.setName("tradeMethods");
		tradeMethodsText.setDisabled(true);
		pickUpModel.tradeMethodsText = tradeMethodsText;

		// 运输方式
		shippingServiceTypeText = new TextItem();
		shippingServiceTypeText.setName("transportationCode");
		shippingServiceTypeText.setDisabled(true);
		pickUpModel.shippingServiceTypeText = shippingServiceTypeText;

		// 是否保税
		CheckboxItem isBondItem = new CheckboxItem();
		isBondItem.setName("isBonded");

		// 指令描述
		descriptionText = new TextItem();
		descriptionText.setName("description");
		descriptionText.setRowSpan(3);
		descriptionText.setColSpan(3);
		descriptionText.setHeight("100%");

		// 发布人
		publishUserText = new TextItem();
		publishUserText.setName("createBy");
		publishUserText.setDisabled(true);
		publishUserText.setValue(UserTools.getCurrentUserName());

		baseForm.setFields(pickupDeliveryOrderTypeItem, deliveryIdText,
				contractIdItem, contractNumberText, prioritySelect,
				deliveryDate, pickupDate, buinessType, freightAgentCheckbox,
				deliveryTypeSelect, carrierNameText,
				appointForwarderLinkmanText, tradeMethodsText,
				appointForwarderContactText, shippingServiceTypeText,
				isBondItem, descriptionText, publishUserText);
		
		Utils.setReadOnlyForm(baseForm);

		baseForm.setValuesManager(vm);

		baseTab.setPane(baseForm);

		freightAgentCheckbox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				carrierNameText.setDisabled(!((Boolean) event.getValue()));
				appointForwarderLinkmanText.setDisabled(!((Boolean) event
						.getValue()));
				appointForwarderContactText.setDisabled(!((Boolean) event
						.getValue()));
			}
		});

	}

	private void buildAddressForm() {
		addressForm.setNumCols(6);
		addressForm.setCellPadding(2);

		// 发货单位
		TextItem item1 = new TextItem();
		item1.setName("companyOfShipper");

		// 发货人
		TextItem item2 = new TextItem();
		item2.setName("shipper");

		/*// 唛头
		TextItem item3 = new TextItem();
		item3.setName("shippingMark");
		item3.setTitle("唛头");
		item3.setHeight("100%");
		item3.setRowSpan(6);
		item3.setTitleVAlign(VerticalAlignment.TOP);*/

		// 发货地址
		TextItem item4 = new TextItem();
		item4.setName("addressOfShipper");
		item4.setColSpan(3);
		item4.setTitleVAlign(VerticalAlignment.TOP);

		// 联系方式
		TextAreaItem item5 = new TextAreaItem();
		item5.setName("telephonOfShipper");
		item5.setColSpan(3);
		item5.setHeight(50);

		// 收货单位
		TextItem item6 = new TextItem();
		item6.setName("companyOfConsignee");

		// 收货人
		TextItem item7 = new TextItem();
		item7.setName("consignee");

		// 收货地址
		TextItem item8 = new TextItem();
		item8.setName("addressOfConsignee");
		item8.setColSpan(3);

		// 联系方式
		TextAreaItem item9 = new TextAreaItem();
		item9.setName("telephoneOfConsignee");
		item9.setColSpan(3);
		item9.setHeight(50);

		addressForm.setFields(item1, item2,item4, item5, item6, item7,
				item8, item9);
		
		Utils.setReadOnlyForm(addressForm);

		addressForm.setValuesManager(vm);

		addressTab.setPane(addressForm);
	}
	

}
