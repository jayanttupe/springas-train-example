package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.view;

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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 修改供应商寄售补库申请单
 * 
 * @author fl
 * 
 */
public class ConsignRenewViewWin extends BaseWindow {

	private static I18n ui = new I18n();
	public ConsignRenewViewWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ConsignRenewViewWin(String windowTitle, boolean isMax,
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
	public ConsignRenewItemViewForm itemForm;

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		//寄售补库信息
		this.setTitle(ui.getM().viewFormTitle(ui.getM().consignRenew_name()));
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
		Tab tab_Base = new Tab("寄售补库");
		tab_Base.setPane(new ConsignRenewBaseViewForm(consignItemGrid));
		topTabSet.addTab(tab_Base);
		// 收发地址
		Tab tab_Addr = new Tab("收发地址");
		tab_Addr.setPane(new BaseAddressForm(BaseAddressForm.Type.view));
		topTabSet.addTab(tab_Addr);

		HLayout gridLayout = new HLayout();
		gridLayout.setMembersMargin(5);
		DataSourceTool dataSourceTool = new DataSourceTool();

		VLayout renewItemGrid = new VLayout();
		renewItemGrid.setGroupTitle("寄售补库明细项");
		renewItemGrid.setIsGroup(true);

		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNRENEW,
				DSKey.S_CONSIGNRENEWITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						consignItemGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(model.consignRenewId);
						criteria.setAttribute("consignRenewId",
								builder.toString());
						consignItemGrid.fetchData(criteria);
						consignItemGrid.drawGrid();
						model.sheetItemGrid = consignItemGrid;
					}
				});
		consignItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				itemForm.form.editSelectedData(consignItemGrid);
			}
		});
		renewItemGrid.addMember(consignItemGrid);
		gridLayout.addMember(renewItemGrid);
		vLayout.addMember(gridLayout);
		itemForm = new ConsignRenewItemViewForm("补库清单明细项");
		vLayout.addMember(itemForm);
		return vLayout;
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
				/**
				 * 关键字（件号描述）
				 */
				ListGridField fileld4 = new ListGridField("keyword");
				/**
				 * 数量
				 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				setFields(fileld2, fileld4, fileld5);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}
}
