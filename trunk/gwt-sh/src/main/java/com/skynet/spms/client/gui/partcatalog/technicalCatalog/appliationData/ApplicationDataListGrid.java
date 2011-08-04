package com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ApplicationDataListGrid extends ListGrid {

	private DataInfo dataInfo;
	private String partIndexId;
	
	public void setPartIndexId(String partIndexId) {
		this.partIndexId = partIndexId;
	}
	public String getPartIndexId() {
		return partIndexId;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	} 
	public ApplicationDataListGrid(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","applicationData_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				ApplicationDataListGrid.this.dataInfo = dataInfo;
				DataSourceField dsf = dataSource.getField("quickEngineChange");
				LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
				boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
				boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
				dsf.setValueMap(boolValueMap);
				drawApplicationDataListGrid(dataSource,editable);
				
			}
		});
	}
	public void drawApplicationDataListGrid(DataSource dataSource,boolean editable) {  
		setDataSource(dataSource);
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22); 
        setCanEdit(editable);
        if(editable){
        	setAutoSaveEdits(false);
        }
        
		final List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		//单机装机
	    ListGridField lgfQuantityPerAricraft = new ListGridField("totalQuantityPerAircraftEngine");  
	    fieldList.add(lgfQuantityPerAricraft);
         
	    //ETOPS
        ListGridField lgfETOPSFlightIndicator = new ListGridField("m_ETOPSFlightIndicator"); 

        fieldList.add(lgfETOPSFlightIndicator);
        
        //快速转发
        ListGridField lgfQuickEngineChangeIndicator = new ListGridField("quickEngineChange");  
	    fieldList.add(lgfQuickEngineChangeIndicator);
	    
	    //级维修
        ListGridField lgfEngineLevelOfMaintenanceCode = new ListGridField("m_EngineLevelOfMaintenanceCode");
	    fieldList.add(lgfEngineLevelOfMaintenanceCode);
        
	    //维修代码
        ListGridField lgfMaintenanceOverhaulRepairCode = new ListGridField("m_MaintenanceOverhaulRepairCode");
	    fieldList.add(lgfMaintenanceOverhaulRepairCode);	
	    
	    //出口控制分类号码
	    ListGridField lgfexportControlClassificationNumber = new ListGridField("m_ExportControlClassificationCode");
	    fieldList.add(lgfexportControlClassificationNumber);
	    	    
        //航线维护备注
	    ListGridField lgfMiscellaneousText = new ListGridField("miscellaneousText");
	    fieldList.add(lgfMiscellaneousText);
	    
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);  
   
    }
}
