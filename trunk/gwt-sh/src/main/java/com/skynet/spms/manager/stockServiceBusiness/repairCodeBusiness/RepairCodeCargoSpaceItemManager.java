package com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCodeCargoSpaceItem;

/**
 * 货位补码明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface RepairCodeCargoSpaceItemManager extends CommonManager<RepairCodeCargoSpaceItem>{
	
	/**
	 * 查询货位补码明细相关信息
	 * @param values
	 * @return 货位补码明细相关信息
	 */
    public List<RepairCodeCargoSpaceItem> queryByProps(Map<String,Object> values);
	
}

