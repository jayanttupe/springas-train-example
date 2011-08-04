package com.skynet.spms.action.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StorageRacks;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * 描述：货架相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StorageRacksDatasourceAction implements DataSourceAction<CargoSpace> {

	private Logger log=LoggerFactory.getLogger(StorageRacksDatasourceAction.class);
	
	@Autowired
	private StorageRacks storageRacks;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"storageRacks_dataSource"};
	}

	/**
	 * 新增货架相关信息
	 * @param cargoSpace
	 */
	@Override
	public void insert(CargoSpace cargoSpace) {
	}
	
	/**
	 * 删除货架相关信息
	 * @param cargoSpaceID
	 */
	@Override
	public void delete(String cargoSpaceID) {
	}

	/**
	 * 更新货架相关信息
	 * @param newValues
	 * @param itemID
	 * @return null
	 */
	@Override
	public CargoSpace update(Map<String, Object> newValues, String itemID) {
		return null;
	}
	
	/**
	 * 查询货架相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 货架相关信息
	 */
	@Override
	public List<CargoSpace> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		return storageRacks.getStorageRacks(startRow, endRow);
	}

	/**
	 * 获取所有货架信息
	 * @param startRow
	 * @param endRow
	 * @return 货架信息
	 */
	@Override
	public List<CargoSpace> getList(int startRow, int endRow) {
		return storageRacks.getStorageRacks(startRow, endRow);
	}

}
