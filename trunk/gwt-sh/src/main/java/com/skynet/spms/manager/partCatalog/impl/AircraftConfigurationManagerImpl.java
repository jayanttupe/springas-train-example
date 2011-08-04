/*package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.AircraftConfigurationManager;
import com.skynet.spms.persistence.entity.partCatalog.aircraftConfigCatalog.AircraftRegistration;
import com.skynet.spms.persistence.entity.partCatalog.repairableCatalog.RepairData;


@Service
@Transactional
public class AircraftConfigurationManagerImpl extends CommonManagerImpl<AircraftRegistration> implements AircraftConfigurationManager {
	@Override
	public List<AircraftRegistration> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(AircraftRegistration.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<AircraftRegistration> list = criteria.list();
		return list;
	}

	@Override
	public List<AircraftRegistration> queryByFilter(List clientCriteria) {
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), clientCriteria, AircraftRegistration.class);
		return query.list();
	}
	
}
*/