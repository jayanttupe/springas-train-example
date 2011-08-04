package com.skynet.spms.client.gui.customerService.repairService.sheet;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.customerService.repairService.repairContract.add.ContractTabSet;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.add.RepairContractTemplateAddWin;
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

	/** 送修申请单数据表格 **/
	public RepairRequisitionListGrid repairRequisitionListGrid;

	/** 修改数据源 **/
	public DataSource dsEdit;
	
	
	/** 送修合同添加窗体 **/
	public RepairContractTemplateAddWin repairContractTemplateAddWin;

	/** tabset **/
	public ContractTabSet contractTabSet;

	/** 送修合同数据源 **/
	public DataSource rqContractTemplateDs;
	
	/**地址数据源**/
	public DataSource addressDataSource;

	/** 添加合同主表记录 **/
	public String contractID;
	
	/**附件数据源**/
	public DataSource attachmentDs;
	
	/**文件上传**/
	public MultiUploader defaultUploader = new MultiUploader(FileInputType.BUTTON);
	
	

}
