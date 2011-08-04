package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.BankInformationManager;
import com.skynet.spms.manager.organization.ConsigneeAddressManager;
import com.skynet.spms.manager.organization.EnterpriseGTAManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.GTAManage.EnterpriseGTA;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class EnterpriseGTADataSourceAction implements DataSourceAction<EnterpriseGTA> {

	private Logger log=LoggerFactory.getLogger(EnterpriseGTADataSourceAction.class);

	@Autowired
	EnterpriseGTAManager enterpriseGTAManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"enterpriseGTA_dataSource"};
	}

	@Override
	public void insert(EnterpriseGTA item) {
		log.info("***********EnterpriseGTADataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("gtaNumber : " + item.getGtaNumber());
		log.info("Title : " + item.getTitle());
		log.info("GTAType : " + item.getM_GTAType());
		log.info("SigningDate : " + item.getSigningDate());
		log.info("ExpiryDate : " + item.getExpiryDate());
		log.info("Description : " + item.getDescription());

		log.info(item.getEnterpriseId());
		enterpriseGTAManager.insertEntity(item);
	}

	@Override
	public EnterpriseGTA update(Map<String, Object> newValues, String itemID) {
		log.info("***********EnterpriseGTADataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return enterpriseGTAManager.updateEntity(newValues, itemID, EnterpriseGTA.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********EnterpriseGTADataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		enterpriseGTAManager.deleteEntity(itemID, EnterpriseGTA.class);
		
	}

	@Override
	public List<EnterpriseGTA> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********EnterpriseGTADataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return enterpriseGTAManager.queryByProps(values);
	}

	@Override
	public List<EnterpriseGTA> getList(int startRow, int endRow) {
		log.info("***********EnterpriseGTADataSourceAction.getList()");
		List<EnterpriseGTA> caList = enterpriseGTAManager.list(startRow, endRow, EnterpriseGTA.class);
		return caList;
	}

}
