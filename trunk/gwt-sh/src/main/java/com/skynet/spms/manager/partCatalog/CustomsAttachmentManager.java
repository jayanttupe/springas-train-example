package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.base.Attachment;

public interface CustomsAttachmentManager extends CommonManager<Attachment> {

	public List<Attachment> queryByProps(Map<String, Object> values);
	
	public Attachment updateCascade(Map<String, Object> values,String itemId);
	
}
