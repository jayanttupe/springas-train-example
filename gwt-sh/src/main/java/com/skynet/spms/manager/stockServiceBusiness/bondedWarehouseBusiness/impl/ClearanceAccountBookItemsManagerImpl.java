/**
 * 
 */
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
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.ClearanceAccountBookItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;

@Service
@Transactional
public class ClearanceAccountBookItemsManagerImpl extends CommonManagerImpl<ClearanceAccountBookItems> implements ClearanceAccountBookItemsManager{

	@Override
	public List<ClearanceAccountBookItems> getClearanceAccountBookItems(
			Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(ClearanceAccountBookItems.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(map, criteria, ClearanceAccountBookItems.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}

	@Override
	public void insertClearanceAccountBookItems(
			ClearanceAccountBookItems clearanceAccountBookItems) {
		clearanceAccountBookItems.setCreateBy(GwtActionHelper.getCurrUser());
		clearanceAccountBookItems.setCreateDate(new Date());
		getSession().saveOrUpdate(clearanceAccountBookItems);
		}

}

