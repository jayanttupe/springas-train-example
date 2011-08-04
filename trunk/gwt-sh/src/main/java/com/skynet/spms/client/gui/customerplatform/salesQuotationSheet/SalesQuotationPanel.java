package com.skynet.spms.client.gui.customerplatform.salesQuotationSheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationItemListGrid;
import com.skynet.spms.client.tools.UserTools;

public class SalesQuotationPanel extends ShowcasePanel {
	
	private static I18n ui = new I18n();
	
	private static final String DESCRIPTION =ui.getM().mod_salesQuotationSheet_desc();

	private SalesQuotationToolStrip toolStripPanel;
	private SalesQuotationListGrid listGrid;
	private SalesQuotationItemListGrid itemListGrid;

	private MainModelLocator modelLocator = MainModelLocator.getInstance();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_salesQuotationSheet_desc();
		private String id;

		public Canvas create() {
			SalesQuotationPanel panel = new SalesQuotationPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {
		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		// 报价单列表
		listGrid = new SalesQuotationListGrid();
		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET,
				DSKey.C_SALESQUOTATIONSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						loadInfo(dataSource);
					}
				});
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(DSKey.C_SALESQUOTATIONSHEET_ITEM,
				DSKey.C_SALESQUOTATIONSHEET_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
						modelLocator.itemDataSource = dataSource;
					}
				});

		// 初始化采购订单列表数据源
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET,
				DSKey.C_SALESREQUISITIONSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.requisitionDS = dataSource;
					}
				});
		//初始化采购订单明细数据源（报价单数据源在新增时创建）
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET_ITEM,
				DSKey.C_SALESREQUISITIONSHEET_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.requisitionItemDS = dataSource;
					}
				});
		
		// 报价单操作ToolScript
		toolStripPanel = new SalesQuotationToolStrip(listGrid);

		// 报价单明细列表
		itemListGrid = new SalesQuotationItemListGrid();

		// 点击报价单查询明细
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				loadItems();

			}
		});

		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 报价单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM().mod_salesQuotationSheet_name());//客户报价单
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		// 报价单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(
				ui.getM().mod_salesQuotationSheet_item_name());//报价单明细
		siItemStackSection.setItems(itemListGrid);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	/***************************************************************************
	 * 根据当前登录用户获得用户报价单
	 **************************************************************************/
	private void loadInfo(DataSource dataSource) {

		// 获取当前登录用户
		String userName = UserTools.getCurrentUserName();
		modelLocator.userName = userName;
		listGrid.setDataSource(dataSource);
		Criteria criteria = new Criteria();
		criteria.setAttribute("m_CustomerIdentificationCode.id", UserTools.getUserVo().getCustomerVo().getId());
		criteria.setAttribute("m_BussinessPublishStatusEntity.m_PublishStatus",
				"published");
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		listGrid.fetchData(criteria);
		listGrid.drawGrid();
		modelLocator.listGrid = listGrid;
		modelLocator.dataSource = dataSource;
	}

	/***************************************************************************
	 * 根据申请单ID获取申请单明细信息
	 **************************************************************************/
	private void loadItems() {
		// 获取申请单主键
		String id = listGrid.getSelectedRecord().getAttribute("id");
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", id);
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
		itemListGrid.drawGrid();
		modelLocator.itemListGrid = itemListGrid;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}