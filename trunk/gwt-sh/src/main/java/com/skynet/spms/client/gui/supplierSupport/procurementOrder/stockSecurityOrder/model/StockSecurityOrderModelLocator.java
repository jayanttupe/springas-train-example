package com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder.model;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.ProcurementOrderListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add.ProcurementOrderAddWindow;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放采购指令业务数据
 * 
 * @author tqc
 * 
 */
public class StockSecurityOrderModelLocator implements
		IModelLocator<StockSecurityOrderModelLocator> {

	private static StockSecurityOrderModelLocator instance;

	private StockSecurityOrderModelLocator() {
	}

	public static StockSecurityOrderModelLocator getInstance() {
		if (instance == null) {
			instance = new StockSecurityOrderModelLocator();
		}
		return instance;
	}


	
	
	/**采购询价单DS**/
	public DataSource procurementInquiryDS;
	/**采购询价单明细DS**/
	public DataSource procurementInquiryItemDS;
	

}
