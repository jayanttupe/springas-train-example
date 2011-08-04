package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.update;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 寄售协议基础信息
 * 
 * @author fl
 * 
 */
public class ConsignProtocolBaseUpdateForm extends VLayout {
	private I18n ui = new I18n();
	public static LayoutDynamicForm form = new LayoutDynamicForm();
	private ConsignProtocolModel model = ConsignProtocolModel.getInstance();
	private BaseListGrid itemGrid;
	public ConsignProtocolBaseUpdateForm(BaseListGrid itemGrid) {
		form.setNumCols(6);
		this.itemGrid=itemGrid;
		DataSourceTool dataSourceTool = new DataSourceTool();
		// 主数据源
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOL_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						form.editSelectedData(model.proMainGrid);
						buildFormFields();
						build();
					}
				});

	}

	public TextItem item_id;
	/** 编号输入框 */
	public TextItem item_no;
	/** 开始日期 **/
	public DateItem startTime;
	/** 结束日期 */
	public DateItem endTime;
	/** 供应商 */
	public SelectItem provider;
	/** 联系人 */
	public TextItem linkManItem;
	/** 联系方式 */
	public TextItem linkWayItem;
	/** GTA协议下拉列表 */
	public SelectItem enterpriseGTAItem;
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
		item_id = new TextItem();
		item_id.setName("id");
		item_id.setVisible(false);
		/**
		 * 初始化数量总计
		 */
		totalCount = new TextItem("totalCount"/* , "项数总计" */);
		/**
		 * 初始化金额总计
		 */
		totalPrice = new TextItem("totalAmount");
		totalPrice.setDisabled(true);
		/**
		 * 初始化制定人输入框
		 */
		makeByItem = new TextItem();
		makeByItem.setName("makeBy");
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
//		/**
//		 * 初始化支付说明输入框
//		 */
//		paymentExplainItem = new TextItem("paymentExplain"/* , "支付说明" */);
//		paymentExplainItem.setColSpan(3);
//		/**
//		 * 初始化支付方式下拉列表
//		 */
//		paymentItem = new SelectItem();
//		paymentItem.setName("m_Payment");
		/**
		 * 初始化GTA协议下拉列表
		 */
		enterpriseGTAItem = new SelectItem("enterpriseIds"/* , "GTA协议" */);
		enterpriseGTAItem.setMultiple(true);
		/**
		 * 初始化联系方式输入框
		 */
		linkWayItem = new TextItem("contactWay"/* , "联系方式" */);
		linkWayItem.setColSpan(3);
		/**
		 * 初始化联系人输入框
		 */
		linkManItem = new TextItem();
		linkManItem.setName("linkman");
		linkManItem.setRequired(true);

		/**
		 * 初始化业务编号输入框
		 */
		item_no = new TextItem();
		item_no.setName("contractNumber");
		item_no.setDisabled(true);
		item_no.setValue("业务编号系统自动生成");

		startTime = new DateItem();
		startTime.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		startTime.setUseTextField(true);
		startTime.setName("startDate");

		endTime = new DateItem();
		endTime.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		endTime.setUseTextField(true);
		endTime.setName("endDate");

		provider = new SelectItem("supplier.id"/* , "供应商" */);
		CodeRPCTool.bindData(CodeRPCTool.SUPPLIERCODE, provider);
		provider.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				String id = provider.getValue().toString();
				CodeRPCTool.bindSuppliercodeData(id, linkManItem, linkWayItem,
						enterpriseGTAItem);
			}
		});
		addItems2Form();

		btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		/**
		 * 初始化保存按钮
		 */
		btnSave = new IButton(ui.getM().btnSave());
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						// 将合同主键放入共享数据
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria c = new Criteria();
						model.proMainGrid.fetchData(c);
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
		Timer timer=Utils.startAmountTimer(itemGrid, totalCount, totalPrice, "price");
		timer.scheduleRepeating(500);
	}

	public void addItems2Form() {

		form.setFields(item_no, startTime, endTime, provider, linkManItem,
				enterpriseGTAItem, linkWayItem, makeByItem, extraProvisionItem,
				remarkTextItem, /*paymentItem, paymentExplainItem,*/ totalCount,
				totalPrice, item_id);
	}
}
