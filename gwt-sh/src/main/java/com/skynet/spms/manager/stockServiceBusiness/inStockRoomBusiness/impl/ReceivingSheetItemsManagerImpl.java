package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetItemsManager;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;

@Service
@Transactional
public class ReceivingSheetItemsManagerImpl extends
		CommonManagerImpl<ReceivingSheetItems> implements ReceivingSheetItemsManager {
	
	@Override
	public ReceivingSheetItems deleteReceivingSheetItems(String receivingSheetItemsID) {

		Criteria criteria = getSession().createCriteria(ReceivingSheetItems.class);
		criteria.add(Restrictions.eq("id", receivingSheetItemsID));
		ReceivingSheetItems receivingSheetItems = (ReceivingSheetItems)criteria.uniqueResult();
		receivingSheetItems.setDeleted(true);
		receivingSheetItems.setCreateBy(GwtActionHelper.getCurrUser());
		receivingSheetItems.setCreateDate(new Date());
		getSession().saveOrUpdate(receivingSheetItems);
		return receivingSheetItems;
	}

	@Override
	public List<ReceivingSheetItems> getReceivingSheetItems(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.ITEM_NUMBER ";
		strQuery = strQuery + ",a.PART_NUMBER ";
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		strQuery = strQuery + ",c.KEYWORD_ZH ";
		strQuery = strQuery + ",a.QUANTITY ";
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		strQuery = strQuery + ",c.SPARE_PART_CLASS_CODE ";
		strQuery = strQuery + ",a.BOX_NO ";
		strQuery = strQuery + ",case when c.IS_SERIAL = 1 then 'true' else 'false' end ";
		strQuery = strQuery + ",a.PACKING_LIST_NUMBER ";
		// 来源表
		strQuery = strQuery + "from SPMS_RECEIVING_SHEET_ITEMS a ";
		strQuery = strQuery + "left join SPMS_PART_INDEX b ";
		strQuery = strQuery + "on a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "and b.IS_DELETED = '0' ";
		strQuery = strQuery + "left join SPMS_BASIC_INFO c ";
		strQuery = strQuery + "on b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "and c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		if (values != null && values.containsKey("receivingSheetID") && values.get("receivingSheetID") != null) {
			strWhere = strWhere + " and a.RECEIVING_SHEET_ID = '" + values.get("receivingSheetID").toString() + "'";
		}
		// 字段排序
		String strOrder = "order by a.PART_NUMBER, a.PART_SERIAL_NUMBER asc ";
		Map extraKey = new HashMap();
		extraKey.put("type", "type");
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values, ReceivingSheetItems.class, "a.", true, extraKey);
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<ReceivingSheetItems> receivingSheetList = new ArrayList<ReceivingSheetItems>();
		for (Object[] objects : result)
		{
			ReceivingSheetItems receivingSheetItems = new ReceivingSheetItems();				
			if (objects[0] != null)
				receivingSheetItems.setId(objects[0].toString());
			if (objects[1] != null)
				receivingSheetItems.setLockVersion(Integer.valueOf(objects[1].toString()));
			if (objects[2] != null)
				receivingSheetItems.setVersion(Integer.valueOf(objects[2].toString()));
			if (objects[3] != null)
				receivingSheetItems.setItemNumber(objects[3].toString());
			if (objects[4] != null)
				receivingSheetItems.setPartNumber(objects[4].toString());
			if (objects[5] != null)
				receivingSheetItems.setPartSerialNumber(objects[5].toString());
			if (objects[6] != null)
				receivingSheetItems.setPartName(objects[6].toString());
			if (objects[7] != null)
				receivingSheetItems.setQuantity(Integer.valueOf(objects[7].toString()));
			if (objects[8] != null)
				receivingSheetItems.setPartUnit(UnitOfMeasureCode.valueOf(objects[8].toString()));
			if (objects[9] != null)
				receivingSheetItems.setPartType(SparePartClassCode.valueOf(objects[9].toString()));
			if (objects[10] != null)
				receivingSheetItems.setBoxNO(objects[10].toString());
			if (objects[11] != null)
				receivingSheetItems.setIsSerial(Boolean.valueOf(objects[11].toString()));
			if (objects[12] != null)
				receivingSheetItems.setPackingListNumber(objects[12].toString());
			
			receivingSheetList.add(receivingSheetItems);
		}
		
		return receivingSheetList;
	}
}
