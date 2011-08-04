package com.skynet.spms.client.gui.commonOrder;

import com.skynet.spms.client.gui.base.EnumTool;

public class InitLocal {

	static {
		// 初始一些国际化值
		EnumTool.initValueMap(EnumTool.INTERNATIONALCURRENCYCODE);
		EnumTool.initValueMap(EnumTool.UNITOFMEASURECODE);
		
		EnumTool.initValueMap(EnumTool.PRIORITY);
		EnumTool.initValueMap(EnumTool.BUSINESSTYPE);
		EnumTool.initValueMap(EnumTool.DELIVERYTYPE);
		EnumTool.initValueMap(EnumTool.TRADEMETHODS);
		EnumTool.initValueMap(EnumTool.SPECIFIEDSHIPPINGMETHODCODE);
		
	}
	
}
