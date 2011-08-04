package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.DepartmentManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;

@Component
public class DepartmentDataSourceAction implements DataSourceAction<DepartmentInformation> {

	private Logger log=LoggerFactory.getLogger(DepartmentDataSourceAction.class);
	
	@Autowired
	DepartmentManager departmentManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"department_dataSource"};
	}

	@Override
	public void insert(DepartmentInformation item) {
		log.info("===============DepartmentDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("department : " + item.getDepartment());
		log.info("parentId : " + item.getParentId());
		log.info("enterpriseId : " + item.getEnterpriseId());
		log.info("remark : " + item.getRemark());
		departmentManager.insertEntity(item);
		log.info("===============================================end");
		
	}

	@Override
	public DepartmentInformation update(Map<String, Object> newValues,
			String itemID) {
		log.info("===============DepartmentDataSourceAction.update()");
		String parentId = (String)newValues.get("parentId");
		if(parentId == null){
			newValues.put("parentId", "");
		}
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		log.info("itemID : " + itemID);
		DepartmentInformation di = departmentManager.updateEntity(newValues, itemID, DepartmentInformation.class);
		log.info("===============================================end");
		return di;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============DepartmentDataSourceAction.delete()");
		log.info("deleted department ID : " + itemID);
		departmentManager.deleteEntity(itemID, DepartmentInformation.class);
		log.info("===============================================end");
	}

	@Override
	public List<DepartmentInformation> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("===============DepartmentDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue());
		}
		String parentId = (String) values.get("parentId");
		List<DepartmentInformation> diList = departmentManager.queryTree(values);
		log.info("===============================================end");
		return diList;
	}

	@Override
	public List<DepartmentInformation> getList(int startRow, int endRow) {
		log.info("===============DepartmentDataSourceAction.doQuery()");
		List<DepartmentInformation> diList= departmentManager.list(startRow, endRow, DepartmentInformation.class);
		log.info("===============================================end");
		return diList;
	}

}
