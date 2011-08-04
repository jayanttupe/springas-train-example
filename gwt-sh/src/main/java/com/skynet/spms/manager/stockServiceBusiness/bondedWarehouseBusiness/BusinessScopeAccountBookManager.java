package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.BusinessScopeAccountBook;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;

/**
 * 经营范围电子账册Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface BusinessScopeAccountBookManager extends
		CommonManager<BusinessScopeAccountBook> {

	/**
	 * 保存经营范围电子账册
	 * @param businessScopeAccountBook
	 * @return 经营范围电子账册
	 */
	public BusinessScopeAccountBook saveBusinessScopeAccountBook(
			BusinessScopeAccountBook businessScopeAccountBook);

	/**
	 * 获取经营范围电子账册
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 经营范围电子账册
	 */
	public List<BusinessScopeAccountBook> getBusinessScopeAccountBook(
			Map values, int startRow, int endRow);

}