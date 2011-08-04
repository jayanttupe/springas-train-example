package com.skynet.spms.manager.partCatalog.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.AircraftConfigCatalogManager;
import com.skynet.spms.persistence.entity.csdd.a.AircraftModelIdentifier;
import com.skynet.spms.persistence.entity.partCatalog.aircraftConfigCatalog.AircraftRegistration;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;


@Service
@Transactional
public class AircraftConfigCatalogManagerImpl extends CommonManagerImpl<AircraftRegistration> implements AircraftConfigCatalogManager {
	@Override
	public List<AircraftRegistration> queryByProps(Map<String, Object> values) {
		String partIndexId = (String)values.remove("partIndexId");
		List<AircraftRegistration> list = new ArrayList<AircraftRegistration>();
		Criteria criteria = getSession().createCriteria(AircraftRegistration.class);
		if(partIndexId != null){
			PartIndex partIndex = (PartIndex)getSession().get(PartIndex.class, partIndexId);
			if(partIndex != null){
				String suitableAircraftModel = partIndex.getM_BasicInformation().getSuitableAircraftModel();
				if(suitableAircraftModel != null){
					String[] models = suitableAircraftModel.split(",");
					List<AircraftModelIdentifier> modelList = new ArrayList<AircraftModelIdentifier>();
					for(String m : models){
						modelList.add(AircraftModelIdentifier.valueOf(m));
					}
					criteria.add(Restrictions.in("m_AircraftModelIdentifier", modelList));
					list = criteria.list();
				}
			}
				
		}else{
			for(Map.Entry<String, Object> entry : values.entrySet()){
				criteria.add(Restrictions.eq(entry.getKey(),entry.getValue() ));
			}
			list = criteria.list();
		}
		return list;
	}
	
	@Override
	public List<AircraftRegistration> queryByFilter(List clientCriteria) {
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), clientCriteria, AircraftRegistration.class);
		return query.list();
	}
	
}
