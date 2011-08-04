package com.skynet.spms.manager.partCatalog.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.helper.EditionGenerator;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartSupplierPriceManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.csdd.s.SupplierCode;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.PartSupplierPriceIndex;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice.SupplierSalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;

@Service
@Transactional
public class PartSupplierPriceManagerImpl extends CommonManagerImpl<PartSupplierPriceIndex> implements PartSupplierPriceManager {
	@Override
	public List<PartSupplierPriceIndex> queryByProps(Map<String, Object> values) {
		String partIndexId = (String)values.remove("partIndexId");
		Criteria criteria = getSession().createCriteria(PartSupplierPriceIndex.class);
		String status = (String)values.remove("m_BussinessPublishStatusEntity.m_PublishStatus");
		if(status != null){
			criteria = criteria.createCriteria("m_BussinessPublishStatusEntity", "ps", Criteria.INNER_JOIN);
			criteria.add(Restrictions.eq("ps.m_PublishStatus", status));
		}
		if(partIndexId != null){
			criteria.createCriteria("m_PartIndex","pi",Criteria.INNER_JOIN).add(Restrictions.eq("pi.id", partIndexId));
		}
		
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		List<PartSupplierPriceIndex> list = criteria.list();
		return list;
	}
	
	@Override
	public void insertCascade(PartSupplierPriceIndex partSupplierPriceIndex,String partIndexId,String supplierCodeId) {
		PartIndex partIndex =(PartIndex)getSession().get(PartIndex.class, partIndexId);
		partSupplierPriceIndex.setM_PartIndex(partIndex);
		
		SupplierCode supplierCode = (SupplierCode)getSession().get(SupplierCode.class, supplierCodeId);
		partSupplierPriceIndex.setM_SupplierCode(supplierCode);
		
		SupplierSalesPrice supplierSalesPrice = new SupplierSalesPrice();
		EditionsInformation editionsInformation = new EditionsInformation();
		getSession().save(supplierSalesPrice);
		getSession().save(editionsInformation);		
		BussinessPublishStatusEntity pubStatus = new BussinessPublishStatusEntity();
		pubStatus.setM_PublishStatus(PublishStatus.unpublished);
		getSession().save(pubStatus);
		
		
		partSupplierPriceIndex.setM_SupplierSalesPrice(supplierSalesPrice);
		partSupplierPriceIndex.setM_EditionsInformation(editionsInformation);
		partSupplierPriceIndex.setM_BussinessPublishStatusEntity(pubStatus);
		partSupplierPriceIndex.setEdition(EditionGenerator.getNewEditionNumber());
		
		String user = GwtActionHelper.getCurrUser();
		partSupplierPriceIndex.setCreateBy(user);
		partSupplierPriceIndex.setCreateDate(new Date());
		getSession().save(partSupplierPriceIndex);
	}

	@Override
	public PartSupplierPriceIndex updateCascade(Map<String, Object> newValues, String itemID) {
		String publishStatus = (String)newValues.remove("publishStatus");
		PartSupplierPriceIndex partSupplierPriceIndex = (PartSupplierPriceIndex)getSession().get(PartSupplierPriceIndex.class, itemID);
		
		
		if(publishStatus!=null){
			Date newDate = new Date();
			if(publishStatus.equals("published")){
				partSupplierPriceIndex.getM_EditionsInformation().setReleaseVersionDate(newDate);
				//需要添加发布人信息
				String currentUser = GwtActionHelper.getCurrUser();
				partSupplierPriceIndex.getM_EditionsInformation().setReleaseMan(currentUser);
			}
			partSupplierPriceIndex.getM_BussinessPublishStatusEntity().setM_PublishStatus(PublishStatus.valueOf(publishStatus));
			partSupplierPriceIndex.getM_BussinessPublishStatusEntity().setActionDate(newDate);
		}else{
			String partIndexId = (String)newValues.remove("partIndexId");
			if(partIndexId != null){
				PartIndex partIndex = (PartIndex)getSession().get(PartIndex.class, partIndexId);
				partSupplierPriceIndex.setM_PartIndex(partIndex);
			}
			String supplierCodeId = (String)newValues.remove("supplierCodeId");
			if(supplierCodeId != null){
				SupplierCode supplierCode = (SupplierCode)getSession().get(SupplierCode.class, supplierCodeId);
				partSupplierPriceIndex.setM_SupplierCode(supplierCode);
			}
			BeanPropUtil.fillEntityWithMap(partSupplierPriceIndex, newValues);
			String nexEdition = EditionGenerator.getNextEditionNumber(partSupplierPriceIndex.getEdition());
			partSupplierPriceIndex.getM_EditionsInformation().setEditionsReviseDate(new Date());
			//添加版次修订人信息
			String currentUser = GwtActionHelper.getCurrUser();
			partSupplierPriceIndex.getM_EditionsInformation().setEditionsReviseMan(currentUser);
			partSupplierPriceIndex.setEdition(nexEdition);
		}
		getSession().update(partSupplierPriceIndex);
		return partSupplierPriceIndex;
	}

	@Override
	public List<PartSupplierPriceIndex> queryFilter(List clientCriteria) {
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), clientCriteria, PartSupplierPriceIndex.class);
		return query.list();
	}
}
