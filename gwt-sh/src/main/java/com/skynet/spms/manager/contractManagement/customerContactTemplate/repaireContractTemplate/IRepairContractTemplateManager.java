package com.skynet.spms.manager.contractManagement.customerContactTemplate.repaireContractTemplate;

import java.util.List;
import java.util.Map;

import com.skynet.spms.client.vo.ContractState;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.RepaireContractTemplate.RepairContractTemplate;

/**
 * 修理合同业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface IRepairContractTemplateManager {
	/**
	 * 添加合同
	 *
	 * @param  @param o   
	 * @return void
	 */
	public void addRepairContractTemplate(RepairContractTemplate o);

	/**
	 * 更新合同
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return RepairContractTemplate
	 */
	public RepairContractTemplate updateSRepairContractTemplate(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除合同
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairContractTemplate(String itemID);

	/**
	 * 分页显示合同
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<RepairContractTemplate>
	 */
	public List<RepairContractTemplate> queryRepairContractTemplateList(
			int startRow, int endRow);

	/**
	 * 根据合同编号查询
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairContractTemplate
	 */
	public RepairContractTemplate getRepairContractTemplateById(String sheetId);

	/**
	 * 查询合同状态
	 * 
	 * @param @param itemID
	 * @param @return
	 * @return ContractState
	 */
	public ContractState getContractState(String itemID);

	/**
	 * 更新合同总金额
	 * 
	 * @param @param contractID
	 * @param @param amount
	 * @return void
	 */
	public void updateContractAmount(String contractID, Float amount);

}
