package com.skynet.spms.manager.stockServiceBusiness.partsInventory.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.PartsInventoryRecordManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.TimeAndLifePartStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;
import com.skynet.spms.schedule.ScheduledJob;

@Service
@Transactional
public class PartsInventoryRecordManagerImpl extends CommonManagerImpl<PartsInventoryRecord> implements PartsInventoryRecordManager{

	// 设置定时器,每30分钟运行一次 cron
	// http://blog.csdn.net/ja_II_ck/article/details/6441830
	@Override
	@ScheduledJob(jobName = "updateTimeAndLifePartStatus", cron = "0 0 12 * * ??")
	public void updateTimeAndLifePartStatus()
	{
		Session session  = getSession();
		String strHql = "";

		// 更新时控件状态
		strHql = "update SPMS_PARTS_INVENTORY_RECORD set ";
		strHql = strHql + "TIME_CONTROL_STATUS = "
						+ "case "
						+ "when (trunc(LIMIT_DATE) - trunc(sysdate)) <= 0 then "
						+ "'" + TimeAndLifePartStatus.Warn + "' "
						+ "when 0 < (trunc(LIMIT_DATE) - trunc(sysdate)) and (trunc(LIMIT_DATE) - trunc(sysdate)) <= 30 then "
						+ "'" + TimeAndLifePartStatus.Exceed + "' "
						+ "else "
						+ "'" + TimeAndLifePartStatus.Monitoring + "' "
						+ "end ";
		Query timeControlQuery = session.createSQLQuery(strHql);
		timeControlQuery.executeUpdate();

		// 更新时寿件状态
		strHql = "update SPMS_PARTS_INVENTORY_RECORD set ";
		strHql = strHql + "LIFE_PART_STATUS = "
						+ "case "
						+ "when (trunc(LIMIT_DATE) - trunc(sysdate)) <= 0 then "
						+ "'" + TimeAndLifePartStatus.Warn + "' "
						+ "when 0 < (trunc(LIMIT_DATE) - trunc(sysdate)) and (trunc(LIMIT_DATE) - trunc(sysdate)) <= 30 then "
						+ "'" + TimeAndLifePartStatus.Exceed + "' "
						+ "else "
						+ "'" + TimeAndLifePartStatus.Monitoring + "' "
						+ "end ";
		strHql = strHql + "where LIFE_PART_STATUS <> '" + TimeAndLifePartStatus.Apply + "'";
		strHql = strHql + "and LIFE_PART_STATUS <> '" + TimeAndLifePartStatus.Discard + "'";
		Query lifeControlQuery = session.createSQLQuery(strHql);
		lifeControlQuery.executeUpdate();
	}
	
