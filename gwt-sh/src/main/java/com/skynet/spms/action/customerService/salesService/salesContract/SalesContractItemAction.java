package com.skynet.spms.action.customerService.salesService.salesContract;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.salesContract.ISalesContractItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesContract.SalesContractItem;

/**
 * 
 * 描述：销售合同明细
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class SalesContractItemAction implements
		DataSourceAction<SalesContractItem> {

	@Resource
	ISalesContractItemManager manager;
	@Autowired
	private ListGridFilterManager<SalesContractItem> filterManager;

	/** 提供合同明细数据源 **/
	public String[] getBindDsName() {
		return new String[] { DSKey.C_SALESCONTRACTITEM_DS };
	}

	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *            销售合同明细实体
	 */
	public void insert(SalesContractItem item) {
		manager.addSalesContractItem(item);
	}

	/**
	 * 描述： 分页查询
	 * 
	 * @param startRow
	 *            开始行
	 * @param endRow
	 *            结束行
	 * @return 返回查询的结果集合
	 */
	public List<SalesContractItem> getList(int startRow, int endRow) {
		return manager.querySalesContractItemList(startRow, endRow);
	}

	/**
	 * 
	 * 描述： 修改
	 * 
	 * @param newValues
	 *            待修改的值集合
	 * @param itemID
	 *            被修改的实体的uuid
	 * @return 返回修改后的实体
	 */
	public SalesContractItem update(Map<String, Object> newValues, String itemID) {
		return manager.updateSalesContractItem(newValues, itemID);
	}

	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	public void delete(String itemID) {
		manager.deleteSalesContractItem(itemID);
	}

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
	public List<SalesContractItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesContractItem.class,
					criteria, startRow, endRow);
		}
		return manager.querySalesContractItemList(values, startRow, endRow);
	}

}
