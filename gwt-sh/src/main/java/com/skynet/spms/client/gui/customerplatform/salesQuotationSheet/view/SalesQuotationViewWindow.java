package com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.view;

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
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.add.SalesPiecewiseQuotationWindow;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.business.SalesQuotationBusiness;

/**
 * 采购报价单修改
 * 
 * @author Tony FANG
 * 
 */
public class SalesQuotationViewWindow extends BaseWindow {
	private static I18n ui = new I18n();
	
	private MainModelLocator modelLocator;

	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private IButton saveItemBtn;// 明细操作按钮

	private FilterListGrid itemListGrid;// 明细Grid

	private FilterListGrid inquiryLG;// 左侧询价单Grid

	private String primaryKey;// 主表id

	private String itemId = "";// 明细id

	private FormItem item1;
	private DateItem item2;
	private FormItem item3;
	private DicSelectItem item4;
	private SelectItem item5;
	private FormItem item6;
	private FormItem item7;
	private FormItem item8;
	private SpinnerItem item9;
	private FormItem item10;
	private DicSelectItem item11;
	private FormItem item12;
	private FormItem item13;
	private DicSelectItem item14;
	private FormItem item15;
	private FormItem item16;
	private FormItem item17;
	private FormItem item18;
	private FormItem item19;
	private FormItem item20;
	private FormItem item21;
	private FormItem itemNumber;
	private FormItem keywordItem;//关键字
	private FormItem ataItem;//ATA章节号
	private SelectItem manufacturerItem;//制造商

