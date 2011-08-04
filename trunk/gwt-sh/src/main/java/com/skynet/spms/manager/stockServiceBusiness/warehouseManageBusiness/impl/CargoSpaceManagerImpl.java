package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.CargoSpaceManager;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockAreaManager;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockRoomManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.StorageSizeType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;
import com.skynet.spms.tools.AnnotationUtil;
import com.skynet.spms.tools.EnumUtil;

@Service
@Transactional
public class CargoSpaceManagerImpl extends CommonManagerImpl<CargoSpace> implements CargoSpaceManager{
	
	@Autowired
	private StockRoomManager stockRoomManager;
	@Autowired
	private StockAreaManager stockAreaManager;
	
	@Override
	public void createCargoSpace(CargoSpace cargoSpace) {

		Session session = getSession();
		// 取得库房信息
		Map stockRoomValues = new HashMap();
		stockRoomValues.put("id", cargoSpace.getStockRoomID());
		List<StockRoom> stockRoomList = stockRoomManager.getStockRoom(stockRoomValues, 0, -1);
		// 取得库房区域信息
		Map stockAreaValues = new HashMap();
		stockAreaValues.put("id", cargoSpace.getStockAreaID());
		List<StockArea> stockAreaList = stockAreaManager.getStockArea(stockAreaValues, 0, -1);
		
		// 库房编号
		String stockRoomNumber = stockRoomList.get(0).getStockRoomNumber();
		// 库房区域
		String stockAreaCode = stockAreaList.get(0).getAreaCode();
		// 货架排号
		String storageRacksRowCode = cargoSpace.getStorageRacksRowCode();
		// 货架类型（形式）
		String storageRacksTypeCode = cargoSpace.getStorageRacksTypeCode();
		// 货架列
		int storageRacksColumnCode = Integer.valueOf(cargoSpace.getStorageRacksColumnCode());
		// 货架层
		int storageRacksTierCode = Integer.valueOf(cargoSpace.getStorageRacksTierCode());
		// 货位格
		int storageRacksCaseCode = Integer.valueOf(cargoSpace.getStorageRacksCaseCode());
		// 货位尺寸
		StorageSizeType storageSizeType = cargoSpace.getStorageSizeType();
		// 货位承重
		String storageWeight = cargoSpace.getStorageWeight();

		// 验证是否已经生成过货位
		String strHql = "select count(id) from CargoSpace ";
		strHql = strHql + "where stockRoomID = '" + cargoSpace.getStockRoomID() + "'";
		strHql = strHql + "and stockAreaID = '" + cargoSpace.getStockAreaID() + "'";
		strHql = strHql + "and storageRacksRowCode = '" + storageRacksRowCode + "'";
		strHql = strHql + "and storageRacksTypeCode = '" + cargoSpace.getStorageRacksTypeCode() + "'";
		List<String> result = session.createQuery(strHql).list();
		cargoSpace.setCreatedFlg("0");
		if (result != null && result.get(0) != null && Integer.valueOf(String.valueOf(result.get(0))) > 0) {
			cargoSpace.setCreatedFlg("1");
			return;
		}
		
		for(int i = 1; i <= storageRacksColumnCode; i++)
		{
			for(int j = 1; j <= storageRacksTierCode; j++)
			{
				for(int k = 1; k <= storageRacksCaseCode; k++)
				{
					CargoSpace newCargoSpace = new CargoSpace();
					String curStorageRacksColumnCode = String.format("%02d", i);
					int intStorageRacksTierCode = j + 64;
					char curStorageRacksTierCode = (char)intStorageRacksTierCode;
					String curStorageRacksCaseCode = String.format("%02d", k);
					String strCargoSpaceNumber = stockRoomNumber
						+ stockAreaCode
						+ "-"
						+ String.format("%02d", Integer.valueOf(storageRacksRowCode))
						+ storageRacksTypeCode
						+ "-"
						+ curStorageRacksColumnCode
						+ String.valueOf(curStorageRacksTierCode)
						+ "-"
						+ curStorageRacksCaseCode;

					// 库房ID
					newCargoSpace.setStockRoomID(cargoSpace.getStockRoomID());
					// 货位编号
					newCargoSpace.setCargoSpaceNumber(strCargoSpaceNumber);
					// 库房区域ID
					newCargoSpace.setStockAreaID(cargoSpace.getStockAreaID());
					// 逻辑库ID
					newCargoSpace.setLogicStockID(cargoSpace.getLogicStockID());
					// 货架排
					newCargoSpace.setStorageRacksRowCode(cargoSpace.getStorageRacksRowCode());
					// 货架类型
					newCargoSpace.setStorageRacksTypeCode(cargoSpace.getStorageRacksTypeCode());
					// 列数
					newCargoSpace.setStorageRacksColumnCode(curStorageRacksColumnCode);
					// 层数
					newCargoSpace.setStorageRacksTierCode(String.valueOf(curStorageRacksTierCode));
					// 货位箱数
					newCargoSpace.setStorageRacksCaseCode(curStorageRacksCaseCode);
					// 货位尺寸
					newCargoSpace.setStorageSizeType(storageSizeType);
					// 货位承重
					newCargoSpace.setStorageWeight(storageWeight);

					newCargoSpace.setCreateBy(GwtActionHelper.getCurrUser());
					newCargoSpace.setCreateDate(new Date());
					session.save(newCargoSpace);
				}
			}
		}
	}

