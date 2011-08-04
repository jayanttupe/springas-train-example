package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * 货位信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CargoSpaceManager extends CommonManager<CargoSpace>{
	
	/**
	 * 生成货位
	 * @param cargoSpace
	 */
	void createCargoSpace(CargoSpace cargoSpace);
	
	/**
	 * 设置货位
	 * @param cargoSpace
	 */
	void setCargoSpace(CargoSpace cargoSpace);
	
	/**
	 * 获取货位相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	List<CargoSpace> getAllCargoSpace(Map values, int startRow, int endRow);
	
	/**
	 * 根据货位ID删除该货位信息
	 * @param cargoSpaceID
	 */
	public void deleteCargoSpace(String[] cargoSpaceID);

	/**
	 * 获取货位信息
	 * @param values
	 * @return 货位信息
	 */ 
	List<CargoSpace> getCargoSpaceFieldData(Map values);

	/**
	 * 拆分货位
	 * @param cargoSpaceNumber
	 * @param storageRacksCaseNumber
	 * @param newCargoSpaceCount
	 */
	void splitCargoSpace(String cargoSpaceNumber, String storageRacksCaseNumber, Integer newCargoSpaceCount);

	/**
	 * 合并货位
	 * @param cargoSpaceNumbers
	 */
	void mergeCargoSpace(String[] cargoSpaceNumbers);
	
	/**
	 * 根据补码ID获取货位信息
	 * @param repairCodeId
	 * @return 货位信息
	 */
	List<CargoSpace> findByRepairCodeId(String repairCodeId);
}
