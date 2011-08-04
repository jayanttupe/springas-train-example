package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.GenerateNumberManager;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CheckAndAcceptSheetManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.spmsdd.CredentialsUseStatus;
import com.skynet.spms.persistence.entity.spmsdd.InStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartEntity;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReport;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class CheckAndAcceptSheetManagerImpl extends CommonManagerImpl<CheckAndAcceptSheet> implements CheckAndAcceptSheetManager {

	@Autowired
	GenerateNumberManager generateNumberManager;
	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<CheckAndAcceptSheet> getCheckAndAcceptSheet(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.CREATE_BY, ";
		strQuery = strQuery + "a.CREATE_DATE, ";
		strQuery = strQuery + "a.IS_DELETED, ";
		strQuery = strQuery + "a.KEYWORD, ";
		strQuery = strQuery + "a.LOCK_VER, ";
		strQuery = strQuery + "a.VERSION, ";
		strQuery = strQuery + "a.BUSINESS_TYPE, ";
		strQuery = strQuery + "a.CERTIFICATE_CODE, ";
		strQuery = strQuery + "a.CHECK_AND_ACCEPT_SHEET_NUMBER, ";
		strQuery = strQuery + "a.CHECK_DATE, ";
		strQuery = strQuery + "a.CHECK_RESULT, ";
		strQuery = strQuery + "a.CHECK_USER, ";
		strQuery = strQuery + "a.CONTRAT_NUMBER, ";
		strQuery = strQuery + "a.CREDENTIALS, ";
		strQuery = strQuery + "a.FAIL_QUANTITY, ";
		strQuery = strQuery + "a.IS_SAMPLING, ";
		strQuery = strQuery + "a.IS_TIME_PART, ";
		strQuery = strQuery + "a.STORAGE_RACKS_LIFE, ";
		strQuery = strQuery + "a.LIMIT_DATE, ";
		strQuery = strQuery + "a.IS_LIFE_PART, ";
		strQuery = strQuery + "a.LIFE_PART_CYCLE, ";
		strQuery = strQuery + "a.LIFE_DATE, ";
		strQuery = strQuery + "a.MANUFACTURE_DATE, ";
		strQuery = strQuery + "a.MANUFACTURER_NAME, ";
		strQuery = strQuery + "a.PACKING_LIST_NUMBER, ";
		strQuery = strQuery + "a.PART_NUMBER, ";
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		strQuery = strQuery + "a.PART_STATUS, ";
		strQuery = strQuery + "a.PARTS_QUALITY_APPEARANCE, ";
		strQuery = strQuery + "a.QUANTITY, ";
		strQuery = strQuery + "a.RECEIVING_SHEET_ID, ";
		strQuery = strQuery + "a.RECEIVING_SHEET_ITEMS_ID, ";
		strQuery = strQuery + "a.RECEIVING_SHEET_NUMBER, ";
		strQuery = strQuery + "a.SAMPLING_SCHEME, ";
		strQuery = strQuery + "a.SUCCEED_QUANTITY, ";
		strQuery = strQuery + "c.KEYWORD_ZH, ";
		strQuery = strQuery + "c.UNIT_MEASURE_CODE, ";
		strQuery = strQuery + "c.SPARE_PART_CLASS_CODE, ";
		strQuery = strQuery + "a.PROVIDER_NAME ";
		// 来源表
		strQuery = strQuery + "from SPMS_CHECK_AND_ACCEPT_SHEET a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values, CheckAndAcceptSheet.class, "a.", true, null);
		// 排序设置
		String strOrder = " order by a.CHECK_DATE desc ";
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<CheckAndAcceptSheet> checkAndAcceptSheetList = new ArrayList<CheckAndAcceptSheet>();
		for (Object[] objects : result)
		{
			CheckAndAcceptSheet checkAndAcceptSheet = new CheckAndAcceptSheet();
			if (objects[0] != null)	
						checkAndAcceptSheet.setId(objects[0].toString());
			if (objects[1] != null)
				checkAndAcceptSheet.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					checkAndAcceptSheet.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[3] != null)
						checkAndAcceptSheet.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
						checkAndAcceptSheet.setKeyword(objects[4].toString());
			if (objects[5] != null)
						checkAndAcceptSheet.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
						checkAndAcceptSheet.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
						checkAndAcceptSheet.setBusinessType(BusinessType.valueOf(objects[7].toString()));
			if (objects[8] != null)
						checkAndAcceptSheet.setCertificateCode(objects[8].toString());
			if (objects[9] != null)
						checkAndAcceptSheet.setCheckAndAcceptSheetNumber(objects[9].toString());
			if (objects[10] != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					checkAndAcceptSheet.setCheckDate(sdf.parse(objects[10].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[11] != null)				
						checkAndAcceptSheet.setCheckResult(objects[11].toString());
			if (objects[12] != null)				
						checkAndAcceptSheet.setCheckUser(objects[12].toString());
			if (objects[13] != null)				
						checkAndAcceptSheet.setContratNumber(objects[13].toString());
			if (objects[14] != null)				
						checkAndAcceptSheet.setCredentials(objects[14].toString());
			if (objects[15] != null)				
						checkAndAcceptSheet.setFailQuantity(objects[15].toString());
			if (objects[16] != null)				
						checkAndAcceptSheet.setIsSampling(objects[16].toString());
			if (objects[17] != null)				
						checkAndAcceptSheet.setIsTimePart(objects[17].toString());
			if (objects[18] != null)				
						checkAndAcceptSheet.setStorageRacksLife(objects[18].toString());
			if (objects[19] != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					checkAndAcceptSheet.setLimitDate(sdf.parse(objects[19].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[20] != null)				
						checkAndAcceptSheet.setIsLifePart(objects[20].toString());
			if (objects[21] != null)				
						checkAndAcceptSheet.setLifePartCycle(objects[21].toString());
			if (objects[22] != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					checkAndAcceptSheet.setLifeDate(sdf.parse(objects[22].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[23] != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					checkAndAcceptSheet.setManufactureDate(sdf.parse(objects[23].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}				
			if (objects[24] != null)				
						checkAndAcceptSheet.setManufacturerName(objects[24].toString());
			if (objects[25] != null)				
						checkAndAcceptSheet.setPackingListNumber(objects[25].toString());
			if (objects[26] != null)				
						checkAndAcceptSheet.setPartNumber(objects[26].toString());
			if (objects[27] != null)				
						checkAndAcceptSheet.setPartSerialNumber(objects[27].toString());
			if (objects[28] != null)				
						checkAndAcceptSheet.setPartStatus(objects[28].toString());
			if (objects[29] != null)				
						checkAndAcceptSheet.setPartsQualityAppearance(objects[29].toString());
			if (objects[30] != null)				
						checkAndAcceptSheet.setQuantity(Integer.valueOf(objects[30].toString()));
			if (objects[31] != null)				
						checkAndAcceptSheet.setReceivingSheetID(objects[31].toString());
			if (objects[32] != null)				
						checkAndAcceptSheet.setReceivingSheetItemsID(objects[32].toString());
			if (objects[33] != null)				
						checkAndAcceptSheet.setReceivingSheetNumber(objects[33].toString());
			if (objects[34] != null)				
						checkAndAcceptSheet.setSamplingScheme(objects[34].toString());
			if (objects[35] != null)				
						checkAndAcceptSheet.setSucceedQuantity(objects[35].toString());
			if (objects[36] != null)				
						checkAndAcceptSheet.setPartName(objects[36].toString());
			if (objects[37] != null)				
						checkAndAcceptSheet.setUnit(UnitOfMeasureCode.valueOf(objects[37].toString()));
			if (objects[38] != null)				
						checkAndAcceptSheet.setPartType(SparePartClassCode.valueOf(objects[38].toString()));
			if (objects[39] != null)				
						checkAndAcceptSheet.setProviderName(objects[39].toString());
			
			checkAndAcceptSheetList.add(checkAndAcceptSheet);
		}
		return checkAndAcceptSheetList;
	}

	@Override
	public CheckAndAcceptSheet saveCheckAndAcceptSheet(CheckAndAcceptSheet checkAndAcceptSheet)
	{
		Session session = getSession();
		String strHql = "";
		// 证书存档位置编号
		String credentialsCode = checkAndAcceptSheet.getCertificateCode();
		credentialsCode = credentialsCode.substring(4, credentialsCode.length());
		if (checkAndAcceptSheet.getId() == null || checkAndAcceptSheet.getId().equals("")) {
			// 判断证书存档编号是否合法
			strHql = "select credentialsUseStatus from Credentials where credentialsCode = '" + credentialsCode + "'";
			List<CredentialsUseStatus> result = session.createQuery(strHql).list();
			if (result != null && result.size() > 0 && result.get(0) != null) {
				if (result.get(0).equals(CredentialsUseStatus.Used)) {
					// 2:当前证书已经被使用
					checkAndAcceptSheet.setExecuteResult("2");
					return checkAndAcceptSheet;
				} else {
					checkAndAcceptSheet.setExecuteResult("0");
				}
			} else {
				// 1:当前证书编号不存在
				checkAndAcceptSheet.setExecuteResult("1");
				return checkAndAcceptSheet;
			}
			
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("QCD");
			// 收料单编号
			checkAndAcceptSheet.setCheckAndAcceptSheetNumber(curNO);

			// 保存收料明细数据至备件实体
			PartEntity partEntity = new PartEntity();
			partEntity.setPartNumber(checkAndAcceptSheet.getPartNumber());
			partEntity.setPartSerialNumber(checkAndAcceptSheet.getPartSerialNumber());
			partEntity.setQuantity(checkAndAcceptSheet.getQuantity());
			partEntity.setCreateBy(GwtActionHelper.getCurrUser());
			partEntity.setCreateDate(new Date());
			session.saveOrUpdate(partEntity);
			
			if (checkAndAcceptSheet.getCheckResult() != null &&
					checkAndAcceptSheet.getCheckResult().equals("验收合格"))
			{
				InStockRecord inStockRecord = new InStockRecord();
				// 检验单号
				if (checkAndAcceptSheet.getCheckAndAcceptSheetNumber() != null)
					inStockRecord.setCheckAndAcceptSheetNumber(checkAndAcceptSheet.getCheckAndAcceptSheetNumber());
				// 收料单号
				if (checkAndAcceptSheet.getReceivingSheetNumber() != null)
					inStockRecord.setReceivingSheetNumber(checkAndAcceptSheet.getReceivingSheetNumber());
				// 检验日期
				if (checkAndAcceptSheet.getCheckDate() != null)
					inStockRecord.setInspectionDate(checkAndAcceptSheet.getCheckDate());
				// 检验员
				if (checkAndAcceptSheet.getCheckUser() != null)
					inStockRecord.setInspector(checkAndAcceptSheet.getCheckUser());
				// 合同编号
				if (checkAndAcceptSheet.getContratNumber() != null)
					inStockRecord.setContratNumber(checkAndAcceptSheet.getContratNumber());
				// 件号
				if (checkAndAcceptSheet.getPartNumber() != null)
					inStockRecord.setPartNumber(checkAndAcceptSheet.getPartNumber());
				// 序号/批号
				if (checkAndAcceptSheet.getPartSerialNumber() != null)
					inStockRecord.setPartSerialNumber(checkAndAcceptSheet.getPartSerialNumber());
				// 数量
				inStockRecord.setQuantity(Integer.valueOf(checkAndAcceptSheet.getQuantity()));
				// 供货单位
				if (checkAndAcceptSheet.getProviderName() != null)
					inStockRecord.setSupplyUnit(checkAndAcceptSheet.getProviderName());
				// 是否时控件
				if (checkAndAcceptSheet.getIsTimePart() != null)
					inStockRecord.setIsTimePart(checkAndAcceptSheet.getIsTimePart());
				// 时控件寿命期
				if (checkAndAcceptSheet.getStorageRacksLife() != null)
					inStockRecord.setUsefulTimePeriod(checkAndAcceptSheet.getStorageRacksLife());
				// 时控件寿命时限
				if (checkAndAcceptSheet.getLimitDate() != null)
					inStockRecord.setLifeTimeDate(checkAndAcceptSheet.getLimitDate());
				// 寿命件
				if (checkAndAcceptSheet.getIsLifePart() != null)
					inStockRecord.setIsLifePart(checkAndAcceptSheet.getIsLifePart());
				// 寿命件寿命期
				if (checkAndAcceptSheet.getLifePartCycle() != null)
					inStockRecord.setUsefulLifePeriod(checkAndAcceptSheet.getLifePartCycle());
				// 寿命件寿命时限
				if (checkAndAcceptSheet.getLifeDate() != null)
					inStockRecord.setLimitDate(checkAndAcceptSheet.getLifeDate());
				// 备件状况
				if (checkAndAcceptSheet.getPartStatus() != null)
					inStockRecord.setPartStatusCode(checkAndAcceptSheet.getPartStatus());
				// 下达发卡状态
				inStockRecord.setSendCardStatus(SendStatus.NotSend);
				// 下达上架状态
				inStockRecord.setShelvingStatus(SendStatus.NotSend);
				// 入库状态
				inStockRecord.setInStockStatus(InStockStatus.NotInStock);

				session.saveOrUpdate(inStockRecord);
			} else if (checkAndAcceptSheet.getCheckResult() != null &&
					checkAndAcceptSheet.getCheckResult().equals("验收不合格"))
			{
				NonconformingReport nonconformingReport = new NonconformingReport();

				// 供货单位
				if (checkAndAcceptSheet.getProviderName() != null)
					nonconformingReport.setSupplyUnit(checkAndAcceptSheet.getProviderName());
				// 合同编号
				if (checkAndAcceptSheet.getContratNumber() != null)
					nonconformingReport.setContratNumber(checkAndAcceptSheet.getContratNumber());
				// 检验单号
				if (checkAndAcceptSheet.getCheckAndAcceptSheetNumber() != null)
					nonconformingReport.setCheckAndAcceptSheetNumber(checkAndAcceptSheet.getCheckAndAcceptSheetNumber());
				// 件号
				if (checkAndAcceptSheet.getPartNumber() != null)
					nonconformingReport.setPartNumber(checkAndAcceptSheet.getPartNumber());
				// 序号批号
				if (checkAndAcceptSheet.getPartSerialNumber() != null)
					nonconformingReport.setPartSerialNumber(checkAndAcceptSheet.getPartSerialNumber());
				// 数量
				nonconformingReport.setQuantity(String.valueOf(checkAndAcceptSheet.getQuantity()));
				// 未处理
				nonconformingReport.setState("1");
				
				session.saveOrUpdate(nonconformingReport);
			}
		}
		
		checkAndAcceptSheet.setCreateBy(GwtActionHelper.getCurrUser());
		checkAndAcceptSheet.setCreateDate(new Date());
		getSession().saveOrUpdate(checkAndAcceptSheet);
		
		// 更新收料明细为已检验
		strHql = "update ReceivingSheetItems set isCheck = '1' where id = :receivingSheetItemsID";
		Query query = session.createQuery(strHql);
		query.setString("receivingSheetItemsID", checkAndAcceptSheet.getReceivingSheetItemsID());
		query.executeUpdate();
		
		// 更新证书存档位置为已使用
		strHql = "update Credentials set credentialsUseStatus = '" + CredentialsUseStatus.Used + "' ";
		strHql = strHql + "where credentialsCode = '" + credentialsCode + "'";
		query = session.createQuery(strHql);
		query.executeUpdate();
		
		return checkAndAcceptSheet;
	}
}
