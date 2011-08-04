package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.tools.ConvertCodeName;

@Service
@Transactional
public class ShippingJobManagerImpl extends CommonManagerImpl<ShippingJob> implements ShippingJobManager {
	
	@Override
	public List<ShippingJob> getShippingJob(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "from ShippingJob a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
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
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.shipping + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.shippingJobNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<ShippingJob> shippingJobList = new ArrayList<ShippingJob>();
		for (Object[] objects : result)
		{
			ShippingJob shippingJob = new ShippingJob();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 起运信息
			if (objects[0] != null)
				shippingJob = (ShippingJob)objects[0];
			// 提发货指令信息
			if (objects[1] != null) {
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
				// 合同编号
				shippingJob.setContractNumber(pickupDeliveryOrder.getContractNumber());
			}
			
			shippingJob.setPickupDeliveryOrder(pickupDeliveryOrder);
			shippingJobList.add(shippingJob);
		}
		
		return shippingJobList;
	}
	
	@Override
	public ShippingJob saveShippingJob(ShippingJob shippingJob) {
		shippingJob.setCreateBy(GwtActionHelper.getCurrUser());
		shippingJob.setCreateDate(new Date());
		// 保存订舱信息
		getSession().saveOrUpdate(shippingJob);

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "tradeMethods = :tradeMethods, ";
		strHql = strHql + "forwarderPaymentType = :forwarderPaymentType, ";
		strHql = strHql + "shippingServiceType = :shippingServiceType, ";
		strHql = strHql + "transportationCode = :transportationCode, ";
		strHql = strHql + "carrierID = :carrierID, ";
		strHql = strHql + "addressOfConsignee = :addressOfConsignee, ";
		strHql = strHql + "companyOfConsignee = :companyOfConsignee, ";
		strHql = strHql + "consignee = :consignee, ";
		strHql = strHql + "mailOfConsignee = :mailOfConsignee, ";
		strHql = strHql + "faxOfConsignee = :faxOfConsignee, ";
		strHql = strHql + "consigneeMark = :consigneeMark, ";
		strHql = strHql + "shippingMark = :shippingMark, ";
		strHql = strHql + "telephoneOfConsignee = :telephoneOfConsignee, ";
		strHql = strHql + "addressOfShipper = :addressOfShipper, ";
		strHql = strHql + "companyOfShipper = :companyOfShipper, ";
		strHql = strHql + "mailOfShipper = :mailOfShipper, ";
		strHql = strHql + "faxOfShipper = :faxOfShipper, ";
		strHql = strHql + "shipper = :shipper, ";
		strHql = strHql + "telephonOfShipper = :telephonOfShipper, ";
		strHql = strHql + "internationalCurrencyCode = :internationalCurrencyCode, ";
		strHql = strHql + "mainWayBill = :mainWayBill, ";
		strHql = strHql + "houseWayBill = :houseWayBill ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		PickupDeliveryOrder pickupDeliveryOrder = shippingJob.getPickupDeliveryOrder();
		pickupDeliveryQuery.setString("tradeMethods", pickupDeliveryOrder.getTradeMethods());
		pickupDeliveryQuery.setString("forwarderPaymentType", pickupDeliveryOrder.getForwarderPaymentType());
		pickupDeliveryQuery.setString("shippingServiceType", pickupDeliveryOrder.getShippingServiceType());
		pickupDeliveryQuery.setString("transportationCode", pickupDeliveryOrder.getTransportationCode());
		pickupDeliveryQuery.setString("carrierID", pickupDeliveryOrder.getCarrierID());
		pickupDeliveryQuery.setString("addressOfConsignee", pickupDeliveryOrder.getAddressOfConsignee());
		pickupDeliveryQuery.setString("companyOfConsignee", pickupDeliveryOrder.getCompanyOfConsignee());
		pickupDeliveryQuery.setString("consignee", pickupDeliveryOrder.getConsignee());
		pickupDeliveryQuery.setString("mailOfConsignee", pickupDeliveryOrder.getMailOfConsignee());
		pickupDeliveryQuery.setString("faxOfConsignee", pickupDeliveryOrder.getFaxOfConsignee());
		pickupDeliveryQuery.setString("consigneeMark", pickupDeliveryOrder.getConsigneeMark());
		pickupDeliveryQuery.setString("shippingMark", pickupDeliveryOrder.getShippingMark());
		pickupDeliveryQuery.setString("telephoneOfConsignee", pickupDeliveryOrder.getTelephoneOfConsignee());
		pickupDeliveryQuery.setString("addressOfShipper", pickupDeliveryOrder.getAddressOfShipper());
		pickupDeliveryQuery.setString("companyOfShipper", pickupDeliveryOrder.getCompanyOfShipper());
		pickupDeliveryQuery.setString("mailOfShipper", pickupDeliveryOrder.getMailOfShipper());
		pickupDeliveryQuery.setString("faxOfShipper", pickupDeliveryOrder.getFaxOfShipper());
		pickupDeliveryQuery.setString("shipper", pickupDeliveryOrder.getShipper());
		pickupDeliveryQuery.setString("telephonOfShipper", pickupDeliveryOrder.getTelephonOfShipper());
		pickupDeliveryQuery.setString("internationalCurrencyCode", pickupDeliveryOrder.getInternationalCurrencyCode());
		pickupDeliveryQuery.setString("mainWayBill", pickupDeliveryOrder.getMainWayBill());
		pickupDeliveryQuery.setString("houseWayBill", pickupDeliveryOrder.getHouseWayBill());
		pickupDeliveryQuery.setString("orderID", pickupDeliveryOrder.getId());

		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return shippingJob;
	}
	
	/**
	 * 设置起运工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '3' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}
