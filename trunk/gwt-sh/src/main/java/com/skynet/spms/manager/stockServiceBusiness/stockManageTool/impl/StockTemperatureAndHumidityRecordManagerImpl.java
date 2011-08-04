package com.skynet.spms.manager.stockServiceBusiness.stockManageTool.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.StockTemperatureAndHumidityRecordHumidityManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity.StockTemperatureAndHumidityRecord;

@Service
@Transactional
public class StockTemperatureAndHumidityRecordManagerImpl extends CommonManagerImpl<StockTemperatureAndHumidityRecord> implements StockTemperatureAndHumidityRecordHumidityManager{

	@Override
	public List<StockTemperatureAndHumidityRecord> getStockTemperatureAndHumidityRecord(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(StockTemperatureAndHumidityRecord.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, StockTemperatureAndHumidityRecord.class, null);
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

}