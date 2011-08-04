package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.add;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
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
 * 采购合同基本信息
 * 
 * @author fl
 * 
 */
public class ContractBaseAddForm extends VLayout {
	private I18n ui = new I18n();
	private static final String uploadUrl = "spms/upload.do";
	public static LayoutDynamicForm form = new LayoutDynamicForm();
	private BuybackPactModelLocator pactMode = BuybackPactModelLocator.getInstance();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	private SheetModelLocator sheetModelLocator = SheetModelLocator
			.getInstance();

	private BaseListGrid itemGrid;

	public ContractBaseAddForm(BaseListGrid itemGrid) {
		form.setNumCols(6);
		this.itemGrid = itemGrid;
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT,
				DSKey.C_BUYBACKPACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						buildFormFields();
						build();
						String id = customerIdentificationCodeItem
								.getValueAsString();
						CodeRPCTool.bindCustomerData(id, linkManItem,
								linkWayItem, enterpriseGTAItem);
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
	public IButton item_save;
	public IButton btnClose;

	/**
	 * 将表单和按钮组添加进来
	 */
	public void build() {
		this.addMember(form);
		this.addMember(btnGroup);
	}

	public void buildFormFields() {
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
		makeByItem.setValue(UserTools.getCurrentUserName());
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
		paymentExplainItem = new TextItem("paymentExplain" );
		paymentExplainItem.setColSpan(3);
		/**
		 * 初始化支付方式下拉列表
		 */
		paymentItem = new SelectItem();
		paymentItem.setName("m_Payment");
		// paymentItem.setTitle("支付方式");
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
		 * 初始化运代商联系人输入框
		 */
		carrierLinkManItem = new TextItem("appointForwarderLinkman");
		carrierLinkManItem.setDisabled(true);
		/**
		 * 初始化运代商联系方式输入框
		 */
		carrierLinkWayItem = new TextItem("appointForwarderContact");
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
		linkWayItem.setValue(pactMode.linkType);
		/**
		 * 初始化联系人输入框
		 */
		linkManItem = new TextItem();
		linkManItem.setName("linkMan");
		linkManItem.setRequired(true);
		linkManItem.setValue(pactMode.linkMan);
		/**
		 * 初始化客户下拉列表
		 */
		customerIdentificationCodeItem = new SelectItem();
		customerIdentificationCodeItem.setName("customerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
				customerIdentificationCodeItem);
		customerIdentificationCodeItem
				.setValue(pactMode.customerIdentificationCode);
		customerIdentificationCodeItem.setDisabled(true);

		item_days = new SpinnerItem("deliveryPeriod");

		/**
		 * 初始化销售合同输入框
		 */
		item_code = new TextItem();
		item_code.setName("sheet.requisitionSheetNumber");
		item_code.setRequired(true);
		item_code.setDisabled(true);
		item_code.setTextBoxStyle("back");
		item_code.setValue(pactMode.requisitionSheetNumber);
		item_sheetId=new TextItem();
		item_sheetId.setName("sheet.id");
		item_sheetId.setVisible(false);
		item_sheetId.setValue(pactMode.sheetId);
		/**
		 * 初始化业务编号输入框
		 */
		item_no = new TextItem();
		item_no.setName("contractNumber");
		// item_no.setTitle("编号");
		item_no.setDisabled(true);
		item_no.setValue(ui.getM().msgAutoIdInfo());

		addItems2Form();

		btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		/**
		 * 初始化保存按钮
		 */
		item_save = new IButton(ui.getM().btnSave());
		item_save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				item_code.setValue(pactMode.requisitionSheetNumber);
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						// 将合同主键放入共享数据
						pactMode.contractID = response.getData()[0]
								.getAttribute("id");
						addressModel.addr_sheetId = pactMode.contractID;
						SC.say(ui.getB().msgAddOpSuccess());
						pactMode.defaultUploader.setVisible(true);
						pactMode.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + pactMode.contractID);
						Criteria c = new Criteria();
						sheetModelLocator.backSheetGrid.fetchData(c);
						
						CodeRPCTool.updateBussinessStateByAdd("BuybackRequisitionSheet",item_sheetId.getValueAsString());
					}
				});
			}
		});
		/**
		 * 关闭
		 */
		btnClose = new IButton(ui.getM().btnClose());
		btnClose.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pactMode.openedWindow.close();
			}
		});
		btnGroup.addMember(item_save);
		btnGroup.addMember(btnClose);

		Timer timer = Utils.startAmountTimer(itemGrid, totalCount, totalPrice,
				"price");
		timer.scheduleRepeating(1000);
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
		form.setFields(item_no, item_code,item_sheetId, 
				customerIdentificationCodeItem, linkManItem,enterpriseGTAItem, 
				linkWayItem,  isBuyerFreightAgentItem,tradeMethodItem,
				carrierNameItem, carrierLinkWayItem, deliveryTypeItem,
				carrierLinkManItem, transportationCodeItem, paymentItem,
				paymentExplainItem, extraProvisionItem, remarkTextItem,
				totalCount, totalPrice,makeByItem, 
				totalPriceUnit,item_days);
	}

}
