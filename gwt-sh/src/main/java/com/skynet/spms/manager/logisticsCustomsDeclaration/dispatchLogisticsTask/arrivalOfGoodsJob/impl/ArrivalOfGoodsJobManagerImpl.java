package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJob;

@Service
@Transactional
public class ArrivalOfGoodsJobManagerImpl extends CommonManagerImpl<ArrivalOfGoodsJob> implements ArrivalOfGoodsJobManager {

	@Override
	public List<ArrivalOfGoodsJob> getArrivalOfGoodsJob(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "from ArrivalOfGoodsJob a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
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
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.arrivalOfGoods + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.arrivalOfGoodsNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ArrivalOfGoodsJob> arrivalOfGoodsJobList = new ArrayList<ArrivalOfGoodsJob>();
		for (Object[] objects : result)
		{
			ArrivalOfGoodsJob arrivalOfGoodsJob = new ArrivalOfGoodsJob();
			if (objects[0] != null)
				arrivalOfGoodsJob = (ArrivalOfGoodsJob)objects[0];
			
			arrivalOfGoodsJobList.add(arrivalOfGoodsJob);
		}
		return arrivalOfGoodsJobList;
	}
	
	/**
	 * 设置到达工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '4' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}