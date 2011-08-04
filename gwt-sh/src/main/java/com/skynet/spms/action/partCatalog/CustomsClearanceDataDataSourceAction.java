package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.CustomsClearanceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;

@Component
public class CustomsClearanceDataDataSourceAction implements DataSourceAction<CustomsClearanceData> {
	

	private Logger log=LoggerFactory.getLogger(CustomsClearanceDataDataSourceAction.class);
	
	@Autowired
	CustomsClearanceDataManager customsClearanceDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"customsClearance_dataSource"}; 
	}

	@Override
	public void insert(CustomsClearanceData item) {
		log.info("===============CustomsClearanceDataDataSourceAction.insert()");
		
		
	}

	@Override
	public CustomsClearanceData update(Map<String, Object> newValues, String itemID) {
		log.info("===============CustomsClearanceDataDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		CustomsClearanceData bi = customsClearanceDataManager.updateCascade(newValues, itemID);
		return bi;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============CustomsClearanceDataDataSourceAction.delete()");
		
		
	}

	@Override
	public List<CustomsClearanceData> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============CustomsClearanceDataDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<CustomsClearanceData> list = customsClearanceDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<CustomsClearanceData> getList(int startRow, int endRow) {
		log.info("===============CustomsClearanceDataDataSourceAction.getList()");
		List<CustomsClearanceData> list = new ArrayList<CustomsClearanceData>();
		return list;
	}
	
	
}
