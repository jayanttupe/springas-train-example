/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.spareBoxBusiness.SpareBoxBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spareBoxBusiness.SpareBox;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class SpareBoxBusinessManagerImpl extends CommonManagerImpl<SpareBox> implements SpareBoxBusinessManager{

	@Override
	public List<SpareBox> getSpareBox(Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(SpareBox.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, SpareBox.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}
