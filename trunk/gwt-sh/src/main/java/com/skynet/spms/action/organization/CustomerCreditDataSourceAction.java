package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.CustomerCreditManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.credit.customerCredit.CustomerCredit;

@Component
public class CustomerCreditDataSourceAction implements DataSourceAction<CustomerCredit> {

	private Logger log=LoggerFactory.getLogger(CustomerCreditDataSourceAction.class);
	
	@Autowired
	CustomerCreditManager customerCreditManager;
	@Override
	public String[] getBindDsName() {
		return new String[]{"customerCredit_dataSource"};
	}

	@Override
	public void insert(CustomerCredit item) {
		log.info("===============CustomerCreditDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("credit : " + item.getCredit());
		log.info("startDate : " + item.getStartDate());
		log.info("expiryDate : " + item.getExpiryDate());
		log.info("enterpriseId : " + item.getEnterpriseId());
		log.info("internationalCurrencyCode : " + item.getM_InternationalCurrencyCode());
		customerCreditManager.insertEntity(item);
		log.info("===============================================end");
		
	}

	@Override
	public CustomerCredit update(Map<String, Object> newValues, String itemID) {
		log.info("===============CustomerCreditDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		CustomerCredit cc = customerCreditManager.updateEntity(newValues, itemID, CustomerCredit.class);
		log.info("===============================================end");
		return cc;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============CustomerCreditDataSourceAction.delete()");
		log.info("deleted CustomerCredit ID : " + itemID);
		customerCreditManager.deleteEntity(itemID, CustomerCredit.class);
		log.info("===============================================end");
		
	}

	@Override
	public List<CustomerCredit> doQuery(Map<String, Object> values, int startRow, int endRow) {
		log.info("===============CustomerCreditDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		List<CustomerCredit> ccList = customerCreditManager.queryByProps(values);
		log.info("===============================================end");
		return ccList;
	}

	@Override
	public List<CustomerCredit> getList(int startRow, int endRow) {
		log.info("===============CustomerCreditDataSourceAction.getList()");
		List<CustomerCredit> ccList= customerCreditManager.list(startRow, endRow, CustomerCredit.class);
		log.info("===============================================end");
		return ccList;
	}

}
