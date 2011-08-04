package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignManager;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItemsManager;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclaration;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetails;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetails;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJob;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssign;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;
import com.skynet.spms.service.UUIDGeneral;
import com.skynet.spms.tools.ConvertCodeName;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PickupDeliveryTaskAssignManagerImpl extends CommonManagerImpl<PickupDeliveryTaskAssign> implements PickupDeliveryTaskAssignManager{

	@Autowired
	private PickupDeliveryVanningManager pickupDeliveryVanningManager;
	
	@Autowired
	private PickupDeliveryVanningItemsManager pickupDeliveryVanningItemsManager;

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<PickupDeliveryTaskAssign> getPickupDeliveryTaskAssign(Map values, int startRow, int endRow)
	{
		List<PickupDeliveryTaskAssign> newPickupTaskAssignList = new ArrayList<PickupDeliveryTaskAssign>();

		for (int i = 0; i < 6; i++)
		{
			PickupDeliveryTaskAssign newPickupTaskAssign = new PickupDeliveryTaskAssign();
			if (i == 0) {
				// 订舱
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.booking);	
			} else if (i == 1) {
				// 起运
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.shipping);
			} else if (i == 2) {
				// 取货
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.takeDeliveryOf);
			} else if (i == 3) {
				// 到达
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.arrivalOfGoods);
			} else if (i == 4) {
				// 清关
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.customsClearance);
			} else if (i == 5) {
				// 交货
				newPickupTaskAssign.setWorkType(LogisticsTaskJobType.deliverTheGoods);
			}
			
			newPickupTaskAssign.setWorkStatus("1");
			newPickupTaskAssign.setWorkStatusName(ConvertCodeName.GetWorkStatus("1"));
			newPickupTaskAssignList.add(newPickupTaskAssign);
		}
		
		Criteria criteria= getSession().createCriteria(PickupDeliveryTaskAssign.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.addOrder(Order.asc("workType"));
		if (values != null && values.containsKey("orderID")) {
    		criteria.add(Restrictions.eq("orderID", values.get("orderID").toString()));
		}

		List<PickupDeliveryTaskAssign> deliveryTaskAssignList = (List<PickupDeliveryTaskAssign>)criteria.list();
		for (int i = 0; i < deliveryTaskAssignList.size(); i++)
		{
			PickupDeliveryTaskAssign curPickupTaskAssign = (PickupDeliveryTaskAssign)deliveryTaskAssignList.get(i);
			LogisticsTaskJobType workType = curPickupTaskAssign.getWorkType();

			PickupDeliveryTaskAssign pickupTaskAssign = null;
			if (workType == LogisticsTaskJobType.booking) {
				// 订舱
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(0);	
			} else if (workType == LogisticsTaskJobType.shipping) {
				// 起运
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(1);
			} else if (workType == LogisticsTaskJobType.takeDeliveryOf) {
				// 取货
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(2);
			} else if (workType == LogisticsTaskJobType.arrivalOfGoods) {
				// 到达
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(3);
			} else if (workType == LogisticsTaskJobType.customsClearance) {
				// 清关
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(4);
			} else if (workType == LogisticsTaskJobType.deliverTheGoods) {
				// 交货
				pickupTaskAssign = (PickupDeliveryTaskAssign)newPickupTaskAssignList.get(5);
			}
			// ID
			pickupTaskAssign.setId(curPickupTaskAssign.getId());
			// Version
			pickupTaskAssign.setVersion(curPickupTaskAssign.getVersion());
			// LockVersion
			pickupTaskAssign.setLockVersion(curPickupTaskAssign.getLockVersion());
			// 指令ID
			pickupTaskAssign.setOrderID(curPickupTaskAssign.getOrderID());
			// 工作类别(订舱/取货/起运/到达/清关/交货)
			pickupTaskAssign.setWorkType(curPickupTaskAssign.getWorkType());
			// 任务开始时间
			pickupTaskAssign.setTaskStartDate(curPickupTaskAssign.getTaskStartDate());
			// 任务结束时间
			pickupTaskAssign.setTaskEndDate(curPickupTaskAssign.getTaskEndDate());
			// 工作状态
			pickupTaskAssign.setWorkStatus(curPickupTaskAssign.getWorkStatus());
			// 工作状态名称
			pickupTaskAssign.setWorkStatusName(ConvertCodeName.GetWorkStatus(curPickupTaskAssign.getWorkStatus()));
			// 负责人员
			pickupTaskAssign.setWorker(curPickupTaskAssign.getWorker());
			// 备注
			pickupTaskAssign.setMemo(curPickupTaskAssign.getMemo());
		}

		return newPickupTaskAssignList;
	}
	
	public void savePickupDeliveryTaskAssign(List<PickupDeliveryTaskAssign> newPickupDeliveryTaskAssignList)
	{
		Session session = getSession();
		
		for (int i = 0; i < newPickupDeliveryTaskAssignList.size(); i++) {
			PickupDeliveryTaskAssign pickupTaskAssign = newPickupDeliveryTaskAssignList.get(i);
			// 指令ID
			String orderID = pickupTaskAssign.getOrderID();
			// 指令编号
			String orderNumber = pickupTaskAssign.getOrderNumber();
			// 物流任务编号
			String logisticsTasksNumber = pickupTaskAssign.getLogisticsTasksNumber();
			// 工作类别(1:订舱/2:取货/3:起运/4:到达/5:清关/6:交货)
			LogisticsTaskJobType workType = pickupTaskAssign.getWorkType();
			// 工作状态
			String workStatus = pickupTaskAssign.getWorkStatus();
			// 负责人员
			String worker = pickupTaskAssign.getWorker();
			
			// 工作状态已经处理完了的情况，不再生成工作数据
			if (workStatus.equals("3")) {
				continue;
			}

			pickupTaskAssign.setCreateBy(GwtActionHelper.getCurrUser());
			pickupTaskAssign.setCreateDate(new Date());
			pickupTaskAssign.setWorkStatus("2");
			// 生成分配工作记录
			session.saveOrUpdate(pickupTaskAssign);

			// 工作状态不是未处理的情况，不再生成工作数据
			if (!workStatus.equals("1") || worker == null || worker.equals("")) {
				continue;
			}
			
			if (workType == LogisticsTaskJobType.booking) {
				Map vanningMap = new HashMap();
				vanningMap.put("orderID", orderID);
				// 取得当前提发货指令下所有箱明细数据
				List<PickupDeliveryVanning> pickupDeliveryVanningList = pickupDeliveryVanningManager.getPickupDeliveryVanning(vanningMap, 0, -1);
				
				// 生成订舱工作数据
				CreateBookingJob(orderID, orderNumber, logisticsTasksNumber, session, pickupDeliveryVanningList);
			} else if (workType == LogisticsTaskJobType.takeDeliveryOf) {
				// 生成取货工作数据
				CreateTakeDeliveryOfJob(orderID, orderNumber, logisticsTasksNumber, session);
			} else if (workType == LogisticsTaskJobType.shipping) {
				Map vanningMap = new HashMap();
				vanningMap.put("orderID", orderID);
				// 取得当前提发货指令下所有箱明细数据
				List<PickupDeliveryVanning> pickupDeliveryVanningList = pickupDeliveryVanningManager.getPickupDeliveryVanning(vanningMap, 0, -1);
				
				// 生成起运工作数据
				CreateShippingJob(orderID, orderNumber, logisticsTasksNumber, session, pickupDeliveryVanningList);
			} else if (workType == LogisticsTaskJobType.arrivalOfGoods) {
				// 生成到达工作数据
				CreateArrivalOfGoodsJob(orderID, orderNumber, logisticsTasksNumber, session);
			} else if (workType == LogisticsTaskJobType.customsClearance) {
				Map vanningItemsMap = new HashMap();
				vanningItemsMap.put("orderID", orderID);
				// 取得当前提发货指令下所有箱明细数据
				List<PickupDeliveryVanningItems> pickupDeliveryVanningItemsList =
					pickupDeliveryVanningItemsManager.getPickupDeliveryVanningItems(vanningItemsMap, 0, -1);
				
				// 生成清关工作数据
				CreateCustomsJob(orderID, orderNumber, session, pickupDeliveryVanningItemsList, pickupTaskAssign.getCustomsDeclarationType());
			} else if (workType == LogisticsTaskJobType.deliverTheGoods) {
				// 生成交货工作数据
				CreateDeliverTheGoodsJob(orderID, orderNumber, logisticsTasksNumber, session);
			}
		}
	}
	
	/**
	 * 生成订舱工作数据
	 */
	private void CreateBookingJob(String orderID
			, String orderNumber
			, String logisticsTasksNumber
			, Session session
			, List<PickupDeliveryVanning> pickupDeliveryVanningList)
	{
		BookingJob bookingJob = new BookingJob();

		// 指令ID
		bookingJob.setOrderID(orderID);
		// 指令编号
		bookingJob.setOrderNumber(orderNumber);
		// 物流任务编号
		bookingJob.setLogisticsTasksNumber(logisticsTasksNumber);
		// 取得下一个编号
		String curNO = uUIDGeneral.getSequence("BOK");
		// 订舱工作编号
		bookingJob.setBookingJobNumber(curNO);
		// 订舱单状态
		bookingJob.setBookingStatus("2");
		bookingJob.setCreateBy(GwtActionHelper.getCurrUser());
		bookingJob.setCreateDate(new Date());
		session.saveOrUpdate(bookingJob);
		
		for (int i = 0; i < pickupDeliveryVanningList.size(); i++)
		{
			BookingJobDetails bookingJobDetails = new BookingJobDetails();
			PickupDeliveryVanning pickupDeliveryVanning = (PickupDeliveryVanning)pickupDeliveryVanningList.get(i);

			// 订舱工作ID
			bookingJobDetails.setBookingJobID(bookingJob.getId());
			// 指令ID
			bookingJobDetails.setOrderID(pickupDeliveryVanning.getOrderID());
			// 箱号
			bookingJobDetails.setPacakgeNumber(pickupDeliveryVanning.getPacakgeNumber());
			// 货物箱数
			bookingJobDetails.setBillOfLadingContainerCount(pickupDeliveryVanning.getBillOfLadingContainerCount());
			// 货物重量(千克)
			bookingJobDetails.setBillOfLadingWeight(pickupDeliveryVanning.getBillOfLadingWeight());
			// 包装尺寸(体积)
			bookingJobDetails.setContainerSizeandWeight(pickupDeliveryVanning.getContainerSizeandWeight());
			// 危险品
			bookingJobDetails.setDangerousGoods(pickupDeliveryVanning.getDangerousGoods());
			// 描述
			bookingJobDetails.setDescription(pickupDeliveryVanning.getDescription());
			// 货物性质
			bookingJobDetails.setGoodsNature(pickupDeliveryVanning.getGoodsNature());
			// 装箱单号
			bookingJobDetails.setPackingListNumber(pickupDeliveryVanning.getPackingListNumber());
			// 特殊需求
			bookingJobDetails.setSpecialRequirements(pickupDeliveryVanning.getSpecialRequirements());
			bookingJobDetails.setCreateBy(GwtActionHelper.getCurrUser());
			bookingJobDetails.setCreateDate(new Date());
			session.saveOrUpdate(bookingJobDetails);
		}
	}
	
	/**
	 * 生成取货工作数据
	 */
	private void CreateTakeDeliveryOfJob(String orderID
			, String orderNumber
			, String logisticsTasksNumber
			, Session session)
	{
		TakeDeliveryOfJob takeDeliveryOfJob = new TakeDeliveryOfJob();

		// 指令ID
		takeDeliveryOfJob.setOrderID(orderID);
		// 指令编号
		takeDeliveryOfJob.setOrderNumber(orderNumber);
		// 物流任务编号
		takeDeliveryOfJob.setLogisticsTasksNumber(logisticsTasksNumber);
		// 取得下一个编号
		String curNO = uUIDGeneral.getSequence("TDL");
		// 取货工作编号
		takeDeliveryOfJob.setTakeDeliveryNumber(curNO);
		// 状态
		takeDeliveryOfJob.setStatus("2");
		takeDeliveryOfJob.setCreateBy(GwtActionHelper.getCurrUser());
		takeDeliveryOfJob.setCreateDate(new Date());
		session.saveOrUpdate(takeDeliveryOfJob);
	}
	
	/**
	 * 生成起运工作数据
	 */
	private void CreateShippingJob(String orderID
			, String orderNumber
			, String logisticsTasksNumber
			, Session session
			, List<PickupDeliveryVanning> pickupDeliveryVanningList)
	{
		ShippingJob shippingJob = new ShippingJob();

		// 指令ID
		shippingJob.setOrderID(orderID);
		// 指令编号
		shippingJob.setOrderNumber(orderNumber);
		// 物流任务编号
		shippingJob.setLogisticsTasksNumber(logisticsTasksNumber);
		// 取得下一个编号
		String curNO = uUIDGeneral.getSequence("SHP");
		// 起运工作编号
		shippingJob.setShippingJobNumber(curNO);
		// 状态
		shippingJob.setStatus("2");
		shippingJob.setCreateBy(GwtActionHelper.getCurrUser());
		shippingJob.setCreateDate(new Date());
		session.saveOrUpdate(shippingJob);
		
		for (int i = 0; i < pickupDeliveryVanningList.size(); i++)
		{
			ShippingJobDetails shippingJobDetails = new ShippingJobDetails();
			PickupDeliveryVanning pickupDeliveryVanning = (PickupDeliveryVanning)pickupDeliveryVanningList.get(i);

			// 订舱工作ID
			shippingJobDetails.setShippingJobID(shippingJob.getId());
			// 指令ID
			shippingJobDetails.setOrderID(pickupDeliveryVanning.getOrderID());
			// 箱号
			shippingJobDetails.setPacakgeNumber(pickupDeliveryVanning.getPacakgeNumber());
			// 货物箱数
			shippingJobDetails.setBillOfLadingContainerCount(pickupDeliveryVanning.getBillOfLadingContainerCount());
			// 货物重量(千克)
			shippingJobDetails.setBillOfLadingWeight(pickupDeliveryVanning.getBillOfLadingWeight());
			// 包装尺寸(体积)
			shippingJobDetails.setContainerSizeandWeight(pickupDeliveryVanning.getContainerSizeandWeight());
			// 危险品
			shippingJobDetails.setDangerousGoods(pickupDeliveryVanning.getDangerousGoods());
			// 描述
			shippingJobDetails.setDescription(pickupDeliveryVanning.getDescription());
			// 货物性质
			shippingJobDetails.setGoodsNature(pickupDeliveryVanning.getGoodsNature());
			// 装箱单号
			shippingJobDetails.setPackingListNumber(pickupDeliveryVanning.getPackingListNumber());
			// 特殊需求
			shippingJobDetails.setSpecialRequirements(pickupDeliveryVanning.getSpecialRequirements());
			shippingJobDetails.setCreateBy(GwtActionHelper.getCurrUser());
			shippingJobDetails.setCreateDate(new Date());
			session.saveOrUpdate(shippingJobDetails);
		}
	}
	
	/**
	 * 生成到达工作数据
	 */
	private void CreateArrivalOfGoodsJob(String orderID
			, String orderNumber
			, String logisticsTasksNumber
			, Session session)
	{
		ArrivalOfGoodsJob arrivalOfGoodsJob = new ArrivalOfGoodsJob();

		// 指令ID
		arrivalOfGoodsJob.setOrderID(orderID);
		// 指令编号
		arrivalOfGoodsJob.setOrderNumber(orderNumber);
		// 物流任务编号
		arrivalOfGoodsJob.setLogisticsTasksNumber(logisticsTasksNumber);
		// 取得下一个编号
		String curNO = uUIDGeneral.getSequence("ARV");
		// 到达工作编号
		arrivalOfGoodsJob.setArrivalOfGoodsNumber(curNO);
		// 状态
		arrivalOfGoodsJob.setStatus("2");
		arrivalOfGoodsJob.setCreateBy(GwtActionHelper.getCurrUser());
		arrivalOfGoodsJob.setCreateDate(new Date());
		session.saveOrUpdate(arrivalOfGoodsJob);
	}
	
	/**
	 * 生成清关工作数据(进出口)
	 */
	private void CreateCustomsJob(String orderID
			, String orderNumber
			, Session session
			, List<PickupDeliveryVanningItems> pickupDeliveryVanningItemsList
			, String customsDeclarationType)
	{
		if (customsDeclarationType.equals("2")) {
			// 生成进口报送数据
			ImportCustomsDeclaration importCustomsDeclaration = new ImportCustomsDeclaration();
			// 指令ID
			importCustomsDeclaration.setOrderID(orderID);
			// 指令编号
			importCustomsDeclaration.setOrderNumber(orderNumber);
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("IPT");
			// 预录入编号
			importCustomsDeclaration.setPreEntryNumber(curNO);
			importCustomsDeclaration.setCreateBy(GwtActionHelper.getCurrUser());
			importCustomsDeclaration.setCreateDate(new Date());
			session.saveOrUpdate(importCustomsDeclaration);

			for (int i = 0; i < pickupDeliveryVanningItemsList.size(); i++)
			{
				ImportCustomsDeclarationItems importCustomsDeclarationItems = new ImportCustomsDeclarationItems();
				PickupDeliveryVanningItems pickupDeliveryVanningItems = (PickupDeliveryVanningItems)pickupDeliveryVanningItemsList.get(i);

				// 指令ID
				importCustomsDeclarationItems.setOrderID(pickupDeliveryVanningItems.getOrderID());
				// 报关ID
				importCustomsDeclarationItems.setCustomsID(importCustomsDeclaration.getId());
				// 总价
				importCustomsDeclarationItems.setAmount(pickupDeliveryVanningItems.getTotalAmount());
				// 名称
				importCustomsDeclarationItems.setName(pickupDeliveryVanningItems.getPartName());
				// 件号
				importCustomsDeclarationItems.setPartNumber(pickupDeliveryVanningItems.getPartNumber());
				// 数量
				importCustomsDeclarationItems.setQuantity(pickupDeliveryVanningItems.getQuantity());
				// 单价
				importCustomsDeclarationItems.setUnitPrice(pickupDeliveryVanningItems.getUnitPriceAmount());
				// 计量单位
				if (pickupDeliveryVanningItems.getUnit() != null) {
					importCustomsDeclarationItems.setUnitOfMeasure(UnitOfMeasureCode.valueOf(pickupDeliveryVanningItems.getUnit()));	
				}
				importCustomsDeclarationItems.setCreateBy(GwtActionHelper.getCurrUser());
				importCustomsDeclarationItems.setCreateDate(new Date());
				session.saveOrUpdate(importCustomsDeclarationItems);
			}
		} else if (customsDeclarationType.equals("3")) {
			// 生成出口报送数据
			ExportCustomsDeclaration exportCustomsDeclaration = new ExportCustomsDeclaration();
			// 指令ID
			exportCustomsDeclaration.setOrderID(orderID);
			// 指令编号
			exportCustomsDeclaration.setOrderNumber(orderNumber);
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("EPT");
			// 预录入编号
			exportCustomsDeclaration.setPreEntryNumber(curNO);
			exportCustomsDeclaration.setCreateBy(GwtActionHelper.getCurrUser());
			exportCustomsDeclaration.setCreateDate(new Date());
			session.saveOrUpdate(exportCustomsDeclaration);

			for (int i = 0; i < pickupDeliveryVanningItemsList.size(); i++)
			{
				ExportCustomsDeclarationItems exportCustomsDeclarationItems = new ExportCustomsDeclarationItems();
				PickupDeliveryVanningItems pickupDeliveryVanningItems = (PickupDeliveryVanningItems)pickupDeliveryVanningItemsList.get(i);

				// 指令ID
				exportCustomsDeclarationItems.setOrderID(pickupDeliveryVanningItems.getOrderID());
				// 报关ID
				exportCustomsDeclarationItems.setCustomsID(exportCustomsDeclaration.getId());
				// 总价
				exportCustomsDeclarationItems.setAmount(pickupDeliveryVanningItems.getTotalAmount());
				// 名称
				exportCustomsDeclarationItems.setName(pickupDeliveryVanningItems.getPartName());
				// 件号
				exportCustomsDeclarationItems.setPartNumber(pickupDeliveryVanningItems.getPartNumber());
				// 数量
				exportCustomsDeclarationItems.setQuantity(pickupDeliveryVanningItems.getQuantity());
				// 单价
				exportCustomsDeclarationItems.setUnitPrice(pickupDeliveryVanningItems.getUnitPriceAmount());
				// 计量单位
				if (pickupDeliveryVanningItems.getUnit() != null) {
					exportCustomsDeclarationItems.setUnitOfMeasure(UnitOfMeasureCode.valueOf(pickupDeliveryVanningItems.getUnit()));	
				}
				exportCustomsDeclarationItems.setCreateBy(GwtActionHelper.getCurrUser());
				exportCustomsDeclarationItems.setCreateDate(new Date());
				session.saveOrUpdate(exportCustomsDeclarationItems);
			}
		}
	}
	
	/**
	 * 生成交货工作数据
	 */
	private void CreateDeliverTheGoodsJob(String orderID
			, String orderNumber
			, String logisticsTasksNumber
			, Session session)
	{
		DeliverTheGoodsJob deliverTheGoodsJob = new DeliverTheGoodsJob();

		// 指令ID
		deliverTheGoodsJob.setOrderID(orderID);
		// 指令编号
		deliverTheGoodsJob.setOrderNumber(orderNumber);
		// 物流任务编号
		deliverTheGoodsJob.setLogisticsTasksNumber(logisticsTasksNumber);
		// 取得下一个编号
		String curNO = uUIDGeneral.getSequence("DLV");
		// 交货工作编号
		deliverTheGoodsJob.setDeliverTheGoodsNumber(curNO);
		// 状态
		deliverTheGoodsJob.setStatus("2");
		deliverTheGoodsJob.setCreateBy(GwtActionHelper.getCurrUser());
		deliverTheGoodsJob.setCreateDate(new Date());
		session.saveOrUpdate(deliverTheGoodsJob);
	}
	
	public void publishPickupDelivery(String orderID)
	{
		String strHql = "update PickupDeliveryOrder set ";
		strHql = strHql + "isPublish = '2' ";
		strHql = strHql + ",status = '2' ";
		strHql = strHql + "where id = :orderID ";
		
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("orderID", orderID);
		// 发布提发货指令
		pickupDeliveryQuery.executeUpdate();
	}
}