package com.skynet.spms.client.gui.supplierSupport.leaseService.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract.SSLeaseContractListGrid;
import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseInstruct.LeaseInstructListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 * 存放供应商租赁业务数据
 * 
 * @author
 * 
 */
public class SSMainModelLocator implements IModelLocator<SSMainModelLocator> {

	private static SSMainModelLocator instance;

	private SSMainModelLocator() {
	}

	public static SSMainModelLocator getInstance() {
		if (instance == null) {
			instance = new SSMainModelLocator();
		}
		return instance;
	}

	/** 供应商租赁合同明细数据源 **/
	public DataSource SSleaseContractItemDs;
	/** 租赁申请指令的ListGrid **/
	public LeaseInstructListGrid leaseInstructListGrid;
	/** 客户租赁合同数据源 **/
	public DataSource leaseContractDs;
	/** 供应商租赁合同数据源 **/
	public DataSource SSLeaseContractDs;
	/** 供应商附件数据源 **/
	public DataSource SSattachmentDs;
	/** 供应商地址数据源 **/
	public DataSource SSaddressDataSource;
	/** 供应商合同Id **/
	public String SSLeaseContractId;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
	/** 供应商合同的ListGrid **/
	public SSLeaseContractListGrid ssleaseContractListGrid;
	/** 供应商租赁合同明细的按钮 **/
	public IButton saveBtn = new IButton("保存明细");
	public DataSource leaseContractItemDs;
}
