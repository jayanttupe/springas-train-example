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
 * 基础数量分段价格明细
 */
public class SubsectionpriceDataListGrid extends ListGrid {
   
	
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
		public SubsectionpriceDataListGrid() {
			DataSourceTool dataSourceTool = new DataSourceTool();
			dataSourceTool.onCreateDataSource("partCatalog.sales","subsectionprice_dataSource", new PostDataSourceInit() {
				
				@Override
				public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
					SubsectionpriceDataListGrid.this.dataInfo = dataInfo;
					drawSubsectionpriceDataListGrid(dataSource);
					
				}
			});	
		}
		private void drawSubsectionpriceDataListGrid(DataSource dataSource){
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
	        ListGridField lgfcycle = new ListGridField("itemNumber");  
		    fieldList.add(lgfcycle);
		 	//数量范围
	    	
		    ListGridField lgfUnitCode = new ListGridField("quantityScope");  
		    fieldList.add(lgfUnitCode);
		    //单价
	        ListGridField lgfTimeCycleType = new ListGridField("priceAmount"); 
	        fieldList.add(lgfTimeCycleType);   
	        //币种
	        ListGridField lgfTimeCycleCode = new ListGridField("m_InternationalCurrencyCode");
		    fieldList.add(lgfTimeCycleCode);
	        //计量单位
	        ListGridField lgfTimeCycleReferenceCode = new ListGridField("m_UnitOfMeasureCode");
		    fieldList.add(lgfTimeCycleReferenceCode);
		 
		    
	        ListGridField[] fields = new ListGridField[fieldList.size()];
	        fieldList.toArray(fields);
	        setFields(fields);
	        
		}
	

} 
