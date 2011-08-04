package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrderManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.DocumentRecords;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.service.UUIDGeneral;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PickupDeliveryOrderManagerImpl extends CommonManagerImpl<PickupDeliveryOrder> implements PickupDeliveryOrderManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<PickupDeliveryOrder> getPickupDeliveryOrder(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(PickupDeliveryOrder.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.desc("orderNumber"));
		SqlHelperTool.createCriteria(values, criteria, PickupDeliveryOrder.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	@Override
	public PickupDeliveryOrder savePickupDeliveryOrder(PickupDeliveryOrder pickupOrder){
		Session session = getSession();
		
		if (pickupOrder.getLogisticsTasksNumber() == null || pickupOrder.getLogisticsTasksNumber().equals("")) {
			// 取得下一个编号
			String logisticsTasksNumber = uUIDGeneral.getSequence("LTN");
			pickupOrder.setLogisticsTasksNumber(logisticsTasksNumber);
		}
		// 更新提发货指令数据
		pickupOrder.setCreateBy(GwtActionHelper.getCurrUser());
		pickupOrder.setCreateDate(new Date());
		session.saveOrUpdate(pickupOrder);

		String strHql = "select count(id) from DocumentRecords where orderID = '" + pickupOrder.getId() + "'";
		List<String> result = session.createQuery(strHql).list();
		if (result != null && result.get(0) != null && Integer.valueOf(String.valueOf(result.get(0))) > 0) {
			// 更新处理 
		} else {
			// 生成单证记录
			DocumentRecords documentRecords = new DocumentRecords();
			documentRecords.setOrderID(pickupOrder.getId());
			documentRecords.setOrderNumber(pickupOrder.getOrderNumber());
			documentRecords.setLogisticsTaskNumber(pickupOrder.getLogisticsTasksNumber());
			documentRecords.setContractNumber(pickupOrder.getContractNumber());
			documentRecords.setCreateBy(GwtActionHelper.getCurrUser());
			documentRecords.setCreateDate(new Date());
			getSession().saveOrUpdate(documentRecords);
		}

		strHql = "select count(id) from LogisticsOutlayRecord where orderID = '" + pickupOrder.getId() + "'";
		result = session.createQuery(strHql).list();
		if (result != null && result.get(0) != null && Integer.valueOf(String.valueOf(result.get(0))) > 0) {
			// 更新处理 
		} else {
			// 生成费用记录
			LogisticsOutlayRecord logisticsOutlayRecord = new LogisticsOutlayRecord();
			logisticsOutlayRecord.setOrderID(pickupOrder.getId());
			logisticsOutlayRecord.setOrderNumber(pickupOrder.getOrderNumber());
			logisticsOutlayRecord.setLogisticsTaskNumber(pickupOrder.getLogisticsTasksNumber());
			logisticsOutlayRecord.setContractNumber(pickupOrder.getContractNumber());
			logisticsOutlayRecord.setCreateBy(GwtActionHelper.getCurrUser());
			logisticsOutlayRecord.setCreateDate(new Date());
			getSession().saveOrUpdate(logisticsOutlayRecord);
		}
		
		return pickupOrder;
	}
}
