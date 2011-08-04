package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

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
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.spmsdd.TradeMethods;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class PickingListManagerImpl extends CommonManagerImpl<PickingList> implements PickingListManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<PickingList> getPickingList(Map values, int startRow, int endRow)
	{
		String strQuery = "select ";
		// ID
		strQuery = strQuery + "a.ID ";
		// 配料单号
		strQuery = strQuery + ",a.PICKING_LIST_NUMBER ";
		// 业务类型
		strQuery = strQuery + ",a.BUSINESS_TYPE ";
		// 指令ID
		strQuery = strQuery + ",a.ORDER_ID ";
		// 指令单号
		strQuery = strQuery + ",a.ORDER_NUMBER ";
		// 合同编号
		strQuery = strQuery + ",a.CONTRACT_NUMBER ";
		// 优先级
		strQuery = strQuery + ",a.PRIORITY ";
		// 交货日期
		strQuery = strQuery + ",a.DELIVERY_DATE ";
		// 贸易方式
		strQuery = strQuery + ",a.TRADE_METHODS ";
		// 业务员
		strQuery = strQuery + ",a.OPERATOR ";
		// 机号
		strQuery = strQuery + ",a.MACHINE_NUMBER ";
		// 是否保税
		strQuery = strQuery + ",a.IS_BONDED ";
		// 状态
		strQuery = strQuery + ",a.STATUS ";
		// 备注
		strQuery = strQuery + ",a.MEMO ";
		// 发货人姓名
		strQuery = strQuery + ",b.SHIPPER ";
		// 发货单位
		strQuery = strQuery + ",b.COMPANY_OF_SHIPPER ";
		// 发货地址及详细地址
		strQuery = strQuery + ",b.ADDRESS_OF_SHIPPER ";
		// 发货人联系方式
		strQuery = strQuery + ",b.TELEPHON_OF_SHIPPER ";
		// 收货人姓名
		strQuery = strQuery + ",b.CONSIGNEE ";
		// 收货单位
		strQuery = strQuery + ",b.COMPANY_OF_CONSIGNEE ";
		// 收货地址及详细地址
		strQuery = strQuery + ",b.ADDRESS_OF_CONSIGNEE ";
		// 收货人联系方式
		strQuery = strQuery + ",b.TELEPHONE_OF_CONSIGNEE ";
		strQuery = strQuery + "from SPMS_PICKING_LIST a ";
		strQuery = strQuery + "left join SPMS_PICKUP_DELIVERY_ORDER b ";
		strQuery = strQuery + "on a.ORDER_ID = b.ID ";
		
		// 查询条件
		String strWhere = "where a.IS_DELETED = 0 ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				PickingList.class,
				"a.",
				true,
				null);
		
		// 排序设置
		String strOrder = " order by a.PICKING_LIST_NUMBER desc ";

		List<PickingList> pickingLists = new ArrayList<PickingList>();
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		for (Object[] objects : result)
		{
			PickingList pickingList = new PickingList();
			// ID
			if (objects[0] != null)
				pickingList.setId(objects[0].toString());
			// 配料单号
			if (objects[1] != null)
				pickingList.setPickingListNumber(objects[1].toString());
			// 业务类型
			if (objects[2] != null)
				pickingList.setBusinessType(BusinessType.valueOf(objects[2].toString()));
			// 指令ID
			if (objects[3] != null)
				pickingList.setOrderID(objects[3].toString());
			// 指令单号
			if (objects[4] != null)
				pickingList.setOrderNumber(objects[4].toString());
			// 合同编号
			if (objects[5] != null)
				pickingList.setContractNumber(objects[5].toString());
			// 优先级
			if (objects[6] != null)
				pickingList.setPriority(Priority.valueOf(objects[6].toString()));
			// 交货日期
			if (objects[7] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					pickingList.setDeliveryDate(sdf.parse(objects[7].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			// 贸易方式
			if (objects[8] != null)
				pickingList.setTradeMethods(TradeMethods.valueOf(objects[8].toString()));
			// 业务员
			if (objects[9] != null)
				pickingList.setOperator(objects[9].toString());
			// 机号
			if (objects[10] != null)
				pickingList.setMachineNumber(objects[10].toString());
			// 是否保税
			if (objects[11] != null)
				pickingList.setIsBonded(objects[11].toString());
			// 状态
			if (objects[12] != null)
				pickingList.setStatus(OutStockStatus.valueOf(objects[12].toString()));
			// 备注
			if (objects[13] != null)
				pickingList.setMemo(objects[13].toString());
			// 发货人姓名
			if (objects[14] != null)
				pickingList.setShipper(objects[14].toString());
			// 发货单位
			if (objects[15] != null)
				pickingList.setCompanyOfShipper(objects[15].toString());
			// 发货地址及详细地址
			if (objects[16] != null)
				pickingList.setAddressOfShipper(objects[16].toString());
			// 发货人联系方式
			if (objects[17] != null)
				pickingList.setTelephonOfShipper(objects[17].toString());
			// 收货人姓名
			if (objects[18] != null)
				pickingList.setConsignee(objects[18].toString());
			// 收货单位
			if (objects[19] != null)
				pickingList.setCompanyOfConsignee(objects[19].toString());
			// 收货地址及详细地址
			if (objects[20] != null)
				pickingList.setAddressOfConsignee(objects[20].toString());
			// 收货人联系方式
			if (objects[21] != null)
				pickingList.setTelephoneOfConsignee(objects[21].toString());
			pickingLists.add(pickingList);
		}
		return pickingLists;
	}

	@Override
	public void updateStatus(String[] pickingListIDs, String status)
	{
		Session session = getSession();		

		for (int i = 0; i < pickingListIDs.length; i++)
		{
			// 更新配料单的状态
			String strHql = "update PickingList set status = :status where id = :pickingListID";
			Query pickingListQuery = session.createQuery(strHql);
			pickingListQuery.setString("status", status);
			pickingListQuery.setString("pickingListID", pickingListIDs[i].toString());
			pickingListQuery.executeUpdate();
		}
	}

	@Override
	public void deletePickingList(String number)
	{
		Session session = getSession();
		
		// 更新配料单的删除标志为删除状态
		String delHql = "update PickingList set deleted = 1 where id = :pickingListID";
		Query pickingListQuery = session.createQuery(delHql);
		pickingListQuery.setString("pickingListID", number);
		pickingListQuery.executeUpdate();

		// 更新配料单明细的删除标志为删除状态
		delHql = "update PickingListPartItems set deleted = 1 where pickingListID = :pickingListID";
		Query pickingListPartItemsQuery = session.createQuery(delHql);
		pickingListPartItemsQuery.setString("pickingListID", number);
		pickingListPartItemsQuery.executeUpdate();
	}

	@Override
	public PickingList SavePickingList(PickingList pickingList)
	{
		Session session = getSession();
		if (pickingList.getId() == null || pickingList.getId().equals("")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("PKG");
			// 收料单编号
			pickingList.setPickingListNumber(curNO);
		}
		pickingList.setCreateBy(GwtActionHelper.getCurrUser());
		pickingList.setCreateDate(new Date());
		// 保存配料单基本信息
		session.saveOrUpdate(pickingList);
		
		// 更新提货指令为已收料
		String strHql = "update PickupDeliveryOrder set isPicking = '1' where id = :orderID";
		Query query = getSession().createQuery(strHql);
		query.setString("orderID", pickingList.getOrderID());
		query.executeUpdate();
		
		return pickingList;
	}
}