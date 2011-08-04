package com.skynet.spms.manager.stockServiceBusiness.printCodeBusiness;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.tagEntity.barCodeEntity.base1DBarCode.CargoSpace1DBarCode;

/**
 * 货位一维码实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CargoSpace1DBarCodeManager extends CommonManager<CargoSpace1DBarCode>{

	/**
	 * 保存货位一维码相关信息
	 * @param cargoSpace1DBarCode
	 * @return 货位一维码信息
	 */
	public CargoSpace1DBarCode saveCargoSpace1DBarCode(CargoSpace1DBarCode cargoSpace1DBarCode);
}
