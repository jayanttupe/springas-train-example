package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockPolicy;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

public class StockPolicyPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "库存策略管理信息维护页面";

	private StockPolicyButtonPanel stockPolicyButtonPanel;
	private StockPolicyListgrid stockPolicyListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库存策略管理管理模块";
		private String id;

		public Canvas create() {
			StockPolicyPanel panel = new StockPolicyPanel();

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
		// 获取数据源
		String modeName = "stockServiceBusiness.basicData.stockPolicy";
		String dsName = "stockPolicy_dataSource";

		// 主Layout
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 主列表Grid
		stockPolicyListgrid = new StockPolicyListgrid();
		stockPolicyListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		stockPolicyListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockPolicyListgrid.selectRecords(stockPolicyListgrid.getSelection(), false);
					stockPolicyListgrid.selectRecord(selectedRecord);
				}else if(stockPolicyListgrid.getSelection().length == 1){
					selectedRecord = stockPolicyListgrid.getSelection()[0];
					stockPolicyListgrid.scrollToRow(stockPolicyListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockPolicyListgrid.setDataInfo(dataInfo);
						stockPolicyListgrid.setDataSource(dataSource);
						stockPolicyListgrid.fetchData();
						stockPolicyListgrid.drawStockPolicyListgrid();
					}
				});

		// 共用按钮面板
		stockPolicyButtonPanel = new StockPolicyButtonPanel(stockPolicyListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("库存策略信息");
		detailPanelSection.setItems(stockPolicyListgrid);
		detailPanelSection.setItems(stockPolicyButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(stockPolicyButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}