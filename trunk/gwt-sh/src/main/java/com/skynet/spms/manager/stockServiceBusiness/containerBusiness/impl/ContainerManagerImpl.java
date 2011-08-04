package com.skynet.spms.manager.stockServiceBusiness.containerBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.containerBusiness.ContainerManager;
import com.skynet.spms.persistence.entity.spmsdd.PackagingMaterial;
import com.skynet.spms.persistence.entity.spmsdd.StockContainerType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.containerManage.Container;
import com.skynet.spms.service.UUIDGeneral;
@Service
@Transactional
public class ContainerManagerImpl extends
		CommonManagerImpl<Container> implements ContainerManager {

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public Container saveContainer(Container container){
		Session session = getSession();
		
		if (container.getContainerNumber() == null || 
				container.getContainerNumber().equals("") ||
				container.getContainerNumber().equals("业务编号系统自动生成")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("GC");
			// 收料单编号
			container.setContainerNumber(curNO);	
		}
		session.saveOrUpdate(container);
		return container;
		
	}
	@Override
	public List<Container> getContainer(Map values, int startRow, int endRow)
	{
		String strQuery = "";

		strQuery = strQuery + "select ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.CONTAINER_MATERIAL ";
		strQuery = strQuery + ",a.CONTAINER_NUMBER ";
		strQuery = strQuery + ",a.CONTAINER_TYPE ";
		strQuery = strQuery + ",a.REMARK ";
		strQuery = strQuery + ",a.STOCK_ROOM_ID ";
		strQuery = strQuery + ",b.STOCK_ROOM_NUMBER ";
		strQuery = strQuery + ",b.STOCK_ROOM_CHINESE_NAME as STOCK_ROOM_NAME ";
		strQuery = strQuery + "from SPMS_CONTAINER a ";
		strQuery = strQuery + "left join SPMS_STOCK_ROOM b ";
		strQuery = strQuery + "on a.STOCK_ROOM_ID = b.ID ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		strWhere = strWhere + "and b.IS_DELETED = '0' ";
		strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
				Container.class,
				"a.",
				true,
				null);
		// 字段排序
		String strOrder = "order by a.CONTAINER_NUMBER asc ";

		Query query = getSession().createSQLQuery(strQuery + strWhere + strOrder).addEntity(Container.class);
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<Container> containerList = new ArrayList<Container>();
		for (Object[] objects : result)
		{
			Container container = new Container();
			if (objects[0] != null)	
				container.setId(objects[0].toString());
			if (objects[1] != null)	
				container.setCreateBy(objects[1].toString());
			if (objects[2] != null)	 {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date createDate = (Date) sdf.parse(objects[2].toString());
					container.setCreateDate(createDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null)	
				container.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)	
				container.setKeyword(objects[4].toString());
			if (objects[5] != null)	
				container.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)	
				container.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)	
				container.setContainerMaterial(PackagingMaterial.valueOf(objects[7].toString()));
			if (objects[8] != null)	
				container.setContainerNumber(objects[8].toString());				
			if (objects[9] != null)	
				container.setContainerType(StockContainerType.valueOf(objects[9].toString()));
			if (objects[10] != null)	
				container.setRemark(objects[10].toString());
			if (objects[11] != null)	
				container.setStockRoomID(objects[11].toString());
			if (objects[12] != null)	
				container.setStockRoomNumber(objects[12].toString());
			if (objects[13] != null)	
				container.setStockRoomName(objects[13].toString());
			
			containerList.add(container);
		}
		
		return containerList;
	}
}
