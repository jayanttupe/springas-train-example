package com.skynet.spms.action.customerService.buybackService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuybackPactManager;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.BuybackContractTemplate.BuybackContractTemplate;

/**
 * 
 * 描述：回购合同action实现类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class BuyBackPactAction implements
		DataSourceAction<BuybackContractTemplate> {
	@Autowired
	private IBuybackPactManager buybackPactManager;
	@Autowired
	private ListGridFilterManager<BuybackContractTemplate> filterManager;

	/**
	 * 绑定回购合同 数据源
	 * 
	 * @return 返回数据源名称数组
	 */
	@Override
	public String[] getBindDsName() {
		return new String[] { DSKey.C_BUYBACKPACT_DS };
	}
	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param pact
	 *            回购合同实体
	 */
	@Override
	public void insert(BuybackContractTemplate item) {
		buybackPactManager.addSheet(item);
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
	@Override
	public BuybackContractTemplate update(Map<String, Object> newValues,
			String itemID) {
		return buybackPactManager.update(newValues, itemID);
	}
	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	@Override
	public void delete(String itemID) {
		buybackPactManager.delete(itemID);
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
	public List<BuybackContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(BuybackContractTemplate.class,
					criteria, startRow, endRow);
		}
		return buybackPactManager.doQuery(values, startRow, endRow);
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
	public List<BuybackContractTemplate> getList(int startRow, int endRow) {
		return buybackPactManager.getList(startRow, endRow);
	}

}
