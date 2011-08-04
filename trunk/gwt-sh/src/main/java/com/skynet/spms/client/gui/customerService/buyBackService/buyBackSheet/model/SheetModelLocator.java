package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui.BuyBackSheetGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.model.IModelLocator;

/**
 * DTO 数据存储、窗口组件句柄保存传递
 * 
 * @author fl
 * 
 */
public class SheetModelLocator implements IModelLocator<SheetModelLocator> {

	private static SheetModelLocator instance;

	private SheetModelLocator() {
	}

	public static SheetModelLocator getInstance() {
		if (instance == null) {
			instance = new SheetModelLocator();
		}
		return instance;
	}
	/** 添加申请单主表记录 **/
	public String sheetID;
	
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
	/** 申请单明细表格 **/
	public BaseListGrid sheetItemGrid;
	/** 申请单表格 **/
	public BuyBackSheetGrid backSheetGrid;
	/**
	 * 当前打开窗口
	 */
	public BaseWindow openenWindow;

}
