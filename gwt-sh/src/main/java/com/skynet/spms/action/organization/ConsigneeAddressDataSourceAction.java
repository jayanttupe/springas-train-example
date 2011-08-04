package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.ConsigneeAddressManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class ConsigneeAddressDataSourceAction implements DataSourceAction<ConsigneeAddress> {

	private Logger log=LoggerFactory.getLogger(ConsigneeAddressDataSourceAction.class);

	@Autowired
	ConsigneeAddressManager consigneeAddressManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"consigneeAddress_dataSource"};
	}

	@Override
	public void insert(ConsigneeAddress item) {
		log.info("***********ConsigneeAddressDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("address : " + item.getAddress());
		log.info("postCode : " + item.getPostCode());
		log.info("isDefault : " + item.isDefault());
		log.info("recipient : " + item.getRecipient());
		
		log.info(item.getEnterpriseId());
		consigneeAddressManager.insertEntity(item);
		
	}

	@Override
	public ConsigneeAddress update(Map<String, Object> newValues, String itemID) {
		log.info("***********ConsigneeAddressDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return consigneeAddressManager.updateEntity(newValues, itemID, ConsigneeAddress.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********ConsigneeAddressDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		consigneeAddressManager.deleteEntity(itemID, ConsigneeAddress.class);
		
	}

	@Override
	public List<ConsigneeAddress> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********ConsigneeAddressDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return consigneeAddressManager.queryByProps(values);
	}

	@Override
	public List<ConsigneeAddress> getList(int startRow, int endRow) {
		log.info("***********ShippingAddressDataSourceAction.getList()");
		List<ConsigneeAddress> caList = consigneeAddressManager.list(startRow, endRow, ConsigneeAddress.class);
		return caList;
	}

}
