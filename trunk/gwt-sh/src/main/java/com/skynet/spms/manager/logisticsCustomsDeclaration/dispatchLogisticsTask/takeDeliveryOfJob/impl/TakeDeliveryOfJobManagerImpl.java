package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.tools.ConvertCodeName;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class TakeDeliveryOfJobManagerImpl extends CommonManagerImpl<TakeDeliveryOfJob> implements TakeDeliveryOfJobManager{

	@Override
	public List<TakeDeliveryOfJob> getTaskDeliveryOfJob(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from TakeDeliveryOfJob a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
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
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.takeDeliveryOf + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.takeDeliveryNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<TakeDeliveryOfJob> takeDeliveryOfJobList = new ArrayList<TakeDeliveryOfJob>();
		for (Object[] objects : result)
		{
			TakeDeliveryOfJob takeDeliveryOfJob = new TakeDeliveryOfJob();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 取货工作信息
			if (objects[0] != null)
				takeDeliveryOfJob = (TakeDeliveryOfJob)objects[0];
			// 提发货指令信息
			if (objects[1] != null)
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
			
			takeDeliveryOfJob.setPickupDeliveryOrder(pickupDeliveryOrder);
			takeDeliveryOfJobList.add(takeDeliveryOfJob);
		}
		
		for (int i = 0; i < takeDeliveryOfJobList.size(); i++)
		{
			TakeDeliveryOfJob takeDeliveryOfJob = (TakeDeliveryOfJob)takeDeliveryOfJobList.get(i);
			takeDeliveryOfJob.setStatusName(ConvertCodeName.GetWorkStatus(takeDeliveryOfJob.getStatus()));
		}
		return takeDeliveryOfJobList;
	}

	@Override
	public TakeDeliveryOfJob saveTaskDeliveryOfJob(TakeDeliveryOfJob takeDeliveryOfJob) {
		takeDeliveryOfJob.setCreateBy(GwtActionHelper.getCurrUser());
		takeDeliveryOfJob.setCreateDate(new Date());

		// 保存订舱信息
		getSession().saveOrUpdate(takeDeliveryOfJob);

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "transportationCode = :transportationCode, ";
		strHql = strHql + "carrierID = :carrierID ";
		strHql = strHql + "where id = :orderID ";
		
		Query packingListQuery = getSession().createQuery(strHql);
		PickupDeliveryOrder pickupDeliveryOrder = takeDeliveryOfJob.getPickupDeliveryOrder();
		packingListQuery.setString("transportationCode", pickupDeliveryOrder.getTransportationCode());
		packingListQuery.setString("carrierID", pickupDeliveryOrder.getCarrierID());
		packingListQuery.setString("orderID", pickupDeliveryOrder.getId());
		// 更新提发货信息
		packingListQuery.executeUpdate();
		
		return takeDeliveryOfJob;
	}
	
	/**
	 * 设置起运工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '2' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}