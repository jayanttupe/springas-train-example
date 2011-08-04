package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.LogicStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

@Service
@Transactional
public class LogicStockManagerImpl extends CommonManagerImpl<LogicStock> implements LogicStockManager{

	@Override
	public void insertLogicStock(LogicStock logicStock){
		logicStock.setCreateBy(GwtActionHelper.getCurrUser());
		logicStock.setCreateDate(new Date());
		getSession().saveOrUpdate(logicStock);
	}
	
	public List<LogicStock> getLogicStock(Map values, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(LogicStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, LogicStock.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}
}
