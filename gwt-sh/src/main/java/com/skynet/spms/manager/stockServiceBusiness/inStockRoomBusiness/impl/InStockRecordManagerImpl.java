package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.InStockRecordManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.InStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecordForPrint;

@Service
@Transactional
public class InStockRecordManagerImpl extends CommonManagerImpl<InStockRecord>
		implements InStockRecordManager {

	@Override
	public InStockRecord updateRecord(InStockRecord inStockRecord) {
		inStockRecord.setCreateBy(GwtActionHelper.getCurrUser());
		inStockRecord.setCreateDate(new Date());
		getSession().saveOrUpdate(inStockRecord);
		return inStockRecord;
	}

	@Override
	public List<InStockRecord> getInStockRecord(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.CREATE_BY, ";
		strQuery = strQuery + "a.CREATE_DATE, ";	
		strQuery = strQuery + "a.IS_DELETED, ";
		strQuery = strQuery + "c.PART_NAME_ZH, ";
		strQuery = strQuery + "a.LOCK_VER, ";
		strQuery = strQuery + "a.VERSION, ";
		strQuery = strQuery + "a.ACCOUNT_BOOK_ITEMS_NUMBER, ";
		strQuery = strQuery + "a.BUSINESS_TYPE, ";
		strQuery = strQuery + "a.CAGE_CODE, ";
		strQuery = strQuery + "a.CARGOSPACE_NUMBER, ";
		strQuery = strQuery + "a.CHECK_AND_ACCEPT_SHEET_NUMBER, ";
		strQuery = strQuery + "a.CLEARANCE_ACCOUNT_BOOK_NUMBER, ";
		strQuery = strQuery + "a.CONTRAT_NUMBER, ";
		strQuery = strQuery + "a.HS_CODE, ";
		strQuery = strQuery + "a.IN_STOCK_DATE, ";
		strQuery = strQuery + "a.IN_STOCK_RECORD_NUMBER, ";
		strQuery = strQuery + "a.IN_STOCK_STATUS, ";
		strQuery = strQuery + "a.INSPECTION_DATE, ";
		strQuery = strQuery + "a.INSPECTOR, ";
		strQuery = strQuery + "a.INVOICE_NUMBER, ";
		strQuery = strQuery + "a.IS_LIFE_PART, ";
		strQuery = strQuery + "a.PART_NUMBER, ";
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		strQuery = strQuery + "a.PART_STATUS_CODE, ";
		strQuery = strQuery + "a.QUANTITY, ";
		strQuery = strQuery + "a.REC_CARGO_SPACE_NUM, ";
		strQuery = strQuery + "a.RECEIVING_SHEET_NUMBER, ";
		strQuery = strQuery + "a.REMARK, ";
		strQuery = strQuery + "a.SPARE_PART_CLASS_CODE, ";
		strQuery = strQuery + "a.SHELVING_STATUS, ";
		strQuery = strQuery + "a.STOCK_INVENTORY_QUANTITY, ";
		strQuery = strQuery + "a.STOCKROOM_CHINESE_NAME, ";
		strQuery = strQuery + "a.STOCKROOM_NUMBER, ";
		strQuery = strQuery + "a.SUPPLY_UNIT, ";
		strQuery = strQuery + "c.UNIT_MEASURE_CODE, ";
		strQuery = strQuery + "a.USEFUL_LIFE_PERIOD, ";
		strQuery = strQuery + "a.SEND_CARD_STATUS, ";
		strQuery = strQuery + "d.CODE, ";
		strQuery = strQuery + "c.KEYWORD_ZH ";
		// 来源表
		strQuery = strQuery + "FROM SPMS_IN_STOCK_RECORD a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASE_CODE d ";
		strQuery = strQuery + "ON b.MANUFACTURER_CODE_ID = d.ID ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 构建不通过共通方法创建查询条件的项目
		Map extraKey = new HashMap();
		extraKey.put("inStockStatus", "inStockStatus");
		extraKey.put("shelvingStatus", "shelvingStatus");
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				InStockRecord.class,
				"a.",
				true,
				extraKey);
		if (values.containsKey("inStockStatus")) {
			strWhere = strWhere + " AND a.IN_STOCK_STATUS = '" + values.get("inStockStatus") + "'";	
		}
		if (values.containsKey("shelvingStatus")) {
			strWhere = strWhere + " AND a.SHELVING_STATUS = '" + values.get("shelvingStatus") + "'";	
		}
		// 排序
		String strOrder = " order by a.CHECK_AND_ACCEPT_SHEET_NUMBER desc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<InStockRecord> inStockRecordList = new ArrayList<InStockRecord>();
		for (Object[] objects : result)
		{
			InStockRecord inStockRecord = new InStockRecord();
			if(objects[0] != null)				
				inStockRecord.setId(objects[0].toString());
			if(objects[1] != null)				
				inStockRecord.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					inStockRecord.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[3] != null)
				inStockRecord.setDeleted(Boolean.valueOf(objects[3].toString()));
			if(objects[4] != null)				
				inStockRecord.setKeyword(objects[4].toString());
			if(objects[5] != null)				
				inStockRecord.setLockVersion(Integer.valueOf(objects[5].toString()));
			if(objects[6] != null)				
				inStockRecord.setVersion(Integer.valueOf(objects[6].toString()));
			if(objects[7] != null)				
				inStockRecord.setAccountBookItemsNumber(objects[7].toString());
			if(objects[8] != null)				
				inStockRecord.setBusinessType(objects[8].toString());
			if(objects[9] != null)				
				inStockRecord.setcAGECode(objects[9].toString());
			if(objects[10] != null)				
				inStockRecord.setCargoSpaceNumber(objects[10].toString());
			if(objects[11] != null)				
				inStockRecord.setCheckAndAcceptSheetNumber(objects[11].toString());
			if(objects[12] != null)				
				inStockRecord.setClearanceAccountBookNumber(objects[12].toString());
			if(objects[13] != null)				
				inStockRecord.setContratNumber(objects[13].toString());
			if(objects[14] != null)				
				inStockRecord.setHsCode(objects[14].toString());
			if(objects[15] != null) {				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					inStockRecord.setCreateDate(sdf.parse(objects[15].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if(objects[16] != null)				
				inStockRecord.setInStockRecordNumber(objects[16].toString());
			if(objects[17] != null)				
				inStockRecord.setInStockStatus(InStockStatus.valueOf(objects[17].toString()));
			if(objects[18] != null)	{
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					inStockRecord.setInspectionDate(sd.parse(objects[18].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(objects[19] != null)				
				inStockRecord.setInspector(objects[19].toString());
			if(objects[20] != null)				
				inStockRecord.setInvoiceNumber(objects[20].toString());
			if(objects[21] != null)				
				inStockRecord.setIsLifePart(objects[21].toString());
			if(objects[22] != null)				
				inStockRecord.setPartNumber(objects[22].toString());
			if(objects[23] != null)				
				inStockRecord.setPartSerialNumber(objects[23].toString());
			if(objects[24] != null)				
				inStockRecord.setPartStatusCode(objects[24].toString());
			if(objects[25] != null)				
				inStockRecord.setQuantity(Integer.valueOf(objects[25].toString()));
			if(objects[26] != null)				
				inStockRecord.setRecCargoSpaceNum(objects[26].toString());
			if(objects[27] != null)				
				inStockRecord.setReceivingSheetNumber(objects[27].toString());
			if(objects[28] != null)				
				inStockRecord.setRemark(objects[28].toString());
			if(objects[29] != null)				
				inStockRecord.setSparePartClassCode(objects[29].toString());
			if(objects[30] != null)				
				inStockRecord.setShelvingStatus(SendStatus.valueOf(objects[30].toString()));
			if(objects[31] != null)				
				inStockRecord.setStockInventoryQuantity(objects[31].toString());
			if(objects[32] != null)				
				inStockRecord.setStockRoomChineseName(objects[32].toString());
			if(objects[33] != null)				
				inStockRecord.setStockRoomNumber(objects[33].toString());
			if(objects[34] != null)				
				inStockRecord.setSupplyUnit(objects[34].toString());
			if(objects[35] != null)				
				inStockRecord.setUnitOfMeasure(UnitOfMeasureCode.valueOf(objects[35].toString()));
			if(objects[36] != null)				
				inStockRecord.setUsefulLifePeriod(objects[36].toString());
			if(objects[37] != null)				
				inStockRecord.setSendCardStatus(SendStatus.valueOf(objects[37].toString()));
			if(objects[38] != null)				
				inStockRecord.setSupplyUnit(objects[38].toString());
			if(objects[39] != null)				
				inStockRecord.setPartName(objects[39].toString());
			
			inStockRecordList.add(inStockRecord);
		}
		return inStockRecordList;
	}
	
	@Override
	public List<InStockRecordForPrint> getInStockRecordForPrint(String[] inStockRecords)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER ";
		// 序号/批次号
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		// 制造商
		strQuery = strQuery + ",d.CODE ";
		// 数量
		strQuery = strQuery + ",a.QUANTITY ";
		// 单位
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		// 航材分类
		strQuery = strQuery + ",c.SPARE_PART_CLASS_CODE ";
		// 客户订单号(暂时先使用合同编号)
		strQuery = strQuery + ",a.CONTRAT_NUMBER ";
		// 库房编号
		strQuery = strQuery + ",a.STOCKROOM_NUMBER ";
		// 入库检验单编号
		strQuery = strQuery + ",a.CHECK_AND_ACCEPT_SHEET_NUMBER ";
		
		// 来源表
		strQuery = strQuery + "FROM SPMS_IN_STOCK_RECORD a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASE_CODE d ";
		strQuery = strQuery + "ON b.MANUFACTURER_CODE_ID = d.ID ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		if (inStockRecords != null && inStockRecords.length > 0) {
			strWhere = strWhere + "and ( ";
			for (int i = 0; i < inStockRecords.length; i++) {
				if (i != 0) {
					strWhere = strWhere + " or ";
				}
				strWhere = strWhere + "a.ID = '" + inStockRecords[i] + "'";	
			}
			strWhere = strWhere + ") ";
		}
		// 排序
		String strOrder = " order by a.PART_NUMBER asc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<InStockRecordForPrint> inStockRecordForPrintList = new ArrayList<InStockRecordForPrint>();
		for (Object[] objects : result)
		{
			InStockRecordForPrint inStockRecordForPrint = new InStockRecordForPrint();

			// 件号
			if(objects[0] != null)				
				inStockRecordForPrint.setPartNumber(objects[0].toString());
			// 序号/批次号
			if(objects[1] != null)				
				inStockRecordForPrint.setPartSerialNumber(objects[1].toString());
			// 制造商
			if(objects[2] != null)				
				inStockRecordForPrint.setManufacturerCode(objects[2].toString());
			// 数量
			if(objects[3] != null)	
				inStockRecordForPrint.setQuantity(Integer.valueOf(objects[3].toString()));
			// 单位
			if(objects[4] != null)				
				inStockRecordForPrint.setUnit(objects[4].toString());
			// 航材分类
			if(objects[5] != null)				
				inStockRecordForPrint.setSparePartClassCode(objects[5].toString());
			// 客户订单号(暂时先使用合同编号)
			if(objects[6] != null)				
				inStockRecordForPrint.setContratNumber(objects[6].toString());
			// 库房编号
			if(objects[7] != null)				
				inStockRecordForPrint.setStockRoomNumber(objects[7].toString());
			// 入库检验单编号
			if(objects[8] != null)				
				inStockRecordForPrint.setCheckAndAcceptSheetNumber(objects[8].toString());
			
			inStockRecordForPrintList.add(inStockRecordForPrint);
		}
		return inStockRecordForPrintList;
	}
}