	@Override
	public void setCargoSpace(CargoSpace cargoSpace) {
		// 货位ID
		String[] cargoSpaceIDs = cargoSpace.getCargoSpaceID();
		// 逻辑库ID
		String logicStockID = cargoSpace.getLogicStockID();
		for (int i = 0; i < cargoSpaceIDs.length; i++)
		{
			String strHql = "update CargoSpace set logicStockID = :logicStockID where id = :cargoSpaceID";
			Query packingListQuery = getSession().createQuery(strHql);
			packingListQuery.setString("logicStockID", logicStockID);
			packingListQuery.setString("cargoSpaceID", cargoSpaceIDs[i]);
			packingListQuery.executeUpdate();
		}
	}
	
	@Override
	public List<CargoSpace> getAllCargoSpace(Map values, int startRow, int endRow)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT ";
		strQuery = strQuery + "a.ID ";
		strQuery = strQuery + ",a.CREATE_BY ";
		strQuery = strQuery + ",a.CREATE_DATE ";
		strQuery = strQuery + ",a.IS_DELETED ";
		strQuery = strQuery + ",a.KEYWORD ";
		strQuery = strQuery + ",a.LOCK_VER ";
		strQuery = strQuery + ",a.VERSION ";
		strQuery = strQuery + ",a.CARGO_SPACE_NUMBER ";
		strQuery = strQuery + ",c.LOGIC_STOCK_NAME ";
		strQuery = strQuery + ",d.AREA_NAME ";
		strQuery = strQuery + ",b.STOCK_ROOM_CHINESE_NAME ";
		strQuery = strQuery + ",b.STOCK_ROOM_NUMBER ";
		strQuery = strQuery + ",a.STORAGE_RACKS_CASE_CODE ";
		strQuery = strQuery + ",a.STORAGE_RACKS_COLUMN_CODE ";
		strQuery = strQuery + ",SUBSTR(a.CARGO_SPACE_NUMBER, 5, 6) STORAGE_RACKS_ROW_CODE ";
		strQuery = strQuery + ",a.STORAGE_RACKS_TIER_CODE ";
		strQuery = strQuery + ",a.STORAGE_RACKS_TYPE_CODE ";
		strQuery = strQuery + ",a.STORAGESIZETYPE ";
		strQuery = strQuery + ",a.STORAGEWEIGHT ";
		strQuery = strQuery + ",a.STOCK_ROOM_ID ";
		strQuery = strQuery + ",a.STOCK_AREA_ID ";
		strQuery = strQuery + ",a.LOGIC_STOCK_ID ";
		strQuery = strQuery + ",rownum rn ";
		// 来源表
		strQuery = strQuery + "FROM SPMS_CARGO_SPACE a ";
		strQuery = strQuery + "LEFT JOIN SPMS_STOCK_ROOM b ";
		strQuery = strQuery + "ON a.STOCK_ROOM_ID = b.ID ";
		strQuery = strQuery + "AND b.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_LOGIC_STOCK c ";
		strQuery = strQuery + "ON a.LOGIC_STOCK_ID = c.ID ";
		strQuery = strQuery + "AND c.IS_DELETED = '0' ";
		strQuery = strQuery + "LEFT JOIN SPMS_STOCK_AREA d ";
		strQuery = strQuery + "ON a.STOCK_AREA_ID = d.ID ";
		strQuery = strQuery + "AND d.IS_DELETED = '0' ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 字段排序
		String strOrder = "order by a.CARGO_SPACE_NUMBER asc ";
		
