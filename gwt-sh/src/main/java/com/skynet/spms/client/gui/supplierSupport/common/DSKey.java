package com.skynet.spms.client.gui.supplierSupport.common;

/**
 * @category 供应商模块名及数据源维护常量
 * 
 * @author tqc
 * 
 */
public interface DSKey {

	/** 送修送检指令 * */
	public static final String C_USTOMERREPAIRINSORDER = "supplierSupport.repairClaim.customerRepairInsOrder";

	/** 供应商修理检验/索赔合同模板 * */
	public static final String R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE = "supplierSupport.repairClaim.repairInspectClaimContractTemplate";
	public static final String R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE_DS = "repairInspectClaimContractTemplate_datasource";
	public static final String R_SUPPLIERINSPECTOUTLAYRECORD_DS = "supplierInspectOutlayRecord_datasource";
	public static final String R_SUPPLIERINSPECTOUTLAYRECORDITEM_DS = "supplierInspectOutlayRecordItem_datasource";
	
	
	public static final String R_REPAIRQUOTERECORD_DS = "supplierRepairQuteRecord_datasource";
	public static final String R_REPAIRQUOTERECORDITEM_DS = "supplierRepairQuteRecordItem_datasource";

	/** 修理发货指令 * */
	public static final String R_REPAIRDELIVERYORDER = "supplierSupport.repairClaim.repairDeliveryOrder";
	/** 修理提货指令 * */
	public static final String R_REPAIRPICKORDER = "supplierSupport.repairClaim.repairPickOrder";

	// 寄售业务
	/** 寄售业务 */
	public static final String S_CONSIGNMENT = "supplierSupport.consignment";
	/** 寄售协议 */
	public static final String S_CONSIGNPROTOCOL = "supplierSupport.consignment.consignProtocol";
	/** 寄售协议 数据源 */
	public static final String S_CONSIGNPROTOCOL_DS = "consignProtocol_dataSource";
	/** 寄售协议明细请单 数据源 */
	public static final String S_CONSIGNPROTOCOLITEM_DS = "consignProtocolItem_dataSource";
	/** 寄售协议附件数据源 */
	public static final String S_CONSIGNATTACH_DS = "consignProtocolAttach_dataSource";

	/** 寄售补库 */
	public static final String S_CONSIGNRENEW = "supplierSupport.consignment.consignRenew";
	/** 寄售补库 数据源 */
	public static final String S_CONSIGNRENEW_DS = "consignRenew_dataSource";
	/** 寄售补库明细请单 数据源 */
	public static final String S_CONSIGNRENEWITEM_DS = "consignRenewItem_dataSource";
	/** 寄售补库地址数据源 */
	public static final String S_CONSIGNRENEWADDR = "consignRenew_address_dataSource";
	/** 寄售补库库房数据源 */
	public static final String S_CONSIGNRENEW_SOCKROOM = "consignRenew_SockRoom";

	/** 提货指令 */
	public static final String S_CONSIGNORDER = "supplierSupport.consignment.consignOrder";
	/** 提货指令 数据源 */
	public static final String S_CONSIGNORDER_DS = "consignOrder_dataSource";
	/** 提货指令明细请单 数据源 */
	public static final String S_CONSIGNORDERITEM_DS = "consignOrderItem_dataSource";
	/** 供应商租赁合同 * */
	public static final String S_LEASECONTRACT = "supplierSupport.LeaseService.LeaseContract";
	/** 供应商租赁合同数据源 * */
	public static final String S_LEASECONTRACT_DS = "SSLeaseContract_datasource";
	/** 供应商租赁合同明细数据源 * */
	public static final String S_LEASECONTRACTITEM_DS = "SSLeaseContractItem_datasource";
	/** 合同地址数据源 * */
	public static final String S_LEASEADDRESS_DS = "SSlease_address_dataSource";
	/** 合同附件数据源 * */
	public static final String S_LEASEATTACHMENT_DS = "SSlease_attachment_op_dataSource";
	/** 租赁提货指令 * */
	public static final String S_LEASEPICKORDER = "supplierSupport.LeaseService.LeasePickOrder";
	/** 租赁发货指令 * */
	public static final String S_LEASEDELIVERYORDER = "supplierSupport.LeaseService.LeaseDeliveryOrder";
	/** 申请租赁指令 * */
	public static final String S_LEASEINSTRUCT = "supplierSupport.LeaseService.LeaseInstruct";

