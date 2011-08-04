package com.skynet.spms.action.finance;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.finance.ClassificationItemManager;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.Subjects;

@Component
public class SubjectDataSourceAction implements DataSourceAction<Subjects> {

	private Logger log=LoggerFactory.getLogger(SubjectDataSourceAction.class);

	@Autowired
	private ClassificationItemManager classificationItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"finance_subjects_dataSource"};
	}

	@Override
	public void insert(Subjects item) {
		log.info("***********SubjectDataSourceAction.insert()");
	}

	@Override
	public Subjects update(Map<String, Object> newValues, String itemID) {
		log.info("***********SubjectDataSourceAction.update()");
		return null;
	}

	@Override
	public void delete(String itemID) {
		log.info("***********SubjectDataSourceAction.delete()");
		
	}

	@Override
	public List<Subjects> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		log.info("***********SubjectDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return null;
	}

	@Override
	public List<Subjects> getList(int startRow, int endRow) {
		log.info("***********VATDataSourceAction.getList()");
		List<Subjects> SubjectsList = classificationItemManager.queryAllSubjects();
		return SubjectsList;
	}
}
