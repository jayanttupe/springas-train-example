package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.view;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author fl
 * 
 */
public class ContractBaseViewForm extends VLayout {
	private I18n ui = new I18n();
	public static LayoutDynamicForm form = new LayoutDynamicForm();
	private BuybackPactModelLocator pactMode = BuybackPactModelLocator
			.getInstance();
	private BaseListGrid itemGrid;

	public ContractBaseViewForm(BaseListGrid itemGrid) {
		form.setNumCols(6);
		form.setSaveOperationType(DSOperationType.UPDATE);
		this.itemGrid = itemGrid;
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT,
				DSKey.C_BUYBACKPACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(pactMode.contractID);
						criteria.setAttribute("id", builder.toString());
						form.fetchData(criteria);
						buildFormFields();
						build();
						String id = customerIdentificationCodeItem
								.getValueAsString();
						CodeRPCTool.bindCustomerData(id, linkManItem,
								linkWayItem, enterpriseGTAItem);
						Utils.setReadOnlyForm(form);
					}
				});
	}

	/** 编号输入框 */
	public TextItem item_no;
	/** 申请号输入框 */
	public TextItem item_code;
	/** 申请号主键输入框 */
	public TextItem item_sheetId;
	/** 回购期（天） */
	public SpinnerItem item_days;
	/** 客户下拉列表 */
	public SelectItem customerIdentificationCodeItem;
	/** 联系人 */
	public TextItem linkManItem;
	/** 贸易方式 */
	public SelectItem tradeMethodItem;
	/** 联系方式 */
	public TextItem linkWayItem;
	/** GTA协议下拉列表 */
	public SelectItem enterpriseGTAItem;
	/** 是否买方指定运代复选框 */
	public CheckboxItem isBuyerFreightAgentItem;
	/** 运代商输入框 */
	public SelectItem carrierNameItem;
	/** 运代商联系方式输入框 */
	public TextItem carrierLinkWayItem;
	/** 交货方式 */
	public SelectItem deliveryTypeItem;
	/** 运代商联系人输入框 */
	public TextItem carrierLinkManItem;
	/** 运输方式下拉列表 */
	public SelectItem transportationCodeItem;
	/** 支付方式下拉列表 */
	public SelectItem paymentItem;
	/** 支付说明输入框 **/
	public TextItem paymentExplainItem;
	/** 特殊条款框输入域 **/
	public TextAreaItem extraProvisionItem;
	/** 备注输入框 **/
	public TextAreaItem remarkTextItem;
	/** 制定人输入框 **/
	public TextItem makeByItem;
	/** 数量总计入框 **/
	public TextItem totalCount;
	/** 金额总计输入框 **/
	public TextItem totalPrice;
	/** 金额总计单位下拉列表（币种） **/
	public SelectItem totalPriceUnit;
	/** 按钮组面板 **/
	public HLayout btnGroup;
	/** 保存按钮 **/
	public IButton btnClose;

	public TextItem item_id;

	/**
	 * 将表单和按钮组添加进来
	 */
	public void build() {
		this.addMember(form);
		this.addMember(btnGroup);
	}

	public void buildFormFields() {
		item_id = new TextItem();
		item_id.setName("id");
		item_id.setVisible(false);
		/**
		 * 初始化数量总计
		 */
		totalCount = new TextItem("totalCount");
		totalCount.setDisabled(true);
		/**
		 * 初始化金额总计
		 */
		totalPrice = new TextItem("extendedValueTotalAmount");
		totalPrice.setDisabled(true);
		/**
		 * 初始化金额总计单位（币种）
		 */
		totalPriceUnit = new SelectItem("m_InternationalCurrencyCode");
		/**
		 * 初始化制定人输入框
		 */
		makeByItem = new TextItem();
		makeByItem.setName("createBy");
		/**
		 * 初始化备注输入框
		 */
		remarkTextItem = new TextAreaItem();
		remarkTextItem.setName("remarkText");
		/**
		 * 初始化特殊条件框输入域
		 */
		extraProvisionItem = new TextAreaItem();
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		/**
		 * 初始化支付说明输入框
		 */
		paymentExplainItem = new TextItem("paymentExplain");
		paymentExplainItem.setColSpan(3);
		/**
		 * 初始化支付方式下拉列表
		 */
		paymentItem = new SelectItem();
		paymentItem.setName("m_Payment");
		/**
		 * 初始化GTA协议下拉列表
		 */
		enterpriseGTAItem = new SelectItem("enterpriseIds");
		enterpriseGTAItem.setMultiple(true);
		/**
		 * 初始化贸易方式下拉列表
		 */
		tradeMethodItem = new SelectItem("m_TradeMethods"/* , "贸易方式" */);
		/**
		 * 初始化运代商联系人输入框
		 */
		carrierLinkManItem = new TextItem("appointForwarderLinkman"/* , "联系人" */);
		carrierLinkManItem.setDisabled(true);
		/**
		 * 初始化运代商联系方式输入框
		 */
		carrierLinkWayItem = new TextItem("appointForwarderContact"/* , "联系方式" */);
		carrierLinkWayItem.setDisabled(true);
		/**
		 * 初始化运代商输入框
		 */
		carrierNameItem = new SelectItem();
		carrierNameItem.setName("carrierName.id");
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE, carrierNameItem);
		carrierNameItem.setDisabled(true);
		carrierNameItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String id = carrierNameItem.getValueAsString();
				CodeRPCTool.bindCarrierData(id, carrierLinkWayItem,
						carrierLinkManItem);
			}
		});
		/**
		 * 初始化运输方式下拉列表
		 */
		transportationCodeItem = new SelectItem();
		transportationCodeItem.setName("m_TransportationCode");
		/**
		 * 初始化是否买方指定运代复选框
		 */
		isBuyerFreightAgentItem = new CheckboxItem();
		isBuyerFreightAgentItem.setName("buyerFreightAgent");
		isBuyerFreightAgentItem.setColSpan(3);
		isBuyerFreightAgentItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				boolean sel = isBuyerFreightAgentItem.getValueAsBoolean();
				boolean enabled = false;
				if (sel) {
					enabled = false;
				} else {
					enabled = true;
				}
				carrierLinkManItem.setDisabled(enabled);
				carrierLinkWayItem.setDisabled(enabled);
				carrierNameItem.setDisabled(enabled);
			}
		});
		/**
		 * 初始化交货方式下拉列表
		 */
		deliveryTypeItem = new SelectItem();
		deliveryTypeItem.setName("m_DeliveryType");
		/**
		 * 初始化联系方式输入框
		 */
		linkWayItem = new TextItem("linkWay");
		linkWayItem.setColSpan(3);
		/**
		 * 初始化联系人输入框
		 */
		linkManItem = new TextItem();
		linkManItem.setName("linkMan");
		linkManItem.setRequired(true);
		/**
		 * 初始化客户下拉列表
		 */
		customerIdentificationCodeItem = new SelectItem();
		customerIdentificationCodeItem.setName("customerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				customerIdentificationCodeItem);
		customerIdentificationCodeItem.setDisabled(true);

		item_days = new SpinnerItem("deliveryPeriod");

		/**
		 * 初始化回购申请号输入框
		 */
		item_code = new TextItem();
		item_code.setName("sheet.requisitionSheetNumber");
		item_code.setRequired(true);
		item_code.setDisabled(true);
		item_sheetId = new TextItem();
		item_sheetId.setName("sheet.id");
		item_sheetId.setVisible(false);
		/**
		 * 初始化业务编号输入框
		 */
		item_no = new TextItem();
		item_no.setName("contractNumber");
		item_no.setDisabled(true);
		item_no.setValue(ui.getM().msgAutoIdInfo());

		addItems2Form();

		btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		btnClose = new IButton(ui.getM().btnSave());
		btnClose.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pactMode.openedWindow.close();
			}
		});
		btnGroup.addMember(btnClose);

		Timer timer = Utils.startAmountTimer(itemGrid, totalCount, totalPrice,
				"price");
		timer.scheduleRepeating(1000);
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
		form.setFields(item_no, item_code, item_sheetId,
				customerIdentificationCodeItem, linkManItem, tradeMethodItem,
				linkWayItem, enterpriseGTAItem, isBuyerFreightAgentItem,
				carrierNameItem, carrierLinkWayItem, deliveryTypeItem,
				carrierLinkManItem, transportationCodeItem, paymentItem,
				paymentExplainItem, extraProvisionItem, remarkTextItem,
				totalCount, makeByItem, totalPrice, totalPriceUnit, item_id,
				item_days);
	}

}
