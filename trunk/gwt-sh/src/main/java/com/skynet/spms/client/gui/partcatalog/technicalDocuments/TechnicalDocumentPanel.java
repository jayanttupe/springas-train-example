package com.skynet.spms.client.gui.partcatalog.technicalDocuments;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class TechnicalDocumentPanel extends ShowcasePanel{

	private static final String DESCRIPTION = "备件技术文件管理信息维护页面";
	private TechnicalDocumentButtonToolBar technicalDocumentButtonToolBar;
	private TechnicalDocumentListGrid technicalDocumentListGrid;
	
	public static class Factory implements PanelFactory{
		private String DESCRIPTION = "备件技术文件信息管理模块";
		private String id;
		
		public Canvas create() {
			TechnicalDocumentPanel panel = new TechnicalDocumentPanel();
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
		mainPanelLayout.setHeight100();
		technicalDocumentListGrid = new TechnicalDocumentListGrid();
		technicalDocumentListGrid.setHeight("90%");
		
		technicalDocumentListGrid.addCellClickHandler(new CellClickHandler() {	
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					technicalDocumentListGrid.selectRecords(technicalDocumentListGrid.getSelection(), false);
					technicalDocumentListGrid.selectRecord(selectedRecord);
				}
			}
		});
		
		technicalDocumentButtonToolBar = new TechnicalDocumentButtonToolBar("partCatalog.technicalDocuments", technicalDocumentListGrid);
		
		mainPanelLayout.addMember(technicalDocumentButtonToolBar);
		mainPanelLayout.addMember(technicalDocumentListGrid);

		return mainPanelLayout;	
	}
	public String getIntro() {
		return DESCRIPTION;
	}

}
