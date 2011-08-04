package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.DeliveryOrderItemsManager;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

@Service
@Transactional
public class DeliveryOrderItemsManagerImpl extends
		CommonManagerImpl<PickingListPartItems> implements DeliveryOrderItemsManager {

	@Override
	public List<PickingListPartItems> getDeliveryOrderItems(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.LOCK_VER, ";
		strQuery = strQuery + "a.VERSION, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 应发数量
		strQuery = strQuery + "a.QUANTITY, ";
		// 单位
		strQuery = strQuery + "c.UNIT_MEASURE_CODE, ";
		// 备件类型
		strQuery = strQuery + "c.SPARE_PART_CLASS_CODE, ";
		// 备件名称
		strQuery = strQuery + "c.KEYWORD_ZH, ";
		// 制造商代码
		strQuery = strQuery + "d.CODE ";
		
		// 来源表
		strQuery = strQuery + "from SPMS_PICKUP_DELIVERY_ITEMS a ";
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
		if (values != null && values.containsKey("orderID") && values.get("orderID") != null) {
			strWhere = strWhere + " and a.ORDER_ID = '" + values.get("orderID").toString() + "'";
		}
		
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<PickingListPartItems> pickingListPartItemsList = new ArrayList<PickingListPartItems>();
		for (Object[] objects : result)
		{
			PickingListPartItems pickingListPartItems = new PickingListPartItems();		
			if (objects[0] != null)
				pickingListPartItems.setId(objects[0].toString());
			if (objects[1] != null)
				pickingListPartItems.setLockVersion(Integer.valueOf(objects[1].toString()));
			if (objects[2] != null)
				pickingListPartItems.setVersion(Integer.valueOf(objects[2].toString()));
			if (objects[3] != null)
				pickingListPartItems.setPartNumber(objects[3].toString());
			if (objects[4] != null)
				pickingListPartItems.setQty(objects[4].toString());
			if (objects[5] != null)
				pickingListPartItems.setUnit(UnitOfMeasureCode.valueOf(objects[5].toString()));
			if (objects[6] != null)
				pickingListPartItems.setPartType(SparePartClassCode.valueOf(objects[6].toString()));
			if (objects[7] != null)
				pickingListPartItems.setPartName(objects[7].toString());
			if (objects[8] != null)
				pickingListPartItems.setManufacturer(objects[8].toString());
			
			pickingListPartItemsList.add(pickingListPartItems);
		}
		
		return pickingListPartItemsList;
	}
}
