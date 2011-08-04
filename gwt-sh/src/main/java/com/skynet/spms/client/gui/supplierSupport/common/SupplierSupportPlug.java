package com.skynet.spms.client.gui.supplierSupport.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.commonOrder.OrderBuilder;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.ConsignProtocolPanel;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.ConsignRenewPanel;
import com.skynet.spms.client.gui.supplierSupport.customerRepairInsOrder.CustomerRepairInsOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract.SSLeaseContractPanel;
import com.skynet.spms.client.gui.supplierSupport.leaseService.leaseInstruct.LeaseInstructPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.PartPlanNeedOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.ProcurementInquirySheetPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.ProcurementOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.ProcurementPlanPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.TransferOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.RepairInspectClaimContractTemplatePanel;

/**
 * 供应商服务模块加载器
 * 
 * @author tqc
 * 
 */
public class SupplierSupportPlug implements ModulePlug {

	public void plug(Map<String, PanelFactory> map) {
		map.put(DSKey.C_USTOMERREPAIRINSORDER,
				new CustomerRepairInsOrderPanel.Factory());
		map.put(DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE,
				new RepairInspectClaimContractTemplatePanel.Factory());
		//寄售
		map.put(DSKey.S_CONSIGNPROTOCOL, new ConsignProtocolPanel.Factory());
		map.put(DSKey.S_CONSIGNRENEW, new ConsignRenewPanel.Factory());
		map.put(DSKey.S_CONSIGNORDER, new OrderBuilder.Factory("1","consignrenew",DSKey.S_CONSIGNORDER));

		
		map.put(DSKey.S_LEASECONTRACT, new SSLeaseContractPanel.Factory());
		
		//修理发货
		map.put(DSKey.R_REPAIRDELIVERYORDER,new OrderBuilder.Factory("2","repair",DSKey.R_REPAIRDELIVERYORDER));
		//修理提货
		map.put(DSKey.R_REPAIRPICKORDER, new OrderBuilder.Factory("1","repair",DSKey.R_REPAIRPICKORDER));
		
		
		// 申请租赁指令
		map.put(DSKey.S_LEASEINSTRUCT, new LeaseInstructPanel.Factory());
		
		// 租赁提货指令
		map.put(DSKey.S_LEASEPICKORDER, new OrderBuilder.Factory("1","lease",DSKey.S_LEASEPICKORDER));
		// 租赁发货指令
		map.put(DSKey.S_LEASEDELIVERYORDER,new OrderBuilder.Factory("2","lease",DSKey.S_LEASEDELIVERYORDER));
		// 库存安全策略
//		map.put(DSKey.S_SAFETYSTOCKSTRATEGY,
//				new StockSecurityOrderPanel.Factory());
		// 备件计划需求
		map.put(DSKey.S_PARTREQUIREMENT, new PartPlanNeedOrderPanel.Factory());
		// 采购计划
		map.put(DSKey.S_PROCUREMENTPLAN, new ProcurementPlanPanel.Factory());
		
		
		
		// 采购指令
		map.put(DSKey.S_PROCUREMENTORDER, new ProcurementOrderPanel.Factory());
		
		
		// 采购询价单
		map.put(DSKey.S_PROCUREMENTINQUIRYSHEET,
				new ProcurementInquirySheetPanel.Factory());
		// 调拨单
		map.put(DSKey.S_PROCUREMENTTRANSFERORDER, new TransferOrderPanel.Factory());

		
		
	}
}
