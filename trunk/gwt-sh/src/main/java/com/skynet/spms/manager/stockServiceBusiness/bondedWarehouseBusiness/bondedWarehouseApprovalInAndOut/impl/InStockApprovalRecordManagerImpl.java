package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.impl;

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
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.InStockApprovalRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord.InStockApprovalRecord;

@Service
@Transactional
public class InStockApprovalRecordManagerImpl extends CommonManagerImpl<InStockApprovalRecord> implements InStockApprovalRecordManager{

	@Override
	public List<InStockApprovalRecord> getInStockApprovalRecord(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(InStockApprovalRecord.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, InStockApprovalRecord.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}