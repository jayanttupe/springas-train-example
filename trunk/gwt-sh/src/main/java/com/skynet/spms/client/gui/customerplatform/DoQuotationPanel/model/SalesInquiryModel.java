package com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model;

import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationItemListGrid;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class SalesInquiryModel implements IModelLocator<SalesInquiryModel> {

	private static SalesInquiryModel instance;

	private SalesInquiryModel() {
	}

	public static SalesInquiryModel getInstance() {
		if (instance == null) {
			instance = new SalesInquiryModel();
		}
		return instance;
	}
	public DoQuotationListGrid listGrid;
	public DoQuotationItemListGrid itemListGrid;
	
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	public DataSource requisitionDS;
	public DataSource requisitionItemDS;
	
	
	public String primaryKey;//询价单主键
	
	public ListGridRecord[] seleRecords;//件号查询模板选中的ListGrid
	//public UserVo currentUserVo;

}
