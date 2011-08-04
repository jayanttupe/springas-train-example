package com.skynet.spms.action.customerService.repairService.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.contractManagement.customerContactTemplate.repaireContractTemplate.IRepairContractTemplateManager;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.RepaireContractTemplate.RepairContractTemplate;

/**
 * 送修客户合同数据源
 * 
 * @author tqc
 */
@Component
public class RepairContractTemplateDatasourceAction implements
		DataSourceAction<RepairContractTemplate> {

	@Autowired
	private IRepairContractTemplateManager manager;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "repaireContract_dataSource",
				"repaireContract_for_list_grid_dataSource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(RepairContractTemplate item) {
		manager.addRepairContractTemplate(item);
	}

	/**
	 * 分页查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<RepairContractTemplate> getList(int startRow, int endRow) {
		List<RepairContractTemplate> result = manager
				.queryRepairContractTemplateList(startRow, endRow);
		return result;
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public RepairContractTemplate update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateSRepairContractTemplate(newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteRepairContractTemplate(itemID);
	}

	/**
	 * 高级查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<RepairContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("_key"))) {
			return this.getList(startRow, endRow);
		}
		if ("getById".equals(values.get("key"))) {
			String contractID = (String) values.get("contractID");
			List<RepairContractTemplate> result = new ArrayList<RepairContractTemplate>();
			result.add(manager.getRepairContractTemplateById(contractID));
			return result;
		}
		return null;
	}

}
