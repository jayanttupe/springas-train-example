package com.skynet.spms.action.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.VATManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.VAT;

@Component
public class VATDataSourceAction implements DataSourceAction<VAT> {

	private Logger log=LoggerFactory.getLogger(VATDataSourceAction.class);

	@Autowired
	VATManager vatManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"vat_dataSource"};
	}

	@Override
	public void insert(VAT item) {
		log.info("***********VATDataSourceAction.insert()");
	}

	@Override
	public VAT update(Map<String, Object> newValues, String itemID) {
		log.info("***********VATDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		VAT vat = vatManager.updateEntity(newValues, itemID, VAT.class);

		return vat;
	}

	@Override
	public void delete(String itemID) {
		log.info("***********VATDataSourceAction.delete()");
		
	}

	@Override
	public List<VAT> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********VATDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<VAT> vfList = vatManager.queryByProps(values);
		return vfList;
	}

	@Override
	public List<VAT> getList(int startRow, int endRow) {
		log.info("***********VATDataSourceAction.getList()");
		List<VAT> vatList = new ArrayList<VAT>();
		return vatList;
	}
}
