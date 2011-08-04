package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.GenerateNumberManager;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class ReceivingSheetManagerImpl extends
		CommonManagerImpl<ReceivingSheet> implements ReceivingSheetManager {
	@Autowired
	ReceivingSheetItemsManager receivingSheetItemsManager;
	@Autowired
	GenerateNumberManager generateNumberManager;
	@Autowired
	UUIDGeneral uUIDGeneral;

	@Override
	public List<ReceivingSheet> getReceivingSheet(Map values, int startRow, int endRow)
	{
		if (values != null && values.containsKey("isWaitCheck") && values.get("isWaitCheck").toString().equals("1")) {
			String strQuery = "";
			strQuery = strQuery + "select distinct a ";
			strQuery = strQuery + "from ReceivingSheet a, ReceivingSheetItems b ";
			// 查询条件
			String strWhere = "where a.deleted = false ";
			strWhere = strWhere + " and b.deleted = false ";
			strWhere = strWhere + " and a.id = b.receivingSheetID ";
			strWhere = strWhere + " and b.isCheck = '0'";
			// 删除实体以外的临时字段
			Map extraKey = new HashMap();
			extraKey.put("isWaitCheck", "isWaitCheck");
			strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values, ReceivingSheet.class, "a.", false, extraKey);
			// 字段排序
			String strOrder = "order by a.receivingSheetNumber desc ";

			return getSession().createQuery(strQuery + strWhere + strOrder).list();
		} else {
			Criteria criteria= getSession().createCriteria(ReceivingSheet.class);
			criteria.add(Restrictions.eq("deleted", false));
			criteria.addOrder(Order.desc("receivingSheetNumber"));
			SqlHelperTool.createCriteria(values, criteria, ReceivingSheet.class, null);
	        
			if(endRow > 0){
				criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
			}

			return criteria.list();
		}
	}
	
	@Override
	public ReceivingSheet saveReceivingSheet(ReceivingSheet receivingSheet)
	{
		String strHql = "";
		Integer recordCount = 0;
		Session session = getSession();

		List<ReceivingSheetItems> receivingSheetItemsList = receivingSheet.getReceivingSheetItemsList();
		for (ReceivingSheetItems receivingSheetItems : receivingSheetItemsList) {
			// 取得当前件和序号在数据库中存在的件数
			strHql = "select count(a.id) from ReceivingSheetItems a ";
			strHql = strHql + "where partNumber = :partNumber ";
			strHql = strHql + "and partSerialNumber = :partSerialNumber ";
			// 更新的情况
			if (receivingSheet.getId() != null && !receivingSheet.getId().equals("")) {
				strHql = strHql + "and a.id <> :receivingSheetItemsID ";
			}
			Query query = session.createQuery(strHql);
			query.setString("partNumber", receivingSheetItems.getPartNumber());
			query.setString("partSerialNumber", receivingSheetItems.getPartSerialNumber());
			// 更新的情况
			if (receivingSheet.getId() != null && !receivingSheet.getId().equals("")) {
				query.setString("receivingSheetItemsID", receivingSheetItems.getId());
			}
			recordCount = Integer.valueOf(query.uniqueResult().toString());
			
			if (recordCount > 0) {
				receivingSheet.setExecuteResult("1");
				receivingSheet.setErrMsg("件号:" +
						receivingSheetItems.getPartNumber() +
						"和序号/批号:" +  
						receivingSheetItems.getPartSerialNumber() +
						"已经存在，请重新输入！");
				return receivingSheet;
			}
		}
		
		if (receivingSheet.getId() == null || receivingSheet.getId().equals("")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("RCV");
			// 收料单编号
			receivingSheet.setReceivingSheetNumber(curNO);
		}
		receivingSheet.setCreateBy(GwtActionHelper.getCurrUser());
		receivingSheet.setCreateDate(new Date());
		// 保存收料单基本信息
		session.saveOrUpdate(receivingSheet);
		
		// 更新提货指令为已收料
		strHql = "update PickupDeliveryOrder set isReceiving = '1' where id = :orderID";
		Query query = getSession().createQuery(strHql);
		query.setString("orderID", receivingSheet.getOrderID());
		query.executeUpdate();
		
		return receivingSheet;
	}
}
