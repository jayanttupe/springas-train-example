package com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.model.RequisitionModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetListGrid;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetItemListGrid;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.UserVo;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;
/*******************************************************************************
 * 客户订单
 * 
 * @author Tony FANG
 * 
 */
public class SalesRequisitionSheetPanel extends ShowcasePanel {
	
	private static I18n ui = new I18n();
	
	private static final String DESCRIPTION = ui.getM().mod_salesRequisitionSheet_desc();

	private SalesRequisitionSheetToolStrip toolStripPanel;
	private SalesRequisitionSheetListGrid listGrid;
	private SalesRequisitionSheetItemListGrid itemListGrid;

	private SalesRequisitionModel modelLocator = SalesRequisitionModel
			.getInstance();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_salesRequisitionSheet_desc();
		private String id;

		public Canvas create() {
			SalesRequisitionSheetPanel panel = new SalesRequisitionSheetPanel();
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
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		// 订单列表
		listGrid = new SalesRequisitionSheetListGrid();
		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET,
				DSKey.C_SALESREQUISITIONSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						loadInfo(dataSource);
					}
				});
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET_ITEM,
				DSKey.C_SALESREQUISITIONSHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.itemDataSource = dataSource;
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
					}
				});

		// 订单操作ToolScript
		toolStripPanel = new SalesRequisitionSheetToolStrip();

		// 订单明细列表
		itemListGrid = new SalesRequisitionSheetItemListGrid();

		// 点击询价单查询明细
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				loadItems();

			}
		});

		// 订单明细操作ToolStrip
//		itemToolStrip = new SalesRequisitionSheetItemToolStrip(itemListGrid);

		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM().mod_salesRequisitionSheet_name());//客户订单
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		// 订单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(
				ui.getM().mod_salesRequisitionSheetItem_name());//客户订单明细

		siItemStackSection.setItems(itemListGrid);
		// 容器添加明细操作按钮
		// siItemStackSection.setItems(itemListGrid, itemToolStrip);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	/***************************************************************************
	 * 获取已发布的报价单
	 **************************************************************************/
	private void loadInfo(DataSource dataSource) {
		modelLocator.dataSource = dataSource;
		listGrid.setDataSource(dataSource);
		Criteria criteria = new Criteria();
		criteria.setAttribute("m_CustomerIdentificationCode.id", UserTools.getUserVo().getCustomerVo().getId());
		criteria.setAttribute("_r", String.valueOf(Math.random()));
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