package com.skynet.spms.client.gui.partcatalog.repairableCatalog;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.gui.partcatalog.repairableCatalog.repairData.RepairDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.basicInformation.BasicInfoPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class RepairableCatalogPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "件号索引管理信息维护页面";
	private RepairableCatalogButtonToolBar repairableCatalogButtonToolBar;
	private IndexInfoListGrid indexInfoListGrid;
	
	public static class Factory implements PanelFactory{
		private String DESCRIPTION = "件号索引信息管理模块";
		private String id;
		
		public Canvas create() {
			RepairableCatalogPanel panel = new RepairableCatalogPanel();
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
		VLayout mainPanelLayout = new VLayout();
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);
		
		indexInfoListGrid = new IndexInfoListGrid();
		repairableCatalogButtonToolBar = new RepairableCatalogButtonToolBar("partCatalog.repairable", indexInfoListGrid);
		
		//barGridLayout.addMember(repairableCatalogButtonToolBar);
		barGridLayout.addMember(indexInfoListGrid);
		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("60%");
	
		Tab basicInfoTab = new Tab("基本数据","pieces/16/star_green.png");
		final BasicInfoPanel basicInfoPanel = new BasicInfoPanel(false);
		basicInfoTab.setPane(basicInfoPanel);
		childNodeTab.addTab(basicInfoTab);
		
		Tab RepairDataTab = new Tab("备件修理数据","pieces/16/star_green.png");
		final RepairDataPanel repairDataPanel = new RepairDataPanel();
		RepairDataTab.setPane(repairDataPanel);
		childNodeTab.addTab(RepairDataTab);
		
		/*Tab LicenseStatementTab = new Tab("维护许可证");
		final LicenseStatementPanel licenseStatementPanel = new LicenseStatementPanel();
		LicenseStatementTab.setPane(licenseStatementPanel);
		childNodeTab.addTab(LicenseStatementTab);*/
		
		indexInfoListGrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				indexInfoListGrid.fetchData(c,new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						Tab selectedTab = childNodeTab.getSelectedTab();
						ListGridRecord selectedRecord = indexInfoListGrid.getSelectedRecord();
						refreshTab(selectedTab, selectedRecord);
						//SC.say("" + indexInfoListGrid.getSelection().length);
					}
				});
			}
		});
		
		indexInfoListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					indexInfoListGrid.selectRecords(indexInfoListGrid.getSelection(), false);
					indexInfoListGrid.selectRecord(selectedRecord);
				}else if(indexInfoListGrid.getSelection().length == 1){
					selectedRecord = indexInfoListGrid.getSelection()[0];
					indexInfoListGrid.scrollToRow(indexInfoListGrid.getRecordIndex(selectedRecord));
				}
				
				Tab selectedTab = childNodeTab.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		childNodeTab.addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = childNodeTab.getSelectedTab();
				ListGridRecord selectedRecord = indexInfoListGrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);	
				
			}
		});
		//mainPanelLayout.addMember(repairableCatalogButtonToolBar);
		//mainPanelLayout.addMember(indexInfoListGrid);
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(childNodeTab);

		return mainPanelLayout;	
	}
	private void refreshTab(Tab selectedTab, ListGridRecord selectedRecord){
		Canvas panel = selectedTab.getPane();
		
		//基本数据
		if(panel instanceof BasicInfoPanel){
			BasicInfoPanel biPanel = (BasicInfoPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String basicInfoId = selectedRecord.getAttribute("m_BasicInformation.id");
				biPanel.fetchData(basicInfoId);
			}else{
				biPanel.clearFormValues();
			}
		}
		//备件修理目录
		if(panel instanceof RepairDataPanel){
			RepairDataPanel rdPanel = (RepairDataPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String partIndexId = selectedRecord.getAttribute("id");
				rdPanel.fetchData(partIndexId);
			}else{
				rdPanel.clearData();
			}	
		}
	}
	public String getIntro() {
		return DESCRIPTION;
	}
	
}
