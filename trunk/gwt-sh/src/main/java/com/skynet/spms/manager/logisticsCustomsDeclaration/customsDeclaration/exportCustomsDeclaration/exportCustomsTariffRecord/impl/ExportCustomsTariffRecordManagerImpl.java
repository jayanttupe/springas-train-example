/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.impl;

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
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecord;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ExportCustomsTariffRecordManagerImpl extends CommonManagerImpl<ExportCustomsTariffRecord> implements ExportCustomsTariffRecordManager{

	@Override
	public List<ExportCustomsTariffRecord> getExportCustomsTariffRecord(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ExportCustomsTariffRecord a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		// 字段排序
		String strOrder = "order by a.customsNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ExportCustomsTariffRecord> exportCustomsTariffRecordList = new ArrayList<ExportCustomsTariffRecord>();
		for (Object[] objects : result)
		{
			ExportCustomsTariffRecord exportCustomsTariffRecord = new ExportCustomsTariffRecord();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 订舱信息
			if (objects[0] != null)
				exportCustomsTariffRecord = (ExportCustomsTariffRecord)objects[0];
			// 提发货指令信息
			if (objects[1] != null){
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				// 报关代理商
				exportCustomsTariffRecord.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
				// 运输方式代码
				exportCustomsTariffRecord.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
			}
			exportCustomsTariffRecordList.add(exportCustomsTariffRecord);
		}
		return exportCustomsTariffRecordList;
	}
	/* (non-Javadoc)
	 * @see com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecordManager#SaveExportCustomsTariffRecord(com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord.ExportCustomsTariffRecord)
	 */
	@Override
	public ExportCustomsTariffRecord saveExportCustomsTariffRecord(ExportCustomsTariffRecord exportCustomsTariffRecord) {
		Session session = getSession();
		exportCustomsTariffRecord.setCreateBy(GwtActionHelper.getCurrUser());
		exportCustomsTariffRecord.setCreateDate(new Date());
		// 保存关税信息
		session.saveOrUpdate(exportCustomsTariffRecord);

		List<ExportCustomsDeclarationItems> exportCustomsDeclarationItemsList = exportCustomsTariffRecord.getExportCustomsDeclarationItems();
		// 保存关税明细信息
		for (int i = 0; i < exportCustomsDeclarationItemsList.size(); i++)
		{
			exportCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			exportCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ExportCustomsDeclarationItems)exportCustomsDeclarationItemsList.get(i));
		}

		// 更新提发货信息
		String pickupDeliveryHql = "update PickupDeliveryOrder set ";
		pickupDeliveryHql = pickupDeliveryHql + "transportationCode  = :transportationCode, ";
		pickupDeliveryHql = pickupDeliveryHql + "clearanceAgent = :clearanceAgent, ";
		pickupDeliveryHql = pickupDeliveryHql + "securityOrCustomsTariff = '2' ";
		pickupDeliveryHql = pickupDeliveryHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(pickupDeliveryHql);
		pickupDeliveryQuery.setString("transportationCode", exportCustomsTariffRecord.getTransportationCode());
		pickupDeliveryQuery.setString("clearanceAgent", exportCustomsTariffRecord.getClearanceAgent());
		pickupDeliveryQuery.setString("orderID", exportCustomsTariffRecord.getOrderID());
		pickupDeliveryQuery.executeUpdate();
		
		return exportCustomsTariffRecord;
	}
}
