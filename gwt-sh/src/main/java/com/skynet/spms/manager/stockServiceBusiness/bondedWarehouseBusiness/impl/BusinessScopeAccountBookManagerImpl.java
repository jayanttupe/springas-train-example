package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BusinessScopeAccountBookManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.BusinessScopeAccountBook;

@Service
@Transactional
public class BusinessScopeAccountBookManagerImpl extends CommonManagerImpl<BusinessScopeAccountBook> implements BusinessScopeAccountBookManager{

	@Override
	public List<BusinessScopeAccountBook> getBusinessScopeAccountBook(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(BusinessScopeAccountBook.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("accountBookItemsNumber"));
		SqlHelperTool.createCriteria(values, criteria, BusinessScopeAccountBook.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	@Override
	public BusinessScopeAccountBook saveBusinessScopeAccountBook(
			BusinessScopeAccountBook businessScopeAccountBook) {
		Session session = getSession();
		businessScopeAccountBook.setCreateBy(GwtActionHelper.getCurrUser());
		businessScopeAccountBook.setCreateDate(new Date());
		session.saveOrUpdate(businessScopeAccountBook);
		return businessScopeAccountBook;
	}

}