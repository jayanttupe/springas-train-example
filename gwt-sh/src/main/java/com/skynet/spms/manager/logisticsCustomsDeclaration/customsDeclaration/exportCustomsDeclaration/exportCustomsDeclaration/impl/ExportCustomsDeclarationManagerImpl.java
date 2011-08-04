/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.impl;

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
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ExportCustomsDeclarationManagerImpl extends CommonManagerImpl<ExportCustomsDeclaration> implements ExportCustomsDeclarationManager{

	@Override
	public List<ExportCustomsDeclaration> getExportCustomsDeclaration(Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from ExportCustomsDeclaration a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
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
		List<ExportCustomsDeclaration> exportCustomsDeclarationList = new ArrayList<ExportCustomsDeclaration>();
		for (Object[] objects : result)
		{
			ExportCustomsDeclaration exportCustomsDeclaration = new ExportCustomsDeclaration();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 订舱信息
			if (objects[0] != null)
				exportCustomsDeclaration = (ExportCustomsDeclaration)objects[0];
			// 提发货指令信息
			if (objects[1] != null) {
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				if (pickupDeliveryOrder.getSecurityOrCustomsTariff().equals("1")) {
					exportCustomsDeclaration.setSecurityOrCustomsTariffName("保证金");
				}
				if (pickupDeliveryOrder.getSecurityOrCustomsTariff().equals("2")) {
					exportCustomsDeclaration.setSecurityOrCustomsTariffName("关税");
				}

				// 主运单号
				exportCustomsDeclaration.setMainWayBill(pickupDeliveryOrder.getMainWayBill());
				// 分运单号
				exportCustomsDeclaration.setHouseWayBill(pickupDeliveryOrder.getHouseWayBill());
				// 合同编号
				exportCustomsDeclaration.setContractNumber(pickupDeliveryOrder.getContractNumber());
				// 运输方式代码
				exportCustomsDeclaration.setTransportationCode(pickupDeliveryOrder.getTransportationCode());
				// 交货方式
				exportCustomsDeclaration.setDeliveryType(pickupDeliveryOrder.getDeliveryType());
				// 贸易方式
				exportCustomsDeclaration.setTradeMethods(pickupDeliveryOrder.getTradeMethods());
				// 报关代理商
				exportCustomsDeclaration.setClearanceAgent(pickupDeliveryOrder.getClearanceAgent());
			}
			
			exportCustomsDeclarationList.add(exportCustomsDeclaration);
		}
		return exportCustomsDeclarationList;
	}
	@Override
	public ExportCustomsDeclaration saveExportCustomsDeclaration(ExportCustomsDeclaration exportCustomsDeclaration) {
		Session session = getSession();
		exportCustomsDeclaration.setCreateBy(GwtActionHelper.getCurrUser());
		exportCustomsDeclaration.setCreateDate(new Date());
		// 保存报关信息
		session.saveOrUpdate(exportCustomsDeclaration);

		List<ExportCustomsDeclarationItems> exportCustomsDeclarationItemsList = exportCustomsDeclaration.getExportCustomsDeclarationItems();
		// 保存报关明细信息
		for (int i = 0; i < exportCustomsDeclarationItemsList.size(); i++)
		{
			exportCustomsDeclarationItemsList.get(i).setCreateBy(GwtActionHelper.getCurrUser());
			exportCustomsDeclarationItemsList.get(i).setCreateDate(new Date());
			session.saveOrUpdate((ExportCustomsDeclarationItems)exportCustomsDeclarationItemsList.get(i));
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
		pickupDeliveryQuery.setString("deliveryType", exportCustomsDeclaration.getDeliveryType());
		pickupDeliveryQuery.setString("transportationCode", exportCustomsDeclaration.getTransportationCode());
		pickupDeliveryQuery.setString("tradeMethods", exportCustomsDeclaration.getTradeMethods());
		pickupDeliveryQuery.setString("clearanceAgent", exportCustomsDeclaration.getClearanceAgent());
		pickupDeliveryQuery.setString("mainWayBill", exportCustomsDeclaration.getMainWayBill());
		pickupDeliveryQuery.setString("houseWayBill", exportCustomsDeclaration.getHouseWayBill());
		pickupDeliveryQuery.setString("orderID", exportCustomsDeclaration.getOrderID());
		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return exportCustomsDeclaration;
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
