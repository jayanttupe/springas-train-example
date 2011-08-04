package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetailsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetails;

@Service
@Transactional
public class ShippingJobDetailsManagerImpl extends CommonManagerImpl<ShippingJobDetails> implements ShippingJobDetailsManager {

	@Override
	public List<ShippingJobDetails> getShippingJobDetails(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(ShippingJobDetails.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, ShippingJobDetails.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		List<ShippingJobDetails> shippingJobDetailsList = criteria.list();
		return shippingJobDetailsList;
	}
}
