package com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodePartItem;

/**
 * 航材补码备件相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface RepairCodePartItemManager extends CommonManager<RepairCodePartItem>{
	
	/**
	 * 查询对应航材补码备件相关信息
	 * @param values
	 * @return 航材补码备件相关信息
	 */
    public List<RepairCodePartItem> queryByProps(Map<String,Object> values);
	
}

