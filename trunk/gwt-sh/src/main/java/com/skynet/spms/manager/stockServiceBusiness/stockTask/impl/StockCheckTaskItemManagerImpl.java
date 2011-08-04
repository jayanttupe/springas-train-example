package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.SendCardTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockCheckTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.InStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockCheckTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

@Service
@Transactional
public class StockCheckTaskItemManagerImpl extends CommonManagerImpl<StockCheckTaskItem>
		implements StockCheckTaskItemManager {
	
	@Override
	public List<StockCheckTaskItem> getStockCheckTaskItem(Map values, int startRow, int endRow) {
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
		strQuery = strQuery + ",a.REALITY_QUANTITY ";
		strQuery = strQuery + ",a.OPERATION_STATUS ";
		strQuery = strQuery + ",a.BARCODE_TAG_UUID ";
		strQuery = strQuery + ",a.TAG_IDENTIFIER_CODE ";
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";

		// 来源表
		strQuery = strQuery + "FROM SPMS_STOCK_CHECK_TASK_ITEM a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				StockCheckTaskItem.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		String strSql = strQuery + strWhere + strOrder;
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<StockCheckTaskItem> stockCheckTaskItemList = new ArrayList<StockCheckTaskItem>();
		for (Object[] objects : result)
		{
			StockCheckTaskItem stockCheckTaskItem = new StockCheckTaskItem();
			if (objects[0] != null)
				stockCheckTaskItem.setId(objects[0].toString());
			if (objects[1] != null)
				stockCheckTaskItem.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					stockCheckTaskItem.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)
				stockCheckTaskItem.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				stockCheckTaskItem.setKeyword(objects[4].toString());
			if (objects[5] != null)
				stockCheckTaskItem.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				stockCheckTaskItem.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				stockCheckTaskItem.setPartNumber(objects[7].toString());
			if (objects[8] != null)
				stockCheckTaskItem.setPartName(objects[8].toString());
			if (objects[9] != null)
				stockCheckTaskItem.setPartUnit(objects[9].toString());
			if (objects[10] != null)
				stockCheckTaskItem.setPartSerialNumber(objects[10].toString());
			if (objects[11] != null)
				stockCheckTaskItem.setQuantity(Double.valueOf(objects[11].toString()));
			if (objects[12] != null)
				stockCheckTaskItem.setTaskID(objects[12].toString());
			if (objects[13] != null)
				stockCheckTaskItem.setRealityQuantity(Double.valueOf(objects[13].toString()));
			if (objects[14] != null)
				stockCheckTaskItem.setOperationStatus(TaskItemStatus.valueOf(objects[14].toString()));
			if (objects[15] != null)
				stockCheckTaskItem.setBarcodeTagUUID(objects[15].toString());
			if (objects[16] != null)
				stockCheckTaskItem.setTagIdentifierCode(objects[16].toString());
			if (objects[17] != null)
				stockCheckTaskItem.setCargoSpaceNumber(objects[17].toString());

			stockCheckTaskItemList.add(stockCheckTaskItem);
		}
		
		return stockCheckTaskItemList;
	}
	
	@Override
	public Boolean updateStockCheckTaskItem(StockTask stockTask,
			List<StockCheckTaskItem> stockCheckTaskItemList) {
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
		
		for (int i = 0; i < stockCheckTaskItemList.size(); i++) {
			StockCheckTaskItem stockCheckTaskItem = (StockCheckTaskItem)stockCheckTaskItemList.get(i);
			// 条码标签唯一编号
			String barcodeTagUUID = stockCheckTaskItem.getBarcodeTagUUID();
			// RFID标签唯一序列号
			String tagIdentifierCode = stockCheckTaskItem.getTagIdentifierCode();

			// 取得任务明细记录信息
			Criteria taskItemCriteria= session.createCriteria(StockCheckTaskItem.class);
			if (barcodeTagUUID != null && !barcodeTagUUID.equals("")) {
				taskItemCriteria.add(Restrictions.eq("barcodeTagUUID", barcodeTagUUID));	
			}
			if (tagIdentifierCode != null && !tagIdentifierCode.equals("")) {
				taskItemCriteria.add(Restrictions.eq("tagIdentifierCode", tagIdentifierCode));	
			}
			taskItemCriteria.add(Restrictions.eq("taskID", dbStockTask.getId()));
			List<StockCheckTaskItem> dbStockCheckTaskItemList = (List<StockCheckTaskItem>)taskItemCriteria.list();
			StockCheckTaskItem dbStockCheckTaskItem = (StockCheckTaskItem)dbStockCheckTaskItemList.get(0);
			// 盘点数量
			dbStockCheckTaskItem.setRealityQuantity(stockCheckTaskItem.getRealityQuantity());	
			// 操作状态
			if (stockCheckTaskItem.getOperationStatus() != null) {
				dbStockCheckTaskItem.setOperationStatus(stockCheckTaskItem.getOperationStatus());
			}
			// 更新任务明细信息表
			session.saveOrUpdate(dbStockCheckTaskItem);
		}
		
		return true;
	}
}
