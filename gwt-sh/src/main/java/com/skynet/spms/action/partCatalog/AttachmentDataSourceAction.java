/*package com.skynet.spms.action.partCatalog;

import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.log.Log;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.persistence.entity.base.Attachment;

@Component
public class AttachmentDataSourceAction implements DataSourceAction<Attachment>{

	AttachmentManager attachmentManager;
	@Override
	public String[] getBindDsName() {s
		// TODO Auto-generated method stub
		return new String[]{"attacment_dataSource"};
	}

	@Override
	public void insert(Attachment item) {
		// TODO Auto-generated method stub
		Log.info("=============AttachmentDataSourceAction.insert()");
		
	}

	@Override
	public Attachment update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		Log.info("=============AttachmentDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			Log.info(entry.getKey() + ":" + entry.getValue());
		}
		    Attachment atch = attachmentManager.updateEntity(newValues, itemID, Attachment.class);
		    
		         return atch ;
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		Log.info("=============AttachmentDataSourceAction.delete()");
		attachmentManager.deleteEntity(itemID, Attachment.class);
		
	}

	@Override
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		// TODO Auto-generated method stub
		Log.info("=============AttachmentDataSourceAction.doQuery()");
        for(Map.Entry<String, Object> entry : values.entrySet()){
        	Log.info(entry.getKey() + ":" + entry.getValue());
        }
            List<Attachment> list = attachmentManager.queryByProps(values);
		    
            return list;
	}

	@Override
	public List<Attachment> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		Log.info("=============AttachmentDataSourceAction.getList()");
		List<Attachment> list = attachmentManager.list(startRow, endRow, Attachment.class);
		return list;
	}
	
	

}
*/