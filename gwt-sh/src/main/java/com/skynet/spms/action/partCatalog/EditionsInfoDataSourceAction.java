package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.EditionsInfoDataManager;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;

@Component
public class EditionsInfoDataSourceAction implements DataSourceAction<EditionsInformation> {
	

	private Logger log=LoggerFactory.getLogger(EditionsInfoDataSourceAction.class);
	
	@Autowired
	EditionsInfoDataManager editionsInfoDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"EditionsInfo_dataSource"}; 
	}

	@Override
	public void insert(EditionsInformation item) {
		log.info("===============EditionsInfoDataSourceAction.insert()");
		log.info("id : " + item.getId());
		//log.info("PartIndexId : " + item.getPartSaleReleaseId());
		log.info("editionsReviseDate : " + item.getReleaseVersionDate());
		log.info("editionsReviseMan" + item.getEditionsReviseMan());
		log.info("ETOPSFlightIndicator : " + item.getEditionsReviseDate());
		log.info("releaseMan" + item.getReleaseMan());
		
		editionsInfoDataManager.insertEntity(item);
	}

	@Override
	public EditionsInformation update(Map<String, Object> newValues, String itemID) {
		log.info("===============EditionsInfoDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		EditionsInformation pad = editionsInfoDataManager.updateEntity(newValues, itemID, EditionsInformation.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============EditionsInfoDataSourceAction.delete()");
		log.info("PartApplicationDataId ：" + itemID);
		editionsInfoDataManager.deleteEntity(itemID, EditionsInformation.class);
	}

	@Override
	public List<EditionsInformation> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============EditionsInfoDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<EditionsInformation> list = editionsInfoDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<EditionsInformation> getList(int startRow, int endRow) {
		log.info("===============EditionsInfoDataSourceAction.getList()");
		List<EditionsInformation> list = editionsInfoDataManager.list(startRow, endRow, EditionsInformation.class);
		
		return list;
	}
	
	
}
