/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.impl;

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
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDepositManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit.ExportSecurityDeposit;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ExportSecurityDepositManagerImpl extends CommonManagerImpl<ExportSecurityDeposit> implements ExportSecurityDepositManager{

	@Override
	public List<ExportSecurityDeposit> getExportSecurityDeposit(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ExportSecurityDeposit a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		// 字段排序
		String strOrder = "order by a.securityDepositNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ExportSecurityDeposit> exportSecurityDepositList = new ArrayList<ExportSecurityDeposit>();
		for (Object[] objects : result)
		{
			ExportSecurityDeposit exportSecurityDeposit = new ExportSecurityDeposit();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 保证金信息
			if (objects[0] != null)
				exportSecurityDeposit = (ExportSecurityDeposit)objects[0];
			// 提发货指令信息
			if (objects[1] != null) {
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				// 报关代理商
				exportSecurityDeposit.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
				// 运输方式代码
				exportSecurityDeposit.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
			}
			
			exportSecurityDepositList.add(exportSecurityDeposit);
		}
		return exportSecurityDepositList;
	}

	@Override
	public ExportSecurityDeposit saveExportSecurityDeposit(ExportSecurityDeposit exportSecurityDeposit) {
		Session session = getSession();
		exportSecurityDeposit.setCreateBy(GwtActionHelper.getCurrUser());
		exportSecurityDeposit.setCreateDate(new Date());
		// 保存保证金信息
		session.saveOrUpdate(exportSecurityDeposit);

		List<ExportCustomsDeclarationItems> exportCustomsDeclarationItemsList = exportSecurityDeposit.getExportCustomsDeclarationItems();
		// 保存保证金明细信息
		for (int i = 0; i < exportCustomsDeclarationItemsList.size(); i++)
		{
			exportCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			exportCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ExportCustomsDeclarationItems)exportCustomsDeclarationItemsList.get(i));
		}

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "transportationCode  = :transportationCode, ";
		strHql = strHql + "clearanceAgent = :clearanceAgent, ";
		strHql = strHql + "securityOrCustomsTariff = '1' ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("transportationCode", exportSecurityDeposit.getTransportationCode());
		pickupDeliveryQuery.setString("clearanceAgent", exportSecurityDeposit.getClearanceAgent());
		pickupDeliveryQuery.setString("orderID", exportSecurityDeposit.getOrderID());
		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return exportSecurityDeposit;
	}
}
