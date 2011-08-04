package com.skynet.spms.client.gui.customerService.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.commonOrder.OrderBuilder;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.BuyBackPactPanel;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui.BuyBackSheetPanel;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.ui.GuaranteePanel;
import com.skynet.spms.client.gui.customerService.leaseService.leaseInstruct.RequisitionLeaseInstructPanel;
import com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet.LeaseRequisitionSheetPanel;
import com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui.LeaseContractPanel;
import com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder.CustomerRepairInsOrderPanel;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.RepairContractPanel;
import com.skynet.spms.client.gui.customerService.repairService.sheet.RepairRequisitionPanel;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetPanel;
import com.skynet.spms.client.gui.customerService.salesService.doQuotation.DoQuotationPanel;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.SalesContractPanel;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder.DoProcurementOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.PartPlanNeedOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.ProcurementContractPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.ProcurementOrderPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.ProcurementPlanPanel;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder.StockSecurityOrderPanel;
import com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.ExchangeRequisitionSheetPanel;
/**
 * 客户服务模块加载器
 * 
 * @author tqc
 * 
 */
public class CustomerServicePlug implements ModulePlug {

	public void plug(Map<String, PanelFactory> map) {
		/*************** 销售业务 **********************/
		//送修
		map.put(DSKey.C_REPAIRREQUISITIONSHEET,
				new RepairRequisitionPanel.Factory());
		// 报价
		map.put(DSKey.C_DOQUOTATION, new DoQuotationPanel.Factory());
		// 报价单管理
		map.put(DSKey.C_SALESQUOTATIONSHEET, new SalesQuotationPanel.Factory());
		// 客户订单
		map.put(DSKey.C_SALESREQUISITIONSHEET,
				new SalesRequisitionSheetPanel.Factory());
		// 销售合同
		map.put(DSKey.C_SALESCONTRACT, new SalesContractPanel.Factory());
		// 销售发货指令
		map.put(DSKey.C_SALESDELIVERYORDER,
				new OrderBuilder.Factory("2","salesservice",DSKey.C_SALESDELIVERYORDER));

		/*************** 采购业务 **********************/
		// 库存安全策略
		map.put(DSKey.C_STOCKSECURITYORDER,
				new StockSecurityOrderPanel.Factory());

		// 备件计划需求
		map.put(DSKey.C_PARTPLANNEEDORDER, new PartPlanNeedOrderPanel.Factory());

		// 采购计划
		map.put(DSKey.C_PROCUREMENTPLAN, new ProcurementPlanPanel.Factory());

		// 采购指令
		map.put(DSKey.C_PROCUREMENTORDER, new ProcurementOrderPanel.Factory());

		// 执行采购
		map.put(DSKey.C_DOPROCUREMENTORDER,
				new DoProcurementOrderPanel.Factory());


		// 采购合同
		map.put(DSKey.C_PROCUREMENTCONTRACT,
				new ProcurementContractPanel.Factory());

		/*// 采购发货
		map.put(DSKey.C_PROCUREMENTDELIVERYORDER,
				new OrderBuilder.Factory("2","procurement"));*/
		// 采购提货
		map.put(DSKey.C_PROCUREMENTPICKUPORDER, new OrderBuilder.Factory("1","procurement",DSKey.C_PROCUREMENTPICKUPORDER));


		map.put(DSKey.C_LEASEREQUISITIONSHEET,
				new LeaseRequisitionSheetPanel.Factory());
		
		// 客户租赁发货指令
		map.put(DSKey.C_LEASEDELIVERYORDER, new OrderBuilder.Factory("2","leaseservice",DSKey.C_LEASEDELIVERYORDER));
		map.put(DSKey.C_LEASEPICKUPORDER, new OrderBuilder.Factory("1","leaseservice",DSKey.C_LEASEPICKUPORDER));
		
		
		map.put(DSKey.C_REPAIRECONTRACT, new RepairContractPanel.Factory());
		
		map.put(DSKey.C_CUSTOMERREPAIRINSORDER,
				new CustomerRepairInsOrderPanel.Factory());
		//回购
		map.put(DSKey.C_BUYBACKSHEET, new BuyBackSheetPanel.Factory());
		map.put(DSKey.C_BUYBACKPACT, new BuyBackPactPanel.Factory());
		map.put(DSKey.C_BUYBACKORDER, new OrderBuilder.Factory("1","customerbuyback",DSKey.C_BUYBACKORDER));

		map.put(DSKey.C_GUARANTEESHEET, new GuaranteePanel.Factory());
		map.put(DSKey.C_LEASECONTRACT, new LeaseContractPanel.Factory());
		// 申请供应商租赁指令
		map.put(DSKey.C_LEASEINSTRUCT,
				new RequisitionLeaseInstructPanel.Factory());
		
		map.put(DSKey.C_EXCHANGE_REQUISITION,new ExchangeRequisitionSheetPanel.Factory());
		

	}
}
