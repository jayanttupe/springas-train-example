package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.model;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.ProcurementInquirySheetItemListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.ProcurementInquirySheetListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class ProcurementInquiryModel implements IModelLocator<ProcurementInquiryModel> {

	private static ProcurementInquiryModel instance;

	private ProcurementInquiryModel() {
	}

	public static ProcurementInquiryModel getInstance() {
		if (instance == null) {
			instance = new ProcurementInquiryModel();
		}
		return instance;
	}

	public ProcurementInquirySheetListGrid listGrid;
	public ProcurementInquirySheetItemListGrid itemListGrid;
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	public DataSource quotationDS;
	public DataSource quotationItemDS;
	
	
	
	

}
