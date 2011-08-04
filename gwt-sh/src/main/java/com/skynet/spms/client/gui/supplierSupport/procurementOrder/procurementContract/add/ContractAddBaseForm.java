package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.add;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
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
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.SuppliersParityBoundWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
/**
 * 合同基本信息
 * 
 * @author fl
 * 
 */
public class ContractAddBaseForm extends VLayout {
	private I18n ui = new I18n();
	private static final String uploadUrl = "spms/upload.do";
	public static LayoutDynamicForm form = new LayoutDynamicForm();
	private ProcurementContractModelLocator model = ProcurementContractModelLocator
			.getInstance();
	private ProcurementOrderModelLocator procurementOrderModelLocator = ProcurementOrderModelLocator
			.getInstance();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	private BaseListGrid itemGrid;

	public ContractAddBaseForm(BaseListGrid itemGrid) {
		form.setNumCols(6);
		this.itemGrid = itemGrid;
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						buildFormFields();
						build();
					}
				});
	}

	/** 合同编号输入框 */
	public TextItem contractNumberItem;
	/** 采购指令输入框 */
	public TextItem item_code;
	/** 采购指令主键输入框 */
	private TextItem item_orderUUid;
	/** 优先级下拉列表 */
	public SelectItem priorityItem;
	/** 供应商下拉列表 */
	public SelectItem supplierItem;
	/** 联系人 */
	public TextItem sellerContactManItem;
	/** 飞机尾号 */
	public SelectItem aircraftIdentificationNumberItem;
	/** 联系方式 */
	public TextItem linkWayItem;
	/** 交货方式 */
	public SelectItem deliveryTypeItem;
	/** 是否由客户指定运代 复选框 */
	public CheckboxItem isSellerFreightAgentItem;
	/** 运输方式下拉列表 */
	public SelectItem transportationCodeItem;
	/** 运代商输入框 */
	public SelectItem m_CarrierNameItem;
	/** 运代商联系人输入框 */
	public TextItem appointForwarderLinkmanItem;
	/** 贸易方式下拉列表 */
	public SelectItem tradeMethodsItem;
	/** 运代商联系方式输入框 */
	public TextItem appointForwarderContactItem;
	/** GTA协议 **/
	public SelectItem gtaItem;
	/** 支付方式 **/
	public SelectItem paymentItem;
	/** 支付说明 **/
	public TextItem zhiFuShuoMingItem;
	/** 特殊条款框输入域 **/
	public TextAreaItem extraProvisionItem;
	/** 备注输入框 **/
	public TextAreaItem remarkItem;
	/** 制定人输入框 **/
	public TextItem makeByItem;
	/** 金额总计 **/
	public TextItem totalPriceUnitItem;
	/**
	 * 项数总计
	 */
	public TextItem totalCount;
	/** 按钮组面板 **/
	public HLayout btnGroup;
	/** 保存按钮 **/
	public IButton btnSave;
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
		 * 初始化 合同编号
		 */
		contractNumberItem = new TextItem("contractNumber");
		contractNumberItem.setDisabled(true);
		contractNumberItem.setValue("业务编号系统自动生成");
		/**
		 * 初始化 采购指令号
		 */
		item_code = new TextItem("procurementPlanNumber");
		item_code.setDisabled(true);
		item_code.setValue(model.orderNumber);
		/**
		 * 初始化 采购指令主键
		 */
		item_orderUUid = new TextItem();
		item_orderUUid.setName("procurementPlanUUid");
		item_orderUUid.setValue(model.procurementPlanUUid);
		item_orderUUid.setVisible(false);
		/**
		 * 初始化 供应商下拉列表
		 */
		supplierItem = new SelectItem();
		supplierItem.setName("seller.id");
		CodeRPCTool.bindData(CodeRPCTool.SUPPLIERCODE, supplierItem);
		supplierItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String id = supplierItem.getValue().toString();
				CodeRPCTool.bindSuppliercodeData(id, sellerContactManItem,
						linkWayItem, gtaItem);
				addressModel.businessType = OrganizationType.SUPPLIER;
				addressModel.enterpriseId = supplierItem.getValueAsString();
			}
		});
		/**
		 * 初始化 联系人
		 * */
		sellerContactManItem = new TextItem("sellerContactMan");
		/**
		 * 初始化 飞机尾号
		 * */
		aircraftIdentificationNumberItem = new SelectItem();
		aircraftIdentificationNumberItem
				.setName("m_AircraftIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(aircraftIdentificationNumberItem);
		/**
		 * 初始化 联系方式
		 * */
		linkWayItem = new TextItem("m_CarrierContact");
		linkWayItem.setColSpan(3);
		/**
		 * 初始化 交货方式
		 * */
		deliveryTypeItem = new SelectItem();
		deliveryTypeItem.setName("deliveryType");
		/**
		 * 初始化 优先级下拉列表
		 */
		priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");
		priorityItem.setValue(model.selectedRecord.getAttribute("m_Priority"));
		/**
		 * 初始化 是否由客户指定运代 复选框
		 * */
		isSellerFreightAgentItem = new CheckboxItem();
		isSellerFreightAgentItem.setName("sellerFreightAgent");
		isSellerFreightAgentItem.setColSpan(3);
		isSellerFreightAgentItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				boolean checked = isSellerFreightAgentItem.getValueAsBoolean();
				boolean state = false;
				if (checked) {
					state = false;
				} else {
					state = true;
				}
				m_CarrierNameItem.setDisabled(state);
				appointForwarderLinkmanItem.setDisabled(state);
				appointForwarderContactItem.setDisabled(state);
			}
		});

		/**
		 * 初始化 运输方式下拉列表
		 * */
		transportationCodeItem = new SelectItem();
		transportationCodeItem.setName("m_TransportationCode");
		/**
		 * 初始化 运代商输入框
		 * */
		m_CarrierNameItem = new SelectItem();
		m_CarrierNameItem.setName("m_CarrierName.id");
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE, m_CarrierNameItem);
		m_CarrierNameItem.setDisabled(true);
		m_CarrierNameItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String id = m_CarrierNameItem.getValueAsString();
				CodeRPCTool.bindCarrierData(id, appointForwarderContactItem,
						appointForwarderLinkmanItem);
			}
		});
		/**
		 * 初始化 运代商联系人输入框
		 * */
		appointForwarderLinkmanItem = new TextItem("appointForwarderLinkman");
		appointForwarderLinkmanItem.setDisabled(true);
		/**
		 * 初始化 贸易方式下拉列表
		 * */
		tradeMethodsItem = new SelectItem();
		tradeMethodsItem.setName("m_TradeMethods");
		/**
		 * 初始化 运代商联系方式输入框
		 * */
		appointForwarderContactItem = new TextItem("appointForwarderContact");
		appointForwarderContactItem.setColSpan(3);
		appointForwarderContactItem.setDisabled(true);
		/**
		 * 初始化 初始化GTA协议下拉列表
		 */
		gtaItem = new SelectItem();
		gtaItem.setMultiple(true);
		gtaItem.setName("gta");
		/**
		 * 初始化 支付方式
		 * **/
		paymentItem = new SelectItem();
		paymentItem.setName("m_Payment");
		/**
		 * 初始化 支付说明
		 * **/
		zhiFuShuoMingItem = new TextItem("paymentSM");
		zhiFuShuoMingItem.setColSpan(3);
		/**
		 * 初始化 特殊条件框输入域
		 */
		extraProvisionItem = new TextAreaItem();
		extraProvisionItem.setName("extraProvision");
		extraProvisionItem.setColSpan(3);
		/**
		 * 初始化 备注输入框
		 */
		remarkItem = new TextAreaItem();
		remarkItem.setName("remarkText");
		/**
		 * 初始化 制定人输入框
		 */
		makeByItem = new TextItem();
		makeByItem.setName("makeBy");
		makeByItem.setValue(UserTools.getCurrentUserName());
		makeByItem.setDisabled(true);
		/**
		 * 初始化 初始化金额总计
		 */
		totalPriceUnitItem = new TextItem("extendedValueTotalAmount");
		totalPriceUnitItem.setValue(0);
		totalPriceUnitItem.setDisabled(true);

		/**
		 * 项数总计
		 */
		totalCount = new TextItem();
		totalCount.setName("totalCount");
		totalCount.setDisabled(true);

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
						// 将合同主键放入共享数据
						model.contractID = response.getData()[0]
								.getAttribute("id");
						addressModel.addr_sheetId = model.contractID;
						SC.say(ui.getB().msgAddOpSuccess());
						model.defaultUploader.setVisible(true);
						model.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + model.contractID);

						Criteria criteria = new Criteria();
						procurementOrderModelLocator.procurementOrderListGrid
								.fetchData(criteria);
						
						//CodeRPCTool.updateBussinessStateByAdd("ProcurementOrder", item_orderUUid.getValueAsString());
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
		Timer timer = Utils.startAmountTimer(itemGrid, totalCount,
				totalPriceUnitItem, "amount");
		timer.scheduleRepeating(500);
	}

	/**
	 * 将表单元素添加到表单中
	 */
	public void addItems2Form() {
	
		form.setFields(contractNumberItem, item_code, item_orderUUid,
				priorityItem, supplierItem, sellerContactManItem,
				aircraftIdentificationNumberItem, linkWayItem,
				deliveryTypeItem, isSellerFreightAgentItem,
				transportationCodeItem, m_CarrierNameItem,
				appointForwarderLinkmanItem, tradeMethodsItem,
				appointForwarderContactItem, gtaItem, paymentItem,
				zhiFuShuoMingItem, extraProvisionItem, remarkItem, makeByItem,
				totalCount, totalPriceUnitItem);
	}

}
