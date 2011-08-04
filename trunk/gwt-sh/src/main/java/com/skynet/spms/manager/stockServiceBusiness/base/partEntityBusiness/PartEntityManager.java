package com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.base.partEntity.PartEntity;

/**
 * 备件Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PartEntityManager extends CommonManager<PartEntity>{
	
	/**
	 * 获取备件实体
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件实体
	 */
	public List<PartEntity> getPartEntity(Map values, int startRow, int endRow);
	
	/**
	 * 更新备件实体的二维标签序列号和RFID标签序列号
	 * @param partEntity
	 */
	void updateBarcodeTagUUID(PartEntity partEntity);
	
	/**
	 * 更新备件实体的二维标签序列号和RFID标签序列号
	 * @param partEntity
	 */
	void updateRFIDTagUUID(PartEntity partEntity);

}