package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckManager;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class StockCheckManagerImpl extends CommonManagerImpl<StockCheck> implements StockCheckManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<StockCheck> getStockCheck(Map values, int startRow,
			int endRow) {
		Criteria criteria= getSession().createCriteria(StockCheck.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, StockCheck.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	@Override
	public StockCheck saveStockCheck(StockCheck stockCheck){
		Session session = getSession();

		if (stockCheck.getCheckNumber() == null || stockCheck.getCheckNumber().equals("")) {
			// 取得下一个编号
			String checkNumber = uUIDGeneral.getSequence("SCN");
			stockCheck.setCheckNumber(checkNumber);
			stockCheck.setSendStatus(SendStatus.NotSend);
		}

		stockCheck.setCreateBy(GwtActionHelper.getCurrUser());
		stockCheck.setCreateDate(new Date());
		session.saveOrUpdate(stockCheck);
		return stockCheck;
	}
}