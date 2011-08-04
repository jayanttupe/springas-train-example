package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.modity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.business.ProcurementPlanBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.vo.PartInfoVO;
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
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ProcurementPlanModifyWindow extends BaseWindow {
	private static I18n ui = new I18n();
	private LayoutDynamicForm itemLdf;// 订单明细Form

	private ProcurementPlanBusiness business = new ProcurementPlanBusiness();
	private BtnsHLayout itemBtnsLayout;// 明细操作按钮
	private ProItemListGrid listItem;
	private PartListGrid list;

	private MainModelLocator ms;

	private FormItem item1;
	private DateItem item2;
	private FormItem item3;
	private FormItem item4;
	private SelectItem item44;
	private FormItem item5;
	private FormItem item6;
	private FormItem item7;
	private FormItem item8;
	private FormItem item9;
	private FormItem item10;

	public ProcurementPlanModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.setTitle(windowTitle);

	}

	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		ms = MainModelLocator.getInstance();

		this.setTitle(ui.getB().modifyFormTitle(
				ui.getM().mod_procurementPlan_name()));
		/** 主面板 **/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		/** 面板1 **/
		TabSet tabSet = new TabSet();
		listItem = new ProItemListGrid();
		Tab baseTab = new Tab(ui.getM().mod_procurementPlan_name());
		baseTab.setPane(new ProcurementPlanBaseModifyForm(listItem));

		Tab attachmentTab = new Tab(ui.getB().attachMent());
		attachmentTab.setPane(new AttachmentCanvas());

		tabSet.setTabs(baseTab, attachmentTab);
		tabSet.setHeight(240);
		vmain.addMember(tabSet);

		/** 面板2 **/
		HLayout twoView = new HLayout();
		twoView.addMember(buildListGrid());
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3 **/
		VLayout threeView = new VLayout();
		threeView.addMember(buildForm());
		vmain.addMember(threeView);
		return vmain;
	}

	private static BaseCodeServiceAsync service = GWT
			.create(BaseCodeService.class);
	final String leaseId = ms.procurementPlanListGrid.getSelectedRecord()
			.getAttribute("id");

	// 加载ListGrid的方法
	private Canvas buildListGrid() {
		HLayout h = new HLayout();
		h.setMembersMargin(5);
		VLayout h1 = new VLayout();
		Label lbl1 = new Label(ui.getM().mod_partPlanNeedOrder_name());
		lbl1.setHeight(10);
		list = new PartListGrid();
		Utils.setListGridHeight(list);
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PARTREQUIREMENT,
				DSKey.S_PARTREQUIREMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						list.setDataSource(dataSource);
						list.fetchData();
						list.buildListGrid();
					}
				});
		list.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = list.getSelectedRecord();
				item1.setValue(record.getAttribute("partNumber"));
				item2.setValue(record
						.getAttributeAsDate("expectingDeliveryDate"));
				item44.setValue(record.getAttribute("m_ManufacturerCode.id"));
				item5.setValue(record.getAttribute("quantity"));
				item6.setValue(record.getAttribute("m_UnitOfMeasureCode"));
				item7.setValue(record.getAttribute("remarkText"));
				service.getPartInfoByPartNumber(event.getRecord().getAttribute(
						"partNumber"), new AsyncCallback<PartInfoVO>() {
					@Override
					public void onFailure(Throwable arg0) {
						SC.say(ui.getB().msgFailure(""));
					}

					@Override
					public void onSuccess(PartInfoVO partVo) {
						item4.setValue(partVo.getKeyword());// 关键字
						item3.setValue(partVo.getAtaNumber());// ATA
					}
				});
			}
		});
		h1.addMember(lbl1);
		h1.addMember(list);

		VLayout h2 = new VLayout();
		Label lbl2 = new Label(ui.getB().procurementPlanItems());
		lbl2.setHeight(10);

		Utils.setListGridHeight(listItem);
		listItem.setDataSource(ms.procurementPlanItemListGrid_ds);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", ms.procurementPlanListGrid
				.getSelectedRecord().getAttribute("id"));
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		listItem.fetchData(criteria);
		listItem.buildListGrid();
		listItem.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				itemLdf.reset();
				itemLdf.editSelectedData(listItem);
			}
		});
		h2.addMember(lbl2);
		h2.addMember(listItem);
		h.setMembers(h1, h2);
		return h;
	}

	/** 加载采购计划明细 **/
	private Canvas buildForm() {

		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getB().procurementPlanItems());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();

		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(4);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);
		final TextItem item0 = new TextItem();

		itemLdf.setDataSource(ms.procurementPlanItemListGrid_ds);

		item0.setName("procurementPlan.id");
		item0.setVisible(false);
		// 件号
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item1.addChangedHandler(new ChangedHandler() {
					public void onChanged(ChangedEvent event) {
						PartInfoVO infoVO = Utils.getPartInfoVO(item1);
						item3.setValue(infoVO.getAtaNumber());
						item4.setValue(infoVO.getKeyword());
						item44.setValue(infoVO.getManufacturerCodeId());
					}
				});
			}
		});
		// 备件技术目录
		FormItemIcon fi1 = new FormItemIcon();
		// 采购分析数据
		FormItemIcon fi2 = new FormItemIcon();
		item1.setIcons(fi1, fi2);
		// 需求日期
		item2 = new DateItem("deliveryDate");
		item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item2.setUseTextField(true);
		// ATA章节号
		item3 = new TextItem("ata");
		item3.setDisabled(true);
		// 关键字
		item4 = new TextItem("itemKeyword");
		item4.setDisabled(true);
		// 制造商
		item44 = new SelectItem();
		item44.setTitle(ui.getB().m_ManufacturerCode());
		item44.setName("m_ManufacturerCode.id");
		item44.setDisabled(true);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item44);
		// 计划采购数量
		item5 = new SpinnerItem();
		item5.setName("plannedQuantity");
		item5.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item9.setValue(Integer.parseInt(item5.getValue().toString())
						* Integer.parseInt(item8.getValue().toString()));
			}
		});
		// 单位
		item6 = new DicSelectItem();
		item6.setName("m_UnitOfMeasureCode");
		// 备注
		item7 = new TextAreaItem("remarkText");
		item7.setTitleVAlign(VerticalAlignment.TOP);
		item7.setRowSpan(3);
		item7.setHeight("100%");
		// 计划采购单价
		item8 = new SpinnerItem();
		item8.setName("plannedUnitPrice");
		item8.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				item9.setValue(Integer.parseInt(item5.getValue().toString())
						* Integer.parseInt(item8.getValue().toString()));
			}
		});
		// 计划采购金额
		item9 = new SpinnerItem();
		item9.setName("unitPriceAmount");
		item9.setDisabled(true);
		// 币种
		item10 = new DicSelectItem();
		item10.setName("m_InternationalCurrencyCode");

		itemLdf.setFields(item0, item1, item3, item4, item44, item2, item5,
				item6, item8, item7, item9, item10);

		v.addMember(itemLdf);

		itemBtnsLayout = new BtnsHLayout();
		// 构建操作按钮
		IButton saveBtn = new IButton(ui.getB().btnSaveItem());
		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				item0.setValue(ms.Pid);

				itemLdf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						Criteria criteria = new Criteria();
						criteria.addCriteria("id", ms.Pid);
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						listItem.fetchData(criteria, new DSCallback() {

							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								listItem.buildListGrid();
								business
										.refeshListGrid(ms.procurementPlanListGrid);
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
