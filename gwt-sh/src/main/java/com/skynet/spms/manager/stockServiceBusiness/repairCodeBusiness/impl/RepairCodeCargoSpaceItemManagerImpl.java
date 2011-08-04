package com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItemManager;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodePartItemManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class RepairCodeCargoSpaceItemManagerImpl extends CommonManagerImpl<RepairCodeCargoSpaceItem> implements RepairCodeCargoSpaceItemManager{

	@Override
	public List<RepairCodeCargoSpaceItem> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(RepairCodeCargoSpaceItem.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<RepairCodeCargoSpaceItem> list = criteria.list();
		return list;
	}

}
