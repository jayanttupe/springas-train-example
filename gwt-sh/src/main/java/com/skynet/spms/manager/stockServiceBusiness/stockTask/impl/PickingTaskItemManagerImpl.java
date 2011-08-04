package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PickingTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

@Service
@Transactional
public class PickingTaskItemManagerImpl extends CommonManagerImpl<PickingTaskItem>
		implements PickingTaskItemManager {

	@Autowired
	StockTaskManager stockTaskManager;
	
	@Override
	public List<PickingTaskItem> getPickingTaskItem(Map values, int startRow, int endRow) {
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.PART_NUMBER ";
		strQuery = strQuery + ",c.KEYWORD_ZH ";
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		strQuery = strQuery + ",a.QUANTITY ";
		strQuery = strQuery + ",a.TASK_ID ";
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";
		strQuery = strQuery + ",a.OPERATION_STATUS ";
		strQuery = strQuery + ",a.BARCODE_TAG_UUID ";
		strQuery = strQuery + ",a.TAG_IDENTIFIER_CODE ";

		// 来源表
		strQuery = strQuery + "FROM SPMS_PICKING_TASK_ITEM a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				PickingTaskItem.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		String strSql = strQuery + strWhere + strOrder;
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<PickingTaskItem> shelvingTaskItemList = new ArrayList<PickingTaskItem>();
		for (Object[] objects : result)
		{
			PickingTaskItem pickingTaskItem = new PickingTaskItem();
			if (objects[0] != null)
				pickingTaskItem.setId(objects[0].toString());
			if (objects[1] != null)
				pickingTaskItem.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					pickingTaskItem.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)
				pickingTaskItem.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				pickingTaskItem.setKeyword(objects[4].toString());
			if (objects[5] != null)
				pickingTaskItem.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				pickingTaskItem.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				pickingTaskItem.setPartNumber(objects[7].toString());
			if (objects[8] != null)
				pickingTaskItem.setPartName(objects[8].toString());
			if (objects[9] != null)
				pickingTaskItem.setPartUnit(objects[9].toString());
			if (objects[10] != null)
				pickingTaskItem.setPartSerialNumber(objects[10].toString());
			if (objects[11] != null)
				pickingTaskItem.setQuantity(Double.valueOf(objects[11].toString()));
			if (objects[12] != null)
				pickingTaskItem.setTaskID(objects[12].toString());
			if (objects[13] != null)
				pickingTaskItem.setCargoSpaceNumber(objects[13].toString());
			if (objects[14] != null)
				pickingTaskItem.setOperationStatus(TaskItemStatus.valueOf(objects[14].toString()));
			if (objects[15] != null)
				pickingTaskItem.setBarcodeTagUUID(objects[15].toString());
			if (objects[16] != null)
				pickingTaskItem.setTagIdentifierCode(objects[16].toString());

			shelvingTaskItemList.add(pickingTaskItem);
		}
		
		return shelvingTaskItemList;
	}
	
	@Override
	public Boolean updatePickingTaskItem(StockTask stockTask, List<PickingTaskItem> pickingTaskItemList){
		Session session = getSession();
		// 任务编号
		String taskNumber = stockTask.getTaskNo();
		Criteria taskCriteria= session.createCriteria(StockTask.class);
		taskCriteria.add(Restrictions.eq("taskNo", taskNumber));

		List<StockTask> dbStockTaskList = (List<StockTask>)taskCriteria.list();
		StockTask dbStockTask = (StockTask)dbStockTaskList.get(0);
		// 任务执行设备号
		if (stockTask.getActionDevice() != null) {
			dbStockTask.setActionDevice(stockTask.getActionDevice());	
		}
		// 任务执行人
		if (stockTask.getActionBy() != null) {
			dbStockTask.setActionBy(stockTask.getActionBy());	
		}
		// 任务执行日期
		if (stockTask.getActionDate() != null) {
			dbStockTask.setActionDate(stockTask.getActionDate());
		}
		// 任务状态
		if (stockTask.getTaskStatus() != null) {
			dbStockTask.setTaskStatus(stockTask.getTaskStatus());
		}
		// 更新任务主信息表
		session.saveOrUpdate(dbStockTask);
		
		for (int i = 0; i < pickingTaskItemList.size(); i++) {
			PickingTaskItem pickingTaskItem = (PickingTaskItem)pickingTaskItemList.get(i);
			// 条码标签唯一编号
			String barcodeTagUUID = pickingTaskItem.getBarcodeTagUUID();
			// RFID标签唯一序列号
			String tagIdentifierCode = pickingTaskItem.getTagIdentifierCode();

			// 取得任务明细记录信息
			Criteria pickingTaskItemCriteria= session.createCriteria(PickingTaskItem.class);
			if (barcodeTagUUID != null && !barcodeTagUUID.equals("")) {
				pickingTaskItemCriteria.add(Restrictions.eq("barcodeTagUUID", barcodeTagUUID));	
			}
			if (tagIdentifierCode != null && !tagIdentifierCode.equals("")) {
				pickingTaskItemCriteria.add(Restrictions.eq("tagIdentifierCode", tagIdentifierCode));	
			}
			pickingTaskItemCriteria.add(Restrictions.eq("deleted", false));
			PickingTaskItem dbPickingTaskItem = (PickingTaskItem)pickingTaskItemCriteria.list().get(0);
			// 后处理建议
			if (pickingTaskItem.getPostaction() != null) {
				dbPickingTaskItem.setPostaction(pickingTaskItem.getPostaction());
			}
			// 操作状态
			dbPickingTaskItem.setOperationStatus(TaskItemStatus.OVR);
			// 更新任务明细信息表
			session.saveOrUpdate(dbPickingTaskItem);
		}

		if (stockTask.getTaskStatus() == TaskStatus.OVR) {
			// 取得拣货记录信息
			Criteria pickingListCriteria= session.createCriteria(PickingList.class);
			pickingListCriteria.add(Restrictions.eq("id", stockTask.getBussinessBillNO()));
			pickingListCriteria.add(Restrictions.eq("deleted", false));
			PickingList pickingList = (PickingList)pickingListCriteria.list().get(0);
			pickingList.setStatus(OutStockStatus.Pick);
			// 更新拣货记录信息表
			session.saveOrUpdate(pickingList);
			
			// 生成装箱任务
			String[] pickinglistIDs = new String[1];
			pickinglistIDs[0] = dbStockTask.getBussinessBillNO();
			stockTaskManager.insertPackingTask(pickinglistIDs);
		}
		
		return true;
	}
}