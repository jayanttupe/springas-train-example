package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.model.IModelLocator;

/**
 * DTO 数据存储、窗口组件句柄保存传递
 * 
 * @author Administrator
 * 
 */
public class BuybackPactModelLocator implements IModelLocator<BuybackPactModelLocator> {

	private static BuybackPactModelLocator instance;

	private BuybackPactModelLocator() {
	}

	public static BuybackPactModelLocator getInstance() {
		if (instance == null) {
			instance = new BuybackPactModelLocator();
		}
		return instance;
	}

	public BaseWindow openedWindow;

	/** 添加合同主表记录 **/
	public String contractID;
	
	/** 添加合同所属申请单编号 **/
	public String requisitionSheetNumber;
	/** 添加合同所属申请单ID **/
	public String sheetId;
	
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
	public BaseListGrid pactGrid;
	/**
	 * 客户
	 */
	public String customerIdentificationCode;
	/**
	 * 联系人
	 */
	public String linkMan;
	/**
	 * 联系方式
	 */
	public String linkType;

	
	
}
