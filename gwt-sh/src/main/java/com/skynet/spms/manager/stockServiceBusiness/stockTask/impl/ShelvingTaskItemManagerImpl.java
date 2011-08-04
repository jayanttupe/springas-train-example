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

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.ShelvingTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.InStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

@Service
@Transactional
public class ShelvingTaskItemManagerImpl extends CommonManagerImpl<ShelvingTaskItem>
		implements ShelvingTaskItemManager {

	@Override
	public List<ShelvingTaskItem> getShelvingTaskItem(Map values, int startRow, int endRow) {
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
		strQuery = strQuery + ",a.REC_CARGO_SPACE_NUMBER ";
		strQuery = strQuery + ",a.OPERATION_STATUS ";
		strQuery = strQuery + ",a.BARCODE_TAG_UUID ";
		strQuery = strQuery + ",a.TAG_IDENTIFIER_CODE ";
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";

		// 来源表
		strQuery = strQuery + "FROM SPMS_SHELVING_TASK_ITEM a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				ShelvingTaskItem.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		String strSql = strQuery + strWhere + strOrder;
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<ShelvingTaskItem> shelvingTaskItemList = new ArrayList<ShelvingTaskItem>();
		for (Object[] objects : result)
		{
			ShelvingTaskItem shelvingTaskItem = new ShelvingTaskItem();
			if (objects[0] != null)
				shelvingTaskItem.setId(objects[0].toString());
			if (objects[1] != null)
				shelvingTaskItem.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					shelvingTaskItem.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)
				shelvingTaskItem.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				shelvingTaskItem.setKeyword(objects[4].toString());
			if (objects[5] != null)
				shelvingTaskItem.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				shelvingTaskItem.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				shelvingTaskItem.setPartNumber(objects[7].toString());
			if (objects[8] != null)
				shelvingTaskItem.setPartName(objects[8].toString());
			if (objects[9] != null)
				shelvingTaskItem.setPartUnit(objects[9].toString());
			if (objects[10] != null)
				shelvingTaskItem.setPartSerialNumber(objects[10].toString());
			if (objects[11] != null)
				shelvingTaskItem.setQuantity(Double.valueOf(objects[11].toString()));
			if (objects[12] != null)
				shelvingTaskItem.setTaskID(objects[12].toString());
			if (objects[13] != null)
				shelvingTaskItem.setRecCargoSpaceNumber(objects[13].toString());
			if (objects[14] != null)
				shelvingTaskItem.setOperationStatus(TaskItemStatus.valueOf(objects[14].toString()));
			if (objects[15] != null)
				shelvingTaskItem.setBarcodeTagUUID(objects[15].toString());
			if (objects[16] != null)
				shelvingTaskItem.setTagIdentifierCode(objects[16].toString());
			if (objects[17] != null)
				shelvingTaskItem.setCargoSpaceNumber(objects[17].toString());

			shelvingTaskItemList.add(shelvingTaskItem);
		}
		
		return shelvingTaskItemList;
	}
	
	@Override
	public Boolean updateShelvingTaskItem(StockTask stockTask, List<ShelvingTaskItem> shelvingTaskItemList) {
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
		dbStockTask.setTaskStatus(TaskStatus.OVR);
		// 更新任务主信息表
		session.saveOrUpdate(dbStockTask);
		
		for (int i = 0; i < shelvingTaskItemList.size(); i++) {
			ShelvingTaskItem shelvingTaskItem = (ShelvingTaskItem)shelvingTaskItemList.get(i);
			// 条码标签唯一编号
			String barcodeTagUUID = shelvingTaskItem.getBarcodeTagUUID();
			// RFID标签唯一序列号
			String tagIdentifierCode = shelvingTaskItem.getTagIdentifierCode();

			// 取得任务明细记录信息
			Criteria taskItemCriteria= session.createCriteria(ShelvingTaskItem.class);
			if (barcodeTagUUID != null && !barcodeTagUUID.equals("")) {
				taskItemCriteria.add(Restrictions.eq("barcodeTagUUID", barcodeTagUUID));	
			}
			if (tagIdentifierCode != null && !tagIdentifierCode.equals("")) {
				taskItemCriteria.add(Restrictions.eq("tagIdentifierCode", tagIdentifierCode));
			}
			List<ShelvingTaskItem> dbShelvingTaskItemList = (List<ShelvingTaskItem>)taskItemCriteria.list();
			ShelvingTaskItem dbShelvingTaskItem = (ShelvingTaskItem)dbShelvingTaskItemList.get(0);
			// 实际货位编号
			if (shelvingTaskItem.getCargoSpaceNumber() != null) {
				dbShelvingTaskItem.setCargoSpaceNumber(shelvingTaskItem.getCargoSpaceNumber());	
			}
			// 操作状态
			if (shelvingTaskItem.getOperationStatus() != null) {
				dbShelvingTaskItem.setOperationStatus(shelvingTaskItem.getOperationStatus());
			}
			// 更新任务明细信息表
			session.saveOrUpdate(dbShelvingTaskItem);

			// 取得入库记录信息
			Criteria inStockRecordCriteria= session.createCriteria(InStockRecord.class);
			inStockRecordCriteria.add(Restrictions.eq("partNumber", shelvingTaskItem.getPartNumber()));
			inStockRecordCriteria.add(Restrictions.eq("partSerialNumber", shelvingTaskItem.getPartSerialNumber()));
			inStockRecordCriteria.add(Restrictions.eq("deleted", false));
			InStockRecord inStockRecord = (InStockRecord)inStockRecordCriteria.list().get(0);
			inStockRecord.setInStockStatus(InStockStatus.InStock);
			inStockRecord.setCargoSpaceNumber(shelvingTaskItem.getCargoSpaceNumber());
			// 更新入库记录信息表
			session.saveOrUpdate(inStockRecord);
			
			// 插入库存数据
			insertPartsInventoryRecord(inStockRecord, stockTask.getActionBy(), stockTask.getActionDate(), session);
		}
		
		return true;
	}
	
	/**
	 * 插入库存数据
	 * @param inStockRecord
	 * @param session
	 */
	private void insertPartsInventoryRecord(InStockRecord inStockRecord, String actionBy, Date actionDate, Session session)
	{
		PartsInventoryRecord partsInventoryRecord = new PartsInventoryRecord();
		// 件号
		if (inStockRecord.getPartNumber() != null) {
			partsInventoryRecord.setPartNumber(inStockRecord.getPartNumber());	
		}
		// 序号
		if (inStockRecord.getPartSerialNumber() != null) {
			partsInventoryRecord.setPartSerialNumber(inStockRecord.getPartSerialNumber());
		}
		// 收料单编号
		if (inStockRecord.getReceivingSheetNumber() != null) {
			partsInventoryRecord.setReceivingNumber(inStockRecord.getReceivingSheetNumber());	
		}
		// 库存结存数量
		partsInventoryRecord.setBalanceQuantity(inStockRecord.getQuantity());
		// 单位
		if (inStockRecord.getUnitOfMeasure() != null) {
			partsInventoryRecord.setUnit(inStockRecord.getUnitOfMeasure());	
		}
		// 是否冻结
		partsInventoryRecord.setIsFreeze("0");
		// 是否时控件
		if (inStockRecord.getIsTimePart() != null) {
			partsInventoryRecord.setIsTimePart(inStockRecord.getIsTimePart());	
		}
		// 时控件到限日期
		if (inStockRecord.getLimitDate() != null) {
			partsInventoryRecord.setLimitDate(inStockRecord.getLimitDate());	
		}
		// 是否寿命件
		if (inStockRecord.getIsLifePart() != null) {
			partsInventoryRecord.setIsLifePart(inStockRecord.getIsLifePart());	
		}
		// 时寿件到限日期
		if (inStockRecord.getLifeTimeDate() != null) {
			partsInventoryRecord.setLifeDate(inStockRecord.getLifeTimeDate());	
		}
		// 库房编号
		if (inStockRecord.getStockRoomNumber() != null) {
			partsInventoryRecord.setStockRoomNumber(inStockRecord.getStockRoomNumber());	
		}
		// 货位编号
		if (inStockRecord.getCargoSpaceNumber() != null) {
			partsInventoryRecord.setCargoSpaceNumber(inStockRecord.getCargoSpaceNumber());	
		}
		// 备件状况
		if (inStockRecord.getPartStatusCode() != null) {
			partsInventoryRecord.setPartStatus(inStockRecord.getPartStatusCode());	
		}
		// 入库员
		if (actionBy != null) {
			partsInventoryRecord.setInStockOperator(actionBy);	
		}
		// 入库日期
		if (actionDate != null) {
			partsInventoryRecord.setInStockDate(actionDate);	
		}
		partsInventoryRecord.setCreateBy(GwtActionHelper.getCurrUser());
		partsInventoryRecord.setCreateDate(new Date());
		// 入库处理
		session.saveOrUpdate(partsInventoryRecord);
	}
}