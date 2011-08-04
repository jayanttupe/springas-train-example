package com.skynet.spms.client.gui.customerService.repairService.repairContract.add;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.skynet.spms.client.gui.customerService.repairService.sheet.MainModelLocator;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.CarrierVO;
import com.skynet.spms.client.vo.CustomerContact;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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

	private static BaseCodeServiceAsync service = GWT
			.create(BaseCodeService.class);

	public MainModelLocator model = MainModelLocator.getInstance();
	
	public ContractModelLocator contractModel=ContractModelLocator.getInstance();

	public static DynamicForm form = new DynamicForm();
	
	public static AttachmentCanvas attachmentCanvas=new AttachmentCanvas();

	public ContractBaseForm() {
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRECONTRACT,
				DSKey.C_REPAIRECONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						build();
					}
				});

	}

	private void build() {
		form.setNumCols(6);
		final Record rqRecord = model.repairRequisitionListGrid.getSelectedRecord();
		// 送修申请单编号
		HiddenItem rqIdItem = new HiddenItem();
		rqIdItem.setName("rqId");
		rqIdItem.setValue(rqRecord.getAttribute("id"));

		// 合同编号
		TextItem contratNumberItem = new TextItem();
		contratNumberItem.setName("contractNumber");
		contratNumberItem.setDisabled(true);
		contratNumberItem.setValue(ui.getB().msgAutoIdInfo());

		// 优先级
		final SelectItem priorityItem = new SelectItem();
		priorityItem.setName("m_Priority");
		priorityItem.setValue(rqRecord.getAttribute("m_Priority"));

		// 飞机机尾号
		final SelectItem planeLastNumberItem = new SelectItem();
		planeLastNumberItem.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(planeLastNumberItem);

		priorityItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if(priorityItem.getDisplayValue().equals("AOG")){
					planeLastNumberItem.setRequired(true);
				}else{
					planeLastNumberItem.setRequired(false);
				}
			}
		});

		// 客户
		final SelectItem buyerItem = new SelectItem();
		buyerItem.setName("buyer.id");
		buyerItem.setRequired(true);
		buyerItem.setTitle(ui.getM().mod_customer_name());
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, buyerItem);
		buyerItem.setDefaultValue(rqRecord
				.getAttribute("m_CustomerIdentificationCode.id"));

		// 联系人
		final TextItem linkManItem = new TextItem();
		linkManItem.setName("linkMan");
		linkManItem.setValue(rqRecord.getAttribute("linkman"));

		// 联系方式
		final TextItem linkWayItem = new TextItem();
		linkWayItem.setName("linkWay");
		linkWayItem.setColSpan(3);
		linkWayItem.setWidth("100%");
		linkWayItem.setValue(rqRecord.getAttribute("contactInformation"));

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

		carrierNameItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				service.getCarrierVO(carrierNameItem.getValueAsString(),
						new AsyncCallback<CarrierVO>() {
							@Override
							public void onSuccess(CarrierVO vo) {
								carrierLinkWayItem.setValue(vo.getLinkType());
								carrierLinkManItem.setValue(vo.getLinkMan());
							}

							@Override
							public void onFailure(Throwable e) {
								SC.warn("get carrier info failed. error:" + e);
							}
						});
			}
		});

		isBuyerFreightAgentItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				carrierNameItem.setDisabled(!((Boolean) event.getValue()));
				carrierLinkWayItem.setDisabled(!((Boolean) event.getValue()));
				carrierLinkManItem.setDisabled(!((Boolean) event.getValue()));
			}
		});

		// 贸易方式
		DicSelectItem tradeMethodItem = new DicSelectItem();
		tradeMethodItem.setName("m_TradeMethods");

		// GTA协议
		final SelectItem enterpriseGTAItem = new SelectItem();
		enterpriseGTAItem.setName("enterpriseIds");
		enterpriseGTAItem.setMultiple(true);
		enterpriseGTAItem.setColSpan(3);
		enterpriseGTAItem.setWidth("100%");

		// 初始化GTA
		service.getCustomerContact(buyerItem.getValueAsString(),
				new AsyncCallback<CustomerContact>() {
					@Override
					public void onSuccess(CustomerContact vo) {
						enterpriseGTAItem.setValueMap(vo.getGtaInfo());
					}

					@Override
					public void onFailure(Throwable e) {
						SC.warn("get GTA info failed. error:" + e);
					}
				});

		// 带出客户相关信息
		buyerItem.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				service.getCustomerContact(buyerItem.getValueAsString(),
						new AsyncCallback<CustomerContact>() {
							@Override
							public void onSuccess(CustomerContact vo) {
								linkManItem.setValue(vo.getLinkman());
								linkWayItem.setValue(vo.getAddress());
								enterpriseGTAItem.setValueMap(vo.getGtaInfo());
							}

							@Override
							public void onFailure(Throwable e) {
								SC.warn("get customer info failed. error:" + e);
							}
						});
			}
		});

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

		// 合同总金额
		TextItem extendedValueTotalAmountItem = new TextItem();
		extendedValueTotalAmountItem.setName("extendedValueTotalAmount");
		extendedValueTotalAmountItem.setTitle(ui.getRepairModule().contract_amount());

		/**
		 * 合同明细信息
		 */
		// 件号
		SelectItem partNumberItem = Utils.getPartNumberList();
		partNumberItem.setStartRow(true);
		partNumberItem.setName("m_RepairContractItem.partNumber");
		partNumberItem.setType("comboBox");
		partNumberItem.setRequired(true);
		partNumberItem.setValue(rqRecord
				.getAttribute("repairRequisitionSheetItem.partNumber"));

		// 序号/批次号
		TextItem batchNumberItem = new TextItem();
		batchNumberItem.setName("m_RepairContractItem.batchNumber");
		batchNumberItem.setValue(rqRecord
				.getAttribute("repairRequisitionSheetItem.batchNumber"));

		// 原始合同编号
		TextItem oldContractNumberItem = new TextItem();
		oldContractNumberItem.setName("m_RepairContractItem.oldContractNumber");
		oldContractNumberItem.setRequired(true);
		oldContractNumberItem.setValue(rqRecord
				.getAttribute("repairRequisitionSheetItem.oldContractNumber"));

		// 备注
		TextAreaItem remarkTextItemItem = new TextAreaItem();
		remarkTextItemItem.setName("m_RepairContractItem.remarkText");
		remarkTextItemItem.setColSpan(3);
		remarkTextItemItem.setWidth("100%");
		remarkTextItemItem.setValue(rqRecord
				.getAttribute("repairRequisitionSheetItem.remarkText"));

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

		form.setFields(rqIdItem, contratNumberItem,priorityItem, planeLastNumberItem,
				 buyerItem, linkManItem, linkWayItem,
				deliveryTypeItem, isBuyerFreightAgentItem,
				transportationCodeItem, carrierNameItem, carrierLinkWayItem,
				carrierLinkManItem, tradeMethodItem, enterpriseGTAItem,
				paymentItem, paymentExplainItem, extraProvisionItem,
				remarkTextItem, makeByItem, contractItemSection,
				partNumberItem, batchNumberItem, oldContractNumberItem,
				remarkTextItemItem, cycleReferenceCodeItem,
				customsReferencePriceItem, currencyItem);

		addMember(form);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(5);
		btnGroup.setLayoutTopMargin(10);
		btnGroup.setLayoutLeftMargin(10);

		final IButton btnSave = new IButton(ui.getB().btnSaveAdd());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						// 将合同主键放入共享数据
						String contractID = response.getData()[0]
								.getAttribute("id");
						contractModel.contractID=contractID;
						BaseAddressModel.getInstance().addr_sheetId = contractID;
						attachmentCanvas.setUuid(contractID);
						
						//更新送修申请单的业务状态
						CodeRPCTool.updateBussinessStateByAdd("RepairRequisitionSheet", rqRecord.getAttribute("id"));
						
						
					}
				});
			}
		});

		IButton btnClose = new IButton(ui.getB().btnClose());
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				model.repairContractTemplateAddWin.destroy();
			}
		});

		btnGroup.addMember(btnSave);
		btnGroup.addMember(btnClose);
		addMember(btnGroup);

	}

}
