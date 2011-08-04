package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author 曹宏炜
 * 飞机构型管理面板
 *
 */
public class AircraftConfigCatalogPanel extends ShowcasePanel{

	private static final String DESCRIPTION = "飞机构型管理信息维护页面";
	private AircraftConfigCatalogButtonToolBar aircraftConfigCatalogButtonToolBar ;
	private AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid;

	public static class Factory implements PanelFactory{
		private String DESCRIPTION = "飞机构型管理模块";
		private String id;
		
		public Canvas create() {
			AircraftConfigCatalogPanel panel = new AircraftConfigCatalogPanel();
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
		// VLayout中的成员无需定义宽度，而只需定义高度
		VLayout mainPanelLayout = new VLayout();
		aircraftConfigCatalogListGrid = new AircraftConfigCatalogListGrid(true);
		aircraftConfigCatalogListGrid.setHeight("90%");
		aircraftConfigCatalogButtonToolBar = new AircraftConfigCatalogButtonToolBar("partCatalog.aircraftConfig", aircraftConfigCatalogListGrid);
		
		mainPanelLayout.addMember(aircraftConfigCatalogButtonToolBar);
		mainPanelLayout.addMember(aircraftConfigCatalogListGrid);

		return mainPanelLayout;	
	}
	public String getIntro() {
		return DESCRIPTION;
	}
	
}
