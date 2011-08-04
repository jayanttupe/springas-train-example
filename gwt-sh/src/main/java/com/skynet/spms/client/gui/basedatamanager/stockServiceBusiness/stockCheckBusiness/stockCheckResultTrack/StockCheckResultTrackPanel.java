package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResultTrack;

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

public class StockCheckResultTrackPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "盘点结果跟踪维护页面";

	private StockCheckResultTrackButtonPanel stockCheckResultTrackButtonPanel;
	private StockCheckResultTrackListgrid stockCheckResultTrackListgrid;
	private StockCheckResultItemTrackListgrid stockCheckResultItemTrackListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;

	private static String stockid;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "盘点结果跟踪模块";
		private String id;

		public Canvas create() {
			StockCheckResultTrackPanel panel = new StockCheckResultTrackPanel();

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

		// 主Layout
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();

		// 主列表Grid
		stockCheckResultTrackListgrid = new StockCheckResultTrackListgrid();
		stockCheckResultTrackListgrid.setHeight("50%");
		// 获取数据源
		String modeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResultTrack";
		String dsName = "stockCheckResultTrack_dataSource";
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockCheckResultTrackListgrid.setDataSource(dataSource);
						stockCheckResultTrackListgrid.fetchData();
						stockCheckResultTrackListgrid.drawStockCheckTrackListgrid();

					}
				});
		

		// ListGrid中的选择事件处理
		stockCheckResultTrackListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockCheckResultTrackListgrid.selectRecords(stockCheckResultTrackListgrid.getSelection(), false);
					stockCheckResultTrackListgrid.selectRecord(selectedRecord);
				}else if(stockCheckResultTrackListgrid.getSelection().length == 1){
					selectedRecord = stockCheckResultTrackListgrid.getSelection()[0];
					stockCheckResultTrackListgrid.scrollToRow(stockCheckResultTrackListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		stockCheckResultItemTrackListgrid = new StockCheckResultItemTrackListgrid();
		stockCheckResultItemTrackListgrid.setHeight("100%");
		// 获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckResultTrack";
		String detaildsName = "stockCheckResultTrackItem_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						stockCheckResultItemTrackListgrid.setDataSource(dataSource);
						stockCheckResultItemTrackListgrid.drawStockCheckResultItemManageListgrid();
					}
				});
		// 共用按钮面板
		stockCheckResultTrackButtonPanel = new StockCheckResultTrackButtonPanel(
				stockCheckResultTrackListgrid);

		stockCheckResultTrackListgrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				stockid = stockCheckResultTrackListgrid.getSelectedRecord().getAttribute("checkNumber");
				Criteria criteria = new Criteria();
				criteria.addCriteria("checkNumber", stockid);
				stockCheckResultItemTrackListgrid.filterData(criteria);

			}
		});
		
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(stockCheckResultItemTrackListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("盘点结果跟踪信息");
		headSection.setItems(stockCheckResultTrackListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("盘点结果跟踪明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 主Section容器
		mainPanelSection = new SectionStack();
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
		mainPanelLayout.addMember(stockCheckResultTrackButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
