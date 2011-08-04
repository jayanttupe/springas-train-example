package com.skynet.spms.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PackingTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PickingTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.RepairCodeTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.SendCardTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.ShelvingTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockCheckTaskItemManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.RepairCodeTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockCheckTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;
import com.skynet.spms.webservice.WebServiceUtils;
import com.skynet.spms.webservice.entity.ComplementCodeTaskExeItem;
import com.skynet.spms.webservice.entity.ComplementCodeTaskItem;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.GetTaskDetailsInputParameters;
import com.skynet.spms.webservice.entity.GetTaskDetailsOutputParameters;
import com.skynet.spms.webservice.entity.PackingTaskExeItem;
import com.skynet.spms.webservice.entity.PickingTaskExeItem;
import com.skynet.spms.webservice.entity.QueryTasklistByTAGInputParameters;
import com.skynet.spms.webservice.entity.QueryTasklistInputParameters;
import com.skynet.spms.webservice.entity.SetTaskDetailsExeInputParameters;
import com.skynet.spms.webservice.entity.ShelvingTaskExeItem;
import com.skynet.spms.webservice.entity.StockCountExeItem;
import com.skynet.spms.webservice.entity.TaskStatus;
import com.skynet.spms.webservice.entity.Tasklist;
import com.skynet.spms.webservice.entity.TasklistReocrdsOutputParameters;
import com.skynet.spms.webservice.service.WsTaskService;

@Service
public class WsTaskServiceImpl implements WsTaskService {

	@Autowired
	private StockTaskManager stockTaskManager;
	@Autowired
	private SendCardTaskItemManager sendCardTaskItemManager;
	@Autowired
	private ShelvingTaskItemManager shelvingTaskItemManager;
	@Autowired
	private PickingTaskItemManager pickingTaskItemManager;
	@Autowired
	private PackingTaskItemManager packingTaskItemManager;
	@Autowired
	private RepairCodeTaskItemManager repairCodeTaskItemManager;
	@Autowired
	private StockCheckTaskItemManager stockCheckTaskItemManager;
	
	/**
	 * 取得所有任务列表信息
	 */
	@Override
	public TasklistReocrdsOutputParameters getTasklist(
			QueryTasklistInputParameters request) throws FaultResponse {
		List<Tasklist> tasklists = new ArrayList<Tasklist>();
		Map stockTaskMap = new HashMap();
		stockTaskMap.put("isHandset", "isHandset");
		List<StockTask> stockTaskList = stockTaskManager.getStockTask(stockTaskMap, 0, -1);
		
		for (int i = 0; i < stockTaskList.size(); i++) {
			StockTask stockTask = (StockTask)stockTaskList.get(i);
			Tasklist tasklist = new Tasklist();
			
			// 任务编号
			tasklist.setTaskNumber(stockTask.getTaskNo());
			// 任务类型
			tasklist.setTasktype(WebServiceUtils.convertTaskType(stockTask.getTaskType()));
			// 任务描述
			tasklist.setDescription(stockTask.getDescription());
			// 任务日期
			tasklist.setTaskDate(WebServiceUtils.convertDate(stockTask.getTaskDate()));
			// 任务引用编号
			tasklist.setTaskRefNumber(stockTask.getBussinessBillNO());
			// 任务来源
			tasklist.setTaskSource(stockTask.getTaskSource());
			// OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
			tasklist.setTaskStatus(TaskStatus.valueOf(stockTask.getTaskStatus().toString()));
			// 任务创建者
			tasklist.setCreatBy(stockTask.getCreateBy());
			// 任务执行人
			tasklist.setActionBy(stockTask.getActionBy());
			// 任务执行日期
			tasklist.setActionDate(WebServiceUtils.convertDate(stockTask.getActionDate()));
			// 执行设备号
			tasklist.setActionDevice(stockTask.getActionDevice());
			tasklists.add(tasklist);
		}
		
		TasklistReocrdsOutputParameters result = new TasklistReocrdsOutputParameters();
		result.setTasklist(tasklists);
		return result;
	}

