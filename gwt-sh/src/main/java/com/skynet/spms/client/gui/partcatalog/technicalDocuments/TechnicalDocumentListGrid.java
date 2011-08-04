package com.skynet.spms.client.gui.partcatalog.technicalDocuments;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;

public class TechnicalDocumentListGrid extends ListGrid {

	private DataInfo dataInfo;

	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public TechnicalDocumentListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technicalDocuments","technicalDocuments_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				TechnicalDocumentListGrid.this.dataInfo = dataInfo;
				drawTechnicalDocumentListGrid(dataSource);

			}
		});
	}
	public void drawTechnicalDocumentListGrid(DataSource dataSource) {  
		
		setDataSource(dataSource); 
		setShowFilterEditor(true);
		addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				fetchData(c);
			}
		});
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setShowAllRecords(false);
		setCanEdit(false);
		setCellHeight(22);
        
        final List<ListGridField> fieldList = new ArrayList<ListGridField>();
	    //存储位置
        ListGridField lgfLocation = new ListGridField("location");  
        lgfLocation.setCanFilter(true);
	    fieldList.add(lgfLocation);  
	
	    //技术出版物
	    ListGridField lgfTechnicalPublishType = new ListGridField("m_TechnicalPublishType");  
	    lgfTechnicalPublishType.setCanFilter(true);
	    fieldList.add(lgfTechnicalPublishType); 
	    
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
        this.fetchData();
        
	} 

}
