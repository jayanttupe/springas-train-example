package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.OptionalDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData.OptionalPart;

@Component
public class OptionalDataSourceAction implements DataSourceAction<OptionalPart> {
	

	private Logger log=LoggerFactory.getLogger(OptionalDataSourceAction.class);
	
	@Autowired
	OptionalDataManager optionalDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"optionalPart_dataSource"}; 
	}

	@Override
	public void insert(OptionalPart item) {
		log.info("==============OptionalDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("PartIndexId : " + item.getPartIndexId());
		log.info("OptionalPartNumber : " + item.getOptionalPartNumber());
		log.info("optionalPartNumberText" + item.getOptionalPartNumberText());
		log.info("m_InterchangeabilityCode : " + item.getM_InterchangeabilityCode());
	
		optionalDataManager.insertEntity(item);
	}

	@Override
	public OptionalPart update(Map<String, Object> newValues, String itemID) {
		log.info("===============OptionalDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		OptionalPart pad = optionalDataManager.updateEntity(newValues, itemID, OptionalPart.class);
		
		return pad;
	}
	@Override
	public void delete(String itemID) {
		log.info("===============OptionalDataSourceAction.delete()");
		log.info("PartApplicationDataId ：" + itemID);
		optionalDataManager.deleteEntity(itemID, OptionalPart.class);
	}

	@Override
	public List<OptionalPart> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============OptionalDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<OptionalPart> list = optionalDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<OptionalPart> getList(int startRow, int endRow) {
		log.info("===============OptionalDataSourceAction.getList()");
		List<OptionalPart> list = optionalDataManager.list(startRow, endRow, OptionalPart.class);
		
		return list;
	}

	
	
	
}
