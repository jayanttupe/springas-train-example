package com.skynet.spms.client.gui.commonOrder;

/**
 * 用于检索合同的 key  
 * @author tqc
 *
 */
public enum ContractIndexKey {
	/**供应商送修合同**/
	repairInspectClaimContractTemplateManagerForOrder,
	/**供应商租赁合同**/
	SSLeaseContractTemplateManagerForOrder,
	/**供应商采购合同**/
	ProcurementContractTemplateManagerForOrder,
	/**(客户)销售合同**/
	SalesContractTemplateForOrder,
	/**客户租赁合同***/
	LeaseContractTemplateManagerForOrder,
	/**客户回购合同**/
	BuybackContractTemplateManagerForOrder,
	/**寄售补库申请单**/
	ConsignRenewManagerForOrder
}
