package com.skynet.spms.client.gui.supplierSupport.customerRepairInsOrder;

import com.skynet.spms.client.model.IModelLocator;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class ModelLocator implements IModelLocator<ModelLocator> {
	
	private static ModelLocator instance;

	private ModelLocator() {
	}

	public static ModelLocator getInstance() {
		if (instance == null) {
			instance = new ModelLocator();
		}
		return instance;
	}
	

}
