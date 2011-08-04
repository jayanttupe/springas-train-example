package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJob;

/**
 * 物流交货计划相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface DeliverTheGoodsJobManager extends CommonManager<DeliverTheGoodsJob>{

	/**
	 * 获取物流交货计划相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流交货计划相关信息
	 */
	public List<DeliverTheGoodsJob> getDeliverTheGoodsJob(Map values, int startRow, int endRow);
	
	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setWorkStatus(String orderID);
}
