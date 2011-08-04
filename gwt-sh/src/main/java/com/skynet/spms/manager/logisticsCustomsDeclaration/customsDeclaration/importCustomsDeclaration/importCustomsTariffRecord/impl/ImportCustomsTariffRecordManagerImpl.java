package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ImportCustomsTariffRecordManagerImpl extends CommonManagerImpl<ImportCustomsTariffRecord> implements ImportCustomsTariffRecordManager{

	@Override
	public List<ImportCustomsTariffRecord> getImportCustomsTariffRecord(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ImportCustomsTariffRecord a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		// 字段排序
		String strOrder = "order by a.customsNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ImportCustomsTariffRecord> importCustomsTariffRecordList = new ArrayList<ImportCustomsTariffRecord>();
		for (Object[] objects : result)
		{
			ImportCustomsTariffRecord importCustomsTariffRecord = new ImportCustomsTariffRecord();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 订舱信息
			if (objects[0] != null)
				importCustomsTariffRecord = (ImportCustomsTariffRecord)objects[0];
			// 提发货指令信息
			if (objects[1] != null){
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				// 报关代理商
				importCustomsTariffRecord.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
				// 运输方式代码
				importCustomsTariffRecord.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
			}
			importCustomsTariffRecordList.add(importCustomsTariffRecord);
		}
		return importCustomsTariffRecordList;
	}

	@Override
	public ImportCustomsTariffRecord saveImportCustomsTariffRecord(ImportCustomsTariffRecord importCustomsTariffRecord) {
		Session session = getSession();
		importCustomsTariffRecord.setCreateBy(GwtActionHelper.getCurrUser());
		importCustomsTariffRecord.setCreateDate(new Date());
		// 保存关税信息
		session.saveOrUpdate(importCustomsTariffRecord);

		List<ImportCustomsDeclarationItems> importCustomsDeclarationItemsList = importCustomsTariffRecord.getImportCustomsDeclarationItems();
		// 保存关税明细信息
		for (int i = 0; i < importCustomsDeclarationItemsList.size(); i++)
		{
			importCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			importCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ImportCustomsDeclarationItems)importCustomsDeclarationItemsList.get(i));
		}
		
		// 更新提发货信息
		String pickupDeliveryHql = "update PickupDeliveryOrder set ";
		pickupDeliveryHql = pickupDeliveryHql + "transportationCode  = :transportationCode, ";
		pickupDeliveryHql = pickupDeliveryHql + "clearanceAgent = :clearanceAgent, ";
		pickupDeliveryHql = pickupDeliveryHql + "securityOrCustomsTariff = '2' ";
		pickupDeliveryHql = pickupDeliveryHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(pickupDeliveryHql);
		pickupDeliveryQuery.setString("transportationCode", importCustomsTariffRecord.getTransportationCode());
		pickupDeliveryQuery.setString("clearanceAgent", importCustomsTariffRecord.getClearanceAgent());
		pickupDeliveryQuery.setString("orderID", importCustomsTariffRecord.getOrderID());
		pickupDeliveryQuery.executeUpdate();

		return importCustomsTariffRecord;
	}

}
