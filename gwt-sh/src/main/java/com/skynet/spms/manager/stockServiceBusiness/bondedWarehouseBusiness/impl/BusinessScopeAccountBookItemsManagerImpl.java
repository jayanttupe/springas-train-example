package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.impl;

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
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BusinessScopeAccountBookItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook.businessScopeAccountBookItems.BusinessScopeAccountBookItems;

@Service
@Transactional
public class BusinessScopeAccountBookItemsManagerImpl extends CommonManagerImpl<BusinessScopeAccountBookItems> implements BusinessScopeAccountBookItemsManager{

	@Override
	public List<BusinessScopeAccountBookItems> getBusinessScopeAccountBookItems(
			Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(BusinessScopeAccountBookItems.class);
		criteria.add(Restrictions.eq("deleted", false));

		// 经营范围电子帐册ID
		if (map.containsKey("businessScopeAccountBookID") && map.get("businessScopeAccountBookID") != null)
		{
			criteria.add(Restrictions.eq("businessScopeAccountBookID", map.get("businessScopeAccountBookID").toString()));
		}
		SqlHelperTool.createCriteria(map, criteria, BusinessScopeAccountBookItems.class, null);
		
		if (endRow > 0) {
			criteria.setFirstResult(startRow).setMaxResults(endRow);;
		}
		return criteria.list();
	}

	@Override
	public void insertBusinessScopeAccountBookItems(
			BusinessScopeAccountBookItems businessScopeAccountBookItems) {
		businessScopeAccountBookItems.setCreateBy(GwtActionHelper.getCurrUser());
		businessScopeAccountBookItems.setCreateDate(new Date());
		getSession().saveOrUpdate(businessScopeAccountBookItems);
	}
}