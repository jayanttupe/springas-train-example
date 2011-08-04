package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.CustomsAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

@Service
@Transactional
public class CustomsAttachmentManagerImpl extends CommonManagerImpl<Attachment> implements CustomsAttachmentManager {

	@Override
	public List<Attachment> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(Attachment.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		return criteria.list();
	}

	@Override
	public Attachment updateCascade(Map<String, Object> values,String itemId) {
		return null;
	}
}
