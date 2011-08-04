package com.skynet.spms.client.gui.base.i18n;

import com.google.gwt.i18n.client.Messages.DefaultMessage;

/**
 * 模块国际化常量
 * 
 * @author tqc
 * 
 */
public interface ModuleConst extends BaseConst {

	String title_partNumber();//件号
	String title_manufacturer();// 制造商
	String title_CustomerIdentificationCode();//询价客户名称
	String title_transferSheetNumber();//调拨单
	String title_supplier();//供应商名称
	String title_keyword();//关键字
	@DefaultMessage("申请单编号")
	String title_requisitionSheetNumber();//申请单编号
	@DefaultMessage("是")
	String title_yes();
	@DefaultMessage("否")
	String title_no();
	
		
	/**
	 * 客户询价
	 */
	String mod_salesInquiry_name();

	String mod_salesInquiry_desc();

	String mod_salesInquiry_list_title();

	String mod_salesInquiry_item_list_title();

	String mod_salesInquiry_item_name();
	
	String mod_salesInquiry_item_waitAdd();
	
	String mod_salesInquiry_item_firstSelect();
	

	/**
	 * 报价单跟踪
	 */
	String mod_salesQuotationSheet_name();

	String mod_salesQuotationSheet_desc();

	String mod_salesQuotationSheet_item_name();

	String mod_salesQuotationSheet_fdbtn_title();

	String mod_salesQuotationSheet_item_msg_firstSelect();

	/**
	 * 执行报价
	 */
	String mod_doQuotation_desc();
	
	/**
	 * 销售分段报价
	 */
	
	String mod_salesPiecewiseQuotation_name();
	
	/**
	 * 客户订单
	 */

	String mod_salesRequisitionSheet_name();

	String mod_salesRequisitionSheet_desc();

	String mod_salesRequisitionSheetItem_name();
	
	String mod_salesRequisitionSheetItem_waitAdd();
	
	String mod_salesRequisitionSheet_contractExists();
	
	@DefaultMessage("订单跟踪")
	String mod_salesRequisitionTrack_name();
	@DefaultMessage("订单跟踪详情")
	String mod_salesRequisitionTrack_view();
	@DefaultMessage("查询")
	String mod_salesRequisitionTrack_btnSearch();
	@DefaultMessage("订单信息")
	String mod_salesRequisitionTrack_view_lg_first();
	@DefaultMessage("订单明细")
	String mod_salesRequisitionTrack_view_lg_second();
	@DefaultMessage("物流信息")
	String mod_salesRequisitionTrack_view_lg_three();
	@DefaultMessage("装箱信息")
	String mod_salesRequisitionTrack_view_lg_four();
	@DefaultMessage("发票信息")
	String mod_salesRequisitionTrack_view_lg_five();
	

	/**
	 * 采购询价单
	 */
	String mod_procurementInquiry_name();
	String mod_procurementInquiry_desc();
	String mod_procurementInquiry_item_name();
	String mod_procurementOrder_msg_firstSelect();
	String mod_procurementInquiry_bJLink_title();
	
	/**
	 * 采购报价单
	 */
	String mod_procurementQuotation_name();
	String mod_procurementQuotation_desc();
	String mod_procurementQuotation_item_name();
	
	
	/**
	 * 比价
	 */
	String mod_procurementSuppliersParity_name();
	
	/**
	 * 调拨单
	 */
	String mod_transferOrder_name();
	String mod_transferOrder_desc();
	String mod_transferOrder_item_name();
	/**
	 * 送修module
	 */
	String mod_repairRequisition_name();

	String mod_repairRequisition_desc();

	String mod_repairRequisition_list_title();

	String mod_repairRequisitionSheet_tab_title();

	String mod_repairRequisitionSheetItem_tab_title();

	String mod_customerPartRepairDisassembleData_tab_title();

	String mod_customerInfo_btn();

	String mod_repairContract_name();

	String mod_customerRepairInsOrder_name();

	String mod_askCutomerConfirmationRepairInsOrder_name();

	String mod_consigneeShipAddressAddForm();

	String mod_recipeInvoiceAddressAddForm();

	String mod_showpartdocument();

	String mod_msg_please_publish_first();

	String mod_customer_name();

	String mod_isBuyerFreightAgent();

	/**
	 * 交换
	 * 
	 * @return
	 */
	String mod_exchangeRequisition_name();

	String mod_exchangeRequisition_desc();

	String mod_exchangeRequisition_list_title();

	String mod_exchangeRequisitionSheet_tab_title();

	String mod_exchangeRequisitionSheetItem_tab_title();

	// 备件计划需求
	String mod_partPlanNeedOrder_name();

	// 采购计划
	String mod_procurementPlan_name();

	// 采购指令
	String mod_procurementOrder_name();

	String mod_repairInspectClaimContractTemplate_name();

	String mod_contractProvision_name();

	/**
	 * 租赁申请单
	 */
	String mod_leaseRequisitionSheeet_name();

	/**
	 * 租赁合同
	 */
	String mod_leaseContract_name();

	/**
	 * 申请供应商租赁指令
	 */
	String mod_requisitionLeaseInstruct_name();

	/**
	 * 供应商租赁合同
	 */
	String mod_ssLeaseContract_name();

	/**
	 * 申请租赁指令
	 */
	String mod_LeaseInstruct_name();
	
	/**
	 *担保索赔
	 */
	@DefaultMessage("担保索赔申请单")
	String guarantee_name();
	@DefaultMessage("担保索赔申请单明细")
	String guarantee_item_name();
	
	
	
	
	/**
	 * 回购申请单
	 */
	@DefaultMessage("回购申请单")
	String mod_buyBackSheet_name();
	@DefaultMessage("回购申请单明细")
	String mod_buyBackSheet_item_name();
	
	/**
	 * 回购合同
	 */
	@DefaultMessage("回购合同")
	String mod_buyBackPact_name();
	@DefaultMessage("回购合同明细")
	String mod_buyBackPact_item_name();
	
	
	
	/**
	 * 寄售协议
	 */
	@DefaultMessage("寄售协议")
	String consignProtocol_name();
	@DefaultMessage("寄售协议明细")
	String consignProtocol_item_name();
	@DefaultMessage("协议基本信息")
    String consignProtocol_base_info_title();
	@DefaultMessage("地址信息")
	String consignProtocol_address_info_title();
	@DefaultMessage("协议条款")
	String cconsignProtocol_provision_title();
	
	/**
	 * 寄售补库
	 */
	@DefaultMessage("寄售补库信息")
	String consignRenew_name();
	@DefaultMessage("寄售补库明细")
	String consignRenew_item_name();
	@DefaultMessage("不能添加明细信息，请核对后再试！")
	String consignRenew_msg_notSaveItem();
	
	
	/**
	 * 供应商比价
	 */
	@DefaultMessage("供应商比价")
	String suppliersParity_name();
	@DefaultMessage("加入比价单")
	String suppliersParity_add();
	@DefaultMessage("已加入比价单")
	String suppliersParity_already_add();
	@DefaultMessage("撤销比价单")
	String suppliersParity_repeal();
	@DefaultMessage("查看比价单")
	String suppliersParity_view();
	@DefaultMessage("合同项列表")
	String suppliersParity_contract_item();
	@DefaultMessage("比价信息")
	String suppliersParity_title_info();
	@DefaultMessage("待绑定比价单")
	String suppliersParity_wait_bond();
	@DefaultMessage("比价单列表")
	String suppliersParity_list();
	@DefaultMessage("确认绑定")
	String suppliersParity_submit_bond();
	@DefaultMessage("删除绑定")
	String suppliersParity_del_bond();
}
