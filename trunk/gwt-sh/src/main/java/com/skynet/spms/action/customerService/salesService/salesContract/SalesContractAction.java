package com.skynet.spms.action.customerService.salesService.salesContract;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.salesService.salesContract.ISalesContractTemplateManager;
import com.skynet.spms.persistence.entity.contractManagement.template.SalesContractTemplate.SalesContractTemplate;

/**
 * 
 * 描述：采购合同
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Component
public class SalesContractAction implements
		DataSourceAction<SalesContractTemplate> {
	@Autowired
	private ISalesContractTemplateManager manange;
	@Autowired
	private ListGridFilterManager<SalesContractTemplate> filterManager;

	@Override
	public String[] getBindDsName() {
		/** 提供合同数据源 **/
		return new String[] { DSKey.C_SALESCONTRACT_DS };
	}

	/**
	 * 
	 * 描述：添加一条记录
	 * 
	 * @param o
	 *            销售合同实体
	 */
	@Override
	public void insert(SalesContractTemplate item) {
		manange.addSalesContractTemplate(item);
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
	public SalesContractTemplate update(Map<String, Object> newValues,
			String itemID) {
		return manange.updateSalesContractTemplate(newValues, itemID);
	}

	/**
	 * 描述： 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 *            被删除记录的UUID
	 */
	@Override
	public void delete(String itemID) {
		manange.deleteSalesContractTemplate(itemID);
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
	public List<SalesContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List criteria = (List) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(SalesContractTemplate.class,
					criteria, startRow, endRow);
		}
		return manange.doQuery(values, startRow, endRow);
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
	public List<SalesContractTemplate> getList(int startRow, int endRow) {
		return manange.querySalesContractTemplateList(startRow, endRow);
	}

}
