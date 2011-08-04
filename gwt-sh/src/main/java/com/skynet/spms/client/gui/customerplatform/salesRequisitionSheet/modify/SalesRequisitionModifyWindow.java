package com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.modify;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
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
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.business.SalesRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.PartInfoVO;
import com.skynet.spms.client.vo.UserVo;
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
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
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
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SalesRequisitionModifyWindow extends BaseWindow {
	
	private static I18n ui = new I18n();
	
	private SalesRequisitionModel modelLocator;

	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private IButton saveItemBtn;// 明细操作按钮

	private FormItem totalAmountItem;

	private SelectItem item1;
	private DateItem item2;
	private SelectItem item3;
	private SelectItem item4;
	private FormItem item6;
	private FormItem item7;
	private SelectItem item8;
	private TextItem item9;
	private FormItem item10;
	private DicSelectItem item11;
	private SelectItem item12;
	private TextItem item13;
	private TextAreaItem item14;
	private FormItem item15;
	private FormItem item16;
	private FormItem keywordItem;// 关键字
	private FormItem ataItem;// ATA章节号
	private SelectItem manufacturerItem;// 制造商
	private FormItem partDescriptionItem;// 件号描述
	private SelectItem certificateTypeItem;// 证书类型

	private FormItem itemNumber;
	private String primaryKey;// 主表id

	private FilterListGrid quotationLG;// 报价单选择Grid

	private FilterListGrid itemListGrid;// 明细Grid

	String quotationId = "";// 报价单id

	private static BaseCodeServiceAsync service = GWT
			.create(BaseCodeService.class);

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public SalesRequisitionModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				closeThis();
			}
		});
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		this.setTitle(ui.getB().modifyFormTitle(ui.getM().mod_salesRequisitionSheet_name()));
		modelLocator = SalesRequisitionModel.getInstance();
		primaryKey = modelLocator.listGrid.getSelectedRecord().getAttribute(
				"id");
		quotationId = modelLocator.listGrid.getSelectedRecord().getAttribute(
				"quotationId");

		itemListGrid = new FilterListGrid();
		/** 主面板* */
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		// /**面板1**/
		VLayout oneView = getPrimaryView();
		oneView.setHeight("35%");
		oneView.setWidth100();
		vmain.addMember(oneView);

		/** 面板2 * */
		HLayout twoView = getShowGridView();
		twoView.setHeight("30%");
		twoView.setWidth100();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		// /**面板3**/
		VLayout threeView = getItemView();
		// threeView.setHeight(180);
		threeView.setLayoutTopMargin(10);
		threeView.setHeight("35%");
		threeView.setWidth100();
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
		v.setGroupTitle(ui.getM().mod_salesRequisitionSheet_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		ldf = new LayoutDynamicForm();
		ldf.setNumCols(6);
		ldf.setWidth100();
		ldf.setHeight100();
		ldf.setCellPadding(2);

		ldf.setDataSource(modelLocator.dataSource);
		ldf.editSelectedData(modelLocator.listGrid);

		// 订单编号
		TextItem item_1 = new TextItem();
		item_1.setName("requisitionSheetNumber");
		item_1.setValue(ui.getB().msgAutoIdInfo());
		item_1.setDisabled(true);

		// 机尾号
		final SelectItem item_3 = new SelectItem();
		item_3.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item_3);

		// 优先级
		final SelectItem item_2 = new SelectItem();
		item_2.setName("m_Priority");
		item_2.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (item_2.getValue().equals("AOG")) {
					item_3.setRequired(true);
				} else {
					item_3.setRequired(false);
				}
			}
		});

		// 联系人
		final TextItem item_6 = new TextItem();
		item_6.setName("linkman");

		// 联系方式
		final TextAreaItem item_8 = new TextAreaItem();
		item_8.setName("contactWay");
		item_8.setTitleVAlign(VerticalAlignment.TOP);
		item_8.setHeight("100%");
		item_8.setRowSpan(2);

		// 客户
		final SelectItem item_4 = new SelectItem();
		item_4.setName("m_CustomerIdentificationCode.id");
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item_4);
		FormItemIcon fi4 = new FormItemIcon();
		item_4.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				CodeRPCTool.bindCustomerData(item_4.getValue() + "", item_6,
						item_8);
			}
		});
		item_4.setDisabled(true);

		// 客户订单号
		TextItem item_7 = new TextItem();
		item_7.setName("customerPurchasingOrderNumber");

		// 发货地址
		// TextItem item_10 = new TextItem();
		// item_10.setName("m_ShippingAddress.address");

		// 总金额
		totalAmountItem = new SpinnerItem();
		totalAmountItem.setName("totalAmount");
		totalAmountItem.setValue("0.00");
		totalAmountItem.setDisabled(true);

		// 备注
		TextAreaItem item_9 = new TextAreaItem();
		item_9.setName("remark");
		item_9.setTitleVAlign(VerticalAlignment.TOP);
		item_9.setWidth("100%");
		item_9.setHeight("100%");
		item_9.setRowSpan(2);

		// 报价单id
		TextItem quotationIdItem = new TextItem();
		quotationIdItem.setName("quotationId");
		quotationIdItem.setVisible(false);

		if (!quotationId.equals("")) {
			ldf.setFields(item_1, item_2, item_3, item_4, item_6, item_7,
					item_8, item_9, totalAmountItem, quotationIdItem);
		} else {
			ldf.setFields(item_1, item_2, item_3, item_4, item_6, item_7,
					item_8, item_9, totalAmountItem);
		}

		v.setMembers(ldf);

		btnsLayout = new BtnsHLayout();

		// 构建操作按钮
		final IButton saveBtn = new IButton(ui.getB().btnSave());
		final IButton cancelBtn = new IButton(ui.getB().btnClose());
		// 仅保存
		// 保存
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ldf.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say(ui.getB().msgAddOpSuccess());
						refeshListGrid();
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

		// 刷新总金额
		Timer timer = Utils.startAmountTimer(itemListGrid, null,
				totalAmountItem, "amount");
		timer.scheduleRepeating(500);
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
		//报价单明细列表
		Label leftLb = new Label(ui.getB().msgListTitle(ui.getM().mod_salesQuotationSheet_item_name()));
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid

		VLayout rightLayout = new VLayout();
		//订单明细列表
		Label rightLb = new Label(ui.getB().msgListTitle(ui.getM().mod_salesRequisitionSheetItem_name()));
		rightLb.setHeight("20");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		/** 根据是否来源报价单 显示报价单明细 **/
		if (!"".equals(quotationId) && quotationId != null) {
			h.addMember(leftLayout);
		}

		h.addMember(rightLayout);
		return h;
	}

	// 布局3(明细添加)
	private VLayout getItemView() {
		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		//订单明细
		v.setGroupTitle(ui.getM().mod_salesRequisitionSheetItem_name());
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(6);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		itemLdf.setDataSource(modelLocator.itemDataSource);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("salesRequisitionSheet.id");

		// 报价件号(采购件号)
		item1 = Utils.getPartNumberList();
		item1.setName("partNumber");
		item1.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				PartInfoVO partVo = Utils.getPartInfoVO(item1);
				manufacturerItem.setValue(partVo.getManufacturerCodeId());// 制造商
				keywordItem.setValue(partVo.getKeyword());// 关键字
				ataItem.setValue(partVo.getAtaNumber());// ATA
				item8.setValue(partVo.getUnitOfMeasureCode());// 单位
				partDescriptionItem.setValue(partVo.getDescription());// 备件描述
			}
		});

		// 关键字
		keywordItem = new TextItem();
		keywordItem.setName("keyword");
		keywordItem.setDisabled(true);

		// 备件描述
		partDescriptionItem = new TextItem();
		partDescriptionItem.setName("partDescription");
		partDescriptionItem.setDisabled(true);
		partDescriptionItem.setPrompt(partDescriptionItem.getValue() + "");

		// ATA
		ataItem = new TextItem();
		ataItem.setName("partAta");
		ataItem.setDisabled(true);

		// 制造商
		manufacturerItem = new SelectItem();
		manufacturerItem.setName("m_ManufacturerCode.id");
		// manufacturerItem.setDisplayField("code");
		// manufacturerItem.setValueField("id");
		manufacturerItem.setTitle(ui.getM().title_manufacturer());
		manufacturerItem.setWidth(50);
		CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, manufacturerItem);
		// manufacturerItem.setDisabled(true);

		// 证书类型
		certificateTypeItem = new SelectItem();
		certificateTypeItem.setName("m_CertificateType");
		certificateTypeItem.setMultiple(true);
		EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, certificateTypeItem);

		// 交货日期
		item2 = new DateItem();
		item2.setName("partDeliveryDate");
		item2.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item2.setUseTextField(true);

		// 优先级
		item3 = new SelectItem();
		item3.setName("m_Priority");

		// 机尾号
		item4 = new SelectItem();
		item4.setName("airIdentificationNumber");
		Utils.setAirIdentificationNumberItemDS(item4);

		// 采购数量
		item6 = new SpinnerItem();
		item6.setName("quantity");
		item6.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		// 特殊包装说明
		item7 = new TextItem();
		item7.setName("salesQuotationSheetItem.packageDescription");

		// 单位
		item8 = new SelectItem();
		item8.setName("m_UnitOfMeasureCode");

		// 特殊包装费用
		item9 = new TextItem();
		item9.setName("salesQuotationSheetItem.packagePrice");
		item9.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});

		// 标准包装数量
		item10 = new SpinnerItem();
		item10.setName("salesQuotationSheetItem.standardPackageQuantity");

		// 币种
		item11 = new DicSelectItem();
		item11.setName("m_InternationalCurrencyCode");

		// 采购单价
		item13 = new SpinnerItem();
		item13.setName("unitOfPrice");
		item13.addChangedHandler(new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				alculationAmount();
			}
		});
		// 制造商
		// item12 = new SelectItem();
		// item12.setTitle("制造商");
		// item12.setName("m_ManufacturerCode.id");
		//		
		// CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item12);

		// 采购金额
		item15 = new SpinnerItem();
		item15.setName("amount");

		// 付款要求
		item14 = new TextAreaItem();
		item14.setName("salesQuotationSheetItem.paymentRequirement");
		item14.setHeight("100%");
		item14.setTitleVAlign(VerticalAlignment.TOP);

		// 备注
		item16 = new TextAreaItem();
		item16.setName("remarkText");
		item14.setHeight("100%");
		item16.setTitleVAlign(VerticalAlignment.TOP);

		// 项号
		itemNumber = new TextItem();
		itemNumber.setName("itemNumber");
		itemNumber.setVisible(false);

		itemLdf.setFields(primaryKeyItem, item1, keywordItem,
				partDescriptionItem, ataItem, manufacturerItem,
				certificateTypeItem, item2, item3, item4, item6, item8, item7,
				item9, item10, item13, item11, item15, item14, item16,
				itemNumber);

		v.addMember(itemLdf);

		itemBtnsLayout = new BtnsHLayout();
		// 构建操作按钮
		saveItemBtn = new IButton(ui.getB().btnSaveItem());
		saveItemBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (quotationLG.getSelectedRecord() != null
						|| itemListGrid.getSelectedRecord() != null
						|| item1.getValue() != null) {
					primaryKeyItem.setValue(primaryKey);
					if (itemLdf.validate()) {

						itemLdf.saveData(new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {

								if (totalAmountItem.getValue() != null) {
									if (!totalAmountItem.getValue().equals("")) {
										Float totalAmount = Float
												.parseFloat(totalAmountItem
														.getValue()
														+ "");
										totalAmount += Float.parseFloat(item15
												.getValue()
												+ "");
										totalAmountItem.setValue(totalAmount);
									}

								}

								itemLdf.clearValues();
								itemLdf.editNewRecord();
								Utils.makeAllNotSelectLG(quotationLG);
								Utils.makeAllNotSelectLG(itemListGrid);
								refreshItemListGrid();

							}
						});
					}
				} else {
					//请先选择报价单明细项
					SC.say(ui.getM().mod_salesQuotationSheet_item_msg_firstSelect());
				}

			}

		});

		// 清空按钮
		IButton cleanBtn = new IButton(ui.getB().btnClean());
		cleanBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				itemLdf.editNewRecord();
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

	// 选择Grid
	private ListGrid getLeftGrid() {
		quotationLG = new FilterListGrid();

		Utils.setListGridHeight(quotationLG);

		// 初始化订单明细数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET_ITEM,
				DSKey.C_SALESQUOTATIONSHEET_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// 指定报价单数据源

						quotationLG.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("id", quotationId);
						criteria.setAttribute("_r", String.valueOf(Math
								.random()));
						quotationLG.fetchData(criteria);


						// 报价编号
						ListGridField field2 = new ListGridField(
								"salesQuotationSheet.quotationSheetNumber");

						// 报价件号
						ListGridField field3 = new ListGridField(
								"quotationPartNumber");
						//关键字
						ListGridField keywordField = new ListGridField(
								"keyword");
						keywordField.setPrompt(field2.getAttribute("keyword"));

						// 报价数量
						ListGridField field4 = new ListGridField("quantity");
						Utils.formatQuantityField(field4);

						// 报价单价
						ListGridField field5 = new ListGridField(
								"unitPriceAmount");
						Utils.formatPriceField(field5);

						// 交货期
						ListGridField field6 = new ListGridField(
								"deliveryLeadTime");

						quotationLG.setFields( field2, field3,
								keywordField, field4, field5, field6);

					}
				});

		// 选中行事件
		quotationLG.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				itemLdf.editNewRecord();

				Utils.makeAllNotSelectLG(itemListGrid);

				// 再冲赋值
				ListGridRecord lgr = event.getRecord();
				setFormItemValues(lgr);

				itemNumber.setValue(itemListGrid.getRecords().length + 1);

			}

		});

		return quotationLG;
	}

	// 订单明细项
	private ListGrid getRightGrid() {
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setCanRemoveRecords(true);
		itemListGrid.setDataSource(modelLocator.itemDataSource);
		// 获得询价单明细项
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", primaryKey);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);

		//项号
		ListGridField field1 = new ListGridField("itemNumber");
		field1.setHidden(true);

		//件号
		ListGridField field2 = new ListGridField("partNumber");
		
		//关键字
		ListGridField keywordField = new ListGridField("keyword");
		keywordField.setPrompt(field2.getAttribute("keyword"));

		//数量
		ListGridField field3 = new ListGridField("quantity");
		Utils.formatQuantityField(field3);

		//单价
		ListGridField field4 = new ListGridField("unitOfPrice");
		Utils.formatPriceField(field4);

		//金额
		ListGridField field5 = new ListGridField("amount");
		Utils.formatPriceField(field5);

		itemListGrid.setFields(field1, field2, keywordField, field3, field4,
				field5);

		// 选中行事件
		itemListGrid.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();

				// 将From定义为修改模式(连接服务器端时修改掉 ListGrid)
				itemLdf.editSelectedData(itemListGrid);

				Utils.makeAllNotSelectLG(quotationLG);
				
				//多选下拉菜单赋值
				Utils.setMultipleFormItemValue(certificateTypeItem);
				
				service.getPartInfoByPartNumber( event.getRecord().getAttribute("partNumber"),
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
								keywordItem.setValue(partVo.getKeyword());//关键字
								ataItem.setValue(partVo.getAtaNumber());//ATA
								item8.setValue(partVo.getUnitOfMeasureCode());//单位
								partDescriptionItem.setValue(partVo.getDescription());//备件描述
							}
						});
				

			}

		});

		return itemListGrid;
	}

	/**
	 * 刷新ItemlistGrid
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

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {
		SalesRequisitionSheetBusiness bus = new SalesRequisitionSheetBusiness();
		bus.refeshListGrid();
	}

	/**
	 * 单击Grid 给Form 赋值
	 * 
	 * @param lgRecord
	 */
	private void setFormItemValues(ListGridRecord lgRecord) {
		ListGridRecord lgr = lgRecord;
		PartInfoVO partInfoVO = Utils.getPartInfoVOByPartNumber(lgRecord
				.getAttribute("quotationPartNumber")
				+ "");

		// 单击报价单明细选择
		item1.setValue(lgr.getAttribute("quotationPartNumber"));

		// Integer deliveryLeadTime=lgr.getAttributeAsDate("quotationDate");
		item2.setValue(lgr.getAttributeAsDate("partRequireDate"));
		item3.setValue(lgr.getAttribute("m_Priority"));
		item4.setValue(lgr.getAttribute("airIdentificationNumber"));
		item6.setValue(lgr.getAttribute("quantity"));
		item7.setValue(lgr.getAttribute("packageDescription"));
		item8.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));
		item9.setValue(lgr.getAttribute("packagePrice"));
		item10.setValue(lgr.getAttribute("standardPackageQuantity"));
		item11.setValue(lgr.getAttribute("m_InternationalCurrencyCode"));
		// item12.setValue(lgr.getAttribute("m_ManufacturerCode"));
		item13.setValue(lgr.getAttribute("unitPriceAmount") + "");
		item14.setValue(lgr.getAttribute("paymentRequirement"));
		item15.setValue(lgr.getAttribute("amount"));
		item16.setValue(lgr.getAttribute("remark"));

		if (partInfoVO != null) {
			keywordItem.setValue(partInfoVO.getKeyword());// 关键字
			ataItem.setValue(partInfoVO.getAtaNumber());// ATA章节号

			manufacturerItem.setValue(partInfoVO.getManufacturerCodeId());
			partDescriptionItem.setValue(partInfoVO.getDescription());
		}

		service.getPartInfoByPartNumber(item1.getValueAsString(),
				new AsyncCallback<PartInfoVO>() {

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						SC.say(arg0.getMessage());
					}
					@Override
					public void onSuccess(PartInfoVO partInfoVO) {
						// TODO Auto-generated method stub
						keywordItem.setValue(partInfoVO.getKeyword());// 关键字
						ataItem.setValue(partInfoVO.getAtaNumber());// ATA章节号
						manufacturerItem.setValue(partInfoVO
								.getManufacturerCodeId());
						partDescriptionItem.setValue(partInfoVO
								.getDescription());
					}
				});

	}

	/***************************************************************************
	 * 计算金额
	 */
	private void alculationAmount() {
		float totalAmount = 0.00f;
		if (item6.getValue() != null && item13.getValue() != null) {

			totalAmount = Float.parseFloat(item6.getValue().toString())
					* Float.parseFloat(item13.getValue().toString());// 数量*单价
			if (item9.getValue() != null) {
				totalAmount += Float.parseFloat(item9.getValue().toString());// 特殊包装费用
			}

		}
		item15.setValue(totalAmount);
	}

	private void closeThis() {
		destroy();
		refeshListGrid();
	}
}
