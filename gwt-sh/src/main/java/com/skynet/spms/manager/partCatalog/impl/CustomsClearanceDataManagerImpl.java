package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.CustomsClearanceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.ExportRestraints;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.ImportRestraints;

@Service
@Transactional
public class CustomsClearanceDataManagerImpl extends CommonManagerImpl<CustomsClearanceData> implements CustomsClearanceDataManager {

	@Override
	public List<CustomsClearanceData> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(CustomsClearanceData.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		return criteria.list();
	}

	@Override
	public CustomsClearanceData updateCascade(Map<String, Object> values,String itemId) {
		/*String exportRestraintsId = (String)values.get("m_ExportRestraints.id");
		String importRestraintsId = (String)values.get("m_ImportRestraints.id");*/
		CustomsClearanceData customsClearanceData = (CustomsClearanceData)getSession().get(CustomsClearanceData.class, itemId);
		/*if(exportRestraintsId != null){
			boolean exportRestraints = Boolean.valueOf((String)values.get("m_ExportRestraints.exportRestraints"));
			customsClearanceData.getM_ExportRestraints().setExportRestraints(exportRestraints);
			String description = (String)values.get("m_ExportRestraints.description");
			customsClearanceData.getM_ExportRestraints().setDescription(description);
		}else if(importRestraintsId != null){
			boolean importRestraints = Boolean.valueOf((String)values.get("m_ImportRestraints.importRestraints"));
			customsClearanceData.getM_ImportRestraints().setImportRestraints(importRestraints);
			String description = (String)values.get("m_ImportRestraints.description");
			customsClearanceData.getM_ExportRestraints().setDescription(description);
		}else{*/
			
			if(customsClearanceData.getM_ExportRestraints() == null){
				ExportRestraints exportRestraints = new ExportRestraints();
				getSession().save(exportRestraints);
				customsClearanceData.setM_ExportRestraints(exportRestraints);
			}
			if(customsClearanceData.getM_ImportRestraints() == null){
				ImportRestraints importRestraints = new ImportRestraints();
				getSession().save(importRestraints);
				customsClearanceData.setM_ImportRestraints(importRestraints);
			}
			BeanPropUtil.fillEntityWithMap(customsClearanceData, values);
		//}
		getSession().update(customsClearanceData);
		return customsClearanceData;
	}
}
