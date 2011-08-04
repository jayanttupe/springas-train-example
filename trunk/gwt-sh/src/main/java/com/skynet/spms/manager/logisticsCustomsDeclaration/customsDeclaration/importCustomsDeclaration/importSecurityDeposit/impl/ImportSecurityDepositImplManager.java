package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.impl;

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
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDepositManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDeposit;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ImportSecurityDepositImplManager extends CommonManagerImpl<ImportSecurityDeposit> implements ImportSecurityDepositManager{

	@Override
	public List<ImportSecurityDeposit> getImportSecurityDeposit(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ImportSecurityDeposit a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		// 字段排序
		String strOrder = "order by a.securityDepositNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ImportSecurityDeposit> importSecurityDepositList = new ArrayList<ImportSecurityDeposit>();
		for (Object[] objects : result)
		{
			ImportSecurityDeposit importSecurityDeposit = new ImportSecurityDeposit();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 保证金信息
			if (objects[0] != null)
				importSecurityDeposit = (ImportSecurityDeposit)objects[0];
			// 提发货指令信息
			if (objects[1] != null) {
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				// 报关代理商
				importSecurityDeposit.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
				// 运输方式代码
				importSecurityDeposit.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
			}
			
			importSecurityDepositList.add(importSecurityDeposit);
		}
		return importSecurityDepositList;
	}
	
	@Override
	public ImportSecurityDeposit saveImportSecurityDeposit(ImportSecurityDeposit importSecurityDeposit) {
		Session session = getSession();
		importSecurityDeposit.setCreateBy(GwtActionHelper.getCurrUser());
		importSecurityDeposit.setCreateDate(new Date());
		// 保存保证金信息
		session.saveOrUpdate(importSecurityDeposit);

		List<ImportCustomsDeclarationItems> importCustomsDeclarationItemsList = importSecurityDeposit.getImportCustomsDeclarationItems();
		// 保存保证金明细信息
		for (int i = 0; i < importCustomsDeclarationItemsList.size(); i++)
		{
			importCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			importCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ImportCustomsDeclarationItems)importCustomsDeclarationItemsList.get(i));
		}

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "transportationCode  = :transportationCode, ";
		strHql = strHql + "clearanceAgent = :clearanceAgent, ";
		strHql = strHql + "securityOrCustomsTariff = '1' ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("transportationCode", importSecurityDeposit.getTransportationCode());
		pickupDeliveryQuery.setString("clearanceAgent", importSecurityDeposit.getClearanceAgent());
		pickupDeliveryQuery.setString("orderID", importSecurityDeposit.getOrderID());
		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return importSecurityDeposit;
	}
}
