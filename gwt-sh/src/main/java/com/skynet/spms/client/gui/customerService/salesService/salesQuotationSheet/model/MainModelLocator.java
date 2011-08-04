package com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model;

import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationItemListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationListGrid;
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
	public SalesQuotationListGrid listGrid;
	public SalesQuotationItemListGrid itemListGrid;
	
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	public DataSource requisitionDS;
	public DataSource requisitionItemDS;
	
	public String userName;//登录用户名
	public String primaryKey;//主键

}
