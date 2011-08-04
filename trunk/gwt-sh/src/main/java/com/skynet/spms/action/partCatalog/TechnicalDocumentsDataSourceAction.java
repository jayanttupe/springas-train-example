package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.TechnicalDocumentsManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog.TechnicalPublishDoc;

@Component
public class TechnicalDocumentsDataSourceAction implements DataSourceAction<TechnicalPublishDoc>{
	
    private Logger log = LoggerFactory.getLogger(TechnicalDocumentsDataSourceAction.class); 
    
    @Autowired
    TechnicalDocumentsManager technicalDocumentsManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"technicalDocuments_dataSource"};
	}

	@Override
	public void insert(TechnicalPublishDoc item) {
		// TODO Auto-generated method stub
		log.info("===========TechnicalDocumentsDataSourceAction.insert()");
		log.info("Id : " + item.getId());
		log.info("location : " + item.getLocation());
		log.info("m_technicalPublishType : " + item.getM_TechnicalPublishType());
		technicalDocumentsManager.insertEntity(item);
		
	}

	@Override
	public TechnicalPublishDoc update(Map<String, Object> newValues,
			String itemID) {
		log.info("===========TechnicalDocumentsDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + ":" + entry.getValue());
		}
		TechnicalPublishDoc tpd = technicalDocumentsManager.updateEntity(newValues, itemID, TechnicalPublishDoc.class);
		return tpd;
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		log.info("===========TechnicalDocumentsDataSourceAction.delete()");
		technicalDocumentsManager.deleteEntity(itemID, TechnicalPublishDoc.class);
		
	}

	@Override
	public List<TechnicalPublishDoc> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		log.info("===========TechnicalDocumentsDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + ":" + entry.getValue());
		}
		List<TechnicalPublishDoc> list = technicalDocumentsManager.queryByProps(values);
		
		return list;
	}

	@Override
	public List<TechnicalPublishDoc> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		log.info("===========TechnicalDocumentsDataSourceAction.getList()");
		List<TechnicalPublishDoc> list = technicalDocumentsManager.list(startRow, endRow, TechnicalPublishDoc.class);
		return list;
	}
}
