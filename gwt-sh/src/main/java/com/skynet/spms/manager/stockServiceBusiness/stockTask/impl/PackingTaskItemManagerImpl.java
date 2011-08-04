package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PackingListManager;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListManager;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PackingTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListBoxItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

@Service
@Transactional
public class PackingTaskItemManagerImpl extends CommonManagerImpl<PackingTaskItem>
		implements PackingTaskItemManager {

	@Autowired
	private PackingListManager packingListManager;
	
	@Autowired
	private PickingListManager pickingListManager;
	
	@Autowired
	private PickingListPartItemsManager pickingListPartItemsManager;
	
	@Override
	public List<PackingTaskItem> getPackingTaskItem(Map values, int startRow, int endRow) {
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.PART_NUMBER ";
		strQuery = strQuery + ",c.KEYWORD_ZH ";
		strQuery = strQuery + ",c.UNIT_MEASURE_CODE ";
		strQuery = strQuery + ",a.PART_SERIAL_NUMBER ";
		strQuery = strQuery + ",a.QUANTITY ";
		strQuery = strQuery + ",a.TASK_ID ";
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";
		strQuery = strQuery + ",a.OPERATION_STATUS ";
		strQuery = strQuery + ",a.BARCODE_TAG_UUID ";
		strQuery = strQuery + ",a.TAG_IDENTIFIER_CODE ";

		// 来源表
		strQuery = strQuery + "FROM SPMS_PACKING_TASK_ITEM a ";
		strQuery = strQuery + "LEFT JOIN SPMS_PART_INDEX b ";
		strQuery = strQuery + "ON a.PART_NUMBER = b.MANUVACTURER_PART_NUMBER ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_BASIC_INFO c ";
		strQuery = strQuery + "ON b.BASIC_INFO_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				PackingTaskItem.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.PART_NUMBER asc ";

		String strSql = strQuery + strWhere + strOrder;
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<PackingTaskItem> shelvingTaskItemList = new ArrayList<PackingTaskItem>();
		for (Object[] objects : result)
		{
			PackingTaskItem packingTaskItem = new PackingTaskItem();
			if (objects[0] != null)
				packingTaskItem.setId(objects[0].toString());
			if (objects[1] != null)
				packingTaskItem.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					packingTaskItem.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)
				packingTaskItem.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				packingTaskItem.setKeyword(objects[4].toString());
			if (objects[5] != null)
				packingTaskItem.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				packingTaskItem.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				packingTaskItem.setPartNumber(objects[7].toString());
			if (objects[8] != null)
				packingTaskItem.setPartName(objects[8].toString());
			if (objects[9] != null)
				packingTaskItem.setPartUnit(objects[9].toString());
			if (objects[10] != null)
				packingTaskItem.setPartSerialNumber(objects[10].toString());
			if (objects[11] != null)
				packingTaskItem.setQuantity(Double.valueOf(objects[11].toString()));
			if (objects[12] != null)
				packingTaskItem.setTaskID(objects[12].toString());
			if (objects[13] != null)
				packingTaskItem.setCargoSpaceNumber(objects[13].toString());
			if (objects[14] != null)
				packingTaskItem.setOperationStatus(TaskItemStatus.valueOf(objects[14].toString()));
			if (objects[15] != null)
				packingTaskItem.setBarcodeTagUUID(objects[15].toString());
			if (objects[16] != null)
				packingTaskItem.setTagIdentifierCode(objects[16].toString());

			shelvingTaskItemList.add(packingTaskItem);
		}
		
		return shelvingTaskItemList;
	}
	
	/**
	 * 更新装箱任务信息
	 */
	@Override
	public Boolean updatePackingTaskItem(StockTask stockTask, List<PackingTaskItem> pickingTaskItemList){
		Session session = getSession();
		// 任务编号
		String taskNumber = stockTask.getTaskNo();
		Criteria taskCriteria= session.createCriteria(StockTask.class);
		taskCriteria.add(Restrictions.eq("taskNo", taskNumber));

		List<StockTask> dbStockTaskList = (List<StockTask>)taskCriteria.list();
		StockTask dbStockTask = (StockTask)dbStockTaskList.get(0);
		// 任务执行设备号
		if (stockTask.getActionDevice() != null) {
			dbStockTask.setActionDevice(stockTask.getActionDevice());	
		}
		// 任务执行人
		if (stockTask.getActionBy() != null) {
			dbStockTask.setActionBy(stockTask.getActionBy());	
		}
		// 任务执行日期
		if (stockTask.getActionDate() != null) {
			dbStockTask.setActionDate(stockTask.getActionDate());
		}
		// 任务状态
		if (stockTask.getTaskStatus() != null) {
			dbStockTask.setTaskStatus(stockTask.getTaskStatus());
		}
		// 更新任务主信息表
		session.saveOrUpdate(dbStockTask);
		
		for (int i = 0; i < pickingTaskItemList.size(); i++) {
			PackingTaskItem packingTaskItem = (PackingTaskItem)pickingTaskItemList.get(i);
			// 条码标签唯一编号
			String barcodeTagUUID = packingTaskItem.getBarcodeTagUUID();
			// RFID标签唯一序列号
			String tagIdentifierCode = packingTaskItem.getTagIdentifierCode();

			// 取得任务明细记录信息
			Criteria pickingTaskItemCriteria= session.createCriteria(PackingTaskItem.class);
			if (barcodeTagUUID != null && !barcodeTagUUID.equals("")) {
				pickingTaskItemCriteria.add(Restrictions.eq("barcodeTagUUID", barcodeTagUUID));	
			}
			if (tagIdentifierCode != null && !tagIdentifierCode.equals("")) {
				pickingTaskItemCriteria.add(Restrictions.eq("tagIdentifierCode", tagIdentifierCode));	
			}
			pickingTaskItemCriteria.add(Restrictions.eq("deleted", false));
			PackingTaskItem dbPackingTaskItem = (PackingTaskItem)pickingTaskItemCriteria.list().get(0);
			// 后处理建议
			if (packingTaskItem.getPostaction() != null) {
				dbPackingTaskItem.setPostaction(packingTaskItem.getPostaction());
			}
			// 操作状态
			dbPackingTaskItem.setOperationStatus(TaskItemStatus.OVR);
			// 更新任务明细信息表
			session.saveOrUpdate(dbPackingTaskItem);
		}
		
		// 生成装箱单信息
		createPackingInfo(session, dbStockTask, pickingTaskItemList);
		
		return true;
	}
	
	/**
	 * 生成装箱单信息
	 * @param session
	 * @param dbStockTask
	 * @param pickingTaskItemList
	 */
	private void createPackingInfo(Session session,
			StockTask dbStockTask,
			List<PackingTaskItem> pickingTaskItemList)
	{
		// 根据拣货单ID取得拣货单相关信息
		Map newMap = new HashMap();
		newMap.put("id", dbStockTask.getBussinessBillNO());
		PickingList pickingList = pickingListManager.getPickingList(newMap, 0, -1).get(0);
		
		// 生成装箱单基本数据
		PackingList packingList = new PackingList();
		// 配料单ID
		packingList.setPickingListID(pickingList.getId());
		// 配料单号
		packingList.setPickingListNumber(pickingList.getPickingListNumber());
		// 业务类型
		packingList.setBusinessType(pickingList.getBusinessType());
		// 指令ID
		packingList.setOrderID(pickingList.getOrderID());
		// 指令编号
		packingList.setOrderNumber(pickingList.getOrderNumber());
		// 合同编号
		packingList.setContractNumber(pickingList.getContractNumber());
		// 优先级
		packingList.setPriority(pickingList.getPriority());
		// 交货日期
		packingList.setDeliveryDate(pickingList.getDeliveryDate());

		// 根据分箱号进行排序
		Collections.sort(pickingTaskItemList, new Comparator<PackingTaskItem>() {
			@Override
			public int compare(PackingTaskItem o1, PackingTaskItem o2) {
				return o1.getBoxNO().compareTo(o2.getBoxNO());
			}
		});
		String oldBoxNO = "";
		List<PackingListPartItems> packingListPartItemsList = new ArrayList<PackingListPartItems>(); 
		List<PackingListBoxItems> packingListBoxItemsList = new ArrayList<PackingListBoxItems>(); 
		for (int i = 0; i < pickingTaskItemList.size(); i++) {
			PackingTaskItem packingTaskItem = pickingTaskItemList.get(i);
			// 根据拣货单明细ID取得拣货单明细相关信息
			Map newDetailMap = new HashMap();
			newDetailMap.put("partNumber", packingTaskItem.getPartNumber());
			newDetailMap.put("partSerialNumber", packingTaskItem.getPartSerialNumber());
			PickingListPartItems pickingListPartItems = pickingListPartItemsManager.getPickingListPartItems(newDetailMap, 0, -1).get(0);

			// 生成装箱单明细数据
			PackingListPartItems packingListPartItems = new PackingListPartItems();
			// 发货指令明细ID
			packingListPartItems.setDeliveryVanningItemsID(pickingListPartItems.getDeliveryVanningItemsID());
			// 件号
			packingListPartItems.setPartNumber(pickingListPartItems.getPartNumber());
			// 序号/批号
			packingListPartItems.setPartSerialNumber(pickingListPartItems.getPartSerialNumber());
			// 分箱号
			packingListPartItems.setBoxID(packingTaskItem.getBoxNO());
			// 实发数量
			packingListPartItems.setSendQty(packingTaskItem.getSendQty());
			packingListPartItemsList.add(packingListPartItems);

			// 生成装箱单中箱子的数据
			if (packingTaskItem.getBoxNO() != null && !packingTaskItem.getBoxNO().equals(oldBoxNO)) {
				oldBoxNO = packingTaskItem.getBoxNO();
				PackingListBoxItems packingListBoxItems = new PackingListBoxItems();
				// 箱号
				packingListBoxItems.setBoxNumber(packingTaskItem.getBoxNO());
				// 包装材质
				packingListBoxItems.setPackagingCode(packingTaskItem.getPackagingMaterial());
				// RFID标签唯一编号
				packingListBoxItems.setPackingRFIDTagUUID(packingTaskItem.getPackingRFIDTagUUID());
				
				packingListBoxItemsList.add(packingListBoxItems);
			}
		}
		
		// 设置装箱单明细数据
		packingList.setPackingListPartItemsList(packingListPartItemsList);
		// 保存装箱单信息
		packingListManager.savePackingList(packingList);
		
		for (int i = 0; i < packingListBoxItemsList.size(); i++) {
			PackingListBoxItems packingListBoxItems = packingListBoxItemsList.get(i);
			packingListBoxItems.setPackingListID(packingList.getId());
			// 保存装箱单箱的信息
			session.saveOrUpdate(packingListBoxItems);
		}
	}
}