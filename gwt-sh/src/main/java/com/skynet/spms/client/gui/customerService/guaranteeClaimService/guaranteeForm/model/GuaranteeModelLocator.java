package com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.model.IModelLocator;

/**
 * DTO 数据存储、窗口组件句柄保存传递
 * 
 * @author fl
 * 
 */
public class GuaranteeModelLocator implements
		IModelLocator<GuaranteeModelLocator> {

	private static GuaranteeModelLocator instance;
	/**
	 * 担保索赔申请单的id
	 */
	public String sheetID;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
	/**
	 * 担保索赔申请单主面板 表格
	 */
	public BaseListGrid mainSheetGrid;
	/**
	 * 担保索赔申请单明细 表格
	 */
	public BaseListGrid itemGrid;
	/**
	 * 当前打开的窗口
	 */
	public BaseWindow openedWindow;

	private GuaranteeModelLocator() {
	}

	public static GuaranteeModelLocator getInstance() {
		if (instance == null) {
			instance = new GuaranteeModelLocator();
		}
		return instance;
	}

}
