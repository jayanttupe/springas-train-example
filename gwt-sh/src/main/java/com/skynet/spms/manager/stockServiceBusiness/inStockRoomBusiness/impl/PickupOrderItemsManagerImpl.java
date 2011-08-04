package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.PickupOrderItemsManager;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;

@Service
@Transactional
public class PickupOrderItemsManagerImpl extends
		CommonManagerImpl<ReceivingSheetItems> implements PickupOrderItemsManager {

	@Override
	public List<ReceivingSheetItems> getPickupOrderItems(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.PART_NUMBER ";
		strQuery = strQuery + ",d.KEYWORD_ZH ";
		strQuery = strQuery + ",a.QUANTITY ";
		strQuery = strQuery + ",d.UNIT_MEASURE_CODE ";
		strQuery = strQuery + ",d.SPARE_PART_CLASS_CODE ";
		strQuery = strQuery + ",b.PACAKGE_NUMBER ";
		strQuery = strQuery + ",case when d.IS_SERIAL = 1 then 'true' else 'false' end ";
		strQuery = strQuery + ",b.PACKING_LIST_NUMBER ";
		strQuery = strQuery + "from SPMS_PICKUP_DELIVERY_ITEMS a ";
		strQuery = strQuery + "INNER JOIN SPMS_PICKUP_DELIVERY_VANNING b ";
		strQuery = strQuery + "ON a.VANNING_ID = b.ID ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX c ";
		strQuery = strQuery + "ON a.PART_NUMBER = c.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO d ";
		strQuery = strQuery + "ON c.BASIC_INFO_ID = d.ID ";
		strQuery = strQuery + "AND d.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		if (values != null && values.containsKey("orderID") && values.get("orderID") != null) {
			strWhere = strWhere + " and a.ORDER_ID = '" + values.get("orderID").toString() + "'";
		}
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<ReceivingSheetItems> receivingSheetList = new ArrayList<ReceivingSheetItems>();
		int itemNumber = 0;
		for (Object[] objects : result)
		{
			ReceivingSheetItems receivingSheetItems = new ReceivingSheetItems();
			// 项号
			itemNumber = itemNumber + 1;
			receivingSheetItems.setItemNumber(String.valueOf(String.format("%03d", itemNumber)));
			// 件号
			if (objects[0] != null)
				receivingSheetItems.setPartNumber(objects[0].toString());
			// 件名称
			if (objects[1] != null)
				receivingSheetItems.setPartName(objects[1].toString());
			// 数量
			if (objects[2] != null)
				receivingSheetItems.setQuantity(Integer.valueOf(objects[2].toString()));
			// 单位
			if (objects[3] != null)
				receivingSheetItems.setPartUnit(UnitOfMeasureCode.valueOf(objects[3].toString()));
			// 备件类型
			if (objects[4] != null)
				receivingSheetItems.setPartType(SparePartClassCode.valueOf(objects[4].toString()));
			// 分箱号
			if (objects[5] != null)
				receivingSheetItems.setBoxNO(objects[5].toString());
			// 是否序号控制
			if (objects[6] != null)
				receivingSheetItems.setIsSerial(Boolean.valueOf(objects[6].toString()));
			// 装箱单号
			if (objects[7] != null)
				receivingSheetItems.setPackingListNumber(objects[7].toString());
			
			receivingSheetList.add(receivingSheetItems);
		}
		
		return receivingSheetList;
	}
}
