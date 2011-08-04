package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CredentialsRecordManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;
/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class CredentialsRecordManagerImpl extends CommonManagerImpl<CheckAndAcceptSheet> implements CredentialsRecordManager{

	@Override
	public List<CheckAndAcceptSheet> getCredentialsRecord(Map values, int startRow, int endRow) {
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		// 证书存档编号
		strQuery = strQuery + "a.CERTIFICATE_CODE, ";
		// 电子证件文件
		strQuery = strQuery + "(a.CERTIFICATE_CODE || '.pdf') as CERTIFICATE_FILE_NAME, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 序号/批号
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		// 数量
		strQuery = strQuery + "a.QUANTITY, ";
		// 单位
		strQuery = strQuery + "c.UNIT_MEASURE_CODE ";
		
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
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere).list();
		List<CheckAndAcceptSheet> checkAndAcceptSheetList = new ArrayList<CheckAndAcceptSheet>();
		for (Object[] objects : result)
		{
			CheckAndAcceptSheet checkAndAcceptSheet = new CheckAndAcceptSheet();

			if (objects[0] != null)
				checkAndAcceptSheet.setCertificateCode(objects[0].toString());
			if (objects[1] != null)
				checkAndAcceptSheet.setCertificateFileName(objects[1].toString());
			if (objects[2] != null)
				checkAndAcceptSheet.setPartNumber(objects[2].toString());
			if (objects[3] != null)
				checkAndAcceptSheet.setPartSerialNumber(objects[3].toString());
			if (objects[4] != null)
				checkAndAcceptSheet.setQuantity(Integer.valueOf(objects[4].toString()));
			if (objects[5] != null)
				checkAndAcceptSheet.setUnit(UnitOfMeasureCode.valueOf(objects[5].toString()));
			
			checkAndAcceptSheetList.add(checkAndAcceptSheet);
		}
		return checkAndAcceptSheetList;
	}

}
