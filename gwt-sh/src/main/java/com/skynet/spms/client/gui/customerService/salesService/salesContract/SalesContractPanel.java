package com.skynet.spms.client.gui.customerService.salesService.salesContract;

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
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
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
 * 销售合同管理主面板
 * 
 * @author fl
 * 
 */
public class SalesContractPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "销售合同";

	private SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();
	private SalesContractToolStrip mainToolStrip;
	private SaleContractGrid mainGrid;
	private SaleContractItemGrid itemGrid;
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
		// 销售单表格
		mainGrid = new SaleContractGrid();
		mainGrid.setHeight100();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACT_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						mainGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						mainGrid.fetchData(criteria);
						mainGrid.drawGrid();
						model.saleGrid = mainGrid;
					}
				});
		mainGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = mainGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.setAttribute("salesTemplate.id", id);
				itemGrid.fetchData(criteria);
				model.contractID = id;
			}
		});

		mainToolStrip = new SalesContractToolStrip(mainGrid);
		mainToolStrip.handlerManager.addHandler(
				FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						mainToolStrip.addMenuButton(MenuButtonBuilder.create(
								ContractIndexKey.SalesContractTemplateForOrder,
								mainGrid, MenuButtonBuilder.DELIVERY,
								DirectiveBusinessType.salesservice.name()), 4);
					}
				});

		v.addMember(mainToolStrip);

		mainGridSection = new SectionStackSection("销售合同信息");
		mainGridSection.addItem(mainGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		// 销售明细表格
		itemGrid = new SaleContractItemGrid();
		itemGrid.setHeight100();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemGrid.setDataSource(dataSource);
						itemGrid.drawGrid();
						model.pactItemGrid = itemGrid;
					}
				});
		itemGridSection = new SectionStackSection("销售合同明细");
		itemGridSection.setItems(itemGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);

		/** 把菜单项加到主面板中 **/
		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "销售合同";
		private String id;

		public Canvas create() {
			SalesContractPanel panel = new SalesContractPanel();
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