package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetails;

/**
 * 物流运单明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ShippingJobDetailsManager extends CommonManager<ShippingJobDetails>{

	/**
	 * 获取物流运单明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流运单明细相关信息
	 */
	public List<ShippingJobDetails> getShippingJobDetails(Map values, int startRow, int endRow);
	
}
