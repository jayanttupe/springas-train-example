package com.skynet.spms.action.finance;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.ClassificationItemManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.ClassificationItem;

public class ClassificationItemAction implements DataSourceAction<ClassificationItem>{

	
	private Logger log=LoggerFactory.getLogger(ClassificationItemAction.class);
	
	@Autowired
	private ClassificationItemManager classificationItemManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"finance_classificationItem_dataSource"};
	}

	@Override
	public void insert(ClassificationItem item) {
		// TODO Auto-generated method stub
		classificationItemManager.insertEntity(item);
	}

	@Override
	public ClassificationItem update(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return classificationItemManager.updateEntity(newValues, itemID, ClassificationItem.class);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		classificationItemManager.deleteEntity(itemID, ClassificationItem.class);
	}

	@Override
	public List<ClassificationItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		ClassificationItem item = new ClassificationItem();
		item.setClassificationItemId((String)values.get("classificationItemId"));
		return classificationItemManager.queryByBean(item, startRow, endRow);
	}

	@Override
	public List<ClassificationItem> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return classificationItemManager.list(startRow, endRow, ClassificationItem.class);
	}

}
