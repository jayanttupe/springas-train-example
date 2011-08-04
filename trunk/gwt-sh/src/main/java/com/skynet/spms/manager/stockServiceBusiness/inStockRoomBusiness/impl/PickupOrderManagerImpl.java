package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.PickupOrderManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

@Service
@Transactional
public class PickupOrderManagerImpl extends
		CommonManagerImpl<ReceivingSheet> implements PickupOrderManager {

	@Override
	public List<ReceivingSheet> getPickupOrder(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.BUSINESS_TYPE, ";
		strQuery = strQuery + "a.CONTRACT_NUMBER, ";
		strQuery = strQuery + "a.MAIN_WAY_BILL, ";
		strQuery = strQuery + "a.CREATE_BY, ";
		strQuery = strQuery + "a.CREATE_DATE, ";
		strQuery = strQuery + "a.ORDER_NUMBER ";
		strQuery = strQuery + "from SPMS_PICKUP_DELIVERY_ORDER a ";
		// 查询条件
		String strWhere = "where a.PICKUP_DELIVERY_ORDER_TYPE = '1' ";
		strWhere = strWhere + " and a.STATUS = '3' ";
		strWhere = strWhere + " and a.IS_RECEIVING <> '1' ";
		// 字段排序
		String strOrder = "order by a.ORDER_NUMBER desc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<ReceivingSheet> receivingSheetList = new ArrayList<ReceivingSheet>();
		for (Object[] objects : result)
		{
			ReceivingSheet receivingSheet = new ReceivingSheet();
			// 指令ID
			if (objects[0] != null)
				receivingSheet.setOrderID(objects[0].toString());
			// 业务类型
			if (objects[1] != null)
				receivingSheet.setBusinessType(BusinessType.valueOf(objects[1].toString()));
			// 合同编号
			if (objects[2] != null)
				receivingSheet.setContractNumber(objects[2].toString());
			// 主运单号
			if (objects[3] != null)
				receivingSheet.setMainWayBill(objects[3].toString());
			// 物流操作员
			if (objects[4] != null)
				receivingSheet.setLogisticsCreateBy(objects[4].toString());
			// 物流操日期
			if (objects[5] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					receivingSheet.setLogisticsCreateDate(sdf.parse(objects[5].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			// 指令单编号
			if (objects[6] != null)
				receivingSheet.setOrderNumber(objects[6].toString());
			
			receivingSheetList.add(receivingSheet);
		}
		
		return receivingSheetList;
	}
}
