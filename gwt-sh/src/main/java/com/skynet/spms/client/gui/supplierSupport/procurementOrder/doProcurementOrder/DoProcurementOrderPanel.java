package com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.ProcurementOrderListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 执行采购指令
 * 
 * @author Tony FANG
 * 
 */
public class DoProcurementOrderPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private static final String DESCRIPTION = ui.getB().doProcurementOrder();
	private ProcurementOrderModelLocator model = ProcurementOrderModelLocator
			.getInstance();
	private DoProcurementOrderToolStrip toolStripPanel;
	private ProcurementOrderListGrid listGrid;
	private DoProcurementOrderItemListGrid itemListGrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getB().doProcurementOrder();
		private String id;

		public Canvas create() {
			DoProcurementOrderPanel panel = new DoProcurementOrderPanel();
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

	public Canvas getViewPanel() {
		// 订单列表
		listGrid = new ProcurementOrderListGrid();
		// 构建执行采购指令数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDER_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("publishState", "publish");
						listGrid.fetchData(criteria);
						listGrid.drawGrid();
						model.procurementOrderListGrid = listGrid;
						model.doProcurementOrderListGrid = listGrid;
					}
				});
		// 点击查看详细信息
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				final String leaseId = listGrid.getSelectedRecord()
						.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("id", leaseId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				itemListGrid.fetchData(criteria);
				itemListGrid.drawGrid();
				// model.procurementOrderListGrid = listGrid;
			}
		});

		listGrid.setCanRemoveRecords(false);// 删除按钮取消

		// 订单操作ToolScript
		toolStripPanel = new DoProcurementOrderToolStrip();

		// 订单明细列表
		itemListGrid = new DoProcurementOrderItemListGrid();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDERITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
						model.procurementOrderItemDs = dataSource;
					}
				});

		/** 初始化调拨单DS **/
		// 初始化调拨单数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTTRANSFERORDER,
				DSKey.S_PROCUREMENTTRANSFERORDER_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.procurementTransferDS = dataSource;
					}
				});
		// 初始化调拨单明细数据源
		dataSourceTool.onCreateDataSource(
				DSKey.S_PROCUREMENTTRANSFERORDER_ITEM,
				DSKey.S_PROCUREMENTTRANSFERORDER_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.procurementTransferItemDS = dataSource;
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();

		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_procurementOrder_name());
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		// 明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(ui
				.getB().procurementOrderItems());
		siItemStackSection.setItems(itemListGrid);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}