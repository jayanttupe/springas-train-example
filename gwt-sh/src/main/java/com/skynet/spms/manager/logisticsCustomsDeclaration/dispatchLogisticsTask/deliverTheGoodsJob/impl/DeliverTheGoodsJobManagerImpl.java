package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJob;

@Service
@Transactional
public class DeliverTheGoodsJobManagerImpl extends CommonManagerImpl<DeliverTheGoodsJob> implements DeliverTheGoodsJobManager {

	@Override
	public List<DeliverTheGoodsJob> getDeliverTheGoodsJob(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "from DeliverTheGoodsJob a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and c.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		strWhere = strWhere + " and b.id = c.orderID ";
		strWhere = strWhere + " and b.isPublish = '2' ";
		if (values != null) {
			if (values.containsKey("workStatus")) {
				strWhere = strWhere + " and c.workStatus = '" + values.get("workStatus").toString() + "'";
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.deliverTheGoods + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.deliverTheGoodsNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<DeliverTheGoodsJob> deliverTheGoodsJobList = new ArrayList<DeliverTheGoodsJob>();
		for (Object[] objects : result)
		{
			DeliverTheGoodsJob deliverTheGoodsJob = new DeliverTheGoodsJob();
			if (objects[0] != null)
				deliverTheGoodsJob = (DeliverTheGoodsJob)objects[0];
			
			deliverTheGoodsJobList.add(deliverTheGoodsJob);
		}
		return deliverTheGoodsJobList;
	}
	
	/**
	 * 设置交货工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '6' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}
