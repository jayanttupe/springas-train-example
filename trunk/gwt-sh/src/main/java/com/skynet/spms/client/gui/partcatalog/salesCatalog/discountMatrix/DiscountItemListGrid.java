package com.skynet.spms.client.gui.partcatalog.salesCatalog.discountMatrix;

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

public class DiscountItemListGrid extends ListGrid{
	private String partSaleReleaseId;
	public String getPartSaleReleaseId() {
		return partSaleReleaseId;
	}
	public void setPartSaleReleaseId(String partSaleReleaseId) {
		this.partSaleReleaseId = partSaleReleaseId;
	}
	public DiscountItemListGrid() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.sales","discountItem_dataSource", new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				drawDiscountMatrixListGrid(dataSource);	
			}
		});
	}
	
	public void drawDiscountMatrixListGrid(DataSource dataSource) {  
		
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
		setHeight100();
		setAutoSaveEdits(false);
        
		final List<ListGridField> fieldList = new ArrayList<ListGridField>();
        //客户分类代码
	    ListGridField lgfCustomerCategoryCode = new ListGridField("m_CustomerCategoryCode");  
	    fieldList.add(lgfCustomerCategoryCode);
	    //客户分类代码
	    ListGridField lgfProductCategoryCode = new ListGridField("m_ProductCategoryCode");  
	    fieldList.add(lgfProductCategoryCode);
         //折扣百分比
        ListGridField lgfDiscountPercentCode = new ListGridField("m_DiscountPercentCode"); 
        fieldList.add(lgfDiscountPercentCode);
        //折扣明细项
        ListGridField lgfDiscountItem = new ListGridField("discountItem");  
	    fieldList.add(lgfDiscountItem);
 
	    ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        setFields(fields); 
   }
}
