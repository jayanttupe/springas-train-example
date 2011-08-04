package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.TimeCyclesDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.TimeCyclesData;

@Component
public class TimeCyclesDataSourceAction implements DataSourceAction<TimeCyclesData> {
	

	private Logger log=LoggerFactory.getLogger(TimeCyclesDataSourceAction.class);
	
	@Autowired
	TimeCyclesDataManager timeCyclesDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"timeCyclesData_dataSource"}; 
	}

	@Override
	public void insert(TimeCyclesData item) {
		log.info("===============TimeCyclesDataSourceAction.insert()");
		log.info("Id : " + item.getId());
		log.info("PartTechnicalDataId : " + item.getPartTechnicalDataId());
		log.info("Cycle : " + item.getCycle());
		log.info("UnitCode : " + item.getM_UnitCode());
		log.info("TimeCycleType : " + item.getM_TimeCycleType());
		log.info("TimeCycleCode : " + item.getM_TimeCycleCode());
		log.info("TimeCycleReferenceCode : " + item.getM_TimeCycleReferenceCode());
		log.info("Handling : " + item.getHandling());
		log.info("Description : " + item.getDescription());
		timeCyclesDataManager.insertEntity(item);
		log.info("===============end");
	}

	@Override
	public TimeCyclesData update(Map<String, Object> newValues, String itemID) {
		log.info("===============TimeCyclesDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		TimeCyclesData tcd = timeCyclesDataManager.updateEntity(newValues, itemID,TimeCyclesData.class);
		return tcd;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============TimeCyclesDataSourceAction.delete()");
		log.info("deleted Id : " + itemID);
		timeCyclesDataManager.deleteEntity(itemID, TimeCyclesData.class);
		
	}

	@Override
	public List<TimeCyclesData> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============TimeCyclesDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<TimeCyclesData> list = timeCyclesDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<TimeCyclesData> getList(int startRow, int endRow) {
		log.info("===============TimeCyclesDataSourceAction.getList()");
		List<TimeCyclesData> list = new ArrayList<TimeCyclesData>();
		return list;
	}
	
	
}
