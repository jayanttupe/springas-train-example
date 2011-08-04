package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.EditorEnterEvent;
import com.smartgwt.client.widgets.grid.events.EditorEnterHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 检修费用配置
 * 
 * @author tqc
 * 
 */
public class RepairQuoteRecordForm extends VLayout {

	private DynamicForm form = new DynamicForm();

	private final ListGrid itemDetailGrid = new ListGrid();

	private I18n ui=new I18n();
	
	private ModelLocator model = ModelLocator.getInstance();

	public RepairQuoteRecordForm() {
		// form 数据源
		DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE,
				DSKey.R_REPAIRQUOTERECORD_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						build();
					}
				});
		// grid 数据源
		tool.onCreateDataSource(DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE,
				DSKey.R_REPAIRQUOTERECORDITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemDetailGrid.setDataSource(dataSource);
						buildGrid();
					}
				});
	}

	private void build() {
		form.setNumCols(4);
		final HiddenItem supplierSupportContractIdHiddenItem = new HiddenItem();
		supplierSupportContractIdHiddenItem
				.setName("supplierSupportContractId");

		DateItem checkDateItem = new DateItem();
		//checkDateItem.setTitle("报价日期");
		checkDateItem.setName("detectionDate");
		checkDateItem.setUseTextField(true);
		checkDateItem.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		SpinnerItem checkCompanyItem = new SpinnerItem();
		checkCompanyItem.setName("usefulLife");
		//checkCompanyItem.setTitle("有效日期(天)");

		TextAreaItem inspectDescriptionItem = new TextAreaItem();
		inspectDescriptionItem.setColSpan(2);
		inspectDescriptionItem.setWidth("100%");
		inspectDescriptionItem.setName("repairQuotedescription");
		//inspectDescriptionItem.setTitle("修理报价描述");

		form.setFields(supplierSupportContractIdHiddenItem, checkDateItem,
				checkCompanyItem, inspectDescriptionItem);

		// 创建操作按钮
		final IButton btnSave = new IButton(ui.getB().btnSaveAdd());
		final IButton btnItemSave = new IButton(ui.getRepairModule().btn_item_add());
		final IButton btnItemModify = new IButton(ui.getRepairModule().btn_item_save());
		final IButton btnItemCannel = new IButton(ui.getRepairModule().cancel());

		controlChange(btnItemSave, btnItemModify, btnItemCannel, true);

		// 按钮构建
		HLayout masterBtnGroup = new HLayout();
		masterBtnGroup.setMembersMargin(3);
		masterBtnGroup.setHeight(25);

		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (model.contractID == null || "".equals(model.contractID)) {
					SC.warn(ui.getRepairModule().warn_save_base_info_first());
					return;
				}
				supplierSupportContractIdHiddenItem.setValue(model.contractID);
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						model.inspectOutlayRecordId = response.getData()[0]
								.getAttribute("id");
						controlChange(btnItemSave, btnItemModify,
								btnItemCannel, false);
						SC.say(ui.getB().msgAddOpSuccess());
					}
				});

			}
		});

		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);
		SectionStackSection section = new SectionStackSection(ui.getRepairModule().supplier_rq_quote_item());
		section.setCanCollapse(false);
		section.setExpanded(true);

		section.setItems(itemDetailGrid);
		sectionStack.setSections(section);
		masterBtnGroup.addMember(btnSave);

		HLayout itemBtnGroup = new HLayout();
		itemBtnGroup.setHeight(25);

		// 编辑键入事件
		itemDetailGrid.addEditorEnterHandler(new EditorEnterHandler() {
			public void onEditorEnter(EditorEnterEvent event) {
				// 设置外键
				itemDetailGrid.setEditValue(event.getRowNum(),
						"repairQuoteRecordId", model.inspectOutlayRecordId);
				// 设置项号
				itemDetailGrid.setEditValue(event.getRowNum(), "itemNumber",
						event.getRowNum() + 1);
			}
		});

		btnItemSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemDetailGrid.startEditingNew();
			}
		});

		btnItemModify.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemDetailGrid.saveAllEdits(new Function() {
					public void execute() {
						SC.say(ui.getB().msgAddOpSuccess());
						RecordList list = itemDetailGrid.getRecordList();
						for (int i = 0; i < list.getLength(); i++) {
							Record record=list.get(i);
							String unitOfPrice=record.getAttribute("unitOfPrice");
							String quantity=record.getAttribute("quantity");
							Float amount=Float.parseFloat(unitOfPrice)*Float.parseFloat(quantity);
							record.setAttribute("amount", amount);
						}
					}
				});
			}
		});

		btnItemCannel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemDetailGrid.discardAllEdits();
			}
		});

		itemBtnGroup.addMember(btnItemSave);
		itemBtnGroup.addMember(btnItemModify);
		itemBtnGroup.addMember(btnItemCannel);

		addMember(form);
		addMember(masterBtnGroup);
		addMember(sectionStack);
		addMember(itemBtnGroup);
	}

	/***
	 * 构建表格
	 */
	private void buildGrid() {
		itemDetailGrid.setWidth100();
		itemDetailGrid.setHeight(224);
		itemDetailGrid.setShowAllRecords(true);
		itemDetailGrid.setCellHeight(22);
		itemDetailGrid.setCanRemoveRecords(true);
		itemDetailGrid.setCanEdit(true);
		itemDetailGrid.setModalEditing(true);
		itemDetailGrid.setEditEvent(ListGridEditEvent.CLICK);
		itemDetailGrid.setListEndEditAction(RowEndEditAction.NEXT);
		itemDetailGrid.setAutoSaveEdits(false);

		ListGridField supplierSupportContractIdFKField = new ListGridField();
		supplierSupportContractIdFKField.setName("repairQuoteRecordId");
		supplierSupportContractIdFKField.setHidden(true);

		ListGridField itemNumberField = new ListGridField();
		//itemNumberField.setTitle("项号");
		itemNumberField.setName("itemNumber");
		itemNumberField.setCanEdit(false);

		ListGridField itemDescriptionField = new ListGridField();
		//itemDescriptionField.setTitle("项目描述");
		itemDescriptionField.setName("itemDescription");

		FloatRangeValidator floatRangeValidator = new FloatRangeValidator();
		IntegerRangeValidator integerRangeValidator = new IntegerRangeValidator();

		ListGridField unitOfPriceField = new ListGridField();
		//unitOfPriceField.setTitle("单价");
		unitOfPriceField.setName("unitOfPrice");
		unitOfPriceField.setValidators(floatRangeValidator);

		ListGridField quantityField = new ListGridField();
		//quantityField.setTitle("数量");
		quantityField.setName("quantity");
		quantityField.setEditorType(new SpinnerItem());
		quantityField.setValidators(integerRangeValidator);
		
		ListGridField amountField = new ListGridField();
		amountField.setName("amount");
		//amountField.setTitle("金额");
		amountField.setValidators(floatRangeValidator);
		amountField.setCanEdit(false);

		ListGridField internationalCurrencyCodeField = new ListGridField();
		//internationalCurrencyCodeField.setTitle("币种");
		internationalCurrencyCodeField.setName("m_InternationalCurrencyCode");

		ListGridField remarkTextField = new ListGridField();
		//remarkTextField.setTitle("备注");
		remarkTextField.setName("remarkText");

		itemDetailGrid.setFields(supplierSupportContractIdFKField,
				itemNumberField, itemDescriptionField, unitOfPriceField,
				quantityField, amountField, internationalCurrencyCodeField,
				remarkTextField);
	}

	private void controlChange(final IButton btnItemSave,
			final IButton btnItemModify, final IButton btnItemCannel,
			boolean flag) {
		btnItemSave.setDisabled(flag);
		btnItemModify.setDisabled(flag);
		btnItemCannel.setDisabled(flag);
	}

}
