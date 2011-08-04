package com.skynet.spms.client.gui.customerService.common;

/**
 * @category 客户服务模块名及数据源维护常量
 * 
 * @author tqc
 * 
 */
public interface DSKey {
	/**
	 * 通用数据源
	 */
	public static final String C_COMMONMODULE = "common_module";
	public static final String C_CUSTOMERIDENTIFICATIONCODE = "CustomerIdentificationCode_dataSource";
	/**
	 * 客户业务——销售服务
	 */
	// 询价单
	public static final String C_SALESINQURIYSHEET = "customerService.salesService.salesInquirySheet";
	public static final String C_SALESINQURIYSHEET_DS = "salesInquirySheet_dataSource";
	public static final String C_SALESINQURIYSHEET_ITEM = "customerService.salesService.salesInquirySheet";
	public static final String C_SALESINQURIYSHEET_ITEM_DS = "salesInquirySheetItem_dataSource";

	// 报价
	public static final String C_DOQUOTATION = "customerService.salesService.doQuotation";
	public static final String C_DOQUOTATION_DS = "doQuotation_dataSource";
	public static final String C_DOQUOTATION_ITEM = "customerService.salesService.doQuotation";
	public static final String C_DOQUOTATION_ITEM_DS = "doQuotationItem_dataSource";

	// 报价单
	public static final String C_SALESQUOTATIONSHEET = "customerService.salesService.salesQuotationSheet";
	public static final String C_SALESQUOTATIONSHEET_DS = "salesQuotationSheet_dataSource";
	public static final String C_SALESQUOTATIONSHEET_ITEM = "customerService.salesService.salesQuotationSheet";
	public static final String C_SALESQUOTATIONSHEET_ITEM_DS = "salesQuotationSheetItem_dataSource";

	// 销售分段报价单
	public static final String C_SALESPIECEWISEQUOTATION_ITEM = "customerService.salesService.salesQuotationSheet";
	public static final String C_SALESPIECEWISEQUOTATION_ITEM_DS = "SalesPiecewiseQuotationItem_datasource";
	// 销售订单常量
	/** 销售订单 **/
	public static final String C_SALESREQUISITIONSHEET = "customerService.salesService.salesRequisitionSheet";
	/** 销售订单数据源 **/
	public static final String C_SALESREQUISITIONSHEET_DS = "salesRequisitionSheet_dataSource";
	/** 销售订单明细 **/
	public static final String C_SALESREQUISITIONSHEET_ITEM = "customerService.salesService.salesRequisitionSheet";
	/** 机尾号 */
	public static final String AIRCRAFTREGISTRATION_SD = "aircraftRegistration_dataSource";

	/** 销售订单明细数据源 **/
	public static final String C_SALESREQUISITIONSHEET_ITEM_DS = "salesRequisitionSheetItem_dataSource";
	/** 销售合同 */
	public static final String C_SALESCONTRACT = "customerService.salesService.salesContract";
	/** 销售合同 数据源 */
	public static final String C_SALESCONTRACT_DS = "saleContract_dataSource";
	/** 销售合同明细请单数据源 */
	public static final String C_SALESCONTRACTITEM_DS = "saleContractItem_dataSource";
	/** address数据源 */
	public static final String C_SALESADDRESS_DS = "saleAddress_dataSource";
	/** 附件 数据源 */
	public static final String C_SALESATTACHMENT_DS = "salesattachment_dataSource";
	// 销售发货指令
	public static final String C_SALESDELIVERYORDER = "customerService.salesService.salesDeliveryOrder";

	/**
	 * 供应商业务——采购业务
	 */
	// 库存安全策略
	public static final String C_STOCKSECURITYORDER = "supplierSupport.procurementOrder.stockSecurityOrder";

	// 备件计划需求
	public static final String C_PARTPLANNEEDORDER = "supplierSupport.procurementOrder.partPlanNeedOrder";

	// 采购计划
	public static final String C_PROCUREMENTPLAN = "supplierSupport.procurementOrder.procurementPlan";

	// 采购指令
	public static final String C_PROCUREMENTORDER = "supplierSupport.procurementOrder.procurementOrder";

	// 执行采购
	public static final String C_DOPROCUREMENTORDER = "supplierSupport.procurementOrder.doProcurementOrder";

	// 采购合同
	public static final String C_PROCUREMENTCONTRACT = "supplierSupport.procurementOrder.procurementContract";

	// 采购发货
	public static final String C_PROCUREMENTDELIVERYORDER = "supplierSupport.procurementOrder.procurementDeliveryOrder";

	// 采购提货
	public static final String C_PROCUREMENTPICKUPORDER = "supplierSupport.procurementOrder.procurementPickupOrder";

	// 送修申请单
	public static final String C_REPAIRREQUISITIONSHEET = "customerService.repairService.repairRequisitionSheet";
	public static final String C_REPAIRREQUISITIONSHEET_DS = "repairRequisitionSheet_dataSource";
	public static final String C_REPAIRREQUISITIONSHEET_ITEM_DS = "repairRequisitionSheetItem_dataSource";
	public static final String CUSTOMERPARTREPAIRDISASSEMBLEDATA_DATASOURCE = "customerPartRepairDisassembleData_dataSource";

