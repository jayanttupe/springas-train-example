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
import com.skynet.spms.manager.organization.DutyManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BankInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.Duty;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Component
public class DutyDataSourceAction implements DataSourceAction<Duty> {

	private Logger log=LoggerFactory.getLogger(DutyDataSourceAction.class);

	@Autowired
	DutyManager dutyManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"duty_dataSource"};
	}

	@Override
	public void insert(Duty item) {
		log.info("***********DutyDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("DutyName : " + item.getDutyName());
		log.info("DutyLevel : " + item.getDutyLevel());
		log.info("DutyDescription : " + item.getDutyDescription());

		log.info("departmentId : " + item.getDepartmentId());
		dutyManager.insertEntity(item);
	}

	@Override
	public Duty update(Map<String, Object> newValues, String itemID) {
		log.info("***********DutyDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return dutyManager.updateEntity(newValues, itemID, Duty.class);
	}

	@Override
	public void delete(String itemID) {
		log.info("***********DutyDataSourceAction.delete()");
		log.info(itemID);
		log.info("************************************************end");
		dutyManager.deleteEntity(itemID, Duty.class);
		
	}

	@Override
	public List<Duty> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********DutyDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return dutyManager.queryByProps(values);
	}

	@Override
	public List<Duty> getList(int startRow, int endRow) {
		log.info("***********DutyDataSourceAction.getList()");
		List<Duty> caList = dutyManager.list(startRow, endRow, Duty.class);
		return caList;
	}
}
