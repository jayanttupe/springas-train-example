package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.PartPlanNeedOrderListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放备件需求的业务数据
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

	// 供应商采购业务

	/** 备件需求 **/
	public PartPlanNeedOrderListGrid partPlanNeedOrderListGrid;
	/** 备件需求附件 **/
	public DataSource attachmentDs;
	/** 备件需求ID **/
	public String partPlanNeedOrderId;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);

}
