package com.skynet.spms.action.stockServiceBusiness.printCodeBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.printCodeBusiness.CargoSpace1DBarCodeManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode.CargoSpace1DBarCode;

/**
 * 描述：货位一维码相关信息处理
 * 
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CargoSpace1DBarCodeDataSourceAction implements
		DataSourceAction<CargoSpace1DBarCode> {
	@Autowired
	CargoSpace1DBarCodeManager cargoSpace1DBarCodeManager;

	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 新增货位一维码相关信息
	 * @param item
	 */
	@Override
	public void insert(CargoSpace1DBarCode item) {
		cargoSpace1DBarCodeManager.saveCargoSpace1DBarCode(item);

	}

	/**
	 * 更新货位一维码相关信息
	 * @param newValues
	 * @param itemID
	 * @return 货位一维码相关信息
	 */
	@Override
	public CargoSpace1DBarCode update(Map<String, Object> newValues,
			String itemID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除货位一维码相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询货位一维码相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 货位一维码相关信息
	 */
	@Override
	public List<CargoSpace1DBarCode> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取货位一维码相关信息
	 * @param startRow
	 * @param endRow
	 * @return 货位一维码相关信息
	 */
	@Override
	public List<CargoSpace1DBarCode> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return null;
	}

}
