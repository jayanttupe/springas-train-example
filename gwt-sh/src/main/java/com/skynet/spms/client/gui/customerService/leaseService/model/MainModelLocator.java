package com.skynet.spms.client.gui.customerService.leaseService.model;

import gwtupload.client.MultiUploader;
import gwtupload.client.IFileInput.FileInputType;

import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.LeaseRequisitionSheetItemListGrid;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.LeaseRequisitionSheetListGrid;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui.LeaseContractItemListGrid;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui.LeaseContractListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 * 存放客户租赁业务数据
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

	/** 租赁申请单的ListGrid **/
	public LeaseRequisitionSheetListGrid leaseRequisitionSheetListGrid;
	/** 租赁申请单明细的ListGrid **/
	public LeaseRequisitionSheetItemListGrid leaseRequisitionSheetItemListGrid;
	/** 租赁申请单明细数据源 **/
	public DataSource LeaseRequisitionSheetItemdataSource;
	public String lrID;

	/** 客户合同的ListGrid **/
	public LeaseContractListGrid leaseContractListGrid;
	/** 客户合同数据源 **/
	public DataSource LeaseContractDs;
	/** 客户合同明细的ListGrid **/
	public LeaseContractItemListGrid leaseContractItemListGrid;
	/** 客户合同明细数据源 **/
	public DataSource leaseContractItemDs;
	/** 客户合同主键 **/
	public String LeaseContractId;
	/** 申请供应商租赁指令的ListGrid **/
	public ListGrid leaseInstructListGrid;
	/** 申请供应商租赁指令数据源 **/
	public DataSource leaseInstructDs;
	public DataSource attachmentDs;
	public DataSource addressDataSource;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
	/**客户合同明细的按钮**/
	public IButton saveBtn = new IButton("保存明细");
}
