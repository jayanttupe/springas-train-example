package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.add;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VerticalAlignment;
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
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 客户租赁合同基本信息
 * 
 * @author tqc
 * 
 */
public class ContractBaseForm extends VLayout {
	private static I18n ui = new I18n();
	private MainModelLocator modellocator = MainModelLocator.getInstance();
	private BaseAddressModel model = BaseAddressModel.getInstance();
	private LeaseRequisitionSheetBusiness business = new LeaseRequisitionSheetBusiness();
	private static final String uploadUrl = "spms/upload.do";
	public static final LayoutDynamicForm form = new LayoutDynamicForm();

	/** 客户联系人 **/
	TextItem linkManItem;
	/** 客户联系方式 **/
	TextItem linkWayItem;
	/** GTA协议 **/
	SelectItem enterpriseGTAItem;
	/** 客户 **/
	SelectItem supplierItem;
	/** 运代商联系人 **/
	TextItem carrierLinkManItem;
	/** 运代商联系方式 **/
	TextItem carrierLinkWayItem;
	/** 金额总计 **/
	TextItem extendedValueTotalAmountItem;

	/** 项数总计 **/
	TextItem quantityItem;

	public ContractBaseForm(final ListGrid lg) {
		this.setAutoHeight();
		form.setNumCols(6);
		form.setCellPadding(2);
		// 初始化合同基本信息数据源数据源, 指定数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT,
				DSKey.C_LEASECONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						form.setDataSource(dataSource);

						TextItem rqid = new TextItem();
						rqid.setName("rqId");
						rqid
								.setValue(modellocator.leaseRequisitionSheetListGrid
										.getSelectedRecord().getAttribute("id"));
						rqid.setVisible(false);

						// 合同编号
						TextItem contratNumberItem = new TextItem();
						contratNumberItem.setName("contractNumber");
						contratNumberItem.setValue(ui.getB().msgAutoIdInfo());
						contratNumberItem.setDisabled(true);

						// 租赁申请号
						TextItem codeItem = new TextItem();
						codeItem.setName("leaseRequisitionNumber");
						codeItem.setDisabled(true);
						codeItem.setTitle("租赁申请号");
						codeItem
								.setValue(modellocator.leaseRequisitionSheetListGrid
										.getSelectedRecord().getAttribute(
												"requisitionSheetNumber"));

						// 优先级
						SelectItem priorityItem = new SelectItem();
						priorityItem.setName("m_Priority");
						priorityItem
								.setValue(modellocator.leaseRequisitionSheetListGrid
										.getSelectedRecord().getAttribute(
												"m_Priority"));
						priorityItem.setDisabled(true);
						// 客户
						supplierItem = new SelectItem();
						supplierItem.setName("buyer.id");
						CodeRPCTool.bindData(
								CodeRPCTool.CUSTOMERIDENTIFICATIONCODE,
								supplierItem);
						supplierItem
								.setValue(modellocator.leaseRequisitionSheetListGrid
										.getSelectedRecord()
										.getAttribute(
												"m_CustomerIdentificationCode.id"));

						// 联系人
						linkManItem = new TextItem();
						linkManItem.setName("linkman");

						// 飞机机尾号
						SelectItem planeLastNumberItem = new SelectItem();
						planeLastNumberItem.setName("aiNumber");
						planeLastNumberItem
								.setValue(modellocator.leaseRequisitionSheetListGrid
										.getSelectedRecord().getAttribute(
												"airIdentificationNumber"));
						planeLastNumberItem.setDisabled(true);

						// 联系方式
						linkWayItem = new TextItem();
						linkWayItem.setName("contactInformation");
						linkWayItem.setColSpan(3);

						// 交货方式
						SelectItem deliveryTypeItem = new SelectItem();
						deliveryTypeItem.setName("m_DeliveryType");

						// 由供应商指定运代
						CheckboxItem isBuyerFreightAgentItem = new CheckboxItem();
						isBuyerFreightAgentItem.setName("buyerFreightAgent");
						isBuyerFreightAgentItem.setColSpan(3);

						// 运输方式
						SelectItem transportationCodeItem = new SelectItem();
						transportationCodeItem.setName("m_TransportationCode");

						// 运代商
						final SelectItem carrierNameItem = new SelectItem();
						carrierNameItem.setName("carrierName.id");
						carrierNameItem.setDisabled(true);
						CodeRPCTool.bindData(CodeRPCTool.CARRIERNAMECODE,
								carrierNameItem);
						carrierNameItem.addChangedHandler(new ChangedHandler() {
							public void onChanged(ChangedEvent event) {
								String id = carrierNameItem.getValueAsString();
								CodeRPCTool.bindCarrierData(id,
										carrierLinkManItem, carrierLinkWayItem);
							}
						});

						// 运代商联系人
						carrierLinkManItem = new TextItem();
						carrierLinkManItem.setName("appointForwarderLinkman");
						carrierLinkManItem.setDisabled(true);

						// 贸易方式
						SelectItem tradeMethodItem = new SelectItem();
						tradeMethodItem.setName("m_TradeMethods");

						// 运代商联系方式
						carrierLinkWayItem = new TextItem();
						carrierLinkWayItem.setName("appointForwarderContact");
						carrierLinkWayItem.setDisabled(true);
						carrierLinkWayItem.setColSpan(3);

						// 判断是否选中复选框
						isBuyerFreightAgentItem
								.addChangedHandler(new ChangedHandler() {
									public void onChanged(ChangedEvent event) {
										carrierNameItem
												.setDisabled(!((Boolean) event
														.getValue()));
										carrierLinkManItem
												.setDisabled(!((Boolean) event
														.getValue()));
										carrierLinkWayItem
												.setDisabled(!((Boolean) event
														.getValue()));
									}
								});

						// GTA协议
						enterpriseGTAItem = new DicSelectItem();
						enterpriseGTAItem.setName("enterpriseIds");
						enterpriseGTAItem.setMultiple(true);

						// 支付方式
						SelectItem paymentItem = new SelectItem();
						paymentItem.setName("m_Payment");

						// 支付说明
						TextItem paymentExplainItem = new TextItem();
						paymentExplainItem.setName("paymentExplain");
						paymentExplainItem.setColSpan(3);

						// 特殊条款
						TextAreaItem extraProvisionItem = new TextAreaItem();
						extraProvisionItem.setName("extraProvision");
						extraProvisionItem.setColSpan(3);
						extraProvisionItem
								.setTitleVAlign(VerticalAlignment.TOP);
						extraProvisionItem.setHeight(50);

						// 备注
						TextAreaItem remarkTextItem = new TextAreaItem();
						remarkTextItem.setName("remarkText");
						remarkTextItem.setTitleVAlign(VerticalAlignment.TOP);
						remarkTextItem.setHeight(50);

						// 数量总计
						quantityItem = new TextItem();
						quantityItem.setName("quantity");
						quantityItem.setDisabled(true);

						// 金额总计
						extendedValueTotalAmountItem = new TextItem();
						extendedValueTotalAmountItem
								.setName("extendedValueTotalAmount");
						extendedValueTotalAmountItem.setDisabled(true);

						// 制定人
						TextItem makeByItem = new TextItem();
						makeByItem.setName("makeBy");
						makeByItem.setDisabled(true);
						makeByItem.setValue(UserTools.getCurrentUserName());

						form.setFields(rqid, contratNumberItem, codeItem,
								priorityItem, supplierItem, linkManItem,
								planeLastNumberItem, linkWayItem,
								deliveryTypeItem, isBuyerFreightAgentItem,
								transportationCodeItem, carrierNameItem,
								carrierLinkManItem, tradeMethodItem,
								carrierLinkWayItem, enterpriseGTAItem,
								paymentItem, paymentExplainItem,
								extraProvisionItem, remarkTextItem, makeByItem,
								quantityItem, extendedValueTotalAmountItem);

						String id = modellocator.leaseRequisitionSheetListGrid
								.getSelectedRecord().getAttribute(
										"m_CustomerIdentificationCode.id");
						CodeRPCTool.bindCustomerData(id, linkManItem,
								linkWayItem, enterpriseGTAItem);

						Timer timer = Utils.startAmountTimer(lg, quantityItem,
								extendedValueTotalAmountItem, "price");
						timer.scheduleRepeating(500);
					}

				});

		this.addMember(form);

		/** 操作按钮 **/
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setMargin(5);
		btnGroup.setLayoutLeftMargin(10);

		IButton btnSave = new IButton(ui.getB().btnSave());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						modellocator.saveBtn.setDisabled(false);
						String leaseContractId = response.getData()[0]
								.getAttribute("id");
						CodeRPCTool
								.updateBussinessStateByAdd(
										"LeaseRequisitionSheet",
										modellocator.leaseRequisitionSheetListGrid
												.getSelectedRecord()
												.getAttribute("id"));
						modellocator.LeaseContractId = leaseContractId;
						model.addr_sheetId = leaseContractId;
						modellocator.defaultUploader.setVisible(true);
						modellocator.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + modellocator.LeaseContractId);
						business
								.refeshListGrid(modellocator.leaseContractListGrid);

					}
				});
			}
		});
		btnGroup.addMember(btnSave);
		this.addMember(btnGroup);
	}

}
