package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;

public class AircraftConfigCatalogListGrid extends ListGrid{
	private DataInfo dataInfo;
	public DataInfo getDataInfo() {
		return dataInfo;
	}
	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	public AircraftConfigCatalogListGrid(final boolean isLoadData) {
		/*setShowFilterEditor(true);
		addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {	
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				fetchData(c);
				
			}
		});
		setShowAllRecords(false);
		setSelectionType(SelectionStyle.SIMPLE);  
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setHoverWidth(200);   
		setHoverHeight(20); 
		setCellHeight(22);   
        
       
	   final List<ListGridField> fieldList = new ArrayList<ListGridField>();
       //适用机型
       ListGridField lgfAircraftModelIdentifier = new ListGridField("m_AircraftModelIdentifier");   
       //lgfAircraftModelIdentifier.setTitle(ConstantsUtil.partCatalogConstants.aircraftModelIdentifier());
       lgfAircraftModelIdentifier.setCanFilter(true);
       fieldList.add(lgfAircraftModelIdentifier);
       
       //飞机尾号
       ListGridField lgfAircraftTailNumber = new ListGridField("aircraftTailNumber"); 
       //lgfAircraftTailNumber.setTitle(ConstantsUtil.partCatalogConstants.aircraftTailNumber());
       lgfAircraftTailNumber.setCanFilter(true);
       fieldList.add(lgfAircraftTailNumber);
       
	  //飞机注册号
       ListGridField lgfAircraftRegistrationNumber = new ListGridField("aircraftRegistrationNumber"); 
       //lgfAircraftRegistrationNumber.setTitle(ConstantsUtil.partCatalogConstants.aircraftRegistrationNumber());
       lgfAircraftRegistrationNumber.setCanFilter(true);
       fieldList.add(lgfAircraftRegistrationNumber);
       
        //国籍
        ListGridField lgfNationality = new ListGridField("m_CountryCode");
        //lgfNationality.setTitle(ConstantsUtil.partCatalogConstants.countryCode());
        lgfNationality.setCanFilter(true);
        fieldList.add(lgfNationality);
	    //飞机运营人
	    ListGridField lgfOperator = new ListGridField("operator");
	    //lgfOperator.setTitle(ConstantsUtil.partCatalogConstants.operator());
	    lgfOperator.setCanFilter(true);
	    lgfOperator.setCanEdit(false);
	    fieldList.add(lgfOperator);
       //飞机所有人
	    ListGridField lgfOwner = new ListGridField("owner");
	    //lgfOwner.setTitle(ConstantsUtil.partCatalogConstants.owner());
	    lgfOwner.setCanFilter(true);
	    fieldList.add(lgfOwner);
	
	   //出厂时间
	    ListGridField lgfFactoryData = new ListGridField("factoryDate");
	    //lgfFactoryData.setTitle(ConstantsUtil.partCatalogConstants.factoryDate());
	    lgfFactoryData.setCanFilter(true);
	    fieldList.add(lgfFactoryData);
	    
	  //注册时间
	    ListGridField lgfRegistrationDate = new ListGridField("registrationDate");
	    //lgfRegistrationDate.setTitle(ConstantsUtil.partCatalogConstants.registrationDate());
	    lgfRegistrationDate.setCanFilter(true);
	    lgfRegistrationDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
	    fieldList.add(lgfRegistrationDate);

        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
		
		dataSourceTool.onInitDataSource("partCatalog.aircraftConfig","aircraftConfig_dataSource", new PostDataSourceInit(){
		}
		*/
		DataSourceTool dataSourceTool = new DataSourceTool();
		//dataSourceTool.onCreateDataSourceWithoutI18n("partCatalog.aircraftConfig","aircraftConfig_dataSource", new PostDataSourceInit(){
		dataSourceTool.onCreateDataSource("partCatalog.aircraftConfig","aircraftConfig_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				AircraftConfigCatalogListGrid.this.dataInfo = dataInfo;		
				drawAircraftConfigCatalogListGrid(dataSource);
				if(isLoadData){
					fetchData();
				}
			}
		});
	}
	
	private void drawAircraftConfigCatalogListGrid(DataSource dataSource) {  
		
		setDataSource(dataSource);
		setShowFilterEditor(true);
		setUseAllDataSourceFields(false);
		setShowAllRecords(false);
		setSelectionType(SelectionStyle.SIMPLE);  
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCellHeight(22);
		setCanEdit(false);
		
		addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					selectRecords(getSelection(), false);
					selectRecord(selectedRecord);
				}
			}
		});
		addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {	
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				fetchData(c);
				
			}
		});
        
	   final List<ListGridField> fieldList = new ArrayList<ListGridField>();
       //适用机型
       ListGridField lgfAircraftModelIdentifier = new ListGridField("m_AircraftModelIdentifier");   
       //lgfAircraftModelIdentifier.setTitle(ConstantsUtil.partCatalogConstants.aircraftModelIdentifier());
       lgfAircraftModelIdentifier.setCanFilter(true);
       fieldList.add(lgfAircraftModelIdentifier);
       
       //飞机尾号
       ListGridField lgfAircraftTailNumber = new ListGridField("aircraftTailNumber"); 
       //lgfAircraftTailNumber.setTitle(ConstantsUtil.partCatalogConstants.aircraftTailNumber());
       lgfAircraftTailNumber.setCanFilter(true);
       fieldList.add(lgfAircraftTailNumber);
       
	  //飞机注册号
       ListGridField lgfAircraftRegistrationNumber = new ListGridField("aircraftRegistrationNumber"); 
       //lgfAircraftRegistrationNumber.setTitle(ConstantsUtil.partCatalogConstants.aircraftRegistrationNumber());
       lgfAircraftRegistrationNumber.setCanFilter(true);
       fieldList.add(lgfAircraftRegistrationNumber);
       
        //国籍
        ListGridField lgfNationality = new ListGridField("m_CountryCode");
        //lgfNationality.setTitle(ConstantsUtil.partCatalogConstants.countryCode());
        lgfNationality.setCanFilter(true);
        fieldList.add(lgfNationality);
	    //飞机运营人
	    ListGridField lgfOperator = new ListGridField("operator");
	    //lgfOperator.setTitle(ConstantsUtil.partCatalogConstants.operator());
	    lgfOperator.setCanFilter(true);
	    lgfOperator.setCanEdit(false);
	    fieldList.add(lgfOperator);
       //飞机所有人
	    ListGridField lgfOwner = new ListGridField("owner");
	    //lgfOwner.setTitle(ConstantsUtil.partCatalogConstants.owner());
	    lgfOwner.setCanFilter(true);
	    fieldList.add(lgfOwner);
	
	   //出厂时间
	    ListGridField lgfFactoryData = new ListGridField("factoryDate");
	    //lgfFactoryData.setTitle(ConstantsUtil.partCatalogConstants.factoryDate());
	    lgfFactoryData.setCanFilter(true);
	    lgfFactoryData.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
	    fieldList.add(lgfFactoryData);
	    
	  //注册时间
	    ListGridField lgfRegistrationDate = new ListGridField("registrationDate");
	    //lgfRegistrationDate.setTitle(ConstantsUtil.partCatalogConstants.registrationDate());
	    lgfRegistrationDate.setCanFilter(true);
	    lgfRegistrationDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
	    fieldList.add(lgfRegistrationDate);

        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields); 
	}
}
