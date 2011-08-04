package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.TechnicalDocumentsManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog.TechnicalPublishDoc;
import com.skynet.spms.persistence.entity.spmsdd.TechnicalPublishType;

@Service
@Transactional
public class TechnicalDocumentsManagerImpl extends CommonManagerImpl<TechnicalPublishDoc> implements TechnicalDocumentsManager{

	@Override
	public List<TechnicalPublishDoc> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(TechnicalPublishDoc.class);
		
		String id = (String)values.get("id");
		if(id != null){
			criteria.add(Restrictions.eq("id", id));
		}
		String location = (String)values.get("location");
		if(location != null){
			criteria.add(Restrictions.ilike("location", "%"+location+"%"));
		}
		String m_TechnicalPublishType = (String)values.get("m_TechnicalPublishType");
		if(m_TechnicalPublishType != null){
			TechnicalPublishType type = TechnicalPublishType.valueOf(m_TechnicalPublishType);
			criteria.add(Restrictions.eq("m_TechnicalPublishType", type));
		}

		List<TechnicalPublishDoc> list = criteria.list();
		return list;
	}

	 
}
