/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBox;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBoxItems;

/**
 * 航材包明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface SpareBoxItemsManager extends CommonManager<SpareBoxItems>{

	/**
	 * 获取航材包明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材包明细信息
	 */ 
	public List<SpareBoxItems> getSpareBoxItems(Map values, int startRow, int endRow);
}