	/**
	 * 取得不同类型的任务详细信息
	 */
	@Override
	public GetTaskDetailsOutputParameters getTaskDetails(
			GetTaskDetailsInputParameters request) throws FaultResponse {
		GetTaskDetailsOutputParameters result = new GetTaskDetailsOutputParameters();
		
		// 取得任务主要信息
		Map stockTaskMap = new HashMap();
		// 任务编号
		String taskNumber = request.getTaskNumber();
		// 设备号
		String equipmentID = request.getEquipmentID();
		stockTaskMap.put("taskNo", taskNumber);
		List<StockTask> stockTask = stockTaskManager.getStockTask(stockTaskMap, 0, -1);
		// 任务编号
		result.setTaskNumber(stockTask.get(0).getTaskNo());
		// 任务类型
		result.setTasktype(WebServiceUtils.convertTaskType(stockTask.get(0).getTaskType()));
		// 任务日期
		result.setTaskDate(WebServiceUtils.convertDate(stockTask.get(0).getTaskDate()));
		// 任务引用编号
		result.setTaskRefNumber(stockTask.get(0).getBussinessBillNO());
		// 任务来源
		result.setTaskSource(stockTask.get(0).getTaskSource());
		// OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
		result.setTaskStatus(TaskStatus.valueOf(stockTask.get(0).getTaskStatus().toString()));

		// 更新任务状态为处理中
		if (equipmentID != null && !equipmentID.equals("00")) {
			StockTask newStockTask = new StockTask();
			newStockTask.setTaskNo(taskNumber);
			stockTaskManager.updateStockTaskStatus(newStockTask);	
		}

		// 取得发卡明细数据
		if (stockTask.get(0).getTaskType().equals(TaskType.SC)) {
			//根据任务ID取得任务明细
			Map shelvingTaskItemMap = new HashMap();
			shelvingTaskItemMap.put("taskID", stockTask.get(0).getId());
			List<SendCardTaskItem> sendCardTaskItemList = sendCardTaskItemManager.getSendCardTaskItem(shelvingTaskItemMap, 0, -1);
			
			for (int i = 0; i < sendCardTaskItemList.size(); i++) {
				SendCardTaskItem sendCardTaskItem = (SendCardTaskItem)sendCardTaskItemList.get(i);
				com.skynet.spms.webservice.entity.SendCardTaskItem newSendCardTaskItem =
					new com.skynet.spms.webservice.entity.SendCardTaskItem();
				// 条码标签唯一编号
				newSendCardTaskItem.setBarcodeTagUUID(sendCardTaskItem.getBarcodeTagUUID());
				// RFID标签唯一序列号
				newSendCardTaskItem.setRFIDTagUUID(sendCardTaskItem.getTagIdentifierCode());
				// 件号
				newSendCardTaskItem.setPartNumber(sendCardTaskItem.getPartNumber());
				// 批次号
				newSendCardTaskItem.setPartSerialNumber(sendCardTaskItem.getPartSerialNumber());
				// 件名称
				newSendCardTaskItem.setPartName(sendCardTaskItem.getPartName());
				// 计量单位
				newSendCardTaskItem.setUnitOfMeasureCode(sendCardTaskItem.getPartUnit());
				// 数量
				newSendCardTaskItem.setQuantity(sendCardTaskItem.getQuantity());
				// 后处理建议
				newSendCardTaskItem.setPostaction(sendCardTaskItem.getPostaction());
				// 操作状态(waiting:待处理/succee:成功/failure:失败)
				newSendCardTaskItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(sendCardTaskItem.getOperationStatus()));
				result.getSendCardTaskItem().add(newSendCardTaskItem);
			}
		}
		// 取得上架明细数据
		if (stockTask.get(0).getTaskType().equals(TaskType.SH)) {
			//根据任务ID取得任务明细
			Map shelvingTaskItemMap = new HashMap();
			shelvingTaskItemMap.put("taskID", stockTask.get(0).getId());
			List<ShelvingTaskItem> stockTaskList = shelvingTaskItemManager.getShelvingTaskItem(shelvingTaskItemMap, 0, -1);
			
			for (int i = 0; i < stockTaskList.size(); i++) {
				ShelvingTaskItem shelvingTaskItem = (ShelvingTaskItem)stockTaskList.get(i);
				com.skynet.spms.webservice.entity.ShelvingTaskItem newShelvingTaskItem =
					new com.skynet.spms.webservice.entity.ShelvingTaskItem();
				// 条码标签唯一编号
				newShelvingTaskItem.setBarcodeTagUUID(shelvingTaskItem.getBarcodeTagUUID());
				// RFID标签唯一序列号
				newShelvingTaskItem.setRFIDTagUUID(shelvingTaskItem.getTagIdentifierCode());
				// 件号
				newShelvingTaskItem.setPartNumber(shelvingTaskItem.getPartNumber());
				// 批次号
				newShelvingTaskItem.setPartSerialNumber(shelvingTaskItem.getPartSerialNumber());
				// 件名称
				newShelvingTaskItem.setPartName(shelvingTaskItem.getPartName());
				// 计量单位
				newShelvingTaskItem.setUnitOfMeasureCode(shelvingTaskItem.getPartUnit());
				// 数量
				newShelvingTaskItem.setQuantity(shelvingTaskItem.getQuantity());
				// 后处理建议
				newShelvingTaskItem.setPostaction(shelvingTaskItem.getPostaction());
				// 操作状态(waiting:待处理/succee:成功/failure:失败)
				newShelvingTaskItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(shelvingTaskItem.getOperationStatus()));
				// 推荐货位
				newShelvingTaskItem.setRecommendLocationNumber(shelvingTaskItem.getRecCargoSpaceNumber());
				result.getShelvingTaskItem().add(newShelvingTaskItem);
			}
		}
		// 取得拣货明细数据
		if (stockTask.get(0).getTaskType().equals(TaskType.PK)) {
			//根据任务ID取得任务明细
			Map pickingTaskItemMap = new HashMap();
			pickingTaskItemMap.put("taskID", stockTask.get(0).getId());
			List<PickingTaskItem> pickingTaskItemList = pickingTaskItemManager.getPickingTaskItem(pickingTaskItemMap, 0, -1);
			
			for (int i = 0; i < pickingTaskItemList.size(); i++) {
				PickingTaskItem pickingTaskItem = (PickingTaskItem)pickingTaskItemList.get(i);
				com.skynet.spms.webservice.entity.PickingTaskItem newPickingTaskItem =
					new com.skynet.spms.webservice.entity.PickingTaskItem();
				// 条码标签唯一编号
				newPickingTaskItem.setBarcodeTagUUID(pickingTaskItem.getBarcodeTagUUID());
				// RFID标签唯一序列号
				newPickingTaskItem.setRFIDTagUUID(pickingTaskItem.getTagIdentifierCode());
				// 件号
				newPickingTaskItem.setPartNumber(pickingTaskItem.getPartNumber());
				// 批次号
				newPickingTaskItem.setPartSerialNumber(pickingTaskItem.getPartSerialNumber());
				// 件名称
				newPickingTaskItem.setPartName(pickingTaskItem.getPartName());
				// 计量单位
				newPickingTaskItem.setUnitOfMeasureCode(pickingTaskItem.getPartUnit());
				// 数量
				newPickingTaskItem.setQuantity(pickingTaskItem.getQuantity());
				// 后处理建议
				newPickingTaskItem.setPostaction(pickingTaskItem.getPostaction());
				// 操作状态(waiting:待处理/succee:成功/failure:失败)
				newPickingTaskItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(pickingTaskItem.getOperationStatus()));
				// 推荐货位
				newPickingTaskItem.setLocationNumber(pickingTaskItem.getCargoSpaceNumber());
				result.getPickingTaskItem().add(newPickingTaskItem);
			}
		}
		// 取得装箱明细数据
		if (stockTask.get(0).getTaskType().equals(TaskType.BX)) {
			//根据任务ID取得任务明细
			Map packingTaskItemMap = new HashMap();
			packingTaskItemMap.put("taskID", stockTask.get(0).getId());
			List<PackingTaskItem> packingTaskItemList = packingTaskItemManager.getPackingTaskItem(packingTaskItemMap, 0, -1);
			
			for (int i = 0; i < packingTaskItemList.size(); i++) {
				PackingTaskItem packingTaskItem = (PackingTaskItem)packingTaskItemList.get(i);
				com.skynet.spms.webservice.entity.PickingTaskItem newPickingTaskItem =
					new com.skynet.spms.webservice.entity.PickingTaskItem();
				// 条码标签唯一编号
				newPickingTaskItem.setBarcodeTagUUID(packingTaskItem.getBarcodeTagUUID());
				// RFID标签唯一序列号
				newPickingTaskItem.setRFIDTagUUID(packingTaskItem.getTagIdentifierCode());
				// 件号
				newPickingTaskItem.setPartNumber(packingTaskItem.getPartNumber());
				// 批次号
				newPickingTaskItem.setPartSerialNumber(packingTaskItem.getPartSerialNumber());
				// 件名称
				newPickingTaskItem.setPartName(packingTaskItem.getPartName());
				// 计量单位
				newPickingTaskItem.setUnitOfMeasureCode(packingTaskItem.getPartUnit());
				// 数量
				newPickingTaskItem.setQuantity(packingTaskItem.getQuantity());
				// 后处理建议
				newPickingTaskItem.setPostaction(packingTaskItem.getPostaction());
				// 操作状态(waiting:待处理/succee:成功/failure:失败)
				newPickingTaskItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(packingTaskItem.getOperationStatus()));
				// 推荐货位
				newPickingTaskItem.setLocationNumber(packingTaskItem.getCargoSpaceNumber());
				result.getPickingTaskItem().add(newPickingTaskItem);
			}
		}
		//取得盘点任务明细
		if (stockTask.get(0).getTaskType().equals(TaskType.ST)) {
			//根据任务ID取得任务明细
			Map stockCheckTaskItemMap = new HashMap();
			stockCheckTaskItemMap.put("taskID", stockTask.get(0).getId());
			List<StockCheckTaskItem> pickingTaskItemList = stockCheckTaskItemManager.getStockCheckTaskItem(stockCheckTaskItemMap, 0, -1);
			
			for (int i = 0; i < pickingTaskItemList.size(); i++) {
				StockCheckTaskItem stockCheckTaskItem = (StockCheckTaskItem)pickingTaskItemList.get(i);
				com.skynet.spms.webservice.entity.StockCountTaskItem newStockCountTaskItem =
					new com.skynet.spms.webservice.entity.StockCountTaskItem();
				// 条码标签唯一编号
				newStockCountTaskItem.setBarcodeTagUUID(stockCheckTaskItem.getBarcodeTagUUID());
				// RFID标签唯一序列号
				newStockCountTaskItem.setRFIDTagUUID(stockCheckTaskItem.getTagIdentifierCode());
				// 件号
				newStockCountTaskItem.setPartNumber(stockCheckTaskItem.getPartNumber());
				// 批次号
				newStockCountTaskItem.setPartSerialNumber(stockCheckTaskItem.getPartSerialNumber());
				// 件名称
				newStockCountTaskItem.setPartName(stockCheckTaskItem.getPartName());
				// 计量单位
				newStockCountTaskItem.setUnitOfMeasureCode(stockCheckTaskItem.getPartUnit());
				// 数量
				newStockCountTaskItem.setQuantity(stockCheckTaskItem.getQuantity());
				// 后处理建议
				newStockCountTaskItem.setPostaction(stockCheckTaskItem.getPostaction());
				// 操作状态(waiting:待处理/succee:成功/failure:失败)
				newStockCountTaskItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(stockCheckTaskItem.getOperationStatus()));
				// 货位编号
				newStockCountTaskItem.setLocationNumber(stockCheckTaskItem.getCargoSpaceNumber());
				result.getStockCountTaskItem().add(newStockCountTaskItem);
			}
		}
		//取得补码任务明细
		if(stockTask.get(0).getTaskType() == TaskType.RC){
			//根据任务ID取得任务明细
			Map<String,Object> valueMap = new HashMap<String,Object>();
			valueMap.put("taskID", stockTask.get(0).getId());
			List<RepairCodeTaskItem> itemList = repairCodeTaskItemManager.getRepairCodeTaskItem(valueMap);
			
			for(int i=0; i<itemList.size(); i++ ){
				//数据库存储的明细项
				RepairCodeTaskItem item = itemList.get(i);
				//对应的WebService明细项
				ComplementCodeTaskItem wsItem = new ComplementCodeTaskItem();
				// 条码标签唯一编号
				wsItem.setBarcodeTagUUID(item.getBarcodeTagUUID());
				//件号
				wsItem.setPartNumber(item.getPartNumber());
				//批次号/序列号
				wsItem.setPartSerialNumber(item.getPartSerialNumber());
				//货位号（当补码为货位时）
				wsItem.setLocationNumber(item.getLocationNumber());
				//件名称
				wsItem.setPartName(item.getPartName());
				//计量单位
				wsItem.setUnitOfMeasureCode(item.getPartUnit());
				//数量
				wsItem.setQuantity(item.getQuantity());
				//后处理建议
				wsItem.setPostaction(item.getPostaction());
				//操作状态
				wsItem.setOperationStatus(WebServiceUtils.convertTaskItemStatus(item.getOperationStatus()));
				
				//将补码明细加入到返回结果集合中
				result.getComplementCodeTaskItem().add(wsItem);
			}
		}

		return result;
	}

	/**
	 * 更新任务明细状态
	 */
	@Override
	public boolean setTaskDetailsExe(SetTaskDetailsExeInputParameters request)
			throws FaultResponse {
		// 上架更新处理
		if (request.getTasktype().equals(com.skynet.spms.webservice.entity.TaskType.SHELVING)) {
			List<ShelvingTaskExeItem> shelvingTaskExeItemList = (List<ShelvingTaskExeItem>)request.getShelvingTaskExeItem();
			
			// 任务主信息
			StockTask stockTask = new StockTask();
			stockTask.setTaskNo(request.getTaskNumber());
			stockTask.setBussinessBillNO(request.getTaskRefNumber());
			stockTask.setActionBy(request.getActionBy());
			stockTask.setActionDate(WebServiceUtils.convertXmlDate(request.getTaskDate()));
			stockTask.setTaskStatus(WebServiceUtils.convertXmlTaskStatus(request.getTaskStatus()));
			
			// 任务明细信息
			List<ShelvingTaskItem> newShelvingTaskItemList = new ArrayList<ShelvingTaskItem>();
			for (int i = 0; i < shelvingTaskExeItemList.size(); i++) {
				ShelvingTaskExeItem shelvingTaskExeItem = (ShelvingTaskExeItem)shelvingTaskExeItemList.get(i);
				
				ShelvingTaskItem shelvingTaskItem = new ShelvingTaskItem();
				shelvingTaskItem.setBarcodeTagUUID(shelvingTaskExeItem.getBarcodeTagUUID());
				shelvingTaskItem.setTagIdentifierCode(shelvingTaskExeItem.getRFIDTagUUID());
				shelvingTaskItem.setPartNumber(shelvingTaskExeItem.getPartNumber());
				shelvingTaskItem.setPartSerialNumber(shelvingTaskExeItem.getPartSerialNumber());
				shelvingTaskItem.setCargoSpaceNumber(shelvingTaskExeItem.getLocationNumber());
				shelvingTaskItem.setPostaction(shelvingTaskExeItem.getPostaction());
				shelvingTaskItem.setOperationStatus(WebServiceUtils.convertXmlTaskItemStatus(shelvingTaskExeItem.getOperationStatus()));;
				newShelvingTaskItemList.add(shelvingTaskItem);
			}
			shelvingTaskItemManager.updateShelvingTaskItem(stockTask, newShelvingTaskItemList);
			return true;
		}
		//拣货任务更新处理
		else if(request.getTasktype().equals(com.skynet.spms.webservice.entity.TaskType.PICKING)){
			//任务主信息
			StockTask stockTask = new StockTask();
			stockTask.setTaskNo(request.getTaskNumber());
			stockTask.setBussinessBillNO(request.getTaskRefNumber());
			stockTask.setActionBy(request.getActionBy());
			stockTask.setActionDate(WebServiceUtils.convertXmlDate(request.getTaskDate()));
			stockTask.setTaskStatus(WebServiceUtils.convertXmlTaskStatus(request.getTaskStatus()));
			
			//任务明细信息
			List<PickingTaskExeItem> pickingTaskExeItemList = request.getPickingTaskExeItem();
			List<PickingTaskItem> newPickTaskItemList = new ArrayList<PickingTaskItem>();
			PickingTaskItem pickingTaskItem = null;
			for(PickingTaskExeItem pickingTaskExeItem : pickingTaskExeItemList){
				pickingTaskItem = new PickingTaskItem();
				pickingTaskItem.setBarcodeTagUUID(pickingTaskExeItem.getBarcodeTagUUID());
				pickingTaskItem.setTagIdentifierCode(pickingTaskExeItem.getRFIDTagUUID());
				pickingTaskItem.setPartNumber(pickingTaskExeItem.getPartNumber());
				pickingTaskItem.setPartSerialNumber(pickingTaskExeItem.getPartSerialNumber());
				pickingTaskItem.setCargoSpaceNumber(pickingTaskExeItem.getLocationNumber());
				pickingTaskItem.setPostaction(pickingTaskExeItem.getPostaction());
				pickingTaskItem.setOperationStatus(WebServiceUtils.convertXmlTaskItemStatus(pickingTaskExeItem.getOperationStatus()));;
				newPickTaskItemList.add(pickingTaskItem);
			}
			pickingTaskItemManager.updatePickingTaskItem(stockTask, newPickTaskItemList);
			return true;
		}
		// 装箱更新处理
		if (request.getTasktype().equals(com.skynet.spms.webservice.entity.TaskType.PACKING)) {
			List<PackingTaskExeItem> packingTaskExeItemList = (List<PackingTaskExeItem>)request.getPackingTaskExeItem();
			
			// 任务主信息
			StockTask stockTask = new StockTask();
			stockTask.setTaskNo(request.getTaskNumber());
			stockTask.setBussinessBillNO(request.getTaskRefNumber());
			stockTask.setActionBy(request.getActionBy());
			stockTask.setActionDate(WebServiceUtils.convertXmlDate(request.getTaskDate()));
			stockTask.setTaskStatus(WebServiceUtils.convertXmlTaskStatus(request.getTaskStatus()));
			
			// 任务明细信息
			List<PackingTaskItem> packingTaskItemList = new ArrayList<PackingTaskItem>();
			for (int i = 0; i < packingTaskExeItemList.size(); i++) {
				PackingTaskExeItem packingTaskExeItem = (PackingTaskExeItem)packingTaskExeItemList.get(i);
				
				PackingTaskItem packingTaskItem = new PackingTaskItem();
				packingTaskItem.setBarcodeTagUUID(packingTaskExeItem.getBarcodeTagUUID());
				packingTaskItem.setTagIdentifierCode(packingTaskExeItem.getRFIDTagUUID());
				packingTaskItem.setPackingRFIDTagUUID(packingTaskExeItem.getPackingRFIDTagUUID());
				packingTaskItem.setPartNumber(packingTaskExeItem.getPartNumber());
				packingTaskItem.setPartSerialNumber(packingTaskExeItem.getPartSerialNumber());
				packingTaskItem.setBoxNO(packingTaskExeItem.getPackingNumber());
				packingTaskItem.setSendQty(packingTaskExeItem.getQuantity());
				packingTaskItem.setPackagingMaterial(packingTaskExeItem.getPackagingMaterial());
				packingTaskItemList.add(packingTaskItem);
			}

			packingTaskItemManager.updatePackingTaskItem(stockTask, packingTaskItemList);
			return true;
		}
		//补码任务更新处理
		if(request.getTasktype() == com.skynet.spms.webservice.entity.TaskType.COMPLEMENT_CODE){
			//主任务信息
			StockTask stockTask = new StockTask();
			stockTask.setTaskNo(request.getTaskNumber());
			stockTask.setBussinessBillNO(request.getTaskRefNumber());
			stockTask.setActionBy(request.getActionBy());
			stockTask.setActionDate(WebServiceUtils.convertXmlDate(request.getTaskDate()));
			stockTask.setTaskStatus(WebServiceUtils.convertXmlTaskStatus(request.getTaskStatus()));
			
			//任务明细信息
			List<ComplementCodeTaskExeItem> wsTaskItemList = request.getComplementCodeTaskExeItem();
			List<RepairCodeTaskItem> taskItemList = new ArrayList<RepairCodeTaskItem>();
			RepairCodeTaskItem repairCodeTaskItem = null;
			for(ComplementCodeTaskExeItem item : wsTaskItemList){
				repairCodeTaskItem = new RepairCodeTaskItem();
				repairCodeTaskItem.setBarcodeTagUUID(item.getBarcodeTagUUID());
				repairCodeTaskItem.setTagIdentifierCode(item.getRFIDTagUUID());
				repairCodeTaskItem.setPostaction(item.getPostaction());
				repairCodeTaskItem.setOperationStatus(WebServiceUtils.convertXmlTaskItemStatus(item.getOperationStatus()));
				taskItemList.add(repairCodeTaskItem);
			}
			
			repairCodeTaskItemManager.updateRepairCodeTaskItem(stockTask, taskItemList);
			return true;
		}

		//盘点任务更新处理
		if(request.getTasktype().equals(com.skynet.spms.webservice.entity.TaskType.STOCK_COUNT)){
			//任务主信息
			StockTask stockTask = new StockTask();
			stockTask.setTaskNo(request.getTaskNumber());
			stockTask.setBussinessBillNO(request.getTaskRefNumber());
			stockTask.setActionBy(request.getActionBy());
			stockTask.setActionDate(WebServiceUtils.convertXmlDate(request.getTaskDate()));
			stockTask.setTaskStatus(WebServiceUtils.convertXmlTaskStatus(request.getTaskStatus()));
			
			//任务明细信息
			List<StockCountExeItem> StockCountExeItemList = request.getStockCountExeItem();
			List<StockCheckTaskItem> newStockCheckTaskItemList = new ArrayList<StockCheckTaskItem>();
			for(StockCountExeItem item : StockCountExeItemList){
				StockCheckTaskItem stockCheckTaskItem = new StockCheckTaskItem();
				stockCheckTaskItem.setBarcodeTagUUID(item.getBarcodeTagUUID());
				stockCheckTaskItem.setTagIdentifierCode(item.getRFIDTagUUID());
				stockCheckTaskItem.setPartNumber(item.getPartNumber());
				stockCheckTaskItem.setPartSerialNumber(item.getPartSerialNumber());
				stockCheckTaskItem.setCargoSpaceNumber(item.getLocationNumber());
				stockCheckTaskItem.setRealityQuantity(item.getRealityQuantity());
				stockCheckTaskItem.setPostaction(item.getPostaction());
				stockCheckTaskItem.setOperationStatus(WebServiceUtils.convertXmlTaskItemStatus(item.getOperationStatus()));;
				newStockCheckTaskItemList.add(stockCheckTaskItem);
			}
			stockCheckTaskItemManager.updateStockCheckTaskItem(stockTask, newStockCheckTaskItemList);
			return true;
		}
		return false;
	}

	@Override
	public TasklistReocrdsOutputParameters getTaskListByRFIDTag(
			QueryTasklistByTAGInputParameters request) throws FaultResponse {
		TasklistReocrdsOutputParameters result = new TasklistReocrdsOutputParameters();
		List<Tasklist> tasklists = new ArrayList<Tasklist>();
		
		// RFID标签代码
		String tagIdentifierCode = request.getTagIdentifierCode();
		List<StockTask> stockTaskList = stockTaskManager.getTaskListByTagIdentifierCode(tagIdentifierCode);
		
		for (int i = 0; i < stockTaskList.size(); i++) {
			StockTask stockTask = (StockTask)stockTaskList.get(i);
			Tasklist taskList = new Tasklist();

			taskList.setTaskNumber(stockTask.getTaskNo());
			taskList.setTasktype(WebServiceUtils.convertTaskType(stockTask.getTaskType()));
			taskList.setDescription(stockTask.getDescription());
			taskList.setTaskDate(WebServiceUtils.convertDate(stockTask.getTaskDate()));
			taskList.setTaskRefNumber(stockTask.getBussinessBillNO());
			taskList.setTaskSource(stockTask.getTaskSource());
			taskList.setTaskStatus(TaskStatus.valueOf(stockTask.getTaskStatus().toString()));
			taskList.setCreatBy(stockTask.getCreateBy());
			taskList.setActionBy(stockTask.getActionBy());
			taskList.setActionDate(WebServiceUtils.convertDate(stockTask.getActionDate()));
			taskList.setActionDevice(stockTask.getActionDevice());
			tasklists.add(taskList);
		}
	    
		result.setTasklist(tasklists);
		return result;
	}
}
