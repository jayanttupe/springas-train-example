package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.BasicInfoManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;

@Component
public class BasicInfoDataSourceAction implements DataSourceAction<BasicInformation> {
	

	private Logger log=LoggerFactory.getLogger(BasicInfoDataSourceAction.class);
	
	@Autowired
	BasicInfoManager basicInfoManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"basicInfo_dataSource"}; 
	}

	@Override
	public void insert(BasicInformation item) {
		log.info("===============BasicInfoDataSourceAction.insert()");
		
		
	}

	@Override
	public BasicInformation update(Map<String, Object> newValues, String itemID) {
		log.info("===============BasicInfoDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		BasicInformation bi = basicInfoManager.updateCascade(newValues, itemID);
		return bi;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============BasicInfoDataSourceAction.delete()");
		
		
	}

	@Override
	public List<BasicInformation> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============BasicInfoDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<BasicInformation> list = basicInfoManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<BasicInformation> getList(int startRow, int endRow) {
		log.info("===============BasicInfoDataSourceAction.getList()");
		List<BasicInformation> list = new ArrayList<BasicInformation>();
		return list;
	}
	
	
}
