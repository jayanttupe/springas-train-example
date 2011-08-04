package com.skynet.spms.action.supplierSupport.repairInspectClaimContractTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.contractManagement.supplierContactTemplate.repairInspectClaimContractTemplate.IRepairInspectClaimContractManager;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplate;

/**
 * 修理检验合同管理
 * 
 * @author tqc
 */
@Component
public class RepairInspectClaimContractTemplateAction implements
		DataSourceAction<RepairInspectClaimContractTemplate> {

	@Resource
	IRepairInspectClaimContractManager manager;

	public String[] getBindDsName() {
		return new String[] { "repairInspectClaimContractTemplate_datasource" };
	}

	public void insert(RepairInspectClaimContractTemplate item) {
		manager.addRepairInspectClaimContractTemplate(item);
	}

	public List<RepairInspectClaimContractTemplate> getList(int startRow,
			int endRow) {
		return manager.queryRepairInspectClaimContractTemplateList(startRow,
				endRow);
	}

	public RepairInspectClaimContractTemplate update(
			Map<String, Object> newValues, String itemID) {
		return manager.updateRepairInspectClaimContractTemplate(newValues,
				itemID);
	}

	public void delete(String itemID) {
		manager.deleteRepairInspectClaimContractTemplate(itemID);
	}

	public List<RepairInspectClaimContractTemplate> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		if("getById".equals(values.get("key"))){
			String contractID=(String)values.get("contractID");
			List<RepairInspectClaimContractTemplate> result=new ArrayList<RepairInspectClaimContractTemplate>();
			result.add(manager.getRepairInspectClaimContractTemplateById(contractID));
			return result;
		}
		return null;
	}

}
