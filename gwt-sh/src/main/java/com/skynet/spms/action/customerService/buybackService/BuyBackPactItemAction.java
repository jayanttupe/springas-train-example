package com.skynet.spms.action.customerService.buybackService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuybackPactItemManager;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackPact.BuybackPactItem;

/**
 * 
 * 描述：回购合同明细action实现类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class BuyBackPactItemAction implements DataSourceAction<BuybackPactItem> {
	@Autowired
	private IBuybackPactItemManager buybackPactItemManager;
	@Autowired
	private ListGridFilterManager<BuybackPactItem> filterManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { DSKey.C_BUYBACKPACTITEM_DS };
	}
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param item
	 *            回购合同明细实体
	 */
	@Override
	public void insert(BuybackPactItem item) {
		buybackPactItemManager.addSheet(item);
	}
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
	@Override
	public BuybackPactItem update(Map<String, Object> newValues, String itemID) {
		return buybackPactItemManager.update(newValues, itemID);
	}
	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	@Override
	public void delete(String itemID) {
		buybackPactItemManager.delete(itemID);
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
	@Override
	public List<BuybackPactItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(BuybackPactItem.class, criteria,
					startRow, endRow);
		}
		return buybackPactItemManager.doQuery(values, startRow, endRow);
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
	@Override
	public List<BuybackPactItem> getList(int startRow, int endRow) {
		return buybackPactItemManager.getList(startRow, endRow);
	}

}
