package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;

public class ConsignRenewModel {
	private static ConsignRenewModel me;
	private ConsignRenewModel(){
		
	}
	public static ConsignRenewModel getInstance(){
		if(me==null){
			me=new ConsignRenewModel();
		}
		return me;
	}
	/**
	 * 当前打开的窗口
	 */
	public BaseWindow openedWindow;
	/**
	 * 寄售补库申请单主键ID
	 */
	public String consignRenewId;
	/**
	 * 寄售补库申请单表格
	 */
	public BaseListGrid consignRenewGrid;
	/**
	 * 寄售补库明细表格
	 */
	public BaseListGrid sheetItemGrid;
	/**
	 * 寄售补库协议表格
	 */
	public BaseListGrid pactItemGrid;
	/**
	 * 寄售补库申请关联的协议主键
	 */
	public String protocolId;
	/**
	 * 寄售补库申请关联的协议编号
	 */
	public String contractNumber;
	
	
}
