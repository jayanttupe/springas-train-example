package com.skynet.spms.manager.contractManagement.supplierContactTemplate.repairInspectClaimContractTemplate;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplate;

public interface IRepairInspectClaimContractManager {
	public void addRepairInspectClaimContractTemplate(
			RepairInspectClaimContractTemplate o);

	public RepairInspectClaimContractTemplate updateRepairInspectClaimContractTemplate(
			Map<String, Object> newValues, String itemID);

	public void deleteRepairInspectClaimContractTemplate(String itemID);

	public List<RepairInspectClaimContractTemplate> queryRepairInspectClaimContractTemplateList(
			int startRow, int endRow);

	public RepairInspectClaimContractTemplate getRepairInspectClaimContractTemplateById(
			String sheetId);
	
	
	/**
	 * 计算合同总金额
	 * @param contractID
	 * @param amount
	 */
	public void updateContractAmount(String contractID,Float amount);
}
