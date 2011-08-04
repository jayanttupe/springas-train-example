package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.model;

import gwtupload.client.MultiUploader;
import gwtupload.client.IFileInput.FileInputType;

import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetItemListGrid;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class RequisitionModelLocator implements IModelLocator<RequisitionModelLocator> {

	private static RequisitionModelLocator instance;

	private RequisitionModelLocator() {
	}

	public static RequisitionModelLocator getInstance() {
		if (instance == null) {
			instance = new RequisitionModelLocator();
		}
		return instance;
	}
	/** 地址数据源 **/
	public DataSource addressDataSource;

	/** 添加合同主表记录 **/
	public String contractID;
	
	/** 添加合同所属申请单ID **/
	public String sheetID;

	/** 附件数据源 **/
	public DataSource attachmentDs;
	
	/** 合同明细项数据源 **/
	public DataSource saleReqItemDs;
	
	/** 合同 数据源 **/
	public DataSource saleReqDs;

	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
 
	/** 销售单明细表格 **/
	public SalesRequisitionSheetListGrid listGrid;
	
	/** 销售单表格 **/
	public SalesRequisitionSheetItemListGrid itemListGrid;
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	public String primaryKey;
	
	

}
