package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.CustomerCreditLineManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.customerCreditLine.CustomerCreditLine;

@Component
public class CustomerCreditLineDataSourceAction implements DataSourceAction<CustomerCreditLine> {

	private Logger log=LoggerFactory.getLogger(CustomerCreditLineDataSourceAction.class);
	
	@Autowired
	CustomerCreditLineManager customerCreditLineManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"carrierNameCode_dataSource"};
	}

	@Override
	public void insert(CustomerCreditLine item) {
		log.info("===============CustomerCreditLineDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("creditLine : " + item.getCreditLine());
		log.info("startDate : " + item.getStartDate());
		log.info("expiryDate : " + item.getExpiryDate());
		log.info("enterpriseId : " + item.getEnterpriseId());
		log.info("internationalCurrencyCode : " + item.getM_InternationalCurrencyCode());
		customerCreditLineManager.insertEntity(item);
		log.info("===============================================end");
		
	}

	@Override
	public CustomerCreditLine update(Map<String, Object> newValues, String itemID) {
		log.info("===============CustomerCreditLineDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		CustomerCreditLine ccl = customerCreditLineManager.updateEntity(newValues, itemID, CustomerCreditLine.class);
		log.info("===============================================end");
		return ccl;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============CustomerCreditLineDataSourceAction.delete()");
		log.info("deleted CustomerCreditLine ID : " + itemID);
		customerCreditLineManager.deleteEntity(itemID, CustomerCreditLine.class);
		log.info("===============================================end");
		
	}

	@Override
	public List<CustomerCreditLine> doQuery(Map<String, Object> values, int startRow, int endRow) {
		log.info("===============CustomerCreditLineDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		List<CustomerCreditLine> cclList = customerCreditLineManager.queryByProps(values);
		log.info("================================doQuery not execute");
		return cclList;
	}

	@Override
	public List<CustomerCreditLine> getList(int startRow, int endRow) {
		log.info("===============CustomerCreditLineDataSourceAction.getList()");
		List<CustomerCreditLine> cclList= customerCreditLineManager.list(startRow, endRow, CustomerCreditLine.class);
		log.info("===============================================end");
		return cclList;
	}

}
