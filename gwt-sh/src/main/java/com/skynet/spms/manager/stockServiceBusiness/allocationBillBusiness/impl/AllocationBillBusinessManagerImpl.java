package com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.allocationBillBusiness.AllocationBillBusinessManager;
import com.skynet.spms.persistence.entity.csdd.b.BusinessType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.allocationBillBusiness.AllocationBillBusiness;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class AllocationBillBusinessManagerImpl extends CommonManagerImpl<AllocationBillBusiness> implements AllocationBillBusinessManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<AllocationBillBusiness> getAllocationBillBusiness(
			Map values, int startRow, int endRow) {
		String strQuery = "";

		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.ALLOCATION_BILL_NUMBER ";
		strQuery = strQuery + ",a.BUSINESS_DATE ";
		strQuery = strQuery + ",a.BUSINESS_TYPE ";
		strQuery = strQuery + ",a.CHECK_DATE ";
		strQuery = strQuery + ",a.CHECK_USER ";
		strQuery = strQuery + ",a.CREATE_BY_DATE ";
		strQuery = strQuery + ",a.CREATE_USER ";
		strQuery = strQuery + ",a.IN_DEPARTMENT ";
		strQuery = strQuery + ",a.IN_WARE_HOUSE ";
		strQuery = strQuery + ",b.STOCK_ROOM_CHINESE_NAME as IN_STOCK_ROOM_NAME ";
		strQuery = strQuery + ",a.OUT_DEPARTMENT ";
		strQuery = strQuery + ",a.OUT_WARE_HOUSE ";
		strQuery = strQuery + ",c.STOCK_ROOM_CHINESE_NAME as OUT_STOCK_ROOM_NAME ";
		strQuery = strQuery + ",a.REMARK ";
		strQuery = strQuery + ",a.SOURCE_BILL_TYPE ";
		strQuery = strQuery + ",a.STATE ";
		strQuery = strQuery + "from SPMS_ALLOCATION_BILL a ";
		strQuery = strQuery + "left join SPMS_STOCK_ROOM b ";
		strQuery = strQuery + "on a.IN_WARE_HOUSE = b.ID ";
		strQuery = strQuery + "left join SPMS_STOCK_ROOM c ";
		strQuery = strQuery + "on a.OUT_WARE_HOUSE = c.ID ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 字段排序
		String strOrder = "order by a.ALLOCATION_BILL_NUMBER asc ";

		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<AllocationBillBusiness> allocationBillBusinessList = new ArrayList<AllocationBillBusiness>();
		for (Object[] objects : result)
		{
			AllocationBillBusiness allocationBillBusiness = new AllocationBillBusiness();

			if (objects[0] != null)
				allocationBillBusiness.setId(objects[0].toString());
			if (objects[1] != null)	
				allocationBillBusiness.setCreateBy(objects[1].toString());
			if (objects[2] != null)	 {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date createDate = (Date) sdf.parse(objects[2].toString());
					allocationBillBusiness.setCreateDate(createDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)	
				allocationBillBusiness.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)	
				allocationBillBusiness.setKeyword(objects[4].toString());
			if (objects[5] != null)	
				allocationBillBusiness.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)	
				allocationBillBusiness.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)	
				allocationBillBusiness.setAllocationBillNumber(objects[7].toString());
			if (objects[8] != null)	{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					allocationBillBusiness.setBusinessDate(sdf.parse(objects[8].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[9] != null)	
				allocationBillBusiness.setBusinessType(BusinessType.valueOf(objects[9].toString()));
			if (objects[10] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					allocationBillBusiness.setCheckDate(sdf.parse(objects[10].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[11] != null)	
				allocationBillBusiness.setCheckUser(objects[11].toString());
			if (objects[12] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					allocationBillBusiness.setCreateByDate(sdf.parse(objects[12].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}	
			if (objects[13] != null)	
				allocationBillBusiness.setCreateUser(objects[13].toString());
			if (objects[14] != null)	
				allocationBillBusiness.setInDepartment(objects[14].toString());
			if (objects[15] != null)	
				allocationBillBusiness.setInWareHouse(objects[15].toString());
			if (objects[16] != null)	
				allocationBillBusiness.setInWareHouseName(objects[16].toString());
			if (objects[17] != null)	
				allocationBillBusiness.setOutDepartment(objects[17].toString());
			if (objects[18] != null)	
				allocationBillBusiness.setOutWareHouse(objects[18].toString());
			if (objects[19] != null)	
				allocationBillBusiness.setOutWareHouseName(objects[19].toString());
			if (objects[20] != null)	
				allocationBillBusiness.setRemark(objects[20].toString());
			if (objects[21] != null)	
				allocationBillBusiness.setSourceBillType(objects[21].toString());
			if (objects[22] != null)	
				allocationBillBusiness.setState(objects[22].toString());
			
			allocationBillBusinessList.add(allocationBillBusiness);
		}
		
		return allocationBillBusinessList;
	}

	@Override
	public AllocationBillBusiness saveAllocationBillBusiness(
			AllocationBillBusiness allocationBillBusiness) {
		Session session = getSession();
		if (allocationBillBusiness.getId() == null || allocationBillBusiness.getId().equals("")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("ALC");
			// 设置单据号
			allocationBillBusiness.setAllocationBillNumber(curNO);
		}
		allocationBillBusiness.setCreateBy(GwtActionHelper.getCurrUser());
		allocationBillBusiness.setCreateDate(new Date());
		session.saveOrUpdate(allocationBillBusiness);
		return allocationBillBusiness;
	}

}