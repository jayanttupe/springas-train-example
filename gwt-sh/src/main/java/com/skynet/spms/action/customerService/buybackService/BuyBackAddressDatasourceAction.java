package com.skynet.spms.action.customerService.buybackService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuyBackAddressManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 
 * 描述：回购模块地址信息处理，主要实现CRUD操作
 * 
 * @author tz_ful
 * @version 1.0
 * @Date 2011-07-12
 */
@Component
public class BuyBackAddressDatasourceAction implements
		DataSourceAction<AddressInfo> {

	@Autowired
	private IBuyBackAddressManager manager;

	/**
	 * 回购地址信息数据源
	 * 
	 * @return 返回回购地址信息数据源
	 */
	public String[] getBindDsName() {
		return new String[] { DSKey.C_BUYBACK_ADDRESS_DS };
	}

	/**
	 * 描述：插入地址信息
	 * 
	 * @param item
	 *            地址信息实体
	 */
	public void insert(AddressInfo item) {
		manager.addAddress(item);
	}

	/**
	 * 描述：分页查询所有的地址信息
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 */
	public List<AddressInfo> getList(int startRow, int endRow) {
		return manager.getList(startRow, endRow);
	}

	/**
	 * 描述：更新操作
	 * 
	 * @param newValues
	 *            需要要更新的字段集合
	 * @param itemId
	 *            被更新的实体的UUID
	 */
	public AddressInfo update(Map<String, Object> newValues, String itemID) {
		return manager.update(newValues, itemID);
	}

	/**
	 * 描述：删除一条地址信息
	 * 
	 * @param itemID
	 *            被删除地址信息的UUID
	 */
	public void delete(String itemID) {
		manager.delete(itemID);
	}

	/**
	 * 描述：按条件分页筛选地址信息
	 * 
	 * @param values
	 *            筛选条件
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 */
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		return manager.doQuery(values, startRow, endRow);
	}

}
