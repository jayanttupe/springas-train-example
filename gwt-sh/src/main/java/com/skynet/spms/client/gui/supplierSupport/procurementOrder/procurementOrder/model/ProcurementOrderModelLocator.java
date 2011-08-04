package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.ProcurementOrderListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add.ProcurementOrderAddWindow;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放采购指令业务数据
 * 
 * @author tqc
 * 
 */
public class ProcurementOrderModelLocator implements
		IModelLocator<ProcurementOrderModelLocator> {

	private static ProcurementOrderModelLocator instance;

	private ProcurementOrderModelLocator() {
	}

	public static ProcurementOrderModelLocator getInstance() {
		if (instance == null) {
			instance = new ProcurementOrderModelLocator();
		}
		return instance;
	}

	/** 添加采购指令主表记录 **/
	public String sheetID;
	/** 采购指令的ListGrid **/
	public ProcurementOrderListGrid procurementOrderListGrid;
	/** 采购指令数据源 **/
	public DataSource procurementOrderDs;

	/** 采购指令明细数据源 **/
	public DataSource procurementOrderItemDs;

	public ProcurementOrderAddWindow procurementOrderAddWin;

	/** 采购询价单DS **/
	public DataSource procurementInquiryDS;
	/** 采购询价单明细DS **/
	public DataSource procurementInquiryItemDS;

	/** 采购调拨单DS **/
	public DataSource procurementTransferDS;
	/** 采购调拨单明细DS **/
	public DataSource procurementTransferItemDS;

	public ProcurementOrderListGrid doProcurementOrderListGrid;
	public String doProcurementOrderId;

}
