package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.add;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.model.ProcurementInquiryModel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.DateItemSelectorFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
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

/*******************************************************************************
 * 新建采购报价单
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementQuotationSheetAddWindow extends BaseWindow {

	private static I18n ui = new I18n();
	
	private ProcurementInquiryModel procurementInquiryModel;// 采购询价单mode

	private ListGrid[] listGrids = null;

	private LayoutDynamicForm ldf;// 主订单From

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private IButton saveItemBtn;// 明细操作按钮

	private FilterListGrid itemListGrid;// 明细Grid

	private FilterListGrid inquiryLG;// 左侧询价单Grid

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private String primaryKey;// 主表id

	private String itemId = "";// 明细id

	private FormItem item1;
	private DateItem item2;
	private FormItem item3;
	private FormItem item4;
	private FormItem item5;
	private FormItem item6;
	private FormItem item7;
	private FormItem item8;
	private FormItem item9;
	private FormItem item10;
	private FormItem item11;
	private DicSelectItem item12;
	private FormItem item13;
	private FormItem item14;
	private DicSelectItem item15;
	private FormItem item16;
	private FormItem item17;
	private FormItem item18;
	private FormItem item19;
	private FormItem item20;
	private FormItem item21;
	private FormItem inquiryItemId;

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ProcurementQuotationSheetAddWindow(String windowTitle,
			boolean isMax, Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		procurementInquiryModel = ProcurementInquiryModel.getInstance();

		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		/** 面板1* */
		VLayout firstView = getPrimaryView();
		firstView.setLayoutTopMargin(5);
		vmain.addMember(firstView);

		/** 面板2* */
		HLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3* */
		VLayout threeView = getItemView();
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
		v.setGroupTitle("采购报价单");
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();

		ldf.setDataSource(procurementInquiryModel.quotationDS);

		ListGridRecord selectLGE = procurementInquiryModel.listGrid
				.getSelectedRecord();

		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		TextItem inquiryIdItem = new TextItem();
		inquiryIdItem.setName("procurementInquirySheet.id");
		inquiryIdItem.setValue(selectLGE.getAttribute("id"));
		inquiryIdItem.setVisible(false);

		// 询价编号
		TextItem item_1 = new TextItem(
				"procurementInquirySheet.inquirySheetNumber");
		item_1.setValue(selectLGE.getAttribute("inquirySheetNumber"));
		item_1.setDisabled(true);

		// 报价编号
		TextItem item_2 = new TextItem("quotationSheetNumber");
		item_2.setDisabled(true);
		item_2.setValue("系统自动生成");

		// 有效日期
		DateItem item_3 = new DateItem("priceEffectiveDate");
		item_3.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item_3.setUseTextField(true);

		// 询价人员
		TextItem item_4 = new TextItem("procurementInquirySheet.createBy");
		item_4.setValue(selectLGE.getAttribute("createBy"));
		item_4.setDisabled(true);

		//报价联系人
		final TextItem item_8 = new TextItem("linkman");
		item_8.setDefaultValue(UserTools.getCurrentUserName());

		//报价联系方式
		final TextItem item_10 = new TextItem("contactInformation");

		//供应商
		final DicSelectItem item_5 = new DicSelectItem();
		item_5.setName("supplierCode.id");
		CodeRPCTool.bindSuppliercodeDataByIds(selectLGE
				.getAttribute("m_supplier"), item_5);
		FormItemIcon fIcon5 = new FormItemIcon();
		item_5.setIcons(fIcon5);

		item_5.addChangedHandler(new ChangedHandler() {

			public void onChanged(ChangedEvent event) {
				CodeRPCTool.bindSuppliercodeData(item_5.getValue() + "",
						item_8, item_10);
			}
		});

		//备注
		TextAreaItem item_6 = new TextAreaItem("quotationRemark");
		item_6.setTitleVAlign(VerticalAlignment.TOP);
		item_6.setRowSpan(3);
		item_6.setHeight("100%");

		//询价联系人
		TextItem item_7 = new TextItem("procurementInquirySheet.linkman"
				);
		item_7.setDisabled(true);
		item_7.setValue(selectLGE.getAttribute("linkman"));

		TextItem item_9 = new TextItem(
				"procurementInquirySheet.contactInformation", "询价联系方式");
		item_9.setDisabled(true);
		item_9.setValue(selectLGE.getAttribute("contactInformation"));

		// 加载供应商数据
		CodeRPCTool.bindSuppliercodeData(item_5.getValue() + "", item_8,
				item_10);
		ldf.setFields(inquiryIdItem, item_1, item_2, item_3, item_4, item_5,
				item_6, item_7, item_8, item_9, item_10);

		v.setMembers(ldf);

		btnsLayout = new BtnsHLayout();
		// 构建操作按钮
		final IButton saveBtn = new IButton("保存");
		final IButton cancelBtn = new IButton("关闭");
		// 仅保存
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("保存成功!");
						saveItemBtn.setDisabled(false);
						primaryKey = response.getData()[0].getAttribute("id");
					}
				});
			}

		});

		// 关闭
		cancelBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
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
		Label leftLb = new Label("询价单明细");
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label("报价单明细");
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		h.addMember(leftLayout);
		h.addMember(rightLayout);
		return h;
	}

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle("报价单明细");
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setNumCols(4);
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setCellPadding(2);

		itemLdf.setDataSource(procurementInquiryModel.quotationItemDS);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("procurementQuotationSheetRecord.id");

		// 询价明细外键
		inquiryItemId = new TextItem();
		inquiryItemId.setName("procurementInquirySheetItem.id");
		inquiryItemId.setVisible(false);

		//询价件号
		item1 = Utils.getPartNumberList();
		item1.setName("procurementInquirySheetItem.partNumber");
		item1.setDisabled(true);

		//需求日期
		item2 = new DateItem();
		item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item2.setUseTextField(true);
		item2.setName("procurementInquirySheetItem.partRequireDate");

		item2.setDisabled(true);

		//指定交货地点
		item3 = new TextItem();
		item3.setName("procurementInquirySheetItem.deliveryAddress");
		item3.setDisabled(true);

		//询价数量
		item4 = new TextItem();
		item4.setName("procurementInquirySheetItem.quantity");
		item4.setDefaultValue(0);
		item4.setValue("20 EA");
		item4.setDisabled(true);

		// item5 = new TextItem();
		// item5.setName("procurementInquirySheetItem.planUnitPrice");
		// item5.setTitle("计划采购单价");
		// item5.setDefaultValue(0.00f);
		// item5.setDisabled(true);
		//
		// item6 = new TextItem();
		// item6.setTitle("计划采购金额");
		// item6.setName("procurementInquirySheetItem.planAmount");
		// item6.setDefaultValue(0.00f);
		// item6.setDisabled(true);

		//报价件号
		item7 = Utils.getPartNumberListByName("quotationPartNumber");
		item7.setName("quotationPartNumber");

		//交货期(天)
		item8 = new TextItem();
		item8.setName("deliveryLeadTime");

		
		//互换性
		item9 = new TextItem();
		item9.setName("item9");
		item9.setVisible(false);

		//报价数量
		item10 = new SpinnerItem();
		item10.setName("quantity");
		item10.setDefaultValue(0.00f);
		item10.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		//特殊包装说明
		item11 = new TextItem();
		item11.setName("packageDescription");

		//单位
		item12 = new DicSelectItem();
		item12.setName("m_UnitOfMeasureCode");

		//特殊包装费用
		item13 = new SpinnerItem();
		item13.setName("packagePrice");
		item13.setDefaultValue(0.00f);
		item13.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		//标准包装数量
		item14 = new SpinnerItem();
		item14.setDefaultValue(0);
		item14.setName("standardPackageQuantity");

		//币种
		item15 = new DicSelectItem();
		item15.setName("m_InternationalCurrencyCode");

		//最小销售数量
		item16 = new SpinnerItem();
		item16.setName("minimumSalesQuantity");
		item16.setDefaultValue(0);

		// 报价单价
		item17 = new SpinnerItem();
		item17.setName("unitPriceAmount");
		item17.setDefaultValue(0.00f);
		item17.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		// 付款要求
		item18 = new TextAreaItem();
		item18.setName("paymentRequirement");
		item18.setRowSpan(2);
		item18.setHeight("100%");
		item18.setTitleVAlign(VerticalAlignment.TOP);

		// 报价总价
		item19 = new TextItem();
		item19.setName("amount");
		item19.setDefaultValue(0.00f);
		item19.setDisabled(true);

		// 分段报价
		item20 = new CheckboxItem();
		item20.setName("isBreakPrice");
		item20.setAlign(Alignment.RIGHT);
		FormItemIcon fi19 = new FormItemIcon();
		fi19.setPrompt("分段报价");
		fi19.addFormItemClickHandler(new FormItemClickHandler() {

			public void onFormItemClick(FormItemIconClickEvent event) {
				if (!itemId.equals("")) {
					ProcurementPiecewiseQuotationWindow win = new ProcurementPiecewiseQuotationWindow(
							"分段报价", false, null, null, null);
					win.setQuotationItemId(itemId);
					win.fromLG = itemListGrid;
					win.fromPrimaryKey = primaryKey;
					win.fromFrom = itemLdf;
					ShowWindowTools.showWindow(item20.getPageRect(), win, 200);
				} else {
					SC.say("请先保存明细");
				}
			}

		});
		item20.setIcons(fi19);

		// 备注
		item21 = new TextAreaItem();
		item21.setName("remark");
		item21.setHeight(50);
		item21.setTitleVAlign(VerticalAlignment.TOP);

		itemLdf.setFields(primaryKeyItem, inquiryItemId, item1, item2, item3,
				item4, item7, item8, item9, item10, item11, item12, item13,
				item14, item15, item16, item17, item18, item19, item20, item21);

		v.addMember(itemLdf);

		itemBtnsLayout = new BtnsHLayout();
		// 构建操作按钮
		saveItemBtn = new IButton("保存明细");
		saveItemBtn.setDisabled(true);
		saveItemBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (inquiryLG.getSelectedRecord() != null
						|| itemListGrid.getSelectedRecord() != null) {
					primaryKeyItem.setValue(primaryKey);
					if (itemLdf.validate()) {
						itemLdf.saveData(new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {

								Utils.makeAllNotSelectLG(inquiryLG);

								refreshItemListGrid();

								itemId = response.getData()[0]
										.getAttribute("id");

							}
						});
					}
				} else {
					SC.say("请先选择询价单明细项");
				}

			}

		});
		// 清空按钮
		IButton cleanBtn = new IButton("清空");
		cleanBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				itemLdf.editNewRecord();
				Utils.makeAllNotSelectLG(itemListGrid);
				Utils.makeAllNotSelectLG(inquiryLG);
			}

		});

		IButton closeBtn = new IButton("关闭");
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		itemBtnsLayout.setMembers(saveItemBtn, cleanBtn, closeBtn);
		v.addMember(itemBtnsLayout);

		return v;
	}

	// 采购指令选择Grid
	private ListGrid getLeftGrid() {
		inquiryLG = new FilterListGrid();
		Utils.setListGridHeight(inquiryLG);
		inquiryLG.setDataSource(procurementInquiryModel.itemDataSource);

		//询价件号
		ListGridField field2 = new ListGridField("partNumber");

		//询价数量
		ListGridField field3 = new ListGridField("quantity");
		Utils.formatQuantityField(field3);

		//需求日期
		ListGridField field4 = new ListGridField("partRequireDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		inquiryLG.setFields(field2, field3, field4);

		/** 初始化数据 **/
		String id = procurementInquiryModel.listGrid.getSelectedRecord()
				.getAttribute("id");
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", id);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		inquiryLG.fetchData(criteria);

		// 使Grid所有列可筛选
		Utils.makeAllCanFilter(inquiryLG);

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
			}

		});

		return inquiryLG;
	}

	// 新采购指令明细项
	private ListGrid getRightGrid() {
		itemListGrid = new FilterListGrid();
		itemListGrid.setCanRemoveRecords(true);
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(procurementInquiryModel.quotationItemDS);

		//询价件号
		ListGridField field2 = new ListGridField(
				"procurementInquirySheetItem.partNumber");

		//报价件号
		ListGridField field3 = new ListGridField("quotationPartNumber");

		//交货期(天)
		ListGridField field4 = new ListGridField("deliveryLeadTime");

		//报价数量
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);

		//报价单价
		ListGridField field6 = new ListGridField("unitPriceAmount");
		Utils.formatPriceField(field6);
		
		//报价金额
		ListGridField field7 = new ListGridField("amount");
		Utils.formatPriceField(field7);

		itemListGrid.setFields(field2, field3, field4, field5, field6, field7);

		refreshItemListGrid();

		// 使Grid所有列可筛选
		Utils.makeAllCanFilter(itemListGrid);

		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				// 将From定义为修改模式(连接服务器端时修改掉 ListGrid)
				itemLdf.editSelectedData(itemListGrid);

				// 报价单单Grid都不选中
				Utils.makeAllNotSelectLG(inquiryLG);

				itemId = event.getRecord().getAttribute("id");// 明细id赋值

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
		inquiryItemId.setValue(lgr.getAttribute("id"));
		item1.setValue(lgr.getAttribute("partNumber"));
		item2.setValue(lgr.getAttributeAsDate("partRequireDate"));
		item3.setValue(lgr.getAttribute("deliveryAddress"));
		item4.setValue(lgr.getAttribute("quantity"));
		item7.setValue(lgr.getAttribute("partNumber"));

		item10.setValue(lgr.getAttribute("quantity"));
		item12.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));
		item15.setValue(lgr.getAttribute("m_InternationalCurrencyCode"));

	}

	/**
	 * 刷新ItemlistGrid
	 */
	private void refreshItemListGrid() {
		if (primaryKey == null) {
			primaryKey = "-1";
		}
		Criteria criteria = new Criteria();
		criteria.setAttribute("procurementQuotationSheetRecord.id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
	}

	/***************************************************************************
	 * 计算金额
	 */
	private void alculationAmount() {
		float totalAmount = 0.00f;
		if (item10.getValue() != null && item17.getValue() != null) {

			totalAmount = Float.parseFloat(item10.getValue().toString())
					* Float.parseFloat(item17.getValue().toString());// 数量*单价
			if (item13.getValue() != null) {
				totalAmount += Float.parseFloat(item13.getValue().toString());// 特殊包装费用
			}

		}
		item19.setValue(totalAmount);

	}

}
