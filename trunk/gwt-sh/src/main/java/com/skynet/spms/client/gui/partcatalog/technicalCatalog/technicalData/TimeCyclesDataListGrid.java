package com.skynet.spms.client.gui.partcatalog.technicalCatalog.technicalData;

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

public class TimeCyclesDataListGrid extends ListGrid {

	private DataInfo dataInfo;
	private String technicalDataId;
	
	public String getTechnicalDataId() {
		return technicalDataId;
	}

	public void setTechnicalDataId(String technicalDataId) {
		this.technicalDataId = technicalDataId;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	}
	public TimeCyclesDataListGrid(final boolean editable){
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","timeCyclesData_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				TimeCyclesDataListGrid.this.dataInfo = dataInfo;
				drawTimeCyclesDataListGrid(dataSource,editable);
				
			}
		});
	}
	private void drawTimeCyclesDataListGrid(DataSource dataSource,boolean editable){
		setDataSource(dataSource);
        setShowAllRecords(true);
        setWidth100();
        setHeight100();
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX); 
        setCanEdit(editable);
        if(editable){
        	setAutoSaveEdits(false);
        }
        setCellHeight(22);
        
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();
        
        //周期类型
        ListGridField lgfTimeCycleType = new ListGridField("m_TimeCycleType"); 
        fieldList.add(lgfTimeCycleType);   
        //周期数
        ListGridField lgfcycle = new ListGridField("cycle");  
	    fieldList.add(lgfcycle);
        //周期数单位
	    ListGridField lgfUnitCode = new ListGridField("m_UnitCode");  
	    fieldList.add(lgfUnitCode);
	    
        /*//时控代码
        ListGridField lgfTimeCycleCode = new ListGridField("m_TimeCycleCode");
	    fieldList.add(lgfTimeCycleCode);
        //时控参考代码
        ListGridField lgfTimeCycleReferenceCode = new ListGridField("m_TimeCycleReferenceCode");
	    fieldList.add(lgfTimeCycleReferenceCode);*/
	    
	    //处理方法
	    ListGridField lgfhandling = new ListGridField("handling");
	    fieldList.add(lgfhandling);
	    //描述
        ListGridField lgfDescription = new ListGridField("description");
	    fieldList.add(lgfDescription); 
	    
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
	}
}
