package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListBoxItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListBoxItems;

@Service
@Transactional
public class PackingListBoxItemsManagerImpl extends CommonManagerImpl<PackingListBoxItems> implements PackingListBoxItemsManager{

	@Override
	public List<PackingListBoxItems> getPackingListBoxItems(Map map, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(PackingListBoxItems.class);
		criteria.add(Restrictions.eq("deleted", false));
        // 按照项号进行排序
        criteria.addOrder(Order.asc("boxNumber"));
        
        SqlHelperTool.createCriteria(map, criteria, PackingListBoxItems.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}

	@Override
	public boolean isExistPackingBox(String[] rFIDTagUUIDs) {
		for (int i = 0; i < rFIDTagUUIDs.length; i++) {
			Criteria criteria = getSession().createCriteria(PackingListBoxItems.class);
			criteria.add(Restrictions.eq("deleted", false));
			criteria.add(Restrictions.eq("packingRFIDTagUUID", rFIDTagUUIDs[i]));
	        
			if (criteria.list().size() == 0) {
				return false;
			}
		}
		
		return true;
	}
}