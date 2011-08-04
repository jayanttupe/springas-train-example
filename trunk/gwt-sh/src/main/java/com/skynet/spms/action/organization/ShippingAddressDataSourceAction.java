package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class ShippingAddressDataSourceAction implements DataSourceAction<ShippingAddress> {

	private Logger log=LoggerFactory.getLogger(ShippingAddressDataSourceAction.class);

	@Autowired
	ShippingAddressManager shippingAddressManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"shippingAddress_dataSource"};
	}

	@Override
	public void insert(ShippingAddress item) {
		log.info("***********ShippingAddressDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("address : " + item.getAddress());
		log.info("postCode : " + item.getPostCode());
		log.info("isDefault : " + item.isDefault());
		log.info("consignor : " + item.getConsignor());
		
		log.info(item.getEnterpriseId());
		shippingAddressManager.insertEntity(item);
		
	}

	@Override
	public ShippingAddress update(Map<String, Object> newValues, String itemID) {
		log.info("***********ShippingAddressDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return shippingAddressManager.updateEntity(newValues, itemID, ShippingAddress.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********ShippingAddressDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		shippingAddressManager.deleteEntity(itemID, ShippingAddress.class);
		
	}

	@Override
	public List<ShippingAddress> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********ShippingAddressDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return shippingAddressManager.queryByProps(values);
	}

	@Override
	public List<ShippingAddress> getList(int startRow, int endRow) {
		log.info("***********ShippingAddressDataSourceAction.getList()");
		List<ShippingAddress> saList = shippingAddressManager.list(startRow, endRow, ShippingAddress.class);
		return saList;
	}

}
