package com.skynet.spms.client.gui.partcatalog.repairableCatalog;

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

public class RepairableCatalogListGrid extends ListGrid{
	
	private DataInfo dataInfo;
	public DataInfo getDataInfo(){
		return dataInfo;
	}
	
	public RepairableCatalogListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.repairable","repairData_dataSource", new PostDataSourceInit() {
			
			@Override
		public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				RepairableCatalogListGrid.this.dataInfo = dataInfo;
				drawRepairableCatalogListGrid(dataSource);
			}
		});
		
	}

	public void drawRepairableCatalogListGrid(DataSource dataSource) {  
		
		setDataSource(dataSource); 
		setAutoFetchData(true);
		setShowFilterEditor(true);
		/*setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");*/
		setShowAllRecords(true);
		setSelectionType(SelectionStyle.SIMPLE);  
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setHoverWidth(200);   
		setHoverHeight(20); 
		setCellHeight(22);   
		//setUseAllDataSourceFields(true); 
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();
        
        ListGridField lgfManufacturerNumber = new ListGridField("manufacturerPartNumber");  
	    lgfManufacturerNumber.setCanFilter(true);
	    fieldList.add(lgfManufacturerNumber);       
	    
        ListGridField lgfPartNumber = new ListGridField("partNumber"); 
        lgfPartNumber.setCanFilter(true);
        fieldList.add(lgfPartNumber);
        
        
        ListGridField lgfOverlengthNumber = new ListGridField("overlengthPartNumber");  
        lgfOverlengthNumber.setCanFilter(true);
        fieldList.add(lgfOverlengthNumber);
        
        ListGridField lgfManufacturerCode = new ListGridField("manufacturerCode");  
        lgfManufacturerCode.setCanFilter(true);
        fieldList.add(lgfManufacturerCode);
        
        ListGridField lgfBussinessPublishStatusEntity = new ListGridField("bussinessPublishStatusEntity");
        lgfBussinessPublishStatusEntity.setCanFilter(true);
        fieldList.add(lgfBussinessPublishStatusEntity);
       
        
        //发布日期字段未找到
        ListGridField lgfActionDate = new ListGridField("actionDate");
        lgfActionDate.setCanFilter(true);
        fieldList.add(lgfActionDate);
        
        
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        
        setFields(fields);
        
        this.fetchData();
        
	} 
}
