package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * DTO 数据存储、窗口组件句柄保存传递
 * 
 * @author gqr
 * 
 */
public class ProcurementContractModelLocator implements IModelLocator<ProcurementContractModelLocator> {

	private static ProcurementContractModelLocator instance;

	private ProcurementContractModelLocator() {
	}

	public static ProcurementContractModelLocator getInstance() {
		if (instance == null) {
			instance = new ProcurementContractModelLocator();
		}
		return instance;
	}
	public BaseWindow openedWindow;

	/** 添加合同主表记录 **/
	public String contractID;
	
	/**
	 * 采购指令号
	 */
	public String orderNumber;

	/**
	 * 采购指令主键
	 */
	public String procurementPlanUUid;
	
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);

	public BaseListGrid sheetItemGrid;
	
	/**
	 * 合同明细表格11
	 */
	public BaseListGrid pactItemGrid;
	
	/**
	 * 合同表格
	 */
	public BaseListGrid pactGrid;
	/**
	 * 建立合同的时候选中的行
	 */
	public ListGridRecord selectedRecord;

}