	private static BaseCodeServiceAsync service = GWT
	.create(BaseCodeService.class);
	
	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SalesQuotationViewWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}


	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		modelLocator = MainModelLocator.getInstance();
		primaryKey = modelLocator.primaryKey;
		this.setTitle(ui.getB().viewFormTitle(ui.getM().mod_salesQuotationSheet_name()));
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setMembersMargin(2);

		/** 面板1* */
		VLayout oneView = getPrimaryView();
		oneView.setHeight("35%");
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3* */
		VLayout threeView = getItemView();
		threeView.setLayoutTopMargin(10);
		threeView.setHeight("40%");
		threeView.setWidth100();
		vmain.addMember(threeView);

		return vmain;
	}

	public void disableBtn() {
		this.btnsLayout.disable();
	}

	/** 布局一* */
	private VLayout getPrimaryView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_salesQuotationSheet_name());//采购报价单
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET,
				DSKey.C_SALESQUOTATIONSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// Form指定数据源
						ldf.setDataSource(dataSource);
						// 根据id去获得信息
						Criteria criteria = new Criteria();
						criteria.setAttribute("id", primaryKey);
						ldf.fetchData(criteria);


						TextItem inquiryIdItem = new TextItem();
						inquiryIdItem.setName("salesInquirySheet.id");
						inquiryIdItem.setVisible(false);

						// 询价编号
						TextItem item_1 = new TextItem();
						item_1.setName("salesInquirySheet.inquirySheetNumber");

						// 报价编号
						TextItem item_2 = new TextItem();
						item_2.setName("quotationSheetNumber");

						// 有效日期
						DateItem item_3 = new DateItem();
						item_3.setName("priceEffectiveDate");
						item_3.setUseTextField(true);
						item_3
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

						// 询价客户名称
						SelectItem item_5 = new SelectItem();

						item_5.setName("m_CustomerIdentificationCode.id");
						CodeRPCTool.bindData(
								CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item_5);

						// 报价人
						TextItem item_6 = new TextItem();
						item_6.setName("createBy");

						// 备注
						TextAreaItem item_7 = new TextAreaItem();
						item_7.setName("quotationRemark");
						item_7.setRowSpan(3);
						item_7.setHeight("100%");
						item_7.setTitleVAlign(VerticalAlignment.TOP);

						// 询价联系人
						TextItem item_8 = new TextItem();
						item_8.setName("salesInquirySheet.linkman");

						// 报价联系人
						TextItem item_9 = new TextItem();
						item_9.setName("linkman");

						// 询价联系方式
						TextItem item_10 = new TextItem();
						item_10.setName("salesInquirySheet.contactInformation");


						// 报价联系方式
						TextItem item_11 = new TextItem();
						item_11.setName("contactInformation");

						
						ldf.setFields(inquiryIdItem, item_1, item_2, item_3,
								item_5, item_6, item_7, item_8, item_9,
								item_10, item_11);
						
						Utils.setReadOnlyForm(ldf);//设置Item不可编辑
					}
				});
		v.setMembers(ldf);
		/** 订单操作按钮* */
		return v;
	}

	/** 布局二* */
	private HLayout getShowGridView() {
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(5);
		h.setLayoutTopMargin(3);

		VLayout leftLayout = new VLayout();
		leftLayout.setWidth("35%");
		leftLayout.setHeight100();
		Label leftLb = new Label(ui.getM().msgListTitle(ui.getM().mod_salesQuotationSheet_item_name()));//询价单明细列表
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		rightLayout.setWidth("65%");
		Label rightLb = new Label(ui.getM().msgListTitle(ui.getM().mod_salesQuotationSheet_item_name()));//报价单明细列表
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		//h.addMember(leftLayout);
		h.addMember(rightLayout);
		return h;
	}

	/** 布局3(明细添加)* */
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_salesQuotationSheet_item_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("salesQuotationSheet.id");

		itemLdf = new LayoutDynamicForm();
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(4);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		// 指定明细Form数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET_ITEM,
				DSKey.C_SALESQUOTATIONSHEET_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						
						itemLdf.setDataSource(dataSource);
						
						// 询价件号
						item1 = Utils.getPartNumberList();
						item1.setName("salesInquirySheetItem.partNumber");

						// 需求日期
						item2 = new DateItem();
						item2.setName("partRequireDate");
						item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
						item2.setUseTextField(true);

						// 指定交货地点
						item3 = new TextItem();
						item3.setName("salesInquirySheetItem.deliveryAddress");

						// 询价数量
						item21 = new SpinnerItem();
						item21.setName("salesInquirySheetItem.quantity");

						// 优先级
						item4 = new DicSelectItem();
						item4.setName("m_Priority");

						// 机尾号
						item5 = new SelectItem();
						item5.setName("airIdentificationNumber");
						Utils.setAirIdentificationNumberItemDS(item5);

						// 报价件号
						item6 = Utils.getPartNumberList();
						item6.setName("quotationPartNumber");
						FormItemIcon fi6 = new FormItemIcon();
						item6.setIcons(fi6);
						
						//关键字
						keywordItem = new TextItem();
						keywordItem.setName("keyword");
						
						//ATA
						ataItem= new TextItem();
						ataItem.setName("ata");
						
						// 制造商
						manufacturerItem = new SelectItem();
						manufacturerItem.setName("m_ManufacturerCode.id");
						manufacturerItem.setTitle(ui.getM().title_manufacturer());
						manufacturerItem.setWidth(50);
						CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, manufacturerItem);

						// 交货期(天)
						item7 = new TextItem();
						item7.setName("deliveryLeadTime");

						// 互换性
						item8 = new TextItem();
						item8.setName("item8");
						item8.setVisible(false);

						// 报价数量
						item9 = new SpinnerItem();
						item9.setName("quantity");
						item9.addChangedHandler(new ChangedHandler() {
							public void onChanged(ChangedEvent event) {
								alculationAmount();
							}
						});

						// 特殊包装说明
						item10 = new TextItem();
						item10.setName("packageDescription");

						// 单位
						item11 = new DicSelectItem();
						item11.setName("m_UnitOfMeasureCode");
						item11.setValueMap(DicKey.UNITOFMEASURECODE);

						// 特殊包装费用
						item12 = new SpinnerItem();
						item12.setName("packagePrice");
						item12.addChangedHandler(new ChangedHandler() {
							public void onChanged(ChangedEvent event) {
								alculationAmount();
							}
						});

						// 标准包装数量
						item13 = new SpinnerItem();
						item13.setName("standardPackageQuantity");

						// 币种
						item14 = new DicSelectItem();
						item14.setName("m_InternationalCurrencyCode");

						// 最小销售数量
						item15 = new SpinnerItem();
						item15.setName("minimumSalesQuantity");

						// 报价单价
						item16 = new SpinnerItem();
						item16.setName("unitPriceAmount");
						item16.addChangedHandler(new ChangedHandler() {
							public void onChanged(ChangedEvent event) {
								alculationAmount();
							}
						});

						// 付款要求
						item17 = new TextAreaItem();
						item17.setName("paymentRequirement");
						item17.setRowSpan(2);
						item17.setHeight("100%");
						item17.setTitleVAlign(VerticalAlignment.TOP);

						// 报价金额
						item18 = new SpinnerItem();
						item18.setName("amount");

						// 分段报价
						item19 = new CheckboxItem();
						item19.setName("isBreakPrice");
						item19.setAlign(Alignment.RIGHT);
						FormItemIcon fi19 = new FormItemIcon();
						fi19.setPrompt(ui.getM().mod_salesQuotationSheet_fdbtn_title());
						fi19.addFormItemClickHandler(new FormItemClickHandler() {

							public void onFormItemClick(FormItemIconClickEvent event) {
								if (!itemId.equals("")) {
									SalesPiecewiseQuotationViewWindow win = new SalesPiecewiseQuotationViewWindow(
											ui.getM().mod_salesQuotationSheet_fdbtn_title(), false, null, null, null);
									win.setQuotationItemId(itemId);
									ShowWindowTools.showWindow(item19.getPageRect(), win, 200);
								} else {
									SC.say(ui.getB().msgFirstSaveItem());//请先保存明细
								}
							}

						});
						item19.setIcons(fi19);

						// 备注
						item20 = new TextAreaItem();
						item20.setName("remark");
						item20.setHeight(50);
						item20.setTitleVAlign(VerticalAlignment.TOP);

						//项号
						itemNumber = new TextItem();
						itemNumber.setName("itemNumber");
						itemNumber.setVisible(false);

						
						itemLdf.setFields(primaryKeyItem, item1, item2, item3, item21, item4,
								item5, item6,keywordItem,ataItem,manufacturerItem, item7, item8, item9, item10, item11, item12,
								item13, item14, item15, item16, item17, item18, item19, item20,
								itemNumber);
						
						Utils.setReadOnlyForm(itemLdf);//设置Item不可编辑
					}
				});
		
		v.addMember(itemLdf);

		/** 操作按钮* */

		return v;
	}

	// 询价单选择Grid
	private ListGrid getLeftGrid() {
		inquiryLG = new FilterListGrid();
		Utils.setListGridHeight(inquiryLG);
		// 初始化报价单明细数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_DOQUOTATION_ITEM,
				DSKey.C_DOQUOTATION_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						// 取到报价单id
						String id = modelLocator.listGrid.getSelectedRecord()
								.getAttribute("salesInquirySheet.id");
						if (id != null) {
							inquiryLG.setDataSource(dataSource);
							// 获得报价单单id，查找对应明细项
							Criteria criteria = new Criteria();
							criteria.setAttribute("id", id);
							criteria.setAttribute("_r", String.valueOf(Math
									.random()));
							inquiryLG.fetchData(criteria);

							//询价件号
							ListGridField field2 = new ListGridField(
									"partNumber");
							
							//关键字
							ListGridField keywordField = new ListGridField("keyword");
							keywordField.setPrompt(field2.getAttribute("keyword"));

							//询价数量
							ListGridField field3 = new ListGridField("quantity");
							Utils.formatQuantityField(field3);

							//需求日期
							ListGridField field4 = new ListGridField(
									"partRequireDate");
							field4
									.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
							inquiryLG.setFields(field2,keywordField, field3, field4);
						}

					}
				});

		// 选中行事件
		inquiryLG.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();
				itemLdf.editNewRecord();
				// 再次给Form赋值
				setFormItemValues(event.getRecord());

				// 新报价单Grid都不选中
				Utils.makeAllNotSelectLG(itemListGrid);

				itemId = "";// 清空明细Id

				itemNumber.setValue(itemListGrid.getRecords().length + 1);
			}

		});

		// 使Grid所有列可筛选
		Utils.makeAllCanFilter(inquiryLG);

		return inquiryLG;
	}

	// 新报价单明细
	private ListGrid getRightGrid() {
		itemListGrid = new FilterListGrid();
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(modelLocator.itemDataSource);

		//询价件号
		ListGridField field2 = new ListGridField(
				"salesInquirySheetItem.partNumber");
		// 报价件号
		ListGridField field3 = new ListGridField("quotationPartNumber");
		
		//关键字
		ListGridField keywordField = new ListGridField("keyword");
		keywordField.setPrompt(field2.getAttribute("keyword"));
		
		// 交货期(天)
		ListGridField field4 = new ListGridField("deliveryLeadTime");
		// 报价数量
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);
		// 报价单价
		ListGridField field6 = new ListGridField("unitPriceAmount");
		Utils.formatPriceField(field6);
		// 报价金额
		ListGridField field7 = new ListGridField("amount");
		Utils.formatPriceField(field7);

		itemListGrid.setFields(field2, field3,keywordField, field4, field5, field6, field7);

		// 使Grid所有列可筛选
		Utils.makeAllCanFilter(itemListGrid);

		// 加载数据
		refreshItemListGrid();

		// 选中行事件
		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				// 将From定义为修改模式(连接服务器端时修改掉 ListGrid)
				itemLdf.editSelectedData(itemListGrid);

				// 报价单单Grid都不选中
				Utils.makeAllNotSelectLG(inquiryLG);

				itemId = itemListGrid.getSelectedRecord().getAttribute("id");
				

				service.getPartInfoByPartNumber( event.getRecord().getAttribute("quotationPartNumber"),
						new AsyncCallback<PartInfoVO>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub
								SC.say(arg0.getMessage());
							}

							@Override
							public void onSuccess(PartInfoVO partVo) {
								// TODO Auto-generated method stub
								manufacturerItem.setValue(partVo.getManufacturerCodeId());//制造商
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
		item2.setValue(lgr.getAttributeAsDate("partRequireDate"));
		item3.setValue(lgr.getAttribute("deliveryAddress"));
		item4.setValue(lgr.getAttribute("m_Priority"));
		item5.setValue(lgr.getAttribute("airIdentificationNumber"));
		item6.setValue(lgr.getAttribute("partNumber"));
		keywordItem.setValue(lgr.getAttribute("keyword"));// 关键字
		ataItem.setValue(lgr.getAttribute("ata"));// ATA章节号
		manufacturerItem.setValue(lgr.getAttribute("m_ManufacturerCode.id"));// 制造商
		item9.setValue(lgr.getAttribute("quantity"));
		item11.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));
		item14.setValue(lgr.getAttribute("m_InternationalCurrencyCode"));
		item21.setValue(lgr.getAttribute("quantity"));
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

	/***************************************************************************
	 * 计算金额
	 */
	private void alculationAmount() {
		float totalAmount = 0.00f;
		if (item9.getValue() != null && item16.getValue() != null) {

			totalAmount = Float.parseFloat(item9.getValue().toString())
					* Float.parseFloat(item16.getValue().toString());// 数量*单价
			if (item12.getValue() != null) {
				totalAmount += Float.parseFloat(item12.getValue().toString());// 特殊包装费用
			}

		}
		item18.setValue(totalAmount);

	}

}
