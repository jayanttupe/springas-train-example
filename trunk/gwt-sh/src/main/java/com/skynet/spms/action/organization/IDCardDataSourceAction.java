package com.skynet.spms.action.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.IDCardManager;
import com.skynet.spms.persistence.entity.organization.userInformation.IDCard;

@Component
public class IDCardDataSourceAction implements DataSourceAction<IDCard> {

	private Logger log=LoggerFactory.getLogger(IDCardDataSourceAction.class);
	
	@Autowired
	private IDCardManager idCardManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"idCard_dataSource"};
	}

	@Override
	public void insert(IDCard item) {
		log.info("=============================IDCardDataSourceAction.insert()");
		
	}

	@Override
	public IDCard update(Map<String, Object> newValues, String itemID) {
		log.info("=============================IDCardDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		IDCard idCard = idCardManager.updateEntity(newValues, itemID, IDCard.class);
		return idCard;
	}

	@Override
	public void delete(String itemID) {
		log.info("=============================IDCardDataSourceAction.delete()");
		
	}

	@Override
	public List<IDCard> doQuery(Map<String, Object> map,
			int startRow, int endRow) {
		log.info("=============================IDCardDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return idCardManager.queryByProps(map);
	}

	@Override
	public List<IDCard> getList(int startRow, int endRow) {
		log.info("=============================IDCardDataSourceAction.getList()");
		List<IDCard> list = new ArrayList<IDCard>();
		return list;
	}
}
