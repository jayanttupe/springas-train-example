package com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.AllocationGoodManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationGood;

@Service
@Transactional
public class AllocationGoodManagerImpl extends CommonManagerImpl<AllocationGood> implements AllocationGoodManager{

	@Override
	public List<AllocationGood> getAllocationGood(
			Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(AllocationGood.class);
		criteria.add(Restrictions.eq("deleted", false));

		// 调拨单ID
		if (map.containsKey("allocationBillID") && map.get("allocationBillID") != null)
		{
			criteria.add(Restrictions.eq("allocationBillID", map.get("allocationBillID").toString()));
		}

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}

	@Override
	public void insertAllocationGood(
			AllocationGood allocationGood) {
		allocationGood.setCreateBy(GwtActionHelper.getCurrUser());
		allocationGood.setCreateDate(new Date());
		getSession().saveOrUpdate(allocationGood);
		}

}