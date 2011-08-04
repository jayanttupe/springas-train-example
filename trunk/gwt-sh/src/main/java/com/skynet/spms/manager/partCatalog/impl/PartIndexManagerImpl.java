package com.skynet.spms.manager.partCatalog.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartIndexManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.m.ManufacturerCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.ExportRestraints;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.ImportRestraints;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;

@Service
@Transactional
public class PartIndexManagerImpl extends CommonManagerImpl<PartIndex> implements PartIndexManager {

	private Logger log=LoggerFactory.getLogger(PartIndexManagerImpl.class);
	
	@Override
	public List<PartIndex> queryByProps(Map<String,Object> values,int startRow,int endRow){
		Criteria criteria = getSession().createCriteria(PartIndex.class);
		String status = (String)values.remove("m_BussinessPublishStatusEntity.m_PublishStatus");
		if(status != null){
			PublishStatus publistStatus = PublishStatus.valueOf(status);
			criteria = criteria.createCriteria("m_BussinessPublishStatusEntity", "ps", Criteria.INNER_JOIN);
			criteria.add(Restrictions.eq("ps.m_PublishStatus", publistStatus));
		}
		
		for(Map.Entry<String, Object> entry : values.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof String){
				criteria.add(Restrictions.like(key, "%" + value + "%"));
			}else{
				criteria.add(Restrictions.eq(key, value));
			}
		}
		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow - startRow);
		}
		List<PartIndex> list = criteria.list();
		return list;
	}
	@Override
	public void insertCascade(PartIndex partIndex, String ManufacturerCodeId) {
		PartTechnicalData partTechnicalData = new PartTechnicalData();
		//FinanceData financeData = new FinanceData();
		BasicInformation basicInformation = new BasicInformation();
		CustomsClearanceData customsClearanceData = new CustomsClearanceData();
        
		partIndex.setM_PartTechnicalData(partTechnicalData);
		partIndex.setM_BasicInformation(basicInformation);
		partIndex.setM_CustomsClearanceData(customsClearanceData);
		//partIndex.setM_FinanceData(financeData);
		
		BussinessPublishStatusEntity pubStatus = new BussinessPublishStatusEntity();
		pubStatus.setM_PublishStatus(PublishStatus.unpublished);
		getSession().save(pubStatus);
		partIndex.setM_BussinessPublishStatusEntity(pubStatus);
		if(ManufacturerCodeId != null){
			ManufacturerCode code = (ManufacturerCode)getSession().get(ManufacturerCode.class, ManufacturerCodeId);
			partIndex.setM_ManufacturerCode(code);
		}
		String user = GwtActionHelper.getCurrUser();
		partIndex.setCreateBy(user);
		partIndex.setCreateDate(new Date());
		getSession().save(partIndex);
		
		ExportRestraints exportRestraints = new ExportRestraints();
		ImportRestraints importRestraints = new ImportRestraints();
		getSession().save(exportRestraints);
		getSession().save(importRestraints);
		partIndex.getM_CustomsClearanceData().setM_ExportRestraints(exportRestraints);
		partIndex.getM_CustomsClearanceData().setM_ImportRestraints(importRestraints);
		getSession().update(partIndex.getM_CustomsClearanceData());
	}

	@Override
	public PartIndex updateCascade(Map<String, Object> newValues, String itemID) {
		
		String publishStatus = (String)newValues.remove("publishStatus");
		PartIndex partIndex = (PartIndex)getSession().get(PartIndex.class, itemID);
		
		//发布操作
		if(publishStatus != null){
			partIndex.getM_BussinessPublishStatusEntity().setM_PublishStatus(PublishStatus.valueOf(publishStatus));
			partIndex.getM_BussinessPublishStatusEntity().setActionDate(new Date());
		}
		else{   //更新操作
			partIndex.setManufacturerPartNumber((String)newValues.get("manufacturerPartNumber"));
			partIndex.setPartNumber((String)newValues.get("partNumber"));
			partIndex.setKeyword_zh((String)newValues.get("keyword_zh"));
			partIndex.setKeyword_en((String)newValues.get("keyword_en"));
			String manufacturerCodeId = (String)newValues.get("manufacturerCodeId");
			ManufacturerCode code = null;
			if(manufacturerCodeId != null){
				code = (ManufacturerCode)getSession().get(ManufacturerCode.class, manufacturerCodeId);
			}
			partIndex.setM_ManufacturerCode(code);
		}
		getSession().update(partIndex);
		return partIndex;
	}

	@Override
	public List<PartIndex> queryFilter(List clientCriteria,int startRow,int endRow) {
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), clientCriteria, PartIndex.class);
		if (endRow > 0) {
			query.setFirstResult(startRow);
			query.setMaxResults(endRow - startRow);
		}
		log.info("===============");
		log.info("HQL : " + query.getQueryString());
		log.info("===============");
		List<PartIndex> list = query.list();
		for(PartIndex pi : list){
			PartApplicationData pad = pi.getM_PartApplicationData();
			if(pad == null){
				pad = new PartApplicationData();
				pi.setM_PartApplicationData(pad);
				getSession().saveOrUpdate(pi);
			}
		}
		/*for(PartIndex pi : list){
			CustomsClearanceData ccd = pi.getM_CustomsClearanceData();
			if(ccd == null){
				ccd = new CustomsClearanceData();
				getSession().saveOrUpdate(ccd);
				pi.setM_CustomsClearanceData(ccd);
				getSession().saveOrUpdate(pi);
			}
			ExportRestraints er = ccd.getM_ExportRestraints();
			if(er == null){
				er = new ExportRestraints();
				getSession().saveOrUpdate(er);
				ccd.setM_ExportRestraints(er);
				getSession().saveOrUpdate(ccd);
			}
			ImportRestraints ir = ccd.getM_ImportRestraints();
			if(ir == null){
				ir = new ImportRestraints();
				getSession().saveOrUpdate(ir);
				ccd.setM_ImportRestraints(ir);
				getSession().saveOrUpdate(ccd);
			}
		}*/
		return list;
	}
	

}
