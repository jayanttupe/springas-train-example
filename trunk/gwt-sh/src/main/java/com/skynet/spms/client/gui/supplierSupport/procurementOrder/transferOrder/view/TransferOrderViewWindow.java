package com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.view;

import java.util.LinkedHashMap;
import com.skynet.spms.client.gui.base.EnumTool;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.DicKey;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.CommonWindow;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.model.TransferOrderModel;
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
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.i18n.I18n;

/*******************************************************************************
 * 主订单 与 明细 添加页面
 * 
 * @author Tony FANG
 * 
 */
public class TransferOrderViewWindow extends BaseWindow {

	private static I18n ui = new I18n();
	
	private TransferOrderModel modelLocator;

	private ListGrid[] listGrids = null;

	private LayoutDynamicForm ldf;// 主订单Form

	private LayoutDynamicForm itemLdf;// 订单明细Form

	private BtnsHLayout btnsLayout;// 主订单操作按钮容器

	private BtnsHLayout itemBtnsLayout;// 明细操作按钮

	private IButton saveItemBtn;

	private String primaryKey;// 主表id

	private FilterListGrid procurementOrderLG;// 采购订单选择Grid

	private FilterListGrid itemListGrid;// 新采购询价单Grid

	private FormItem item1;
	private SelectItem item2;
	private FormItem item3;
	private SelectItem item4;
	private FormItem item5;
	private SelectItem item6;
	private DateItem item7;
	private FormItem item8;
	private FormItem item9;
	private SelectItem item10;
	private FormItem item11;
	private FormItem item12;
	private FormItem item13;
	private FormItem item14;
	private FormItem item15;
	private FormItem item16;
	
	private static BaseCodeServiceAsync service = GWT
	.create(BaseCodeService.class);

	

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public TransferOrderViewWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		//调拨单信息
		this.setTitle(ui.getM().viewFormTitle(ui.getM().mod_transferOrder_name()));
		modelLocator = TransferOrderModel.getInstance();
		primaryKey=modelLocator.listGrid.getSelectedRecord().getAttribute("id");
		
		itemListGrid = new FilterListGrid();

		/** 主面板* */
		VLayout vmain = new VLayout();
		/**
		 * vmain.setLayoutMargin(3); 设置此属性由于总体宽度不够 就会报one or more 错 解决此问题的方式
		 * 只有调整元素 高度与宽度；或者 直接设置主面板宽度
		 */
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
		//调拨单
		v.setGroupTitle(ui.getM().mod_transferOrder_name());
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

		//调拨单编号
		TextItem item_1 = new TextItem("transferSheetNumber");
		item_1.setValue(selectLGR.getAttribute("transferSheetNumber"));

		//采购指令号
		TextItem item_2 = new TextItem("orderNumber");
		FormItemIcon fii2 = new FormItemIcon();
		item_2.setIcons(fii2);
		item_2.setValue(selectLGR.getAttribute("orderNumber"));

		//优先级
		SelectItem item_3 = new SelectItem();
		item_3.setName("m_Priority");
		item_3.setValue(selectLGR.getAttribute("m_Priority"));

		//调拨来源
		SelectItem item_4 = new SelectItem();
		item_4.setName("transferFrom.id");
		CodeRPCTool.bindData(CodeRPCTool.CAGECODE, item_4);
		item_4.setValue(selectLGR.getAttribute("transferFrom.id"));

		//联系人
		TextItem item_5 = new TextItem("linkman");
		item_5.setValue(selectLGR.getAttribute("linkman"));
		
		//飞机尾号
		SelectItem item_6 = new SelectItem();
		item_6.setName("airIdentificationNumber");
		item_6.setValue(selectLGR.getAttribute("airIdentificationNumber"));
		Utils.setAirIdentificationNumberItemDS(item_6);
		
		//联系方式
		TextItem item_7 = new TextItem("contactInformation");
		item_7.setColSpan(3);
		item_7.setValue(selectLGR.getAttribute("contactInformation"));

		//交货方式
		DicSelectItem item_8 = new DicSelectItem();
		item_8.setName("m_DeliveryType");
		item_8.setValue(selectLGR.getAttribute("m_DeliveryType"));

		//联系人
		final TextItem item_12 = new TextItem("carrierLinkman");
		item_12.setValue(selectLGR.getAttribute("carrierLinkman"));
		
		//联系方式
		final TextItem item_14 = new TextItem("carrierContactInformation");
		item_14.setColSpan(3);
		item_14.setValue(selectLGR.getAttribute("carrierContactInformation"));

