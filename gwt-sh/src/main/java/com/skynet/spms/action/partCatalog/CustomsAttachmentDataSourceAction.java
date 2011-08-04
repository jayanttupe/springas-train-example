package com.skynet.spms.action.partCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.partCatalog.CustomsAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

@Component
public class CustomsAttachmentDataSourceAction implements DataSourceAction<Attachment> {
	

	private Logger log=LoggerFactory.getLogger(CustomsAttachmentDataSourceAction.class);
	
	@Autowired
	CustomsAttachmentManager customsAttachmentManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"customsAttachment_dataSource"}; 
	}

	@Override
	public void insert(Attachment item) {
		log.info("===============CustomsAttachmentDataSourceAction.insert()");
		
		
	}

	@Override
	public Attachment update(Map<String, Object> newValues, String itemID) {
		log.info("===============CustomsAttachmentDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		Attachment attachment = customsAttachmentManager.updateCascade(newValues, itemID);
		return attachment;
	}

	@Override
	public void delete(String itemID) {
		log.info("===============CustomsAttachmentDataSourceAction.delete()");
		
		
	}

	@Override
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		log.info("===============CustomsAttachmentDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<Attachment> list = customsAttachmentManager.queryByProps(values);
	
		return list;
	}

	@Override
	public List<Attachment> getList(int startRow, int endRow) {
		log.info("===============CustomsAttachmentDataSourceAction.getList()");
		List<Attachment> list = new ArrayList<Attachment>();
		return list;
	}

}
