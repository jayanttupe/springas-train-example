package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

public class StockCheckResultPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "盘点结果管理维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "盘点结果管理模块";
		private String id;

		@Override
		public Canvas create() {
			StockCheckResultPanel panel = new StockCheckResultPanel();

			id = panel.getID();
			return panel;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {
		// 主Layout
		VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主列表Grid
		final StockCheckResultListgrid stockCheckResultManageListgrid = new StockCheckResultListgrid();
		stockCheckResultManageListgrid.setHeight("50%");
		// 获取数据源
		String modeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResult";
		String dsName = "stockCheckResult_dataSource";
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockCheckResultManageListgrid.setDataSource(dataSource);
						stockCheckResultManageListgrid.fetchData();
						stockCheckResultManageListgrid.drawStockCheckTrackResultManageListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		stockCheckResultManageListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockCheckResultManageListgrid.selectRecords(stockCheckResultManageListgrid.getSelection(), false);
					stockCheckResultManageListgrid.selectRecord(selectedRecord);
				}else if(stockCheckResultManageListgrid.getSelection().length == 1){
					selectedRecord = stockCheckResultManageListgrid.getSelection()[0];
					stockCheckResultManageListgrid.scrollToRow(stockCheckResultManageListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		final StockCheckResultItemListgrid stockCheckResultItemManageListgrid = new StockCheckResultItemListgrid();
		stockCheckResultItemManageListgrid.setHeight("100%");
		// 获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResult";
		String detaildsName = "stockCheckResultItem_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
                        
						stockCheckResultItemManageListgrid.setDataSource(dataSource);
						stockCheckResultItemManageListgrid.drawStockCheckResultItemManageListgrid();
					}
				});
		// 共用按钮面板
		StockCheckResultButtonPanel stockCheckResultManageButtonPanel = new StockCheckResultButtonPanel(
				stockCheckResultManageListgrid);

		stockCheckResultManageListgrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				String stockid = stockCheckResultManageListgrid.getSelectedRecord().getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("stockCheckID", stockid);
				stockCheckResultItemManageListgrid.filterData(criteria);

			}
		});
		
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(stockCheckResultItemManageListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("盘点结果信息");
		headSection.setItems(stockCheckResultManageListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("盘点结果明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 主Section容器
		SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 明细Section容器
		final SectionStack detailPanelSection = new SectionStack();
		detailPanelSection.setHeight100();
		detailPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		detailPanelSection.setAnimateSections(true);
		detailPanelSection.addSection(detailSection);
		
		// 加载各面板到容器
		mainPanelSection.addSection(headSection);
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(stockCheckResultManageButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
}
