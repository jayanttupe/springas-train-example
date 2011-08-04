package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.add;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseRightListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 添加供应商寄售补库申请单
 * 
 * @author fl
 * 
 */
public class ConsignRenewAddWin extends BaseWindow {
	private static I18n ui = new I18n();
	public ConsignRenewAddWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ConsignRenewAddWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			OperationMode opm) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		initViewState(opm);
	}

	/**
	 * 根据操作方式 初始化试图
	 * 
	 * @param opm
	 */
	private void initViewState(OperationMode opm) {
		/** 根据当前操作方式，设置页面 **/
		if (opm != null) {
			// 如果是添加主订单，则保存明细按钮 禁用
			if (opm.equals(OperationMode.add)) {
			} else if (opm.equals(OperationMode.addItem)) {
			}
		}
	}

	public ConsignRenewModel model;
	public ConsignRenewItemForm itemForm;

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		//新建寄售补库
		this.setTitle(ui.getM().addFormTitle(ui.getM().consignRenew_name()));
		model = ConsignRenewModel.getInstance();
		VLayout vLayout = new VLayout();
		vLayout.setMembersMargin(15);
		vLayout.setHeight100();

		VLayout tabsLayout = new VLayout();
		tabsLayout.setHeight(400);
		vLayout.addMember(tabsLayout);

		final TabSet topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);
		topTabSet.setWidth100();
		tabsLayout.addMember(topTabSet);
		final BaseListGrid consignItemGrid = getRenewItemGrid();
		// 寄售补库
		Tab tab_Base = new Tab(ui.getM().consignRenew_name());
		tab_Base.setPane(new ConsignRenewBaseForm(consignItemGrid));
		topTabSet.addTab(tab_Base);
		// 收发地址
		Tab tab_Addr = new Tab(ui.getM().address_info_title());
		tab_Addr.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));
		topTabSet.addTab(tab_Addr);

		HLayout gridLayout = new HLayout();
		gridLayout.setMembersMargin(5);
		VLayout protocolItemGrid = new VLayout();
		//寄售协议明细列表
		protocolItemGrid.setGroupTitle(ui.getM().msgListTitle(ui.getM().consignProtocol_item_name()));
		protocolItemGrid.setIsGroup(true);
		final BaseListGrid protocolGrid = getProtocolItemGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOLITEM_DS, new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						protocolGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("consignId", model.protocolId);
						protocolGrid.fetchData(criteria);
						protocolGrid.drawGrid();
						model.pactItemGrid = protocolGrid;
					}
				});
		protocolGrid.addRecordClickHandler(new RecordClickHandler() {

			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = protocolGrid.getSelectedRecord();
				if (record != null) {
					itemForm.form.editNewRecord();
					//保存
					itemForm.item_save.setTitle(ui.getM().btnSaveItem());
					itemForm.item_no.setValue(record.getAttribute("partNumber"));
					itemForm.item_type.setValue(record
							.getAttribute("m_ModelofApplicabilityCode"));
					itemForm.item_keys.setValue(record.getAttribute("keyword"));
					itemForm.item_unit.setValue(record
							.getAttribute("m_UnitOfMeasureCode"));
					itemForm.item_count.setValue(record
							.getAttribute("quantity"));
					itemForm.item_currency.setValue(record
							.getAttribute("currency"));
					itemForm.item_unitPrice.setValue(record
							.getAttribute("unitPrice"));
					itemForm.item_price.setValue(record.getAttribute("price"));
				}
			}
		});
		protocolItemGrid.addMember(protocolGrid);
		gridLayout.addMember(protocolItemGrid);

		VLayout renewItemGrid = new VLayout();
		//寄售补库明细列表
		renewItemGrid.setGroupTitle(ui.getM().msgListTitle(ui.getM().consignRenew_item_name()));
		renewItemGrid.setIsGroup(true);

		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNRENEW,
				DSKey.S_CONSIGNRENEWITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						consignItemGrid.setDataSource(dataSource);
						consignItemGrid.drawGrid();
						model.sheetItemGrid = consignItemGrid;
					}
				});
		consignItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				itemForm.form.editSelectedData(consignItemGrid);
				itemForm.item_save.setTitle(ui.getM().btnModify());
			}
		});
		renewItemGrid.addMember(consignItemGrid);
		gridLayout.addMember(renewItemGrid);
		vLayout.addMember(gridLayout);
		//寄售补库明细项
		itemForm = new ConsignRenewItemForm(ui.getM().consignRenew_item_name());
		vLayout.addMember(itemForm);
		return vLayout;
	}

	/**
	 * 寄售协议明细项表格
	 * 
	 * @return
	 */
	public BaseListGrid getProtocolItemGrid() {
		BaseListGrid lg = new BaseListGrid() {

			@Override
			public void drawGrid() {
				/**件号*/
				ListGridField fileld2 = new ListGridField("partNumber", ui.getM().title_partNumber());
				fileld2.setCanFilter(true);
				/** 数量 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				/** 单价 */
				ListGridField fileld7 = new ListGridField("unitPrice");
				Utils.formatPriceField(fileld7, "currency");
				fileld7.setCanFilter(true);
				/** 金额 */
				ListGridField fileld9 = new ListGridField("price");
				Utils.formatPriceField(fileld9, "currency");
				fileld9.setCanFilter(true);
				/** 备注 */
				ListGridField fileld10 = new ListGridField("remarkText");
				fileld10.setCanFilter(true);
				/** 寄售地点 */
				ListGridField fileld11 = new ListGridField("consignAddr");
				fileld11.setCanFilter(true);
				setFields(fileld2, fileld5, fileld7, fileld9, fileld10,
						fileld11);
			}
		};
		//数据加载中
		lg.setLoadingDataMessage(ui.getM().msgLoading());
		return lg;
	}

	/**
	 * 寄售补库清单明细项表格
	 * 
	 * @return
	 */
	public BaseListGrid getRenewItemGrid() {
		BaseListGrid lg = new BaseRightListGrid() {
			@Override
			public void drawGrid() {
				/**
				 * 件号
				 */
				ListGridField fileld2 = new ListGridField("partNumber");
				fileld2.setCanFilter(true);
				/**
				 * 关键字（件号描述）
				 */
				ListGridField fileld4 = new ListGridField("keyword");
				fileld4.setCanFilter(true);
				/**
				 * 数量
				 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				setFields(fileld2, fileld4, fileld5);
			}
		};
		lg.setLoadingDataMessage(ui.getM().msgLoading());
		return lg;
	}
}
