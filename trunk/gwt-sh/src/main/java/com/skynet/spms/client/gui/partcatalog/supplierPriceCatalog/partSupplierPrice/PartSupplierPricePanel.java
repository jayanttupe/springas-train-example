package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.partSupplierPrice;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.editionsInfo.EditionsInfoPanel;
import com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.supplierSalesPrice.SupplierSalesPricePanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.basicInformation.BasicInfoPanel;
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

public class PartSupplierPricePanel extends ShowcasePanel{
	private static final String DESCRIPTION = "供应商价格管理模块";
	private PartSupplierPriceButtonToolBar partSupplierPriceButtonToolBar;
	private PartSupplierPriceListGrid partSupplierPriceListGrid;
	
	public static class Factory implements PanelFactory{
		private String DESCRIPTION = "供应商价格管理模块";
		private String id;
		
		public Canvas create() {
			PartSupplierPricePanel panel = new PartSupplierPricePanel();
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
		
		partSupplierPriceListGrid = new PartSupplierPriceListGrid();
		partSupplierPriceButtonToolBar = new PartSupplierPriceButtonToolBar("partCatalog.supplierPrice", partSupplierPriceListGrid);
		
		barGridLayout.addMember(partSupplierPriceButtonToolBar);
		barGridLayout.addMember(partSupplierPriceListGrid);
		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("60%");
		
		Tab basicInfoTab = new Tab("基本数据","pieces/16/star_green.png");
		final BasicInfoPanel basicInfoPanel = new BasicInfoPanel(false);
		basicInfoTab.setPane(basicInfoPanel);
		childNodeTab.addTab(basicInfoTab);
	
		Tab supplierSalesPriceTab = new Tab("供应商销售价格","pieces/16/star_green.png");
		final SupplierSalesPricePanel supplierSalesPricePanel = new SupplierSalesPricePanel();
		supplierSalesPriceTab.setPane(supplierSalesPricePanel);
		childNodeTab.addTab(supplierSalesPriceTab);
		
		Tab editionsInfoTab= new Tab("版次信息","pieces/16/star_green.png");
		final EditionsInfoPanel editionsInfoPanel=new EditionsInfoPanel("partCatalog.supplierPrice","partSupplierPrice_dataSource");
		editionsInfoTab.setPane(editionsInfoPanel);
		childNodeTab.addTab(editionsInfoTab);
		
		partSupplierPriceListGrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				partSupplierPriceListGrid.fetchData(c,new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						Tab selectedTab = childNodeTab.getSelectedTab();
						ListGridRecord selectedRecord = partSupplierPriceListGrid.getSelectedRecord();
						refreshTab(selectedTab, selectedRecord); 
					}
				});
			}
		});

		partSupplierPriceListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					partSupplierPriceListGrid.selectRecords(partSupplierPriceListGrid.getSelection(), false);
					partSupplierPriceListGrid.selectRecord(selectedRecord);
				}else if(partSupplierPriceListGrid.getSelection().length == 1){
					selectedRecord = partSupplierPriceListGrid.getSelection()[0];
					partSupplierPriceListGrid.scrollToRow(partSupplierPriceListGrid.getRecordIndex(selectedRecord));
				}
				
				Tab selectedTab = childNodeTab.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		childNodeTab.addTabSelectedHandler(new TabSelectedHandler() {	
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = event.getTab();
				ListGridRecord selectedRecord = partSupplierPriceListGrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		
		//mainPanelLayout.addMember(partSupplierPriceButtonToolBar);
		//mainPanelLayout.addMember(partSupplierPriceListGrid);
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(childNodeTab);

		return mainPanelLayout;	
	}
	private void refreshTab(Tab selectedTab, ListGridRecord selectedRecord){
		Canvas panel = selectedTab.getPane();
		//基本数据
		if(panel instanceof BasicInfoPanel){
			BasicInfoPanel biPanel = (BasicInfoPanel)panel;
			if(partSupplierPriceListGrid.getSelection().length == 1){
				String basicInfoId = selectedRecord.getAttribute("m_PartIndex.m_BasicInformation.id");
				biPanel.fetchData(basicInfoId);
			}else{
				biPanel.clearFormValues();
			}
			
		}
		//供应商销售价格
		if(panel instanceof SupplierSalesPricePanel){
			SupplierSalesPricePanel sspPanel = (SupplierSalesPricePanel)panel;
			if(partSupplierPriceListGrid.getSelection().length == 1){
				String spSalesPriceId = selectedRecord.getAttribute("m_SupplierSalesPrice.id");
				sspPanel.fetchData(spSalesPriceId);
			}else{
				sspPanel.clearFormValues();
			}	
		}
		//版次信息
		if(panel instanceof EditionsInfoPanel){
			EditionsInfoPanel adPanel = (EditionsInfoPanel)panel;
			if(partSupplierPriceListGrid.getSelection().length == 1){
				String saleReleaseId = selectedRecord.getAttribute("id");
				adPanel.fetchData(saleReleaseId);
			}else{
				adPanel.clearFormValues();
			}
		}	
	}
	public String getIntro() {
		return DESCRIPTION;
	}
}
