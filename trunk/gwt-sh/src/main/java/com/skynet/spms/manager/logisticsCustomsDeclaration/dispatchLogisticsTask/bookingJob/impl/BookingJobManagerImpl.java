package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.impl;

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
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.tools.ConvertCodeName;

@Service
@Transactional
public class BookingJobManagerImpl extends CommonManagerImpl<BookingJob> implements BookingJobManager {

	@Override
	public List<BookingJob> getBookingJob(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "from BookingJob a, PickupDeliveryOrder b, PickupDeliveryTaskAssign c ";
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
				strWhere = strWhere + " and c.workType = '" + LogisticsTaskJobType.booking + "' ";
			}
			// 从提发货任务管理列表链接过来的时候，需要查找除未分配以外所有的数据
			if (values.containsKey("orderID")) {
				strWhere = strWhere + " and a.orderID = '" + values.get("orderID").toString() + "'";
			}
		}
		// 字段排序
		String strOrder = "order by a.bookingJobNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<BookingJob> bookingJobList = new ArrayList<BookingJob>();
		for (Object[] objects : result)
		{
			BookingJob bookingJob = new BookingJob();
			PickupDeliveryOrder pickupDeliveryOrder = new PickupDeliveryOrder();
			// 订舱信息
			if (objects[0] != null)
				bookingJob = (BookingJob)objects[0];
			// 提发货指令信息
			if (objects[1] != null)
				pickupDeliveryOrder = (PickupDeliveryOrder)objects[1];
			
			bookingJob.setPickupDeliveryOrder(pickupDeliveryOrder);
			bookingJobList.add(bookingJob);
		}
		
		for (int i = 0; i < bookingJobList.size(); i++)
		{
			BookingJob bookingJob = (BookingJob)bookingJobList.get(i);
			bookingJob.setBookingStatus(ConvertCodeName.GetWorkStatus(bookingJob.getBookingStatus()));
		}
		return bookingJobList;
	}
	
	@Override
	public BookingJob saveBookingJob(BookingJob bookingJob) {
		bookingJob.setCreateBy(GwtActionHelper.getCurrUser());
		bookingJob.setCreateDate(new Date());

		// 保存订舱信息
		getSession().saveOrUpdate(bookingJob);

		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "carrierID = :carrierID, ";
		strHql = strHql + "clearanceAgent = :clearanceAgent, ";
		strHql = strHql + "specifiedShippingMethodCode = :specifiedShippingMethodCode, ";
		strHql = strHql + "tradeMethods = :tradeMethods, ";
		strHql = strHql + "deliveryType = :deliveryType, ";
		strHql = strHql + "transportationCode = :transportationCode, ";
		strHql = strHql + "forwarderPaymentType = :forwarderPaymentType, ";
		strHql = strHql + "shippingServiceType = :shippingServiceType, ";
		strHql = strHql + "addressOfShipper = :addressOfShipper, ";
		strHql = strHql + "companyOfShipper = :companyOfShipper, ";
		strHql = strHql + "mailOfShipper = :mailOfShipper, ";
		strHql = strHql + "faxOfShipper = :faxOfShipper, ";
		strHql = strHql + "shipper = :shipper, ";
		strHql = strHql + "telephonOfShipper = :telephonOfShipper, ";
		strHql = strHql + "addressOfConsignee = :addressOfConsignee, ";
		strHql = strHql + "companyOfConsignee = :companyOfConsignee, ";
		strHql = strHql + "consignee = :consignee, ";
		strHql = strHql + "mailOfConsignee = :mailOfConsignee, ";
		strHql = strHql + "faxOfConsignee = :faxOfConsignee, ";
		strHql = strHql + "shippingMark = :shippingMark, ";
		strHql = strHql + "consigneeMark = :consigneeMark, ";
		strHql = strHql + "telephoneOfConsignee = :telephoneOfConsignee, ";
		strHql = strHql + "deliveryDate = :deliveryDate, ";
		strHql = strHql + "shippedDate = :shippedDate, ";
		strHql = strHql + "arrivalDate = :arrivalDate, ";
		strHql = strHql + "pickupDate = :pickupDate ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		PickupDeliveryOrder pickupDeliveryOrder = bookingJob.getPickupDeliveryOrder();
		pickupDeliveryQuery.setString("carrierID", pickupDeliveryOrder.getCarrierID());
		pickupDeliveryQuery.setString("clearanceAgent", pickupDeliveryOrder.getClearanceAgent());
		pickupDeliveryQuery.setString("specifiedShippingMethodCode", pickupDeliveryOrder.getSpecifiedShippingMethodCode());
		pickupDeliveryQuery.setString("tradeMethods", pickupDeliveryOrder.getTradeMethods());
		pickupDeliveryQuery.setString("deliveryType", pickupDeliveryOrder.getDeliveryType());
		pickupDeliveryQuery.setString("transportationCode", pickupDeliveryOrder.getTransportationCode());
		pickupDeliveryQuery.setString("forwarderPaymentType", pickupDeliveryOrder.getForwarderPaymentType());
		pickupDeliveryQuery.setString("shippingServiceType", pickupDeliveryOrder.getShippingServiceType());
		pickupDeliveryQuery.setString("addressOfShipper", pickupDeliveryOrder.getAddressOfShipper());
		pickupDeliveryQuery.setString("companyOfShipper", pickupDeliveryOrder.getCompanyOfShipper());
		pickupDeliveryQuery.setString("mailOfShipper", pickupDeliveryOrder.getMailOfShipper());
		pickupDeliveryQuery.setString("faxOfShipper", pickupDeliveryOrder.getFaxOfShipper());
		pickupDeliveryQuery.setString("shipper", pickupDeliveryOrder.getShipper());
		pickupDeliveryQuery.setString("telephonOfShipper", pickupDeliveryOrder.getTelephonOfShipper());
		pickupDeliveryQuery.setString("addressOfConsignee", pickupDeliveryOrder.getAddressOfConsignee());
		pickupDeliveryQuery.setString("companyOfConsignee", pickupDeliveryOrder.getCompanyOfConsignee());
		pickupDeliveryQuery.setString("consignee", pickupDeliveryOrder.getConsignee());
		pickupDeliveryQuery.setString("mailOfConsignee", pickupDeliveryOrder.getMailOfConsignee());
		pickupDeliveryQuery.setString("faxOfConsignee", pickupDeliveryOrder.getFaxOfConsignee());
		pickupDeliveryQuery.setString("shippingMark", pickupDeliveryOrder.getShippingMark());
		pickupDeliveryQuery.setString("consigneeMark", pickupDeliveryOrder.getConsigneeMark());
		pickupDeliveryQuery.setString("telephoneOfConsignee", pickupDeliveryOrder.getTelephoneOfConsignee());
		pickupDeliveryQuery.setDate("deliveryDate", pickupDeliveryOrder.getDeliveryDate());
		pickupDeliveryQuery.setDate("shippedDate", pickupDeliveryOrder.getShippedDate());
		pickupDeliveryQuery.setDate("arrivalDate", pickupDeliveryOrder.getArrivalDate());
		pickupDeliveryQuery.setDate("pickupDate", pickupDeliveryOrder.getPickupDate());
		pickupDeliveryQuery.setString("orderID", pickupDeliveryOrder.getId());
		// 更新提发货信息
		pickupDeliveryQuery.executeUpdate();
		
		return bookingJob;
	}
	
	/**
	 * 设置订舱工作状态为已结束
	 */
	@Override
	public void setWorkStatus(String orderID) {
		String strHql = "update PickupDeliveryTaskAssign set ";
		strHql = strHql + "workStatus = '3' ";
		strHql = strHql + "where orderID = :orderID ";
		strHql = strHql + "and workType = '1' ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 更新工作状态为已结束
		pickupDeliveryQuery.executeUpdate();
	}
}
