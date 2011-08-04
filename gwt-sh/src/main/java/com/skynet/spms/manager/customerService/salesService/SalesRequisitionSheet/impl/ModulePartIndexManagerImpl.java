/**
 * ModulePartIndexManagerImpl.java
 * com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.impl
 *
 * TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Jul 25, 2011 		Administrator
 *
 * Copyright (c) 2011, Tocersoft.
*/

package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.ModulePartIndexManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

/**
 * TODO
 * @author   Administrator
 * @version  1.0
 * @Date	 Jul 25, 2011
 *
 */
@Service
@Transactional
public class ModulePartIndexManagerImpl implements ModulePartIndexManager {
	
	private static String SIMPLHQL="select p from PartIndex p where p.partNumber=?";
	
	private static String PARTHQL="select count(*) from PartsInventoryRecord where partNumber=?";
	
	@Autowired
	SessionFactory sessionFactory;

	public PartIndex getPartIndexByPartNumber(String partNumber) {
		Query query=sessionFactory.getCurrentSession().createQuery(SIMPLHQL);
		query.setParameter(0, partNumber);
		return (PartIndex) query.uniqueResult();
	}

	public Long getPartIndexNumberFromInventoryRecord(String partNumber) {
		Query query=sessionFactory.getCurrentSession().createQuery(PARTHQL);
		query.setParameter(0, partNumber);
		return ((Long) query.iterate().next()).longValue();
		
	}

}

