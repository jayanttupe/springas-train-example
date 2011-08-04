package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PickupDeliveryVanningManagerImpl extends CommonManagerImpl<PickupDeliveryVanning> implements PickupDeliveryVanningManager{

	@Override
	public List<PickupDeliveryVanning> getPickupDeliveryVanning(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(PickupDeliveryVanning.class);
		criteria.addOrder(Order.asc("packingListNumber").asc("pacakgeNumber"));
		SqlHelperTool.createCriteria(values, criteria, PickupDeliveryVanning.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}
