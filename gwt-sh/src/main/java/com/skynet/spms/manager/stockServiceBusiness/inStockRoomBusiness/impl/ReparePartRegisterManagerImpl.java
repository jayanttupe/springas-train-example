/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReparePartRegisterManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.reparePartRegister.ReparePartRegister;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class ReparePartRegisterManagerImpl extends CommonManagerImpl<ReparePartRegister> implements ReparePartRegisterManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<ReparePartRegister> getReparePartRegisterBusiness(Map values, int startRow, int endRow)
	{
		Criteria criteria= getSession().createCriteria(ReparePartRegister.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, ReparePartRegister.class, null);
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}
		return criteria.list();
	}

	@Override
	public ReparePartRegister saveReparePartRegister(ReparePartRegister reparePartRegister)
	{
		Session session = getSession();
		if (reparePartRegister.getId() == null || reparePartRegister.getId().equals("")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("RPR");
			// 送修登记单号
			reparePartRegister.setRepairRegisterSheetNumber(curNO);
		}
		reparePartRegister.setCreateBy(GwtActionHelper.getCurrUser());
		reparePartRegister.setCreateDate(new Date());
		// 保存收料单基本信息
		session.saveOrUpdate(reparePartRegister);
		
		return reparePartRegister;
	
	}
}