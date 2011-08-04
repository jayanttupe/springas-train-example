package com.skynet.spms.client.gui.customerService.salesService.salesContract.update;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 销售合同基本信息
 * 
 * @author fl
 * 
 */
public class ContractBaseUpdateForm extends VLayout {
	private I18n ui = new I18n();
	public static LayoutDynamicForm form = new LayoutDynamicForm();
	private SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();
	private BaseListGrid itemGrid;

	public ContractBaseUpdateForm(BaseListGrid itemGrid) {
		form.setNumCols(6);
		form.setSaveOperationType(DSOperationType.UPDATE);
		this.itemGrid = itemGrid;
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACT_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						form.editSelectedData(model.saleGrid);
						buildFormFields();
						build();
						String codeId = model.saleGrid.getSelectedRecord()
								.getAttribute("customerIdenty.id");
						// 初始化客户联系人/联系方式/GTA 相关信息
						CodeRPCTool.bindCustomerData(codeId, linkManItem,
								linkWayItem, enterpriseGTAItem);
						Boolean res = buyerFreightAgentItem.getValueAsBoolean();
						changeFreightAgentState(res);
					}
				});

	}

	/** 合同编号 */
	private TextItem item_no;
	/** 订单id */
	private TextItem item_sheetId;
	/** 订单编号 */
	private TextItem item_sheetNum;
	/** 优先级 */
	private SelectItem item_m_Priority;
	/** 客户id */
	private TextItem customerIdentyIdItem;
	/** 客户Code */
	private TextItem customerIdentyCodeItem;
	/** 联系人 */
	private TextItem linkManItem;
	/** 飞机机尾号 */
	private SelectItem aircraftNumber;
	/** 联系方式 */
	private TextItem linkWayItem;
	/** 交货方式 */
	private SelectItem deliveryTypeItem;
	/** 是否买方指定运代复选框 */
	private CheckboxItem buyerFreightAgentItem;
	/** 运输方式下拉列表 */
	private SelectItem transportationCodeItem;
	/** 运代商输入框 */
	public SelectItem carrierNameItem;
	/** 运代商联系方式输入框 */
	private TextItem carrierLinkWayItem;
	/** 贸易方式 */
	private SelectItem tradeMethodItem;
	/** 运代商联系人输入框 */
	private TextItem carrierLinkManItem;
	/** GTA协议下拉列表 */
	private SelectItem enterpriseGTAItem;
	/** 支付方式下拉列表 */
	private SelectItem paymentItem;
	/** 支付说明输入框 **/
	private TextItem paymentExplainItem;
	/** 特殊条款框输入域 **/
	private TextAreaItem extraProvisionItem;
	/** 备注输入框 **/
	private TextAreaItem remarkTextItem;
	/** 制定人输入框 **/
	private TextItem makeByItem;
	/** 金额总计输入框 **/
	private TextItem totalPrice;
	/** 项数总计 */
	private TextItem totalCount;

	/** 按钮组面板 **/
	public HLayout btnGroup;
	/** 保存按钮 **/
	public IButton btnSave;
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
		 * 初始化金额总计
		 */
		totalPrice = new TextItem("extendedValueTotalAmount");
		totalPrice.setDisabled(true);

		totalCount = new TextItem();
		totalCount.setName("totalCount");
		totalCount.setDisabled(true);
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
		tradeMethodItem = new SelectItem("m_TradeMethods");
		/**
		 * 初始化运行商联系人输入框
		 */
		carrierLinkManItem = new TextItem("appointForwarderLinkman");
		/**
		 * 初始化运行商联系方式输入框
		 */
		carrierLinkWayItem = new TextItem("appointForwarderContact");
		/**
		 * 初始化运代商输入框
		 */
		carrierNameItem = new SelectItem();
		carrierNameItem.setName("carrierName.id");
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE, carrierNameItem);
		carrierNameItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String value = carrierNameItem.getValue().toString();
				CodeRPCTool.bindCarrierData(value, carrierLinkManItem,
						carrierLinkWayItem, enterpriseGTAItem);
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
		buyerFreightAgentItem = new CheckboxItem();
		buyerFreightAgentItem.setName("buyerFreightAgent");
		buyerFreightAgentItem.setColSpan(3);
		buyerFreightAgentItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				boolean checked = buyerFreightAgentItem.getValueAsBoolean();
				changeFreightAgentState(checked);
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
		 * 初始化飞机机尾号
		 */
		aircraftNumber = new SelectItem("aircraftNumber");
		aircraftNumber.setDisabled(true);
		/**
		 * 初始化客户id
		 */
		customerIdentyIdItem = new TextItem();
		customerIdentyIdItem.setName("customerIdenty.id");
		customerIdentyIdItem.setVisible(false);
		/**
		 * 初始化客户code
		 */
		customerIdentyCodeItem = new TextItem("customerIdenty.code");
		customerIdentyCodeItem.setTitle("客户编号");
		customerIdentyCodeItem.setDisabled(true);
		customerIdentyCodeItem.setRequired(true);
		/**
		 * 初始化订单id
		 */
		item_sheetId = new TextItem();
		item_sheetId.setName("saleSheet.id");
		item_sheetId.setRequired(true);
		item_sheetId.setVisible(false);
		/**
		 * 初始化订单编号
		 */
		item_sheetNum = new TextItem();
		item_sheetNum.setName("saleSheet.requisitionSheetNumber");
		item_sheetNum.setDisabled(true);
		/**
		 * 优先级
		 */
		item_m_Priority = new SelectItem("m_Priority");

		/**
		 * 合同编号输入框
		 */
		item_no = new TextItem();
		item_no.setName("contractNumber");
		item_no.setDisabled(true);
		item_no.setValue("业务编号系统自动生成");

		addItems2Form();

		btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		/**
		 * 初始化保存按钮
		 */
		btnSave = new IButton("保存");
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						model.saleGrid.fetchData(criteria);
					}
				});
			}
		});
		btnClose = new IButton("关闭");
		btnClose.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				model.openedWindow.close();
			}
		});
		btnGroup.addMember(btnSave);
		btnGroup.addMember(btnClose);
		Timer timer = Utils.startAmountTimer(itemGrid, totalCount, totalPrice,
				"price");
		timer.scheduleRepeating(500);
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
		form.setFields(item_no, item_sheetId, item_sheetNum, item_m_Priority,
				customerIdentyIdItem, customerIdentyCodeItem, linkManItem, aircraftNumber, linkWayItem,
				deliveryTypeItem, buyerFreightAgentItem,
				transportationCodeItem, carrierNameItem, carrierLinkWayItem,
				tradeMethodItem, carrierLinkManItem, enterpriseGTAItem,
				paymentItem, paymentExplainItem, extraProvisionItem,
				remarkTextItem, makeByItem, totalCount, totalPrice);
	}
	
	/**
	 * 根据是否选择运代商改变输入框的状态
	 * @param sel
	 */
	private void changeFreightAgentState(boolean sel) {
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
}
