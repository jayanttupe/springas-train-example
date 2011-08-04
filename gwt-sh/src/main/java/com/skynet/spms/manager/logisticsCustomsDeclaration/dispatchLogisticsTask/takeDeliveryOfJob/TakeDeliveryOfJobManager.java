package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJob;

/**
 * 物流取货计划相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface TakeDeliveryOfJobManager extends CommonManager<TakeDeliveryOfJob>{
	
	/**
	 * 获取物流取货计划相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流取货计划信息
	 */
	public List<TakeDeliveryOfJob> getTaskDeliveryOfJob(Map values, int startRow, int endRow);

	/**
	 * 保存物流取货计划相关信息
	 * @param takeDeliveryOfJob
	 * @return 物流取货计划信息
	 */
	public TakeDeliveryOfJob saveTaskDeliveryOfJob(TakeDeliveryOfJob takeDeliveryOfJob);

	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setWorkStatus(String orderID);
}
