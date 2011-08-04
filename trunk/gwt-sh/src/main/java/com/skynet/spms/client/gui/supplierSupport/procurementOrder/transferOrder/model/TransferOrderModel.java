package com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.model;

import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.TransferOrderItemListGrid;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.TransferOrderListGrid;
import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.data.DataSource;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class TransferOrderModel implements IModelLocator<TransferOrderModel> {

	private static TransferOrderModel instance;

	private TransferOrderModel() {
	}

	public static TransferOrderModel getInstance() {
		if (instance == null) {
			instance = new TransferOrderModel();
		}
		return instance;
	}

	public TransferOrderListGrid listGrid;
	public TransferOrderItemListGrid itemListGrid;
	public DataSource dataSource;
	public DataSource itemDataSource;
	
	
	
	

}
