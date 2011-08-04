package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog.TechnicalPublishDoc;

public interface TechnicalDocumentsManager extends CommonManager<TechnicalPublishDoc>{
	
	  public List<TechnicalPublishDoc> queryByProps(Map<String ,Object> values);

}
