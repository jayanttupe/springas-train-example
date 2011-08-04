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
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class BankInformationDataSourceAction implements DataSourceAction<BankInformation> {

	private Logger log=LoggerFactory.getLogger(BankInformationDataSourceAction.class);

	@Autowired
	BankInformationManager bankInformationManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"bankInformation_dataSource"};
	}

	@Override
	public void insert(BankInformation item) {
		log.info("***********BankInformationDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("bankName : " + item.getBankName());
		log.info("bankAccount : " + item.getBankAccount());
		log.info("bankAddress : " + item.getBankAddress());
		log.info("isDefault : " + item.isDefault());

		log.info(item.getEnterpriseId());
		bankInformationManager.insertEntity(item);
	}

	@Override
	public BankInformation update(Map<String, Object> newValues, String itemID) {
		log.info("***********BankInformationDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return bankInformationManager.updateEntity(newValues, itemID, BankInformation.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********BankInformationDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		bankInformationManager.deleteEntity(itemID, BankInformation.class);
		
	}

	@Override
	public List<BankInformation> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********BankInformationDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return bankInformationManager.queryByProps(values);
	}

	@Override
	public List<BankInformation> getList(int startRow, int endRow) {
		log.info("***********BankInformationDataSourceAction.getList()");
		List<BankInformation> caList = bankInformationManager.list(startRow, endRow, BankInformation.class);
		return caList;
	}

}
