package com.skynet.spms.manager.stockServiceBusiness.discardServiceBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.discardServiceBusiness.DiscardServiceBusiness;

/**
 * 备件报废信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface DiscardServiceBusinessManager extends CommonManager<DiscardServiceBusiness>{
	
	/**
	 * 获取备件报废信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件报废信息
	 */
	public List<DiscardServiceBusiness> getDiscardServiceBusiness(Map values, int startRow, int endRow);
	
	/**
	 * 保存备件报废信息
	 * @param discardServiceBusiness
	 * @return 备件报废信息
	 */
	public DiscardServiceBusiness saveDiscardServiceBusiness(DiscardServiceBusiness discardServiceBusiness);
}