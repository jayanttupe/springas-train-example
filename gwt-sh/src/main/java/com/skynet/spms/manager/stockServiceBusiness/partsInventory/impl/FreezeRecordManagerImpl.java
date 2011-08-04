package com.skynet.spms.manager.stockServiceBusiness.partsInventory.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.FreezeRecordManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.freezeRecord.FreezeRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

@Service
@Transactional
public class FreezeRecordManagerImpl extends CommonManagerImpl<FreezeRecord> implements FreezeRecordManager{

	@Override
	public List<FreezeRecord> getFreezeRecord(Map values, int startRow, int endRow) {
		String strQuery = "";

		strQuery = strQuery + "select ";
		// 库房编号
		strQuery = strQuery + "a.STOCK_ROOM_NUMBER ";
		// 件号
		strQuery = strQuery + ",a.PART_NUMBER ";
		// 关键字
		strQuery = strQuery + ",a.KEYWORD ";
		// 序号/批号
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		// 数量
		strQuery = strQuery + ",a.BALANCE_QUANTITY ";
		// 单位
		strQuery = strQuery + ",a.UNIT ";
		// 入库日期
		strQuery = strQuery + ",a.IN_STOCK_DATE ";
		// 备件状态
		strQuery = strQuery + ",a.STATE ";
		// 寿命期
		strQuery = strQuery + ",a.USEFUL_LIFE_PERIOD ";
		// 冻结ID
		strQuery = strQuery + ",b.ID as FREEZE_ID ";
		// 冻结数量
		strQuery = strQuery + ",b.FREEZE_QUANTITY ";
		// 冻结用途
		strQuery = strQuery + ",b.FREEZE_APPLICATION ";
		// 冻结原因
		strQuery = strQuery + ",b.FREEZE_REASON_CODE ";
		// 冻结解除日期
		strQuery = strQuery + ",b.FREEZE_AUTO_REMOVE_DATE ";
		// 无期限，直至人工解除
		strQuery = strQuery + ",b.INFINITI_REMOVE ";
		// 冻结人
		strQuery = strQuery + ",b.FREEZE_OPERATOR ";
		// 冻结日期
		strQuery = strQuery + ",b.FREEZE_DATE ";
		strQuery = strQuery + ",b.VERSION ";
		strQuery = strQuery + ",b.LOCK_VER ";
		// 库存ID
		strQuery = strQuery + ",a.ID PARTS_INVENTORY_ID ";
		// 是否冻结
		strQuery = strQuery + ",a.IS_FREEZE ";
		strQuery = strQuery + "from SPMS_PARTS_INVENTORY_RECORD a ";
		strQuery = strQuery + "left join SPMS_FREEZE_RECORD b ";
		strQuery = strQuery + "on a.ID = b.PARTS_INVENTORY_ID ";
		strQuery = strQuery + "and b.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<FreezeRecord> freezeRecordList = new ArrayList<FreezeRecord>();
		for (Object[] objects : result)
		{
			PartsInventoryRecord partsInventoryRecord = new PartsInventoryRecord();
			FreezeRecord freezeRecord = new FreezeRecord();
			
			if (objects[0] != null)
				partsInventoryRecord.setStockRoomNumber(objects[0].toString());
			if (objects[1] != null)
				partsInventoryRecord.setPartNumber(objects[1].toString());
			if (objects[2] != null)
				partsInventoryRecord.setKeyword(objects[2].toString());
			if (objects[3] != null)
				partsInventoryRecord.setPartSerialNumber(objects[3].toString());
			if (objects[4] != null)
				partsInventoryRecord.setBalanceQuantity(Integer.valueOf(objects[4].toString()));
			if (objects[5] != null)
				partsInventoryRecord.setUnit(UnitOfMeasureCode.valueOf(objects[5].toString()));
			if (objects[6] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					partsInventoryRecord.setInStockDate(sdf.parse(objects[6].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[7] != null)
				partsInventoryRecord.setState(objects[7].toString());
			if (objects[8] != null)
				partsInventoryRecord.setUsefulLifePeriod(objects[8].toString());
			if (objects[9] != null)
				freezeRecord.setId(objects[9].toString());
			if (objects[10] != null)
				freezeRecord.setFreezeQuantity(objects[10].toString());
			if (objects[11] != null)
				freezeRecord.setFreezeApplication(objects[11].toString());
			if (objects[12] != null)
				freezeRecord.setFreezeReasonCode(objects[12].toString());
			if (objects[13] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					freezeRecord.setFreezeAutoRemoveDate(sdf.parse(objects[13].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[14] != null)
				freezeRecord.setInfinitiRemove(objects[14].toString());
			if (objects[15] != null)
				freezeRecord.setFreezeOperator(objects[15].toString());
			if (objects[16] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					freezeRecord.setFreezeDate(sdf.parse(objects[16].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[17] != null)
				freezeRecord.setVersion(Integer.valueOf(objects[17].toString()));
			if (objects[18] != null)
				freezeRecord.setLockVersion(Integer.valueOf(objects[18].toString()));
			if (objects[19] != null)
				freezeRecord.setPartsInventoryID(objects[19].toString());
			if (objects[20] != null)
				partsInventoryRecord.setIsFreeze(objects[20].toString());
			
			freezeRecord.setPartsInventoryRecord(partsInventoryRecord);
			freezeRecordList.add(freezeRecord);
		}
		
		return freezeRecordList;
	}

	@Override
	public FreezeRecord saveFreezeRecord(FreezeRecord freezeRecord) {
		
		freezeRecord.setCreateBy(GwtActionHelper.getCurrUser());
		freezeRecord.setCreateDate(new Date());
		getSession().saveOrUpdate(freezeRecord);

		String strHql = "update PartsInventoryRecord set ";
		strHql = strHql + "isFreeze = '1' ";
		strHql = strHql + "where id = :partsInventoryID ";

		// 更新为已冻结
		Query pickupDeliveryQuery = getSession().createQuery(strHql);
		pickupDeliveryQuery.setString("partsInventoryID", freezeRecord.getPartsInventoryID());
		pickupDeliveryQuery.executeUpdate();
		
		return freezeRecord;
	}

	@Override
	public void releaseFreezeRecord(String freezeRecordID, String partsInventoryID) {
		Session session  = getSession();

		String strHql = "update FreezeRecord set ";
		strHql = strHql + "deleted = '1' ";
		strHql = strHql + "where id = :freezeRecordID ";
		
		// 删除冻结数据
		Query freezeQuery = session.createQuery(strHql);
		freezeQuery.setString("freezeRecordID", freezeRecordID);
		freezeQuery.executeUpdate();

		strHql = "update PartsInventoryRecord set ";
		strHql = strHql + "isFreeze = '0' ";
		strHql = strHql + "where id = :partsInventoryID ";
		
		// 解除冻结
		Query partsInventoryQuery = session.createQuery(strHql);
		partsInventoryQuery.setString("partsInventoryID", partsInventoryID);
		partsInventoryQuery.executeUpdate();
	}
}