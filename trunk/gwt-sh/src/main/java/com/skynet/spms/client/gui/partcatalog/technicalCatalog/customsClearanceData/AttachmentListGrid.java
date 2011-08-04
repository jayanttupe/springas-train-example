package com.skynet.spms.client.gui.partcatalog.technicalCatalog.customsClearanceData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AttachmentListGrid extends ListGrid {

	private Map<String, Object> map;
	public AttachmentListGrid(){
		map = new LinkedHashMap<String, Object>();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","customsAttachment_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				drawAttachmentListGrid(dataSource);
			}
		});
	}
	public void addObject(String key,Object value){
		map.put(key, value);
	}
	public Object getObject(String key){
		return map.get(key);
	}
	private void drawAttachmentListGrid(DataSource dataSource){
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
        
        List<ListGridField> fieldList = new ArrayList<ListGridField>();
        
        //项号
        ListGridField lgfItemNumber = new ListGridField("itemNumber");
        fieldList.add(lgfItemNumber);
        
        //标题
        ListGridField lgfTitle = new ListGridField("title");
        fieldList.add(lgfTitle);
        
        //描述
        ListGridField lgfDescription = new ListGridField("description");
        fieldList.add(lgfDescription);
        
        //文件名称
        ListGridField lgfFileName = new ListGridField("fileName");
        fieldList.add(lgfFileName);
        
        //修改日期
        ListGridField lgfModifyDate = new ListGridField("modifyDate");
        fieldList.add(lgfModifyDate);
        
        //修改人
        ListGridField lgfOperator = new ListGridField("operator");
        fieldList.add(lgfOperator);
        
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
	}
}
