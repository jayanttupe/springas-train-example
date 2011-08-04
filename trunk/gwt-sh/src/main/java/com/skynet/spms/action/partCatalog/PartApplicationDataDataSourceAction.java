package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.PartApplicationDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
	
@Component
public class PartApplicationDataDataSourceAction implements DataSourceAction<PartApplicationData> {
	

	private Logger log=LoggerFactory.getLogger(PartApplicationDataDataSourceAction.class);
	
	@Autowired
	PartApplicationDataManager partApplicationDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"applicationData_dataSource"}; 
	}

	@Override
	public void insert(PartApplicationData item) {
		log.info("===============PartApplicationDataDataSourceAction.insert()");
		}
	
	@Override
	public PartApplicationData update(Map<String, Object> newValues, String itemID) {
		log.info("===============PartApplicationDataDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		PartApplicationData pad = partApplicationDataManager.updateCascade(newValues, itemID);
		//PartApplicationData pad = partApplicationDataManager.updateEntity(newValues, itemID, PartApplicationData.class);
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============PartApplicationDataDataSourceAction.delete()");
		}

	@Override
	public List<PartApplicationData> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============PartApplicationDataDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<PartApplicationData> list = partApplicationDataManager.queryByProps(values);
	
		return list;
	}
	
	@Override
	public List<PartApplicationData> getList(int startRow, int endRow) {
		log.info("===============PartApplicationDataDataSourceAction.getList()");
		//List<PartApplicationData> list = partApplicationDataManager.list(startRow, endRow, PartApplicationData.class);
		List<PartApplicationData> list = new ArrayList<PartApplicationData>();
		return list;
	}
		
}

