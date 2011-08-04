package com.skynet.spms.client.gui.customerService.repairService.repairContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 修理记录配置
 * 
 * @author tqc
 * 
 */
public class RepairQuoteRecordForm extends VLayout {

	private DynamicForm form = new DynamicForm();
	
	private I18n ui=new I18n();

	private final ListGrid itemDetailGrid = new ListGrid();

	private ContractModelLocator model = ContractModelLocator.getInstance();

	public static AttachmentCanvas attachmentCanvas = new AttachmentCanvas();

	public RepairQuoteRecordForm() {
		DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(DSKey.C_REPAIRECONTRACT,
				DSKey.C_RepairQuoteRecord_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						form.setDataSource(dataSource);
						build();
						Criteria criteria = new Criteria();
						criteria.setAttribute("key", "getByID");
						StringBuilder stringBuilder=new StringBuilder();
						stringBuilder.append( model.viewDetailContractID);
						criteria.setAttribute("contractId", stringBuilder.toString());
						form.fetchData(criteria, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								// 存在记录了
								if (response.getData() != null
										&& response.getData().length > 0) {
									final String repairReocrdId = response
											.getData()[0].getAttribute("id");
									model.repairReocrdId = repairReocrdId;
									initItemGird(repairReocrdId);
									attachmentCanvas.setUuid(repairReocrdId);
									addMember(attachmentCanvas);
								} else {// 第一次添加
									initItemGird("temp");
									addMember(new AttachmentCanvas());
								}
							}
						});

					}
				});
	}

	private void initItemGird(final String repairReocrdId) {
		final DataSourceTool tool = new DataSourceTool();
		// 构建明细grid
		tool.onCreateDataSource(DSKey.C_REPAIRECONTRACT,
				DSKey.C_RepairQuoteRecordItem_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemDetailGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("key", "getByID");
						StringBuilder stringBuilder=new StringBuilder();
						stringBuilder.append( repairReocrdId);
						criteria.setAttribute("repairQutoReocdId",
								stringBuilder.toString());
						itemDetailGrid.fetchData(criteria, new DSCallback() {
							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								buildGrid();
							}
						});
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
		Utils.setReadOnlyForm(form);

		// 按钮构建
		HLayout masterBtnGroup = new HLayout();
		masterBtnGroup.setMembersMargin(3);
		masterBtnGroup.setHeight(25);

		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);
		SectionStackSection section = new SectionStackSection(ui.getRepairModule().supplier_rq_quote_item());
		section.setCanCollapse(false);
		section.setExpanded(true);

		section.setItems(itemDetailGrid);
		sectionStack.setSections(section);

		HLayout itemBtnGroup = new HLayout();
		itemBtnGroup.setHeight(25);

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
		itemDetailGrid.setCanEdit(false);

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
		unitOfPriceField.setRequired(true);

		ListGridField quantityField = new ListGridField();
		//quantityField.setTitle("数量");
		quantityField.setName("quantity");
		quantityField.setEditorType(new SpinnerItem());
		quantityField.setValidators(integerRangeValidator);
		quantityField.setRequired(true);

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

}
