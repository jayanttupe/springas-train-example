package com.skynet.spms.client.gui.partcatalog.salesCatalog.salesPrice;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AlternateSupplyLocationListGrid extends ListGrid {
	
	private String priceId;
	
	public String getPriceId() {
		return priceId;
	}
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
	public AlternateSupplyLocationListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales","alternateSupplyLoc_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				drawOtherPriceDataListGrid(dataSource);	
			}
		});	
	}
	private void drawOtherPriceDataListGrid(DataSource dataSource){
		setDataSource(dataSource);
    	setAutoFetchData(true);
        setCanRemoveRecords(true);
        setRemoveFieldTitle("删除");
        setShowAllRecords(true);
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX); 
        setCanEdit(true);   
        setHoverWidth(200);   
        setHoverHeight(20); 
        setCellHeight(22);
        this.setAutoSaveEdits(false);
       
      //可选地址
        ListGridField lgfLocation =new ListGridField("location");
        setFields(lgfLocation);
	}
}  
