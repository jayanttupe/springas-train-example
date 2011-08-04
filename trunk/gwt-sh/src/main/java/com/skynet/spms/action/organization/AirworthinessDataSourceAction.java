package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.AirworthinessManager;
import com.skynet.spms.manager.organization.BankInformationManager;
import com.skynet.spms.manager.organization.ConsigneeAddressManager;
import com.skynet.spms.manager.organization.EnterpriseGTAManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.airworthinessInformation.AirworthinessInformationEntity;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class AirworthinessDataSourceAction implements DataSourceAction<AirworthinessInformationEntity> {

	private Logger log=LoggerFactory.getLogger(AirworthinessDataSourceAction.class);

	@Autowired
	AirworthinessManager airworthinessManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"airworthinessInformation_dataSource"};
	}

	@Override
	public void insert(AirworthinessInformationEntity item) {
		log.info("***********AirworthinessDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("airworthinessNumber : " + item.getAirworthinessNumber());
		log.info("airworthinessOrg : " + item.getM_AirworthinessOrg());
		log.info("signingDate : " + item.getSigningDate());
		log.info("expiryDate : " + item.getExpiryDate());

		log.info(item.getEnterpriseId());
		airworthinessManager.insertEntity(item);
	}

	@Override
	public AirworthinessInformationEntity update(Map<String, Object> newValues, String itemID) {
		log.info("***********AirworthinessDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return airworthinessManager.updateEntity(newValues, itemID, AirworthinessInformationEntity.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********AirworthinessDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		airworthinessManager.deleteEntity(itemID, AirworthinessInformationEntity.class);
		
	}

	@Override
	public List<AirworthinessInformationEntity> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********AirworthinessDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return airworthinessManager.queryByProps(values);
	}

	@Override
	public List<AirworthinessInformationEntity> getList(int startRow, int endRow) {
		log.info("***********AirworthinessDataSourceAction.getList()");
		List<AirworthinessInformationEntity> caList = airworthinessManager.list(startRow, endRow, AirworthinessInformationEntity.class);
		return caList;
	}

}
