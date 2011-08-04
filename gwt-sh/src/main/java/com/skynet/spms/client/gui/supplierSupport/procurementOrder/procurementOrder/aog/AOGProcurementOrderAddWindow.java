package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.aog;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.business.ProcurementOrderBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AOGProcurementOrderAddWindow extends BaseWindow {

	private static I18n ui = new I18n();
	private ProcurementOrderBusiness business = new ProcurementOrderBusiness();
	private SaleContractModelLocator contractModelLocator;
	private ProcurementOrderModelLocator model;

	private LayoutDynamicForm ldf;// 主订单From

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	LayoutDynamicForm itemLdf;

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public AOGProcurementOrderAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		contractModelLocator = SaleContractModelLocator.getInstance();
		model = ProcurementOrderModelLocator.getInstance();
		this.setTitle(ui.getB().addFormTitle(
				ui.getM().mod_procurementOrder_name()));
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setBorder("none");
		vmain.setLayoutMargin(3);
		vmain.setWidth100();
		vmain.setHeight100();

		/** 面板1 **/
		VLayout firstView = getPrimaryView();
		firstView.setHeight(150);
		firstView.setLayoutTopMargin(5);
		vmain.addMember(firstView);

		/** 面板2 **/
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3 **/
		VLayout threeView = getItemView();
		threeView.setHeight(180);
		threeView.setLayoutTopMargin(10);
		vmain.addMember(threeView);

		return vmain;
	}

	// 布局一
	private VLayout getPrimaryView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_procurementOrder_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化采购指令数据源
		dataSourceTool
				.onCreateDataSource(
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDER,
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDER_DS,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								ldf.setDataSource(dataSource);
								// 采购指令编号
								TextItem item1 = new TextItem();
								item1.setName("orderNumber");
								item1.setValue(ui.getB().msgAutoIdInfo());
								item1.setDisabled(true);

								// 优先级
								DicSelectItem item2 = new DicSelectItem();
								item2.setName("m_Priority");
								item2.setTitleAlign(Alignment.LEFT);
								item2.setDisabled(true);
								item2.setValue(contractModelLocator.saleGrid
										.getSelectedRecord().getAttribute(
												"m_Priority"));

								// 飞机机尾号
								TextItem item3 = new TextItem();
								item3.setName("m_AircraftIdentificationNumber");
								item3.setValue(contractModelLocator.saleGrid
										.getSelectedRecord().getAttribute(
												"aircraftNumber"));
								item3.setDisabled(true);

								// 销售合同号
								TextItem item4 = new TextItem();
								FormItemIcon fIcon = new FormItemIcon();
								item4.setIcons(fIcon);
								item4.setValue(contractModelLocator.saleGrid
										.getSelectedRecord().getAttribute(
												"contractNumber"));
								item4.setDisabled(true);

								// 备注
								TextAreaItem item5 = new TextAreaItem();
								item5.setName("remarkText");
								item5.setRowSpan(3);
								item5.setHeight("100%");
								item5.setTitleVAlign(VerticalAlignment.TOP);
								item5.setValue(contractModelLocator.saleGrid
										.getSelectedRecord().getAttribute(
												"remarkText"));

								// 采购数量总计
								TextItem item6 = new TextItem();
								item6.setName("itemCount");
								item6.setDisabled(true);

								// 采购金额总计
								TextItem item8 = new TextItem();
								item8.setName("totalAmount");
								item8.setDisabled(true);

								// 指令描述
								TextAreaItem item10 = new TextAreaItem();
								item10.setName("description");
								item10.setTitleVAlign(VerticalAlignment.TOP);
								item10.setColSpan(3);

								ldf.setFields(item1, item2, item3, item4,
										item6, item8, item10, item5);

								Timer timer = Utils.startAmountTimer(lg, item6,
										item8, "unitPriceAmount");
								timer.scheduleRepeating(1000);

							}
						});
		v.setMembers(ldf);
		btnsLayout = new BtnsHLayout();
		// 构建操作按钮
		final IButton cancelBtn = new IButton(ui.getB().btnCancel());
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}

		});

		final IButton saveBtn = new IButton(ui.getB().btnSaveAdd());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						itemBtnsLayout.enable();// 明细按钮可用
						model.sheetID = response.getData()[0]
								.getAttribute("id");
						business.refeshListGrid(model.procurementOrderListGrid);
					}
				});
			}
		});

		btnsLayout.addMember(saveBtn);
		btnsLayout.addMember(cancelBtn);

		v.addMember(btnsLayout);

		return v;
	}

	private FormItem item1;
	private DateItem item2;
	private FormItem item3;
	private SelectItem item4;
	private FormItem item5;
	private SelectItem item6;
	private FormItem item7;
	private FormItem item8;
	private FormItem item9;
	private FormItem item10;
	private FormItem item11;
	private FormItem item12;

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		Label leftLb = new Label("销售合同明细项");
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(ui.getB().procurementOrderItems());
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		h.addMember(leftLayout);
		h.addMember(rightLayout);
		return h;
	}

	// 销售合同明细项Grid
	private ListGrid getLeftGrid() {
		final FilterListGrid lg = new FilterListGrid();
		Utils.setListGridHeight(lg);
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						lg.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("salesTemplate.id",
								contractModelLocator.contractID);
						lg.fetchData(criteria);
						// 件号
						ListGridField fileld2 = new ListGridField("partNumber");
						// 销售数量
						ListGridField fileld5 = new ListGridField("quantity");
						Utils.formatQuantityField(fileld5);

						lg.setFields(fileld2, fileld5);
					}
				});
		lg.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ListGridRecord record = lg.getSelectedRecord();
				item1.setValue(record.getAttribute("partNumber"));
				item2.setValue(record.getAttributeAsDate("deliveryDate"));
				item3.setValue(record.getAttribute("ata"));
				item4.setValue(record.getAttribute("m_ManufacturerCode.id"));
				item5.setValue(record.getAttribute("keyword"));
				item7.setValue(record.getAttribute("quantity"));
				item8.setValue(record.getAttribute("m_UnitOfMeasureCode"));
				item9.setValue(record.getAttribute("remarkText"));
				item10.setValue(record.getAttribute("unitPrice"));
				item11.setValue(record.getAttribute("price"));
				item12.setValue(record.getAttribute("currency"));
			}
		});
		return lg;
	}

	private FilterListGrid lg;

	// 新采购指令明细项
	private ListGrid getRightGrid() {
		lg = new FilterListGrid();

		lg.setCanRemoveRecords(true);
		Utils.setListGridHeight(lg);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化采购指令明细数据源
		dataSourceTool
				.onCreateDataSource(
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDER,
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDERITEM_DS,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								lg.setDataSource(dataSource);
								// 件号
								ListGridField fileld2 = new ListGridField(
										"partNumber");
								// 需求日期
								ListGridField fileld4 = new ListGridField(
										"deliveryDate");
								// 采购数量
								ListGridField fileld5 = new ListGridField(
										"plannedQuantity");
								Utils.formatQuantityField(fileld5);

								lg.setFields(fileld2, fileld4, fileld5);
							}
						});
		return lg;
	}

	final TextItem item0 = new TextItem();

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getB().procurementOrderItems());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setDisabled(true);
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(6);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化采购指令明细数据源
		dataSourceTool
				.onCreateDataSource(
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDER,
						com.skynet.spms.client.gui.supplierSupport.common.DSKey.S_PROCUREMENTORDERITEM_DS,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								itemLdf.setDataSource(dataSource);
								item0.setName("procurementOrder.id");
								item0.setVisible(false);

								// 件号
								item1 = new SelectItem();
								item1.setName("partNumber");
								item1.setDisabled(true);
								// 需求日期
								item2 = new DateItem();
								item2.setName("deliveryDate");
								item2
										.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
								item2.setUseTextField(true);
								item2.setDisabled(true);
								// ATA章节号
								item3 = new TextItem();
								item3.setName("ata");
								item3.setDisabled(true);

								// 制造商
								item4 = new SelectItem();
								item4.setTitle(ui.getB().m_ManufacturerCode());
								item4.setName("m_ManufacturerCode.id");
								item4.setDisabled(true);
								CodeRPCTool.bindData(
										CodeRPCTool.MANUFACTURERCODE, item4);
								// 关键字
								item5 = new TextItem();
								item5.setName("itemKeyword");
								item5.setDisabled(true);
								// 证书类型
								item6 = new SelectItem();
								item6.setName("m_CertificateType");
								item6.setMultiple(true);
								EnumTool.setValueMap(EnumTool.CERTIFICATETYPE,
										item6);
								// 采购数量
								item7 = new SpinnerItem();
								item7.setName("plannedQuantity");
								item7.setDisabled(true);
								// 单位
								item8 = new DicSelectItem();
								item8.setName("m_UnitOfMeasureCode");
								item8.setDisabled(true);
								// 备注
								item9 = new TextAreaItem();
								item9.setName("remarkText");
								item9.setRowSpan(2);
								item9.setHeight(50);
								item9.setDisabled(true);
								// 采购单价
								item10 = new SpinnerItem();
								item10.setName("plannedUnitPrice");
								item10.setDisabled(true);
								// 采购金额
								item11 = new SpinnerItem();
								item11.setName("unitPriceAmount");
								item11.setDisabled(true);
								// 币种
								item12 = new DicSelectItem();
								item12.setName("m_InternationalCurrencyCode");
								item12.setDisabled(true);

								itemLdf.setFields(item0, item1, item2, item3,
										item4, item5, item6, item7, item8,
										item9, item10, item12, item11);
							}
						});
		v.addMember(itemLdf);

		itemBtnsLayout = new BtnsHLayout();
		// 构建操作按钮
		IButton saveBtn = new IButton(ui.getB().btnSaveAdd());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				item0.setValue(model.sheetID);
				itemLdf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						itemLdf.editNewRecord();
						Criteria criteria = new Criteria();
						criteria.addCriteria("id", model.sheetID);
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						lg.fetchData(criteria);
					}
				});
			}
		});

		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		itemBtnsLayout.setMembers(saveBtn, closeBtn);
		v.addMember(itemBtnsLayout);

		return v;
	}
}
