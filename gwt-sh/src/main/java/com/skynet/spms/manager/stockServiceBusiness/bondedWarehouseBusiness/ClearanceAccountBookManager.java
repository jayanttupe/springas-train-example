/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.ClearanceAccountBook;

/**
 * 通关电子帐册Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ClearanceAccountBookManager extends
		CommonManager<ClearanceAccountBook> {

	/**
	 * 保存通关电子帐册信息
	 * @param clearanceAccountBook
	 * @return 通关电子帐册信息
	 */
	public ClearanceAccountBook saveClearanceAccountBook(
			ClearanceAccountBook clearanceAccountBook);

	/**
	 * 获取通关电子帐册信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册信息
	 */
	public List<ClearanceAccountBook> getClearanceAccountBook(Map values,
			int startRow, int endRow);

}
