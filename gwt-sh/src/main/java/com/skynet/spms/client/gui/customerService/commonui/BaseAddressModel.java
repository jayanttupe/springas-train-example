package com.skynet.spms.client.gui.customerService.commonui;

import java.util.LinkedHashMap;

import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.model.IModelLocator;
import com.skynet.spms.client.vo.AddressVO;

public class BaseAddressModel implements
		IModelLocator<SaleContractModelLocator> {
	private static BaseAddressModel instance;

	private BaseAddressModel() {
	}

	public static BaseAddressModel getInstance() {
		if (instance == null) {
			instance = new BaseAddressModel();
		}
		return instance;
	}
	
	/**
	 * 地址信息关联的表单主键
	 */
	public String addr_sheetId;
	/**
	 * 地址信息关联的业务编号（企业编号），如：客户/供应商等
	 */
	public String enterpriseId;
	/**
	 * 所传入的业务编号的业务类型
	 */
	public String businessType;
	/**
	 * 发货地址集合
	 */
	public LinkedHashMap<String, AddressVO> shippingAddrMap;
	/**
	 * 发票地址集合
	 */
	public LinkedHashMap<String, AddressVO> invocieAddrMap;
	/**
	 * 收货地址集合
	 */
	public LinkedHashMap<String, AddressVO> consigneeAddrMap;
}
