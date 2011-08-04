/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultTrackManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

@Service
@Transactional
public class StockCheckResultTrackManagerImpl extends CommonManagerImpl<StockCheck> implements StockCheckResultTrackManager{

	@Override
	public List<StockCheck> getStockCheckResultTrack(int startRow, int endRow)
	{
		Criteria criteria = getSession().createCriteria(StockCheck.class);
		criteria.add(Restrictions.eq("deleted",false));
		criteria.add(Restrictions.eq("state","已上报"));
		return criteria.list();
	}

	@Override
	public List<StockCheck> getStockCheckResultTrackByCondition(Map values,
			int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(StockCheck.class);
		criteria.add(Restrictions.eq("deleted", false));
		
		Set set = values.entrySet();
		Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
        	Map.Entry entry =(Map.Entry)iterator.next();
        	String fieldName = (String)entry.getKey().toString();
    		criteria.add(Restrictions.ilike(fieldName, values.get(fieldName).toString(), MatchMode.ANYWHERE));
        }
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

}
