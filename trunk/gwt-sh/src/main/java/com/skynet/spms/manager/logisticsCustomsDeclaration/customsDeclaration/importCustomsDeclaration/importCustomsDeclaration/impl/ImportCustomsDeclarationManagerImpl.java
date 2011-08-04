/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ImportCustomsDeclarationManagerImpl extends CommonManagerImpl<ImportCustomsDeclaration> implements ImportCustomsDeclarationManager{
	
	@Override
	public List<ImportCustomsDeclaration> getImportCustomsDeclaration(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ImportCustomsDeclaration a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and c.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		strWhere = strWhere + " and b.id = c.orderID ";
		strWhere = strWhere + " and b.isPublish = '2' ";
		if (values != null) {
			if (values.containsKey("workStatus")) {
				strWhere = strWhere + " and c.workStatus = '" + values.get("workStatus").toString() + "'";
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.customsClearance + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.customsNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ImportCustomsDeclaration> importCustomsDeclarationList = new ArrayList<ImportCustomsDeclaration>();
		for (Object[] objects : result)
		{
			ImportCustomsDeclaration importCustomsDeclaration = new ImportCustomsDeclaration();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 订舱信息
			if (objects[0] != null)
				importCustomsDeclaration = (ImportCustomsDeclaration)objects[0];
			// 提发货指令信息
			if (objects[1] != null) {
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				if (pickupDeliveryOrder.getSecurityOrCustomsTariff() != null) {
					if (pickupDeliveryOrder.getSecurityOrCustomsTariff().equals("1")) {
						importCustomsDeclaration.setSecurityOrCustomsTariffName("保证金");
					}
					if (pickupDeliveryOrder.getSecurityOrCustomsTariff().equals("2")) {
						importCustomsDeclaration.setSecurityOrCustomsTariffName("关税");
					}
				}

				// 主运单号
				importCustomsDeclaration.setMainWayBill(pickupDeliveryOrder.getMainWayBill());
				// 分运单号
				importCustomsDeclaration.setHouseWayBill(pickupDeliveryOrder.getHouseWayBill());
				// 合同编号
				importCustomsDeclaration.setContractNumber(pickupDeliveryOrder.getContractNumber());
				// 运输方式代码
				importCustomsDeclaration.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
				// 交货方式
				importCustomsDeclaration.setDeliveryType(pickupDeliveryOrder.getDeliveryType());
				// 贸易方式
				importCustomsDeclaration.setTradeMethods(pickupDeliveryOrder.getTradeMethods());
				// 报关代理商
				importCustomsDeclaration.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
			}
			
			importCustomsDeclarationList.add(importCustomsDeclaration);
		}
		return importCustomsDeclarationList;
	}
	
	@Override
	public ImportCustomsDeclaration saveImportCustomsDeclaration(ImportCustomsDeclaration importCustomsDeclaration) {
		Session session = getSession();
		importCustomsDeclaration.setCreateBy(GwtActionHelper.getCurrUser());
		importCustomsDeclaration.setCreateDate(new Date());
		// 保存报关信息
		session.saveOrUpdate(importCustomsDeclaration);

		List<ImportCustomsDeclarationItems> importCustomsDeclarationItemsList = importCustomsDeclaration.getImportCustomsDeclarationItems();
		// 保存报关明细信息
		for (int i = 0; i < importCustomsDeclarationItemsList.size(); i++) {
			importCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			importCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ImportCustomsDeclarationItems)importCustomsDeclarationItemsList.get(i));
		}

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "deliveryType = :deliveryType, ";
		strHql = strHql + "transportationCode  = :transportationCode, ";
		strHql = strHql + "tradeMethods  = :tradeMethods, ";
		strHql = strHql + "clearanceAgent = :clearanceAgent, ";
		strHql = strHql + "mainWayBill = :mainWayBill, ";
		strHql = strHql + "houseWayBill = :houseWayBill ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("deliveryType", importCustomsDeclaration.getDeliveryType());
		pickupDeliveryQuery.setString("transportationCode", importCustomsDeclaration.getTransportationCode());
		pickupDeliveryQuery.setString("tradeMethods", importCustomsDeclaration.getTradeMethods());
		pickupDeliveryQuery.setString("clearanceAgent", importCustomsDeclaration.getClearanceAgent());
		pickupDeliveryQuery.setString("mainWayBill", importCustomsDeclaration.getMainWayBill());
		pickupDeliveryQuery.setString("houseWayBill", importCustomsDeclaration.getHouseWayBill());
		pickupDeliveryQuery.setString("orderID", importCustomsDeclaration.getOrderID());
		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return importCustomsDeclaration;
	}
	
	/**
	 * 设置清关工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '5' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}