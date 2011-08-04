package com.skynet.spms.client.gui.partcatalog.salesCatalog.salesPrice;

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


/*
 * 其他费用
 */
public class OtherPriceDataListGrid extends ListGrid {
	
	private DataInfo dataInfo;
	private String salesPriceId;
	
	public String getSalesPriceId() {
		return salesPriceId;
	}

	public void setSalesPriceId(String salesPriceId) {
		this.salesPriceId = salesPriceId;
	}
	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}

	public DataInfo getDataInfo() {
		return dataInfo;
	}
	
	public OtherPriceDataListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales","otherPrice_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				OtherPriceDataListGrid.this.dataInfo = dataInfo;
				drawOtherPriceDataListGrid(dataSource);
				
			}
		});	
	}
	private void drawOtherPriceDataListGrid(DataSource dataSource){
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
      //其费用代码
        ListGridField listOtherChargeCode=new ListGridField("m_OtherChargeCode");
        fieldList.add(listOtherChargeCode);
      //其他费用金额
        ListGridField listOtherChargeAmount=new ListGridField("otherChargeAmount");
        fieldList.add(listOtherChargeAmount);
      //币种
        ListGridField listCurrencyCode=new ListGridField("m_InternationalCurrencyCode");
        fieldList.add(listCurrencyCode);
      //备注
        ListGridField listRemarkText=new ListGridField("remarkText");
        fieldList.add(listRemarkText);
        
        
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields);
	}
}  
