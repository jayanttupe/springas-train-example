package com.skynet.spms.action.stockServiceBusiness.warehouseManageBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.CargoSpaceManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * 描述：货位相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CargoSpaceDatasourceAction implements DataSourceAction<CargoSpace> {

	private Logger log=LoggerFactory.getLogger(CargoSpaceDatasourceAction.class);
	
	@Autowired
	private CargoSpaceManager cargoSpaceManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"cargoSpace_dataSource"};
	}

	/**
	 * 新增货位相关信息
	 * @param cargoSpace
	 */
	@Override
	public void insert(CargoSpace cargoSpace) {
		if (cargoSpace.getOperatorType() != null &&
				cargoSpace.getOperatorType().equals("setCargo")) {
			// 设置货位
			cargoSpaceManager.setCargoSpace(cargoSpace);
		} else {
			// 生成货位
			cargoSpaceManager.createCargoSpace(cargoSpace);			
		}
	}
	
	/**
	 * 更新货位相关信息
	 * @param newValues
	 * @param rowID
	 * @return null
	 */
	@Override
	public CargoSpace update(Map newValues, String rowID) {
		// 更新货位删除标志为已删除
		if (newValues.containsKey("cargoSpaceID")) {
			String[] cargoSpaceID = (String[])newValues.get("cargoSpaceID");
			cargoSpaceManager.deleteCargoSpace(cargoSpaceID);
		} else if (newValues.containsKey("split")) {
			// 拆分货位
			String cargoSpaceNumber = newValues.get("cargoSpaceNumber").toString();
			String storageRacksCaseNumber = newValues.get("storageRacksCaseNumber").toString();
			Integer newCargoSpaceCount = Integer.valueOf(newValues.get("newCargoSpaceCount").toString());
			cargoSpaceManager.splitCargoSpace(cargoSpaceNumber, storageRacksCaseNumber, newCargoSpaceCount);
		} else if (newValues.containsKey("merge")) {
			// 合并货位
			ArrayList<String> cargoSpaceNumberList = (ArrayList<String>)newValues.get("cargoSpaceNumbers");
			String[] cargoSpaceNumbers = new String[cargoSpaceNumberList.size()];
			cargoSpaceNumbers = cargoSpaceNumberList.toArray(cargoSpaceNumbers);
			cargoSpaceManager.mergeCargoSpace(cargoSpaceNumbers);
		}

		return null;
	}

	/**
	 * 删除货位相关信息
	 * @param cargoSpaceID
	 */
	@Override
	public void delete(String cargoSpaceID) {
		cargoSpaceManager.deleteEntity(cargoSpaceID, CargoSpace.class);
	}

	/**
	 * 查询货位相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 货位相关信息
	 */
	@Override
	public List<CargoSpace> doQuery(Map<String, Object> values, int startRow, int endRow) {
		if(values.containsKey("repairCodeId")){
			String repairCodeId = (String)values.get("repairCodeId");
			return cargoSpaceManager.findByRepairCodeId(repairCodeId);
		}else if (values.containsKey("selectSingleField")) {
			return cargoSpaceManager.getCargoSpaceFieldData(values);
		} else {
			return cargoSpaceManager.getAllCargoSpace(values, startRow, endRow);
		}
	}

	/**
	 * 获取所有货位信息
	 * @param startRow
	 * @param endRow
	 * @return 货位信息
	 */
	@Override
	public List<CargoSpace> getList(int startRow, int endRow) {
		return cargoSpaceManager.getAllCargoSpace(null, startRow, endRow);
	}
}
