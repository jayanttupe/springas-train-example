package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.OtherChargeDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.OtherCharge;

@Component
public class OtherChargeDataSourceAction implements DataSourceAction<OtherCharge> {
	

	private Logger log=LoggerFactory.getLogger(OtherChargeDataSourceAction.class);
	
	@Autowired
	OtherChargeDataManager otherChargeDataManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"otherCharge_dataSource"}; 
	}

	@Override
	public void insert(OtherCharge item) {
		log.info("===============OtherChargeDataSourceAction.insert()");
		log.info("id : " + item.getId());
		//log.info("id : " + item.getPartSaleReleaseId());
		log.info("InternationalCurrencyCode : " + item.getM_InternationalCurrencyCode());
		log.info("OtherChargeAmount : " + item.getOtherChargeAmount());
		log.info("OtherChargeCode" + item.getM_OtherChargeCode());
		log.info("RemarkText : " + item.getRemarkText());
		
		otherChargeDataManager.insertEntity(item);
	}

	@Override
	public OtherCharge update(Map<String, Object> newValues, String itemID) {
		log.info("===============OtherChargeDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		OtherCharge pad = otherChargeDataManager.updateEntity(newValues, itemID, OtherCharge.class);
		
		return pad;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============OtherChargeDataSourceAction.delete()");
		log.info("OtherChargeId ：" + itemID);
		otherChargeDataManager.deleteEntity(itemID, OtherCharge.class);
	}

	@Override
	public List<OtherCharge> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============OtherChargeDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<OtherCharge> list = otherChargeDataManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<OtherCharge> getList(int startRow, int endRow) {
		log.info("===============OtherChargeDataSourceAction.getList()");
		List<OtherCharge> list = otherChargeDataManager.list(startRow, endRow, OtherCharge.class);
		
		return list;
	}
	
	
}
