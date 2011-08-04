package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationItems;


/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ImportCustomsDeclarationItemsManagerImpl extends CommonManagerImpl<ImportCustomsDeclarationItems> implements ImportCustomsDeclarationItemsManager{

	@Override
	public void insertImportCustomsDeclarationItems(ImportCustomsDeclarationItems importCustomsDeclarationItems) {
		importCustomsDeclarationItems.setCreateBy(GwtActionHelper.getCurrUser());
		importCustomsDeclarationItems.setCreateDate(new Date());
		getSession().saveOrUpdate(importCustomsDeclarationItems);
	}

	@Override
	public List<ImportCustomsDeclarationItems> getImportCustomsDeclarationItems(Map map, int startRow, int endRow) {

		
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.AMOUT ";
		strQuery = strQuery + ",a.CUSTOMS_ID ";
		strQuery = strQuery + ",a.FREE_GOODS_PROPERTIE ";
		strQuery = strQuery + ",a.INVOICE_GUARANTY_AMOUNT ";
		strQuery = strQuery + ",a.INVOIC_RATES ";
		strQuery = strQuery + ",a.INVOICE_TAX_AMOUT ";
		strQuery = strQuery + ",a.INVOICE_UNIT ";
		strQuery = strQuery + ",a.ITEM_NUMBER ";
		strQuery = strQuery + ",a.NAME ";
		strQuery = strQuery + ",a.ORDER_ID ";
		strQuery = strQuery + ",a.PART_NUMBER ";
		strQuery = strQuery + ",a.PAY_TAFIFF_PRICE ";
		strQuery = strQuery + ",a.PAY_TAXES_PRICE ";
		strQuery = strQuery + ",a.QUANTITY ";
		strQuery = strQuery + ",a.TAFIFF_PAYMENT ";
		strQuery = strQuery + ",a.TARIFF_RATE ";
		strQuery = strQuery + ",a.TAX_FILE_NUMBER ";
		strQuery = strQuery + ",a.TAX_PAYMENT ";
		strQuery = strQuery + ",a.TAX_RATE ";
		strQuery = strQuery + ",a.UNIT_PRICE ";
		strQuery = strQuery + ",a.COUNTRY_OF_ORIGIN ";
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		strQuery = strQuery + ",c.KEYWORD_ZH ";

		// 来源表
		strQuery = strQuery + "from SPMS_IMPORT_CUS_DEC_ITEMS a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(map, ImportCustomsDeclarationItems.class, "a.", true, null);
		
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<ImportCustomsDeclarationItems> importCustomsDeclarationItemsList = new ArrayList<ImportCustomsDeclarationItems>();
		for (Object[] objects : result)
		{
			ImportCustomsDeclarationItems importCustomsDeclarationItems = new ImportCustomsDeclarationItems();
			if (objects[0] != null)
				importCustomsDeclarationItems.setId(objects[0].toString());
			if (objects[1] != null)
				importCustomsDeclarationItems.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					importCustomsDeclarationItems.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[3] != null)
				importCustomsDeclarationItems.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				importCustomsDeclarationItems.setKeyword(objects[4].toString());
			if (objects[5] != null)
				importCustomsDeclarationItems.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				importCustomsDeclarationItems.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)				
				importCustomsDeclarationItems.setAmount(objects[7].toString());
			if (objects[8] != null)				
				importCustomsDeclarationItems.setCustomsID(objects[8].toString());
			if (objects[9] != null)				
				importCustomsDeclarationItems.setFreeGoodsPropertie(objects[9].toString());
			if (objects[10] != null)				
				importCustomsDeclarationItems.setInvoiceGuarantyAmount(objects[10].toString());
			if (objects[11] != null)				
				importCustomsDeclarationItems.setInvoiceRates(objects[11].toString());
			if (objects[12] != null)				
				importCustomsDeclarationItems.setInvoiceTaxAmount(objects[12].toString());
			if (objects[13] != null)				
				importCustomsDeclarationItems.setInvoiceUnit(objects[13].toString());
			if (objects[14] != null)				
				importCustomsDeclarationItems.setItemNumber(objects[14].toString());
			if (objects[15] != null)				
				importCustomsDeclarationItems.setName(objects[15].toString());
			if (objects[16] != null)				
				importCustomsDeclarationItems.setOrderID(objects[16].toString());
			if (objects[17] != null)				
				importCustomsDeclarationItems.setPartNumber(objects[17].toString());
			if (objects[18] != null)				
				importCustomsDeclarationItems.setPayTafiffPrice(objects[18].toString());
			if (objects[19] != null)				
				importCustomsDeclarationItems.setPayTaxesPrice(objects[19].toString());
			if (objects[20] != null)				
				importCustomsDeclarationItems.setQuantity(objects[20].toString());
			if (objects[21] != null)				
				importCustomsDeclarationItems.setTafiffPayment(objects[21].toString());
			if (objects[22] != null)				
				importCustomsDeclarationItems.setTariffRate(objects[22].toString());
			if (objects[23] != null)				
				importCustomsDeclarationItems.setTaxFileNumber(objects[23].toString());
			if (objects[24] != null)				
				importCustomsDeclarationItems.setTaxPayment(objects[24].toString());
			if (objects[25] != null)				
				importCustomsDeclarationItems.setTaxRate(objects[25].toString());
			if (objects[26] != null)				
				importCustomsDeclarationItems.setUnitPrice(objects[26].toString());
			if (objects[27] != null)				
				importCustomsDeclarationItems.setCountryOfOrigin(objects[27].toString());
			if (objects[28] != null)				
				importCustomsDeclarationItems.setUnitOfMeasure(UnitOfMeasureCode.valueOf(objects[28].toString()));
			if (objects[29] != null)				
				importCustomsDeclarationItems.setPartName(objects[29].toString());
			
			importCustomsDeclarationItemsList.add(importCustomsDeclarationItems);
		}
		
		return importCustomsDeclarationItemsList;
	}
}
