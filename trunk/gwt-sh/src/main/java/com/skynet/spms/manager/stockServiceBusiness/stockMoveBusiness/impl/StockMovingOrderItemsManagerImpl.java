/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingOrderItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;

@Service
@Transactional
public class StockMovingOrderItemsManagerImpl extends CommonManagerImpl<BaseTaskItem> implements StockMovingOrderItemManager{
	
	public List<BaseTaskItem> getStockInfo(Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(BaseTaskItem.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(map, criteria, BaseTaskItem.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}
}
