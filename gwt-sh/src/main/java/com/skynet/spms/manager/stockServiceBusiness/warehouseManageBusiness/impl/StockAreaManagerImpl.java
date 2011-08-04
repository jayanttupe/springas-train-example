package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockAreaManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;
import com.skynet.spms.tools.AnnotationUtil;

@Service
@Transactional
public class StockAreaManagerImpl extends CommonManagerImpl<StockArea> implements StockAreaManager{
	
	public List<StockArea> getStockArea(Map values, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(StockArea.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, StockArea.class, null);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}
		return criteria.list();
	}
}
