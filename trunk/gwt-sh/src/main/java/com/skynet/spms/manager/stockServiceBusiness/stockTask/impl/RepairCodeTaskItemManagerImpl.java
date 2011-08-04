package com.skynet.spms.manager.stockServiceBusiness.stockTask.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.RepairCodeTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskItemStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.RepairCodeTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

@Service
@Transactional
public class RepairCodeTaskItemManagerImpl extends CommonManagerImpl<RepairCodeTaskItem>
		implements RepairCodeTaskItemManager {

	@Override
	public List<RepairCodeTaskItem> getRepairCodeTaskItem(Map<String,Object> values) {
		Criteria criteria = getSession().createCriteria(RepairCodeTaskItem.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(),entry.getValue() ));
		}
		List<RepairCodeTaskItem> list = criteria.list();
		return list;
	}

	@Override
	public Boolean updateRepairCodeTaskItem(StockTask stockTask,
			List<RepairCodeTaskItem> repairCodeTaskItemList) {
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
		
		for(int i=0; i<repairCodeTaskItemList.size(); i++){
			RepairCodeTaskItem item  = repairCodeTaskItemList.get(i);
			//条码标签号
			String barcodeTagUUID = item.getBarcodeTagUUID();
			
			// 根据条码标签唯一编号取得任务明细项记录信息
			Criteria itemCriteria= session.createCriteria(RepairCodeTaskItem.class);
			itemCriteria.add(Restrictions.eq("barcodeTagUUID", barcodeTagUUID));
			itemCriteria.add(Restrictions.eq("deleted", false));
			RepairCodeTaskItem dbRepairCodeTaskItem = (RepairCodeTaskItem)itemCriteria.uniqueResult();
			
			// 后处理建议
			if (item.getPostaction() != null) {
				dbRepairCodeTaskItem.setPostaction(item.getPostaction());
			}
			dbRepairCodeTaskItem.setOperationStatus(TaskItemStatus.OVR);
			
			// 更新任务明细信息表
			session.saveOrUpdate(dbRepairCodeTaskItem);
		}
		return true;
	}

	
}