package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEvent;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEventHandler;
import com.skynet.spms.client.gui.commonOrder.ContractIndexKey;
import com.skynet.spms.client.gui.commonOrder.DirectiveBusinessType;
import com.skynet.spms.client.gui.commonOrder.MenuButtonBuilder;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 采购合同面板
 * 
 * @author gqr
 * 
 */
public class ProcurementContractPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "采购合同";
	private ProcurementContractModelLocator modelLocator = ProcurementContractModelLocator
			.getInstance();
	private ProcurementContractToolStrip toolStripPanel;
	private ProcurementContractListGrid listGrid;
	private ProcurementContractItemListGrid itemListGrid;
	private SectionStackSection mainGridSection;
	private SectionStackSection itemGridSection;
	private SectionStack mainStack;
	private VLayout v;

	public Canvas getViewPanel() {
		v = new VLayout(0);
		// 主容器
		mainStack = new SectionStack();
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		// 采购表格
		listGrid = new ProcurementContractListGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACT_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						listGrid.drawGrid();
						modelLocator.pactGrid = listGrid;
					}
				});
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = listGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("template.id", id);
				itemListGrid.fetchData(criteria);
			}
		});

		toolStripPanel = new ProcurementContractToolStrip(listGrid);

		toolStripPanel.handlerManager.addHandler(
				FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						toolStripPanel.addMenuButton(
								MenuButtonBuilder
										.create(ContractIndexKey.ProcurementContractTemplateManagerForOrder,
												listGrid,MenuButtonBuilder.PICKUP,DirectiveBusinessType.procurement.name()), 5);
					}
				});
		v.addMember(toolStripPanel);

		mainGridSection = new SectionStackSection("采购合同信息");
		mainGridSection.addItem(listGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		// 采购明细表格
		itemListGrid = new ProcurementContractItemListGrid();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemListGrid.setDataSource(dataSource);
						itemListGrid.drawGrid();
						modelLocator.pactItemGrid = itemListGrid;
					}
				});
		itemGridSection = new SectionStackSection("采购合同明细信息");
		itemGridSection.setItems(itemListGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);

		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "采购合同";
		private String id;

		public Canvas create() {
			ProcurementContractPanel panel = new ProcurementContractPanel();
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

	public String getIntro() {
		return DESCRIPTION;
	}

}
