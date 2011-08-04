package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PickupDeliveryVanningItemsManagerImpl extends CommonManagerImpl<PickupDeliveryVanningItems> implements PickupDeliveryVanningItemsManager{

	@Override
	public List<PickupDeliveryVanningItems> getPickupDeliveryVanningItems(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(PickupDeliveryVanningItems.class);
		criteria.add(Restrictions.eq("deleted", false));
		
		if (values != null) {
			Set set = values.entrySet();
			Iterator iterator = set.iterator();
	        while(iterator.hasNext())
	        {
	        	Map.Entry entry =(Map.Entry)iterator.next();
	        	String fieldName = (String)entry.getKey().toString();
	        	if (!fieldName.equals("temp")) {
	        		criteria.add(Restrictions.eq(fieldName, values.get(fieldName).toString()));
	        	}
	        }
		}       
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}
