/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItemsManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItems;

/**
 * @author Administrator
 */
@Service
@Transactional
public class ExportCustomsDeclarationItemsManagerImpl extends CommonManagerImpl<ExportCustomsDeclarationItems> implements ExportCustomsDeclarationItemsManager{

	@Override
	public void insertExportCustomsDeclarationItems(ExportCustomsDeclarationItems exportCustomsDeclarationItems) {
		exportCustomsDeclarationItems.setCreateBy(GwtActionHelper.getCurrUser());
		exportCustomsDeclarationItems.setCreateDate(new Date());
		getSession().saveOrUpdate(exportCustomsDeclarationItems);
	}

	@Override
	public List<ExportCustomsDeclarationItems> getExportCustomsDeclarationItems(Map map, int startRow, int endRow) {
		
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
		strQuery = strQuery + "from SPMS_EXPORT_CUS_DEC_ITEMS a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(map, ExportCustomsDeclarationItems.class, "a.", true, null);
		
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<ExportCustomsDeclarationItems> exportCustomsDeclarationItemsList = new ArrayList<ExportCustomsDeclarationItems>();
		for (Object[] objects : result)
		{
			ExportCustomsDeclarationItems exportCustomsDeclarationItems = new ExportCustomsDeclarationItems();
			if (objects[0] != null)
				exportCustomsDeclarationItems.setId(objects[0].toString());
			if (objects[1] != null)
				exportCustomsDeclarationItems.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					exportCustomsDeclarationItems.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[3] != null)
				exportCustomsDeclarationItems.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				exportCustomsDeclarationItems.setKeyword(objects[4].toString());
			if (objects[5] != null)
				exportCustomsDeclarationItems.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				exportCustomsDeclarationItems.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)				
				exportCustomsDeclarationItems.setAmount(objects[7].toString());
			if (objects[8] != null)				
				exportCustomsDeclarationItems.setCustomsID(objects[8].toString());
			if (objects[9] != null)				
				exportCustomsDeclarationItems.setFreeGoodsPropertie(objects[9].toString());
			if (objects[10] != null)				
				exportCustomsDeclarationItems.setInvoiceGuarantyAmount(objects[10].toString());
			if (objects[11] != null)				
				exportCustomsDeclarationItems.setInvoiceRates(objects[11].toString());
			if (objects[12] != null)				
				exportCustomsDeclarationItems.setInvoiceTaxAmount(objects[12].toString());
			if (objects[13] != null)				
				exportCustomsDeclarationItems.setInvoiceUnit(objects[13].toString());
			if (objects[14] != null)				
				exportCustomsDeclarationItems.setItemNumber(objects[14].toString());
			if (objects[15] != null)				
				exportCustomsDeclarationItems.setName(objects[15].toString());
			if (objects[16] != null)				
				exportCustomsDeclarationItems.setOrderID(objects[16].toString());
			if (objects[17] != null)				
				exportCustomsDeclarationItems.setPartNumber(objects[17].toString());
			if (objects[18] != null)				
				exportCustomsDeclarationItems.setPayTafiffPrice(objects[18].toString());
			if (objects[19] != null)				
				exportCustomsDeclarationItems.setPayTaxesPrice(objects[19].toString());
			if (objects[20] != null)				
				exportCustomsDeclarationItems.setQuantity(objects[20].toString());
			if (objects[21] != null)				
				exportCustomsDeclarationItems.setTafiffPayment(objects[21].toString());
			if (objects[22] != null)				
				exportCustomsDeclarationItems.setTariffRate(objects[22].toString());
			if (objects[23] != null)				
				exportCustomsDeclarationItems.setTaxFileNumber(objects[23].toString());
			if (objects[24] != null)				
				exportCustomsDeclarationItems.setTaxPayment(objects[24].toString());
			if (objects[25] != null)				
				exportCustomsDeclarationItems.setTaxRate(objects[25].toString());
			if (objects[26] != null)				
				exportCustomsDeclarationItems.setUnitPrice(objects[26].toString());
			if (objects[27] != null)				
				exportCustomsDeclarationItems.setCountryOfOrigin(objects[27].toString());
			if (objects[28] != null)				
				exportCustomsDeclarationItems.setUnitOfMeasure(UnitOfMeasureCode.valueOf(objects[28].toString()));
			if (objects[29] != null)				
				exportCustomsDeclarationItems.setPartName(objects[29].toString());
			
			exportCustomsDeclarationItemsList.add(exportCustomsDeclarationItems);
		}
		return exportCustomsDeclarationItemsList;
	}
}
