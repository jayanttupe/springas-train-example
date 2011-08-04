package com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.TransprotationDocumentManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.TransprotationDocument;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class TransprotationDocumentManagerImpl extends CommonManagerImpl<TransprotationDocument> implements TransprotationDocumentManager{

	@Override
	public List<TransprotationDocument> getTransprotationDocument(
			Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(TransprotationDocument.class);
		criteria.add(Restrictions.eq("deleted", false));
		
		Set set = values.entrySet();
		Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
        	Map.Entry entry =(Map.Entry)iterator.next();
        	String fieldName = (String)entry.getKey().toString();
        	if (!fieldName.equals("temp")) {
        		criteria.add(Restrictions.eq(fieldName, values.get(fieldName).toString()));	
        	}
        }
        
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
}