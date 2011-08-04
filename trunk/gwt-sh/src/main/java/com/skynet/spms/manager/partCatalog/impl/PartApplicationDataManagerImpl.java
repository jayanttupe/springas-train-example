package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartApplicationDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;

@Service
@Transactional
public class PartApplicationDataManagerImpl extends CommonManagerImpl<PartApplicationData> implements PartApplicationDataManager {
	@Override
	public List<PartApplicationData> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(PartApplicationData.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<PartApplicationData> list = criteria.list();
		return list;
	}

	@Override
	public PartApplicationData updateCascade(Map<String, Object> values,String itemId) {

		PartApplicationData partApplicationData = (PartApplicationData)getSession().get(PartApplicationData.class, itemId);
		
		//单机装机数量
		partApplicationData.setTotalQuantityPerAircraftEngine((String)values.get("totalQuantityPerAircraftEngine"));
		
		String etops = (String)values.get("etops");
		partApplicationData.setEtops(Boolean.valueOf(etops));
		//出口分类代码
		partApplicationData.setM_ExportControlClassificationCode((String)values.get("m_ExportControlClassificationCode"));
		
		//有效性范围代码
		partApplicationData.setEffectivityRangeCode((String)values.get("effectivityRangeCode"));

		//订货点
		//private int reorderLine;
		long reorderLine = (Long)values.get("reorderLine");
		partApplicationData.setReorderLine((int)reorderLine);
		//安全库存点
		//private int safeStockLine;
		long safeStockLine = (Long)values.get("safeStockLine");
		partApplicationData.setSafeStockLine((int)safeStockLine);
		//再订货量
		//private int reorderQuantity;
		long reorderQuantity = (Long)values.get("reorderQuantity");
		partApplicationData.setReorderQuantity((int)reorderQuantity);

		 //航线维护备注说明
		partApplicationData.setMiscellaneousText((String)values.get("miscellaneousText"));
		
		//快速转发
		String isQuickEngineChange = (String)values.get("quickEngineChange");
		partApplicationData.setQuickEngineChange(Boolean.valueOf(isQuickEngineChange));
		
		getSession().update(partApplicationData);
		return partApplicationData;
	}
	
}
