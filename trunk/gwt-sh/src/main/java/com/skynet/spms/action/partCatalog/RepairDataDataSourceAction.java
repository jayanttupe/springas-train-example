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
import com.skynet.spms.manager.partCatalog.RepairDataManager;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.partCatalog.repairableCatalog.RepairData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.tools.OneToManyTools;

@Component
public class RepairDataDataSourceAction implements DataSourceAction<RepairData> {
	

	private Logger log=LoggerFactory.getLogger(RepairDataDataSourceAction.class);
	
	@Autowired
	RepairDataManager repairDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"repairData_dataSource"}; 
	}

	@Override
	public void insert(RepairData item) {
		log.info("===============RepairDataDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("PartIndexId : " + item.getPartIndexId());
		log.info("RemarkText : " + item.getRemarkText());
		log.info("RepairProcessRemarks : " + item.getRepairProcessRemarks());
		log.info("MeanShopProcessingTime : " + item.getMeanShopProcessingTime());
		log.info("UnitCode : " + item.getM_UnitCode());
		log.info("RepairShopCode : " + item.getM_RepairShopCode());
		log.info("RepairCodeId : " + item.getRepairCodeId());
		log.info("===============end()");
		
		repairDataManager.insertCascade(item);
		
	}

	@Override
	public RepairData update(Map<String, Object> newValues, String itemID) {
		log.info("===============RepairDataDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		RepairData rd = repairDataManager.updateCascade(newValues, itemID);
		return rd;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============RepairDataDataSourceAction.delete()");
		repairDataManager.deleteEntity(itemID, RepairData.class);
		
	}

	@Override
	public List<RepairData> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============RepairDataDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		Object filter = values.remove("filter");
		List<RepairData> list = null;
		if(filter == null){
			list = repairDataManager.queryByProps(values);
		}else{
			list = repairDataManager.queryByFilter(values);
		}	
	
		return list;
	}

	@Override
	public List<RepairData> getList(int startRow, int endRow) {
		log.info("===============RepairDataDataSourceAction.getList()");
		List<RepairData> list = repairDataManager.list(startRow, endRow, RepairData.class);
		return list;
	}
}
