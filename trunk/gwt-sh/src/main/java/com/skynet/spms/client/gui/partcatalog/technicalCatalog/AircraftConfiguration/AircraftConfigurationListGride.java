package com.skynet.spms.client.gui.partcatalog.technicalCatalog.AircraftConfiguration;

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

public class AircraftConfigurationListGride extends ListGrid {

	private DataInfo dataInfo;
	private String partIndexId;
	private String suitableAircraftModel;
	public String getSuitableAircraftModel() {
		return suitableAircraftModel;
	}
	public void setSuitableAircraftModel(String suitableAircraftModel) {
		this.suitableAircraftModel = suitableAircraftModel;
	}
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
	public String getPartIndexId() {
		return partIndexId;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	} 
	public AircraftConfigurationListGride() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","aircraftConfiguration_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				AircraftConfigurationListGride.this.dataInfo = dataInfo;
				
				drawAircraftConfigurationListGride(dataSource);
				
			}
		});
	}
	public void drawAircraftConfigurationListGride(DataSource dataSource) {  
		setDataSource(dataSource);
		
		setAutoFetchData(true);
        setShowFilterEditor(true);
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
		
		
       final  List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
       //适用机型
       ListGridField lgfAircraftModelIdentifier = new ListGridField("m_AircraftModelIdentifier"); 
       fieldList.add(lgfAircraftModelIdentifier);
       
       //飞机尾号
       ListGridField lgfAircraftTailNumber = new ListGridField("aircraftTailNumber"); 
       fieldList.add(lgfAircraftTailNumber);
       
	  //飞机注册号
       ListGridField lgfAircraftRegistrationNumber = new ListGridField("aircraftRegistrationNumber"); 
       fieldList.add(lgfAircraftRegistrationNumber);
       
        //国籍
        ListGridField lgfNationality = new ListGridField("m_CountryCode");
	    fieldList.add(lgfNationality);
	    //飞机运营人
	    ListGridField lgfOperator = new ListGridField("operator");
	    fieldList.add(lgfOperator);
       //飞机所有人
	    ListGridField lgfOwner = new ListGridField("owner");
	    fieldList.add(lgfOwner);
	
	   //出厂时间
	    ListGridField lgfFactoryData = new ListGridField("factoryDate");
	    fieldList.add(lgfFactoryData);
	    
	  //注册时间
	    ListGridField lgfRegistrationDate = new ListGridField("registrationDate");
	    fieldList.add(lgfRegistrationDate);
	
        
        
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);  

        
    }
	
}
