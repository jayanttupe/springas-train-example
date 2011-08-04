package com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness.PartEntityManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartEntity;

@Service
@Transactional
public class PartEntityManagerImpl extends CommonManagerImpl<PartEntity> implements PartEntityManager{

	@Override
	public List<PartEntity> getPartEntity(Map values, int startRow, int endRow) {
		Criteria criteria= getSession().createCriteria(PartEntity.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, PartEntity.class, null);
		
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
	
	/**
	 * 更新备件实体的二维标签序列号和RFID标签序列号
	 */
	@Override
	public void updateBarcodeTagUUID(PartEntity partEntity) {
		String strHql = "update PartEntity set ";
		strHql = strHql + "barcodeTagUUID = :barcodeTagUUID ";
		strHql = strHql + "where partNumber = :partNumber ";
		strHql = strHql + "and partSerialNumber = :partSerialNumber ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("barcodeTagUUID", partEntity.getBarcodeTagUUID());
		pickupDeliveryQuery.setString("partNumber", partEntity.getPartNumber());
		pickupDeliveryQuery.setString("partSerialNumber", partEntity.getPartSerialNumber());
		pickupDeliveryQuery.executeUpdate();
	}
	
	/**
	 * 更新备件实体的二维标签序列号和RFID标签序列号
	 */
	@Override
	public void updateRFIDTagUUID(PartEntity partEntity) {
		String strHql = "update PartEntity set ";
		strHql = strHql + "rFIDTagUUID = :rFIDTagUUID ";
		strHql = strHql + "where partNumber = :partNumber ";
		strHql = strHql + "and partSerialNumber = :partSerialNumber ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("rFIDTagUUID", partEntity.getrFIDTagUUID());
		pickupDeliveryQuery.setString("partNumber", partEntity.getPartNumber());
		pickupDeliveryQuery.setString("partSerialNumber", partEntity.getPartSerialNumber());
		pickupDeliveryQuery.executeUpdate();
	}
}