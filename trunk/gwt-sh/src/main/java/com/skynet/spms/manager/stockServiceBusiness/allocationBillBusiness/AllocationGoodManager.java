package com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationGood;

/**
 * 调拨单明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface AllocationGoodManager extends CommonManager<AllocationGood>{

	/**
	 * 新增调拨单
	 * @param allocationGood
	 */
	public void insertAllocationGood(AllocationGood allocationGood);
	
	/**
	 * 获取调拨单
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 调拨单明细
	 */
	public List<AllocationGood> getAllocationGood(Map map, int startRow, int endRow);
}