package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.SendCardTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;

@Service
@Transactional
public class SendCardTaskItemManagerImpl extends CommonManagerImpl<SendCardTaskItem>
		implements SendCardTaskItemManager {

	@Override
	public List<SendCardTaskItem> getSendCardTaskItem(Map values, int startRow, int endRow) {
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
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";

		// 来源表
		strQuery = strQuery + "FROM SPMS_SEND_CARD_TASK_ITEM a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				SendCardTaskItem.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		String strSql = strQuery + strWhere + strOrder;
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<SendCardTaskItem> sendCardTaskItemList = new ArrayList<SendCardTaskItem>();
		for (Object[] objects : result)
		{
			SendCardTaskItem sendCardTaskItem = new SendCardTaskItem();
			if (objects[0] != null)
				sendCardTaskItem.setId(objects[0].toString());
			if (objects[1] != null)
				sendCardTaskItem.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					sendCardTaskItem.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)
				sendCardTaskItem.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				sendCardTaskItem.setKeyword(objects[4].toString());
			if (objects[5] != null)
				sendCardTaskItem.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				sendCardTaskItem.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				sendCardTaskItem.setPartNumber(objects[7].toString());
			if (objects[8] != null)
				sendCardTaskItem.setPartName(objects[8].toString());
			if (objects[9] != null)
				sendCardTaskItem.setPartUnit(objects[9].toString());
			if (objects[10] != null)
				sendCardTaskItem.setPartSerialNumber(objects[10].toString());
			if (objects[11] != null)
				sendCardTaskItem.setQuantity(Double.valueOf(objects[11].toString()));
			if (objects[12] != null)
				sendCardTaskItem.setTaskID(objects[12].toString());
			if (objects[13] != null)
				sendCardTaskItem.setRecCargoSpaceNumber(objects[13].toString());
			if (objects[14] != null)
				sendCardTaskItem.setOperationStatus(TaskItemStatus.valueOf(objects[14].toString()));
			if (objects[15] != null)
				sendCardTaskItem.setBarcodeTagUUID(objects[15].toString());
			if (objects[16] != null)
				sendCardTaskItem.setCargoSpaceNumber(objects[16].toString());

			sendCardTaskItemList.add(sendCardTaskItem);
		}
		
		return sendCardTaskItemList;
	}
}