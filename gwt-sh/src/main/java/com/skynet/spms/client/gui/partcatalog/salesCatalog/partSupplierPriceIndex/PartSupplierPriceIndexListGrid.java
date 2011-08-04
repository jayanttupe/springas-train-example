package com.skynet.spms.client.gui.partcatalog.salesCatalog.partSupplierPriceIndex;

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

public class PartSupplierPriceIndexListGrid extends ListGrid  {

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
	
	
	public PartSupplierPriceIndexListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
        dataSourceTool.onCreateDataSource("partCatalog.supplierPrice","partSupplierPrice_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				PartSupplierPriceIndexListGrid.this.dataInfo = dataInfo;
				
				drawPartSupplierPriceIndexListGrid(dataSource);
				
			}
		});
		
	}

	public void drawPartSupplierPriceIndexListGrid(DataSource dataSource) {  
		
		setDataSource(dataSource); 
		setAutoFetchData(true);
        setShowFilterEditor(true);
        setShowAllRecords(true);
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX);   
        setHoverWidth(200);   
        setHoverHeight(20); 
        setCellHeight(22);  
		
		
		final List<ListGridField> fieldList = new ArrayList<ListGridField>();
    	//供应商代码	   	
	    ListGridField lgfSupplierCode = new ListGridField("m_SupplierCode.code");  
	    lgfSupplierCode.setCanEdit(false);
	    fieldList.add(lgfSupplierCode);
   
        //单价
        ListGridField lgfUnitPriceAmount = new ListGridField("m_SupplierSalesPrice.unitPriceAmount");  
	    fieldList.add(lgfUnitPriceAmount);
	    
	    ///币种
	    ListGridField lgfCurrency = new ListGridField("m_SupplierSalesPrice.upaCurrencyCode");
	    fieldList.add(lgfCurrency);
	   
	    //最小销售量
        ListGridField lgfMinimumSalesQuantity = new ListGridField("m_SupplierSalesPrice.minimumSalesQuantity");
	    fieldList.add(lgfMinimumSalesQuantity);
        
	    //报价日期 quotationDate
        ListGridField lgfQuotationDate = new ListGridField("m_BussinessPublishStatusEntity.actionDate");
	    fieldList.add(lgfQuotationDate);
	    //报价有效期
        ListGridField lgfPriceEffectiveDate = new ListGridField("m_SupplierSalesPrice.priceEffectiveDate");
	    fieldList.add(lgfPriceEffectiveDate);
	    
       //发布版本号
	    ListGridField lgfReleaseVersion  = new ListGridField("releaseVersion");
	    lgfReleaseVersion.setCanEdit(false);
	    fieldList.add(lgfReleaseVersion);
	    
	   //版次
	    ListGridField lgfEdition  = new ListGridField("edition");
	    lgfEdition.setCanEdit(false);
	    fieldList.add(lgfEdition);
	    
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);  

	}
}
 
