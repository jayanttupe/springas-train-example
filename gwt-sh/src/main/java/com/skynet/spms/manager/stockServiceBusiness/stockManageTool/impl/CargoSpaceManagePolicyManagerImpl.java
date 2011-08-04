package com.skynet.spms.manager.stockServiceBusiness.stockManageTool.impl;

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
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.CargoSpaceManagePolicyManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy.CargoSpaceManagePolicy;

@Service
@Transactional
public class CargoSpaceManagePolicyManagerImpl extends CommonManagerImpl<CargoSpaceManagePolicy> implements CargoSpaceManagePolicyManager{

	@Override
	public List<CargoSpaceManagePolicy> getCargoSpaceManagePolicy(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(CargoSpaceManagePolicy.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, CargoSpaceManagePolicy.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}