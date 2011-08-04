package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
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
 * 指令单
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementOrderPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private static final String DESCRIPTION = ui.getM()
			.mod_procurementOrder_name();
	private ProcurementOrderModelLocator model = ProcurementOrderModelLocator
			.getInstance();
	private ProcurementOrderToolStrip toolStripPanel;
	private ProcurementOrderListGrid listGrid;
	private ProcurementOrderItemListGrid itemListGrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_procurementOrder_name();
		private String id;

		public Canvas create() {
			ProcurementOrderPanel panel = new ProcurementOrderPanel();
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
		// 构建采购指令数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDER_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						listGrid.drawGrid();
						model.procurementOrderListGrid = listGrid;
					}
				});

		// 点击查看详细信息
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				final String leaseId = listGrid.getSelectedRecord()
						.getAttribute("id");
				model.doProcurementOrderId = leaseId;
				Criteria criteria = new Criteria();
				criteria.addCriteria("id", leaseId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				itemListGrid.fetchData(criteria);
				itemListGrid.drawGrid();
			}
		});

		listGrid.setCanRemoveRecords(false);// 删除按钮取消

		// 订单操作ToolScript
		toolStripPanel = new ProcurementOrderToolStrip(listGrid);

		// 订单明细列表
		itemListGrid = new ProcurementOrderItemListGrid();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDERITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.procurementOrderItemDs = dataSource;
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
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