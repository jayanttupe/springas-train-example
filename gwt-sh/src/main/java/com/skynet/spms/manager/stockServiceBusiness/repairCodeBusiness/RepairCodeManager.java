package com.skynet.spms.manager.stockServiceBusiness.repairCodeBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.repairCodeBusiness.RepairCode;

/**
 * 航材补码相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface RepairCodeManager extends CommonManager<RepairCode>{
	
	/**
	 *  保存航材补码相关信息
	 * @param repairCode
	 * @return 航材补码相关信息
	 */
	public RepairCode saveRepairCode(RepairCode repairCode);
	
	/**
	 * 获取航材补码相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材补码相关信息
	 */
    public List<RepairCode> getRepairCode(Map values, int startRow, int endRow);
	
    /**
     * 更新航材补码相关信息
     * @param values
     * @param itemId
     * @return 航材补码相关信息
     */
    public RepairCode updateRepairCode(Map<String,Object> values,String itemId);
    
    /**
     * 删除航材补码相关信息
     * @param itemId
     */
    public void deleteRepairCode(String itemId);
    
}

