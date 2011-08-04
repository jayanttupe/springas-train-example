package com.skynet.spms.action.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.CargoSpaceManagePolicyManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy.CargoSpaceManagePolicy;

/**
 * 描述：货位策略相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CargoSpaceManagePolicyDatasourceAction implements DataSourceAction<CargoSpaceManagePolicy>{
@Autowired
private CargoSpaceManagePolicyManager cargoSpaceManagePolicyManager;

@Override
public String[] getBindDsName() {
	return new String[]{"cargoSpaceManagePolicy_dataSource"};
}

/**
 * 新增货位策略相关信息
 * @param cargoSpaceManagePolicy
 */
@Override
public void insert(CargoSpaceManagePolicy cargoSpaceManagePolicy) {
	cargoSpaceManagePolicyManager.insertEntity(cargoSpaceManagePolicy);
}

/**
 * 更新货位策略相关信息
 * @param newValues
 * @param number
 * @return 货位策略相关信息
 */
@Override
public CargoSpaceManagePolicy update(Map newValues, String number) {
	return (CargoSpaceManagePolicy) cargoSpaceManagePolicyManager.updateEntity(newValues, number, CargoSpaceManagePolicy.class);
}

/**
 * 删除货位策略相关信息
 * @param number
 */
@Override
public void delete(String number) {
	cargoSpaceManagePolicyManager.deleteEntity(number, CargoSpaceManagePolicy.class);
}

/**
 * 查询货位策略相关信息
 * @param values
 * @param startRow
 * @param endRow
 * @return 货位策略相关信息
 */
@Override
public List<CargoSpaceManagePolicy> doQuery(Map values, int startRow, int endRow) {
	return cargoSpaceManagePolicyManager.getCargoSpaceManagePolicy(values, 0, -1);
}

/**
 * 获取所有货位策略信息
 * @param startRow
 * @param endRow
 * @return 货位策略信息
 */
@Override
public List<CargoSpaceManagePolicy> getList(int startRow, int endRow) {
	return cargoSpaceManagePolicyManager.getCargoSpaceManagePolicy(null, 0, -1);
}
}