package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

@Service
@Transactional
public class StockCheckResultManagerImpl extends CommonManagerImpl<StockCheck> implements StockCheckResultManager{

	@Override
	public List<StockCheck> GetStockCheckResult(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "select distinct a ";
		strQuery = strQuery + "from StockCheck a, StockTask b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.id = b.bussinessBillNO ";
		strWhere = strWhere + " and b.taskStatus = '" + TaskStatus.OVR + "' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values, StockCheck.class, "a.", false, null);
		// 字段排序
		String strOrder = "order by a.checkNumber desc ";

		return getSession().createQuery(strQuery + strWhere + strOrder).list();
	}
	
	/**
	 * 更新盘点状态
	 */
	public StockCheck updateRecord(Map map){
		Criteria criteria = getSession().createCriteria(StockCheck.class);
		criteria.add(Restrictions.eq("checkNumber", map.get("checkNumber")));
		StockCheck record =(StockCheck)criteria.uniqueResult();
		record.setCreateBy(GwtActionHelper.getCurrUser());
		record.setCreateDate(new Date());
		getSession().saveOrUpdate(record);
		return record;
	}
}