	@Override
	public List<PartsInventoryRecord> getPartsInventoryRecord(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select distinct ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.LOCK_VER, ";
		strQuery = strQuery + "a.VERSION, ";
		// 库房编号
		strQuery = strQuery + "a.STOCK_ROOM_NUMBER, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 序号/批号
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		// 制造商代码
		strQuery = strQuery + "d.CODE, ";
		// 备件名称
		strQuery = strQuery + "c.KEYWORD_ZH, ";
		// 数量
		strQuery = strQuery + "a.BALANCE_QUANTITY, ";
		// 单位
		strQuery = strQuery + "c.UNIT_MEASURE_CODE, ";
		// 货位编号
		strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
		// 备件状况
		strQuery = strQuery + "a.PART_STATUS, ";
		// 出厂日期
		strQuery = strQuery + "a.BUILD_DATE, ";
		// 是否冻结
		strQuery = strQuery + "a.IS_FREEZE, ";
		// 入库员
		strQuery = strQuery + "a.IN_STOCK_OPERATOR, ";
		// 入库日期
		strQuery = strQuery + "a.IN_STOCK_DATE, ";
		// 条码标签唯一编号
		strQuery = strQuery + "e.BARCODE_TAG_UUID, ";
		// RFID标签唯一编号
		strQuery = strQuery + "e.RFID_TAG_UUID, ";
		// 时控件状态
		strQuery = strQuery + "case when a.LIMIT_DATE is not null and to_char(a.LIMIT_DATE, 'yyyy-mm-dd') < to_char(sysdate, 'yyyy-mm-dd') then '"
			+ TimeAndLifePartStatus.Warn + "' else '"
			+ TimeAndLifePartStatus.Monitoring + "' end, ";
		// 时寿件状态
		strQuery = strQuery + "case when a.LIFE_DATE is not null and to_char(a.LIFE_DATE, 'yyyy-mm-dd') < to_char(sysdate, 'yyyy-mm-dd') then '"
			+ TimeAndLifePartStatus.Warn + "' else '"
			+ TimeAndLifePartStatus.Monitoring + "' end ";
		
		// 来源表
		strQuery = strQuery + "from SPMS_PARTS_INVENTORY_RECORD a ";
		strQuery = strQuery + "left join SPMS_PART_INDEX b ";
		strQuery = strQuery + "on a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "and b.IS_DELETED = '0' ";
		strQuery = strQuery + "left join SPMS_BASIC_INFO c ";
		strQuery = strQuery + "on b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "and c.IS_DELETED = '0' ";
		strQuery = strQuery + "left join SPMS_BASE_CODE d ";
		strQuery = strQuery + "on b.MANUFACTURER_CODE_ID = d.ID ";
		strQuery = strQuery + "left join SPMS_PART_ENTITY e ";
		strQuery = strQuery + "on a.PART_NUMBER = e.PART_NUMBER ";
		strQuery = strQuery + "and a.PART_SERIAL_NUMBER = e.PART_SERIAL_NUMBER ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 字段排序
		String strOrder = "order by a.PART_NUMBER, a.PART_SERIAL_NUMBER asc ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values, PartsInventoryRecord.class, "a.", true, null);
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<PartsInventoryRecord> partsInventoryRecordList = new ArrayList<PartsInventoryRecord>();
		for (Object[] objects : result)
		{
			PartsInventoryRecord partsInventoryRecord = new PartsInventoryRecord();				
			if (objects[0] != null)
				partsInventoryRecord.setId(objects[0].toString());
			if (objects[1] != null)
				partsInventoryRecord.setLockVersion(Integer.valueOf(objects[1].toString()));
			if (objects[2] != null)
				partsInventoryRecord.setVersion(Integer.valueOf(objects[2].toString()));
			if (objects[3] != null)
				partsInventoryRecord.setStockRoomNumber(objects[3].toString());
			if (objects[4] != null)
				partsInventoryRecord.setPartNumber(objects[4].toString());
			if (objects[5] != null)
				partsInventoryRecord.setPartSerialNumber(objects[5].toString());
			if (objects[6] != null)
				partsInventoryRecord.setManufacturerCode(objects[6].toString());
			if (objects[7] != null)
				partsInventoryRecord.setPartName(objects[7].toString());
			if (objects[8] != null)
				partsInventoryRecord.setBalanceQuantity(Integer.valueOf(objects[8].toString()));
			if (objects[9] != null)
				partsInventoryRecord.setUnit(UnitOfMeasureCode.valueOf(objects[9].toString()));
			if (objects[10] != null)
				partsInventoryRecord.setCargoSpaceNumber(objects[10].toString());
			if (objects[11] != null)
				partsInventoryRecord.setPartStatus(objects[11].toString());
			if (objects[12] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					partsInventoryRecord.setBuildDate(sdf.parse(objects[12].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[13] != null)
				partsInventoryRecord.setIsFreeze(objects[13].toString());
			if (objects[14] != null)
				partsInventoryRecord.setInStockOperator(objects[14].toString());
			if (objects[15] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					partsInventoryRecord.setInStockDate(sdf.parse(objects[15].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[16] != null)
				partsInventoryRecord.setBarcodeTagUUID(objects[16].toString());
			if (objects[17] != null)
				partsInventoryRecord.setrFIDTagUUID(objects[17].toString());
			if (objects[18] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					partsInventoryRecord.setLimitDate(sdf.parse(objects[18].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[19] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					partsInventoryRecord.setLifeDate(sdf.parse(objects[19].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			partsInventoryRecordList.add(partsInventoryRecord);
		}
		
		return partsInventoryRecordList;
	}
	
	/**
	 * 补码管理使用，根据补码ID查找所要补码的库存备件
	 */
	@Override
	public List<PartsInventoryRecord> findByRepairCodeId(String repairCodeId){
		Criteria criteria = getSession().createCriteria(RepairCodePartItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCodeId));
		List<RepairCodePartItem> list = criteria.list();
		List<PartsInventoryRecord> pirList = new ArrayList<PartsInventoryRecord>();
		for(RepairCodePartItem item : list){
			pirList.add(item.getPartsInventoryRecord());
		}
		return pirList;
	}
}