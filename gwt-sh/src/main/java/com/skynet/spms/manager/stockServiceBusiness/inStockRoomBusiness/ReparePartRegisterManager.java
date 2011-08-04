package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.reparePartRegister.ReparePartRegister;

/**
 * 送修单登记Manager实现类
 * 
 * @author HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ReparePartRegisterManager extends
		CommonManager<ReparePartRegister> {

	/**
	 * 获取送修单登记信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 送修单登记信息
	 */
	public List<ReparePartRegister> getReparePartRegisterBusiness(Map values,
			int startRow, int endRow);

	/**
	 * 保存送修单登记信息
	 * @param reparePartRegister
	 * @return 送修单登记信息
	 */
	public ReparePartRegister saveReparePartRegister(
			ReparePartRegister reparePartRegister);

}