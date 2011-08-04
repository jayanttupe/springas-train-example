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
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStock;

@Service
@Transactional
public class BondedWarehouseInStockManagerImpl extends CommonManagerImpl<BondedWarehouseInStock> implements BondedWarehouseInStockManager{

	//获得未被删除的数据
    public List<BondedWarehouseInStock> getBondedWarehouseInStocks(Map values, int startRow, int endRow){
    	Criteria criteria= getSession().createCriteria(BondedWarehouseInStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseInStock.class, null);
		return criteria.list();
    }

    //获取库存记录
    public List<BondedWarehouseInStock> getInStocks(Map values, int startRow, int endRow){
    	Criteria criteria= getSession().createCriteria(BondedWarehouseInStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("state", "入库"));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseInStock.class, null);
		return criteria.list();  	
    }
    
    //发送至海关监管
    public BondedWarehouseInStock updateCustoms(Map map){
    	Criteria criteria= getSession().createCriteria(BondedWarehouseInStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("bondedInStockNumber", map.get("bondedInStockNumber")));
		BondedWarehouseInStock bStock =(BondedWarehouseInStock)criteria.uniqueResult();
		bStock.setCustoms(true);
		bStock.setCreateBy(GwtActionHelper.getCurrUser());
		bStock.setCreateDate(new Date());
		getSession().saveOrUpdate(bStock);
		return bStock;
    }
    
    //获取为海关监管的库存记录
	@Override
	public List<BondedWarehouseInStock> getInStocksByIsCustoms(Map values, int startRow, int endRow) {
    	Criteria criteria= getSession().createCriteria(BondedWarehouseInStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("state", "入库"));
		criteria.add(Restrictions.eq("customs", true));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseInStock.class, null);
		return criteria.list();  	
    }
    //获取为海关监管未被删除的数据
	@Override
	public List<BondedWarehouseInStock> getBondedWarehouseInStocksByIsCustoms(Map values, int startRow, int endRow) {
    	Criteria criteria= getSession().createCriteria(BondedWarehouseInStock.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("customs", true));
		SqlHelperTool.createCriteria(values, criteria, BondedWarehouseInStock.class, null);
		return criteria.list();  	
    }
}