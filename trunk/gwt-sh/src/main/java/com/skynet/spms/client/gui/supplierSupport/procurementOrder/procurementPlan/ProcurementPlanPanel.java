package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
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
 * 采购计划
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementPlanPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private MainModelLocator model = MainModelLocator.getInstance();
	private ProcurementPlanToolStrip toolStripPanel;
	private ProcurementPlanListGrid listGrid;
	private ProcurementPlanItemListGrid itemListGrid;

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_procurementPlan_name();
		private String id;

		public Canvas create() {
			ProcurementPlanPanel panel = new ProcurementPlanPanel();
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
		/** 主容器 **/
		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();

		// 订单列表
		listGrid = new ProcurementPlanListGrid();
		// 构建采购计划数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTPLAN,
				DSKey.S_PROCUREMENTPLAN_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						listGrid.buildListGrid();
						model.procurementPlanListGrid = listGrid;
					}
				});
		// 初始化附件数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTPLAN,
				DSKey.S_PROCUREMENTPLAN_ATTACHMENT_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.attachmentDs = dataSource;
					}
				});

		// 点击查看详细信息
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				final String leaseId = listGrid.getSelectedRecord()
						.getAttribute("id");
				model.Pid = leaseId;
				Criteria criteria = new Criteria();
				criteria.addCriteria("id", leaseId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				itemListGrid.fetchData(criteria);
				itemListGrid.buildListGrid();
				model.procurementPlanItemListGrid = itemListGrid;

			}
		});
		// 订单操作ToolScript
		toolStripPanel = new ProcurementPlanToolStrip(listGrid);
		// 订单明细列表
		itemListGrid = new ProcurementPlanItemListGrid();
		// 构建采购明细数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTPLAN,
				DSKey.S_PROCUREMENTPLANITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.procurementPlanItemListGrid_ds = dataSource;
						itemListGrid.setDataSource(dataSource);
						itemListGrid.buildListGrid();
					}
				});
		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_procurementPlan_name());
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		// 订单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(ui
				.getM().mod_procurementPlan_name());
		siItemStackSection.setItems(itemListGrid);
		// siItemStackSection.setItems(itemListGrid, itemToolStrip);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}
}
