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
import com.skynet.spms.action.partCatalog.PartSaleReleaseDataSourceAction;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.helper.EditionGenerator;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartSaleReleaseManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;

@Service
@Transactional
public class PartSaleReleaseManagerImpl extends CommonManagerImpl<PartSaleRelease> implements PartSaleReleaseManager {
	
	private Logger log=LoggerFactory.getLogger(PartSaleReleaseManagerImpl.class);
	@Override
	public List<PartSaleRelease> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(PartSaleRelease.class);
		String status = (String)values.remove("m_BussinessPublishStatusEntity.m_PublishStatus");
		if(status != null){
			criteria = criteria.createCriteria("m_BussinessPublishStatusEntity", "ps", Criteria.INNER_JOIN);
			criteria.add(Restrictions.eq("ps.m_PublishStatus", status));
		}
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<PartSaleRelease> list = criteria.list();
		return list;
	}
	
	@Override
	public void insertCascade(PartSaleRelease partSaleRelease,String partIndexId) {
		PartIndex partIndex =(PartIndex)getSession().get(PartIndex.class, partIndexId);
		partSaleRelease.setM_PartIndex(partIndex);
		
		SalesPrice salesPrice = new SalesPrice();
		EditionsInformation editionsInformation = new EditionsInformation();
		getSession().save(salesPrice);
		getSession().save(editionsInformation);		
		BussinessPublishStatusEntity pubStatus = new BussinessPublishStatusEntity();
		pubStatus.setM_PublishStatus(PublishStatus.unpublished);
		getSession().save(pubStatus);
		
		
		partSaleRelease.setM_SalesPrice(salesPrice);
		partSaleRelease.setM_EditionsInformation(editionsInformation);
		partSaleRelease.setM_BussinessPublishStatusEntity(pubStatus);
		partSaleRelease.setEdition(EditionGenerator.getNewEditionNumber());
		
		String user = GwtActionHelper.getCurrUser();
		partSaleRelease.setCreateBy(user);
		partSaleRelease.setCreateDate(new Date());
		getSession().save(partSaleRelease);
	}

	@Override
	public PartSaleRelease updateCascade(Map<String, Object> newValues, String itemID) {
		String publishStatus = (String)newValues.remove("publishStatus");
		PartSaleRelease partSaleRelease = (PartSaleRelease)getSession().get(PartSaleRelease.class, itemID);
		
		
		if(publishStatus!=null){
			Date newDate = new Date();
			if(publishStatus.equals("published")){
				partSaleRelease.getM_EditionsInformation().setReleaseVersionDate(newDate);
				//需要添加发布人信息
			}
			partSaleRelease.getM_BussinessPublishStatusEntity().setM_PublishStatus(PublishStatus.valueOf(publishStatus));
			partSaleRelease.getM_BussinessPublishStatusEntity().setActionDate(newDate);
		}else{
			String partIndexId = (String)newValues.remove("partIndexId");
			if(partIndexId != null){
				PartIndex partIndex = (PartIndex)getSession().get(PartIndex.class, partIndexId);
				partSaleRelease.setM_PartIndex(partIndex);
			}
			BeanPropUtil.fillEntityWithMap(partSaleRelease, newValues);
			for(Map.Entry<String, Object> entry : newValues.entrySet()){
				log.info(entry.getKey() + " : " + entry.getValue() );
			}
			String nexEdition = EditionGenerator.getNextEditionNumber(partSaleRelease.getEdition());
			partSaleRelease.getM_EditionsInformation().setEditionsReviseDate(new Date());
			//需要添加版次修订人信息
			partSaleRelease.setEdition(nexEdition);
		}
		getSession().update(partSaleRelease);
		return partSaleRelease;
	}

	@Override
	public List<PartSaleRelease> queryFilter(List clientCriteria) {
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), clientCriteria, PartSaleRelease.class);
		log.info("===============");
		log.info("HQL : " + query.getQueryString());
		log.info("===============");
		return query.list();
	}

}
