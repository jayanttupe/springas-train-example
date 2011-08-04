package com.skynet.spms.manager.supplierSupport.procurement.PartRequirment;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.partRequirement.PartRequirement;

/**
 * 备件计划需求
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface PartRequirementManager {

	/**
	 * 
	 * 添加备件计划需求
	 * 
	 * @param o
	 *            备件计划需求对象
	 * @return void
	 */
	public void addPartRequirement(PartRequirement o);

	/**
	 * 
	 * 删除备件计划需求的方法
	 * 
	 * @param id
	 *            备件计划需求ID
	 * @return void
	 * @throws
	 */
	public void deletePartRequirement(String id);

	/**
	 * 
	 * 修改备件计划需求的方法
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            对象ID
	 * @return 备件计划需求对象
	 */
	public PartRequirement updatePartRequirement(Map<String, Object> newValues,
			String itemID);

	/**
	 * 
	 * 分页查询备件计划需求的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 备件计划需求对象集合
	 */
	public List<PartRequirement> queryPartRequirementList(int startRow,
			int endRow);

}
