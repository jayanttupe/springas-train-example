package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.SalesHistoryInfoDataManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.salseHistoryInfo;

@Component
public class SalesHistoryDataSourceAction implements DataSourceAction<salseHistoryInfo> {
	

	private Logger log=LoggerFactory.getLogger(SalesHistoryDataSourceAction.class);
	
	@Autowired
	SalesHistoryInfoDataManager salseHistoryInfoDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"salesHistory_dataSource"}; 
	}

	@Override
	public void insert(salseHistoryInfo item) {
		log.info("===============SalesHistoryDataSourceAction.insert()");
		log.info("id : " + item.getId());
		//log.info("PartIndexId : " + item.getPartSaleReleaseId());
		log.info("RecommendedQuantity : " + item);
		log.info("isQuickEngineChange" + item.getSalesOrderNumber());
		log.info("ETOPSFlightIndicator : " + item.getM_CustomerIdentificationCode());
		log.info("EngineLevelOfMaintenanceCode" + item.getUnitPriceAmount());
		log.info("ExportControlClassificationCode" + item.getManufacturersFiles());
		log.info("MaintenanceOverhaulRepairCode" + item.getTotalAmount());
		log.info("MiscellaneousText : " + item.getPreviousPurchaseAveragePrice());
		log.info("MiscellaneousText : " + item.getDeliveryDate());
		log.info("MiscellaneousText : " + item.getM_InternationalCurrencyCode());
		salseHistoryInfoDataManager.insertEntity(item);
	}

	@Override
	public salseHistoryInfo update(Map<String, Object> newValues, String itemID) {
		log.info("===============SalesHistoryDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		salseHistoryInfo pad = salseHistoryInfoDataManager.updateEntity(newValues, itemID, salseHistoryInfo.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============SalesHistoryDataSourceAction.delete()");
		log.info("PartApplicationDataId ：" + itemID);
		salseHistoryInfoDataManager.deleteEntity(itemID, salseHistoryInfo.class);
	}

	@Override
	public List<salseHistoryInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============SalesHistoryDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<salseHistoryInfo> list = salseHistoryInfoDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<salseHistoryInfo> getList(int startRow, int endRow) {
		log.info("===============SalesHistoryDataSourceAction.getList()");
		List<salseHistoryInfo> list = salseHistoryInfoDataManager.list(startRow, endRow, salseHistoryInfo.class);
		
		return list;
	}
	
	
}
