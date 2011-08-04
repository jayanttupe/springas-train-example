package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEvent;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEventHandler;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.commonOrder.ContractIndexKey;
import com.skynet.spms.client.gui.commonOrder.DirectiveBusinessType;
import com.skynet.spms.client.gui.commonOrder.MenuButtonBuilder;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
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
 * 回购合同主面板
 * 
 * @author fl
 * 
 */
public class BuyBackPactPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	//"回购合同"
	private static final String DESCRIPTION = ui.getM().mod_buyBackPact_name();
	private BuybackPactModelLocator locator=BuybackPactModelLocator.getInstance();
	private BuyBackPactToolStrip mainToolStrip;
	private BuyBackPactGrid  mainGrid;
	private BuyBackPactItemGrid itemGrid;
	private SectionStackSection mainGridSection;
	private SectionStackSection itemGridSection;
	private SectionStack mainStack;
	private VLayout v;
	
	public Canvas getViewPanel() {
		v = new VLayout(0);
		//主容器
		mainStack = new SectionStack();
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		//回购单表格
		mainGrid=new BuyBackPactGrid();
		DataSourceTool dataSourceTool=new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT, DSKey.C_BUYBACKPACT_DS, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				mainGrid.setDataSource(dataSource);
				mainGrid.fetchData();
				mainGrid.drawGrid();
				locator.pactGrid=mainGrid;
			}
		});
		mainGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = mainGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("template.id", id);
				itemGrid.fetchData(criteria);
			}
		});
		
		mainToolStrip=new BuyBackPactToolStrip(mainGrid);
		
		// 提交审批
		mainToolStrip.handlerManager.addHandler(FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						//挂载提货指令
						mainToolStrip.addMenuButton(
								MenuButtonBuilder
										.create(ContractIndexKey.BuybackContractTemplateManagerForOrder,
												mainGrid,MenuButtonBuilder.PICKUP,DirectiveBusinessType.customerbuyback.name()), 5);
					}
				});
		v.addMember(mainToolStrip);
		
		mainGridSection=new SectionStackSection(ui.getM().mod_buyBackPact_name());
		mainGridSection.addItem(mainGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		//回购单明细表格
		itemGrid=new BuyBackPactItemGrid();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT, DSKey.C_BUYBACKPACTITEM_DS,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				itemGrid.setDataSource(dataSource);
				itemGrid.drawGrid();
				locator.pactItemGrid=itemGrid;
			}
		});
		itemGridSection=new SectionStackSection(ui.getM().mod_buyBackPact_item_name());
		itemGridSection.setItems(itemGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);
		
		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		private String DESCRIPTION =BuyBackPactPanel.DESCRIPTION;
		private String id;

		public Canvas create() {
			BuyBackPactPanel panel = new BuyBackPactPanel();
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
