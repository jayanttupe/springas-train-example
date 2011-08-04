package com.skynet.spms.manager.customerService.BuybackService.BuybackContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackPact.BuybackPactItem;

/**
 * 
 * 描述：合同明细相关操作
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
public interface IBuybackPactItemManager {
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *            回购合同明细实体
	 */
	public void addSheet(BuybackPactItem item);

	/**
	 * 
	 * 描述：根据ID查询一条记录
	 * 
	 * @param id
	 *            UUID
	 * @return 返回查询到的实体，未查询到返回null
	 */
	public BuybackPactItem getById(String id);

	/**
	 * 
	 * 描述： 修改回购明细信息
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的实体的uuid
	 * @return 返回修改后的实体
	 */
	public BuybackPactItem update(Map<String, Object> newValues, String itemID);

	/**
	 * 描述： 分页查询
	 * 
	 * @param values
	 *            查询条件集合,各个条件为and的关系
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询结果集合
	 */
	public List<BuybackPactItem> doQuery(Map<String, Object> values,
			int startRow, int endRow);

	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<BuybackPactItem> getList(int startRow, int endRow);

	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID);
}
