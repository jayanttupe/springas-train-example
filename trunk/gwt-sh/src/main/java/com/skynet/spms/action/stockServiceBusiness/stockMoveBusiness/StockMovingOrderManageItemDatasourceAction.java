/**
 * 
 */
package com.skynet.spms.action.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingOrderItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;

/**
 * 描述：移库指令相关明细信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockMovingOrderManageItemDatasourceAction implements DataSourceAction<BaseTaskItem>{
	@Autowired
	private StockMovingOrderItemManager stockMovingOrderItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockMovingOrderItem_dataSource"};
	}
	
	/**
	 * 新增移库指令相关明细信息
	 * @param baseTaskItem
	 */
	@Override
	public void insert(BaseTaskItem baseTaskItem) {
		stockMovingOrderItemManager.insertEntity(baseTaskItem);
	}

	/**
	 * 更新移库指令相关明细信息
	 * @param newValues
	 * @param number
	 * @return 移库指令相关明细信息
	 */
	@Override
	public BaseTaskItem update(Map newValues, String number) {
		return (BaseTaskItem) stockMovingOrderItemManager.updateEntity(newValues, number, BaseTaskItem.class);
	}

	/**
	 * 删除移库指令相关明细信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockMovingOrderItemManager.deleteEntity(number, BaseTaskItem.class);
	}

	/**
	 * 查询移库指令相关明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库指令相关明细信息
	 */
	@Override
	public List<BaseTaskItem> doQuery(Map values, int startRow, int endRow) {
		return stockMovingOrderItemManager.getStockInfo(values, startRow, endRow);
	}

	/**
	 * 获取移库指令相关明细信息
	 * @param startRow
	 * @param endRow
	 * @return 移库指令相关明细信息
	 */
	@Override
	public List<BaseTaskItem> getList(int startRow, int endRow) {
		return stockMovingOrderItemManager.list(0, -1, BaseTaskItem.class);
	}

}
