package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.impl;

import java.sql.Timestamp;
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
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.moveTask.MoveTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.moveTask.MoveTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class StockMovingRecordManagerImpl extends CommonManagerImpl<StockMovingRecord> implements StockMovingRecordManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	public StockMovingRecord updateRecord(Map map){
		Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.eq("stockMovingNumber", map.get("stockMovingNumber")));
		StockMovingRecord record =(StockMovingRecord)criteria.uniqueResult();
		record.setState(map.get("state").toString());
		record.setCreateBy(GwtActionHelper.getCurrUser());
		record.setCreateDate(new Date());
		getSession().saveOrUpdate(record);
		return record;
	}
	
	public List<StockMovingRecord> findbystate(String state,String state2){
		Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.or(Restrictions.eq("state", state),Restrictions.eq("state", state2)));
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();
	}
	
	public List<StockMovingRecord> findbyall(){
		Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.eq("state","审批完毕"));
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();
	}
	
	public StockMovingRecord updateRecordstate(Map map){
		
		try {	
			Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
			criteria.add(Restrictions.eq("stockMovingNumber", map.get("stockMovingNumber")));
			StockMovingRecord record =(StockMovingRecord)criteria.uniqueResult();
			record.setState(map.get("state").toString());
			record.setCreateBy(GwtActionHelper.getCurrUser());
			record.setCreateDate(new Date());
			getSession().saveOrUpdate(record);
			MoveTask baseTask = new MoveTask();
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			String taskno = String.valueOf(timestamp.getTime()+map.get("stockMovingNumber").toString());
			baseTask.setTaskDate(date);
			baseTask.setTaskType("移库");
			baseTask.setTaskNo(taskno);
			baseTask.setTaskBy("awrfs");
			baseTask.setCreateBy(GwtActionHelper.getCurrUser());
			baseTask.setCreateDate(new Date());
			getSession().saveOrUpdate(baseTask);
			Criteria criteriaitem = getSession().createCriteria(StockMovingRecordItems.class);
			criteriaitem.add(Restrictions.eq("stockMovingNumber", map.get("stockMovingNumber")));
			List<StockMovingRecordItems> listitem = criteriaitem.list();
			for(int i=0;i<listitem.size();i++){
				StockMovingRecordItems items = listitem.get(i);
				MoveTaskItem taskItem = new MoveTaskItem();
				taskItem.setTaskNo(taskno);
				taskItem.setATA("ATA22");
				taskItem.setItemNo(String.valueOf(i + 1));
				taskItem.setVersion(0);
				taskItem.setUnit(items.getUnitMeasureCode());
				taskItem.setQuantity(items.getQuantity());
				taskItem.setStockRoomNumber(map.get("movingInStockRoomNumbers").toString());
				taskItem.setPartNumber(items.getPartNumber());
				taskItem.setPartSerialNumber(items.getPartSerialNumber());
				taskItem.setCreateBy(GwtActionHelper.getCurrUser());
				taskItem.setCreateDate(new Date());
				getSession().saveOrUpdate(taskItem);
			}
			return record;
		} catch (Exception e) {			
			return null;
		}
		
	}
	
	@Override
	public List<StockMovingRecord> getAllByCondition(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, StockMovingRecord.class, null);
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}

	@Override
	public StockMovingRecord SaveStockMovingRecord(StockMovingRecord stockMovingRecord) {
		Session session = getSession();
		if (stockMovingRecord.getStockMovingNumber() == null 
				|| stockMovingRecord.getStockMovingNumber().equals("")
				|| stockMovingRecord.getStockMovingNumber().equals("业务编号自动生成")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("MR");
			// 移库单号
			stockMovingRecord.setStockMovingNumber(curNO);
		}
		stockMovingRecord.setCreateBy(GwtActionHelper.getCurrUser());
		stockMovingRecord.setCreateDate(new Date());
		// 保存申请移库基本信息
		getSession().saveOrUpdate(stockMovingRecord);
		
		return stockMovingRecord;
	}
	
	public List<StockMovingRecord> GetAllbyApply(){
		Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.eq("deleted",false));
		criteria.add(Restrictions.isNull("state"));
		return criteria.list();
	}
	
	public List<StockMovingRecord> GetAllbyApproval(){
		Criteria criteria = getSession().createCriteria(StockMovingRecord.class);
		criteria.add(Restrictions.eq("state","审批中"));
		criteria.add(Restrictions.eq("deleted",false));
		return criteria.list();		
	}
}