package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.GenerateNumberManager;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;
import com.skynet.spms.persistence.entity.spmsdd.OutStockStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class PackingListManagerImpl extends CommonManagerImpl<PackingList> implements PackingListManager{
	@Autowired
	GenerateNumberManager generateNumberManager;
	@Autowired
	UUIDGeneral uUIDGeneral;

	@Override
	public List<PackingList> getPackingList(Map values, int startRow, int endRow)
	{
		String strQuery = "select ";
		// ID
		strQuery = strQuery + "a.ID ";
		// 装箱单编号
		strQuery = strQuery + ",a.PACKING_LIST_NUMBER ";
		// 配料单ID
		strQuery = strQuery + ",a.PICKING_LIST_ID ";
		// 配料单号
		strQuery = strQuery + ",a.PICKING_LIST_NUMBER ";
		// 业务类型
		strQuery = strQuery + ",a.BUSINESS_TYPE ";
		// 指令编号
		strQuery = strQuery + ",a.ORDER_NUMBER ";
		// 合同编号
		strQuery = strQuery + ",a.CONTRACT_NUMBER ";
		// 优先级
		strQuery = strQuery + ",a.PRIORITY ";
		// 交货日期
		strQuery = strQuery + ",a.DELIVERY_DATE ";
		// 箱数
		strQuery = strQuery + ",a.BOX_QTY ";
		// 状态
		strQuery = strQuery + ",a.STATUS ";
		// 版本锁
		strQuery = strQuery + ",a.LOCK_VER ";
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
		
		strQuery = strQuery + "from SPMS_PACKING_LIST a ";
		strQuery = strQuery + "left join SPMS_PICKUP_DELIVERY_ORDER b ";
		strQuery = strQuery + "on a.ORDER_ID = b.ID ";
		
		// 查询条件
		String strWhereString = "where a.IS_DELETED = 0 ";
		if (values.containsKey("type")) {
			if (values.get("type").equals("notRelease")) {
				strWhereString = strWhereString + "and (a.STATUS = '" + String.valueOf(OutStockStatus.Packing) +
					"' or a.STATUS = '" + String.valueOf(OutStockStatus.Pack) + "') ";		
			} else if (values.get("type").equals("release")) {
				strWhereString = strWhereString + "and a.STATUS = '" + String.valueOf(OutStockStatus.CheckOut) + "' ";
			}
		}
		String strOrder  = " order by a.PACKING_LIST_NUMBER desc ";

		Map extraKey = new HashMap();
		extraKey.put("type", "type");
		strWhereString = strWhereString + SqlHelperTool.createSqlOrHqlCondition(values, PackingList.class, "a.", true, extraKey);

		List<PackingList> packingLists = new ArrayList<PackingList>();
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhereString + strOrder).list();
		for (Object[] objects : result)
		{
			PackingList packingList = new PackingList();
			// ID
			if (objects[0] != null)
				packingList.setId(objects[0].toString());
			// 装箱单编号
			if (objects[1] != null)
				packingList.setPackingListNumber(objects[1].toString());
			// 配料单ID
			if (objects[2] != null)
				packingList.setPickingListID(objects[2].toString());
			// 配料单号
			if (objects[3] != null)
				packingList.setPickingListNumber(objects[3].toString());
			// 业务类型
			if (objects[4] != null)
				packingList.setBusinessType(BusinessType.valueOf(objects[4].toString()));
			// 指令编号
			if (objects[5] != null)
				packingList.setOrderNumber(objects[5].toString());
			// 合同编号
			if (objects[6] != null)
				packingList.setContractNumber(objects[6].toString());
			// 优先级
			if (objects[7] != null)
				packingList.setPriority(Priority.valueOf(objects[7].toString()));
			// 交货日期
			if (objects[8] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					packingList.setDeliveryDate(sdf.parse(objects[8].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			// 箱数
			if (objects[9] != null)
				packingList.setBoxQty(Integer.valueOf(objects[9].toString()));
			// 状态
			if (objects[10] != null)
				packingList.setStatus(OutStockStatus.valueOf(objects[10].toString()));
			// 版本锁
			if (objects[11] != null)
				packingList.setLockVersion(Integer.valueOf(objects[11].toString()));
			// 发货人姓名
			if (objects[12] != null)
				packingList.setShipper(objects[12].toString());
			// 发货单位
			if (objects[13] != null)
				packingList.setCompanyOfShipper(objects[13].toString());
			// 发货地址及详细地址
			if (objects[14] != null)
				packingList.setAddressOfShipper(objects[14].toString());
			// 发货人联系方式
			if (objects[15] != null)
				packingList.setTelephonOfShipper(objects[15].toString());
			// 收货人姓名
			if (objects[16] != null)
				packingList.setConsignee(objects[16].toString());
			// 收货单位
			if (objects[17] != null)
				packingList.setCompanyOfConsignee(objects[17].toString());
			// 收货地址及详细地址
			if (objects[18] != null)
				packingList.setAddressOfConsignee(objects[18].toString());
			// 收货人联系方式
			if (objects[19] != null)
				packingList.setTelephoneOfConsignee(objects[19].toString());
			
			packingLists.add(packingList);
		}
		return packingLists;
	}

	@Override
	public PackingList updateStatus(Map values)
	{
		Session session = getSession();		
		ArrayList packinglistIDs = (ArrayList)values.get("packingListID");
		String status = (String)values.get("status");

		for (int i = 0; i < packinglistIDs.size(); i++)
		{
			// 更新配料单的状态
			String strHql = "update PackingList set status = :status where id = :packingListID";
			Query packingListQuery = session.createQuery(strHql);
			packingListQuery.setString("status", status);
			packingListQuery.setString("packingListID", packinglistIDs.get(i).toString());
			packingListQuery.executeUpdate();
		}

		Map newMap = new HashMap();
		newMap.put("id", packinglistIDs.get(0).toString());
		return getPackingList(newMap, 0, -1).get(0);
	}
	
	@Override
	public PackingList savePackingList(PackingList packingList) {
		Session session = getSession();
		if (packingList.getId() == null || packingList.getId().equals("")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("PAC");
			// 装箱单编号
			packingList.setPackingListNumber(curNO);
			packingList.setStatus(OutStockStatus.Packing);
		}
		packingList.setCreateBy(GwtActionHelper.getCurrUser());
		packingList.setCreateDate(new Date());
		// 保存装箱单信息
		session.saveOrUpdate(packingList);

		// 删除物流的装箱信息
		String strDelHql = "delete from PickupDeliveryVanning where orderID = :orderID";
		Query delQuery = session.createQuery(strDelHql);
		delQuery.setString("orderID", packingList.getOrderID());
		delQuery.executeUpdate();
		
		Map boxIDMap = new HashMap();
		List<PackingListPartItems> packingListPartItemsList = packingList.getPackingListPartItemsList();
		for (int i = 0; i < packingListPartItemsList.size(); i++) {
			PackingListPartItems packingListPartItems = packingListPartItemsList.get(i);
			String boxID = packingListPartItems.getBoxID();
			if (boxID == null || boxID.equals("")) {
				continue;
			}
			
			if (!boxIDMap.containsKey(boxID)) {
				String strQueryHql = "select boxNumber from PackingListBoxItems where id = :packingListBoxItemsID";
				Query strQuery = session.createQuery(strQueryHql);
				strQuery.setString("packingListBoxItemsID", packingListPartItems.getBoxID());
				String boxNO = (String)strQuery.uniqueResult();
				
				PickupDeliveryVanning pickupDeliveryVanning = new PickupDeliveryVanning();
				// 指令ID
				pickupDeliveryVanning.setOrderID(packingList.getOrderID());
				// 箱号
				pickupDeliveryVanning.setPacakgeNumber(boxNO);
				// 装箱单号
				pickupDeliveryVanning.setPackingListNumber(packingList.getPackingListNumber());
				// 插入物流的装箱信息
				session.saveOrUpdate(pickupDeliveryVanning);
				boxIDMap.put(boxID, boxID);
			}
		}

		// 更新状态为装箱中
		String strHql = "update PickingList set status = '" + String.valueOf(OutStockStatus.Packing) + "' where id = :pickingListID";
		Query pickingListQuery = session.createQuery(strHql);
		pickingListQuery.setString("pickingListID", packingList.getPickingListID());
		pickingListQuery.executeUpdate();
		
		return packingList;
	}
}