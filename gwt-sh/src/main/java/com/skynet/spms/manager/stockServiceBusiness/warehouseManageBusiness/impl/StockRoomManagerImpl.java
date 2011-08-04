package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockRoomManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

@Service
@Transactional
public class StockRoomManagerImpl extends CommonManagerImpl<StockRoom> implements StockRoomManager{

	@Override
	public StockRoom saveStockRoom(StockRoom stockRoom){
		Session session = getSession();

		if (stockRoom.getStockRoomNumber() == null || stockRoom.getStockRoomNumber().equals("")) {
			// 备件中心位置
			String partCentreLocation = stockRoom.getPartCentreLocation();
			String strHql = "select max(stockRoomNumber) from StockRoom where stockRoomNumber like '" + partCentreLocation + "%'";
			List<String> result = session.createQuery(strHql).list();
			if (result != null && result.get(0) != null) {
				// 当前数据库中最大的编号
				String maxNO = result.get(0).substring(4, 5);
				stockRoom.setStockRoomNumber(partCentreLocation + "-" + String.valueOf(Integer.valueOf(maxNO) + 1));
			} else {
				stockRoom.setStockRoomNumber(partCentreLocation + "-1");
			}
		}

		stockRoom.setCreateBy(GwtActionHelper.getCurrUser());
		stockRoom.setCreateDate(new Date());
		session.saveOrUpdate(stockRoom);
		return stockRoom;
	}

	@Override
	public List<StockRoom> getStockRoom(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(StockRoom.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("stockRoomNumber"));
		SqlHelperTool.createCriteria(values, criteria, StockRoom.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}
