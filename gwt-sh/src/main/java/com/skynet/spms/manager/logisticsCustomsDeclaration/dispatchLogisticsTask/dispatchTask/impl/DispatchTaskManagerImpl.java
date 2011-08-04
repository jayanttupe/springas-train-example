package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.DispatchTaskManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.tools.ConvertCodeName;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class DispatchTaskManagerImpl extends CommonManagerImpl<PickupDeliveryOrder> implements DispatchTaskManager{

	@Override
	public List<PickupDeliveryOrder> getDispatchTask(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(PickupDeliveryOrder.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, PickupDeliveryOrder.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		List<PickupDeliveryOrder> pickupOrderList = criteria.list();
		for (int i = 0; i < pickupOrderList.size(); i++)
		{
			PickupDeliveryOrder pickupDeliveryOrder = (PickupDeliveryOrder)pickupOrderList.get(i);
			pickupDeliveryOrder.setStatusName(ConvertCodeName.GetPickupDeliveryStatusName(pickupDeliveryOrder.getStatus()));
		}
		return pickupOrderList;
	}
	
	/**
	 * 设置指令任务状态为已完成
	 */
	@Override
	public void setTaskStatus(String orderID) {
		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "status = '3' ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新指令任务状态为已完成
		pickupDeliveryQuery.executeUpdate();
	}
}
