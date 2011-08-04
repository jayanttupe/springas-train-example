package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

@Service
@Transactional
public class PickingListPartItemsManagerImpl extends CommonManagerImpl<PickingListPartItems> implements PickingListPartItemsManager{

	@Override
	public List<PickingListPartItems> getPickingListPartItems(Map map, int startRow, int endRow) {
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID, ";
		strQuery = strQuery + "a.LOCK_VER, ";
		strQuery = strQuery + "a.VERSION, ";
		// 件号
		strQuery = strQuery + "a.PART_NUMBER, ";
		// 发货指令明细ID
		strQuery = strQuery + "a.DELIVERY_VANNING_ITEMS_ID, ";
		// 序号/批号
		strQuery = strQuery + "a.PART_SERIAL_NUMBER, ";
		// 实发数量
		strQuery = strQuery + "a.SEND_QTY, ";
		// 备件状况
		strQuery = strQuery + "a.PART_STATUS, ";
		// 库房编号
		strQuery = strQuery + "a.STOCK_ROOM_NUMBER, ";
		// 货位编号
		strQuery = strQuery + "a.CARGO_SPACE_NUMBER, ";
		// 剩余寿命
		strQuery = strQuery + "a.LIFE, ";
		// 状态
		strQuery = strQuery + "a.STATUS, ";
		// 备注
		strQuery = strQuery + "a.MEMO, ";
		// 配料单号
		strQuery = strQuery + "a.PICKINGLIST_ID, ";
		// 单位
		strQuery = strQuery + "c.UNIT_MEASURE_CODE, ";
		// 备件类型
		strQuery = strQuery + "c.SPARE_PART_CLASS_CODE, ";
		// 备件名称
		strQuery = strQuery + "c.KEYWORD_ZH, ";
		// 制造商代码
		strQuery = strQuery + "d.CODE ";
		
		// 来源表
		strQuery = strQuery + "from SPMS_PICKING_LIST_PART_ITEMS a ";
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
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(map, PickingListPartItems.class, "a.", true, null);
		
		// 字段排序
		String strOrder = "order by a.PART_NUMBER, a.PART_SERIAL_NUMBER asc ";
		
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
				pickingListPartItems.setDeliveryVanningItemsID(objects[4].toString());
			if (objects[5] != null)
				pickingListPartItems.setPartSerialNumber(objects[5].toString());
			if (objects[6] != null)
				pickingListPartItems.setSendQty(Double.valueOf(objects[6].toString()));
			if (objects[7] != null)
				pickingListPartItems.setPartStatus(objects[7].toString());
			if (objects[8] != null)
				pickingListPartItems.setStockRoomNumber(objects[8].toString());
			if (objects[9] != null)
				pickingListPartItems.setCargoSpaceNumber(objects[9].toString());
			if (objects[10] != null)
				pickingListPartItems.setLife(objects[10].toString());
			if (objects[11] != null)
				pickingListPartItems.setStatus(objects[11].toString());
			if (objects[12] != null)
				pickingListPartItems.setMemo(objects[12].toString());
			if (objects[13] != null)
				pickingListPartItems.setPickingListID(objects[13].toString());
			if (objects[14] != null)
				pickingListPartItems.setUnit(UnitOfMeasureCode.valueOf(objects[14].toString()));
			if (objects[15] != null)
				pickingListPartItems.setPartType(SparePartClassCode.valueOf(objects[15].toString()));
			if (objects[16] != null)
				pickingListPartItems.setPartName(objects[16].toString());
			if (objects[17] != null)
				pickingListPartItems.setManufacturer(objects[17].toString());
			
			pickingListPartItemsList.add(pickingListPartItems);
		}
		
		return pickingListPartItemsList;
	}

	public void savePickingListPartItems(Map newValues)
	{
		// 件号
		ArrayList partNumber = (ArrayList)newValues.get("partNumber");
		// 序号/批号
		ArrayList partSerialNumber = (ArrayList)newValues.get("partSerialNumber");
		// 实发数量
		ArrayList sendQty = (ArrayList)newValues.get("sendQty");
		// 单位
		ArrayList unit = (ArrayList)newValues.get("unit");
		// 备件状况
		ArrayList partStatus = (ArrayList)newValues.get("partStatus");
		// 库房编号
		ArrayList stockRoomNumber = (ArrayList)newValues.get("stockRoomNumber");
		// 货位编号
		ArrayList cargoSpaceNumber = (ArrayList)newValues.get("cargoSpaceNumber");
		// 配料单ID
		ArrayList pickingListID = (ArrayList)newValues.get("pickingListID");
		// 配料单明细ID
		ArrayList pickinglistPartItemsID = (ArrayList)newValues.get("pickinglistPartItemsID");

		for (int i = 0; i < partNumber.size(); i++)
		{
			PickingListPartItems pickingListPartItems = new PickingListPartItems();
			// 件号
			if (partNumber != null && partNumber.size() >= i+1 && partNumber.get(i) != null)
				pickingListPartItems.setPartNumber(partNumber.get(i).toString());
			// 序号/批号
			if (partSerialNumber != null && partSerialNumber.size() >= i+1 && partSerialNumber.get(i) != null)
				pickingListPartItems.setPartSerialNumber(partSerialNumber.get(i).toString());
			// 实发数量
			if (sendQty != null && sendQty.size() >= i+1 && sendQty.get(i) != null)
				pickingListPartItems.setSendQty(Double.valueOf(sendQty.get(i).toString()));
			// 单位
			if (unit != null && unit.size() >= i+1 && unit.get(i) != null)
				pickingListPartItems.setUnit(UnitOfMeasureCode.valueOf(unit.get(i).toString()));
			// 备件状况
			if (partStatus != null && partStatus.size() >= i+1 && partStatus.get(i) != null)
				pickingListPartItems.setPartStatus(partStatus.get(i).toString());
			// 库房编号
			if (stockRoomNumber != null && stockRoomNumber.size() >= i+1 && stockRoomNumber.get(i) != null)
				pickingListPartItems.setStockRoomNumber(stockRoomNumber.get(i).toString());
			// 货位编号
			if (cargoSpaceNumber != null && cargoSpaceNumber.size() >= i+1 && cargoSpaceNumber.get(i) != null)
				pickingListPartItems.setCargoSpaceNumber(cargoSpaceNumber.get(i).toString());
			// 配料单ID
			if (pickingListID != null && pickingListID.size() >= i+1 && pickingListID.get(i) != null)
				pickingListPartItems.setPickingListID(pickingListID.get(i).toString());
			// 配料单明细ID
			if (pickinglistPartItemsID != null && pickinglistPartItemsID.size() >= i+1 && pickinglistPartItemsID.get(i) != null)
				pickingListPartItems.setId(pickinglistPartItemsID.get(i).toString());
			
			if (pickingListPartItems.getId() != null && !pickingListPartItems.getId().equals(""))
			{
				String delHql = "delete from PickingListPartItems where id = :id";
				Query query = getSession().createQuery(delHql);
		        query.setString("id", pickingListPartItems.getId());
		        query.executeUpdate();
			}
			
			if (pickingListPartItems.getSendQty() != 0) {
				insertEntity(pickingListPartItems);	
			}
		}
	}
}