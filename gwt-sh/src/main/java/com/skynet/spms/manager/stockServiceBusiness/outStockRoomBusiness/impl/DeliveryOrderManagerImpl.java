package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.DeliveryOrderManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

@Service
@Transactional
public class DeliveryOrderManagerImpl extends
		CommonManagerImpl<PickingList> implements DeliveryOrderManager {

	@Override
	public List<PickingList> getDeliveryOrder(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.id ";
		strQuery = strQuery + ",a.orderNumber ";
		strQuery = strQuery + ",a.businessType ";
		strQuery = strQuery + ",a.contractNumber ";
		strQuery = strQuery + ",a.companyOfConsignee ";
		strQuery = strQuery + ",a.priority ";
		strQuery = strQuery + ",a.deliveryDate ";
		// 发货人姓名
		strQuery = strQuery + ",a.shipper ";
		// 发货单位
		strQuery = strQuery + ",a.companyOfShipper ";
		// 发货地址及详细地址
		strQuery = strQuery + ",a.addressOfShipper ";
		// 发货人联系方式
		strQuery = strQuery + ",a.telephonOfShipper ";
		// 收货人姓名
		strQuery = strQuery + ",a.consignee ";
		// 收货单位
		strQuery = strQuery + ",a.companyOfConsignee ";
		// 收货地址及详细地址
		strQuery = strQuery + ",a.addressOfConsignee ";
		// 收货人联系方式
		strQuery = strQuery + ",a.telephoneOfConsignee ";
		strQuery = strQuery + "from PickupDeliveryOrder a ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and a.pickupDeliveryOrderType = '2' ";
		strWhere = strWhere + " and a.isPicking = '0' ";
		strWhere = strWhere + " and a.isPublish = '1' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				PickupDeliveryOrder.class,
				"a.",
				false,
				null);
		// 字段排序
		String strOrder = "order by a.orderNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<PickingList> pickingListList = new ArrayList<PickingList>();
		for (Object[] objects : result)
		{
			PickingList pickingList = new PickingList();

			// 指令ID
			if (objects[0] != null)
				pickingList.setOrderID(objects[0].toString());
			// 指令单编号
			if (objects[1] != null)
				pickingList.setOrderNumber(objects[1].toString());
			// 业务类型
			if (objects[2] != null)
				pickingList.setBusinessType(BusinessType.valueOf(objects[2].toString()));
			// 合同编号
			if (objects[3] != null)
				pickingList.setContractNumber(objects[3].toString());
			// 收货单位
			if (objects[4] != null)
				pickingList.setCompanyOfConsignee(objects[4].toString());
			// 优先级
			if (objects[5] != null)
				pickingList.setPriority(Priority.valueOf(objects[5].toString()));
			// 交货日期
			if (objects[6] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					pickingList.setDeliveryDate(sdf.parse(objects[6].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// 发货人姓名
			if (objects[7] != null)
				pickingList.setShipper(objects[7].toString());
			// 发货单位
			if (objects[8] != null)
				pickingList.setCompanyOfShipper(objects[8].toString());
			// 发货地址及详细地址
			if (objects[9] != null)
				pickingList.setAddressOfShipper(objects[9].toString());
			// 发货人联系方式
			if (objects[10] != null)
				pickingList.setTelephonOfShipper(objects[10].toString());
			// 收货人姓名
			if (objects[11] != null)
				pickingList.setConsignee(objects[11].toString());
			// 收货单位
			if (objects[12] != null)
				pickingList.setCompanyOfConsignee(objects[12].toString());
			// 收货地址及详细地址
			if (objects[13] != null)
				pickingList.setAddressOfConsignee(objects[13].toString());
			// 收货人联系方式
			if (objects[14] != null)
				pickingList.setTelephoneOfConsignee(objects[14].toString());
			
			pickingListList.add(pickingList);
		}
		
		return pickingListList;
	}
}
