package com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui.LeaseContractItemListGrid;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RequisitonLeaseInstructAddWindow extends BaseWindow {
	private static I18n ui = new I18n();
	private MainModelLocator modelLocator;
	private LayoutDynamicForm itemLdf;
	private LeaseRequisitionSheetBusiness business = new LeaseRequisitionSheetBusiness();

	public RequisitonLeaseInstructAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		modelLocator = MainModelLocator.getInstance();
		VLayout v = new VLayout();
		v
				.setMembers(buildInstructForm(), buildListGrid(),
						buildLeaseItemForm());
		return v;
	}

	// 加载申请供应商租赁指令的form
	private Canvas buildInstructForm() {
		VLayout v = new VLayout();
		v.setHeight(100);
		v.setIsGroup(true);
		v.setGroupTitle(ui.getM().mod_requisitionLeaseInstruct_name());

		HLayout h1 = new HLayout();
		final LayoutDynamicForm form = new LayoutDynamicForm();

		form.setNumCols(6);
		form.setWidth100();
		final TextItem item2 = new TextItem();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASEINSTRUCT,
				DSKey.C_LEASEINSTRUCT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						form.setDataSource(dataSource);

						TextItem item0 = new TextItem();
						item0.setVisible(false);

						item0.setName("m_LeaseContractTemplate.id");
						item0.setValue(modelLocator.leaseContractListGrid
								.getSelectedRecord().getAttribute("id"));
						// 指令编号
						TextItem item1 = new TextItem();
						item1.setName("orderNumber");
						item1.setValue(ui.getB().msgAutoIdInfo());
						item1.setDisabled(true);

						// 租赁合同编号
						item2.setColSpan(2);
						item2.setName("contractNumber");
						item2.setValue(modelLocator.leaseContractListGrid
								.getSelectedRecord().getAttribute(
										"contractNumber"));
						item2.setDisabled(true);

						// 客户
						SelectItem item3 = new SelectItem();
						item3.setName("m_CustomerIdentificationCode.id");
						item3.setValue(modelLocator.leaseContractListGrid
								.getSelectedRecord().getAttribute("buyer.id"));
						CodeRPCTool.bindData(
								CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item3);
						item3.setDisabled(true);

						// 联系人
						TextItem item4 = new TextItem();
						item4.setName("linkman");
						item4.setDisabled(true);

						// 指令下达人
						TextItem item5 = new TextItem();
						item5.setName("orderedBy");
						item5.setValue(UserTools.getCurrentUserName());
						item5.setDisabled(true);

						// 联系方式
						TextItem item6 = new TextItem();
						item6.setColSpan(6);
						item6.setName("contactInformation");

						// 指令描述
						TextAreaItem item7 = new TextAreaItem();
						item7.setHeight(50);
						item7.setName("description");

						form.setFields(item0, item1, item2, item3, item4,
								item5, item6, item7);
						String id = modelLocator.leaseContractListGrid
								.getSelectedRecord().getAttribute("buyer.id");
						CodeRPCTool.bindCustomerData(id, item4, item6);

					}
				});

		h1.addMember(form);
		HLayout h2 = new HLayout();
		h2.setHeight(20);
		IButton saveBtn = new IButton(ui.getB().btnSave());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say(ui.getB().msgAddOpSuccess());
							CodeRPCTool.updateBussinessStateByAdd(
									"LeaseContractTemplate",
									modelLocator.leaseContractListGrid
											.getSelectedRecord().getAttribute(
													"id"));
							business
									.refeshListGrid(modelLocator.leaseInstructListGrid);

						}
					});
				}
			}
		});
		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		h2.setMembers(saveBtn, closeBtn);
		v.setMembers(h1, h2);
		return v;
	}

	SelectItem item2;
	TextItem item3;
	SelectItem item4;
	SelectItem item5;
	SelectItem item8;
	TextItem item9;
	SelectItem item10;
	SelectItem item11;
	SelectItem item12;
	DateItem item13;
	TextAreaItem item14;
	DateItem item15;
	TextItem item16;
	TextItem item17;
	SelectItem item18;
	TextItem item19;
	TextItem item21;
	TextItem item22;

	// 加载租赁合同明细项的ListGrid
	private Canvas buildListGrid() {
		VLayout v = new VLayout();
		v.setHeight(180);
		HLayout h1 = new HLayout();
		h1.setHeight(20);
		Label lbl = new Label(ui.getB().leaseContractItems());
		h1.addMember(lbl);
		HLayout h2 = new HLayout();
		final LeaseContractItemListGrid leaseContractItemListGrid = new LeaseContractItemListGrid();
		leaseContractItemListGrid
				.setDataSource(modelLocator.leaseContractItemDs);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", modelLocator.leaseContractListGrid
				.getSelectedRecord().getAttribute("id"));
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		leaseContractItemListGrid.fetchData(criteria);
		leaseContractItemListGrid.drawGrid();
		leaseContractItemListGrid
				.addRecordClickHandler(new RecordClickHandler() {
					public void onRecordClick(RecordClickEvent event) {
						Record record = leaseContractItemListGrid
								.getSelectedRecord();
						item2.setValue(record
								.getAttribute("m_ManufacturerCode.id"));
						item3.setValue(record.getAttribute("keyword"));
						item4
								.setValue(record
										.getAttribute("m_CertificateType"));
						item5.setValue(record.getAttribute("m_ConditionCode"));
						item8.setValue(record
								.getAttribute("m_ModelofApplicabilityCode"));
						item9.setValue(record.getAttribute("quantity"));
						item10.setValue(record.getAttribute("m_PackagingCode"));
						item11.setValue(record
								.getAttribute("m_UnitOfMeasureCode"));
						item12.setValue(record
								.getAttribute("m_InternationalCurrencyCode"));
						item13.setValue(record.getAttributeAsDate("startDate"));
						item14.setValue(record.getAttribute("remarkText"));
						item15.setValue(record.getAttributeAsDate("endDate"));
						item16.setValue(record.getAttribute("leaseDays"));
						item17.setValue(record
								.getAttribute("generalRentOfUnit"));
						item18.setValue(record.getAttribute("m_UnitOfRent"));
						item19.setValue(record.getAttribute("factorage"));
						item21.setValue(record.getAttribute("price"));
						item22.setValue(record.getAttribute("originalValue"));
					}
				});
		h2.addMember(leaseContractItemListGrid);
		v.setMembers(h1, h2);
		return v;
	}

	// 加载租令合同明细项的Form
	private Canvas buildLeaseItemForm() {

		VLayout v = new VLayout();
		v.setDisabled(true);
		v.setGroupTitle(ui.getB().leaseContractItems());
		v.setIsGroup(true);

		HLayout h1 = new HLayout();
		itemLdf = new LayoutDynamicForm();
		itemLdf.setNumCols(6);
		itemLdf.setWidth100();

		final TextItem leaseContractId = new TextItem();
		leaseContractId.setVisible(false);
		leaseContractId.setName("leaseContractTemplate.id");

		// 制造商
		item2 = new SelectItem();
		item2.setTitle(ui.getB().m_ManufacturerCode());
		item2.setName("m_ManufacturerCode.id");
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);

		// 关键字
		item3 = new TextItem();
		item3.setName("keyword");

		// 证书类型
		item4 = new SelectItem();
		item4.setName("m_CertificateType");

		// 备件状态代码
		item5 = new SelectItem();
		item5.setName("m_ConditionCode");

		// 适用机型
		item8 = new SelectItem();
		item8.setName("m_ModelofApplicabilityCode");

		// 租赁数量
		item9 = new TextItem();
		item9.setName("quantity");

		// 包装代码
		item10 = new SelectItem();
		item10.setName("m_PackagingCode");

		// 单位
		item11 = new SelectItem();
		item11.setName("m_UnitOfMeasureCode");

		// 币种
		item12 = new SelectItem();
		item12.setName("m_InternationalCurrencyCode");

		// 租赁开始日期
		item13 = new DateItem();
		item13.setName("startDate");
		item13.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item13.setUseTextField(true);

		// 申请单备注
		item14 = new TextAreaItem();
		item14.setName("remarkText");
		item14.setHeight(50);

		// 租赁结束日期
		item15 = new DateItem();
		item15.setName("endDate");
		item15.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item15.setUseTextField(true);

		// 租赁天数
		item16 = new TextItem();
		item16.setName("leaseDays");

		// 标准单位租金
		item17 = new TextItem();
		item17.setName("generalRentOfUnit");

		// 租金计算单位
		item18 = new SelectItem();
		item18.setName("m_UnitOfRent");

		// 手续费
		item19 = new TextItem();
		item19.setName("factorage");

		// 总租金
		item21 = new TextItem();
		item21.setName("price");

		// 原价值
		item22 = new TextItem();
		item22.setName("originalValue");

		itemLdf.setFields(leaseContractId, item2, item3, item4, item5, item8,
				item9, item10, item11, item12, item13, item14, item15, item16,
				item17, item18, item19, item21, item22);

		h1.addMember(itemLdf);
		HLayout h2 = new HLayout();
		h2.setHeight(20);
		IButton saveItemBtn = new IButton(ui.getB().btnSaveItem());
		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		h2.setMembers(saveItemBtn, closeBtn);
		v.addMember(h1);
		return v;
	}
}
