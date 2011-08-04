package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStock;

@Service
@Transactional
public class BondedWarehouseOutStockManagerImpl extends CommonManagerImpl<BondedWarehouseOutStock> implements BondedWarehouseOutStockManager{
	//获得未被删除的数据
    public List<BondedWarehouseOutStock> getBondedWarehousOutStocks(
			Map values, int startRow, int endRow){
    	Criteria criteria= getSession().createCriteria(BondedWarehouseOutStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseOutStock.class, null);
		return criteria.list();
    }
    
    
    //发送至海关监管
    public BondedWarehouseOutStock updateCustoms(Map map){
    	Criteria criteria= getSession().createCriteria(BondedWarehouseOutStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("bondedOutStockNumber", map.get("bondedOutStockNumber")));
		BondedWarehouseOutStock bStock =(BondedWarehouseOutStock)criteria.uniqueResult();
		bStock.setCustoms(true);
		bStock.setCreateBy(GwtActionHelper.getCurrUser());
		bStock.setCreateDate(new Date());
		getSession().saveOrUpdate(bStock);
		return bStock;
    }

    //获得海关监管的数据
	@Override
	public List<BondedWarehouseOutStock> getBondedWarehousOutStocksByIsCustoms(Map values, int startRow, int endRow) {
    	Criteria criteria= getSession().createCriteria(BondedWarehouseOutStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("customs", true));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseOutStock.class, null);
		return criteria.list();  	
    }
}