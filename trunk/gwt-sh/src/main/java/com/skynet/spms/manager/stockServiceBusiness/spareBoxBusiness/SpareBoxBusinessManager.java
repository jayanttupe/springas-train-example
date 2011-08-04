/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBox;

/**
 * 航材包信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface SpareBoxBusinessManager extends CommonManager<SpareBox>{

	/**
	 * 获取航材包相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材包相关信息
	 */
	public List<SpareBox> getSpareBox(Map values, int startRow, int endRow);
}
