package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;

@Service
@Transactional
public class StockMovingRecordItemsManagerImpl extends CommonManagerImpl<StockMovingRecordItems> implements StockMovingRecordItemsManager{

	public List<StockMovingRecordItems> findby(String id){
		Criteria criteria = getSession().createCriteria(StockMovingRecordItems.class);
		criteria.add(Restrictions.eq("stockMovingNumber",id));
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();
		
	}
	
	public List<StockMovingRecordItems> findbyall(){
		Criteria criteria = getSession().createCriteria(StockMovingRecordItems.class);
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();
	}

	@Override
	public void InsertStockMovingRecordItems(
			StockMovingRecordItems stockMovingRecordItems) {
		stockMovingRecordItems.setCreateBy(GwtActionHelper.getCurrUser());
		stockMovingRecordItems.setCreateDate(new Date());
		getSession().saveOrUpdate(stockMovingRecordItems);
		
	}

	@Override
	public List<StockMovingRecordItems> getStockMovingRecordItems(Map map,int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(StockMovingRecordItems.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(map, criteria, StockMovingRecordItems.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}

	
}