/**
 * 
 */
package com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.reparePartBusiness.ReparePartBusiness;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class LogisticsOutlayRecordManagerImpl extends CommonManagerImpl<LogisticsOutlayRecord> implements LogisticsOutlayRecordManager{

	/* (non-Javadoc)
	 * @see com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.logisticsOutlayRecord.LogisticsOutlayRecordManager#GetLogisticsOutlayRecordByCondition(java.util.Map, int, int)
	 */
	@Override
	public List<LogisticsOutlayRecord> getLogisticsOutlayRecord(
			Map values, int startRow, int endRow) {
		String strQuery = "";
		strQuery = strQuery + "from LogisticsOutlayRecord a, PickupDeliveryOrder b ";
		// 查询条件
		String strWhere = "where a.deleted = false ";
		strWhere = strWhere + " and b.deleted = false ";
		strWhere = strWhere + " and a.orderID = b.id ";
		strWhere = strWhere + " and b.isPublish = '2' ";
		// 字段排序
		String strOrder = "order by a.logisticsTaskNumber desc ";

		List<Object[]> result = getSession().createQuery(strQuery + strWhere + strOrder).list();
		List<LogisticsOutlayRecord> logisticsOutlayRecordList = new ArrayList<LogisticsOutlayRecord>();
		for (Object[] objects : result)
		{
			LogisticsOutlayRecord logisticsOutlayRecord = new LogisticsOutlayRecord();
			if (objects[0] != null)
				logisticsOutlayRecord = (LogisticsOutlayRecord)objects[0];
			
			logisticsOutlayRecordList.add(logisticsOutlayRecord);
		}
		return logisticsOutlayRecordList;
	}
}
