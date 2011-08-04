package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

@Service
@Transactional
public class StockCheckResultItemManagerImpl extends CommonManagerImpl<StockCheckItem> implements StockCheckResultItemManager{

	@Override
	public List<StockCheckItem> getStockCheckItem(Map values){
		Criteria criteria = getSession().createCriteria(StockCheckItem.class);
		SqlHelperTool.createCriteria(values, criteria, StockCheckItem.class, null);
		return criteria.list();
	}
}