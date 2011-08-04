package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class StockRoomPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "库房管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库房管理模块";
		private String id;
		
		public Canvas create() {
			StockRoomPanel panel = new StockRoomPanel();
			
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
		// 头部列表Grid
		final StockRoomListgrid stockRoomListgrid = new StockRoomListgrid();
		stockRoomListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.basicData.stockRoom";
		String headDSName = "stockRoom_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockRoomListgrid.setDataInfo(dataInfo);
						
						stockRoomListgrid.setDataSource(dataSource);
						stockRoomListgrid.fetchData();
						stockRoomListgrid.drawStockRoomListgrid();
					}
				});
		
		stockRoomListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
			}
		});

		// ListGrid中的选择事件处理
		stockRoomListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockRoomListgrid.selectRecords(stockRoomListgrid.getSelection(), false);
					stockRoomListgrid.selectRecord(selectedRecord);
				}else if(stockRoomListgrid.getSelection().length == 1){
					selectedRecord = stockRoomListgrid.getSelection()[0];
					stockRoomListgrid.scrollToRow(stockRoomListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 区域明细列表Grid
		final StockAreaListgrid stockAreaListgrid = new StockAreaListgrid();
		stockAreaListgrid.setHeight100();
		stockAreaListgrid.setCanEdit(true);
		stockAreaListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool areaDST = new DataSourceTool();
		String areaModeName = "stockServiceBusiness.basicData.stockRoom";
		String areaDSName = "stockArea_dataSource";
		areaDST.onCreateDataSource(areaModeName, areaDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockAreaListgrid.setDataSource(dataSource);
						stockAreaListgrid.drawStockAreaListgrid();
					}
				});

		// 逻辑库明细列表Grid
		final LogicStockListgrid logicStockListgrid = new LogicStockListgrid();
		logicStockListgrid.setHeight100();
		logicStockListgrid.setCanEdit(true);
		logicStockListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool logicDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.basicData.stockRoom";
		String detailDSName = "logicStock_dataSource";
		logicDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						logicStockListgrid.setDataSource(dataSource);
						logicStockListgrid.drawLogicStockListgrid();
					}
				});
		
		// 根据选择的仓库，取得相应的逻辑库
		stockRoomListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				stockAreaListgrid.setData(new ListGridRecord[]{});
				stockAreaListgrid.fetchData(new Criteria("stockRoomID", stockRoomListgrid.getSelectedRecord().getAttribute("id").toString()));
				
				logicStockListgrid.setData(new ListGridRecord[]{});
				logicStockListgrid.fetchData(new Criteria("stockRoomID", stockRoomListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});
		
		// 主Layout
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		final SectionStack mainSectionStack = new SectionStack();
		mainSectionStack.setHeight100();
		mainSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainSectionStack.setAnimateSections(true);
		
		VLayout headVLayout = new VLayout();
		headVLayout.addMember(stockRoomListgrid);
		headVLayout.setHeight("50%");
		headVLayout.setShowResizeBar(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("库房信息列表");
		headSection.setItems(headVLayout);
		headSection.setExpanded(true);

		final TabSet deatilsTabSet = new TabSet();  
		deatilsTabSet.setTabBarPosition(Side.TOP);
		deatilsTabSet.setHeight("50%");
		deatilsTabSet.setWidth100();

		Tab tTab1 = new Tab("库房区域");  
		tTab1.setPane(stockAreaListgrid);
		deatilsTabSet.addTab(tTab1);
   
		Tab tTab2 = new Tab("逻辑库");  
		tTab2.setPane(logicStockListgrid);
		deatilsTabSet.addTab(tTab2);
	       
		// 共用按钮面板
		final StockRoomButtonPanel stockroomManageButtonPanel = new StockRoomButtonPanel(stockRoomListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addMember(deatilsTabSet);
		mainPanelLayout.addMember(stockroomManageButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
