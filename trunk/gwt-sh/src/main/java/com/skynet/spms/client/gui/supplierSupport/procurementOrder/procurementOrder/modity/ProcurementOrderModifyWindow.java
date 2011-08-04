package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.modity;

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
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.business.ProcurementOrderBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/***
 * 主订单 与 明细 添加页面
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementOrderModifyWindow extends BaseWindow {
	private static I18n ui = new I18n();
	private ProcurementOrderModelLocator model;
	private ProcurementOrderBusiness business = new ProcurementOrderBusiness();
	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private TextItem item0;
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

	ViewProcurementOrderItemListGrid orderItemListGrid;

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ProcurementOrderModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model = ProcurementOrderModelLocator.getInstance();
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		// /**面板1**/
		VLayout oneView = getPrimaryView();
		oneView.setHeight(150);
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 **/
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		// /**面板3**/
		VLayout threeView = getItemView();
		threeView.setHeight(180);
		threeView.setLayoutTopMargin(10);
		threeView.setWidth100();
		vmain.addMember(threeView);

		return vmain;
	}

	// 布局一
	private VLayout getPrimaryView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight("180");
		v.setGroupTitle(ui.getM().mod_procurementOrder_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setDataSource(model.procurementOrderListGrid.getDataSource());
		ldf.reset();
		ldf.editSelectedData(model.procurementOrderListGrid);
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setCellPadding(2);

		// 采购指令编号
		TextItem item_1 = new TextItem("orderNumber");
		item_1.setAttribute("backgroundColor", "red");
		item_1.setDisabled(true);

		// 优先级
		DicSelectItem item_2 = new DicSelectItem();
		item_2.setName("m_Priority");

		// 飞机机尾号
		TextItem item_3 = new TextItem("m_AircraftIdentificationNumber");

		// 采购计划号
		final TextItem item_5 = new TextItem("procurementPlanNumber");
		item_5.setDisabled(true);

		// 计划人员
		TextItem item_6 = new TextItem("createBy");
		item_6.setDisabled(true);

		// 备注
		TextAreaItem item_7 = new TextAreaItem("remarkText");
		item_7.setRowSpan(2);
		item_7.setHeight(50);
		item_7.setTitleVAlign(VerticalAlignment.TOP);

		// 计划开始日期
		DateItem item_8 = new DateItem();
		item_8.setName("startDate");
		item_8.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item_8.setDisabled(true);
		item_8.setUseTextField(true);

		// 计划结束日期
		DateItem item_9 = new DateItem();
		item_9.setName("endDate");
		item_9.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item_9.setDisabled(true);
		item_9.setUseTextField(true);

		// 指令描述
		TextAreaItem item_10 = new TextAreaItem("description");
		item_10.setRowSpan(2);
		item_10.setHeight(50);
		item_10.setTitleVAlign(VerticalAlignment.TOP);

		// 采购数量总计
		SpinnerItem item_11 = new SpinnerItem();
		item_11.setName("itemCount");
		item_11.setDisabled(true);

		// 采购金额总计
		SpinnerItem item_12 = new SpinnerItem();
		item_12.setName("totalAmount");
		item_12.setDisabled(true);

		ldf.setFields(item_1, item_2, item_3, item_5, item_6, item_7, item_8,
				item_9, item_10, item_11, item_12);

		v.setMembers(ldf);

		Timer timer = Utils.startAmountTimer(orderItemListGrid, item_11,
				item_12, "unitPriceAmount");
		timer.scheduleRepeating(1000);

		btnsLayout = new BtnsHLayout();

		// 构建操作按钮
		final IButton saveBtn = new IButton(ui.getB().btnSaveAdd());
		final IButton cancelBtn = new IButton(ui.getB().btnClose());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						business.refeshListGrid(model.procurementOrderListGrid);
					}
				});
				itemBtnsLayout.enable();// 明细按钮可用
			}

		});

		// 关闭
		cancelBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				destroy();
			}

		});
		btnsLayout.addMember(saveBtn);
		btnsLayout.addMember(cancelBtn);

		v.addMember(btnsLayout);

		return v;
	}

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		Label leftLb = new Label(ui.getB().procurementPlanItems());
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		// 采购计划明细的ListGrid
		final ViewProcurementPlanItemListGrid planItemListGrid = new ViewProcurementPlanItemListGrid();
		Utils.setListGridHeight(planItemListGrid);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTPLAN,
				DSKey.S_PROCUREMENTPLANITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						planItemListGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("procurementPlanNumber",
								model.procurementOrderListGrid
										.getSelectedRecord().getAttribute(
												"procurementPlanNumber"));
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						planItemListGrid.fetchData(criteria);
						planItemListGrid.drawGrid();
					}
				});
		planItemListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				itemLdf.editNewRecord();
				ListGridRecord record = planItemListGrid.getSelectedRecord();
				item1.setValue(record.getAttribute("partNumber"));
				item2.setValue(record.getAttributeAsDate("deliveryDate"));
				item3.setValue(record.getAttribute("ata"));
				item4.setValue(record.getAttribute("m_ManufacturerCode.id"));
				item5.setValue(record.getAttribute("itemKeyword"));
				item7.setValue(record.getAttribute("plannedQuantity"));
				item8.setValue(record.getAttribute("m_UnitOfMeasureCode"));
				item9.setValue(record.getAttribute("remarkText"));
				item10.setValue(record.getAttribute("plannedUnitPrice"));
				item11.setValue(record.getAttribute("unitPriceAmount"));
				item12.setValue(record
						.getAttribute("m_InternationalCurrencyCode"));
			}
		});
		leftLayout.addMember(planItemListGrid);// 再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(ui.getB().procurementOrderItems());
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		// 采购指令明细的ListGrid
		orderItemListGrid = new ViewProcurementOrderItemListGrid();
		Utils.setListGridHeight(orderItemListGrid);
		orderItemListGrid.setDataSource(model.procurementOrderItemDs);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", model.doProcurementOrderId);
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		orderItemListGrid.fetchData(criteria);

		rightLayout.addMember(orderItemListGrid);

		orderItemListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				itemLdf.reset();
				itemLdf.editSelectedData(orderItemListGrid);
			}
		});
		h.addMember(leftLayout);
		h.addMember(rightLayout);
		return h;
	}

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getB().procurementOrderItems());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setDataSource(model.procurementOrderItemDs);
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(6);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		item0 = new TextItem();
		item0.setName("procurementOrder.id");
		item0.setVisible(false);

		// 件号
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.setDisabled(true);

		// 需求日期
		item2 = new DateItem();
		item2.setName("deliveryDate");
		item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
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
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item4);

		// 关键字
		item5 = new TextItem();
		item5.setName("itemKeyword");
		item5.setDisabled(true);

		// 证书类型
		item6 = new SelectItem();
		item6.setMultiple(true);
		item6.setName("m_CertificateType");
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, item6);

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

		itemLdf.setFields(item0, item1, item2, item3, item4, item5, item6,
				item7, item8, item9, item10, item12, item11);

		v.addMember(itemLdf);

		itemBtnsLayout = new BtnsHLayout();
		// 构建操作按钮
		IButton saveBtn = new IButton(ui.getB().btnSaveItem());
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				item0.setValue(model.doProcurementOrderId);
				itemLdf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						criteria.addCriteria("id", model.doProcurementOrderId);
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						orderItemListGrid.fetchData(criteria, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								orderItemListGrid.drawGrid();
								business
										.refeshListGrid(model.procurementOrderListGrid);
							}
						});

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
