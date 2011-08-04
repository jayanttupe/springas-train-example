package com.skynet.spms.client.gui.partcatalog.repairableCatalog.repairData;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;


public class RepairDataListGrid extends ListGrid{
		
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
		
		public RepairDataListGrid() {
			DataSourceTool dataSourceTool = new DataSourceTool();
			dataSourceTool.onCreateDataSource("partCatalog.repairable","repairData_dataSource", new PostDataSourceInit() {
				
				@Override
				public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
					RepairDataListGrid.this.dataInfo = dataInfo;
					drawRepairDataListGrid(dataSource);
					
				}
			});
		}
		
		public void drawRepairDataListGrid(DataSource dataSource) {  
			setDataSource(dataSource);
			setAutoFetchData(true);
	        setShowFilterEditor(true);
	        setCanEdit(false);
	        addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {				
				@Override
				public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
					Criteria c = event.getCriteria();
					if(partIndexId != null){
						c.addCriteria("partIndexId", partIndexId);
						c.addCriteria("filter", "filter");
						fetchData(c);
					}
				}
			});
	        setShowAllRecords(true);
	        setSelectionType(SelectionStyle.SIMPLE);  
	        setSelectionAppearance(SelectionAppearance.CHECKBOX);   
	        setCellHeight(22);  

			final List<ListGridField> fieldList = new ArrayList<ListGridField>();
			//承修商代码
			ListGridField lgfRepairShopCode = new ListGridField("m_RepairShopCode.code"); 
			lgfRepairShopCode.setCanFilter(true);
			fieldList.add(lgfRepairShopCode);
	        //车间平均维修时间
	        ListGridField lgfMeanShopProcessingTime = new ListGridField("meanShopProcessingTime"); 
	        lgfMeanShopProcessingTime.setType(ListGridFieldType.INTEGER);
	        lgfMeanShopProcessingTime.setCanFilter(true);
	        fieldList.add(lgfMeanShopProcessingTime);
	        //参考修理工时
	        ListGridField lgfReferenceProcessingTime = new ListGridField("referenceProcessingTime"); 
	        lgfReferenceProcessingTime.setType(ListGridFieldType.INTEGER);
	        lgfReferenceProcessingTime.setCanFilter(true);
	        fieldList.add(lgfReferenceProcessingTime);
	        //单位代码
	        ListGridField lgfUnitCode = new ListGridField("m_UnitCode"); 
	        lgfUnitCode.setCanFilter(true);
	        fieldList.add(lgfUnitCode);
	        //修理工作类别
	        ListGridField lgfRepairProcessCodes = new ListGridField("m_RepairProcessCodes"); 
	        lgfRepairProcessCodes.setCanFilter(true);
	        fieldList.add(lgfRepairProcessCodes);
	        //价格类型代码
	        ListGridField lgfPriceTypeCode = new ListGridField("m_PriceTypeCode"); 
	        lgfPriceTypeCode.setCanFilter(true);
	        fieldList.add(lgfPriceTypeCode);
	        //是否可交换
	        ListGridField lgfExchangeUnitAvailableIndicator = new ListGridField("m_ExchangeUnitAvailableIndicator"); 
	        lgfExchangeUnitAvailableIndicator.setCanFilter(true);
	        fieldList.add(lgfExchangeUnitAvailableIndicator);
		
	        ListGridField[] fields = new ListGridField[fieldList.size()];
	        fieldList.toArray(fields);
	        this.setFields(fields);

		}
	}


