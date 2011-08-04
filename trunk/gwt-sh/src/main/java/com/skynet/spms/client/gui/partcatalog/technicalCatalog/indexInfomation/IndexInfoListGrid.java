package com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

import com.smartgwt.client.types.DateDisplayFormat;



public class IndexInfoListGrid extends ListGrid {
	
	public IndexInfoListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","partIndex_dataSource", new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
				valueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
				valueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
				DataSourceField isPartBagField = dataSource.getField("m_IsPartbag");
				isPartBagField.setValueMap(valueMap);
				
				drawIndexInfoListGrid(dataSource);	
			}
		});	
	}

	public void drawIndexInfoListGrid(DataSource dataSource) {  
		
		setDataSource(dataSource); 
		//setAutoFetchData(true);
        setShowFilterEditor(true);
        setShowAllRecords(false);
        //setShowRowNumbers(true);
        
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22);   
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		//原厂商件号
	    ListGridField lgfManufacturerNumber = new ListGridField("manufacturerPartNumber");
	    lgfManufacturerNumber.setCanFilter(true);
	    lgfManufacturerNumber.setCanEdit(false);
	    fieldList.add(lgfManufacturerNumber);
	    //商飞件号
        ListGridField lgfPartNumber = new ListGridField("partNumber");
        lgfPartNumber.setCanFilter(true);
        lgfPartNumber.setCanEdit(false);
        fieldList.add(lgfPartNumber);
        
        //中文关键字
        ListGridField textKeyword_zh=new ListGridField("keyword_zh");
		textKeyword_zh.setCanFilter(true);
		textKeyword_zh.setCanEdit(false);
		fieldList.add(textKeyword_zh);
		        
		//英文关键字
		ListGridField textKeyword_en=new ListGridField("keyword_en");
		textKeyword_en.setCanFilter(true);
		textKeyword_en.setCanEdit(false);
		fieldList.add(textKeyword_en);

        //制造商代码
        ListGridField lgfManufacturerCode = new ListGridField("m_ManufacturerCode.code");
        lgfManufacturerCode.setCanFilter(true);
        lgfManufacturerCode.setCanEdit(false);
        fieldList.add(lgfManufacturerCode);
        
        //是否航材包
        ListGridField isPartBag = new ListGridField("m_IsPartbag");  
		isPartBag.setCanFilter(true);
		isPartBag.setCanEdit(false);
		fieldList.add(isPartBag);
		
        //发布状态
        ListGridField lgfPubStatus = new ListGridField("m_BussinessPublishStatusEntity.m_PublishStatus");
        lgfPubStatus.setCanFilter(true);
        lgfPubStatus.setCanEdit(false);
        fieldList.add(lgfPubStatus);
       
        //发布时间
        ListGridField lgfPubTime = new ListGridField("m_BussinessPublishStatusEntity.actionDate");
        lgfPubTime.setCanFilter(true);
        lgfPubTime.setCanEdit(false);
        lgfPubTime.setWidth(120);
        lgfPubTime.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATETIME);
        fieldList.add(lgfPubTime);

        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        
        setFields(fields);
        fetchData();
    }  
}