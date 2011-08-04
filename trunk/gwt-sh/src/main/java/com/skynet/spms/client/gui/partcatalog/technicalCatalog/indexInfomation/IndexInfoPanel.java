package com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.AircraftConfiguration.AircraftConfigurationPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData.ApplicationDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData.ApplicationDataPanel2;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.basicInformation.BasicInfoPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.customsClearanceData.CustomsClearanceDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.optionalData.OptionalDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.partBagItem.PartBagItemPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.technicalData.TechnicalDataPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class IndexInfoPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "件号索引管理信息维护页面";

	private IndexInfoButtonToolBar indexInfoButtonToolBar;
	private IndexInfoListGrid indexInfoListGrid;
	private VLayout mainPanelLayout;
	private Label lblPartNumber;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "件号索引信息管理模块";
		private String id;
		

		public Canvas create() {
			IndexInfoPanel panel = new IndexInfoPanel();
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
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);
		
		indexInfoListGrid = new IndexInfoListGrid();	
		indexInfoButtonToolBar = new IndexInfoButtonToolBar("partCatalog.technical", indexInfoListGrid);
		
		barGridLayout.addMember(indexInfoButtonToolBar);
		barGridLayout.addMember(indexInfoListGrid);
		
		//中间的所选件号提示
		HLayout tipLayout = new HLayout(5);
		tipLayout.setHeight(20);
		Label lblTip = new Label("所选件号：");
		lblPartNumber = new Label();
		lblPartNumber.setWidth(200);
		tipLayout.addMember(lblTip);
		tipLayout.addMember(lblPartNumber);
		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("59%");
		
		//基本数据
		Tab basicInfoTab = new Tab(ConstantsUtil.partCatalogConstants.basicInfo(),"pieces/16/star_green.png");
		final BasicInfoPanel basicInfoPanel = new BasicInfoPanel(true);
		basicInfoTab.setPane(basicInfoPanel);
		childNodeTab.addTab(basicInfoTab);
		//应用数据
		Tab applicationDataTab = new Tab(ConstantsUtil.partCatalogConstants.applicationData(),"pieces/16/star_green.png");
		final ApplicationDataPanel applicationDataPanel = new ApplicationDataPanel(true);
		applicationDataTab.setPane(applicationDataPanel);
		childNodeTab.addTab(applicationDataTab);
		//技术数据
		Tab technicalDataTab = new Tab(ConstantsUtil.partCatalogConstants.technicalData(),"pieces/16/star_green.png");
		final TechnicalDataPanel technicalDataPanel = new TechnicalDataPanel(true);
		technicalDataTab.setPane(technicalDataPanel);
		childNodeTab.addTab(technicalDataTab);
		//报关数据
		Tab customsClearanceDataPanelTab = new Tab(ConstantsUtil.partCatalogConstants.customsClearanceData(),"pieces/16/star_green.png");
		final CustomsClearanceDataPanel customsClearanceDataPanel=new CustomsClearanceDataPanel(true);
		customsClearanceDataPanelTab.setPane(customsClearanceDataPanel);
		childNodeTab.addTab(customsClearanceDataPanelTab);
		//可互换备件
		Tab optionanDataTab = new Tab(ConstantsUtil.partCatalogConstants.optionalData(),"pieces/16/star_green.png");
		final OptionalDataPanel optionanDataPanel = new OptionalDataPanel(true);
		optionanDataTab.setPane(optionanDataPanel);
		childNodeTab.addTab(optionanDataTab);
		//飞机构型
		Tab aircraftConfigurationTab = new Tab(ConstantsUtil.partCatalogConstants.aircraftConfiguration(),"pieces/16/star_green.png");
		final AircraftConfigurationPanel aircraftConfigurationPanel = new AircraftConfigurationPanel();
		aircraftConfigurationTab.setPane(aircraftConfigurationPanel);
		childNodeTab.addTab(aircraftConfigurationTab);
		
		//航材包信息
		Tab partBagItemTab = new Tab(ConstantsUtil.partCatalogConstants.partBagItem(),"pieces/16/star_green.png");
		final PartBagItemPanel partBagItemPanel = new PartBagItemPanel(true);
		partBagItemTab.setPane(partBagItemPanel);
		childNodeTab.addTab(partBagItemTab);
		
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
		
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(tipLayout);
		mainPanelLayout.addMember(childNodeTab);

		return mainPanelLayout;
	}
	private void refreshTab(Tab selectedTab, ListGridRecord selectedRecord){
		Canvas panel = selectedTab.getPane();
		if(indexInfoListGrid.getSelection().length == 1){
			lblPartNumber.setContents(selectedRecord.getAttribute("manufacturerPartNumber"));
		}else{
			lblPartNumber.setContents("");
		}
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
		//应用数据
		if(panel instanceof ApplicationDataPanel){
			ApplicationDataPanel adPanel = (ApplicationDataPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String applicationDataId = selectedRecord.getAttribute("m_PartApplicationData.id");
				adPanel.fetchData(applicationDataId);
			}else{
				adPanel.clearFormValues();
			}	
		}
		
		//技术数据
		if(panel instanceof TechnicalDataPanel){
			TechnicalDataPanel tdPanel = (TechnicalDataPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String partTechnicalDataId = selectedRecord.getAttribute("m_PartTechnicalData.id");
				tdPanel.fetchData(partTechnicalDataId);
			}else{
				tdPanel.clearFormValues();
			}	
		}
		
		//报关数据
		if(panel instanceof CustomsClearanceDataPanel){
			CustomsClearanceDataPanel ccdPanel = (CustomsClearanceDataPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String customsClearanceDataId = selectedRecord.getAttribute("m_CustomsClearanceData.id");
				ccdPanel.fetchData(customsClearanceDataId);
			}else{
				ccdPanel.clearFormValues();
			}
		}
		
		//可互换备件
		if(panel instanceof OptionalDataPanel){
			OptionalDataPanel adPanel = (OptionalDataPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String partIndexId = selectedRecord.getAttribute("id");
				adPanel.fetchData(partIndexId);
			}else{
				adPanel.clearData();
			}
			
		}
		//飞机构型
		if(panel instanceof AircraftConfigurationPanel){
			AircraftConfigurationPanel acPanel = (AircraftConfigurationPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String partIndexId = selectedRecord.getAttribute("id");
				acPanel.fetchData(partIndexId); 
			}else{
				acPanel.clearData();
			}   
		}
		
		//可互换备件
		if(panel instanceof PartBagItemPanel){
			PartBagItemPanel pbiPanel = (PartBagItemPanel)panel;
			if(indexInfoListGrid.getSelection().length == 1){
				String partIndexId = selectedRecord.getAttribute("id");
				pbiPanel.fetchData(partIndexId);
			}else{
				pbiPanel.clearData();
			}
			
		}
		
	}
	
	public String getIntro() {
		return DESCRIPTION;
	} 
}
