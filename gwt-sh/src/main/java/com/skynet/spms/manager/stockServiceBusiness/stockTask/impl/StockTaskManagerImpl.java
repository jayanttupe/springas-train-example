package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.repairCodeType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.RepairCodeTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockCheckTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class StockTaskManagerImpl extends CommonManagerImpl<StockTask>
		implements StockTaskManager {

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockTask> getStockTaskByType(TaskType type) {
		Criteria criteria = getSession().createCriteria(StockTask.class);

		criteria.add(Restrictions.eq("taskType",type));
		criteria.addOrder(Order.desc("taskDate"));

		criteria.setMaxResults(20);

		List<StockTask> list=new ArrayList<StockTask>();
		
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND,0);
		for(int i=0;i<10;i++){
			StockTask task=new StockTask();

			cal.add(Calendar.MINUTE, i);
			task.setActionDate(cal.getTime());
			task.setActionBy("user "+i);
			task.setTaskBy("task by "+i);
			task.setTaskDate(cal.getTime());
			task.setTaskNo("No."+i);
			task.setTaskStatus(TaskStatus.values()[i%4]);
			task.setTaskType(TaskType.SC);
			task.setTaskSource("source "+i);
			list.add(task);
		}
		return list;
//		return criteria.list();
	}

	@Override
	public List<StockTask> getStockTask(Map values, int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(StockTask.class);
		criteria.add(Restrictions.eq("deleted", false));
		if (values.containsKey("isHandset")) {
			criteria.add(Restrictions.or(Restrictions.eq("taskStatus", TaskStatus.OPN),
					Restrictions.eq("taskStatus", TaskStatus.WIP)));	
		}
		criteria.addOrder(Order.desc("taskNo"));
		Map extraKeyMap = new HashMap();
		extraKeyMap.put("isHandset", "isHandset");
		SqlHelperTool.createCriteria(values, criteria, StockTask.class, extraKeyMap);

		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}

		return criteria.list();
	}

	/**
	 * 根据标签唯一序列号查找任务明细
	 */
	@Override
	public List<StockTask> getTaskListByTagIdentifierCode(String tagIdentifierCode) {
		Session session = getSession();
		List objList = null;
		String strQuery = "";
		String strWhere = "";
		List<StockTask> stockTaskList = new ArrayList<StockTask>();
		
		// 根据标签唯一序列号查找发卡任务明细
		strQuery = strQuery + "select distinct b ";
		strQuery = strQuery + "from SendCardTaskItem a, StockTask b ";
		// 查询条件
		strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.taskID = b.id ";
		strWhere = strWhere + " and a.tagIdentifierCode = '" + tagIdentifierCode + "'";
		objList = session.createQuery(strQuery + strWhere).list();
		if (objList.size() > 0) {
			stockTaskList.add((StockTask)objList.get(0));	
		}

		// 根据标签唯一序列号查找拣货任务明细
		strQuery = "select distinct b ";
		strQuery = strQuery + "from PickingTaskItem a, StockTask b ";
		// 查询条件
		strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.taskID = b.id ";
		strWhere = strWhere + " and a.tagIdentifierCode = '" + tagIdentifierCode + "'";
		objList = session.createQuery(strQuery + strWhere).list();
		if (objList.size() > 0) {
			stockTaskList.add((StockTask)objList.get(0));	
		}

		// 根据标签唯一序列号查找上架任务明细
		strQuery = "select distinct b ";
		strQuery = strQuery + "from ShelvingTaskItem a, StockTask b ";
		// 查询条件
		strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.taskID = b.id ";
		strWhere = strWhere + " and a.tagIdentifierCode = '" + tagIdentifierCode + "'";
		objList = session.createQuery(strQuery + strWhere).list();
		if (objList.size() > 0) {
			stockTaskList.add((StockTask)objList.get(0));	
		}

		// 根据标签唯一序列号查找盘点任务明细
		strQuery = "select distinct b ";
		strQuery = strQuery + "from StockCheckTaskItem a, StockTask b ";
		// 查询条件
		strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.taskID = b.id ";
		strWhere = strWhere + " and a.tagIdentifierCode = '" + tagIdentifierCode + "'";
		objList = session.createQuery(strQuery + strWhere).list();
		if (objList.size() > 0) {
			stockTaskList.add((StockTask)objList.get(0));
		}
		
		return stockTaskList;
	}
	/**
	 * 更新任务状态
	 */
	@Override
	public void updateStockTaskStatus(StockTask stockTask) {
		// 更新任务状态为处理中
		String strHql = "update StockTask set ";
		strHql = strHql + "taskStatus = '" + TaskStatus.WIP + "'";
		strHql = strHql + ",actionBy = :actionBy ";
		strHql = strHql + ",actionDate = :actionDate ";
		strHql = strHql + "where taskNo = :taskNo ";
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("taskNo", stockTask.getTaskNo());
		pickupDeliveryQuery.setString("actionBy", stockTask.getActionBy());
		pickupDeliveryQuery.setDate("actionDate", stockTask.getActionDate());
		pickupDeliveryQuery.executeUpdate();
	}
	
	/**
	 * 生成发卡任务
	 */
	@Override
	public void insertSendCardTask(String[] inStockRecordIDs) {
		Session session = getSession();

		// 生成主任务信息
		String curNO = uUIDGeneral.getSequence("SP-SC");
		StockTask stockTask = createStockTask(curNO, TaskType.SC, "", session);
		
		for (int i = 0; i < inStockRecordIDs.length; i++) {
			String strQuery = "select ";
			// 检索项目
			// ID
			strQuery = strQuery + "a.ID, ";
			// 推荐货位
			strQuery = strQuery + "a.REC_CARGO_SPACE_NUM, ";
			// 件号
			strQuery = strQuery + "a.PART_NUMBER, ";
			// 序号/批号
			strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
			// 数量
			strQuery = strQuery + "a.QUANTITY ";
			
			// 来源表
			strQuery = strQuery + "from SPMS_IN_STOCK_RECORD a ";
			
			// 查询条件
			String strWhere = "where a.IS_DELETED = '0' ";
			strWhere = strWhere + "and a.ID = '" + inStockRecordIDs[i] + "'";

			List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
			if (result != null && result.size() > 0) {
				// 更新为已发卡状态
				String strHql = "update InStockRecord set ";
				strHql = strHql + "sendCardStatus = '" + SendStatus.Send + "'";
				strHql = strHql + "where id = :InStockRecordID ";
				Query pickupDeliveryQuery = getSession().createQuery(strHql);
				pickupDeliveryQuery.setString("InStockRecordID", inStockRecordIDs[i]);
				pickupDeliveryQuery.executeUpdate();

				Object[] object = result.get(0);
				// 生成上架任务明细信息
				SendCardTaskItem sendCardTaskItem = new SendCardTaskItem();
				// 任务ID
				sendCardTaskItem.setTaskID(stockTask.getId());
				// 操作状态
				sendCardTaskItem.setOperationStatus(TaskItemStatus.OPN);
				// 条码标签唯一编号
				if (object[0] != null)
					sendCardTaskItem.setBarcodeTagUUID(object[0].toString());
				// 推荐货位
				if (object[1] != null)
					sendCardTaskItem.setRecCargoSpaceNumber(object[1].toString());
				// 件号
				if (object[2] != null)
					sendCardTaskItem.setPartNumber(object[2].toString());
				// 序号/批号
				if (object[3] != null)
					sendCardTaskItem.setPartSerialNumber(object[3].toString());
				// 数量
				if (object[4] != null)
					sendCardTaskItem.setQuantity(Double.valueOf(object[4].toString()));
				sendCardTaskItem.setCreateBy(GwtActionHelper.getCurrUser());
				sendCardTaskItem.setCreateDate(new Date());
				session.saveOrUpdate(sendCardTaskItem);
			}
		}
	}
	
	/**
	 * 生成上架任务
	 */
	@Override
	public void insertShelveTask(String[] inStockRecordIDs) {
		Session session = getSession();

		// 生成主任务信息
		String curNO = uUIDGeneral.getSequence("SP-SH");
		StockTask stockTask = createStockTask(curNO, TaskType.SH, "", session);
		
		for (int i = 0; i < inStockRecordIDs.length; i++) {
			// 检索项目
			String strQuery = "select ";
			// 推荐货位
			strQuery = strQuery + "a.REC_CARGO_SPACE_NUM, ";
			// 件号
			strQuery = strQuery + "a.PART_NUMBER, ";
			// 序号/批号
			strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
			// 数量
			strQuery = strQuery + "a.QUANTITY, ";
			// 条码标签唯一编号
			strQuery = strQuery + "b.BARCODE_TAG_UUID, ";
			// RFID标签唯一编号
			strQuery = strQuery + "b.RFID_TAG_UUID ";
			
			// 来源表
			strQuery = strQuery + "from SPMS_IN_STOCK_RECORD a ";
			strQuery = strQuery + "left join SPMS_PART_ENTITY b ";
			strQuery = strQuery + "on a.PART_NUMBER = b.PART_NUMBER ";
			strQuery = strQuery + "and a.PART_SERIAL_NUMBER = b.PART_SERIAL_NUMBER ";
			strQuery = strQuery + "and b.IS_DELETED = 0 ";
			
			// 查询条件
			String strWhere = "where a.IS_DELETED = 0 ";
			strWhere = strWhere + "and a.ID = '" + inStockRecordIDs[i] + "'";

			List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
			if (result != null && result.size() > 0) {
				// 更新为已下达状态
				String strHql = "update InStockRecord set ";
				strHql = strHql + "shelvingStatus = '" + SendStatus.Send + "'";
				strHql = strHql + "where id = :InStockRecordID ";
				Query pickupDeliveryQuery = getSession().createQuery(strHql);
				pickupDeliveryQuery.setString("InStockRecordID", inStockRecordIDs[i]);
				pickupDeliveryQuery.executeUpdate();

				Object[] object = result.get(0);
				// 生成上架任务明细信息
				ShelvingTaskItem shelvingTaskItem = new ShelvingTaskItem();
				// 任务ID
				shelvingTaskItem.setTaskID(stockTask.getId());
				// 操作状态
				shelvingTaskItem.setOperationStatus(TaskItemStatus.OPN);
				// 推荐货位
				if (object[0] != null)
					shelvingTaskItem.setRecCargoSpaceNumber(object[0].toString());
				// 件号
				if (object[1] != null)
					shelvingTaskItem.setPartNumber(object[1].toString());
				// 序号/批号
				if (object[2] != null)
					shelvingTaskItem.setPartSerialNumber(object[2].toString());
				// 数量
				if (object[3] != null)
					shelvingTaskItem.setQuantity(Double.valueOf(object[3].toString()));
				// 条码标签唯一编号
				if (object[4] != null)
					shelvingTaskItem.setBarcodeTagUUID(object[4].toString());
				// RFID标签唯一编号
				if (object[5] != null)
					shelvingTaskItem.setTagIdentifierCode(object[5].toString());
				shelvingTaskItem.setCreateBy(GwtActionHelper.getCurrUser());
				shelvingTaskItem.setCreateDate(new Date());
				session.saveOrUpdate(shelvingTaskItem);
			}
		}
	}
	
	/**
	 * 生成拣货任务
	 */
	@Override
	public void insertPickingTask(String[] pickinglistIDs) {
		Session session = getSession();
		
		for (int i = 0; i < pickinglistIDs.length; i++) {
			// 生成主任务信息
			String curNO = uUIDGeneral.getSequence("SP-PK");
			StockTask stockTask = createStockTask(curNO, TaskType.PK, pickinglistIDs[i], session);
			
			// 更新为已下达状态
			String strHql = "update PickingList set ";
			strHql = strHql + "status = '" + OutStockStatus.OrderPick + "' ";
			strHql = strHql + "where id = :PickingListID ";
			Query updateQuery = getSession().createQuery(strHql);
			updateQuery.setString("PickingListID", pickinglistIDs[i]);
			updateQuery.executeUpdate();

			// 检索项目
			String strQuery = "select ";
			// 货位编号
			strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
			// 件号
			strQuery = strQuery + "a.PART_NUMBER, ";
			// 序号/批号
			strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
			// 数量
			strQuery = strQuery + "a.SEND_QTY, ";
			// 条码标签唯一编号
			strQuery = strQuery + "b.BARCODE_TAG_UUID, ";
			// RFID标签唯一编号
			strQuery = strQuery + "b.RFID_TAG_UUID ";
			// 来源表
			strQuery = strQuery + "from SPMS_PICKING_LIST_PART_ITEMS a ";
			strQuery = strQuery + "left join SPMS_PART_ENTITY b ";
			strQuery = strQuery + "on a.PART_NUMBER = b.PART_NUMBER ";
			strQuery = strQuery + "and a.PART_SERIAL_NUMBER = b.PART_SERIAL_NUMBER ";
			strQuery = strQuery + "and b.IS_DELETED = 0 ";
			// 查询条件
			String strWhere = "where a.IS_DELETED = '0' ";
			strWhere = strWhere + "and a.PICKINGLIST_ID = '" + pickinglistIDs[i] + "'";

			List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
			for (Object[] object : result) {
				// 生成拣货任务明细信息
				PickingTaskItem pickingTaskItem = new PickingTaskItem();
				// 任务ID
				pickingTaskItem.setTaskID(stockTask.getId());
				// 操作状态
				pickingTaskItem.setOperationStatus(TaskItemStatus.OPN);
				// 货位编号
				if (object[0] != null)
					pickingTaskItem.setCargoSpaceNumber(object[0].toString());
				// 件号
				if (object[1] != null)
					pickingTaskItem.setPartNumber(object[1].toString());
				// 序号/批号
				if (object[2] != null)
					pickingTaskItem.setPartSerialNumber(object[2].toString());
				// 数量
				if (object[3] != null)
					pickingTaskItem.setQuantity(Double.valueOf(object[3].toString()));
				// 条码标签唯一编号
				if (object[4] != null)
					pickingTaskItem.setBarcodeTagUUID(object[4].toString());
				// RFID标签唯一编号
				if (object[5] != null)
					pickingTaskItem.setTagIdentifierCode(object[5].toString());
				pickingTaskItem.setCreateBy(GwtActionHelper.getCurrUser());
				pickingTaskItem.setCreateDate(new Date());
				session.saveOrUpdate(pickingTaskItem);
			}
		}
	}
	
	/**
	 * 生成装箱任务
	 */
	@Override
	public void insertPackingTask(String[] pickinglistIDs) {
		Session session = getSession();
		
		for (int i = 0; i < pickinglistIDs.length; i++) {
			// 生成主任务信息
			String curNO = uUIDGeneral.getSequence("SP-BX");
			StockTask stockTask = createStockTask(curNO, TaskType.BX, pickinglistIDs[i], session);

			// 检索项目
			String strQuery = "select ";
			// 货位编号
			strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
			// 件号
			strQuery = strQuery + "a.PART_NUMBER, ";
			// 序号/批号
			strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
			// 数量
			strQuery = strQuery + "a.SEND_QTY, ";
			// 条码标签唯一编号
			strQuery = strQuery + "b.BARCODE_TAG_UUID, ";
			// RFID标签唯一编号
			strQuery = strQuery + "b.RFID_TAG_UUID ";
			// 来源表
			strQuery = strQuery + "from SPMS_PICKING_LIST_PART_ITEMS a ";
			strQuery = strQuery + "left join SPMS_PART_ENTITY b ";
			strQuery = strQuery + "on a.PART_NUMBER = b.PART_NUMBER ";
			strQuery = strQuery + "and a.PART_SERIAL_NUMBER = b.PART_SERIAL_NUMBER ";
			strQuery = strQuery + "and b.IS_DELETED = 0 ";
			// 查询条件
			String strWhere = "where a.IS_DELETED = '0' ";
			strWhere = strWhere + "and a.PICKINGLIST_ID = '" + pickinglistIDs[i] + "'";

			List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
			for (Object[] object : result) {
				// 生成装箱任务明细信息
				PackingTaskItem packingTaskItem = new PackingTaskItem();
				// 任务ID
				packingTaskItem.setTaskID(stockTask.getId());
				// 操作状态
				packingTaskItem.setOperationStatus(TaskItemStatus.OPN);
				// 货位编号
				if (object[0] != null)
					packingTaskItem.setCargoSpaceNumber(object[0].toString());
				// 件号
				if (object[1] != null)
					packingTaskItem.setPartNumber(object[1].toString());
				// 序号/批号
				if (object[2] != null)
					packingTaskItem.setPartSerialNumber(object[2].toString());
				// 数量
				if (object[3] != null)
					packingTaskItem.setQuantity(Double.valueOf(object[3].toString()));
				// 条码标签唯一编号
				if (object[4] != null)
					packingTaskItem.setBarcodeTagUUID(object[4].toString());
				// RFID标签唯一编号
				if (object[5] != null)
					packingTaskItem.setTagIdentifierCode(object[5].toString());
				packingTaskItem.setCreateBy(GwtActionHelper.getCurrUser());
				packingTaskItem.setCreateDate(new Date());
				session.saveOrUpdate(packingTaskItem);
			}
		}
	}

	/**
	 * 生成补码任务
	 */
	@Override
	public void insertRepairCodeTask(String[] repairCodeIDs) {
		Session session = getSession();
		for(int i=0; i< repairCodeIDs.length; i++){
			RepairCode repairCode = (RepairCode)session.get(RepairCode.class, repairCodeIDs[i]);
			// 标识已下达补码任务
			repairCode.setSendStatus(SendStatus.Send);
			session.saveOrUpdate(repairCode);
			
			// 生成主任务信息
			String curNO = uUIDGeneral.getSequence("SP-" + TaskType.RC);
			StockTask stockTask = createStockTask(curNO, TaskType.RC,repairCodeIDs[i], session);
			
			Criteria criteria = null;
			if(repairCode.getRepairCodeType() == repairCodeType.spareCode){
				criteria = session.createCriteria(RepairCodePartItem.class);
				criteria.add(Restrictions.eq("repairCodeId", repairCodeIDs[i]));
				List<RepairCodePartItem> rcpiList = criteria.list();
				// 生成航材任务明细信息
				for(RepairCodePartItem item : rcpiList){
					
					RepairCodeTaskItem taskItem = new RepairCodeTaskItem();
					// 任务ID
					taskItem.setTaskID(stockTask.getId());
					// 操作状态
					taskItem.setOperationStatus(TaskItemStatus.OPN);
					// 条码标签唯一编号
					taskItem.setBarcodeTagUUID(item.getId());
					// 货位编号
					taskItem.setLocationNumber(item.getPartsInventoryRecord().getCargoSpaceNumber());
					// 件号
					taskItem.setPartNumber(item.getPartsInventoryRecord().getPartNumber());
					// 序号/批号
					taskItem.setPartSerialNumber(item.getPartsInventoryRecord().getPartSerialNumber());
					// 计量单位
					taskItem.setPartUnit(item.getPartsInventoryRecord().getUnit().toString());
					// 数量
					taskItem.setQuantity(Double.valueOf(item.getPartsInventoryRecord().getBalanceQuantity()));
					//任务明细项的创建者与日期
					taskItem.setCreateBy(GwtActionHelper.getCurrUser());
					taskItem.setCreateDate(new Date());
					
					session.saveOrUpdate(taskItem);
				}
				
				
			} else if (repairCode.getRepairCodeType() == repairCodeType.carge){
				criteria = session.createCriteria(RepairCodeCargoSpaceItem.class);
				criteria.add(Restrictions.eq("repairCodeId", repairCodeIDs[i]));
				List<RepairCodeCargoSpaceItem> rccsiList = criteria.list();
				// 生成货位任务明细信息
				for(RepairCodeCargoSpaceItem item : rccsiList){
					
					RepairCodeTaskItem taskItem = new RepairCodeTaskItem();
					// 任务ID
					taskItem.setTaskID(stockTask.getId());
					// 操作状态
					taskItem.setOperationStatus(TaskItemStatus.OPN);
					// 条码标签唯一编号
					taskItem.setBarcodeTagUUID(item.getId());
					// 货位编号
					taskItem.setLocationNumber(item.getCargoSpace().getCargoSpaceNumber());
					//任务明细项的创建者与日期
					taskItem.setCreateBy(GwtActionHelper.getCurrUser());
					taskItem.setCreateDate(new Date());
					
					session.saveOrUpdate(taskItem);
				}
			}
		}
	}
	
	/**
	 * 生成盘点任务
	 */
	@Override
	public void insertStockCountTask(String stockCheckID) {
		Session session = getSession();
		
		// 生成主任务信息
		String curNO = uUIDGeneral.getSequence("SP-ST");
		StockTask stockTask = createStockTask(curNO, TaskType.ST, "", session);
		
		// 更新为已下达状态
		String strHql = "update StockCheck set ";
		strHql = strHql + "sendStatus = '" + SendStatus.Send + "'";
		strHql = strHql + "where id = :stockCheckID ";
		Query updateQuery = getSession().createQuery(strHql);
		updateQuery.setString("stockCheckID", stockCheckID);
		updateQuery.executeUpdate();

		// 检索项目
		String strQuery = "select ";
		// 货位编号
		strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 序号/批号
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		// 数量
		strQuery = strQuery + "a.QUANTITY, ";
		// 条码标签唯一编号
		strQuery = strQuery + "c.BARCODE_TAG_UUID, ";
		// RFID标签唯一编号
		strQuery = strQuery + "c.RFID_TAG_UUID ";
		// 来源表
		strQuery = strQuery + "from SPMS_STOCK_CHECK_ITEM a ";
		strQuery = strQuery + "inner join SPMS_STOCK_CHECK b ";
		strQuery = strQuery + "on b.ID = a.STOCK_CHECK_ID ";
		strQuery = strQuery + "and b.IS_DELETED = 0 ";
		strQuery = strQuery + "left join SPMS_PART_ENTITY c ";
		strQuery = strQuery + "on a.PART_NUMBER = c.PART_NUMBER ";
		strQuery = strQuery + "and a.PART_SERIAL_NUMBER = c.PART_SERIAL_NUMBER ";
		strQuery = strQuery + "and c.IS_DELETED = 0 ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = 0 ";
		strWhere = strWhere + "and b.ID = '" + stockCheckID + "'";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
		for (Object[] object : result) {
			// 生成盘点任务明细信息
			StockCheckTaskItem stockCheckTaskItem = new StockCheckTaskItem();
			// 任务ID
			stockCheckTaskItem.setTaskID(stockTask.getId());
			// 操作状态
			stockCheckTaskItem.setOperationStatus(TaskItemStatus.OPN);
			// 货位编号
			if (object[0] != null)
				stockCheckTaskItem.setCargoSpaceNumber(object[0].toString());
			// 件号
			if (object[1] != null)
				stockCheckTaskItem.setPartNumber(object[1].toString());
			// 序号/批号
			if (object[2] != null)
				stockCheckTaskItem.setPartSerialNumber(object[2].toString());
			// 数量
			if (object[3] != null)
				stockCheckTaskItem.setQuantity(Double.valueOf(object[3].toString()));
			// 条码标签唯一编号
			if (object[4] != null)
				stockCheckTaskItem.setBarcodeTagUUID(object[4].toString());
			// RFID标签唯一编号
			if (object[5] != null)
				stockCheckTaskItem.setTagIdentifierCode(object[5].toString());
			stockCheckTaskItem.setCreateBy(GwtActionHelper.getCurrUser());
			stockCheckTaskItem.setCreateDate(new Date());
			session.saveOrUpdate(stockCheckTaskItem);
		}
	}
	
	/**
	 * 生成主任务信息
	 * @param taskNO
	 * @param taskType
	 * @param session
	 */
	private StockTask createStockTask(String taskNO, TaskType taskType, String bussinessBillNO, Session session) {
		StockTask stockTask = new StockTask();
		// 任务编号(SHL:上架/PIK:拣货/MOV:移库/SCK:盘点/5:补码/6:发卡)
		stockTask.setTaskNo(taskNO);
		// 任务类型(1:上架/2:拣货/3:移库/4:盘点/5:补码/6:发卡)
		stockTask.setTaskType(taskType);
		// 任务状态(1:待处理/2:处理中/3:结束/4:取消)
		stockTask.setTaskStatus(TaskStatus.OPN);
		// 任务来源
		stockTask.setTaskSource("SPMS");
		// 业务单据号
		stockTask.setBussinessBillNO(bussinessBillNO);
		// 任务创建人
		stockTask.setTaskBy(GwtActionHelper.getCurrUser());
		// 任务日期
		stockTask.setTaskDate(new Date());
		session.saveOrUpdate(stockTask);
		
		return stockTask;
	}
}