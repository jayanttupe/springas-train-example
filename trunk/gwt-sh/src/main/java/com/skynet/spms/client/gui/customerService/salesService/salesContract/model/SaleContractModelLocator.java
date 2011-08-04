package com.skynet.spms.client.gui.customerService.salesService.salesContract.model;

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
 * @author Administrator
 * 
 */
public class SaleContractModelLocator implements IModelLocator<SaleContractModelLocator> {

	private static SaleContractModelLocator instance;

	private SaleContractModelLocator() {
	}

	public static SaleContractModelLocator getInstance() {
		if (instance == null) {
			instance = new SaleContractModelLocator();
		}
		return instance;
	}
	public BaseWindow openedWindow;

	/** 添加合同主表记录 **/
	public String contractID;

	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);

	/**
	 * 申请单明细表格
	 */
	public BaseListGrid sheetItemGrid;
	/**
	 * 合同明细表格
	 */
	public BaseListGrid pactItemGrid;
	
	/**
	 * 合同表格
	 */
	public BaseListGrid saleGrid;
	/**
	 * 选定申请单表格当中的行记录
	 */
	public ListGridRecord selectedRecord;
	/**
	 * 申请单ID
	 */
	public String sheetID;

	
}
