package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.ConsigneeAddressManager;
import com.skynet.spms.manager.organization.RecipeInvoiceAddressManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.RecipeInvoiceAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class RecipeInvoiceAddressDataSourceAction implements DataSourceAction<RecipeInvoiceAddress> {

	private Logger log=LoggerFactory.getLogger(RecipeInvoiceAddressDataSourceAction.class);

	@Autowired
	RecipeInvoiceAddressManager recipeInvoiceAddressManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"recipeInvoiceAddress_dataSource"};
	}

	@Override
	public void insert(RecipeInvoiceAddress item) {
		log.info("***********RecipeInvoiceAddressDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("itemNumber : " + item.getItemNumber());
		log.info("address : " + item.getAddress());
		log.info("postCode : " + item.getPostCode());
		log.info("isDefault : " + item.isDefault());
		log.info("recipient : " + item.getRecipient());
		
		log.info(item.getEnterpriseId());
		recipeInvoiceAddressManager.insertEntity(item);
		
	}

	@Override
	public RecipeInvoiceAddress update(Map<String, Object> newValues, String itemID) {
		log.info("***********RecipeInvoiceAddressDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return recipeInvoiceAddressManager.updateEntity(newValues, itemID, RecipeInvoiceAddress.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********RecipeInvoiceAddressDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		recipeInvoiceAddressManager.deleteEntity(itemID, RecipeInvoiceAddress.class);
		
	}

	@Override
	public List<RecipeInvoiceAddress> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********RecipeInvoiceAddressDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return recipeInvoiceAddressManager.queryByProps(values);
	}

	@Override
	public List<RecipeInvoiceAddress> getList(int startRow, int endRow) {
		log.info("***********RecipeInvoiceAddressDataSourceAction.getList()");
		List<RecipeInvoiceAddress> caList = recipeInvoiceAddressManager.list(startRow, endRow, RecipeInvoiceAddress.class);
		return caList;
	}

}
