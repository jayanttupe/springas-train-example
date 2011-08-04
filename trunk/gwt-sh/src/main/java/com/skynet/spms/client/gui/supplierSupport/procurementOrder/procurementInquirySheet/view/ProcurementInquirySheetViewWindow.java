package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.DicKey;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.model.ProcurementInquiryModel;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
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
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 主订单 与 明细 修改页面
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementInquirySheetViewWindow extends BaseWindow {

	private static I18n ui = new I18n();
	
	private ProcurementInquiryModel modelLocator;

	private ListGrid[] listGrids = null;

	private LayoutDynamicForm ldf;// 主订单From

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮容器

	private IButton saveItemBtn;

	private String primaryKey;// 主表id

	private FilterListGrid procurementOrderLG;// 采购订单选择Grid

	private FilterListGrid itemListGrid;// 新采购询价单Grid

	private FormItem item1;
	private SelectItem item2;
	private FormItem item3;
	private FormItem item4;
	private DateItem item5;
	private SelectItem item6;
	private FormItem item7;
	private SelectItem item8;
	private FormItem item9;
	private FormItem item10;
	private FormItem item11;
	private FormItem item12;
	private DicSelectItem item13;

	String supplierIds;
	private SelectItem supplierCodeItem;

	private static BaseCodeServiceAsync service = GWT
			.create(BaseCodeService.class);

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ProcurementInquirySheetViewWindow(String windowTitle,
			boolean isMax, Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		modelLocator = ProcurementInquiryModel.getInstance();
		primaryKey = modelLocator.listGrid.getSelectedRecord().getAttribute(
				"id");
		//采购询价单信息
		this.setTitle(ui.getM().viewFormTitle(ui.getM().mod_procurementInquiry_name()));
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		/** 面板1* */
		VLayout firstView = getPrimaryView();
		firstView.setHeight(150);
		firstView.setLayoutTopMargin(5);
		vmain.addMember(firstView);

		/** 面板2* */
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3* */
		VLayout threeView = getItemView();
		threeView.setHeight(180);
		threeView.setLayoutTopMargin(10);
		vmain.addMember(threeView);

		return vmain;
	}

	public void disableBtn() {
		this.btnsLayout.disable();
	}

	// 布局一
	private VLayout getPrimaryView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		//采购询价单
		v.setGroupTitle(ui.getM().mod_procurementInquiry_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		ldf.setDataSource(modelLocator.dataSource);
		ldf.editSelectedData(modelLocator.listGrid);

		ListGridRecord selectLGR = modelLocator.listGrid.getSelectedRecord();
		supplierIds = selectLGR.getAttribute("m_supplier");

		// 询价单编号
		TextItem item_1 = new TextItem();
		item_1.setName("inquirySheetNumber");
		item_1.setValue(selectLGR.getAttribute("inquirySheetNumber"));

		// 采购指令号
		TextItem item_2 = new TextItem();
		item_2.setName("orderNumber");
		FormItemIcon fii2 = new FormItemIcon();
		item_2.setIcons(fii2);
		item_2.setValue(selectLGR.getAttribute("orderNumber"));

		// 机尾号
		final SelectItem item_8 = new SelectItem();
		item_8.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item_8);

		// 优先级
		DicSelectItem item_3 = new DicSelectItem();
		item_3.setName("m_Priority");
		item_3.setValue(selectLGR.getAttribute("m_Priority"));
		item_3.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("AOG")) {
					item_8.setRequired(true);
				} else {
					item_8.setRequired(false);
				}
			}
		});

		//发送供应商
		supplierCodeItem = new SelectItem();
		supplierCodeItem.setName("m_supplier");
		supplierCodeItem.setValue(selectLGR.getAttribute("m_supplier"));
		supplierCodeItem.setMultiple(true);
		CodeRPCTool.bindData(CodeRPCTool.SUPPLIERCODE, supplierCodeItem);

		// 询价联系人
		TextItem item_5 = new TextItem();
		item_5.setName("linkman");
		item_5.setValue(selectLGR.getAttribute("linkman"));

		

		// 询价联系方式
		TextAreaItem item_7 = new TextAreaItem();
		item_7.setName("contactInformation");
		item_7.setTitleVAlign(VerticalAlignment.TOP);
		item_7.setValue(selectLGR.getAttribute("contactInformation"));
		
		// 备注
		TextAreaItem item_6 = new TextAreaItem();
		item_6.setName("remark");
		item_6.setTitleVAlign(VerticalAlignment.TOP);
		item_6.setColSpan(3);
		item_6.setValue(selectLGR.getAttribute("remark"));

		ldf.setFields(item_1, item_2, supplierCodeItem, item_3,
				item_8, item_5, item_7, item_6);
		
		Utils.setReadOnlyForm(ldf);

		v.setMembers(ldf);


		return v;
	}

	// 布局2
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);


		VLayout rightLayout = new VLayout();
		//采购询价明细列表
		Label rightLb = new Label(ui.getM().msgListTitle(ui.getM().mod_procurementInquiry_item_name()));
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		h.addMember(rightLayout);
		return h;
	}

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_procurementInquiry_item_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setNumCols(4);
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("procurementInquirySheet.id");

		itemLdf.setDataSource(modelLocator.itemDataSource);

		// 件号
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				PartInfoVO partVo = Utils.getPartInfoVO(item1);
				item2.setValue(partVo.getManufacturerCodeId());// 制造商
				item4.setValue(partVo.getKeyword());// 关键字
				item3.setValue(partVo.getAtaNumber());// ATA
				item8.setValue(partVo.getUnitOfMeasureCode());// 单位
			}
		});

		// 制造商
		item2 = new SelectItem();
		item2.setName("m_ManufacturerCode.id");
		item2.setDisplayField("code");
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);

		// ATA章节号
		item3 = new TextItem();
		item3.setName("ata");

		item4 = new TextItem();
		item4.setName("item4");
		item4.setVisible(false);

		// 需求日期
		item5 = new DateItem();
		item5.setName("partRequireDate");
		item5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item5.setUseTextField(true);

		// 随件资料
		item6 = new SelectItem();
		item6.setMultiple(true);
		item6.setName("m_CertificateType");
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, item6);
		// String certificateType=item6.getValueAsString();
		// item6.setValues(certificateType.split(","));

		// 询价数量
		item7 = new TextItem();
		item7.setName("quantity");

		// 单位
		item8 = new DicSelectItem();
		item8.setName("m_UnitOfMeasureCode");

		// 指定交货地点
		item9 = new TextItem();
		item9.setName("deliveryAddress");

		// 备注
		item10 = new TextAreaItem();
		item10.setName("remark");
		item10.setRowSpan(3);
		item10.setHeight("100%");
		item10.setTitleVAlign(VerticalAlignment.TOP);

		itemLdf.setFields(primaryKeyItem, item1, item2, item3, item4, item5,
				item6, item7, item8, item9, item10);
		
		Utils.setReadOnlyForm(itemLdf);

		v.addMember(itemLdf);
		itemBtnsLayout = new BtnsHLayout();
		itemBtnsLayout.setVisible(false);
		// 构建操作按钮
		saveItemBtn = new IButton(ui.getM().btnSaveItem());
		saveItemBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// if (procurementOrderLG.getSelectedRecord() != null
				// || itemListGrid.getSelectedRecord() != null
				// || item1.getValue() != null) {
				primaryKeyItem.setValue(primaryKey);
				if (itemLdf.validate()) {
					itemLdf.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {

							itemLdf.clearValues();
							itemLdf.editNewRecord();
							Utils.makeAllNotSelectLG(procurementOrderLG);
							Utils.makeAllNotSelectLG(itemListGrid);
							refreshItemListGrid();
						}
					});
				}
				// } else {
				// SC.say("请先选择采购指令明细项");
				// }
			}
		});
		// 清空按钮
		IButton cleanBtn = new IButton(ui.getM().btnClean());
		cleanBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				itemLdf.editNewRecord();
			}

		});

		//关闭
		IButton closeBtn = new IButton(ui.getM().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		itemBtnsLayout.setMembers(saveItemBtn, cleanBtn, closeBtn);
		v.addMember(itemBtnsLayout);
		return v;
	}


	// 新调拨单明细项
	private ListGrid getRightGrid() {
		itemListGrid = new FilterListGrid();
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(modelLocator.itemDataSource);
		refreshItemListGrid();

		// 件号
		ListGridField field2 = new ListGridField("partNumber");

		// 需求日期
		ListGridField field4 = new ListGridField("partRequireDate");

		// 询价数量
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);

		itemListGrid.setFields(field2, field4, field5);

		Utils.makeAllCanFilter(itemListGrid);

		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();
				// 将From定义为修改模式(连接服务器端时修改掉 ListGrid)
				itemLdf.editSelectedData(itemListGrid);
				// 报价单单Grid都不选中
				Utils.makeAllNotSelectLG(procurementOrderLG);

				//多选下拉菜单赋值
				Utils.setMultipleFormItemValue(item6);//随件资料
				
				service.getPartInfoByPartNumber(event.getRecord().getAttribute(
						"partNumber"), new AsyncCallback<PartInfoVO>() {

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						SC.say(arg0.getMessage());
					}

					@Override
					public void onSuccess(PartInfoVO partInfoVO) {
						// TODO Auto-generated method stub

						item3.setValue(partInfoVO.getAtaNumber());
					}
				});

			}
		});
		return itemListGrid;
	}

	/**
	 * 单击Grid 给Form 赋值
	 * 
	 * @param lgRecord
	 */
	private void setFormItemValues(ListGridRecord lgRecord) {
		ListGridRecord lgr = lgRecord;
		// 单击询价单明细选择
		item1.setValue(lgr.getAttribute("partNumber"));
		item2.setValue(lgr.getAttribute("m_ManufacturerCode.id"));
		item3.setValue(lgr.getAttribute("ata"));
		item4.setValue(lgr.getAttribute("itemKeyword"));
		item5.setValue(lgr.getAttributeAsDate("deliveryDate"));
		item7.setValue(lgr.getAttribute("plannedQuantity"));

		item8.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));
		item11.setValue(lgr.getAttribute("plannedUnitPrice"));
		item12.setValue(lgr.getAttribute("amount"));
		item13.setValue(lgr.getAttribute("m_InternationalCurrencyCode"));

	}

	/**
	 * 刷新listGrid
	 */
	private void refreshItemListGrid() {
		if (primaryKey == null) {
			primaryKey = "-1";
		}
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
	}

}
