package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.editionsInformation;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class SupplierHistoryEditionsShowWindow extends BaseWindow{
	
	public SupplierHistoryEditionsShowWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, SupplierHistoryEditionsShowWindow.this, -1);
			}
		});
		setWidth(470);
		setHeight(280);
		setTitle("供应商历史版次信息管理");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				//buttonTouchThis.setTitle("Touch This");
				destroy();
			}
		});
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		ListGridField lgfVersionNumber = new ListGridField("versionNumber", "版本号");  
		lgfVersionNumber.setCanEdit(false);
	    fieldList.add(lgfVersionNumber);
		
	    ListGridField lgfEditionsNumber = new ListGridField("editionsNumber", "版次");  
	    lgfEditionsNumber.setCanEdit(false);
	    fieldList.add(lgfEditionsNumber);
	    
	    ListGridField lgfReleaseVersionDate = new ListGridField("releaseVersionDate", "版本发布日期");  
	    lgfReleaseVersionDate.setCanEdit(false);
	    fieldList.add(lgfReleaseVersionDate);
	    
	    ListGridField lgfReleaseMan = new ListGridField("releaseMan", "版布人");  
	    lgfReleaseMan.setCanEdit(false);
	    fieldList.add(lgfReleaseMan);
		
	    ListGridField lgfEditionsReviseDate = new ListGridField("editionsReviseDate", "版次修订日期");  
	    lgfEditionsReviseDate.setCanEdit(false);
	    fieldList.add(lgfEditionsReviseDate);
		
	    ListGridField lgfEditionsReviseMan = new ListGridField("editionsReviseMan","修订人");  
	    lgfEditionsReviseMan.setCanEdit(false);
	    fieldList.add(lgfEditionsReviseMan);
	    
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        
        TileGrid tileGrid = new TileGrid();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		tileGrid.setTileWidth(150);
		tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		
        
        VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		return vLayout;
	}
		
}
