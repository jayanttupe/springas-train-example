package com.skynet.spms.manager.partCatalog.impl;
/**
 * Description: TODO
 *
 * @author   Administrator
 * @version  Ver 1.0
 * @Date	 2011-7-25
 *
 */

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartBagItemDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.partBagItem.PartBagItem;

@Service
@Transactional
public class PartBagItemDataManagerImpl extends CommonManagerImpl<PartBagItem> implements PartBagItemDataManager {
	public List<PartBagItem> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(PartBagItem.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<PartBagItem> list = criteria.list();
		return list;
	}


	
}