	// 送修合同
	public static final String C_REPAIRECONTRACT = "customerService.repairService.repaireContract";
	public static final String C_REPAIRECONTRACT_DS = "repaireContract_dataSource";
	public static final String C_REPAIRECONTRACT_FOR_LIST_GRID_DS = "repaireContract_for_list_grid_dataSource";
	public static final String C_InspectOutlayRecordForm_DS = "customerInspectOutlayRecord_datasource";
	public static final String C_InspectOutlayRecordFormItem_DS = "customerInspectOutlayRecordItem_datasource";

	public static final String C_RepairQuoteRecord_DS = "customerRepairQuteRecord_datasource_B";
	public static final String C_RepairQuoteRecordItem_DS = "customerRepairQuteRecordItem_datasource_B";

	// 送修送检指令
	public static final String C_CUSTOMERREPAIRINSORDER = "customerService.repairService.customerRepairInsOrder";
	public static final String C_CUSTOMERREPAIRINSORDER_DS = "customerRepairInsOrder_datasource";
	public static final String C_CUSTOMERREPAIRINSORDER_GTA_DS = "enterpriseGTA_datasource";

	// 客户确认修理指令
	public static final String C_AffirmRepaireOrder_DS = "AffirmRepaireOrder_datasource";

	// 租赁申请单
	public static final String C_LEASEREQUISITIONSHEET = "customerService.leaseService.leaseRequisitionSheet";
	public static final String C_LEASEREQUISITIONSHEET_DS = "leaseRequisitionSheet_dataSource";
	public static final String C_LEASEREQUISITIONSHEET_ITEM = "customerService.leaseService.leaseRequisitionSheet";
	public static final String C_LEASEREQUISITIONSHEET_ITEM_DS = "leaseRequisitionSheetItem_dataSource";

	// 租赁合同
	public static final String C_LEASECONTRACT = "customerService.leaseService.leaseContract";
	public static final String C_LEASECONTRACT_DS = "leaseContract_dataSource";
	public static final String C_LEASECONTRACT_ITEM = "customerService.leaseService.leaseContract";
	public static final String C_LEASECONTRACT_TIEM_DS = "leaseContractItem_dataSource";
	public static final String C_LEASEADDRESS_DS = "lease_address_dataSource";
	public static final String C_LEASEATTACHMENT_DS = "lease_attachment_op_dataSource";

	// 申请供应商租赁指令
	public static final String C_LEASEINSTRUCT = "customerService.leaseService.LeaseInstruct";
	public static final String C_LEASEINSTRUCT_DS = "LeaseInstruct_dataSource";
	// 发货指令
	public static final String C_LEASEDELIVERYORDER = "customerService.leaseService.LeaseDeliveryOrder";
	// 提货指令
	public static final String C_LEASEPICKUPORDER = "customerService.leaseService.LeasePickUpOrder";

	// 回购服务

	/** 回购服务 **/
	public static final String C_BUYBACKSERVICE = "customerService.buyBackService";

	/** -------------------- 回购申请单 */
	public static final String C_BUYBACKSHEET = "customerService.buyBackService.buyBackSheet";
	public static final String C_BUYBACKSHEET_PLAT = "customerplat.buybackSheetService";
	/** 回购申请单 数据源 */
	public static final String C_BUYBACKSHEET_DS = "buyBackSheet_dataSource";
	/** 回购申明细请单 数据源 */
	public static final String C_BUYBACKSHEETITEM_DS = "buyBackSheetItem_dataSource";

	/** ---------------------- 回购合同 */
	public static final String C_BUYBACKPACT = "customerService.buyBackService.buyBackPact";
	/** 回购合同 数据源 */
	public static final String C_BUYBACKPACT_DS = "buyBackPact_dataSource";
	/** 回购合同明细请单 数据源 */
	public static final String C_BUYBACKPACTITEM_DS = "buyBackPactItem_dataSource";

	/** --------------------- 回购提货指令 */
	public static final String C_BUYBACKORDER = "customerService.buyBackService.buyBackPickOrder";

	/** 回购提货指令 数据源 */
	public static final String C_BUYBACKORDER_DS = "buyBackOrder_dataSource";
	/** 回购提货指令明细请单 数据源 */
	public static final String C_BUYBACKORDERITEM_DS = "buyBackOrderItem_dataSource";

	// 担保索赔
	/** ---------------------- 担保索赔 */
	public static final String C_GUARANTEECLAIMSERVICE = "customerService.guaranteeClaimService";

	/** ---------------------担保索赔申请单 */
	public static final String C_GUARANTEESHEET = "customerService.guaranteeClaimService.guaranteeSheet";
	public static final String C_GUARANTEESHEET_PLAT = "customerplat.guaranteeForm";
	/** 担保索赔数据源 */
	public static final String C_GUARANTEESHEET_DS = "guaranteeSheet_dataSource";
	/** 担保索赔明细请单 数据源 */
	public static final String C_GUARANTEESHEETITEM_DS = "guaranteeSheetItem_dataSource";
	/** 担保索赔附件数据源 **/
	public static final String C_GUARANTEE_ATTACHMENT_DS = "guarantee_attachment_op_dataSource";

	/** 回购地址信息数据源 */
	public static final String C_BUYBACK_ADDRESS_DS = "buyBack_address_dataSource";
	/** 回购附件数据源 **/
	public static final String C_BUYBACK_ATTACHMENT_DS = "buyBack_attachment_op_dataSource";

	/** 交换模块 **/
	public static final String C_EXCHANGE_REQUISITION = "customerService.exchangeRequisition.exchangeRequisition";
	/** 交换数据源 **/
	public static final String C_EXCHANGE_REQUISITION_DS = "exchangeRequisition_dataSource";
}
