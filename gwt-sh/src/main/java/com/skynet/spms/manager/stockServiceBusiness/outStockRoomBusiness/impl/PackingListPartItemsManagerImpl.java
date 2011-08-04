package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListPartItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

@Service
@Transactional
public class PackingListPartItemsManagerImpl extends CommonManagerImpl<PackingListPartItems> implements PackingListPartItemsManager{
	@Autowired
	private PickingListPartItemsManager pickingListPartItemsManager;

	@Override
	public List<PackingListPartItems> getPackingListPartItems(Map map, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		// 装箱单ID
		strQuery = strQuery + ",a.PACKING_LIST_ID ";
		// 件号
		strQuery = strQuery + ",a.PART_NUMBER ";
		// 序号/批号
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		// 实发数量
		strQuery = strQuery + ",a.SEND_QTY ";
		// 随件证件
		strQuery = strQuery + ",a.CERTIFICATE_TYPE ";
		// 分箱号
		strQuery = strQuery + ",a.BOX_ID ";
		// 单位
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		// 备件名称
		strQuery = strQuery + ",c.KEYWORD_ZH ";
		// 制造商代码
		strQuery = strQuery + ",d.CODE ";
		
		// 来源表
		strQuery = strQuery + "from SPMS_PACKING_LIST_PART_ITEMS a ";
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
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(map, PackingListPartItems.class, "a.", true, null);
		
		// 字段排序
		String strOrder = "order by a.PART_NUMBER, a.PART_SERIAL_NUMBER asc ";
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<PackingListPartItems> packingListPartItemsList = new ArrayList<PackingListPartItems>();
		for (Object[] objects : result)
		{
			PackingListPartItems packingListPartItems = new PackingListPartItems();		
			if (objects[0] != null)
				packingListPartItems.setId(objects[0].toString());
			if (objects[1] != null)
				packingListPartItems.setLockVersion(Integer.valueOf(objects[1].toString()));
			if (objects[2] != null)
				packingListPartItems.setVersion(Integer.valueOf(objects[2].toString()));
			if (objects[3] != null)
				packingListPartItems.setPackingListID(objects[3].toString());
			if (objects[4] != null)
				packingListPartItems.setPartNumber(objects[4].toString());
			if (objects[5] != null)
				packingListPartItems.setPartSerialNumber(objects[5].toString());
			if (objects[6] != null)
				packingListPartItems.setSendQty(Double.valueOf(objects[6].toString()));
			if (objects[7] != null)
				packingListPartItems.setCertificateType(objects[7].toString());
			if (objects[8] != null)
				packingListPartItems.setBoxID(objects[8].toString());
			if (objects[9] != null)
				packingListPartItems.setUnit(UnitOfMeasureCode.valueOf(objects[9].toString()));
			if (objects[10] != null)
				packingListPartItems.setPartName(objects[10].toString());
			if (objects[11] != null)
				packingListPartItems.setManufacturer(objects[11].toString());
			
			packingListPartItemsList.add(packingListPartItems);
		}
		
		return packingListPartItemsList;
	}
	
	@Override
	public void savePackingListPartItems(Map map) {
		// 配料单ID
		String pickingListID = "";
		// 装箱单ID
		String packingListID = "";
		// 装箱单备件明细ID
		ArrayList packingListPartItemsIDs = new ArrayList();
		// 分箱号
		ArrayList boxIDs = new ArrayList();
		if (map.containsKey("pickingListID"))
		{
			pickingListID = map.get("pickingListID").toString();
		}
		if (map.containsKey("packingListID"))
		{
			packingListID = map.get("packingListID").toString();
		}
		if (map.containsKey("packingListPartItemsID"))
		{
			packingListPartItemsIDs = (ArrayList)map.get("packingListPartItemsID");
		}
		if (map.containsKey("boxID"))
		{
			boxIDs = (ArrayList)map.get("boxID");
		}
		
		Map newMap = new HashMap();
		newMap.put("pickingListID", pickingListID);
		List<PickingListPartItems> pickingListPartItemsList = pickingListPartItemsManager.getPickingListPartItems(newMap, 0, -1);
		
		for (int i = 0; i < pickingListPartItemsList.size(); i++)
		{
			PackingListPartItems packingListPartItems = new PackingListPartItems();
			PickingListPartItems pickingListPartItems = (PickingListPartItems)pickingListPartItemsList.get(i);

			// 装箱单备件明细ID
			if (packingListPartItemsIDs.size() > 0 && packingListPartItemsIDs.get(i) != null)
			{
				packingListPartItems.setId(packingListPartItemsIDs.get(i).toString());	
			}
			// 分箱号
			if (boxIDs.size() > 0 && boxIDs.get(i) != null)
			{
				packingListPartItems.setBoxID(boxIDs.get(i).toString());	
			}
			
			// 装箱单ID
			packingListPartItems.setPackingListID(packingListID);
			// 件号
			packingListPartItems.setPartNumber(pickingListPartItems.getPartNumber());
			// 序号/批号
			packingListPartItems.setPartSerialNumber(pickingListPartItems.getPartSerialNumber());
			// 制造商
			packingListPartItems.setManufacturer(pickingListPartItems.getManufacturer());
			// 实发数量
			packingListPartItems.setSendQty(Double.valueOf(pickingListPartItems.getSendQty()));
			// 收料追溯号
			packingListPartItems.setReceivingSheetID("");
			// 随件证件
			packingListPartItems.setCertificateType("");
			// 状态
			packingListPartItems.setStatus("");

			packingListPartItems.setCreateBy(GwtActionHelper.getCurrUser());
			packingListPartItems.setCreateDate(new Date());

			// 保存装箱单备件明细数据
			getSession().saveOrUpdate(packingListPartItems);
		}
	}
}