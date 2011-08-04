package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.AlternateSupplyLocationManager;
import com.skynet.spms.manager.partCatalog.OtherChargeDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.OtherCharge;

@Component
public class AlternateSupplyLocationDataSourceAction implements DataSourceAction<AlternateSupplyLocationText> {
	

	private Logger log=LoggerFactory.getLogger(AlternateSupplyLocationDataSourceAction.class);
	
	@Autowired
	AlternateSupplyLocationManager alternateSupplyLocationManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"alternateSupplyLoc_dataSource"}; 
	}

	@Override
	public void insert(AlternateSupplyLocationText item) {
		log.info("===============AlternateSupplyLocationDataSourceAction.insert()");
		log.info("Id : " + item.getId());
		log.info("PriceId : " + item.getPriceId());
		log.info("Location : " + item.getLocation());
		alternateSupplyLocationManager.insertEntity(item);
	}

	@Override
	public AlternateSupplyLocationText update(Map<String, Object> newValues, String itemID) {
		log.info("===============AlternateSupplyLocationDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return alternateSupplyLocationManager.updateEntity(newValues, itemID, AlternateSupplyLocationText.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("===============AlternateSupplyLocationDataSourceAction.delete()");
		log.info("AlternateSupplyLocationId ：" + itemID);
		alternateSupplyLocationManager.deleteEntity(itemID, AlternateSupplyLocationText.class);
	}

	@Override
	public List<AlternateSupplyLocationText> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============AlternateSupplyLocationDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<AlternateSupplyLocationText> list = alternateSupplyLocationManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<AlternateSupplyLocationText> getList(int startRow, int endRow) {
		log.info("===============AlternateSupplyLocationDataSourceAction.getList()");
		List<AlternateSupplyLocationText> list = new ArrayList<AlternateSupplyLocationText>();	
		return list;
	}

}
