package com.skynet.spms.action.baseoper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.attachment.IAttachmentManager;
import com.skynet.spms.manager.baseoper.AttachmentsManager;
import com.skynet.spms.manager.finance.PayApplyManager;
import com.skynet.spms.persistence.entity.base.Attachment;

@Component
public class AttachmentAction implements DataSourceAction<Attachment> {

	private Logger log=LoggerFactory.getLogger(AttachmentAction.class);
	
	@Autowired
	private IAttachmentManager attachmentsManager;
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"attachments_dataSource"};
	}

	@Override
	public void insert(Attachment item) {
		// TODO Auto-generated method stub
		attachmentsManager.addAttachment(item);
	}

	@Override
	public Attachment update(Map<String, Object> newValues, String itemID) {
		// TODO Auto-generated method stub
		
		return attachmentsManager.updateAttachment(newValues,itemID);
	}

	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub
		attachmentsManager.deleteAttachment(itemID);
	}

	@Override
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		// TODO Auto-generated method stub
		List<Attachment> attachments = new ArrayList<Attachment>();
		if (values.get("bussuuid") != null) {
			attachments = attachmentsManager.getListByBussId(values.get("bussuuid")
					.toString());
		}
		return attachments;
	}

	@Override
	public List<Attachment> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return new ArrayList<Attachment>();
	}

}
