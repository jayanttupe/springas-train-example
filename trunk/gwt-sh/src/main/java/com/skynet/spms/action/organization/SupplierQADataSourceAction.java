package com.skynet.spms.action.organization;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.SupplierQAManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.qualityAssurance.supplierQAManage.SupplierQAEntity;

@Component
public class SupplierQADataSourceAction implements DataSourceAction<SupplierQAEntity> {
	private Logger log=LoggerFactory.getLogger(SupplierQADataSourceAction.class);
	
	@Autowired
	SupplierQAManager supplierQAManager;
	@Override
	public String[] getBindDsName() {
		return new String[]{"supplierQA_dataSource"};
	}

	@Override
	public void insert(SupplierQAEntity item) {
		log.info("===============SupplierQADataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("startDate : " + item.getStartDate());
		log.info("expireDate : " + item.getExpiryDate());
		log.info("enterpriseId : " + item.getEnterpriseId());
		log.info("modifyDate : " + item.getModifyDate());
		supplierQAManager.insertEntity(item);
		log.info("===============================================end");
		
	}

	@Override
	public SupplierQAEntity update(Map<String, Object> newValues, String itemID) {
		log.info("===============SupplierQADataSourceAction.update()");
		newValues.put("modifyDate", new Date());
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		SupplierQAEntity sqa = supplierQAManager.updateEntity(newValues, itemID, SupplierQAEntity.class);
		log.info("===============================================end");
		return sqa;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============SupplierQADataSourceAction.delete()");
		log.info("deleted department ID : " + itemID);
		supplierQAManager.deleteEntity(itemID, SupplierQAEntity.class);
		log.info("===============================================end");
		
	}

	@Override
	public List<SupplierQAEntity> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("===============SupplierQADataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		List<SupplierQAEntity> sqaList = supplierQAManager.queryByProps(values);
		log.info("================================doQuery not execute");
		return sqaList;
	}

	@Override
	public List<SupplierQAEntity> getList(int startRow, int endRow) {
		log.info("===============SupplierQADataSourceAction.getList()");
		List<SupplierQAEntity> diList= supplierQAManager.list(startRow, endRow, SupplierQAEntity.class);
		log.info("===============================================end");
		return diList;
	}

}
