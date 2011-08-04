package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 修改寄售协议
 * 
 * @author fl
 * 
 */
public class ConsignProtocolViewWin extends BaseWindow {

	private static I18n ui = new I18n();
	
	public ConsignProtocolViewWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public ConsignProtocolViewWin(String windowTitle, boolean isMax,
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

	private ConsignProtocolItemViewForm itemForm;
	private BaseListGrid itemGrid;
	private ConsignProtocolModel model;
	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		//寄售协议信息
		this.setTitle(ui.getM().modifyFormTitle(ui.getM().consignProtocol_name()));
		model=ConsignProtocolModel.getInstance();
		VLayout vLayout = new VLayout();
		vLayout.setMembersMargin(15);
		vLayout.setHeight100();

		VLayout tabsLayout = new VLayout();
		tabsLayout.setHeight(400);
		vLayout.addMember(tabsLayout);

		final TabSet topTabSet = new TabSet();
		topTabSet.setWidth100();
		tabsLayout.addMember(topTabSet);
		itemGrid = getProtocolItemGrid();
		// 协议基本信息
		Tab tab_Base = new Tab(ui.getM().consignProtocol_base_info_title());
		ConsignProtocolBaseViewForm baseForm=new ConsignProtocolBaseViewForm(itemGrid);
		tab_Base.setPane(baseForm);
		topTabSet.addTab(tab_Base);
		// 协议条款
		Tab tab_Items = new Tab(ui.getM().cconsignProtocol_provision_title());
		tab_Items.setPane(new ContractProvisionPanel(baseForm.form, TagEnum.RepairInspectClaimContractTemplate));
		topTabSet.addTab(tab_Items);
		// 附件
		Tab tab_affix = new Tab(ui.getM().attachment_title());
		tab_affix.setPane(new AttachmentViewForm());
		topTabSet.addTab(tab_affix);
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOLITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria criteria=new Criteria();
						criteria.setAttribute("consignId",model.protocolId);
						itemGrid.setDataSource(dataSource);
						itemGrid.fetchData(criteria);
						itemGrid.drawGrid();
					}
				});
		itemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				itemForm.form.editSelectedData(itemGrid);
			}
		});
		vLayout.addMember(itemGrid);
		//寄售协议明细
		itemForm = new ConsignProtocolItemViewForm(ui.getM().consignProtocol_item_name(), itemGrid);
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
				/** 备注*/
				ListGridField fileld10 = new ListGridField("remarkText");
				fileld10.setCanFilter(true);
				/** 寄售地点*/
				ListGridField fileld11 = new ListGridField("consignAddr");
				fileld11.setCanFilter(true);
				setFields(fileld2, fileld5, fileld7,fileld9,
						fileld10, fileld11);
			}
		};
		//数据加载中，请稍后......
		lg.setLoadingDataMessage(ui.getM().msgLoading());
		lg.setHeight(120);
		return lg;
	}
}
