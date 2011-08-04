package com.skynet.spms.client.gui.partcatalog.salesCatalog.editionsInfo;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SalesHistoryEditionsListGrid extends ListGrid{
	
	
	private String partSaleReleaseId;
	
	public String getPartSaleReleaseId() {
		return partSaleReleaseId;
	}
	public void setPartSaleReleaseId(String partSaleReleaseId) {
		this.partSaleReleaseId = partSaleReleaseId;
	}
	public SalesHistoryEditionsListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales","editionsHistory_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {

				drawSalesCatalogListGrid(dataSource);
				
			}
		});	
	}
	public void drawSalesCatalogListGrid(DataSource dataSource) {  
		
        setDataSource(dataSource); 
		setAutoFetchData(true);
        setShowFilterEditor(true);
        setCanRemoveRecords(true);
        setRemoveFieldTitle("删除");
        setShowAllRecords(true);
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCanEdit(true);   
        setCanDragRecordsOut(true); 
        setShowAllRecords(true);
        setHoverWidth(200);   
        setHoverHeight(20); 
        setCellHeight(22);   
		//setUseAllDataSourceFields(true); 
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		//版本号
		ListGridField lgfVersionNumber = new ListGridField("releaseVersion");  
		lgfVersionNumber.setCanEdit(false);
	    fieldList.add(lgfVersionNumber);
		//版次
	    ListGridField lgfEditionsNumber = new ListGridField("edition");  
	    lgfEditionsNumber.setCanEdit(false);
	    fieldList.add(lgfEditionsNumber);
	    //版本发布日期
	    ListGridField lgfReleaseVersionDate = new ListGridField("releaseVersionDate");  
	    lgfReleaseVersionDate.setCanEdit(false);
	    fieldList.add(lgfReleaseVersionDate);
	    //发布人
	    ListGridField lgfReleaseMan = new ListGridField("releaseMan");  
	    lgfReleaseMan.setCanEdit(false);
	    fieldList.add(lgfReleaseMan);
		//版本修订日期
	    ListGridField lgfEditionsReviseDate = new ListGridField("editionsReviseDate");  
	    lgfEditionsReviseDate.setCanEdit(false);
	    fieldList.add(lgfEditionsReviseDate);
		//修订人
	    ListGridField lgfEditionsReviseMan = new ListGridField("editionsReviseMan");  
	    lgfEditionsReviseMan.setCanEdit(false);
	    fieldList.add(lgfEditionsReviseMan);
	    
		 ListGridField[] fields = new ListGridField[fieldList.size()];
	     fieldList.toArray(fields);      
	     setFields(fields);  
	     this.fetchData();
   }
	
	
		
}
