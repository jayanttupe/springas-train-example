package com.skynet.spms.client.gui.customerService.repairService.repairContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class ContractBaseForm extends VLayout {

	private I18n ui = new I18n();

	public ContractModelLocator model = ContractModelLocator.getInstance();

	public static DynamicForm form = new DynamicForm();

	public ContractBaseForm() {
		
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRECONTRACT,
				DSKey.C_REPAIRECONTRACT_FOR_LIST_GRID_DS,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(
							com.smartgwt.client.data.DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("key", "getById");
						StringBuilder stringBuilder=new StringBuilder();
						stringBuilder.append(model.viewDetailContractID);
						criteria.addCriteria("contractID",stringBuilder.toString());
						form.fetchData(criteria);
						build();
					}
				});

	}

	private void build() {
		form.setNumCols(6);
		// 合同编号
		TextItem contratNumberItem = new TextItem();
		contratNumberItem.setName("contractNumber");
		contratNumberItem.setDisabled(true);

		// 优先级
		SelectItem priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");

		// 飞机机尾号
		SelectItem planeLastNumberItem = new SelectItem();
		planeLastNumberItem.setName("airIdentificationNumber");
		planeLastNumberItem.setRequired(true);
		Utils.setAirIdentificationNumberItemDS(planeLastNumberItem);

		// 客户
		final SelectItem buyerItem = new SelectItem();
		buyerItem.setName("buyer.id");
		buyerItem.setRequired(true);
		buyerItem.setTitle(ui.getM().mod_customer_name());
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, buyerItem);

		// 联系人
		final TextItem linkManItem = new TextItem();
		linkManItem.setName("linkMan");

		// 联系方式
		final TextItem linkWayItem = new TextItem();
		linkWayItem.setName("linkWay");
		linkWayItem.setColSpan(3);
		linkWayItem.setWidth("100%");

		// 交货方式
		DicSelectItem deliveryTypeItem = new DicSelectItem();
		deliveryTypeItem.setName("m_DeliveryType");

		// 是否买方指定运代
		final CheckboxItem isBuyerFreightAgentItem = new CheckboxItem();
		isBuyerFreightAgentItem.setName("buyerFreightAgent");
		isBuyerFreightAgentItem.setColSpan(3);
		isBuyerFreightAgentItem.setValue(false);

		// 运输方式
		DicSelectItem transportationCodeItem = new DicSelectItem();
		transportationCodeItem.setName("m_TransportationCode");

		// 运代商
		final SelectItem carrierNameItem = new SelectItem();
		carrierNameItem.setName("carrierName.id");
		carrierNameItem.setTitle(ui.getRepairModule().title_for_shipmanager());
		carrierNameItem.setDisabled(true);
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAME, carrierNameItem);

		// 运代商联系方式
		final TextItem carrierLinkWayItem = new TextItem();
		carrierLinkWayItem.setName("carrierLinkWay");
		carrierLinkWayItem.setDisabled(true);

		// 运代商联系人
		final TextItem carrierLinkManItem = new TextItem();
		carrierLinkManItem.setName("carrierLinkMan");
		carrierLinkManItem.setDisabled(true);

		// 贸易方式
		DicSelectItem tradeMethodItem = new DicSelectItem();
		tradeMethodItem.setName("m_TradeMethods");

		// GTA协议
		final SelectItem enterpriseGTAItem = new SelectItem();
		enterpriseGTAItem.setName("enterpriseIds");
		enterpriseGTAItem.setMultiple(true);
		enterpriseGTAItem.setColSpan(3);
		enterpriseGTAItem.setWidth("100%");

		// 支付方式
		DicSelectItem paymentItem = new DicSelectItem();
		paymentItem.setName("m_Payment");

		// 支付说明
		TextItem paymentExplainItem = new TextItem();
		paymentExplainItem.setName("paymentExplain");
		paymentExplainItem.setColSpan(3);
		paymentExplainItem.setWidth("100%");

		// 特殊条款
		TextAreaItem extraProvisionItem = new TextAreaItem();
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		extraProvisionItem.setWidth("100%");

		// 备注
		TextAreaItem remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");

		// 制定人
		TextItem makeByItem = new TextItem();
		makeByItem.setName("makeBy");
		makeByItem.setDisabled(true);
		makeByItem.setValue(UserTools.getCurrentUserName());

		/**
		 * 合同明细信息
		 */
		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		partNumberItem.setStartRow(true);
		partNumberItem.setName("m_RepairContractItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);

		// 序号/批次号
		TextItem batchNumberItem = new TextItem();
		batchNumberItem.setName("m_RepairContractItem.batchNumber");

		// 原始合同编号
		TextItem oldContractNumberItem = new TextItem();
		oldContractNumberItem.setName("m_RepairContractItem.oldContractNumber");
		oldContractNumberItem.setRequired(true);

		// 备注
		TextAreaItem remarkTextItemItem = new TextAreaItem();
		remarkTextItemItem.setName("m_RepairContractItem.remarkText");
		remarkTextItemItem.setColSpan(3);
		remarkTextItemItem.setWidth("100%");

		// 担保时间/循环代码
		SelectItem cycleReferenceCodeItem = new SelectItem();
		cycleReferenceCodeItem
				.setName("m_RepairContractItem.cycleReferenceCode");

		// 海关参考价
		SpinnerItem customsReferencePriceItem = new SpinnerItem();
		customsReferencePriceItem.setStartRow(true);
		customsReferencePriceItem
				.setName("m_RepairContractItem.customsReferencePrice");
		// 币种
		SelectItem currencyItem = new SelectItem();
		currencyItem.setName("m_RepairContractItem.currency");

		SectionItem contractItemSection = new SectionItem();
		contractItemSection.setTop(5);
		contractItemSection.setDefaultValue(ui.getRepairModule().repair_item_title());
		contractItemSection.setSectionExpanded(true);
		contractItemSection.setItemIds("m_RepairContractItem.partNumber",
				"m_RepairContractItem.batchNumber",
				"m_RepairContractItem.oldContractNumber",
				"m_RepairContractItem.remarkText",
				"m_RepairContractItem.cycleReferenceCode",
				"m_RepairContractItem.customsReferencePrice",
				"m_RepairContractItem.currency");

		form.setFields(contratNumberItem, planeLastNumberItem, priorityItem,
				buyerItem, linkManItem, linkWayItem, deliveryTypeItem,
				isBuyerFreightAgentItem, transportationCodeItem,
				carrierNameItem, carrierLinkWayItem, carrierLinkManItem,
				tradeMethodItem, enterpriseGTAItem, paymentItem,
				paymentExplainItem, extraProvisionItem, remarkTextItem,
				makeByItem, contractItemSection, partNumberItem,
				batchNumberItem, oldContractNumberItem, remarkTextItemItem,
				cycleReferenceCodeItem, customsReferencePriceItem, currencyItem);
		
		Utils.setReadOnlyForm(form);

		addMember(form);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(5);
		btnGroup.setLayoutTopMargin(10);
		btnGroup.setLayoutLeftMargin(10);

		addMember(btnGroup);

	}

}
