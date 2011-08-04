package com.skynet.spms.client.gui.customerplatform.DoQuotationPanel;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationItemListGrid;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationItemToolStrip;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationListGrid;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/*******************************************************************************
 * 客户询价
 * 
 * @author Tony FANG
 * 
 */
public class DoQuotationPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private static final String DESCRIPTION = ui.getM().mod_salesInquiry_desc();
	

	private DoQuotationToolStrip toolStripPanel;
	private DoQuotationItemToolStrip itemToolStrip;
	private DoQuotationListGrid listGrid;
	private DoQuotationItemListGrid itemListGrid;

	private SalesInquiryModel modelLocator = SalesInquiryModel.getInstance();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_salesInquiry_desc();
		private String id;

		public Canvas create() {
			DoQuotationPanel panel = new DoQuotationPanel();
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
		// 询价单列表
		listGrid = new DoQuotationListGrid();
		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_DOQUOTATION,
				DSKey.C_DOQUOTATION_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.listGrid=listGrid;
						loadInfo(dataSource);// 初始化用户询价单
						

					}
				});
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(DSKey.C_DOQUOTATION_ITEM,
				DSKey.C_DOQUOTATION_ITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.itemDataSource = dataSource;
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
					}
				});

		// 询价单操作ToolScript
		toolStripPanel = new DoQuotationToolStrip(listGrid);

		// 询价单明细列表
		itemListGrid = new DoQuotationItemListGrid();

		// 点击询价单查询明细
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				loadItems();
			}
		});

		// 订单明细操作ToolStrip
		itemToolStrip = new DoQuotationItemToolStrip(itemListGrid);

		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setHeight100();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 询价单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM().mod_salesInquiry_list_title());
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		// 询价单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(
				ui.getM().mod_salesInquiry_item_list_title());

		siItemStackSection.setItems(itemListGrid);
		// 容器添加明细操作按钮
		// siItemStackSection.setItems(itemListGrid,
		// itemToolStrip);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	/***************************************************************************
	 * 根据当前登录用户获得用户报价单
	 **************************************************************************/
	private void loadInfo(DataSource dataSource) {
		
		modelLocator.dataSource = dataSource;
		// 获取当前登录用户
		String userName = UserTools.getCurrentUserName();
		listGrid.setDataSource(dataSource);
		Criteria criteria = new Criteria();
		criteria.setAttribute("m_CustomerIdentificationCode.id", UserTools.getUserVo().getCustomerVo().getId());
		listGrid.fetchData(criteria);
		listGrid.drawGrid();
		modelLocator.listGrid = listGrid;
		
	}

	/***************************************************************************
	 * 根据申请单ID获取申请单明细信息
	 **************************************************************************/
	private void loadItems() {
		// 获取申请单主键
		String id = listGrid.getSelectedRecord().getAttribute("id");
		Criteria criteria = new Criteria();
		criteria.setAttribute("id", id);
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
		itemListGrid.drawGrid();
		modelLocator.itemListGrid = itemListGrid;

	}

	public String getIntro() {
		return ui.getM().mod_salesInquiry_desc();
	}
}