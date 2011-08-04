package com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.business;

import java.util.Date;

import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.skynet.spms.client.gui.base.BaseBusiness;
/**
 * 申请单业务类
 * **/
public class SalesQuotationBusiness extends BaseBusiness{

	public MainModelLocator model = MainModelLocator.getInstance();
	
	/**
	 * 刷新ItemListGrid
	 * 
	 * @param grid
	 */
	public void refeshItemListGrid() {
		super.refeshListGrid(model.itemListGrid);
	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {
		super.refeshListGrid(model.listGrid);
	}


	
}