	// 供应商采购业务
	/** 库存安全策略 * */
	public static final String S_SAFETYSTOCKSTRATEGY = "supplierSupport.procurementOrder.SafetyStockStrategy";
	/** 库存安全策略数据源 * */
	public static final String S_SAFETYSTOCKSTRATEGY_DS = "SafetyStockStrategy_datasource";
	/** 备件计划需求 * */
	public static final String S_PARTREQUIREMENT = "supplierSupport.procurementOrder.PartRequirement";
	/** 备件计划需求 数据源 * */
	public static final String S_PARTREQUIREMENT_DS = "PartRequirement_datasource";
	/** 备件需求附件数据源 **/
	public static final String S_PARTREQUIREMENT_ATTACHMENT_DS = "PartRequirement_attachment_dataSource";
	/** 采购计划 * */
	public static final String S_PROCUREMENTPLAN = "supplierSupport.procurementOrder.ProcurementPlan";
	/** 采购计划数据源 * */
	public static final String S_PROCUREMENTPLAN_DS = "ProcurementPlan_datasource";
	/** 采购计划明细数据源 * */
	public static final String S_PROCUREMENTPLANITEM_DS = "ProcurementPlanItem_datasource";
	/**采购计划附件数据源**/
	public static final String S_PROCUREMENTPLAN_ATTACHMENT_DS="ProcurementPlan_attachment_dataSource";
	/** 采购指令 * */
	public static final String S_PROCUREMENTORDER = "supplierSupport.procurementOrder.ProcurementOrder";
	/** 采购指令数据源 * */
	public static final String S_PROCUREMENTORDER_DS = "ProcurementOrder_datasource";
	/** 采购指令项数据源 * */
	public static final String S_PROCUREMENTORDERITEM_DS = "ProcurementOrderItem_datasource";

	/** 执行采购 * */
	public static final String S_DOPROCUREMENTORDER = "supplierSupport.procurementOrder.doProcurementOrder";

	// 采购询价单
	public static final String S_PROCUREMENTINQUIRYSHEET = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PROCUREMENTINQUIRYSHEET_DS = "procurementInquirySheet_datasource";
	public static final String S_PROCUREMENTINQUIRYSHEET_ITEM = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PROCUREMENTINQUIRYSHEET_ITEM_DS = "procurementInquirySheetItem_datasource";
	
	public static final String S_SUPPLIERCODE_DS="supplierCode_dataSource";

	// 采购比价单
	public static final String S_PRICECOMPARISONSSHEETREPORT = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PRICECOMPARISONSSHEETREPORT_DS = "ProcurementOrderItem_datasource";

	// 采购报价单
	public static final String S_PROCUREMENTQUOTATIONHEET = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PROCUREMENTQUOTATIONHEET_DS = "procurementQuotationSheet_datasource";
	public static final String S_PROCUREMENTQUOTATIONHEET_ITEM = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PROCUREMENTQUOTATIONHEET_ITEM_DS = "procurementQuotationSheetItem_datasource";

	public static final String S_PROCUREMENTPIECEWISEQUOTATION_ITEM = "supplierSupport.procurementOrder.procurementInquirySheet";
	public static final String S_PROCUREMENTPIECEWISEQUOTATION_ITEM_DS = "ProcurementPiecewiseQuotationItem_datasource";

	// 调拨单
	public static final String S_PROCUREMENTTRANSFERORDER = "supplierSupport.procurementOrder.transferSheet";
	public static final String S_PROCUREMENTTRANSFERORDER_DS = "transferSheet_datasource";
	public static final String S_PROCUREMENTTRANSFERORDER_ITEM = "supplierSupport.procurementOrder.transferSheet";
	public static final String S_PROCUREMENTTRANSFERORDER_ITEM_DS = "transferSheetItem_datasource";
	public static final String S_CUSTOMERREPAIRINSORDER_GTA_DS = "enterpriseGTA_datasource";

	/** 采购合同* */
	public static final String S_PROCUREMENTCONTRACT = "supplierSupport.procurementOrder.procurementContract";
	/** 采购合同数据源 * */
	public static final String S_PROCUREMENTCONTRACT_DS = "ProcurementContract_datasource";
	/** 采购合同明细项数据源 * */
	public static final String S_PROCUREMENTCONTRACTITEM_DS = "ProcurementContractItem_datasource";
	/** * 采购合同地址信息 */
	public static final String S_PROCUREMENT_ADDRESS_DS = "procurement_address_dataSource";
	/** 附件数据源 * */
	public static final String S_PROCUREMENT_ATTACHMENT_DS = "procurement_attachment_op_dataSource";

	/** GTA 协议数据源 */
	public static final String C_REPAIRREQUISITIONSHEET = "customerService.repairService.repairRequisitionSheet";
	public static final String C_CUSTOMERREPAIRINSORDER_GTA_DS = "enterpriseGTA_datasource";
	
}
