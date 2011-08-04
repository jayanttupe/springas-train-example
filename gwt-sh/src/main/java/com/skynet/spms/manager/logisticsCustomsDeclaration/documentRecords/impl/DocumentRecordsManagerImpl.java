package com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.documentRecords.DocumentRecordsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.documentRecords.DocumentRecords;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class DocumentRecordsManagerImpl extends CommonManagerImpl<DocumentRecords> implements DocumentRecordsManager{

	@Override
	public List<DocumentRecords> getDocumentRecords(
			Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from DocumentRecords a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		strWhere = strWhere + " and b.isPublish = '2' ";
		// 字段排序
		String strOrder = "order by a.logisticsTaskNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<DocumentRecords> documentRecordsList = new ArrayList<DocumentRecords>();
		for (Object[] objects : result)
		{
			DocumentRecords documentRecords = new DocumentRecords();
			if (objects[0] != null)
				documentRecords = (DocumentRecords)objects[0];
			
			documentRecordsList.add(documentRecords);
		}
		return documentRecordsList;
	}

	@Override
	public DocumentRecords saveDocumentRecords(DocumentRecords documentRecords){
		Session session = getSession();
		documentRecords.setCreateBy(GwtActionHelper.getCurrUser());
		documentRecords.setCreateDate(new Date());
		session.saveOrUpdate(documentRecords);
		return documentRecords;
	}
}