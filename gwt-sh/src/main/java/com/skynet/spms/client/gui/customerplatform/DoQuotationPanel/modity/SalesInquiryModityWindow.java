package com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.modity;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.business.SalesInquiryBusiness;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
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
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/*******************************************************************************
 * 询价单修改接口
 * 
 * @author Tony FANG
 * 
 */
public class SalesInquiryModityWindow extends BaseWindow {
	private static I18n ui = new I18n();
	private SalesInquiryModel modelLocator;
	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private HLayout btnsLayout;// 主订单操作按钮容器

	private IButton saveItemBtn;// 明细操作按钮

	private String primaryKey;// 主键id

	private FilterListGrid itemListGrid;

	private SelectItem item1;
	private SelectItem item2;
	private TextAreaItem item3;
	private FormItem item4;
	private DicSelectItem item5;
	private SelectItem item6;
	private SelectItem item7;
	private DateItem item8;
	private FormItem item9;
	private FormItem keywordItem;// 关键字
	private FormItem ataItem;// ATA章节号

	private FormItem itemNumber;

	private String itemId = "";// 明细id

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SalesInquiryModityWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.addCloseClickHandler(new CloseClickHandler() {

			@Override
			public void onCloseClick(CloseClientEvent event) {
				// TODO Auto-generated method stub
				SalesInquiryBusiness bus = new SalesInquiryBusiness();
				bus.refeshListGrid();
				destroy();
			}
		});
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		// initDataSource();// 初始化ItemDataSource
		modelLocator = SalesInquiryModel.getInstance();
		primaryKey = modelLocator.listGrid.getSelectedRecord().getAttribute(
				"id");
		this.setTitle(ui.getB().modifyFormTitle(ui.getM().mod_salesInquiry_name()));//修改询价单
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setMembersMargin(2);
		/** 面板1* */
		VLayout oneView = getProcurementView();
		// oneView.setHeight(150);
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setHeight(150);
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		/** 面板3* */
		VLayout threeView = getProcurementItemView();
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
	private VLayout getProcurementView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_salesInquiry_name());//询价单
		v.setIsGroup(true);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		ldf.setDataSource(modelLocator.dataSource);
		ldf.editSelectedData(modelLocator.listGrid);

		// 编号
		TextItem item_1 = new TextItem();
		item_1.setName("inquirySheetNumber");
		item_1.setDisabled(true);

		// 联系人
		final TextItem item_3 = new TextItem();
		item_3.setName("linkman");

		// 联系方式
		final TextAreaItem item_5 = new TextAreaItem();
		item_5.setName("contactInformation");
		// 添加有关行的高度代码
		item_5.setRowSpan(3);
		// item_5.setHeight("100%");
		item_5.setTitleVAlign(VerticalAlignment.TOP);

		// 客户信息
		final SelectItem item_2 = new SelectItem();
		item_2.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item_2);
		FormItemIcon fi2 = new FormItemIcon();
		fi2.setPrompt("客户信息");
		item_2.setIcons(fi2);
		item_2.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				CodeRPCTool.bindCustomerData(item_2.getValue() + "", item_3,
						item_5);
			}
		});
		item_2.setDisabled(true);

		// 备注
		TextAreaItem item_6 = new TextAreaItem();
		item_6.setName("remark");
		item_6.setRowSpan(3);
		// item_6.setHeight("100%");
		item_6.setTitleVAlign(VerticalAlignment.TOP);

		ldf.setFields(item_1, item_2, item_3, item_5, item_6);

		v.setMembers(ldf);

		/** 订单操作按钮* */
		btnsLayout = new HLayout();
		btnsLayout.setLayoutLeftMargin(15);
		btnsLayout.setMembersMargin(5);
		btnsLayout.setMargin(5);

		// 构建操作按钮
		final IButton saveBtn = new IButton(ui.getB().btnSave());//保存
		final IButton cancelBtn = new IButton(ui.getB().btnClose());//关闭
		// 保存
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());//保存成功
						refeshListGrid();
						saveItemBtn.setDisabled(false);
						primaryKey = response.getData()[0].getAttribute("id");

					}
				});
			}
		});
		// 关闭
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				SalesInquiryBusiness bus = new SalesInquiryBusiness();
				bus.refeshListGrid();
				destroy();
			}
		});
		btnsLayout.addMember(saveBtn);
		btnsLayout.addMember(cancelBtn);

		v.addMember(btnsLayout);
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
		leftLayout.setWidth100();
		leftLayout.setHeight100();
		
		Label leftLb = new Label(ui.getB().msgListTitle(ui.getM().mod_salesInquiry_item_name()));//询价单明细列表
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		h.addMember(leftLayout);
		return h;
	}

	/** 布局3(明细添加)* */
	private VLayout getProcurementItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setGroupTitle(ui.getM().mod_salesInquiry_item_name());//询价单明细
		v.setIsGroup(true);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(4);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);
		itemLdf.setDataSource(modelLocator.itemDataSource);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("salesInquirySheet.id");

		// 询价件号
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {

				PartInfoVO partVo = Utils.getPartInfoVO(item1);
				item2.setValue(partVo.getManufacturerCodeId());// 制造商
				keywordItem.setValue(partVo.getKeyword());// 关键字
				ataItem.setValue(partVo.getAtaNumber());// ATA
				item5.setValue(partVo.getUnitOfMeasureCode());// 单位
			}
		});

		// 关键字
		keywordItem = new TextItem();
		keywordItem.setName("keyword");
		keywordItem.setDisabled(true);

		// ATA
		ataItem = new TextItem();
		ataItem.setName("ata");
		ataItem.setDisabled(true);

		// 制造商
		item2 = new SelectItem();
		item2.setName("m_ManufacturerCode.id");
		item2.setDisplayField("code");
		item2.setWidth(50);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);
		item2.setDisabled(true);

		// 询价数量
		item4 = new SpinnerItem();
		item4.setName("quantity");
		item4.setWidth(50);
		// 单位
		item5 = new DicSelectItem();
		item5.setName("m_UnitOfMeasureCode");
		item5.setWidth(50);
		// 优先级
		item6 = new SelectItem();
		item6.setName("m_Priority");
		item6.setWidth(50);
		item6.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (item6.getValue().equals("AOG")) {
					item7.setRequired(true);
				} else {
					item7.setRequired(false);
				}
			}
		});
		// 飞机机尾号
		item7 = new SelectItem();
		item7.setName("airIdentificationNumber");
		item7.setWidth(50);
		Utils.setAirIdentificationNumberItemDS(item7);
		// 需求日期
		item8 = new DateItem();
		item8.setName("partRequireDate");
		item8.setUseTextField(true);
		item8.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item8.setWidth(50);

		/*
		 * item9 = new TextItem(); item9.setName("description");
		 * item9.setTitle("中文全称");
		 */

		// 指定交货地点
		item3 = new TextAreaItem();
		item3.setName("deliveryAddress");
		item3.setRowSpan(4);
		// item3.setHeight("100%");
		item3.setTitleVAlign(VerticalAlignment.TOP);

		// 项号
		itemNumber = new TextItem();
		itemNumber.setName("itemNumber");
		itemNumber.setVisible(false);

		itemLdf.setFields(primaryKeyItem, item1, keywordItem, ataItem, item3,
				item2, item4, item5, item6, item7, item8, itemNumber);

		v.addMember(itemLdf);

		/** 操作按钮* */
		HLayout itemBtnsLayout = new HLayout();
		itemBtnsLayout.setLayoutLeftMargin(15);
		itemBtnsLayout.setMembersMargin(5);
		itemBtnsLayout.setMargin(5);
		// 构建操作按钮
		saveItemBtn = new IButton(ui.getB().btnSaveItem());
		saveItemBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				primaryKeyItem.setValue(primaryKey);
				itemNumber.setValue(itemListGrid.getRecords().length + 1);

				if (itemLdf.validate()) {
					itemLdf.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							itemLdf.clearValues();
							Criteria criteria = new Criteria();
							criteria.addCriteria("id", primaryKey);
							criteria.addCriteria("_r", String.valueOf(Math
									.random()));
							itemListGrid.fetchData(criteria);
							itemLdf.editNewRecord();

							itemId = response.getData()[0].getAttribute("id");
						}
					});
				}

			}

		});

		// 清空按钮
		IButton cleanBtn = new IButton(ui.getB().btnClean());
		cleanBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				itemLdf.editNewRecord();
				Utils.makeAllNotSelectLG(itemListGrid);
			}

		});
		//关闭
		IButton closeBtn = new IButton(ui.getB().btnClose());
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		itemBtnsLayout.setMembers(saveItemBtn, cleanBtn, closeBtn);
		v.addMember(itemBtnsLayout);

		return v;
	}

	// 新询价单信息
	private ListGrid getLeftGrid() {
		itemListGrid = new FilterListGrid();
		itemListGrid.setCanRemoveRecords(true);
		itemListGrid.setRemoveFieldTitle(ui.getB().btnRemove());

		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(modelLocator.itemDataSource);
		// 获得询价单明细项
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);

		// 询价件号
		ListGridField field1 = new ListGridField("partNumber");
		// 关键字
		ListGridField field2 = new ListGridField("keyword");
		// 询价数量
		ListGridField field3 = new ListGridField("quantity");
		// 需求日期
		ListGridField field4 = new ListGridField("partRequireDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		itemListGrid.setFields(field1, field2, field3, field4);

		// 选中行事件
		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				itemLdf.editSelectedData(itemListGrid);

				itemId = event.getRecord().getAttribute("id");// 明细id赋值
			}

		});

		return itemListGrid;
	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {

		SalesInquiryBusiness slb = new SalesInquiryBusiness();
		slb.refeshListGrid();

	}

}