		if (values != null) {
			boolean isFilter = false;
			if (values.containsKey("filter")) {
				isFilter = true;
			} else {
				isFilter = false;
			}
			
			if (values.containsKey("stockRoomNumber")) {
	    		// 库房编号
	    		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, "b.STOCK_ROOM_NUMBER", String.class, values.get("stockRoomNumber").toString());
	    		values.remove("stockRoomNumber");
			}
			if (values.containsKey("stockRoomChineseName")) {
	    		// 库房名称
	    		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, "b.STOCK_ROOM_CHINESE_NAME", String.class, values.get("stockRoomChineseName").toString());
	    		values.remove("stockRoomChineseName");
			}
			if (values.containsKey("stockAreaName")) {
	    		// 所属区域
	    		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, "d.AREA_NAME", String.class, values.get("stockAreaName").toString());
	    		values.remove("stockRoomChineseName");
			}
			if (values.containsKey("logicStockName")) {
	    		// 所属逻辑库
	    		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, "c.LOGIC_STOCK_NAME", String.class, values.get("logicStockName").toString());
	    		values.remove("logicStockName");
			}
			strWhere = strWhere + SqlHelperTool.createSqlOrHqlCondition(values,
					CargoSpace.class,
					"a.",
					true,
					null);
		}

		String strSql = "select * from (" + strQuery + strWhere + strOrder + ") where rn >= " + startRow;
		if (endRow != -1){
			strSql = strSql + " and rn <= " + endRow;
		}
		List<Object[]> result = getSession().createSQLQuery(strSql).list();
		List<CargoSpace> cargoSpaceList = new ArrayList<CargoSpace>();
		for (Object[] objects : result)
		{
			CargoSpace cargoSpace = new CargoSpace();
			
			if (objects[0] != null)
				cargoSpace.setId(objects[0].toString());
			if (objects[1] != null)
				cargoSpace.setCreateBy(objects[1].toString());
			if (objects[2] != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					cargoSpace.setCreateDate(sdf.parse(objects[2].toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
			if (objects[3] != null)
				cargoSpace.setDeleted(Boolean.valueOf(objects[3].toString()));
			if (objects[4] != null)
				cargoSpace.setKeyword(objects[4].toString());
			if (objects[5] != null)
				cargoSpace.setLockVersion(Integer.valueOf(objects[5].toString()));
			if (objects[6] != null)
				cargoSpace.setVersion(Integer.valueOf(objects[6].toString()));
			if (objects[7] != null)
				cargoSpace.setCargoSpaceNumber(objects[7].toString());
			if (objects[8] != null)
				cargoSpace.setLogicStockName(objects[8].toString());
			if (objects[9] != null)
				cargoSpace.setStockAreaName(objects[9].toString());
			if (objects[10] != null)
				cargoSpace.setStockRoomChineseName(objects[10].toString());
			if (objects[11] != null)
				cargoSpace.setStockRoomNumber(objects[11].toString());
			if (objects[12] != null)
				cargoSpace.setStorageRacksCaseCode(objects[12].toString());
			if (objects[13] != null)
				cargoSpace.setStorageRacksColumnCode(objects[13].toString());
			if (objects[14] != null)
				cargoSpace.setStorageRacksRowCode(objects[14].toString());
			if (objects[15] != null)
				cargoSpace.setStorageRacksTierCode(objects[15].toString());
			if (objects[16] != null)
				cargoSpace.setStorageRacksTypeCode(objects[16].toString());
			if (objects[17] != null)
				cargoSpace.setStorageSizeType(StorageSizeType.valueOf(objects[17].toString()));
			if (objects[18] != null)
				cargoSpace.setStorageWeight(objects[18].toString());
			if (objects[19] != null)
				cargoSpace.setStockRoomID(objects[19].toString());
			if (objects[20] != null)
				cargoSpace.setStockAreaID(objects[20].toString());
			if (objects[21] != null)
				cargoSpace.setLogicStockID(objects[21].toString());
			
			cargoSpaceList.add(cargoSpace);
		}
		
		return cargoSpaceList;
	}
	
	@Override
	public void deleteCargoSpace(String[] cargoSpaceID)
	{
		for (int i = 0; i < cargoSpaceID.length; i++)
		{
			String strHql = "update CargoSpace set deleted = true where id = :cargoSpaceID";
			Query packingListQuery = getSession().createQuery(strHql);
			packingListQuery.setString("cargoSpaceID", cargoSpaceID[i]);
			packingListQuery.executeUpdate();
		}
	}
	
	@Override
	public List<CargoSpace> getCargoSpaceFieldData(Map values)
	{
		String strQuery = "";
		// 检索项目
		strQuery = strQuery + "SELECT DISTINCT ";
		if (values.containsKey("fieldName")) {
			strQuery = strQuery + "a."
				+ AnnotationUtil.getColumnName(CargoSpace.class, values.get("fieldName").toString())
				+ " ";
		}
		// 来源表
		strQuery = strQuery + "FROM SPMS_CARGO_SPACE a ";
		// 查询条件
		String strWhere = "where a.IS_DELETED = '0' ";
		// 字段排序
		String strOrder = "order by a."
				+ AnnotationUtil.getColumnName(CargoSpace.class, values.get("fieldName").toString()) + " asc ";

		Map extraKey = new HashMap();
		extraKey.put("fieldName", "fieldName");
		extraKey.put("selectSingleField", "selectSingleField");
		SqlHelperTool.createSqlOrHqlCondition(values, CargoSpace.class, "a.", true, extraKey);	
		
		List<Object[]> result = getSession().createSQLQuery(strQuery + strWhere + strOrder).list();
		List<CargoSpace> cargoSpaceList = new ArrayList<CargoSpace>();
		for (Object objects : result)
		{
			CargoSpace cargoSpace = new CargoSpace();

			// 货架排
			if (values.get("fieldName").equals("storageRacksRowCode")) {
				if (objects != null)
					cargoSpace.setStorageRacksRowCode(objects.toString());
			}
			// 货架形式
			if (values.get("fieldName").equals("storageRacksTypeCode")) {
				if (objects != null)
					cargoSpace.setStorageRacksTypeCode(objects.toString());
			}
			// 列数
			if (values.get("fieldName").equals("storageRacksColumnCode")) {
				if (objects != null)
					cargoSpace.setStorageRacksColumnCode(objects.toString());
			}
			// 层数
			if (values.get("fieldName").equals("storageRacksTierCode")) {
				if (objects != null)
					cargoSpace.setStorageRacksTierCode(objects.toString());
			}
			
			cargoSpaceList.add(cargoSpace);
		}
		
		return cargoSpaceList;
	}

	@Override
	public void splitCargoSpace(String cargoSpaceNumber, String storageRacksCaseNumber, Integer newCargoSpaceCount) {
		Session session = getSession();
		
		// 查询最大货位
		String strHql = "select max(cargoSpaceNumber) from CargoSpace where cargoSpaceNumber like '%" + storageRacksCaseNumber + "%'";
		List<String> result = session.createQuery(strHql).list();
		Integer maxCargoSpaceNumber = Integer.valueOf(String.valueOf(result.get(0)).toString().substring(15, 17));

		Criteria criteria = getSession().createCriteria(CargoSpace.class);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.add(Restrictions.eq("cargoSpaceNumber", cargoSpaceNumber));
		List<CargoSpace> cargoSpace = (List<CargoSpace>)criteria.list();
		
		for (int i = 0; i < newCargoSpaceCount - 1; i++) {
			CargoSpace newCargoSpace = new CargoSpace();
			// 库房ID
			newCargoSpace.setStockRoomID(cargoSpace.get(0).getStockRoomID());
			// 货位编号
			newCargoSpace.setCargoSpaceNumber(storageRacksCaseNumber + "-" + String.format("%02d", Integer.valueOf(maxCargoSpaceNumber + i + 1)));
			// 库房区域ID
			newCargoSpace.setStockAreaID(cargoSpace.get(0).getStockAreaID());
			// 逻辑库ID
			newCargoSpace.setLogicStockID(cargoSpace.get(0).getLogicStockID());
			// 货架排
			newCargoSpace.setStorageRacksRowCode(cargoSpace.get(0).getStorageRacksRowCode());
			// 货架类型
			newCargoSpace.setStorageRacksTypeCode(cargoSpace.get(0).getStorageRacksTypeCode());
			// 列数
			newCargoSpace.setStorageRacksColumnCode(cargoSpace.get(0).getStorageRacksColumnCode());
			// 层数
			newCargoSpace.setStorageRacksTierCode(String.valueOf(cargoSpace.get(0).getStorageRacksTierCode()));
			// 货位箱数
			newCargoSpace.setStorageRacksCaseCode(cargoSpace.get(0).getStorageRacksCaseCode());
			// 货位尺寸
			newCargoSpace.setStorageSizeType(cargoSpace.get(0).getStorageSizeType());
			// 货位承重
			newCargoSpace.setStorageWeight(cargoSpace.get(0).getStorageWeight());

			newCargoSpace.setCreateBy(GwtActionHelper.getCurrUser());
			newCargoSpace.setCreateDate(new Date());
			session.save(newCargoSpace);
		}
	}

	@Override
	public void mergeCargoSpace(String[] cargoSpaceNumbers) {
		String minNO = "";
		// 最小的货位编号
		String minCargoSpaceNumber = "";
		for (int i = 0; i < cargoSpaceNumbers.length; i++) {
			String curNO = String.valueOf(cargoSpaceNumbers[i].toString().substring(15, 17));
			if (i == 0 || minNO.compareTo(curNO) > 0) {
				minNO = curNO;
				minCargoSpaceNumber = cargoSpaceNumbers[i];
			}
		}

		for (int i = 0; i < cargoSpaceNumbers.length; i++) {
			if (!cargoSpaceNumbers[i].equals(minCargoSpaceNumber)) {
				// 删除多余的货位
				String strHql = "update CargoSpace set deleted = true where cargoSpaceNumber = :cargoSpaceNumber";
				Query strQuery = getSession().createQuery(strHql);
				strQuery.setString("cargoSpaceNumber", cargoSpaceNumbers[i]);
				strQuery.executeUpdate();
			}
		}
	}
	/**
	 * 补码管理使用，根据补码ID查找所要补码的货位
	 */
	@Override
	public List<CargoSpace> findByRepairCodeId(String repairCodeId){
		Criteria criteria = getSession().createCriteria(RepairCodeCargoSpaceItem.class);
		criteria.add(Restrictions.eq("repairCodeId", repairCodeId));
		List<RepairCodeCargoSpaceItem> list = criteria.list();
		List<CargoSpace> csList = new ArrayList<CargoSpace>();
		for(RepairCodeCargoSpaceItem item : list){
			csList.add(item.getCargoSpace());
		}
		return csList;
	}
}
