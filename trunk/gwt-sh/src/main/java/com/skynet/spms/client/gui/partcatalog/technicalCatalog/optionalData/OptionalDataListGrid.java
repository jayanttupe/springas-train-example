package com.skynet.spms.client.gui.partcatalog.technicalCatalog.optionalData;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class OptionalDataListGrid extends ListGrid {

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
	public OptionalDataListGrid(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","optionalPart_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				OptionalDataListGrid.this.dataInfo = dataInfo;
				drawOptionalDataListGrid(dataSource,editable);
				
			}
		});
	}
	public void drawOptionalDataListGrid(DataSource dataSource,boolean editable) {  
        setDataSource(dataSource);;
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCellHeight(22); 
		setCanEdit(editable);
		if(editable){
			setAutoSaveEdits(false);
		}
		
		final List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		//件号   
	    ListGridField lgfPartIndexId = new ListGridField("optionalPartNumber");
	    fieldList.add(lgfPartIndexId);
         
	    //可互换类别
	    ListGridField lgfInterchangeabilityCode = new ListGridField("m_InterchangeabilityCode");
	    fieldList.add(lgfInterchangeabilityCode);
        
        //描述
        ListGridField lgfOptionalPartNumberText = new ListGridField("optionalPartNumberText");  
	    fieldList.add(lgfOptionalPartNumberText);
	  
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);  

    }
}
