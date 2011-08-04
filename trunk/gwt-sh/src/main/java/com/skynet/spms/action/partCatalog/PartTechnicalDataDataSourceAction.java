package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.TechnicalDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;

@Component
public class PartTechnicalDataDataSourceAction implements DataSourceAction<PartTechnicalData> {
	

	private Logger log=LoggerFactory.getLogger(PartTechnicalDataDataSourceAction.class);
	
	@Autowired
	TechnicalDataManager technicalDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"technicalData_dataSource"}; 
	}

	@Override
	public void insert(PartTechnicalData item) {
		log.info("===============PartTechnicalDataDataSourceAction.insert()");
		
		
	}

	@Override
	public PartTechnicalData update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartTechnicalDataDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PartTechnicalData ptd = technicalDataManager.updateEntity(newValues, itemID,PartTechnicalData.class);
		return ptd;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PartTechnicalDataDataSourceAction.delete()");
		
		
	}

	@Override
	public List<PartTechnicalData> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartTechnicalDataDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<PartTechnicalData> list = technicalDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<PartTechnicalData> getList(int startRow, int endRow) {
		log.info("===============BasicInfoDataSourceAction.getList()");
		List<PartTechnicalData> list = new ArrayList<PartTechnicalData>();
		return list;
	}
	
	
}
