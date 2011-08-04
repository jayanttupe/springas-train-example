package com.skynet.spms.manager.customerService.BuybackService.BuybackContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 
 * 描述：回购合同关联的地址信息接口
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
public interface IBuyBackAddressManager {

	/**
	 * 
	 * 描述： 增加一条回购地址信息
	 * 
	 * @param form
	 *            地址信息实体
	 */
	public void addAddress(AddressInfo form);

	/**
	 * 
	 * 描述：根据记录的id查询一条记录
	 * 
	 * @param id
	 *            地址实体的UUID
	 * @return 返回查询到的地址实体
	 */
	public AddressInfo getById(String id);

	/**
	 * 
	 * 描述： 修改一条地址记录信息
	 * 
	 * @param newValues
	 *            要修改的值的集合
	 * @param itemID
	 *            被修改的实体的UUID
	 * @return 返回修改后的实体集合
	 */
	public AddressInfo update(Map<String, Object> newValues, String itemID);

	/**
	 * 
	 * 描述： 分页查询地址信息
	 * 
	 * @param values
	 *            查询条件集合
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集
	 */
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow);

	/**
	 * 
	 * 描述：分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集
	 */
	public List<AddressInfo> getList(int startRow, int endRow);

	/**
	 * 
	 * 描述：删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID);

}
