package com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.impl;


import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayItemManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.logisticsOutlayItem.LogisticsOutlayItem;

@Service
@Transactional
public class LogisticsOutlayItemManagerImpl extends CommonManagerImpl<LogisticsOutlayItem> implements LogisticsOutlayItemManager{

	public List<LogisticsOutlayItem> getLogisticsOutlayItem(Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(LogisticsOutlayItem.class);
		criteria.add(Restrictions.eq("deleted", false));

		// 调拨单ID
		String logisticsOutlayItemID = "";
		if (map.containsKey("logisticsOutlayItemID"))
		{
			criteria.add(Restrictions.eq("logisticsOutlayItemID", (String)map.get("logisticsOutlayItemID")));
		}

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}
}
