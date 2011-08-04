package com.skynet.spms.client.gui.supplierSupport.procurementOrder.pickUpOrder.tabs;

import com.skynet.spms.client.gui.commonOrder.pickup.model.DataModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class PickUpBaseForm extends VLayout {

	private LayoutDynamicForm form = new LayoutDynamicForm();
	
	public DataModelLocator pickupModel=DataModelLocator.getInstance();
	
	/** 指令编号 */
	private TextItem deliveryIdText;
	/** 运单号 */
	private TextItem orderNumberText;
	/** 合同号 */
	private TextItem contractNumberText;
	/** 优先级 */
	private TextItem priorityText;
	/** 发货日期 */
	private DateItem deliveryDate;
	/** 指令类型 */
	/*private SelectItem directiveTypeSelect;*/
	/** 供应商 */
	private TextItem supplierText;
	/** 制定运代 */
	private CheckboxItem freightAgentCheckbox;
	/** 发送方案 */
	private SelectItem sendFormulaSelect;
	/** 交货方式 */
	private TextItem deliveryTypeText;
	/** 运代商 */
	private TextItem carrierNameText;
	/** 联系人*/
	private TextItem linkmanText;
	/** 贸易方式 */
	private TextItem tradeMethodsText;
	/** 联系方式 */
	private TextItem contactTypeText;
	/** 指令描述 */
	private TextItem descriptionText;
	/** 运输方式 */
	private TextItem transportTypeText;
	/** 指定发送方式 */
	private SelectItem sendTypeSelect;
	/** 报关代理商 */
	private SelectItem clearanceAgentSelect;
	/** 指定类型 */
	private SelectItem typeSelect;
	/** 发布人 */
	private TextItem publishUserText;
	
	
	public PickUpBaseForm() {
		// form.setDataSource(ds);
		// form.editSelectedData(repairRequisitionListGrid);
		form.setNumCols(6);
		form.setCellPadding(2);
		build();
	}

	private void build() {
		// 指令编号
		deliveryIdText = new TextItem();
		deliveryIdText.setName("deliveryIdText");
		deliveryIdText.setValue("业务编号系统自动生成");
		deliveryIdText.setTitle("指令编号");
		deliveryIdText.setDisabled(true);

		// 运单号
		orderNumberText= new TextItem();
		orderNumberText.setName("orderNumberText");
		orderNumberText.setDisabled(true);
		orderNumberText.setTitle("运单号");

		// 优先级
		priorityText= new TextItem();
		priorityText.setTitle("优先级");
		priorityText.setName("priorityText");
		priorityText.setDisabled(true);
		//pickupModel.priorityText=priorityText;

		// 发货日期
		deliveryDate = new DateItem();
		deliveryDate.setTitle("发货日期");
		deliveryDate.setName("deliveryDate");

		// 依据合同号
		contractNumberText= new TextItem();
		contractNumberText.setName("contractNumberText");
		contractNumberText.setTitle("依据合同号");
		pickupModel.contractNumberText=contractNumberText;

		// 供应商/客户
		supplierText = new TextItem();
		supplierText.setName("supplierText");
		supplierText.setTitle("供应商/客户");
		supplierText.setDisabled(true);

		// 已指定运代
		freightAgentCheckbox = new CheckboxItem();
		freightAgentCheckbox.setName("freightAgentCheckbox");
		freightAgentCheckbox.setTitle("已指定运代");

		// 发送方案
		sendFormulaSelect = new SelectItem();
		sendFormulaSelect.setName("item8");
		sendFormulaSelect.setTitle("发送方案");

		// 交货方式
		deliveryTypeText= new TextItem();
		deliveryTypeText.setName("deliveryTypeText");
		deliveryTypeText.setTitle("交货方式");
		deliveryTypeText.setDisabled(true);
		//pickupModel.deliveryTypeText=deliveryIdText;

		// 运代商
		carrierNameText = new TextItem();
		carrierNameText.setName("carrierNameText");
		carrierNameText.setTitle("运代商");
		carrierNameText.setDisabled(true);

		// 联系人
		linkmanText= new TextItem();
		linkmanText.setName("linkmanText");
		linkmanText.setTitle("联系人");
		linkmanText.setDisabled(true);
		
		// 贸易方式
		tradeMethodsText= new TextItem();
		tradeMethodsText.setName("tradeMethodsText");
		tradeMethodsText.setTitle("贸易方式");
		tradeMethodsText.setDisabled(true);
		pickupModel.tradeMethodsText=tradeMethodsText;

		// 联系方式
		contactTypeText= new TextItem();
		contactTypeText.setName("contactTypeText");
		contactTypeText.setTitle("联系方式");
		contactTypeText.setColSpan(3);
		contactTypeText.setDisabled(true);

		// 运输方式
		transportTypeText= new TextItem();
		transportTypeText.setName("transportTypeText");
		transportTypeText.setTitle("运输方式");
		transportTypeText.setDisabled(true);
		//pickupModel.transportTypeText=transportTypeText;

		// 指令描述
		descriptionText= new TextItem();
		descriptionText.setName("descriptionText");
		descriptionText.setTitle("指令描述");
		descriptionText.setRowSpan(4);
		descriptionText.setColSpan(3);
		descriptionText.setHeight("100%");

		// 指定发送方式
		sendTypeSelect = new SelectItem();
		sendTypeSelect.setName("sendTypeSelect");
		sendTypeSelect.setTitle("指定发送方式");

		// 报关代理商
		clearanceAgentSelect = new SelectItem();
		clearanceAgentSelect.setName("clearanceAgentSelect");
		clearanceAgentSelect.setTitle("报关代理商");
		
		// 指定类型
		typeSelect = new SelectItem();
		typeSelect.setName("typeSelect");
		typeSelect.setTitle("指定类型");

		// 发布人
		publishUserText = new TextItem();
		publishUserText.setName("publishUserText");
		publishUserText.setTitle("发布人");
		publishUserText.setDisabled(true);
		
		
		

		form.setFields(deliveryIdText,orderNumberText,priorityText,deliveryDate,contractNumberText,supplierText,freightAgentCheckbox,sendFormulaSelect,deliveryTypeText,carrierNameText,
				linkmanText,tradeMethodsText,contactTypeText,transportTypeText,descriptionText,sendTypeSelect,clearanceAgentSelect,typeSelect,publishUserText);
		this.addMember(form);

		/**操作按钮**/
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(5);
		btnGroup.setMargin(5);
		btnGroup.setLayoutLeftMargin(50);

		IButton btnSave = new IButton("保存");
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("ok");
					}
				});
			}
		});

		IButton closeBtn = new IButton("关闭");
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
				
			}
		});
		btnGroup.addMember(btnSave);
		btnGroup.addMember(closeBtn);
		this.addMember(btnGroup);
	}

}
