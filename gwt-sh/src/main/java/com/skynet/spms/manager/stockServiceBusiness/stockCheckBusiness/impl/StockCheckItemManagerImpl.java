package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckItemManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

@Service
@Transactional
public class StockCheckItemManagerImpl extends CommonManagerImpl<StockCheckItem> implements StockCheckItemManager{

	@Override
	public List<StockCheckItem> getStockData(Map values, int startRow, int endRow){
		String strQuery = "";
		strQuery = strQuery + "select ";
		// 货位编号
		strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 序号/批号
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		// 件名称
		strQuery = strQuery + "c.KEYWORD_ZH, ";
		// 数量
		strQuery = strQuery + "a.BALANCE_QUANTITY, ";
		// 单位
		strQuery = strQuery + "c.UNIT_MEASURE_CODE ";
		strQuery = strQuery + "from SPMS_PARTS_INVENTORY_RECORD a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		if (values != null && values.containsKey("fromCargoSpaceNumber") && values.get("toCargoSpaceNumber") != null) {
			strWhere = strWhere + " and a.CARGO_SPACE_NUMBER >= '" + values.get("fromCargoSpaceNumber").toString() + "'";
			strWhere = strWhere + " and a.CARGO_SPACE_NUMBER <= '" + values.get("toCargoSpaceNumber").toString() + "'";
		}
		// 字段排序
		String strOrder = "order by a.CARGO_SPACE_NUMBER asc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<StockCheckItem> stockCheckItemList = new ArrayList<StockCheckItem>();
		for (Object[] objects : result)
		{
			StockCheckItem stockCheckItem = new StockCheckItem();
			// 货位编号
			if (objects[0] != null)
				stockCheckItem.setCargoSpaceNumber(objects[0].toString());
			// 件号
			if (objects[1] != null)
				stockCheckItem.setPartNumber(objects[1].toString());
			// 序号/批号
			if (objects[2] != null)
				stockCheckItem.setPartSerialNumber(objects[2].toString());
			// 件名称
			if (objects[3] != null)
				stockCheckItem.setPartName(objects[3].toString());
			// 数量
			if (objects[4] != null)
				stockCheckItem.setQuantity(Integer.valueOf(objects[4].toString()));
			// 单位
			if (objects[5] != null)
				stockCheckItem.setPartUnit(UnitOfMeasureCode.valueOf(objects[5].toString()));
			
			stockCheckItemList.add(stockCheckItem);
		}
		
		return stockCheckItemList;
	}
	
	@Override
	public List<StockCheckItem> getStockCheckItem(Map values, int startRow, int endRow){
		Criteria criteria= getSession().createCriteria(StockCheckItem.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("cargoSpaceNumber"));
		SqlHelperTool.createCriteria(values, criteria, StockCheckItem.class, null);        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}