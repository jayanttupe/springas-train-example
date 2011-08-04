package com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.logisticsOutlayItem.LogisticsOutlayItem;

/**
 * 费用记录管理明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface LogisticsOutlayItemManager extends CommonManager<LogisticsOutlayItem>{

	/**
	 * 获取费用记录管理明细相关信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理明细相关信息
	 */
	public List<LogisticsOutlayItem> getLogisticsOutlayItem(Map map, int startRow, int endRow);
}