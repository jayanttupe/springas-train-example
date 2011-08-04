package com.skynet.spms.client.gui.partcatalog.salesCatalog.partSupplierPriceIndex;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.layout.VLayout;

public class PartSupplierPriceIndexPanel extends VLayout {

	private PartSupplierPriceIndexListGrid partSupplierPriceIndexListGrid;
	
	public PartSupplierPriceIndexPanel() {
		partSupplierPriceIndexListGrid=new PartSupplierPriceIndexListGrid();
		
		this.addMember(partSupplierPriceIndexListGrid);
	}
		 
		
	public void fetchData(String partIndexId){
			
		Criteria criteria = new Criteria("partIndexId",partIndexId);
			
		partSupplierPriceIndexListGrid.fetchData(criteria);
			
	}
	
}
 
