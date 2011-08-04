package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.partSupplierPrice;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartSupplierPriceListGrid extends ListGrid {
	private DataInfo dataInfo;
	public DataInfo getDataInfo() {
		return dataInfo;
	}
	public PartSupplierPriceListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.supplierPrice","partSupplierPrice_dataSource", new PostDataSourceInit() {
			@Override
		public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				PartSupplierPriceListGrid.this.dataInfo = dataInfo;
				drawPartSupplierPrice(dataSource);
			}
		});
	}

	public void drawPartSupplierPrice(DataSource dataSource) {  
     	setDataSource(dataSource); 
		//setAutoFetchData(true);
        setShowFilterEditor(true);
        setShowAllRecords(false);
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22);   
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
        //原厂商件号   	
	    ListGridField lgfManufacturerNumber = new ListGridField("m_PartIndex.manufacturerPartNumber");  
	    lgfManufacturerNumber.setCanFilter(true);
	    fieldList.add(lgfManufacturerNumber);       
	    //商飞件号
        ListGridField lgfPartNumber = new ListGridField("m_PartIndex.partNumber"); 
        lgfPartNumber.setCanFilter(true);
        fieldList.add(lgfPartNumber);
        //供应商代码
        ListGridField lgfSupplierCode = new ListGridField("m_SupplierCode.code");
        lgfSupplierCode.setCanFilter(true);
        fieldList.add(lgfSupplierCode);
        //发布版本号
        ListGridField lgfReleaseVersion = new ListGridField("releaseVersion");
        lgfReleaseVersion.setCanFilter(true);
        fieldList.add(lgfReleaseVersion);
        //版次
        ListGridField lgfEdition = new ListGridField("edition");
        lgfEdition.setCanFilter(true);
        fieldList.add(lgfEdition);
        //是否发布
        ListGridField lgfPublishStatus = new ListGridField("m_BussinessPublishStatusEntity.m_PublishStatus");
        lgfPublishStatus.setCanFilter(true);
        fieldList.add(lgfPublishStatus);
        
         //发布时间
        ListGridField lgfActionDate = new ListGridField("m_BussinessPublishStatusEntity.actionDate");
        lgfActionDate.setCanFilter(true);
        lgfActionDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATETIME);
        fieldList.add(lgfActionDate);
        
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
        
        this.fetchData();  
    }  
}

