package com.skynet.spms.client.gui.partcatalog.salesCatalog.editionsInfo;


import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.layout.VLayout;
public class SalesHistoryEditionsPanel extends VLayout {
	
	private SalesHistoryEditionsListGrid salesHistoryEditionsListGrid;
	
	public SalesHistoryEditionsPanel() {
		
		salesHistoryEditionsListGrid = new SalesHistoryEditionsListGrid();
		
		this.addMember(salesHistoryEditionsListGrid);
    	
     	
		
	}
	
	public void fetchData(String partSaleReleaseId){
		salesHistoryEditionsListGrid.setPartSaleReleaseId(partSaleReleaseId);
		Criteria criteria = new Criteria("partSaleReleaseId",partSaleReleaseId);
		salesHistoryEditionsListGrid.fetchData(criteria);
		
	}

}