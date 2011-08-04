package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

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
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetHistory;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

@Service
@Transactional
public class ReceivingSheetHistoryImpl extends
		CommonManagerImpl<ReceivingSheet> implements ReceivingSheetHistory {

	@Override
	public List<ReceivingSheet> getReceivingSheetHistory(Map values,
			int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(ReceivingSheet.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, ReceivingSheet.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}
