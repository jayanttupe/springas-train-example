package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJob;

/**
 * 物流运单相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ShippingJobManager extends CommonManager<ShippingJob>{

	/**
	 * 获取物流运单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流运单相关信息
	 */
	List<ShippingJob> getShippingJob(Map values, int startRow, int endRow);

	/**
	 * 保存物流运单相关信息
	 * @param shippingJob
	 * @return 物流运单相关信息
	 */
	public ShippingJob saveShippingJob(ShippingJob shippingJob);
	
	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setWorkStatus(String orderID);
}
