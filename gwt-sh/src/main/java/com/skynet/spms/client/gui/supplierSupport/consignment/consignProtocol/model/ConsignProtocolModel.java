package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.MultiUploader;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;

public class ConsignProtocolModel {
	private static ConsignProtocolModel me;
	private ConsignProtocolModel(){
		
	}
	public static ConsignProtocolModel getInstance(){
		if(me==null){
			me=new ConsignProtocolModel();
		}
		return me;
	}
	/**
	 * 当前打开的窗口
	 */
	public BaseWindow openedWindow;
	/**
	 * 寄售协议表格
	 */
	public BaseListGrid proMainGrid;
	/**
	 * 寄售协议明细表格
	 */
	public BaseListGrid proItemGrid;
	/**
	 * 协议的主键uuid
	 */
	public String protocolId;
	/** 文件上传 **/
	public MultiUploader defaultUploader = new MultiUploader(
			FileInputType.BUTTON);
}
