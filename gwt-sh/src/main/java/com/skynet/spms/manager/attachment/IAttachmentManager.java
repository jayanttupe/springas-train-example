package com.skynet.spms.manager.attachment;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.Attachment;


public interface IAttachmentManager {

	public Attachment addAttachment(Attachment o);

	public void deleteAttachment(String uuid);
	
	public List<Attachment> getListByBussId(String bID);

	public Attachment updateAttachment(Map<String, Object> newValues,
			String itemID);

}
