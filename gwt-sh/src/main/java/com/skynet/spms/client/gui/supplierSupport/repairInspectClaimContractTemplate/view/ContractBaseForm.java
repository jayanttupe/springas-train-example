package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
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


	public static DynamicForm form = new DynamicForm();

	private ModelLocator model = ModelLocator.getInstance();

	public ContractBaseForm() {
		
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(
				DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE,
				DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
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
		//contratNumberItem.setTitle("合同编号");
		contratNumberItem.setName("contractNumber");
		contratNumberItem.setDisabled(true);

		// 优先级
		DicSelectItem priorityItem = new DicSelectItem();
		//priorityItem.setTitle("优先级");
		priorityItem.setName("m_Priority");

		// 飞机机尾号
		SelectItem planeLastNumberItem = new SelectItem();
		planeLastNumberItem.setTitle(new I18n().getRepairModule().aircraftIdentificationNumber());
		planeLastNumberItem.setName("m_AircraftIdentificationNumber");
		planeLastNumberItem.setRequired(true);
		Utils.setAirIdentificationNumberItemDS(planeLastNumberItem);

		// 承修商
		SelectItem buyerItem = new SelectItem();
		//buyerItem.setTitle("承修商");
		buyerItem.setName("m_RepairShopCode.id");
		buyerItem.setRequired(true);
		CodeRPCTool.bindData(CodeRPCTool.REPAIRSHOPCODE, buyerItem);

		// 联系人
		final TextItem linkManItem = new TextItem();
		//linkManItem.setTitle("联系人");
		linkManItem.setName("linkMan");

		// 联系方式
		final TextItem linkWayItem = new TextItem();
		//linkWayItem.setTitle("联系方式");
		linkWayItem.setName("linkWay");
		linkWayItem.setColSpan(3);
		linkWayItem.setWidth("100%");

		// 交货方式
		DicSelectItem deliveryTypeItem = new DicSelectItem();
		//deliveryTypeItem.setTitle("交货方式");
		deliveryTypeItem.setName("m_DeliveryType");

		// 是否买方指定运代
		final CheckboxItem isBuyerFreightAgentItem = new CheckboxItem();
		//isBuyerFreightAgentItem.setTitle("是否承修商指定运代");
		isBuyerFreightAgentItem.setName("buyerFreightAgent");
		isBuyerFreightAgentItem.setColSpan(3);
		isBuyerFreightAgentItem.setValue(false);

		// 运输方式
		DicSelectItem transportationCodeItem = new DicSelectItem();
		//transportationCodeItem.setTitle("运输方式");
		transportationCodeItem.setName("m_TransportationCode");

		// 运代商
		final SelectItem carrierNameItem = new SelectItem();
		//carrierNameItem.setTitle("运代商");
		carrierNameItem.setName("carrierName.id");
		carrierNameItem.setDisabled(true);
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAME, carrierNameItem);

		// 运代商联系人
		final TextItem carrierLinkManItem = new TextItem();
		//carrierLinkManItem.setTitle("运代商联系人");
		carrierLinkManItem.setName("carrierLinkMan");
		carrierLinkManItem.setDisabled(true);

		// 运代商联系方式
		final TextItem carrierLinkWayItem = new TextItem();
		//carrierLinkWayItem.setTitle("运代商联系方式");
		carrierLinkWayItem.setName("carrierLinkWay");
		carrierLinkWayItem.setDisabled(true);


		// 贸易方式
		DicSelectItem tradeMethodItem = new DicSelectItem();
		//tradeMethodItem.setTitle("贸易方式");
		tradeMethodItem.setName("m_TradeMethods");

		// GTA协议
		final SelectItem enterpriseGTAItem = new SelectItem();
		//enterpriseGTAItem.setTitle("GTA协议");
		enterpriseGTAItem.setName("enterpriseIds");
		enterpriseGTAItem.setMultiple(true);
		enterpriseGTAItem.setColSpan(3);
		enterpriseGTAItem.setWidth("100%");

		// 支付方式
		DicSelectItem paymentItem = new DicSelectItem();
		//paymentItem.setTitle("支付方式");
		paymentItem.setName("m_Payment");

		// 支付说明
		TextItem paymentExplainItem = new TextItem();
		//paymentExplainItem.setTitle("支付说明");
		paymentExplainItem.setName("paymentExplain");
		paymentExplainItem.setColSpan(3);
		paymentExplainItem.setWidth("100%");

		// 特殊条款
		TextAreaItem extraProvisionItem = new TextAreaItem();
		//extraProvisionItem.setTitle("特殊条款");
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		extraProvisionItem.setWidth("100%");

		// 备注
		TextAreaItem remarkTextItem = new TextAreaItem();
		//remarkTextItem.setTitle("备注");
		remarkTextItem.setName("remarkText");

		// 制定人
		TextItem makeByItem = new TextItem();
		//makeByItem.setTitle("制定人");
		makeByItem.setName("makeBy");
		makeByItem.setDisabled(true);

		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		//partNumberItem.setTitle("件号");
		partNumberItem.setStartRow(true);
		partNumberItem.setName("m_RepairContractItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);
		FormItemIcon btnPartItem = new FormItemIcon();
		partNumberItem.setIcons(btnPartItem);

		// 序列号
		TextItem batchNumberItem = new TextItem();
		//batchNumberItem.setTitle("序列号");
		batchNumberItem.setName("m_RepairContractItem.batchNumber");

		// 海关参考价
		SpinnerItem customsReferencePriceItem = new SpinnerItem();
		//customsReferencePriceItem.setTitle("海关参考价");
		customsReferencePriceItem.setStartRow(true);
		customsReferencePriceItem
				.setName("m_RepairContractItem.customsReferencePrice");

		// 币种
		SelectItem currencyItem = new SelectItem();
		//currencyItem.setTitle("币种");
		currencyItem.setName("m_RepairContractItem.currency");

		// 备注
		TextAreaItem remarkTextItemItem = new TextAreaItem();
		//remarkTextItemItem.setTitle("备注");
		remarkTextItemItem.setName("m_RepairContractItem.remarkText");
		remarkTextItemItem.setColSpan(3);
		remarkTextItemItem.setWidth("100%");

		form.setFields(contratNumberItem, priorityItem, buyerItem, linkManItem,
				planeLastNumberItem, linkWayItem, deliveryTypeItem,
				isBuyerFreightAgentItem, transportationCodeItem,
				carrierNameItem, carrierLinkWayItem, carrierLinkManItem,
				tradeMethodItem, enterpriseGTAItem, paymentItem,
				paymentExplainItem, extraProvisionItem, remarkTextItem,
				makeByItem, partNumberItem, batchNumberItem,
				customsReferencePriceItem, currencyItem, remarkTextItemItem);
		Utils.setReadOnlyForm(form);
		
		addMember(form);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(5);
		btnGroup.setLayoutTopMargin(10);
		btnGroup.setLayoutLeftMargin(10);


		addMember(btnGroup);

	}

}
