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

public class SalesHistoryListGrid extends ListGrid{

	public SalesHistoryListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales","salesHistory_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {

				drawSalesHistoryListGrid(dataSource);
				
			}
		});
		
		
	}

	public void drawSalesHistoryListGrid(DataSource dataSource) {  
		    setDataSource(dataSource); 
			setAutoFetchData(true);
	        setShowFilterEditor(true);
	        setCanRemoveRecords(true);
	        setRemoveFieldTitle("删除");
	        setShowAllRecords(true);
	        setSelectionType(SelectionStyle.SIMPLE);  
	        setSelectionAppearance(SelectionAppearance.CHECKBOX);
	        setCanEdit(true);   
	        setCanDragRecordsOut(true); 
	        setShowAllRecords(true);
	        setHoverWidth(200);   
	        setHoverHeight(20); 
	        setCellHeight(22);   
		
		
	
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
    	//销售订单编号
	    ListGridField lgfSalesOrderNumber = new ListGridField("salesOrderNumber");  
	    lgfSalesOrderNumber.setCanEdit(false);
	    fieldList.add(lgfSalesOrderNumber);
        //客户识别代码
        ListGridField lgfCustomer = new ListGridField("m_CustomerIdentificationCode"); 
        lgfCustomer.setCanEdit(false);
        fieldList.add(lgfCustomer);
        
        //销售单价
        ListGridField lgfSalePricet = new ListGridField("unitPriceAmount");  
        lgfSalePricet.setCanEdit(false);
	    fieldList.add(lgfSalePricet);
	    
	    //销售数量
	    ListGridField lgfXssl = new ListGridField("manufacturersFiles");
	    lgfXssl.setCanEdit(false);
	    fieldList.add(lgfXssl);
	
	    //总金额
        ListGridField lgfSum = new ListGridField("totalAmount");
        lgfSum.setCanEdit(false);
	    fieldList.add(lgfSum);
	    
	    //历时采购均价
	    ListGridField lgfPreviousPurchasingAllValence  = new ListGridField("previousPurchaseAveragePrice");
	    lgfPreviousPurchasingAllValence.setCanEdit(false);
	    fieldList.add(lgfPreviousPurchasingAllValence);
	    //币种
	    ListGridField lgfCurrency = new ListGridField("m_InternationalCurrencyCode");
	    lgfCurrency.setCanEdit(false);
	    fieldList.add(lgfCurrency);
	    //销售日期
	    ListGridField lgfSaleData = new ListGridField("deliveryDate");
	    lgfSaleData.setCanEdit(false);
	    fieldList.add(lgfSaleData);
	    
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);

        setFields(fields);     
        this.fetchData();
       
		
		
	 
	}
}
