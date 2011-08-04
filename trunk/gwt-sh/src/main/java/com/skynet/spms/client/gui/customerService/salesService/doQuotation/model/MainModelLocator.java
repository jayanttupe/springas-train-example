package com.skynet.spms.client.gui.customerService.salesService.doQuotation.model;

import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationItemListGrid;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class MainModelLocator implements IModelLocator<MainModelLocator> {

	private static MainModelLocator instance;

	private MainModelLocator() {
	}

	public static MainModelLocator getInstance() {
		if (instance == null) {
			instance = new MainModelLocator();
		}
		return instance;
	}

	public DoQuotationListGrid listGrid;
	public DoQuotationItemListGrid itemListGrid;
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	public DataSource quotationDS;
	public DataSource quotationItemDS;

}
