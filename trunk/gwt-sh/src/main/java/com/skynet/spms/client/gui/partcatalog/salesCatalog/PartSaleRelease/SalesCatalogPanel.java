package com.skynet.spms.client.gui.partcatalog.salesCatalog.PartSaleRelease;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.discountMatrix.DiscountItemPanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.editionsInfo.EditionsInfoPanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.partSupplierPriceIndex.PartSupplierPriceIndexPanel;
import com.skynet.spms.client.gui.partcatalog.salesCatalog.salesPrice.SalesPricePanel;
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
public class SalesCatalogPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "备件销售管理信息维护页面";

	private SalesCatalogButtonToolBar salesCatalogButtonToolBar;
	private SalesCatalogListGrid salesCatalogListGrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "备件销售信息管理模块";
		private String id;

		public Canvas create() {
			SalesCatalogPanel panel = new SalesCatalogPanel();
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
		
		salesCatalogListGrid = new SalesCatalogListGrid();
		salesCatalogButtonToolBar = new SalesCatalogButtonToolBar("partCatalog.sales", salesCatalogListGrid);
		barGridLayout.addMember(salesCatalogButtonToolBar);
		barGridLayout.addMember(salesCatalogListGrid);
		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("60%");
		Tab basicInfoTab = new Tab("基本数据","pieces/16/star_green.png");
		final BasicInfoPanel basicInfoPanel = new BasicInfoPanel(false);
		basicInfoTab.setPane(basicInfoPanel);
		childNodeTab.addTab(basicInfoTab);
		
	    Tab partSupplierPriceIndexTab=new Tab("供货商价格索引","pieces/16/star_green.png");
		final PartSupplierPriceIndexPanel partSupplierPricePanel=new PartSupplierPriceIndexPanel();
		partSupplierPriceIndexTab.setPane(partSupplierPricePanel);
		childNodeTab.addTab(partSupplierPriceIndexTab);

		Tab salesPricePanelTab=new Tab("销售价格","pieces/16/star_green.png");
		final SalesPricePanel salesPricePanel=new SalesPricePanel();
		salesPricePanelTab.setPane(salesPricePanel);
		childNodeTab.addTab(salesPricePanelTab);

		Tab discountItemPanelTab=new Tab("销售折扣","pieces/16/star_green.png");
		final DiscountItemPanel discountItemPanel=new DiscountItemPanel();
		discountItemPanelTab.setPane(discountItemPanel);
		childNodeTab.addTab(discountItemPanelTab);
		
		Tab editionsInfoTab= new Tab("版次信息","pieces/16/star_green.png");
		final EditionsInfoPanel editionsInfoPanel=new EditionsInfoPanel("partCatalog.sales","partSaleRelease_dataSource");
		editionsInfoTab.setPane(editionsInfoPanel);
		childNodeTab.addTab(editionsInfoTab);
		
		salesCatalogListGrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				salesCatalogListGrid.fetchData(c,new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						Tab selectedTab = childNodeTab.getSelectedTab();
						ListGridRecord selectedRecord = salesCatalogListGrid.getSelectedRecord();
						refreshTab(selectedTab, selectedRecord); 
					}
				});
			}
		});
		
		salesCatalogListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					salesCatalogListGrid.selectRecords(salesCatalogListGrid.getSelection(), false);
					salesCatalogListGrid.selectRecord(selectedRecord);
				}else if(salesCatalogListGrid.getSelection().length == 1){
					selectedRecord = salesCatalogListGrid.getSelection()[0];
					salesCatalogListGrid.scrollToRow(salesCatalogListGrid.getRecordIndex(selectedRecord));
				}
				
				Tab selectedTab = childNodeTab.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		}) ;
		childNodeTab.addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = childNodeTab.getSelectedTab();
				ListGridRecord selectedRecord = salesCatalogListGrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);	
			}
		});

		//mainPanelLayout.addMember(salesCatalogButtonToolBar);
		//mainPanelLayout.addMember(salesCatalogListGrid);
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(childNodeTab);

		return mainPanelLayout;
	}
	private void refreshTab(Tab selectedTab, ListGridRecord selectedRecord){
		Canvas panel = selectedTab.getPane();
		
		//基本数据
		if(panel instanceof BasicInfoPanel){
			BasicInfoPanel biPanel = (BasicInfoPanel)panel;
			if(salesCatalogListGrid.getSelection().length == 1){
				String basicInfoId = selectedRecord.getAttribute("m_PartIndex.m_BasicInformation.id");
				biPanel.fetchData(basicInfoId);
			}else{
				biPanel.clearFormValues();
			}
			
		}
		//供应商价格索引
		if(panel instanceof PartSupplierPriceIndexPanel){
			PartSupplierPriceIndexPanel ppiPanel = (PartSupplierPriceIndexPanel)panel;
			if(salesCatalogListGrid.getSelection().length == 1){
				String partIndexId = selectedRecord.getAttribute("m_PartIndex.id");
				ppiPanel.fetchData(partIndexId);
			}else{
				ppiPanel.fetchData("clear");
			}
		}

		//销售价格
		if(panel instanceof SalesPricePanel){
			SalesPricePanel spp = (SalesPricePanel)panel;
			if(salesCatalogListGrid.getSelection().length == 1){
				String salePriceId = selectedRecord.getAttribute("m_SalesPrice.id");
				spp.fetchData(salePriceId);
			}else{
				spp.clearFormValues();
			}
			
		}
		//折扣信息
		if(panel instanceof DiscountItemPanel){
			DiscountItemPanel tdPanel = (DiscountItemPanel)panel;
			if(salesCatalogListGrid.getSelection().length == 1){
				String partSaleReleaseId = selectedRecord.getAttribute("id");
				tdPanel.fetchData(partSaleReleaseId);
			}else{
				tdPanel.clearData();
			} 
			
		}
		//版次信息
		if(panel instanceof EditionsInfoPanel){
			EditionsInfoPanel adPanel = (EditionsInfoPanel)panel;
			if(salesCatalogListGrid.getSelection().length == 1){
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
