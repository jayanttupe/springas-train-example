package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.BasicInfoManager;
import com.skynet.spms.persistence.entity.csdd.e.EssentialityCode;
import com.skynet.spms.persistence.entity.csdd.p.PMAPartCausedDefectIndicator;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BuyerFurnishedEquipmentIndicator;
import com.skynet.spms.persistence.entity.partCatalog.technicalDocumentsCatalog.TechnicalPublishRecord;
import com.skynet.spms.persistence.entity.spmsdd.DangerCategory;
import com.skynet.spms.persistence.entity.spmsdd.EquipmentType;
import com.skynet.spms.persistence.entity.spmsdd.TechnicalPublishType;

@Service
@Transactional
public class BasicInfoManagerImpl extends CommonManagerImpl<BasicInformation> implements BasicInfoManager {

	@Override
	public List<BasicInformation> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(BasicInformation.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<BasicInformation> list = criteria.list();
		return list;
	}

	@Override
	public BasicInformation updateCascade(Map<String, Object> values,String itemId) {
		String publishreferenceNumber = (String)values.remove("m_technicalPublishRecord.referenceNumber");
		String m_TechnicalPublishType = (String)values.remove("m_technicalPublishRecord.m_TechnicalPublishType");
		BasicInformation basicInformation = (BasicInformation)getSession().get(BasicInformation.class, itemId);
		
		TechnicalPublishRecord record = basicInformation.getM_technicalPublishRecord();
		if(record == null){
			record = new TechnicalPublishRecord();
			basicInformation.setM_technicalPublishRecord(record);
		}
		record.setReferenceNumber(publishreferenceNumber);
		TechnicalPublishType technicalPublishType = null;
		if(m_TechnicalPublishType != null){
			technicalPublishType = TechnicalPublishType.valueOf(m_TechnicalPublishType);
		}
		record.setM_TechnicalPublishType(technicalPublishType);
		
		//basicInformation.setKeyword_zh((String)values.get("keyword_zh"));
		//basicInformation.setKeyword_en((String)values.get("keyword_en"));
		basicInformation.setPartName_zh((String)values.get("partName_zh"));
		basicInformation.setPartName_en((String)values.get("partName_en"));
		basicInformation.setNextHigherAssemblyPartNumber((String)values.get("nextHigherAssemblyPartNumber"));
		basicInformation.setSuitableAircraftModel((String)values.get("suitableAircraftModel"));
		
		String m_EssentialityCode = (String)values.get("m_EssentialityCode");
		EssentialityCode essentialityCode = null; 
		if(m_EssentialityCode != null){
			essentialityCode = EssentialityCode.valueOf(m_EssentialityCode);
		}
		basicInformation.setM_EssentialityCode(essentialityCode);
		
		String m_SparePartClassCode = (String)values.get("m_SparePartClassCode");
		SparePartClassCode sparePartClassCode = null;
		if(m_SparePartClassCode != null){
			sparePartClassCode = SparePartClassCode.valueOf(m_SparePartClassCode);
		}
		basicInformation.setM_SparePartClassCode(sparePartClassCode);
		
		String m_PMAPartCausedDefectIndicator = (String)values.get("m_PMAPartCausedDefectIndicator");
		PMAPartCausedDefectIndicator pmaPartCausedDefectIndicator = null;
		if(m_PMAPartCausedDefectIndicator != null){
			pmaPartCausedDefectIndicator = PMAPartCausedDefectIndicator.valueOf(m_PMAPartCausedDefectIndicator);
		}
		basicInformation.setM_PMAPartCausedDefectIndicator(pmaPartCausedDefectIndicator);
		
		String m_EquipmentType = (String)values.get("m_EquipmentType");
		EquipmentType equipmentType = null;
		if(m_EquipmentType != null){
			equipmentType = EquipmentType.valueOf(m_EquipmentType);
		}
		basicInformation.setM_EquipmentType(equipmentType);
		
		String engine = (String)values.get("engine");
		basicInformation.setEngine(Boolean.valueOf(engine));
		
		String m_UnitOfMeasureCode = (String)values.get("m_UnitOfMeasureCode");
		UnitOfMeasureCode unitOfMeasureCode = null;
		if(m_UnitOfMeasureCode != null){
			unitOfMeasureCode = UnitOfMeasureCode.valueOf(m_UnitOfMeasureCode);
		}
		basicInformation.setM_UnitOfMeasureCode(unitOfMeasureCode);
		
		String serial = (String)values.get("serial");
		basicInformation.setSerial(Boolean.valueOf(serial));
		
		basicInformation.setRemarkText((String)values.get("remarkText"));
		
		String m_BuyerFurnishedEquipmentIndicator = (String)values.get("m_BuyerFurnishedEquipmentIndicator");
		BuyerFurnishedEquipmentIndicator buyerFurnishedEquipmentIndicator = null;
		if(m_BuyerFurnishedEquipmentIndicator != null){
			buyerFurnishedEquipmentIndicator = BuyerFurnishedEquipmentIndicator.valueOf(m_BuyerFurnishedEquipmentIndicator);
		}
		basicInformation.setM_BuyerFurnishedEquipmentIndicator(buyerFurnishedEquipmentIndicator);
		
		long dataSource = (Long)values.get("dataSource");
		basicInformation.setDataSource((int)dataSource);
		
		String electrostaticSensitiveDeviceIndicator = (String)values.get("electrostaticSensitiveDeviceIndicator");
		basicInformation.setElectrostaticSensitiveDeviceIndicator(Boolean.valueOf(electrostaticSensitiveDeviceIndicator));
		
		String hazardousMaterial = (String)values.get("hazardousMaterial");
		basicInformation.setHazardousMaterial(Boolean.valueOf(hazardousMaterial));
		
		String m_DangerCategory = (String)values.get("m_DangerCategory");
		DangerCategory dangerCategory = null;
		if(m_DangerCategory != null){
			dangerCategory = DangerCategory.valueOf(m_DangerCategory);
		}
		basicInformation.setM_DangerCategory(dangerCategory);
		
		basicInformation.setHazardousMaterialDescription((String)values.get("hazardousMaterialDescription"));
		basicInformation.setAtaNumber((String)values.get("ataNumber"));
		
		String comacPatent = (String)values.get("comacPatent");
		basicInformation.setComacPatent(Boolean.valueOf(comacPatent));
		
		basicInformation.setPatentDescription((String)values.get("patentDescription"));
		
		getSession().update(basicInformation);
		return basicInformation;
	}
}