		//运代商
		final SelectItem item_11 = new SelectItem("m_CarrierName.id");
		CodeRPCTool.bindData(CodeRPCTool.CARRIERNAME, item_11);
		item_11.setValue(selectLGR.getAttribute("m_CarrierName.id"));
		item_11.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				String id = item_11.getValueAsString();
				CodeRPCTool.bindCarrierData(id, item_12,
						item_14);
			}
		});
		
		//由调拨来源企业指定运代
		CheckboxItem item_9 = new CheckboxItem();
		item_9.setName("CAGEAssignCarrier");
		item_9.setColSpan(3);
		item_9.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if ((Boolean) event.getValue()) {
					item_14.setDisabled(true);
					item_11.setDisabled(true);
					item_12.setDisabled(true);
				} else {
					item_14.setDisabled(false);
					item_11.setDisabled(false);
					item_12.setDisabled(false);
				}
			}
		});
		item_9.setValue(selectLGR.getAttribute("CAGEAssignCarrier"));

		//运输方式
		final SelectItem item_10 = new SelectItem();
		item_10.setName("m_TransportationCode");
		item_10.setValue(selectLGR.getAttribute("m_TransportationCode"));

		//贸易方式
		SelectItem item_13 = new SelectItem();
		item_13.setName("m_TradeMethods");
		item_13.setValue(selectLGR.getAttribute("m_TradeMethods"));

		//GTA协议
		final SelectItem item_15 = new SelectItem();
		item_15.setName("m_EnterpriseGTA");
		item_15.setMultiple(true);
		DataSourceTool dsTool = new DataSourceTool();
		dsTool.onCreateDataSource(DSKey.S_PROCUREMENTTRANSFERORDER,
				DSKey.S_CUSTOMERREPAIRINSORDER_GTA_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						item_15.setOptionDataSource(dataSource);
						item_15.fetchData();
					}
				});
		item_15.setValue(selectLGR.getAttribute("m_EnterpriseGTA"));
		

		//备注
		TextAreaItem item_16 = new TextAreaItem("remarkText");
		item_16.setColSpan(3);
		item_16.setRowSpan(3);
		item_16.setHeight("100%");
		item_16.setTitleVAlign(VerticalAlignment.TOP);
		item_16.setValue(selectLGR.getAttribute("remarkText"));
		
		//制定人
		DicSelectItem item_17 = new DicSelectItem();
		item_17.setName("makeBy");
		item_17.setValue(selectLGR.getAttribute("makeBy"));

		//数量总计
		DicSelectItem item_18 = new DicSelectItem();
		item_18.setName("itemCount");
		item_18.setValue(selectLGR.getAttribute("itemCount"));
		
		//单位
		SelectItem item_19 = new SelectItem();
		item_19.setName("m_UnitOfMeasureCode");
		item_19.setValue(selectLGR.getAttribute("m_UnitOfMeasureCode"));

		ldf.setFields(item_1, item_2, item_3, item_4, item_5, item_6, item_7,
				item_8, item_9, item_10, item_11, item_12, item_13, item_14,
				item_15, item_16, item_17, item_18, item_19);

		
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

		VLayout leftLayout = new VLayout();
		//采购指令明细列表
		Label leftLb = new Label(ui.getM().msgListTitle(ui.getM().procurementOrderItems()));
		leftLb.setHeight("20");
		leftLayout.addMember(leftLb);// 先放label
		leftLayout.addMember(getLeftGrid());// 再放Grid
		leftLayout.setVisible(false);

		VLayout rightLayout = new VLayout();
		//调拨明细列表
		Label rightLb = new Label(ui.getM().msgListTitle(ui.getM().mod_transferOrder_item_name()));
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
		//采购指令明细列表
		Label leftLb = new Label(ui.getM().msgListTitle(ui.getM().procurementOrderItems()));
		v.setIsGroup(true);
		v.setLayoutLeftMargin(10);

		itemLdf = new LayoutDynamicForm();
		itemLdf.setWidth100();
		itemLdf.setHeight100();
		itemLdf.setNumCols(4);
		itemLdf.setCellPadding(2);
		itemLdf.setMargin(5);

		final TextItem primaryKeyItem = new TextItem();
		primaryKeyItem.setVisible(false);
		primaryKeyItem.setName("procurementTransferSheet.id");

		// 初始化订单Form数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(
				DSKey.S_PROCUREMENTTRANSFERORDER_ITEM,
				DSKey.S_PROCUREMENTTRANSFERORDER_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						itemLdf.setDataSource(dataSource);

						//件号
						item1 = Utils.getPartNumberList();
						item1.setName("partNumber");
						item1.addChangedHandler(new ChangedHandler() {
							public void onChanged(ChangedEvent event) {
								PartInfoVO partVo=Utils.getPartInfoVO(item1);
								item2.setValue(partVo.getManufacturerCodeId());//制造商
								item3.setValue(partVo.getAtaNumber());//ATA
								item5.setValue(partVo.getKeyword());//关键字
								item10.setValue(partVo.getUnitOfMeasureCode());//单位
							}
						});

						//制造商
						item2 = new SelectItem();
						item2.setName("m_ManufacturerCode.id");
						CodeRPCTool.bindData(CodeRPCTool.MANUFACTURERCODE, item2);


						//ATA章节号
						item3 = new TextItem("ATA");

						//适用机型
						item4 = new SelectItem();
						item4.setName("m_ModelofApplicabilityCode");
						item4.setMultiple(true);

						//关键字
						item5 = new TextItem("itemKeyword");

						//随件资料
						item6 = new SelectItem();
						item6.setName("m_CertificateType");
						item6.setMultiple(true);
						EnumTool.setValueMap(EnumTool.CERTIFICATETYPE, item6);

						//交货日期
						item7 = new DateItem("deliveryDate");
						item7.setUseTextField(true);
						item7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

						//备注
						item8 = new TextAreaItem();
						item8.setName("remarkText");
						item8.setRowSpan(3);
						item8.setHeight("100%");
						item8.setTitleVAlign(VerticalAlignment.TOP);

						//调拨数量
						item9 = new TextItem("plannedQuantity");

						//单位
						item10 = new SelectItem("m_UnitOfMeasureCode");

						/** 隐藏属性* */
						//单价
						item11 = new TextItem("plannedUnitPrice");
						item11.setVisible(false);

						//金额
						item12 = new TextItem("amount");
						item12.setVisible(false);

						itemLdf.setFields(primaryKeyItem, item1, item2, item3,
								item4, item5, item6, item7, item8, item9,
								item10);
						Utils.setReadOnlyForm(itemLdf);
					}
				});

		v.addMember(itemLdf);


		return v;
	}

	// 备件选择Grid
	private ListGrid getLeftGrid() {
		procurementOrderLG = new FilterListGrid();
		Utils.setListGridHeight(procurementOrderLG);

		// 初始化订单数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDERITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						procurementOrderLG.setDataSource(dataSource);

						String proOrderNumber = modelLocator.listGrid
								.getSelectedRecord()
								.getAttribute("orderNumber");
						Criteria criteria = new Criteria();
						criteria.setAttribute("orderNumber", proOrderNumber);
						criteria.setAttribute("_r", String.valueOf(Math
								.random()));
						procurementOrderLG.fetchData(criteria);

					

						//件号
						ListGridField field2 = new ListGridField("partNumber"
								);

						//关键字
						ListGridField field3 = new ListGridField("itemKeyword");
						field3.setHidden(true);
						
						//需求日期
						ListGridField field4 = new ListGridField(
								"deliveryDate");
						field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

						//计划采购数量
						ListGridField field5 = new ListGridField(
								"plannedQuantity");
						Utils.formatQuantityField(field5);

						procurementOrderLG.setFields( field2, field3,
								field4, field5);

						Utils.makeAllCanFilter(procurementOrderLG);
					}
				});

		// 选中行事件
		procurementOrderLG.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {
				// 先清空Form表单的值
				itemLdf.clearValues();
				// 新添加模式
				itemLdf.editNewRecord();
				// 再次给Form赋值
				setFormItemValues(event.getRecord());
				// 新报价单Grid都不选中
				Utils.makeAllNotSelectLG(itemListGrid);
			}
		});

		return procurementOrderLG;
	}

	// 新采购指令明细项
	private ListGrid getRightGrid() {
		Utils.setListGridHeight(itemListGrid);
		itemListGrid.setDataSource(modelLocator.itemDataSource);
		refreshItemListGrid();

		//件号
		ListGridField field2 = new ListGridField("partNumber");

		//关键字
		ListGridField field3 = new ListGridField("itemKeyword");
		field3.setHidden(true);

		//需求日期
		ListGridField field4 = new ListGridField("deliveryDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//调拨数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		Utils.formatQuantityField(field5);

		itemListGrid.setFields( field2, field3, field4, field5);

		Utils.makeAllCanFilter(itemListGrid);

		// 选中行事件
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
				
				service.getPartInfoByPartNumber(event.getRecord().getAttribute("partNumber"),
						new AsyncCallback<PartInfoVO>() {

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
		item6.setValue(lgr.getAttribute("m_CertificateType"));
		item7.setValue(lgr.getAttributeAsDate("deliveryDate"));
		item8.setValue(lgr.getAttribute("remarkText"));
		item9.setValue(lgr.getAttribute("plannedQuantity"));
		item10.setValue(lgr.getAttribute("m_UnitOfMeasureCode"));

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
