package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StorageRacks;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.tools.EnumUtil;

@Service
@Transactional
public class StorageRacksImpl extends CommonManagerImpl<CargoSpace> implements StorageRacks{

	@Autowired
	private EnumUtil enumUtil;
	
	@Override
	public List<CargoSpace> getStorageRacks(int startRow, int endRow)
	{
		String strQuery = "SELECT ";
		strQuery = strQuery + "B.STOCK_ROOM_CHINESE_NAME ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 1, 14) as storageRacksCode ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 10, 1) as storageRacksTypeCode ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 6, 1) as storageRacksAreaCode ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 8, 3) as storageRacksNumber ";
		strQuery = strQuery + "FROM SPMS_CARGO_SPACE A ";
		strQuery = strQuery + "LEFT JOIN SPMS_STOCK_ROOM B ";
		strQuery = strQuery + "ON A.STOCK_ROOM_ID = B.ID ";
		strQuery = strQuery + "AND B.IS_DELETED = '0' ";
		strQuery = strQuery + "WHERE A.IS_DELETED = '0' ";
		strQuery = strQuery + "GROUP BY ";
		strQuery = strQuery + "B.STOCK_ROOM_CHINESE_NAME ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 1, 14) ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 10, 1) ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 6, 1) ";
		strQuery = strQuery + ",SUBSTR(A.CARGO_SPACE_NUMBER, 8, 3) ";
		
		List<CargoSpace> cargoSpaces = new ArrayList<CargoSpace>();
		List<Object[]> result = getSession().createSQLQuery(strQuery).list();
		for (Object[] objects : result)
		{
			CargoSpace cargoSpace = new CargoSpace();
			if (objects[0] != null)
				cargoSpace.setStockRoomChineseName(objects[0].toString());
			if (objects[1] != null)
				cargoSpace.setStorageRacksCode(objects[1].toString());
			if (objects[2] != null)
				cargoSpace.setStorageRacksTypeCode(objects[2].toString());
			if (objects[3] != null)
				cargoSpace.setStorageRacksAreaCode(objects[3].toString());
			if (objects[4] != null)
				cargoSpace.setStorageRacksNumber(objects[4].toString());
			
			cargoSpaces.add(cargoSpace);
		}
		return cargoSpaces;
	}
}