package com.skynet.spms.action.customerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.attachment.IAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

@Component
public class AttachmentOperAction implements DataSourceAction<Attachment> {

	public String[] getBindDsName() {
		return new String[] { "attachment_op_dataSource" };
	}

	@Resource
	IAttachmentManager manager;

	public void insert(Attachment item) {

	}

	public Attachment update(Map<String, Object> newValues, String itemID) {

		return null;
	}

	public void delete(String itemID) {
		
	}

	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		if (values.get("bussuuid") != null) {
			attachments = manager.getListByBussId(values.get("bussuuid")
					.toString());
		}
		return attachments;
	}

	public List<Attachment> getList(int startRow, int endRow) {
		return new ArrayList<Attachment>();
	}

}
