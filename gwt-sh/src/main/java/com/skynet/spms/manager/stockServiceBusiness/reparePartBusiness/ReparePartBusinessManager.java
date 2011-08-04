package com.skynet.spms.manager.stockServiceBusiness.reparePartBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness.ReparePartBusiness;

/**
 * 备件修理信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ReparePartBusinessManager extends CommonManager<ReparePartBusiness>{
	
	/**
	 * 获取备件修理信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件修理信息
	 */
	public List<ReparePartBusiness> getReparePartBusiness(Map values, int startRow, int endRow);

}