package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;

/**
 * 经营范围电子账册明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface BusinessScopeAccountBookItemsManager extends
		CommonManager<BusinessScopeAccountBookItems> {

	/**
	 * 添加经营范围电子账册明细
	 * @param businessScopeAccountBookItems
	 */
	public void insertBusinessScopeAccountBookItems(
			BusinessScopeAccountBookItems businessScopeAccountBookItems);

	/**
	 * 获取经营范围电子账册明细
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册明细
	 */
	public List<BusinessScopeAccountBookItems> getBusinessScopeAccountBookItems(
			Map map, int startRow, int endRow);
}