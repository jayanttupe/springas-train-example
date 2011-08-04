package com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness.RepairCodeManager;
import com.skynet.spms.persistence.entity.spmsdd.SendStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.repairCodeType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.repairType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class RepairCodeManagerImpl extends
		CommonManagerImpl<RepairCode> implements RepairCodeManager {

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public RepairCode saveRepairCode(RepairCode repairCode){
		Session session = getSession();
		
		if (repairCode.getTaskNumber() == null || 
				repairCode.getTaskNumber().equals("") ||
				repairCode.getTaskNumber().equals("业务编号系统自动生成")) {
			// 取得下一个编号
			String rurNO = uUIDGeneral.getSequence("RCI");
			// 任务编号
			repairCode.setTaskNumber(rurNO);	
		}
		String user = GwtActionHelper.getCurrUser();
		repairCode.setCreateBy(user);
		repairCode.setCreateDate(new Date());
		repairCode.setSendStatus(SendStatus.NotSend);
		session.saveOrUpdate(repairCode);
		
		List<String> itemIdList = repairCode.getItemIdList();
		if(repairCode.getRepairCodeType() == repairCodeType.spareCode){
			RepairCodePartItem partItem = null;	
			for(String id : itemIdList){
				PartsInventoryRecord partRec = (PartsInventoryRecord)session.get(PartsInventoryRecord.class, id);
				partItem = new RepairCodePartItem();
				partItem.setRepairCodeId(repairCode.getId());
				partItem.setPartsInventoryRecord(partRec);
				session.saveOrUpdate(partItem);
			}
		}else if(repairCode.getRepairCodeType() == repairCodeType.carge){
			RepairCodeCargoSpaceItem cargoItem = null;
			for(String id : itemIdList){
				CargoSpace cargoRec = (CargoSpace)session.get(CargoSpace.class, id);
				cargoItem = new RepairCodeCargoSpaceItem();
				cargoItem.setRepairCodeId(repairCode.getId());
				cargoItem.setCargoSpace(cargoRec);
				session.saveOrUpdate(cargoItem);
			}
		}
		return repairCode;
	}
	
	@Override
	public List<RepairCode> getRepairCode(Map values, int startRow,
			int endRow)
	{
		Criteria criteria= getSession().createCriteria(RepairCode.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, RepairCode.class, null);
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
	@Override
	public RepairCode updateRepairCode(Map<String,Object> values,String itemId){
		Session session = getSession();
		RepairCode repairCode = (RepairCode)session.get(RepairCode.class, itemId);
		
		//补码任务编号
		String taskNumber = (String)values.get("taskNumber");
		repairCode.setTaskNumber(taskNumber);
		//补码原因
		String repairCodeReason =(String)values.get("repairCodeReason");
		repairCode.setRepairCodeReason(repairCodeReason);
		//补码类型
		String strRct = (String)values.get("repairCodeType");
		repairCodeType rct = null;
		if(strRct != null){
			rct = repairCodeType.valueOf(strRct);
		}
		repairCode.setRepairCodeType(rct);
		//补码种类
		String strRt = (String)values.get("repairType");
		repairType rt = null;
		if(strRt != null){
			rt = repairType.valueOf(strRt);
		}
		repairCode.setRepairType(rt);
		//备注
		String remark = (String)values.get("remark");
		repairCode.setRemark(remark);
		//更新补码任务主表
		session.saveOrUpdate(repairCode);
		
		//获取补码任务明细项所对应的航材或货位ID
		List<String> itemIdList = (List<String>)values.get("itemIdList");
		
		//修改补码任务明细项
		//先删除补码任务的所有航材明细项
		Criteria criteria = session.createCriteria(RepairCodePartItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCode.getId()));
		List<RepairCodePartItem> rcpiList = criteria.list();
		for(RepairCodePartItem item : rcpiList){
			session.delete(item);
		}
		//再删除补码任务的所有货位明细项
		criteria = session.createCriteria(RepairCodeCargoSpaceItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCode.getId()));
		List<RepairCodeCargoSpaceItem> rccsiList = criteria.list();
		for(RepairCodeCargoSpaceItem item : rccsiList){
			session.delete(item);
		}
		if(repairCode.getRepairCodeType() == repairCodeType.spareCode){
			//为补码任务建立新的航材明细项
			RepairCodePartItem partItem = null;	
			for(String id : itemIdList){
				PartsInventoryRecord partRec = (PartsInventoryRecord)session.get(PartsInventoryRecord.class, id);
				partItem = new RepairCodePartItem();
				partItem.setRepairCodeId(repairCode.getId());
				partItem.setPartsInventoryRecord(partRec);
				session.saveOrUpdate(partItem);
			}
		}else if(repairCode.getRepairCodeType() == repairCodeType.carge){
			//为补码任务建立新的货位明细项
			RepairCodeCargoSpaceItem cargoItem = null;
			for(String id : itemIdList){
				CargoSpace cargoRec = (CargoSpace)session.get(CargoSpace.class, id);
				cargoItem = new RepairCodeCargoSpaceItem();
				cargoItem.setRepairCodeId(repairCode.getId());
				cargoItem.setCargoSpace(cargoRec);
				session.saveOrUpdate(cargoItem);
			}
		}
		return repairCode;
	}

	@Override
	public void deleteRepairCode(String itemId) {
		Session session = getSession();
		RepairCode repairCode = (RepairCode)session.get(RepairCode.class, itemId);
		repairCode.setDeleted(true);
		session.saveOrUpdate(repairCode);
		
		//先删除补码任务的所有航材明细项
		Criteria criteria = session.createCriteria(RepairCodePartItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCode.getId()));
		List<RepairCodePartItem> rcpiList = criteria.list();
		for(RepairCodePartItem item : rcpiList){
			session.delete(item);
		}
		//再删除补码任务的所有货位明细项
		criteria = session.createCriteria(RepairCodeCargoSpaceItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCode.getId()));
		List<RepairCodeCargoSpaceItem> rccsiList = criteria.list();
		for(RepairCodeCargoSpaceItem item : rccsiList){
			session.delete(item);
		}
		
	}
		
}
