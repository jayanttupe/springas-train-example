package com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationBillBusiness;

/**
 * 调拨单Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface AllocationBillBusinessManager extends CommonManager<AllocationBillBusiness>{

	/**
	 * 保存调拨单
	 * @param allocationBillBusiness 调拨单
	 */
	public AllocationBillBusiness saveAllocationBillBusiness(AllocationBillBusiness allocationBillBusiness);
	
	/**
	 * 获取调拨单
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 调拨单
	 */
	public List<AllocationBillBusiness> getAllocationBillBusiness(Map values, int startRow, int endRow);
}