/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingOrderManager;     
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class StockMovingOrderManageImpl extends CommonManagerImpl<BaseTask> implements StockMovingOrderManager{

	@Override
	public List<BaseTask> findbystate() {
		Criteria criteria = getSession().createCriteria(BaseTask.class);
		criteria.add(Restrictions.eq("taskType", "移库"));
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();
	}
	
	@Override
	public List<BaseTask> getStockMovingOrder(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(BaseTask.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, BaseTask.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	
}
