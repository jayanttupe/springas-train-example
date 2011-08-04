/**
 * 
 */
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
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.ClearanceAccountBookManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.ClearanceAccountBook;

@Service
@Transactional
public class ClearanceAccountBookManagerImpl extends CommonManagerImpl<ClearanceAccountBook> implements ClearanceAccountBookManager{

	@Override
	public List<ClearanceAccountBook> getClearanceAccountBook(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(ClearanceAccountBook.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("clearanceAccountBookNumber"));
		SqlHelperTool.createCriteria(values, criteria, ClearanceAccountBook.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	@Override
	public ClearanceAccountBook saveClearanceAccountBook(
			ClearanceAccountBook clearanceAccountBook) {
		Session session = getSession();
		clearanceAccountBook.setCreateBy(GwtActionHelper.getCurrUser());
		clearanceAccountBook.setCreateDate(new Date());
		session.saveOrUpdate(clearanceAccountBook);
		return clearanceAccountBook;
	}

}
