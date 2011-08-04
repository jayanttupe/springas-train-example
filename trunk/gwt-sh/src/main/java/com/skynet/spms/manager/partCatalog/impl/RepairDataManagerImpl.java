package com.skynet.spms.manager.partCatalog.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.RepairDataManager;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.partCatalog.repairableCatalog.RepairData;

@Service
@Transactional
public class RepairDataManagerImpl extends CommonManagerImpl<RepairData> implements RepairDataManager
{

	@Override
	public List<RepairData> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(RepairData.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<RepairData> list = criteria.list();
		return list;
	}

	@Override
	public RepairData updateCascade(Map<String, Object> values,String itemId) {
		String repairCodeId = (String)values.remove("repairCodeId");
		RepairData repairData = (RepairData)getSession().get(RepairData.class, itemId);
		if(repairCodeId != null){
			RepairShopCode repairShopCode = (RepairShopCode)getSession().get(RepairShopCode.class, repairCodeId);
			repairData.setM_RepairShopCode(repairShopCode);
		}
		BeanPropUtil.fillEntityWithMap(repairData, values);
		getSession().saveOrUpdate(repairData);
		return repairData;
	}

	@Override
	public void insertCascade(RepairData repairData) {
		String repairCodeId = repairData.getRepairCodeId();
		if(repairCodeId != null){
			RepairShopCode repairShopCode = (RepairShopCode)getSession().get(RepairShopCode.class, repairCodeId);
			repairData.setM_RepairShopCode(repairShopCode);
		}
		
		String user = GwtActionHelper.getCurrUser();
		repairData.setCreateBy(user);
		repairData.setCreateDate(new Date());
		getSession().saveOrUpdate(repairData);
		
	}

	@Override
	public List<RepairData> queryByFilter(Map<String, Object> values) {
		Query query = CriteriaConverter.convertValueMapToQuery(getSession(), values, RepairData.class);
		return query.list();
	}
}
