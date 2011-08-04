package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model;

import gwtupload.client.MultiUploader;
import gwtupload.client.IFileInput.FileInputType;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.ProcurementPlanItemListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.ProcurementPlanListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.IButton;

/**
 * 存放采购计划业务数据
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

	/** 采购计划 **/
	public ProcurementPlanListGrid procurementPlanListGrid;

	/** 采购计划明细 **/
	public ProcurementPlanItemListGrid procurementPlanItemListGrid;

	/** 采购计划明细数据源 **/
	public DataSource procurementPlanItemListGrid_ds;

	/** 采购计划Id **/
	public String Pid;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);

	public DataSource attachmentDs;
	// 保存采购计划明细的按钮
	public IButton saveItemBtn = new IButton("保存明细");

}
